package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;

@SimpleObject
/* loaded from: classes.dex */
public abstract class VisibleComponent implements Component {
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public abstract int Height();

    @SimpleProperty
    public abstract void Height(int i);

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public abstract int Width();

    @SimpleProperty
    public abstract void Width(int i);
}
