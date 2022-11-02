package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ViewUtil;

@DesignerComponent(category = ComponentCategory.LAYOUT, description = "<p>A formatting element in which to place components that should be displayed in tabular form.</p>", version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class TableArrangement extends AndroidViewComponent implements Component, ComponentContainer {
    private final Activity context;
    private final TableLayout viewLayout;

    public TableArrangement(ComponentContainer container) {
        super(container);
        this.context = container.$context();
        this.viewLayout = new TableLayout(this.context, 2, 2);
        container.$add(this);
    }

    @SimpleProperty(userVisible = false)
    public int Columns() {
        return this.viewLayout.getNumColumns();
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "2", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void Columns(int numColumns) {
        this.viewLayout.setNumColumns(numColumns);
    }

    @SimpleProperty(userVisible = false)
    public int Rows() {
        return this.viewLayout.getNumRows();
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "2", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void Rows(int numRows) {
        this.viewLayout.setNumRows(numRows);
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public Activity $context() {
        return this.context;
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public Form $form() {
        return this.container.$form();
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void $add(AndroidViewComponent component) {
        this.viewLayout.add(component);
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildWidth(AndroidViewComponent component, int width) {
        ViewUtil.setChildWidthForTableLayout(component.getView(), width);
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildHeight(AndroidViewComponent component, int height) {
        ViewUtil.setChildHeightForTableLayout(component.getView(), height);
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.viewLayout.getLayoutManager();
    }
}
