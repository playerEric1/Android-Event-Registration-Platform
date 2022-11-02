package com.google.appinventor.components.runtime;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.IOException;

@UsesPermissions(permissionNames = "android.permission.INTERNET")
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>Ball</code>s and other <code>ImageSprite</code>s) and the edge of the Canvas, and move according to its property values.  Its appearance is that of the image specified in its <code>Picture</code> property (unless its <code>Visible</code> property is <code>False</code>.</p> <p>To have an <code>ImageSprite</code> move 10 pixels to the left every 1000 milliseconds (one second), for example, you would set the <code>Speed</code> property to 10 [pixels], the <code>Interval</code> property to 1000 [milliseconds], the <code>Heading</code> property to 180 [degrees], and the <code>Enabled</code> property to <code>True</code>.  A sprite whose <code>Rotates</code> property is <code>True</code> will rotate its image as the sprite's <code>Heading</code> changes.  Checking for collisions with a rotated sprite currently checks the sprite's unrotated position so that collision checking will be inaccurate for tall narrow or short wide sprites that are rotated.  Any of the sprite properties can be changed at any time under program control.</p> ", version = 6)
@SimpleObject
/* loaded from: classes.dex */
public class ImageSprite extends Sprite {
    private double cachedRotationHeading;
    private BitmapDrawable drawable;
    private final Form form;
    private int heightHint;
    private Matrix mat;
    private String picturePath;
    private Bitmap rotatedBitmap;
    private BitmapDrawable rotatedDrawable;
    private boolean rotates;
    private boolean rotationCached;
    private Bitmap scaledBitmap;
    private Bitmap unrotatedBitmap;
    private int widthHint;

    public ImageSprite(ComponentContainer container) {
        super(container);
        this.widthHint = -1;
        this.heightHint = -1;
        this.picturePath = "";
        this.form = container.$form();
        this.mat = new Matrix();
        this.rotates = true;
        this.rotationCached = false;
    }

    @Override // com.google.appinventor.components.runtime.Sprite
    public void onDraw(android.graphics.Canvas canvas) {
        if (this.unrotatedBitmap != null && this.visible) {
            int xinit = (int) Math.round(this.xLeft);
            int yinit = (int) Math.round(this.yTop);
            int w = Width();
            int h = Height();
            if (!this.rotates) {
                this.drawable.setBounds(xinit, yinit, xinit + w, yinit + h);
                this.drawable.draw(canvas);
                return;
            }
            if (!this.rotationCached || this.cachedRotationHeading != Heading()) {
                this.mat.setRotate((float) (-Heading()), w / 2, h / 2);
                if (w != this.unrotatedBitmap.getWidth() || h != this.unrotatedBitmap.getHeight()) {
                    this.scaledBitmap = Bitmap.createScaledBitmap(this.unrotatedBitmap, w, h, true);
                } else {
                    this.scaledBitmap = this.unrotatedBitmap;
                }
                this.rotatedBitmap = Bitmap.createBitmap(this.scaledBitmap, 0, 0, this.scaledBitmap.getWidth(), this.scaledBitmap.getHeight(), this.mat, true);
                this.rotatedDrawable = new BitmapDrawable(this.rotatedBitmap);
                this.cachedRotationHeading = Heading();
            }
            this.rotatedDrawable.setBounds(((w / 2) + xinit) - (this.rotatedBitmap.getWidth() / 2), ((h / 2) + yinit) - (this.rotatedBitmap.getHeight() / 2), (w / 2) + xinit + (this.rotatedBitmap.getWidth() / 2), (h / 2) + yinit + (this.rotatedBitmap.getHeight() / 2));
            this.rotatedDrawable.draw(canvas);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The picture that determines the sprite's appearence")
    public String Picture() {
        return this.picturePath;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void Picture(String path) {
        if (path == null) {
            path = "";
        }
        this.picturePath = path;
        try {
            this.drawable = MediaUtil.getBitmapDrawable(this.form, this.picturePath);
        } catch (IOException e) {
            Log.e("ImageSprite", "Unable to load " + this.picturePath);
            this.drawable = null;
        }
        if (this.drawable != null) {
            this.unrotatedBitmap = this.drawable.getBitmap();
        } else {
            this.unrotatedBitmap = null;
        }
        registerChange();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public int Height() {
        if (this.heightHint == -1 || this.heightHint == -2) {
            if (this.drawable == null) {
                return 0;
            }
            return this.drawable.getBitmap().getHeight();
        }
        return this.heightHint;
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Height(int height) {
        this.heightHint = height;
        registerChange();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public int Width() {
        if (this.widthHint == -1 || this.widthHint == -2) {
            if (this.drawable == null) {
                return 0;
            }
            return this.drawable.getBitmap().getWidth();
        }
        return this.widthHint;
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Width(int width) {
        this.widthHint = width;
        registerChange();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the sprite image rotates to match the sprite's heading. If false, the sprite image does not rotate when the sprite changes heading. The sprite rotates around its centerpoint.")
    public boolean Rotates() {
        return this.rotates;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Rotates(boolean rotates) {
        this.rotates = rotates;
        registerChange();
    }
}
