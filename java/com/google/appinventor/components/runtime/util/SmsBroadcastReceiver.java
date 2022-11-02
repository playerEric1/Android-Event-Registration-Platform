package com.google.appinventor.components.runtime.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.Texting;
import gnu.expr.Declaration;

/* loaded from: classes.dex */
public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 8647;
    public static final String TAG = "SmsBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        String phone = getPhoneNumber(intent);
        String msg = getMessage(intent);
        Log.i(TAG, "Received " + phone + " : " + msg);
        int receivingEnabled = Texting.isReceivingEnabled(context);
        if (receivingEnabled == 1) {
            Log.i(TAG, context.getApplicationInfo().packageName + " Receiving is not enabled, ignoring message.");
        } else if ((receivingEnabled == 2 || isRepl(context)) && !Texting.isRunning()) {
            Log.i(TAG, context.getApplicationInfo().packageName + " Texting isn't running, and either receivingEnabled is FOREGROUND or we are the repl.");
        } else {
            Texting.handledReceivedMessage(context, phone, msg);
            if (Texting.isRunning()) {
                Log.i(TAG, context.getApplicationInfo().packageName + " App in Foreground, delivering message.");
                return;
            }
            Log.i(TAG, context.getApplicationInfo().packageName + " Texting isn't running, but receivingEnabled == 2, sending notification.");
            sendNotification(context, phone, msg);
        }
    }

    private String getPhoneNumber(Intent intent) {
        String phone = "";
        if (intent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
            String phone2 = intent.getExtras().getString(Texting.PHONE_NUMBER_TAG);
            return PhoneNumberUtils.formatNumber(phone2);
        }
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) pdu);
            String phone3 = smsMsg.getOriginatingAddress();
            phone = PhoneNumberUtils.formatNumber(phone3);
        }
        return phone;
    }

    private String getMessage(Intent intent) {
        String msg = "";
        if (intent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
            String msg2 = intent.getExtras().getString(Texting.MESSAGE_TAG);
            return msg2;
        }
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) pdu);
            msg = smsMsg.getMessageBody();
        }
        return msg;
    }

    private void sendNotification(Context context, String phone, String msg) {
        String classname;
        Intent newIntent;
        Log.i(TAG, "sendingNotification " + phone + ":" + msg);
        String packageName = context.getPackageName();
        Log.i(TAG, "Package name : " + packageName);
        try {
            classname = packageName + ".Screen1";
            newIntent = new Intent(context, Class.forName(classname));
        } catch (ClassNotFoundException e) {
            e = e;
        }
        try {
            newIntent.setAction("android.intent.action.MAIN");
            newIntent.addCategory("android.intent.category.LAUNCHER");
            newIntent.addFlags(805306368);
            NotificationManager nm = (NotificationManager) context.getSystemService("notification");
            Notification note = new Notification(17301648, phone + " : " + msg, System.currentTimeMillis());
            note.flags |= 16;
            note.defaults |= 1;
            PendingIntent activity = PendingIntent.getActivity(context, 0, newIntent, Declaration.PACKAGE_ACCESS);
            note.setLatestEventInfo(context, "Sms from " + phone, msg, activity);
            note.number = Texting.getCachedMsgCount();
            nm.notify(null, NOTIFICATION_ID, note);
            Log.i(TAG, "Notification sent, classname: " + classname);
        } catch (ClassNotFoundException e2) {
            e = e2;
            e.printStackTrace();
        }
    }

    private boolean isRepl(Context context) {
        try {
            String packageName = context.getPackageName();
            String classname = packageName + ".Screen1";
            Class appClass = Class.forName(classname);
            Class superClass = appClass.getSuperclass();
            return superClass.equals(ReplForm.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
