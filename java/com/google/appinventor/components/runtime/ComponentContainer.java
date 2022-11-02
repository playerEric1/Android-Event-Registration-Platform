package com.google.appinventor.components.runtime;

import android.app.Activity;

/* loaded from: classes.dex */
public interface ComponentContainer {
    void $add(AndroidViewComponent androidViewComponent);

    Activity $context();

    Form $form();

    void setChildHeight(AndroidViewComponent androidViewComponent, int i);

    void setChildWidth(AndroidViewComponent androidViewComponent, int i);
}
