package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;

@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_WIFI_STATE,android.permission.ACCESS_NETWORK_STATE")
@DesignerComponent(category = ComponentCategory.LAYOUT, description = "Top-level component containing all other components in the program", showOnPalette = false, version = 13)
@SimpleObject
/* loaded from: classes.dex */
public class Form extends Activity implements Component, ComponentContainer, HandlesEventDispatching {
    public static final String APPINVENTOR_URL_SCHEME = "appinventor";
    private static final String ARGUMENT_NAME = "APP_INVENTOR_START";
    private static final String LOG_TAG = "Form";
    private static final String RESULT_NAME = "APP_INVENTOR_RESULT";
    private static final int SWITCH_FORM_REQUEST_CODE = 1;
    protected static Form activeForm;
    private static boolean applicationIsBeingClosed;
    private String aboutScreen;
    private AlignmentUtil alignmentSetter;
    private int backgroundColor;
    private Drawable backgroundDrawable;
    private String closeAnimType;
    protected String formName;
    private FrameLayout frameLayout;
    private FullScreenVideoUtil fullScreenVideoUtil;
    private int horizontalAlignment;
    private String nextFormName;
    private String openAnimType;
    private boolean screenInitialized;
    private boolean scrollable;
    private int verticalAlignment;
    private LinearLayout viewLayout;
    private static int nextRequestCode = 2;
    private static long minimumToastWait = 10000000000L;
    private final Handler androidUIHandler = new Handler();
    private String backgroundImagePath = "";
    private final HashMap<Integer, ActivityResultListener> activityResultMap = Maps.newHashMap();
    private final Set<OnStopListener> onStopListeners = Sets.newHashSet();
    private final Set<OnNewIntentListener> onNewIntentListeners = Sets.newHashSet();
    private final Set<OnResumeListener> onResumeListeners = Sets.newHashSet();
    private final Set<OnPauseListener> onPauseListeners = Sets.newHashSet();
    private final Set<OnDestroyListener> onDestroyListeners = Sets.newHashSet();
    private final Set<OnInitializeListener> onInitializeListeners = Sets.newHashSet();
    protected String startupValue = "";
    private long lastToastTime = System.nanoTime() - minimumToastWait;
    private String yandexTranslateTagline = "";

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        String className = getClass().getName();
        int lastDot = className.lastIndexOf(46);
        this.formName = className.substring(lastDot + 1);
        Log.d(LOG_TAG, "Form " + this.formName + " got onCreate");
        activeForm = this;
        Log.i(LOG_TAG, "activeForm is now " + activeForm.formName);
        this.viewLayout = new LinearLayout(this, 1);
        this.alignmentSetter = new AlignmentUtil(this.viewLayout);
        defaultPropertyValues();
        Intent startIntent = getIntent();
        if (startIntent != null && startIntent.hasExtra(ARGUMENT_NAME)) {
            this.startupValue = startIntent.getStringExtra(ARGUMENT_NAME);
        }
        this.fullScreenVideoUtil = new FullScreenVideoUtil(this, this.androidUIHandler);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        int softInputMode = params.softInputMode;
        getWindow().setSoftInputMode(softInputMode | 16);
        $define();
        Initialize();
    }

    private void defaultPropertyValues() {
        Scrollable(false);
        BackgroundImage("");
        AboutScreen("");
        BackgroundColor(-1);
        AlignHorizontal(1);
        AlignVertical(1);
        Title("");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        final int newOrientation = newConfig.orientation;
        if (newOrientation == 2 || newOrientation == 1) {
            this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.Form.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean dispatchEventNow = false;
                    if (Form.this.frameLayout != null) {
                        if (newOrientation == 2) {
                            if (Form.this.frameLayout.getWidth() >= Form.this.frameLayout.getHeight()) {
                                dispatchEventNow = true;
                            }
                        } else if (Form.this.frameLayout.getHeight() >= Form.this.frameLayout.getWidth()) {
                            dispatchEventNow = true;
                        }
                    }
                    if (!dispatchEventNow) {
                        Form.this.androidUIHandler.post(this);
                    } else {
                        Form.this.ScreenOrientationChanged();
                    }
                }
            });
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (!BackPressed()) {
                boolean handled = super.onKeyDown(keyCode, event);
                AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnimType);
                return handled;
            }
            return true;
        }
        boolean handled2 = super.onKeyDown(keyCode, event);
        return handled2;
    }

    @SimpleEvent(description = "Device back button pressed.")
    public boolean BackPressed() {
        return EventDispatcher.dispatchEvent(this, "BackPressed", new Object[0]);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String resultString;
        Log.i(LOG_TAG, "Form " + this.formName + " got onActivityResult, requestCode = " + requestCode + ", resultCode = " + resultCode);
        if (requestCode == 1) {
            if (data != null && data.hasExtra(RESULT_NAME)) {
                resultString = data.getStringExtra(RESULT_NAME);
            } else {
                resultString = "";
            }
            Object decodedResult = decodeJSONStringForForm(resultString, "other screen closed");
            OtherScreenClosed(this.nextFormName, decodedResult);
            return;
        }
        ActivityResultListener component = this.activityResultMap.get(Integer.valueOf(requestCode));
        if (component != null) {
            component.resultReturned(requestCode, resultCode, data);
        }
    }

    private static Object decodeJSONStringForForm(String jsonString, String functionName) {
        Log.i(LOG_TAG, "decodeJSONStringForForm -- decoding JSON representation:" + jsonString);
        Object valueFromJSON = "";
        try {
            valueFromJSON = JsonUtil.getObjectFromJson(jsonString);
            Log.i(LOG_TAG, "decodeJSONStringForForm -- got decoded JSON:" + valueFromJSON.toString());
            return valueFromJSON;
        } catch (JSONException e) {
            activeForm.dispatchErrorOccurredEvent(activeForm, functionName, ErrorMessages.ERROR_SCREEN_BAD_VALUE_RECEIVED, jsonString);
            return valueFromJSON;
        }
    }

    public int registerForActivityResult(ActivityResultListener listener) {
        int requestCode = generateNewRequestCode();
        this.activityResultMap.put(Integer.valueOf(requestCode), listener);
        return requestCode;
    }

    public void unregisterForActivityResult(ActivityResultListener listener) {
        List<Integer> keysToDelete = Lists.newArrayList();
        for (Map.Entry<Integer, ActivityResultListener> mapEntry : this.activityResultMap.entrySet()) {
            if (listener.equals(mapEntry.getValue())) {
                keysToDelete.add(mapEntry.getKey());
            }
        }
        for (Integer key : keysToDelete) {
            this.activityResultMap.remove(key);
        }
    }

    private static int generateNewRequestCode() {
        int i = nextRequestCode;
        nextRequestCode = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "Form " + this.formName + " got onResume");
        activeForm = this;
        if (applicationIsBeingClosed) {
            closeApplication();
            return;
        }
        for (OnResumeListener onResumeListener : this.onResumeListeners) {
            onResumeListener.onResume();
        }
    }

    public void registerForOnResume(OnResumeListener component) {
        this.onResumeListeners.add(component);
    }

    public void registerForOnInitialize(OnInitializeListener component) {
        this.onInitializeListeners.add(component);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(LOG_TAG, "Form " + this.formName + " got onNewIntent " + intent);
        for (OnNewIntentListener onNewIntentListener : this.onNewIntentListeners) {
            onNewIntentListener.onNewIntent(intent);
        }
    }

    public void registerForOnNewIntent(OnNewIntentListener component) {
        this.onNewIntentListeners.add(component);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "Form " + this.formName + " got onPause");
        for (OnPauseListener onPauseListener : this.onPauseListeners) {
            onPauseListener.onPause();
        }
    }

    public void registerForOnPause(OnPauseListener component) {
        this.onPauseListeners.add(component);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "Form " + this.formName + " got onStop");
        for (OnStopListener onStopListener : this.onStopListeners) {
            onStopListener.onStop();
        }
    }

    public void registerForOnStop(OnStopListener component) {
        this.onStopListeners.add(component);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "Form " + this.formName + " got onDestroy");
        EventDispatcher.removeDispatchDelegate(this);
        for (OnDestroyListener onDestroyListener : this.onDestroyListeners) {
            onDestroyListener.onDestroy();
        }
    }

    public void registerForOnDestroy(OnDestroyListener component) {
        this.onDestroyListeners.add(component);
    }

    @Override // android.app.Activity
    public Dialog onCreateDialog(int id) {
        switch (id) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG /* 189 */:
                return this.fullScreenVideoUtil.createFullScreenVideoDialog();
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override // android.app.Activity
    public void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG /* 189 */:
                this.fullScreenVideoUtil.prepareFullScreenVideoDialog(dialog);
                return;
            default:
                super.onPrepareDialog(id, dialog);
                return;
        }
    }

    protected void $define() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.appinventor.components.runtime.HandlesEventDispatching
    public boolean canDispatchEvent(Component component, String eventName) {
        boolean canDispatch = this.screenInitialized || (component == this && eventName.equals("Initialize"));
        if (canDispatch) {
            activeForm = this;
        }
        return canDispatch;
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        throw new UnsupportedOperationException();
    }

    @SimpleEvent(description = "Screen starting")
    public void Initialize() {
        this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.Form.2
            @Override // java.lang.Runnable
            public void run() {
                if (Form.this.frameLayout == null || Form.this.frameLayout.getWidth() == 0 || Form.this.frameLayout.getHeight() == 0) {
                    Form.this.androidUIHandler.post(this);
                    return;
                }
                EventDispatcher.dispatchEvent(Form.this, "Initialize", new Object[0]);
                Form.this.screenInitialized = true;
                for (OnInitializeListener onInitializeListener : Form.this.onInitializeListeners) {
                    onInitializeListener.onInitialize();
                }
                if (Form.activeForm instanceof ReplForm) {
                    ((ReplForm) Form.activeForm).HandleReturnValues();
                }
            }
        });
    }

    @SimpleEvent(description = "Screen orientation changed")
    public void ScreenOrientationChanged() {
        EventDispatcher.dispatchEvent(this, "ScreenOrientationChanged", new Object[0]);
    }

    @SimpleEvent(description = "Event raised when an error occurs. Only some errors will raise this condition.  For those errors, the system will show a notification by default.  You can use this event handler to prescribe an error behavior different than the default.")
    public void ErrorOccurred(Component component, String functionName, int errorNumber, String message) {
        String componentType = component.getClass().getName();
        Log.e(LOG_TAG, "Form " + this.formName + " ErrorOccurred, errorNumber = " + errorNumber + ", componentType = " + componentType.substring(componentType.lastIndexOf(".") + 1) + ", functionName = " + functionName + ", messages = " + message);
        if (!EventDispatcher.dispatchEvent(this, "ErrorOccurred", component, functionName, Integer.valueOf(errorNumber), message) && this.screenInitialized) {
            new Notifier(this).ShowAlert("Error " + errorNumber + ": " + message);
        }
    }

    public void dispatchErrorOccurredEvent(final Component component, final String functionName, final int errorNumber, final Object... messageArgs) {
        runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.Form.3
            @Override // java.lang.Runnable
            public void run() {
                String message = ErrorMessages.formatMessage(errorNumber, messageArgs);
                Form.this.ErrorOccurred(component, functionName, errorNumber, message);
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "When checked, there will be a vertical scrollbar on the screen, and the height of the application can exceed the physical height of the device. When unchecked, the application height is constrained to the height of the device.")
    public boolean Scrollable() {
        return this.scrollable;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Scrollable(boolean scrollable) {
        if (this.scrollable != scrollable || this.frameLayout == null) {
            if (this.frameLayout != null) {
                this.frameLayout.removeAllViews();
            }
            this.scrollable = scrollable;
            this.frameLayout = scrollable ? new ScrollView(this) : new FrameLayout(this);
            this.frameLayout.addView(this.viewLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
            this.frameLayout.setBackgroundColor(this.backgroundColor);
            if (this.backgroundDrawable != null) {
                ViewUtil.setBackgroundImage(this.frameLayout, this.backgroundDrawable);
            }
            setContentView(this.frameLayout);
            this.frameLayout.requestLayout();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void BackgroundColor(int argb) {
        this.backgroundColor = argb;
        if (argb != 0) {
            this.viewLayout.getLayoutManager().setBackgroundColor(argb);
            this.frameLayout.setBackgroundColor(argb);
            return;
        }
        this.viewLayout.getLayoutManager().setBackgroundColor(-1);
        this.frameLayout.setBackgroundColor(-1);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
    public String BackgroundImage() {
        return this.backgroundImagePath;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The screen background image.")
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void BackgroundImage(String path) {
        if (path == null) {
            path = "";
        }
        this.backgroundImagePath = path;
        try {
            this.backgroundDrawable = MediaUtil.getBitmapDrawable(this, this.backgroundImagePath);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to load " + this.backgroundImagePath);
            this.backgroundDrawable = null;
        }
        ViewUtil.setBackgroundImage(this.frameLayout, this.backgroundDrawable);
        this.frameLayout.invalidate();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The caption for the form, which apears in the title bar")
    public String Title() {
        return getTitle().toString();
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Title(String title) {
        setTitle(title);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Information about the screen.  It appears when \"About this Application\" is selected from the system menu. Use it to inform people about your app.  In multiple screen apps, each screen has its own AboutScreen info.")
    public String AboutScreen() {
        return this.aboutScreen;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXTAREA)
    public void AboutScreen(String aboutScreen) {
        this.aboutScreen = aboutScreen;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The requested screen orientation. Commonly used values are unspecified (-1), landscape (0), portrait (1), sensor (4), and user (2).  See the Android developer docuemntation for ActivityInfo.Screen_Orientation for the complete list of possible settings.")
    public String ScreenOrientation() {
        switch (getRequestedOrientation()) {
            case -1:
                return "unspecified";
            case 0:
                return "landscape";
            case 1:
                return "portrait";
            case 2:
                return "user";
            case 3:
                return "behind";
            case 4:
                return "sensor";
            case 5:
                return "nosensor";
            case 6:
                return "sensorLandscape";
            case 7:
                return "sensorPortrait";
            case 8:
                return "reverseLandscape";
            case 9:
                return "reversePortrait";
            case 10:
                return "fullSensor";
            default:
                return "unspecified";
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(defaultValue = "unspecified", editorType = PropertyTypeConstants.PROPERTY_TYPE_SCREEN_ORIENTATION)
    public void ScreenOrientation(String screenOrientation) {
        if (screenOrientation.equalsIgnoreCase("behind")) {
            setRequestedOrientation(3);
        } else if (screenOrientation.equalsIgnoreCase("landscape")) {
            setRequestedOrientation(0);
        } else if (screenOrientation.equalsIgnoreCase("nosensor")) {
            setRequestedOrientation(5);
        } else if (screenOrientation.equalsIgnoreCase("portrait")) {
            setRequestedOrientation(1);
        } else if (screenOrientation.equalsIgnoreCase("sensor")) {
            setRequestedOrientation(4);
        } else if (screenOrientation.equalsIgnoreCase("unspecified")) {
            setRequestedOrientation(-1);
        } else if (screenOrientation.equalsIgnoreCase("user")) {
            setRequestedOrientation(2);
        } else if (SdkLevel.getLevel() >= 9) {
            if (screenOrientation.equalsIgnoreCase("fullSensor")) {
                setRequestedOrientation(10);
            } else if (screenOrientation.equalsIgnoreCase("reverseLandscape")) {
                setRequestedOrientation(8);
            } else if (screenOrientation.equalsIgnoreCase("reversePortrait")) {
                setRequestedOrientation(9);
            } else if (screenOrientation.equalsIgnoreCase("sensorLandscape")) {
                setRequestedOrientation(6);
            } else if (screenOrientation.equalsIgnoreCase("sensorPortrait")) {
                setRequestedOrientation(7);
            } else {
                dispatchErrorOccurredEvent(this, "ScreenOrientation", ErrorMessages.ERROR_INVALID_SCREEN_ORIENTATION, screenOrientation);
            }
        } else {
            dispatchErrorOccurredEvent(this, "ScreenOrientation", ErrorMessages.ERROR_INVALID_SCREEN_ORIENTATION, screenOrientation);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the screen are aligned  horizontally. The choices are: 1 = left aligned, 2 = horizontally centered,  3 = right aligned.")
    public int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "1", editorType = PropertyTypeConstants.PROPERTY_TYPE_HORIZONTAL_ALIGNMENT)
    public void AlignHorizontal(int alignment) {
        try {
            this.alignmentSetter.setHorizontalAlignment(alignment);
            this.horizontalAlignment = alignment;
        } catch (IllegalArgumentException e) {
            dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(alignment));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom. Vertical alignment has no effect if the screen is scrollable.")
    public int AlignVertical() {
        return this.verticalAlignment;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "1", editorType = PropertyTypeConstants.PROPERTY_TYPE_VERTICAL_ALIGNMENT)
    public void AlignVertical(int alignment) {
        try {
            this.alignmentSetter.setVerticalAlignment(alignment);
            this.verticalAlignment = alignment;
        } catch (IllegalArgumentException e) {
            dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(alignment));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for switching to another screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
    public String OpenScreenAnimation() {
        return this.openAnimType;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "default", editorType = PropertyTypeConstants.PROPERTY_TYPE_SCREEN_ANIMATION)
    public void OpenScreenAnimation(String animType) {
        if (animType != "default" && animType != "fade" && animType != "zoom" && animType != "slidehorizontal" && animType != "slidevertical" && animType != "none") {
            dispatchErrorOccurredEvent(this, "Screen", ErrorMessages.ERROR_SCREEN_INVALID_ANIMATION, animType);
        } else {
            this.openAnimType = animType;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The animation for closing current screen and returning  to the previous screen. Valid options are default, fade, zoom, slidehorizontal, slidevertical, and none")
    public String CloseScreenAnimation() {
        return this.closeAnimType;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "default", editorType = PropertyTypeConstants.PROPERTY_TYPE_SCREEN_ANIMATION)
    public void CloseScreenAnimation(String animType) {
        if (animType != "default" && animType != "fade" && animType != "zoom" && animType != "slidehorizontal" && animType != "slidevertical" && animType != "none") {
            dispatchErrorOccurredEvent(this, "Screen", ErrorMessages.ERROR_SCREEN_INVALID_ANIMATION, animType);
        } else {
            this.closeAnimType = animType;
        }
    }

    public String getOpenAnimType() {
        return this.openAnimType;
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void Icon(String name) {
    }

    @SimpleProperty(description = "An integer value which must be incremented each time a new Android Application Package File (APK) is created for the Google Play Store.", userVisible = false)
    @DesignerProperty(defaultValue = "1", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void VersionCode(int vCode) {
    }

    @SimpleProperty(description = "A string which can be changed to allow Google Play Store users to distinguish between different versions of the App.", userVisible = false)
    @DesignerProperty(defaultValue = "1.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void VersionName(String vName) {
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen width (x-size).")
    public int Width() {
        return this.frameLayout.getWidth();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Screen height (y-size).")
    public int Height() {
        return this.frameLayout.getHeight();
    }

    public static void switchForm(String nextFormName) {
        if (activeForm != null) {
            activeForm.startNewForm(nextFormName, null);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void switchFormWithStartValue(String nextFormName, Object startValue) {
        Log.i(LOG_TAG, "Open another screen with start value:" + nextFormName);
        if (activeForm != null) {
            activeForm.startNewForm(nextFormName, startValue);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    protected void startNewForm(String nextFormName, Object startupValue) {
        String jValue;
        Log.i(LOG_TAG, "startNewForm:" + nextFormName);
        Intent activityIntent = new Intent();
        activityIntent.setClassName(this, getPackageName() + "." + nextFormName);
        String functionName = startupValue == null ? "open another screen" : "open another screen with start value";
        if (startupValue != null) {
            Log.i(LOG_TAG, "StartNewForm about to JSON encode:" + startupValue);
            jValue = jsonEncodeForForm(startupValue, functionName);
            Log.i(LOG_TAG, "StartNewForm got JSON encoding:" + jValue);
        } else {
            jValue = "";
        }
        activityIntent.putExtra(ARGUMENT_NAME, jValue);
        this.nextFormName = nextFormName;
        Log.i(LOG_TAG, "about to start new form" + nextFormName);
        try {
            Log.i(LOG_TAG, "startNewForm starting activity:" + activityIntent);
            startActivityForResult(activityIntent, 1);
            AnimationUtil.ApplyOpenScreenAnimation(this, this.openAnimType);
        } catch (ActivityNotFoundException e) {
            dispatchErrorOccurredEvent(this, functionName, ErrorMessages.ERROR_SCREEN_NOT_FOUND, nextFormName);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String jsonEncodeForForm(Object value, String functionName) {
        String jsonResult = "";
        Log.i(LOG_TAG, "jsonEncodeForForm -- creating JSON representation:" + value.toString());
        try {
            jsonResult = JsonUtil.getJsonRepresentation(value);
            Log.i(LOG_TAG, "jsonEncodeForForm -- got JSON representation:" + jsonResult);
            return jsonResult;
        } catch (JSONException e) {
            activeForm.dispatchErrorOccurredEvent(activeForm, functionName, ErrorMessages.ERROR_SCREEN_BAD_VALUE_FOR_SENDING, value.toString());
            return jsonResult;
        }
    }

    @SimpleEvent(description = "Event raised when another screen has closed and control has returned to this screen.")
    public void OtherScreenClosed(String otherScreenName, Object result) {
        Log.i(LOG_TAG, "Form " + this.formName + " OtherScreenClosed, otherScreenName = " + otherScreenName + ", result = " + result.toString());
        EventDispatcher.dispatchEvent(this, "OtherScreenClosed", otherScreenName, result);
    }

    @Override // com.google.appinventor.components.runtime.Component
    public HandlesEventDispatching getDispatchDelegate() {
        return this;
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public Activity $context() {
        return this;
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public Form $form() {
        return this;
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void $add(AndroidViewComponent component) {
        this.viewLayout.add(component);
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildWidth(AndroidViewComponent component, int width) {
        ViewUtil.setChildWidthForVerticalLayout(component.getView(), width);
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildHeight(AndroidViewComponent component, int height) {
        ViewUtil.setChildHeightForVerticalLayout(component.getView(), height);
    }

    public static Form getActiveForm() {
        return activeForm;
    }

    public static String getStartText() {
        if (activeForm != null) {
            return activeForm.startupValue;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static Object getStartValue() {
        if (activeForm != null) {
            return decodeJSONStringForForm(activeForm.startupValue, "get start value");
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void finishActivity() {
        if (activeForm != null) {
            activeForm.closeForm(null);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void finishActivityWithResult(Object result) {
        if (activeForm != null) {
            if (activeForm instanceof ReplForm) {
                ((ReplForm) activeForm).setResult(result);
                activeForm.closeForm(null);
                return;
            }
            String jString = jsonEncodeForForm(result, "close screen with value");
            Intent resultIntent = new Intent();
            resultIntent.putExtra(RESULT_NAME, jString);
            activeForm.closeForm(resultIntent);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    public static void finishActivityWithTextResult(String result) {
        if (activeForm != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(RESULT_NAME, result);
            activeForm.closeForm(resultIntent);
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    protected void closeForm(Intent resultIntent) {
        if (resultIntent != null) {
            setResult(-1, resultIntent);
        }
        finish();
        AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnimType);
    }

    public static void finishApplication() {
        if (activeForm != null) {
            activeForm.closeApplicationFromBlocks();
            return;
        }
        throw new IllegalStateException("activeForm is null");
    }

    protected void closeApplicationFromBlocks() {
        closeApplication();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeApplicationFromMenu() {
        closeApplication();
    }

    private void closeApplication() {
        applicationIsBeingClosed = true;
        finish();
        if (this.formName.equals("Screen1")) {
            System.exit(0);
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        addExitButtonToMenu(menu);
        addAboutInfoToMenu(menu);
        return true;
    }

    public void addExitButtonToMenu(Menu menu) {
        MenuItem stopApplicationItem = menu.add(0, 0, 1, "Stop this application").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.appinventor.components.runtime.Form.4
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem item) {
                Form.this.showExitApplicationNotification();
                return true;
            }
        });
        stopApplicationItem.setIcon(17301594);
    }

    public void addAboutInfoToMenu(Menu menu) {
        MenuItem aboutAppItem = menu.add(0, 0, 2, "About this application").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.appinventor.components.runtime.Form.5
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem item) {
                Form.this.showAboutApplicationNotification();
                return true;
            }
        });
        aboutAppItem.setIcon(17301651);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showExitApplicationNotification() {
        Runnable stopApplication = new Runnable() { // from class: com.google.appinventor.components.runtime.Form.6
            @Override // java.lang.Runnable
            public void run() {
                Form.this.closeApplicationFromMenu();
            }
        };
        Runnable doNothing = new Runnable() { // from class: com.google.appinventor.components.runtime.Form.7
            @Override // java.lang.Runnable
            public void run() {
            }
        };
        Notifier.twoButtonDialog(this, "Stop this application and exit? You'll need to relaunch the application to use it again.", "Stop application?", "Stop and exit", "Don't stop", false, stopApplication, doNothing, doNothing);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setYandexTranslateTagline() {
        this.yandexTranslateTagline = "<p><small>Powered by Yandex.Translate</small></p>";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAboutApplicationNotification() {
        this.aboutScreen = this.aboutScreen.replaceAll("\\n", "<br>");
        String message = this.aboutScreen + "<p><small><em>Invented with MIT App Inventor<br>appinventor.mit.edu</em></small>" + this.yandexTranslateTagline;
        Notifier.oneButtonAlert(this, message, "About This App", "Got it");
    }

    public void clear() {
        this.viewLayout.getLayoutManager().removeAllViews();
        defaultPropertyValues();
        this.screenInitialized = false;
    }

    public void deleteComponent(Object component) {
        if (component instanceof OnStopListener) {
            OnStopListener onStopListener = (OnStopListener) component;
            if (this.onStopListeners.contains(onStopListener)) {
                this.onStopListeners.remove(onStopListener);
            }
        }
        if (component instanceof OnResumeListener) {
            OnResumeListener onResumeListener = (OnResumeListener) component;
            if (this.onResumeListeners.contains(onResumeListener)) {
                this.onResumeListeners.remove(onResumeListener);
            }
        }
        if (component instanceof OnPauseListener) {
            OnPauseListener onPauseListener = (OnPauseListener) component;
            if (this.onPauseListeners.contains(onPauseListener)) {
                this.onPauseListeners.remove(onPauseListener);
            }
        }
        if (component instanceof OnDestroyListener) {
            OnDestroyListener onDestroyListener = (OnDestroyListener) component;
            if (this.onDestroyListeners.contains(onDestroyListener)) {
                this.onDestroyListeners.remove(onDestroyListener);
            }
        }
        if (component instanceof Deleteable) {
            ((Deleteable) component).onDelete();
        }
    }

    public void dontGrabTouchEventsForComponent() {
        this.frameLayout.requestDisallowInterceptTouchEvent(true);
    }

    protected boolean toastAllowed() {
        long now = System.nanoTime();
        if (now > this.lastToastTime + minimumToastWait) {
            this.lastToastTime = now;
            return true;
        }
        return false;
    }

    public void callInitialize(Object component) throws Throwable {
        try {
            Method method = component.getClass().getMethod("Initialize", null);
            try {
                Log.i(LOG_TAG, "calling Initialize method for Object " + component.toString());
                method.invoke(component, null);
            } catch (InvocationTargetException e) {
                Log.i(LOG_TAG, "invoke exception: " + e.getMessage());
                throw e.getTargetException();
            }
        } catch (NoSuchMethodException e2) {
        } catch (SecurityException e3) {
            Log.i(LOG_TAG, "Security exception " + e3.getMessage());
        }
    }

    public synchronized Bundle fullScreenVideoAction(int action, VideoPlayer source, Object data) {
        return this.fullScreenVideoUtil.performAction(action, source, data);
    }
}
