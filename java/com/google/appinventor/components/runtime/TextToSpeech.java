package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.ExternalTextToSpeech;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import com.google.appinventor.components.runtime.util.InternalTextToSpeech;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "Component for using TextToSpeech to speak a message", iconName = "images/textToSpeech.png", nonVisible = true, version = 2)
@SimpleObject
/* loaded from: classes.dex */
public class TextToSpeech extends AndroidNonvisibleComponent implements Component, OnStopListener, OnResumeListener, OnDestroyListener {
    private static final String LOG_TAG = "TextToSpeech";
    private String country;
    private String iso2Country;
    private String iso2Language;
    private String language;
    private float pitch;
    private boolean result;
    private float speechRate;
    private final ITextToSpeech tts;
    private static final Map<String, Locale> iso3LanguageToLocaleMap = Maps.newHashMap();
    private static final Map<String, Locale> iso3CountryToLocaleMap = Maps.newHashMap();

    static {
        initLocaleMaps();
    }

    private static void initLocaleMaps() {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            try {
                String iso3Country = locale.getISO3Country();
                if (iso3Country.length() > 0) {
                    iso3CountryToLocaleMap.put(iso3Country, locale);
                }
            } catch (MissingResourceException e) {
            }
            try {
                String iso3Language = locale.getISO3Language();
                if (iso3Language.length() > 0) {
                    iso3LanguageToLocaleMap.put(iso3Language, locale);
                }
            } catch (MissingResourceException e2) {
            }
        }
    }

    public TextToSpeech(ComponentContainer container) {
        super(container.$form());
        this.pitch = 1.0f;
        this.speechRate = 1.0f;
        this.result = false;
        Language("");
        Country("");
        boolean useExternalLibrary = SdkLevel.getLevel() < 4;
        Log.v(LOG_TAG, "Using " + (useExternalLibrary ? "external" : "internal") + " TTS library.");
        ITextToSpeech.TextToSpeechCallback callback = new ITextToSpeech.TextToSpeechCallback() { // from class: com.google.appinventor.components.runtime.TextToSpeech.1
            @Override // com.google.appinventor.components.runtime.util.ITextToSpeech.TextToSpeechCallback
            public void onSuccess() {
                TextToSpeech.this.result = true;
                TextToSpeech.this.AfterSpeaking(true);
            }

            @Override // com.google.appinventor.components.runtime.util.ITextToSpeech.TextToSpeechCallback
            public void onFailure() {
                TextToSpeech.this.result = false;
                TextToSpeech.this.AfterSpeaking(false);
            }
        };
        this.tts = useExternalLibrary ? new ExternalTextToSpeech(container, callback) : new InternalTextToSpeech(container.$context(), callback);
        this.form.registerForOnStop(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnDestroy(this);
        this.form.setVolumeControlStream(3);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Result() {
        return this.result;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the language for TextToSpeech")
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Language(String language) {
        Locale locale;
        switch (language.length()) {
            case 2:
                locale = new Locale(language);
                this.language = locale.getLanguage();
                break;
            case 3:
                locale = iso3LanguageToLocale(language);
                this.language = locale.getISO3Language();
                break;
            default:
                locale = Locale.getDefault();
                this.language = locale.getLanguage();
                break;
        }
        this.iso2Language = locale.getLanguage();
    }

    private static Locale iso3LanguageToLocale(String iso3Language) {
        Locale mappedLocale = iso3LanguageToLocaleMap.get(iso3Language);
        if (mappedLocale == null) {
            mappedLocale = iso3LanguageToLocaleMap.get(iso3Language.toLowerCase(Locale.ENGLISH));
        }
        return mappedLocale == null ? Locale.getDefault() : mappedLocale;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the Pitch for tts. The values should be between 0 and 2 where lower values lower the tone of synthesized voice and greater values increases it")
    @DesignerProperty(defaultValue = "1.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void Pitch(float pitch) {
        if (pitch < 0.0f || pitch > 2.0f) {
            Log.i(LOG_TAG, "Pitch value should be between 0 and 2, but user specified: " + pitch);
            return;
        }
        this.pitch = pitch;
        ITextToSpeech iTextToSpeech = this.tts;
        if (pitch == 0.0f) {
            pitch = 0.1f;
        }
        iTextToSpeech.setPitch(pitch);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of Pitch")
    public float Pitch() {
        return this.pitch;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the SpeechRate for tts. The values should be between 0 and 2 where lower values slow down the pitch and greater values accelerate it")
    @DesignerProperty(defaultValue = "1.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void SpeechRate(float speechRate) {
        if (speechRate < 0.0f || speechRate > 2.0f) {
            Log.i(LOG_TAG, "speechRate value should be between 0 and 2, but user specified: " + speechRate);
            return;
        }
        this.speechRate = speechRate;
        ITextToSpeech iTextToSpeech = this.tts;
        if (speechRate == 0.0f) {
            speechRate = 0.1f;
        }
        iTextToSpeech.setSpeechRate(speechRate);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of SpeechRate")
    public float SpeechRate() {
        return this.speechRate;
    }

    @SimpleProperty
    public String Language() {
        return this.language;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Country(String country) {
        Locale locale;
        switch (country.length()) {
            case 2:
                locale = new Locale(country);
                this.country = locale.getCountry();
                break;
            case 3:
                locale = iso3CountryToLocale(country);
                this.country = locale.getISO3Country();
                break;
            default:
                locale = Locale.getDefault();
                this.country = locale.getCountry();
                break;
        }
        this.iso2Country = locale.getCountry();
    }

    private static Locale iso3CountryToLocale(String iso3Country) {
        Locale mappedLocale = iso3CountryToLocaleMap.get(iso3Country);
        if (mappedLocale == null) {
            mappedLocale = iso3CountryToLocaleMap.get(iso3Country.toUpperCase(Locale.ENGLISH));
        }
        return mappedLocale == null ? Locale.getDefault() : mappedLocale;
    }

    @SimpleProperty
    public String Country() {
        return this.country;
    }

    @SimpleFunction
    public void Speak(String message) {
        BeforeSpeaking();
        Locale loc = new Locale(this.iso2Language, this.iso2Country);
        this.tts.speak(message, loc);
    }

    @SimpleEvent
    public void BeforeSpeaking() {
        EventDispatcher.dispatchEvent(this, "BeforeSpeaking", new Object[0]);
    }

    @SimpleEvent
    public void AfterSpeaking(boolean result) {
        EventDispatcher.dispatchEvent(this, "AfterSpeaking", Boolean.valueOf(result));
    }

    @Override // com.google.appinventor.components.runtime.OnStopListener
    public void onStop() {
        this.tts.onStop();
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        this.tts.onResume();
    }

    @Override // com.google.appinventor.components.runtime.OnDestroyListener
    public void onDestroy() {
        this.tts.onDestroy();
    }
}
