package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.JsonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "TinyDB is a non-visible component that stores data for an app. <p> Apps created with App Inventor are initialized each time they run: If an app sets the value of a variable and the user then quits the app, the value of that variable will not be remembered the next time the app is run. In contrast, TinyDB is a <em> persistent </em> data store for the app, that is, the data stored there will be available each time the app is run. An example might be a game that saves the high score and retrieves it each time the game is played. </<p> <p> Data items are strings stored under <em>tags</em> . To store a data item, you specify the tag it should be stored under.  Subsequently, you can retrieve the data that was stored under a given tag. </p><p> There is only one data store per app. Even if you have multiple TinyDB components, they will use the same data store. To get the effect of separate stores, use different keys. Also each app has its own data store. You cannot use TinyDB to pass data between two different apps on the phone, although you <em>can</em> use TinyDb to shares data between the different screens of a multi-screen app. </p> <p>When you are developing apps using the AI Companion, all the apps using that companion will share the same TinyDb.  That sharing will disappear once the apps are packaged.  But, during development, you should be careful to clear the TinyDb each time you start working on a new app.</p>", iconName = "images/tinyDB.png", nonVisible = true, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class TinyDB extends AndroidNonvisibleComponent implements Component, Deleteable {
    private Context context;
    private SharedPreferences sharedPreferences;

    public TinyDB(ComponentContainer container) {
        super(container.$form());
        this.context = container.$context();
        this.sharedPreferences = this.context.getSharedPreferences("TinyDB1", 0);
    }

    @SimpleFunction
    public void StoreValue(String tag, Object valueToStore) {
        SharedPreferences.Editor sharedPrefsEditor = this.sharedPreferences.edit();
        try {
            sharedPrefsEditor.putString(tag, JsonUtil.getJsonRepresentation(valueToStore));
            sharedPrefsEditor.commit();
        } catch (JSONException e) {
            throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
        }
    }

    @SimpleFunction
    public Object GetValue(String tag, Object valueIfTagNotThere) {
        try {
            String value = this.sharedPreferences.getString(tag, "");
            if (value.length() == 0) {
                return valueIfTagNotThere;
            }
            Object valueIfTagNotThere2 = JsonUtil.getObjectFromJson(value);
            return valueIfTagNotThere2;
        } catch (JSONException e) {
            throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Creation Error.");
        }
    }

    @SimpleFunction
    public Object GetTags() {
        List<String> keyList = new ArrayList<>();
        Map<String, ?> keyValues = this.sharedPreferences.getAll();
        keyList.addAll(keyValues.keySet());
        Collections.sort(keyList);
        return keyList;
    }

    @SimpleFunction
    public void ClearAll() {
        SharedPreferences.Editor sharedPrefsEditor = this.sharedPreferences.edit();
        sharedPrefsEditor.clear();
        sharedPrefsEditor.commit();
    }

    @SimpleFunction
    public void ClearTag(String tag) {
        SharedPreferences.Editor sharedPrefsEditor = this.sharedPreferences.edit();
        sharedPrefsEditor.remove(tag);
        sharedPrefsEditor.commit();
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        SharedPreferences.Editor sharedPrefsEditor = this.sharedPreferences.edit();
        sharedPrefsEditor.clear();
        sharedPrefsEditor.commit();
    }
}
