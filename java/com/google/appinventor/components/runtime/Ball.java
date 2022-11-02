package com.google.appinventor.components.runtime;

import android.graphics.Paint;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.PaintUtil;

@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A round 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>ImageSprite</code>s and other <code>Ball</code>s) and the edge of the Canvas, and move according to its property values.</p><p>For example, to have a <code>Ball</code> move 4 pixels toward the top of a <code>Canvas</code> every 500 milliseconds (half second), you would set the <code>Speed</code> property to 4 [pixels], the <code>Interval</code> property to 500 [milliseconds], the <code>Heading</code> property to 90 [degrees], and the <code>Enabled</code> property to <code>True</code>.  These and its other properties can be changed at any time.</p><p>The difference between a Ball and an <code>ImageSprite</code> is that the latter can get its appearance from an image file, while a Ball's appearance can only be changed by varying its <code>PaintColor</code> and <code>Radius</code> properties.</p>", version = 5)
@SimpleObject
/* loaded from: classes.dex */
public final class Ball extends Sprite {
    static final int DEFAULT_RADIUS = 5;
    private Paint paint;
    private int paintColor;
    private int radius;

    public Ball(ComponentContainer container) {
        super(container);
        this.paint = new Paint();
        PaintColor(Component.COLOR_BLACK);
        Radius(5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.appinventor.components.runtime.Sprite
    public void onDraw(android.graphics.Canvas canvas) {
        if (this.visible) {
            canvas.drawCircle(((float) this.xLeft) + this.radius, ((float) this.yTop) + this.radius, this.radius, this.paint);
        }
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    public int Height() {
        return this.radius * 2;
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    public void Height(int height) {
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    public int Width() {
        return this.radius * 2;
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    public void Width(int width) {
    }

    @Override // com.google.appinventor.components.runtime.Sprite
    public boolean containsPoint(double qx, double qy) {
        double xCenter = this.xLeft + this.radius;
        double yCenter = this.yTop + this.radius;
        return ((qx - xCenter) * (qx - xCenter)) + ((qy - yCenter) * (qy - yCenter)) <= ((double) (this.radius * this.radius));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(defaultValue = "5", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void Radius(int radius) {
        this.radius = radius;
        registerChange();
    }

    @SimpleProperty
    public int Radius() {
        return this.radius;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int PaintColor() {
        return this.paintColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_BLACK, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void PaintColor(int argb) {
        this.paintColor = argb;
        if (argb != 0) {
            PaintUtil.changePaint(this.paint, argb);
        } else {
            PaintUtil.changePaint(this.paint, Component.COLOR_BLACK);
        }
        registerChange();
    }
}
