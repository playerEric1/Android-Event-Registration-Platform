package com.google.appinventor.components.runtime;

import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.SeekBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "A Slider is a progress bar that adds a draggable thumb. You can touch the thumb and drag left or right to set the slider thumb position. As the Slider thumb is dragged, it will trigger the PositionChanged event, reporting the position of the Slider thumb. The reported position of the Slider thumb can be used to dynamically update another component attribute, such as the font size of a TextBox or the radius of a Ball.", version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class Slider extends AndroidViewComponent implements SeekBar.OnSeekBarChangeListener {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "Slider";
    private static final int initialLeftColor = -14336;
    private static final String initialLeftColorString = "&HFFFFC800";
    private static final int initialRightColor = -7829368;
    private static final String initialRightColorString = "&HFF888888";
    private ClipDrawable beforeThumb;
    private LayerDrawable fullBar;
    private int leftColor;
    private float maxValue;
    private float minValue;
    private int rightColor;
    private final SeekBar seekbar;
    private float thumbPosition;

    public Slider(ComponentContainer container) {
        super(container);
        this.seekbar = new SeekBar(container.$context());
        this.fullBar = (LayerDrawable) this.seekbar.getProgressDrawable();
        this.beforeThumb = (ClipDrawable) this.fullBar.findDrawableByLayerId(16908301);
        this.leftColor = -14336;
        this.rightColor = -7829368;
        setSliderColors();
        container.$add(this);
        this.minValue = 10.0f;
        this.maxValue = 50.0f;
        this.thumbPosition = 30.0f;
        this.seekbar.setOnSeekBarChangeListener(this);
        this.seekbar.setMax(100);
        setSeekbarPosition();
    }

    private void setSliderColors() {
        this.fullBar.setColorFilter(this.rightColor, PorterDuff.Mode.SRC);
        this.beforeThumb.setColorFilter(this.leftColor, PorterDuff.Mode.SRC);
    }

    private void setSeekbarPosition() {
        float seekbarPosition = ((this.thumbPosition - this.minValue) / (this.maxValue - this.minValue)) * 100.0f;
        this.seekbar.setProgress((int) seekbarPosition);
    }

    @SimpleProperty(description = "Sets the position of the slider thumb. If this value is greater than MaxValue, then it will be set to same value as MaxValue. If this value is less than MinValue, then it will be set to same value as MinValue.", userVisible = true)
    @DesignerProperty(defaultValue = "30.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void ThumbPosition(float position) {
        this.thumbPosition = Math.max(Math.min(position, this.maxValue), this.minValue);
        setSeekbarPosition();
        PositionChanged(this.thumbPosition);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the position of slider thumb", userVisible = true)
    public float ThumbPosition() {
        return this.thumbPosition;
    }

    @SimpleProperty(description = "Sets the minimum value of slider.  Changing the minimum value also resets Thumbposition to be halfway between the (new) minimum and the maximum. If the new minimum is greater than the current maximum, then minimum and maximum will both be set to this value.  Setting MinValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.", userVisible = true)
    @DesignerProperty(defaultValue = "10.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void MinValue(float value) {
        this.minValue = value;
        this.maxValue = Math.max(value, this.maxValue);
        ThumbPosition((this.minValue + this.maxValue) / 2.0f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the value of slider min value.", userVisible = true)
    public float MinValue() {
        return this.minValue;
    }

    @SimpleProperty(description = "Sets the maximum value of slider.  Changing the maximum value also resets Thumbposition to be halfway between the minimum and the (new) maximum. If the new maximum is less than the current minimum, then minimum and maximum will both be set to this value.  Setting MaxValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.", userVisible = true)
    @DesignerProperty(defaultValue = "50.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void MaxValue(float value) {
        this.maxValue = value;
        this.minValue = Math.min(value, this.minValue);
        ThumbPosition((this.minValue + this.maxValue) / 2.0f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the slider max value.", userVisible = true)
    public float MaxValue() {
        return this.maxValue;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of slider to the left of the thumb.")
    public int ColorLeft() {
        return this.leftColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "&HFFFFC800", editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void ColorLeft(int argb) {
        this.leftColor = argb;
        setSliderColors();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of slider to the left of the thumb.")
    public int ColorRight() {
        return this.rightColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "&HFF888888", editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void ColorRight(int argb) {
        this.rightColor = argb;
        setSliderColors();
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.seekbar;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.thumbPosition = (((this.maxValue - this.minValue) * progress) / 100.0f) + this.minValue;
        PositionChanged(this.thumbPosition);
    }

    @SimpleEvent
    public void PositionChanged(float thumbPosition) {
        EventDispatcher.dispatchEvent(this, "PositionChanged", Float.valueOf(thumbPosition));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    public int Height() {
        return getView().getHeight();
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent, com.google.appinventor.components.runtime.VisibleComponent
    public void Height(int height) {
        this.container.setChildHeight(this, height);
    }
}
