package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
/* loaded from: classes.dex */
public class HVArrangement extends AndroidViewComponent implements Component, ComponentContainer {
    private final AlignmentUtil alignmentSetter;
    private final Activity context;
    private int horizontalAlignment;
    private final int orientation;
    private int verticalAlignment;
    private final LinearLayout viewLayout;

    public HVArrangement(ComponentContainer container, int orientation) {
        super(container);
        this.context = container.$context();
        this.orientation = orientation;
        this.viewLayout = new LinearLayout(this.context, orientation, 100, 100);
        this.alignmentSetter = new AlignmentUtil(this.viewLayout);
        this.horizontalAlignment = 1;
        this.verticalAlignment = 1;
        this.alignmentSetter.setHorizontalAlignment(this.horizontalAlignment);
        this.alignmentSetter.setVerticalAlignment(this.verticalAlignment);
        container.$add(this);
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
        if (this.orientation == 0) {
            ViewUtil.setChildWidthForHorizontalLayout(component.getView(), width);
        } else {
            ViewUtil.setChildWidthForVerticalLayout(component.getView(), width);
        }
    }

    @Override // com.google.appinventor.components.runtime.ComponentContainer
    public void setChildHeight(AndroidViewComponent component, int height) {
        if (this.orientation == 0) {
            ViewUtil.setChildHeightForHorizontalLayout(component.getView(), height);
        } else {
            ViewUtil.setChildHeightForVerticalLayout(component.getView(), height);
        }
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.viewLayout.getLayoutManager();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = horizontally centered,  3 = right aligned.  Alignment has no effect if the arrangement's width is automatic.")
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
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(alignment));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.")
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
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(alignment));
        }
    }
}
