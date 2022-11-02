package com.google.appinventor.common.version;

/* loaded from: classes.dex */
public final class GitBuildId {
    public static final String ACRA_URI = "${acra.uri}";
    public static final String ANT_BUILD_DATE = "December 31 2014";
    public static final String GIT_BUILD_FINGERPRINT = "ddb6e38fbe9b443884c06ad7006b1a7071a6c812";
    public static final String GIT_BUILD_VERSION = "nls-588-gddb6e38";

    private GitBuildId() {
    }

    public static String getVersion() {
        if (GIT_BUILD_VERSION != "" && !GIT_BUILD_VERSION.contains(" ")) {
            return GIT_BUILD_VERSION;
        }
        return "none";
    }

    public static String getFingerprint() {
        return GIT_BUILD_FINGERPRINT;
    }

    public static String getDate() {
        return ANT_BUILD_DATE;
    }

    public static String getAcraUri() {
        return ACRA_URI.equals(ACRA_URI) ? "" : ACRA_URI.trim();
    }
}
