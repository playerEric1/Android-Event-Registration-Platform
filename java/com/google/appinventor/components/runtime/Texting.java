package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.SmsBroadcastReceiver;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

@SimpleObject
@UsesPermissions(permissionNames = "android.permission.RECEIVE_SMS, android.permission.SEND_SMS, com.google.android.apps.googlevoice.permission.RECEIVE_SMS, com.google.android.apps.googlevoice.permission.SEND_SMS, android.permission.ACCOUNT_MANAGER, android.permission.MANAGE_ACCOUNTS, android.permission.GET_ACCOUNTS, android.permission.USE_CREDENTIALS")
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "<p>A component that will, when the <code>SendMessage</code> method is called, send the text message specified in the <code>Message</code> property to the phone number specified in the <code>PhoneNumber</code> property.</p> <p>If the <code>ReceivingEnabled</code> property is set to 1 messages will <b>not</b> be received. If <code>ReceivingEnabled</code> is set to 2 messages will be received only when the application is running. Finally if <code>ReceivingEnabled</code> is set to 3, messages will be received when the application is running <b>and</b> when the application is not running they will be queued and a notification displayed to the user.</p> <p>When a message arrives, the <code>MessageReceived</code> event is raised and provides the sending number and message.</p> <p> An app that includes this component will receive messages even when it is in the background (i.e. when it's not visible on the screen) and, moreso, even if the app is not running, so long as it's installed on the phone. If the phone receives a text message when the app is not in the foreground, the phone will show a notification in the notification bar.  Selecting the notification will bring up the app.  As an app developer, you'll probably want to give your users the ability to control ReceivingEnabled so that they can make the phone ignore text messages.</p> <p>If the GoogleVoiceEnabled property is true, messages can be sent over Wifi using Google Voice. This option requires that the user have a Google Voice account and that the mobile Voice app is installed on the phone. The Google Voice option works only on phones that support Android 2.0 (Eclair) or higher.</p> <p>To specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces may not be included.</p> <p>Another way for an app to specify a phone number would be to include a <code>PhoneNumberPicker</code> component, which lets the users select a phone numbers from the ones stored in the the phone's contacts.</p>", iconName = "images/texting.png", nonVisible = true, version = 3)
@UsesLibraries(libraries = "google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar")
/* loaded from: classes.dex */
public class Texting extends AndroidNonvisibleComponent implements Component, OnResumeListener, OnPauseListener, OnInitializeListener, OnStopListener {
    private static final String CACHE_FILE = "textingmsgcache";
    public static final String GV_INTENT_FILTER = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_PACKAGE_NAME = "com.google.android.apps.googlevoice";
    private static final String GV_SERVICE = "grandcentral";
    public static final String GV_SMS_RECEIVED = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_SMS_SEND_URL = "https://www.google.com/voice/b/0/sms/send/";
    public static final String GV_URL = "https://www.google.com/voice/b/0";
    private static final String MESSAGE_DELIMITER = "\u0001";
    public static final String MESSAGE_TAG = "com.google.android.apps.googlevoice.TEXT";
    public static final String META_DATA_SMS_KEY = "sms_handler_component";
    public static final String META_DATA_SMS_VALUE = "Texting";
    public static final String PHONE_NUMBER_TAG = "com.google.android.apps.googlevoice.PHONE_NUMBER";
    private static final String PREF_FILE = "TextingState";
    private static final String PREF_GVENABLED = "gvenabled";
    private static final String PREF_RCVENABLED = "receiving2";
    private static final String PREF_RCVENABLED_LEGACY = "receiving";
    private static final String SENT = "SMS_SENT";
    private static final int SERVER_TIMEOUT_MS = 30000;
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String TAG = "Texting Component";
    public static final String TELEPHONY_INTENT_FILTER = "android.provider.Telephony.SMS_RECEIVED";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13";
    private static final String UTF8 = "UTF-8";
    private static Activity activity;
    private static Component component;
    private static boolean isRunning;
    private static int messagesCached;
    private String authToken;
    private ComponentContainer container;
    private boolean googleVoiceEnabled;
    private GoogleVoiceUtil gvHelper;
    private boolean isInitialized;
    private String message;
    private Queue<String> pendingQueue;
    private String phoneNumber;
    private SmsManager smsManager;
    private static int receivingEnabled = 2;
    private static Object cacheLock = new Object();

