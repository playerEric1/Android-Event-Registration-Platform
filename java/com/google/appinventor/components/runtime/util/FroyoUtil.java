package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.media.AudioManager;
import android.net.http.SslError;
import android.view.Display;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Player;

/* loaded from: classes.dex */
public class FroyoUtil {
    private FroyoUtil() {
    }

    public static int getRotation(Display display) {
        return display.getRotation();
    }

    public static AudioManager setAudioManager(Activity activity) {
        return (AudioManager) activity.getSystemService("audio");
    }

    public static Object setAudioFocusChangeListener(final Player player) {
        return new AudioManager.OnAudioFocusChangeListener() { // from class: com.google.appinventor.components.runtime.util.FroyoUtil.1
            private boolean playbackFlag = false;

            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case -3:
                    case -2:
                        if (Player.this != null && Player.this.playerState == 2) {
                            Player.this.pause();
                            this.playbackFlag = true;
                            return;
                        }
                        return;
                    case -1:
                        this.playbackFlag = false;
                        Player.this.OtherPlayerStarted();
                        return;
                    case 0:
                    default:
                        return;
                    case 1:
                        if (Player.this != null && this.playbackFlag && Player.this.playerState == 4) {
                            Player.this.Start();
                            this.playbackFlag = false;
                            return;
                        }
                        return;
                }
            }
        };
    }

    public static boolean focusRequestGranted(AudioManager am, Object afChangeListener) {
        int result = am.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) afChangeListener, 3, 1);
        return result == 1;
    }

    public static void abandonFocus(AudioManager am, Object afChangeListener) {
        am.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) afChangeListener);
    }

    public static WebViewClient getWebViewClient(final boolean ignoreErrors, final boolean followLinks, final Form form, final Component component) {
        return new WebViewClient() { // from class: com.google.appinventor.components.runtime.util.FroyoUtil.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return !followLinks;
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (ignoreErrors) {
                    handler.proceed();
                    return;
                }
                handler.cancel();
                form.dispatchErrorOccurredEvent(component, "WebView", ErrorMessages.ERROR_WEBVIEW_SSL_ERROR, new Object[0]);
            }
        };
    }
}
