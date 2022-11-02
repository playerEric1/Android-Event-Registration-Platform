package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class RetValManager {
    private static final String LOG_TAG = "RetValManager";
    private static final long TENSECONDS = 10000;
    private static final Object semaphore = new Object();
    private static ArrayList<JSONObject> currentArray = new ArrayList<>(10);

    private RetValManager() {
    }

    public static void appendReturnValue(String blockid, String ok, String item) {
        synchronized (semaphore) {
            JSONObject retval = new JSONObject();
            try {
                retval.put("status", ok);
                retval.put("type", "return");
                retval.put("value", item);
                retval.put("blockid", blockid);
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (sendNotify) {
                    semaphore.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void sendError(String error) {
        synchronized (semaphore) {
            JSONObject retval = new JSONObject();
            try {
                retval.put("status", "OK");
                retval.put("type", "error");
                retval.put("value", error);
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (sendNotify) {
                    semaphore.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void pushScreen(String screenName, Object value) {
        synchronized (semaphore) {
            JSONObject retval = new JSONObject();
            try {
                retval.put("status", "OK");
                retval.put("type", "pushScreen");
                retval.put("screen", screenName);
                if (value != null) {
                    retval.put("value", value.toString());
                }
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (sendNotify) {
                    semaphore.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void popScreen(String value) {
        synchronized (semaphore) {
            JSONObject retval = new JSONObject();
            try {
                retval.put("status", "OK");
                retval.put("type", "popScreen");
                if (value != null) {
                    retval.put("value", value.toString());
                }
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (sendNotify) {
                    semaphore.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static String fetch(boolean block) {
        String str;
        long startTime = System.currentTimeMillis();
        synchronized (semaphore) {
            while (currentArray.isEmpty() && block) {
                long time = System.currentTimeMillis();
                if (time - startTime > 9900) {
                    break;
                }
                try {
                    semaphore.wait(TENSECONDS);
                } catch (InterruptedException e) {
                }
            }
            JSONArray arrayoutput = new JSONArray((Collection) currentArray);
            JSONObject output = new JSONObject();
            try {
                output.put("status", "OK");
                output.put("values", arrayoutput);
                currentArray.clear();
                str = output.toString();
            } catch (JSONException e2) {
                Log.e(LOG_TAG, "Error fetching retvals", e2);
                str = "{\"status\" : \"BAD\", \"message\" : \"Failure in RetValManager\"}";
            }
        }
        return str;
    }
}