    public Texting(ComponentContainer container) {
        super(container.$form());
        this.pendingQueue = new ConcurrentLinkedQueue();
        Log.d(TAG, "Texting constructor");
        this.container = container;
        component = this;
        activity = container.$context();
        SharedPreferences prefs = activity.getSharedPreferences(PREF_FILE, 0);
        if (prefs != null) {
            receivingEnabled = prefs.getInt(PREF_RCVENABLED, -1);
            if (receivingEnabled == -1) {
                if (prefs.getBoolean(PREF_RCVENABLED_LEGACY, true)) {
                    receivingEnabled = 2;
                } else {
                    receivingEnabled = 1;
                }
            }
            this.googleVoiceEnabled = prefs.getBoolean(PREF_GVENABLED, false);
            Log.i(TAG, "Starting with receiving Enabled=" + receivingEnabled + " GV enabled=" + this.googleVoiceEnabled);
        } else {
            receivingEnabled = 2;
            this.googleVoiceEnabled = false;
        }
        if (this.googleVoiceEnabled) {
            new AsyncAuthenticate().execute(new Void[0]);
        }
        this.smsManager = SmsManager.getDefault();
        PhoneNumber("");
        this.isInitialized = false;
        isRunning = false;
        container.$form().registerForOnInitialize(this);
        container.$form().registerForOnResume(this);
        container.$form().registerForOnPause(this);
        container.$form().registerForOnStop(this);
    }

