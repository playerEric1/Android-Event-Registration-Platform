package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;

/* loaded from: classes.dex */
public class ElementsUtil {
    public static YailList elementsFromString(String itemString) {
        YailList items = new YailList();
        if (itemString.length() > 0) {
            YailList items2 = YailList.makeList(itemString.split(" *, *"));
            return items2;
        }
        return items;
    }

    public static YailList elements(YailList itemList, String componentName) {
        Object[] objects = itemList.toStringArray();
        for (Object obj : objects) {
            if (!(obj instanceof String)) {
                throw new YailRuntimeError("Items passed to " + componentName + " must be Strings", "Error");
            }
        }
        return itemList;
    }

    public static int selectionIndex(int index, YailList items) {
        if (index <= 0 || index > items.size()) {
            return 0;
        }
        return index;
    }

    public static String setSelectionFromIndex(int index, YailList items) {
        return index == 0 ? "" : items.getString(index - 1);
    }

    public static int setSelectedIndexFromValue(String value, YailList items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.getString(i).equals(value)) {
                return i + 1;
            }
        }
        return 0;
    }
}
