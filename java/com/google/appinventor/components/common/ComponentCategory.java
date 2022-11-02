package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum ComponentCategory {
    USERINTERFACE("User Interface"),
    LAYOUT("Layout"),
    MEDIA("Media"),
    ANIMATION("Drawing and Animation"),
    SENSORS("Sensors"),
    SOCIAL("Social"),
    STORAGE("Storage"),
    CONNECTIVITY("Connectivity"),
    LEGOMINDSTORMS("LEGO® MINDSTORMS®"),
    INTERNAL("For internal use only"),
    UNINITIALIZED("Uninitialized");
    
    private static final Map<String, String> DOC_MAP = new HashMap();
    private String name;

    static {
        DOC_MAP.put("User Interface", "userinterface");
        DOC_MAP.put("Layout", "layout");
        DOC_MAP.put("media", "media");
        DOC_MAP.put("Drawing and Animation", "animation");
        DOC_MAP.put("Sensors", "sensors");
        DOC_MAP.put("Social", "social");
        DOC_MAP.put("Storage", "storage");
        DOC_MAP.put("Connectivity", "connectivity");
        DOC_MAP.put("LEGO® MINDSTORMS®", "legomindstorms");
    }

    ComponentCategory(String categoryName) {
        this.name = categoryName;
    }

    public String getName() {
        return this.name;
    }

    public String getDocName() {
        return DOC_MAP.get(this.name);
    }
}
