package com.google.appinventor.components.runtime;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@UsesPermissions(permissionNames = "android.permission.INTERNET")
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Component for displaying images.  The picture to display, and other aspects of the Image's appearance, can be specified in the Designer or in the Blocks Editor.", version = 1)
@SimpleObject
/* loaded from: classes.dex */
public final class Image extends AndroidViewComponent {
    private String picturePath;
    private final ImageView view;

    public Image(ComponentContainer container) {
        super(container);
        this.picturePath = "";
        this.view = new ImageView(container.$context()) { // from class: com.google.appinventor.components.runtime.Image.1
            @Override // android.widget.ImageView, android.view.View
            public boolean verifyDrawable(Drawable dr) {
                super.verifyDrawable(dr);
                return true;
            }
        };
        container.$add(this);
        this.view.setFocusable(true);
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.view;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public String Picture() {
        return this.picturePath;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void Picture(String path) {
        Drawable drawable;
        if (path == null) {
            path = "";
        }
        this.picturePath = path;
        try {
            drawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.picturePath);
        } catch (IOException e) {
            Log.e("Image", "Unable to load " + this.picturePath);
            drawable = null;
        }
        ViewUtil.setImage(this.view, drawable);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is a limited form of animation that can attach a small number of motion types to images.  The allowable motions are ScrollRightSlow, ScrollRight, ScrollRightFast, ScrollLeftSlow, ScrollLeft, ScrollLeftFast, and Stop")
    public void Animation(String animation) {
        AnimationUtil.ApplyAnimation(this.view, animation);
    }
}
