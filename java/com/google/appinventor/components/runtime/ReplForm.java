package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.RetValManager;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ReplForm extends Form {
    private static final String REPL_ASSET_DIR = "/sdcard/AppInventor/assets/";
    public static ReplForm topform;
    private AppInvHTTPD httpdServer = null;
    private boolean IsUSBRepl = false;
    private boolean assetsLoaded = false;
    private boolean isDirect = false;
    private Object replResult = null;
    private String replResultFormName = null;

    public ReplForm() {
        topform = this;
    }

    @Override // com.google.appinventor.components.runtime.Form, android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d("ReplForm", "onCreate");
        Intent intent = getIntent();
        processExtras(intent, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.appinventor.components.runtime.Form, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.appinventor.components.runtime.Form, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.appinventor.components.runtime.Form, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.httpdServer != null) {
            this.httpdServer.stop();
            this.httpdServer = null;
        }
        finish();
        System.exit(0);
    }

    @Override // com.google.appinventor.components.runtime.Form
    protected void startNewForm(String nextFormName, Object startupValue) {
        if (startupValue != null) {
            this.startupValue = jsonEncodeForForm(startupValue, "open another screen with start value");
        }
        RetValManager.pushScreen(nextFormName, startupValue);
    }

    public void setFormName(String formName) {
        this.formName = formName;
        Log.d("ReplForm", "formName is now " + formName);
    }

    @Override // com.google.appinventor.components.runtime.Form
    protected void closeForm(Intent resultIntent) {
        RetValManager.popScreen("Not Yet");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setResult(Object result) {
        Log.d("ReplForm", "setResult: " + result);
        this.replResult = result;
        this.replResultFormName = this.formName;
    }

    @Override // com.google.appinventor.components.runtime.Form
    protected void closeApplicationFromBlocks() {
        runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.ReplForm.1
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(ReplForm.this, "Closing forms is not currently supported during development.", 1).show();
            }
        });
    }

    @Override // com.google.appinventor.components.runtime.Form, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        addSettingsButton(menu);
        return true;
    }

    public void addSettingsButton(Menu menu) {
        MenuItem showSettingsItem = menu.add(0, 0, 3, "Settings").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.appinventor.components.runtime.ReplForm.2
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem item) {
                PhoneStatus.doSettings();
                return true;
            }
        });
        showSettingsItem.setIcon(17301651);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.appinventor.components.runtime.Form, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("ReplForm", "onNewIntent Called");
        processExtras(intent, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void HandleReturnValues() {
        Log.d("ReplForm", "HandleReturnValues() Called, replResult = " + this.replResult);
        if (this.replResult != null) {
            OtherScreenClosed(this.replResultFormName, this.replResult);
            Log.d("ReplForm", "Called OtherScreenClosed");
            this.replResult = null;
        }
    }

    protected void processExtras(Intent intent, boolean restart) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Log.d("ReplForm", "extras: " + extras);
            Iterator<String> keys = extras.keySet().iterator();
            while (keys.hasNext()) {
                Log.d("ReplForm", "Extra Key: " + keys.next());
            }
        }
        if (extras != null && extras.getBoolean("rundirect")) {
            Log.d("ReplForm", "processExtras rundirect is true and restart is " + restart);
            this.isDirect = true;
            this.assetsLoaded = true;
            if (restart) {
                clear();
                if (this.httpdServer != null) {
                    this.httpdServer.resetSeq();
                    return;
                }
                startHTTPD(true);
                AppInvHTTPD appInvHTTPD = this.httpdServer;
                AppInvHTTPD.setHmacKey("emulator");
            }
        }
    }

    public boolean isDirect() {
        return this.isDirect;
    }

    public void setIsUSBrepl() {
        this.IsUSBRepl = true;
    }

    public void startHTTPD(boolean secure) {
        try {
            if (this.httpdServer == null) {
                checkAssetDir();
                this.httpdServer = new AppInvHTTPD(8001, new java.io.File(REPL_ASSET_DIR), secure, this);
                Log.i("ReplForm", "started AppInvHTTPD");
            }
        } catch (IOException ex) {
            Log.e("ReplForm", "Setting up NanoHTTPD: " + ex.toString());
        }
    }

    private void checkAssetDir() {
        java.io.File f = new java.io.File(REPL_ASSET_DIR);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public boolean isAssetsLoaded() {
        return this.assetsLoaded;
    }

    public void setAssetsLoaded() {
        this.assetsLoaded = true;
    }
}