    @Override // com.google.appinventor.components.runtime.util.OnInitializeListener
    public void onInitialize() {
        Log.i(TAG, "onInitialize()");
        this.isInitialized = true;
        isRunning = true;
        processCachedMessages();
        NotificationManager nm = (NotificationManager) activity.getSystemService("notification");
        nm.cancel(SmsBroadcastReceiver.NOTIFICATION_ID);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void PhoneNumber(String phoneNumber) {
        Log.i(TAG, "PhoneNumber set: " + phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number that the message will be sent to when the SendMessage method is called. The number is a text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces should not be included.")
    public String PhoneNumber() {
        return this.phoneNumber;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The message that will be sent when the SendMessage method is called.")
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Message(String message) {
        Log.i(TAG, "Message set: " + message);
        this.message = message;
    }

    @SimpleProperty
    public String Message() {
        return this.message;
    }

    @SimpleFunction
    public void SendMessage() {
        Log.i(TAG, "Sending message " + this.message + " to " + this.phoneNumber);
        String phoneNumber = this.phoneNumber;
        String message = this.message;
        if (this.googleVoiceEnabled) {
            if (this.authToken == null) {
                Log.i(TAG, "Need to get an authToken -- enqueing " + phoneNumber + " " + message);
                boolean ok = this.pendingQueue.offer(phoneNumber + ":::" + message);
                if (!ok) {
                    Toast.makeText(activity, "Pending message queue full. Can't send message", 0).show();
                    return;
                } else if (this.pendingQueue.size() == 1) {
                    new AsyncAuthenticate().execute(new Void[0]);
                    return;
                } else {
                    return;
                }
            }
            Log.i(TAG, "Creating AsyncSendMessage");
            new AsyncSendMessage().execute(phoneNumber, message);
            return;
        }
        Log.i(TAG, "Sending via SMS");
        sendViaSms();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPendingQueue() {
        while (this.pendingQueue.size() != 0) {
            String entry = this.pendingQueue.remove();
            String phoneNumber = entry.substring(0, entry.indexOf(":::"));
            String message = entry.substring(entry.indexOf(":::") + 3);
            Log.i(TAG, "Sending queued message " + phoneNumber + " " + message);
            new AsyncSendMessage().execute(phoneNumber, message);
        }
    }

    @SimpleEvent
    public static void MessageReceived(String number, String messageText) {
        if (receivingEnabled > 1) {
            Log.i(TAG, "MessageReceived from " + number + ":" + messageText);
            if (EventDispatcher.dispatchEvent(component, "MessageReceived", number, messageText)) {
                Log.i(TAG, "Dispatch successful");
                return;
            }
            Log.i(TAG, "Dispatch failed, caching");
            synchronized (cacheLock) {
                addMessageToCache(activity, number, messageText);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, then SendMessage will attempt to send messages over Wifi using Google Voice.  This requires that the Google Voice app must be installed and set up on the phone or tablet, with a Google Voice account.  If GoogleVoiceEnabled is false, the device must have phone and texting service in order to send or receive messages with this component.")
    public boolean GoogleVoiceEnabled() {
        return this.googleVoiceEnabled;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void GoogleVoiceEnabled(boolean enabled) {
        if (SdkLevel.getLevel() >= 5) {
            this.googleVoiceEnabled = enabled;
            SharedPreferences prefs = activity.getSharedPreferences(PREF_FILE, 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(PREF_GVENABLED, enabled);
            editor.commit();
            return;
        }
        Toast.makeText(activity, "Sorry, your phone's system does not support this option.", 1).show();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to 1 (OFF) no messages will be received.  If set to 2 (FOREGROUND) or3 (ALWAYS) the component will respond to messages if it is running. If the app is not running then the message will be discarded if set to 2 (FOREGROUND). If set to 3 (ALWAYS) and the app is not running the phone will show a notification.  Selecting the notification will bring up the app and signal the MessageReceived event.  Messages received when the app is dormant will be queued, and so several MessageReceived events might appear when the app awakens.  As an app developer, it would be a good idea to give your users control over this property, so they can make their phones ignore text messages when your app is installed.")
    public int ReceivingEnabled() {
        return receivingEnabled;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "2", editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXT_RECEIVING)
    public void ReceivingEnabled(int enabled) {
        if (enabled < 1 || enabled > 3) {
            this.container.$form().dispatchErrorOccurredEvent(this, META_DATA_SMS_VALUE, ErrorMessages.ERROR_BAD_VALUE_FOR_TEXT_RECEIVING, Integer.valueOf(enabled));
            return;
        }
        receivingEnabled = enabled;
        SharedPreferences prefs = activity.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREF_RCVENABLED, enabled);
        editor.remove(PREF_RCVENABLED_LEGACY);
        editor.commit();
    }

    public static int isReceivingEnabled(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_FILE, 0);
        int retval = prefs.getInt(PREF_RCVENABLED, -1);
        if (retval == -1) {
            return prefs.getBoolean(PREF_RCVENABLED_LEGACY, true) ? 2 : 1;
        }
        return retval;
    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length];
        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i2 = 0; i2 < pduCount; i2++) {
            pdus[i2] = pduObjs[i2];
            msgs[i2] = SmsMessage.createFromPdu(pdus[i2]);
        }
        return msgs;
    }

    private void processCachedMessages() {
        String[] messagelist;
        synchronized (cacheLock) {
            messagelist = retrieveCachedMessages();
        }
        if (messagelist != null) {
            Log.i(TAG, "processing " + messagelist.length + " cached messages ");
            for (int k = 0; k < messagelist.length; k++) {
                String phoneAndMessage = messagelist[k];
                Log.i(TAG, "Message + " + k + " " + phoneAndMessage);
                int delim = phoneAndMessage.indexOf(":");
                if (receivingEnabled > 1 && delim != -1) {
                    MessageReceived(phoneAndMessage.substring(0, delim), phoneAndMessage.substring(delim + 1));
                }
            }
        }
    }

    private String[] retrieveCachedMessages() {
        String[] messagelist = null;
        Log.i(TAG, "Retrieving cached messages");
        try {
            FileInputStream fis = activity.openFileInput(CACHE_FILE);
            byte[] bytes = new byte[8192];
            if (fis == null) {
                Log.e(TAG, "Null file stream returned from openFileInput");
            } else {
                int n = fis.read(bytes);
                Log.i(TAG, "Read " + n + " bytes from " + CACHE_FILE);
                String cache = new String(bytes, 0, n);
                try {
                    fis.close();
                    activity.deleteFile(CACHE_FILE);
                    messagesCached = 0;
                    Log.i(TAG, "Retrieved cache " + cache);
                    messagelist = cache.split(MESSAGE_DELIMITER);
                } catch (FileNotFoundException e) {
                    Log.e(TAG, "No Cache file found -- this is not (usually) an error");
                    return messagelist;
                } catch (IOException e2) {
                    e = e2;
                    Log.e(TAG, "I/O Error reading from cache file");
                    e.printStackTrace();
                    return messagelist;
                }
            }
        } catch (FileNotFoundException e3) {
        } catch (IOException e4) {
            e = e4;
        }
        return messagelist;
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static int getCachedMsgCount() {
        return messagesCached;
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        Log.i(TAG, "onResume()");
        isRunning = true;
        if (this.isInitialized) {
            processCachedMessages();
            NotificationManager nm = (NotificationManager) activity.getSystemService("notification");
            nm.cancel(SmsBroadcastReceiver.NOTIFICATION_ID);
        }
    }

    @Override // com.google.appinventor.components.runtime.OnPauseListener
    public void onPause() {
        Log.i(TAG, "onPause()");
        isRunning = false;
    }

    public static void handledReceivedMessage(Context context, String phone, String msg) {
        if (isRunning) {
            MessageReceived(phone, msg);
            return;
        }
        synchronized (cacheLock) {
            addMessageToCache(context, phone, msg);
        }
    }

    private static void addMessageToCache(Context context, String phone, String msg) {
        try {
            String cachedMsg = phone + ":" + msg + MESSAGE_DELIMITER;
            Log.i(TAG, "Caching " + cachedMsg);
            FileOutputStream fos = context.openFileOutput(CACHE_FILE, 32768);
            fos.write(cachedMsg.getBytes());
            fos.close();
            messagesCached++;
            Log.i(TAG, "Cached " + cachedMsg);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found error writing to cache file");
            e.printStackTrace();
        } catch (IOException e2) {
            Log.e(TAG, "I/O Error writing to cache file");
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class GoogleVoiceUtil {
        private final int MAX_REDIRECTS = 5;
        String authToken;
        String general;
        private boolean isInitialized;
        int redirectCounter;
        String rnrSEE;

        public GoogleVoiceUtil(String authToken) {
            Log.i(Texting.TAG, "Creating GV Util");
            this.authToken = authToken;
            try {
                this.general = getGeneral();
                Log.i(Texting.TAG, "general = " + this.general);
                setRNRSEE();
                this.isInitialized = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean isInitialized() {
            return this.isInitialized;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String sendGvSms(String smsData) {
            Log.i(Texting.TAG, "sendGvSms()");
            String response = "";
            try {
                String smsData2 = smsData + "&" + URLEncoder.encode("_rnr_se", Texting.UTF8) + "=" + URLEncoder.encode(this.rnrSEE, Texting.UTF8);
                Log.i(Texting.TAG, "smsData = " + smsData2);
                URL smsUrl = new URL(Texting.GV_SMS_SEND_URL);
                URLConnection smsConn = smsUrl.openConnection();
                smsConn.setRequestProperty("Authorization", "GoogleLogin auth=" + this.authToken);
                smsConn.setRequestProperty("User-agent", Texting.USER_AGENT);
                smsConn.setDoOutput(true);
                smsConn.setConnectTimeout(Texting.SERVER_TIMEOUT_MS);
                Log.i(Texting.TAG, "sms request = " + smsConn);
                OutputStreamWriter callwr = new OutputStreamWriter(smsConn.getOutputStream());
                callwr.write(smsData2);
                callwr.flush();
                BufferedReader callrd = new BufferedReader(new InputStreamReader(smsConn.getInputStream()));
                while (true) {
                    String line = callrd.readLine();
                    if (line == null) {
                        break;
                    }
                    response = response + line + "\n\r";
                }
                Log.i(Texting.TAG, "sendGvSms:  Sent SMS, response = " + response);
                callwr.close();
                callrd.close();
                if (response.equals("")) {
                    throw new IOException("No Response Data Received.");
                }
                return response;
            } catch (IOException e) {
                Log.i(Texting.TAG, "IO Error on Send " + e.getMessage());
                e.printStackTrace();
                return "IO Error Message not sent";
            }
        }

        public String getGeneral() throws IOException {
            Log.i(Texting.TAG, "getGeneral()");
            return get(Texting.GV_URL);
        }

        private void setRNRSEE() throws IOException {
            Log.i(Texting.TAG, "setRNRSEE()");
            if (this.general != null) {
                if (this.general.contains("'_rnr_se': '")) {
                    String p1 = this.general.split("'_rnr_se': '", 2)[1];
                    this.rnrSEE = p1.split("',", 2)[0];
                    Log.i(Texting.TAG, "Successfully Received rnr_se.");
                    return;
                }
                Log.i(Texting.TAG, "Answer did not contain rnr_se! " + this.general);
                throw new IOException("Answer did not contain rnr_se! " + this.general);
            }
            Log.i(Texting.TAG, "setRNRSEE(): Answer was null!");
            throw new IOException("setRNRSEE(): Answer was null!");
        }

        String get(String urlString) throws IOException {
            InputStream is;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = 0;
            try {
                conn.setRequestProperty("Authorization", "GoogleLogin auth=" + this.authToken);
                conn.setRequestProperty("User-agent", Texting.USER_AGENT);
                conn.setInstanceFollowRedirects(false);
                conn.connect();
                responseCode = conn.getResponseCode();
                Log.i(Texting.TAG, urlString + " - " + conn.getResponseMessage());
                if (responseCode == 200) {
                    is = conn.getInputStream();
                } else if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) {
                    this.redirectCounter++;
                    if (this.redirectCounter > 5) {
                        this.redirectCounter = 0;
                        throw new IOException(urlString + " : " + conn.getResponseMessage() + "(" + responseCode + ") : Too many redirects. exiting.");
                    }
                    String location = conn.getHeaderField("Location");
                    if (location != null && !location.equals("")) {
                        System.out.println(urlString + " - " + responseCode + " - new URL: " + location);
                        return get(location);
                    }
                    throw new IOException(urlString + " : " + conn.getResponseMessage() + "(" + responseCode + ") : Received moved answer but no Location. exiting.");
                } else {
                    is = conn.getErrorStream();
                }
                this.redirectCounter = 0;
                if (is == null) {
                    throw new IOException(urlString + " : " + conn.getResponseMessage() + "(" + responseCode + ") : InputStream was null : exiting.");
                }
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer();
                    while (true) {
                        String line = rd.readLine();
                        if (line != null) {
                            sb.append(line + "\n\r");
                        } else {
                            rd.close();
                            String result = sb.toString();
                            return result;
                        }
                    }
                } catch (Exception e) {
                    throw new IOException(urlString + " - " + conn.getResponseMessage() + "(" + responseCode + ") - " + e.getLocalizedMessage());
                }
            } catch (Exception e2) {
                throw new IOException(urlString + " : " + conn.getResponseMessage() + "(" + responseCode + ") : IO Error.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleSentMessage(Context context, BroadcastReceiver receiver, int resultCode, String smsMsg) {
        switch (resultCode) {
            case -1:
                Log.i(TAG, "Received OK, msg:" + smsMsg);
                Toast.makeText(activity, "Message sent", 0).show();
                break;
            case 1:
                Log.e(TAG, "Received generic failure, msg:" + smsMsg);
                Toast.makeText(activity, "Generic failure: message not sent", 0).show();
                break;
            case 2:
                Log.e(TAG, "Received radio off error, msg:" + smsMsg);
                Toast.makeText(activity, "Could not send SMS message: radio off.", 1).show();
                break;
            case 3:
                Log.e(TAG, "Received null PDU error, msg:" + smsMsg);
                Toast.makeText(activity, "Received null PDU error. Message not sent.", 0).show();
                break;
            case 4:
                Log.e(TAG, "Received no service error, msg:" + smsMsg);
                Toast.makeText(activity, "No Sms service available. Message not sent.", 0).show();
                break;
        }
    }

    private void sendViaSms() {
        Log.i(TAG, "Sending via built-in Sms");
        ArrayList<String> parts = this.smsManager.divideMessage(this.message);
        int numParts = parts.size();
        ArrayList<PendingIntent> pendingIntents = new ArrayList<>();
        for (int i = 0; i < numParts; i++) {
            pendingIntents.add(PendingIntent.getBroadcast(activity, 0, new Intent(SENT), 0));
        }
        BroadcastReceiver sendReceiver = new BroadcastReceiver() { // from class: com.google.appinventor.components.runtime.Texting.1
            @Override // android.content.BroadcastReceiver
            public synchronized void onReceive(Context arg0, Intent arg1) {
                try {
                    Texting.this.handleSentMessage(arg0, null, getResultCode(), Texting.this.message);
                    Texting.activity.unregisterReceiver(this);
                } catch (Exception e) {
                    Log.e("BroadcastReceiver", "Error in onReceive for msgId " + arg1.getAction());
                    Log.e("BroadcastReceiver", e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        activity.registerReceiver(sendReceiver, new IntentFilter(SENT));
        this.smsManager.sendMultipartTextMessage(this.phoneNumber, null, parts, pendingIntents, null);
    }

    /* loaded from: classes.dex */
    class AsyncAuthenticate extends AsyncTask<Void, Void, String> {
        AsyncAuthenticate() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... arg0) {
            Log.i(Texting.TAG, "Authenticating");
            return new OAuth2Helper().getRefreshedAuthToken(Texting.activity, Texting.GV_SERVICE);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String result) {
            Log.i(Texting.TAG, "authToken = " + result);
            Texting.this.authToken = result;
            Toast.makeText(Texting.activity, "Finished authentication", 0).show();
            Texting.this.processPendingQueue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AsyncSendMessage extends AsyncTask<String, Void, String> {
        AsyncSendMessage() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... args) {
            String smsData;
            String phoneNumber = args[0];
            String message = args[1];
            String response = "";
            Log.i(Texting.TAG, "Async sending phoneNumber = " + phoneNumber + " message = " + message);
            try {
                smsData = URLEncoder.encode("phoneNumber", Texting.UTF8) + "=" + URLEncoder.encode(phoneNumber, Texting.UTF8) + "&" + URLEncoder.encode(PropertyTypeConstants.PROPERTY_TYPE_TEXT, Texting.UTF8) + "=" + URLEncoder.encode(message, Texting.UTF8);
                if (Texting.this.gvHelper == null) {
                    Texting.this.gvHelper = new GoogleVoiceUtil(Texting.this.authToken);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Texting.this.gvHelper.isInitialized()) {
                response = Texting.this.gvHelper.sendGvSms(smsData);
                Log.i(Texting.TAG, "Sent SMS, response = " + response);
                return response;
            }
            return "IO Error: unable to create GvHelper";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String result) {
            super.onPostExecute((AsyncSendMessage) result);
            boolean ok = false;
            int code = 0;
            try {
                JSONObject json = new JSONObject(result);
                ok = json.getBoolean("ok");
                code = json.getJSONObject("data").getInt("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (ok) {
                Toast.makeText(Texting.activity, "Message sent", 0).show();
            } else if (code == 58) {
                Toast.makeText(Texting.activity, "Errcode 58: SMS limit reached", 0).show();
            } else if (result.contains("IO Error")) {
                Toast.makeText(Texting.activity, result, 0).show();
            }
        }
    }

    @Override // com.google.appinventor.components.runtime.OnStopListener
    public void onStop() {
        SharedPreferences prefs = activity.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREF_RCVENABLED, receivingEnabled);
        editor.putBoolean(PREF_GVENABLED, this.googleVoiceEnabled);
        editor.commit();
    }
}
