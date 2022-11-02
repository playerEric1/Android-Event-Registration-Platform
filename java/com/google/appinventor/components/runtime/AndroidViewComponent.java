package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;
import com.google.appinventor.components.common.PropertyTypeConstants;

@SimpleObject
/* loaded from: classes.dex */
public abstract class AndroidViewComponent extends VisibleComponent {
    protected final ComponentContainer container;
    private int lastSetWidth = -3;
    private int lastSetHeight = -3;
    private int column = -1;
    private int row = -1;

    public abstract View getView();

    /* JADX INFO: Access modifiers changed from: protected */
    public AndroidViewComponent(ComponentContainer container) {
        this.container = container;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getView().getVisibility() == 0;
    }

    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_VISIBILITY)
    public void Visible(Boolean visibility) {
        getView().setVisibility(visibility.booleanValue() ? 0 : 8);
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public int Width() {
        return getView().getWidth();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Width(int width) {
        this.container.setChildWidth(this, width);
        this.lastSetWidth = width;
    }

    @SimplePropertyCopier
    public void CopyWidth(AndroidViewComponent sourceComponent) {
        Width(sourceComponent.lastSetWidth);
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public int Height() {
        return getView().getHeight();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Height(int height) {
        this.container.setChildHeight(this, height);
        this.lastSetHeight = height;
    }

    @SimplePropertyCopier
    public void CopyHeight(AndroidViewComponent sourceComponent) {
        Height(sourceComponent.lastSetHeight);
    }

    @SimpleProperty(userVisible = false)
    public int Column() {
        return this.column;
    }

    @SimpleProperty(userVisible = false)
    public void Column(int column) {
        this.column = column;
    }

    @SimpleProperty(userVisible = false)
    public int Row() {
        return this.row;
    }

    @SimpleProperty(userVisible = false)
    public void Row(int row) {
        this.row = row;
    }

    @Override // com.google.appinventor.components.runtime.Component
    public HandlesEventDispatching getDispatchDelegate() {
        return this.container.$form();
    }
}
