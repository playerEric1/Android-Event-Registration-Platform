package com.google.appinventor.components.runtime;

import android.app.Application;
import android.util.Log;
import com.google.appinventor.common.version.GitBuildId;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(formKey = "")
/* loaded from: classes.dex */
public class ReplApplication extends Application {
    private static ReplApplication thisInstance;
    private boolean active = false;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        thisInstance = this;
        String acraUri = GitBuildId.getAcraUri();
        if (acraUri.equals("")) {
            Log.i("ReplApplication", "ACRA Not Active");
            return;
        }
        Log.i("ReplApplication", "ACRA Active, URI = " + acraUri);
        ACRAConfiguration config = ACRA.getNewDefaultConfig(this);
        config.setFormUri(acraUri);
        config.setDisableSSLCertValidation(true);
        ACRA.setConfig(config);
        ACRA.init(this);
        this.active = true;
    }

    public static void reportError(Throwable ex) {
        if (thisInstance != null && thisInstance.active) {
            ACRA.getErrorReporter().handleException(ex);
        }
    }
}
