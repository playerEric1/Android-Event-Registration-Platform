package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

@UsesPermissions(permissionNames = "android.permission.INTERNET")
@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component that communicates with a Web service to store and retrieve information.", iconName = "images/tinyWebDB.png", nonVisible = true, version = 2)
@SimpleObject
/* loaded from: classes.dex */
public class TinyWebDB extends AndroidNonvisibleComponent implements Component {
    private static final String GETVALUE_COMMAND = "getvalue";
    private static final String LOG_TAG = "TinyWebDB";
    private static final String STOREAVALUE_COMMAND = "storeavalue";
    private static final String TAG_PARAMETER = "tag";
    private static final String VALUE_PARAMETER = "value";
    private Handler androidUIHandler;
    private String serviceURL;

    public TinyWebDB(ComponentContainer container) {
        super(container.$form());
        this.androidUIHandler = new Handler();
        this.serviceURL = "http://appinvtinywebdb.appspot.com/";
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ServiceURL() {
        return this.serviceURL;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "http://appinvtinywebdb.appspot.com", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void ServiceURL(String url) {
        this.serviceURL = url;
    }

    @SimpleFunction
    public void StoreValue(final String tag, final Object valueToStore) {
        Runnable call = new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.1
            @Override // java.lang.Runnable
            public void run() {
                TinyWebDB.this.postStoreValue(tag, valueToStore);
            }
        };
        AsynchUtil.runAsynchronously(call);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postStoreValue(String tag, Object valueToStore) {
        AsyncCallbackPair<String> myCallback = new AsyncCallbackPair<String>() { // from class: com.google.appinventor.components.runtime.TinyWebDB.2
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(String response) {
                TinyWebDB.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        TinyWebDB.this.ValueStored();
                    }
                });
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(final String message) {
                TinyWebDB.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TinyWebDB.this.WebServiceError(message);
                    }
                });
            }
        };
        try {
            WebServiceUtil.getInstance().postCommand(this.serviceURL, STOREAVALUE_COMMAND, Lists.newArrayList(new BasicNameValuePair(TAG_PARAMETER, tag), new BasicNameValuePair(VALUE_PARAMETER, JsonUtil.getJsonRepresentation(valueToStore))), myCallback);
        } catch (JSONException e) {
            throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
        }
    }

    @SimpleEvent
    public void ValueStored() {
        EventDispatcher.dispatchEvent(this, "ValueStored", new Object[0]);
    }

    @SimpleFunction
    public void GetValue(final String tag) {
        Runnable call = new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.3
            @Override // java.lang.Runnable
            public void run() {
                TinyWebDB.this.postGetValue(tag);
            }
        };
        AsynchUtil.runAsynchronously(call);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postGetValue(final String tag) {
        AsyncCallbackPair<JSONArray> myCallback = new AsyncCallbackPair<JSONArray>() { // from class: com.google.appinventor.components.runtime.TinyWebDB.4
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(JSONArray result) {
                if (result == null) {
                    TinyWebDB.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TinyWebDB.this.WebServiceError("The Web server did not respond to the get value request for the tag " + tag + ".");
                        }
                    });
                    return;
                }
                try {
                    final String tagFromWebDB = result.getString(1);
                    String value = result.getString(2);
                    final String valueFromWebDB = value.length() == 0 ? "" : JsonUtil.getObjectFromJson(value);
                    TinyWebDB.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            TinyWebDB.this.GotValue(tagFromWebDB, valueFromWebDB);
                        }
                    });
                } catch (JSONException e) {
                    TinyWebDB.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.4.3
                        @Override // java.lang.Runnable
                        public void run() {
                            TinyWebDB.this.WebServiceError("The Web server returned a garbled value for the tag " + tag + ".");
                        }
                    });
                }
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(final String message) {
                TinyWebDB.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.TinyWebDB.4.4
                    @Override // java.lang.Runnable
                    public void run() {
                        TinyWebDB.this.WebServiceError(message);
                    }
                });
            }
        };
        WebServiceUtil.getInstance().postCommandReturningArray(this.serviceURL, GETVALUE_COMMAND, Lists.newArrayList(new BasicNameValuePair(TAG_PARAMETER, tag)), myCallback);
    }

    @SimpleEvent
    public void GotValue(String tagFromWebDB, Object valueFromWebDB) {
        EventDispatcher.dispatchEvent(this, "GotValue", tagFromWebDB, valueFromWebDB);
    }

    @SimpleEvent
    public void WebServiceError(String message) {
        EventDispatcher.dispatchEvent(this, "WebServiceError", message);
    }
}