package com.google.appinventor.components.runtime;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
/* loaded from: classes.dex */
public abstract class TextBoxBase extends AndroidViewComponent implements View.OnFocusChangeListener {
    private int backgroundColor;
    private boolean bold;
    private Drawable defaultTextBoxDrawable;
    private int fontTypeface;
    private String hint;
    private boolean italic;
    private int textAlignment;
    private int textColor;
    protected final EditText view;

    public TextBoxBase(ComponentContainer container, EditText textview) {
        super(container);
        this.view = textview;
        this.view.setOnFocusChangeListener(this);
        this.defaultTextBoxDrawable = this.view.getBackground();
        container.$add(this);
        container.setChildWidth(this, ComponentConstants.TEXTBOX_PREFERRED_WIDTH);
        TextAlignment(0);
        Enabled(true);
        this.fontTypeface = 0;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, this.italic);
        FontSize(14.0f);
        Hint("");
        Text("");
        TextColor(Component.COLOR_BLACK);
    }

    @Override // com.google.appinventor.components.runtime.AndroidViewComponent
    public View getView() {
        return this.view;
    }

    @SimpleEvent
    public void GotFocus() {
        EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }

    @SimpleEvent
    public void LostFocus() {
        EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should be left justified, centered, or right justified.  By default, text is left justified.", userVisible = false)
    public int TextAlignment() {
        return this.textAlignment;
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "0", editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXTALIGNMENT)
    public void TextAlignment(int alignment) {
        this.textAlignment = alignment;
        TextViewUtil.setAlignment(this.view, alignment, false);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The background color of the input box.  You can choose a color by name in the Designer or in the Blocks Editor.  The default background color is 'default' (shaded 3-D look).")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_DEFAULT, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void BackgroundColor(int argb) {
        this.backgroundColor = argb;
        if (argb != 0) {
            TextViewUtil.setBackgroundColor(this.view, argb);
        } else {
            ViewUtil.setBackgroundDrawable(this.view, this.defaultTextBoxDrawable);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the user can enter text into this input box.  By default, this is true.")
    public boolean Enabled() {
        return TextViewUtil.isEnabled(this.view);
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Enabled(boolean enabled) {
        TextViewUtil.setEnabled(this.view, enabled);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the font for the text should be bold.  By default, it is not.", userVisible = false)
    public boolean FontBold() {
        return this.bold;
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void FontBold(boolean bold) {
        this.bold = bold;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, bold, this.italic);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should appear in italics.  By default, it does not.", userVisible = false)
    public boolean FontItalic() {
        return this.italic;
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void FontItalic(boolean italic) {
        this.italic = italic;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, italic);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size for the text.  By default, it is 14.0 points.")
    public float FontSize() {
        return TextViewUtil.getFontSize(this.view);
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "14.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void FontSize(float size) {
        TextViewUtil.setFontSize(this.view, size);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font for the text.  The value can be changed in the Designer.", userVisible = false)
    public int FontTypeface() {
        return this.fontTypeface;
    }

    @SimpleProperty(userVisible = false)
    @DesignerProperty(defaultValue = "0", editorType = PropertyTypeConstants.PROPERTY_TYPE_TYPEFACE)
    public void FontTypeface(int typeface) {
        this.fontTypeface = typeface;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, this.italic);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text that should appear faintly in the input box to provide a hint as to what the user should enter.  This can only be seen if the <code>Text</code> property is empty.")
    public String Hint() {
        return this.hint;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Hint(String hint) {
        this.hint = hint;
        this.view.setHint(hint);
        this.view.invalidate();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Text() {
        return TextViewUtil.getText(this.view);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The text in the input box, which can be set by the programmer in the Designer or Blocks Editor, or it can be entered by the user (unless the <code>Enabled</code> property is false).")
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Text(String text) {
        TextViewUtil.setText(this.view, text);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color for the text.  You can choose a color by name in the Designer or in the Blocks Editor.  The default text color is black.")
    public int TextColor() {
        return this.textColor;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.DEFAULT_VALUE_COLOR_BLACK, editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR)
    public void TextColor(int argb) {
        this.textColor = argb;
        if (argb != 0) {
            TextViewUtil.setTextColor(this.view, argb);
        } else {
            TextViewUtil.setTextColor(this.view, Component.COLOR_BLACK);
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View previouslyFocused, boolean gainFocus) {
        if (gainFocus) {
            GotFocus();
        } else {
            LostFocus();
        }
    }
}
