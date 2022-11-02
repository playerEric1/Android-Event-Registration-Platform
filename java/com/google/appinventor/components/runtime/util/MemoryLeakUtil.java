package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class MemoryLeakUtil {
    private static final String LOG_TAG = "MemoryLeakUtil";
    private static final AtomicInteger prefixGenerator = new AtomicInteger(0);
    private static final Map<String, WeakReference<Object>> TRACKED_OBJECTS = Maps.newTreeMap();

    private MemoryLeakUtil() {
    }

    public static String trackObject(String tag, Object object) {
        String key = tag == null ? prefixGenerator.incrementAndGet() + "_" : prefixGenerator.incrementAndGet() + "_" + tag;
        TRACKED_OBJECTS.put(key, new WeakReference<>(object));
        return key;
    }

    public static boolean isTrackedObjectCollected(String key, boolean stopTrackingIfCollected) {
        System.gc();
        WeakReference<Object> ref = TRACKED_OBJECTS.get(key);
        if (ref != null) {
            Object o = ref.get();
            String tag = key.substring(key.indexOf("_") + 1);
            Log.i(LOG_TAG, "Object with tag " + tag + " has " + (o != null ? "not " : "") + "been garbage collected.");
            if (stopTrackingIfCollected && o == null) {
                TRACKED_OBJECTS.remove(key);
            }
            return o == null;
        }
        throw new IllegalArgumentException("key not found");
    }

    public static void checkAllTrackedObjects(boolean verbose, boolean stopTrackingCollectedObjects) {
        Log.i(LOG_TAG, "Checking Tracked Objects ----------------------------------------");
        System.gc();
        int countRemaining = 0;
        int countCollected = 0;
        Iterator<Map.Entry<String, WeakReference<Object>>> it = TRACKED_OBJECTS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, WeakReference<Object>> entry = it.next();
            String key = entry.getKey();
            WeakReference<Object> ref = entry.getValue();
            Object o = ref.get();
            if (o != null) {
                countRemaining++;
            } else {
                countCollected++;
                if (stopTrackingCollectedObjects) {
                    it.remove();
                }
            }
            if (verbose) {
                String tag = key.substring(key.indexOf("_") + 1);
                Log.i(LOG_TAG, "Object with tag " + tag + " has " + (o != null ? "not " : "") + "been garbage collected.");
            }
        }
        Log.i(LOG_TAG, "summary: collected " + countCollected);
        Log.i(LOG_TAG, "summary: remaining " + countRemaining);
        Log.i(LOG_TAG, "-----------------------------------------------------------------");
    }
}
