package com.google.appinventor.components.runtime.util;

import java.util.Locale;

/* loaded from: classes.dex */
public interface ITextToSpeech {

    /* loaded from: classes.dex */
    public interface TextToSpeechCallback {
        void onFailure();

        void onSuccess();
    }

    void onDestroy();

    void onResume();

    void onStop();

    void setPitch(float f);

    void setSpeechRate(float f);

    void speak(String str, Locale locale);
}
