package com.google.appinventor.components.runtime.util;

import android.os.Handler;

/* loaded from: classes.dex */
public class AsynchUtil {
    public static void runAsynchronously(Runnable call) {
        Thread thread = new Thread(call);
        thread.start();
    }

    public static void runAsynchronously(final Handler androidUIHandler, final Runnable call, final Runnable callback) {
        Runnable runnable = new Runnable() { // from class: com.google.appinventor.components.runtime.util.AsynchUtil.1
            @Override // java.lang.Runnable
            public void run() {
                call.run();
                if (callback != null) {
                    androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.util.AsynchUtil.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.run();
                        }
                    });
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
