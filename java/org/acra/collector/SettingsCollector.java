package org.acra.collector;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.lang.reflect.Field;
import org.acra.ACRA;

/* loaded from: classes.dex */
final class SettingsCollector {
    SettingsCollector() {
    }

    public static String collectSystemSettings(Context ctx) {
        StringBuilder result = new StringBuilder();
        Field[] keys = Settings.System.class.getFields();
        for (Field key : keys) {
            if (!key.isAnnotationPresent(Deprecated.class) && key.getType() == String.class) {
                try {
                    String value = Settings.System.getString(ctx.getContentResolver(), (String) key.get(null));
                    if (value != null) {
                        result.append(key.getName()).append("=").append((Object) value).append("\n");
                    }
                } catch (IllegalAccessException e) {
                    Log.w(ACRA.LOG_TAG, "Error : ", e);
                } catch (IllegalArgumentException e2) {
                    Log.w(ACRA.LOG_TAG, "Error : ", e2);
                }
            }
        }
        return result.toString();
    }

    public static String collectSecureSettings(Context ctx) {
        StringBuilder result = new StringBuilder();
        Field[] keys = Settings.Secure.class.getFields();
        for (Field key : keys) {
            if (!key.isAnnotationPresent(Deprecated.class) && key.getType() == String.class && isAuthorized(key)) {
                try {
                    String value = Settings.Secure.getString(ctx.getContentResolver(), (String) key.get(null));
                    if (value != null) {
                        result.append(key.getName()).append("=").append((Object) value).append("\n");
                    }
                } catch (IllegalAccessException e) {
                    Log.w(ACRA.LOG_TAG, "Error : ", e);
                } catch (IllegalArgumentException e2) {
                    Log.w(ACRA.LOG_TAG, "Error : ", e2);
                }
            }
        }
        return result.toString();
    }

    private static boolean isAuthorized(Field key) {
        return (key == null || key.getName().startsWith("WIFI_AP")) ? false : true;
    }
}
