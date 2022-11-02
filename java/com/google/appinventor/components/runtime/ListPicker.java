package com.google.appinventor.components.runtime;

import android.content.Intent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.YailList;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "<p>A button that, when clicked on, displays a list of texts for the user to choose among. The texts can be specified through the Designer or Blocks Editor by setting the <code>ElementsFromString</code> property to their string-separated concatenation (for example, <em>choice 1, choice 2, choice 3</em>) or by setting the <code>Elements</code> property to a List in the Blocks editor.</p><p>Setting property ShowFilterBar to true, will make the list searchable.  Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).</p>", version = 8)
@SimpleObject
/* loaded from: classes.dex */
public class ListPicker extends Picker implements ActivityResultListener, Deleteable, OnResumeListener {
    private static final boolean DEFAULT_ENABLED = false;
    private YailList items;
    private boolean resumedFromListFlag;
    private String selection;
    private int selectionIndex;
    private boolean showFilter;
    private String title;
    private static final String LIST_ACTIVITY_CLASS = ListPickerActivity.class.getName();
    static final String LIST_ACTIVITY_ARG_NAME = LIST_ACTIVITY_CLASS + ".list";
    static final String LIST_ACTIVITY_RESULT_NAME = LIST_ACTIVITY_CLASS + ".selection";
    static final String LIST_ACTIVITY_RESULT_INDEX = LIST_ACTIVITY_CLASS + ".index";
    static final String LIST_ACTIVITY_ANIM_TYPE = LIST_ACTIVITY_CLASS + ".anim";
    static final String LIST_ACTIVITY_SHOW_SEARCH_BAR = LIST_ACTIVITY_CLASS + ".search";
    static final String LIST_ACTIVITY_TITLE = LIST_ACTIVITY_CLASS + ".title";

    public ListPicker(ComponentContainer container) {
        super(container);
        this.showFilter = false;
        this.title = "";
        this.resumedFromListFlag = false;
        this.items = new YailList();
        this.selection = "";
        this.selectionIndex = 0;
        container.$form().registerForOnResume(this);
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        if (this.resumedFromListFlag) {
            this.container.$form().getWindow().setSoftInputMode(3);
            this.resumedFromListFlag = false;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The selected item.  When directly changed by the programmer, the SelectionIndex property is also changed to the first item in the ListPicker with the given value.  If the value does not appear, SelectionIndex will be set to 0.")
    public String Selection() {
        return this.selection;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Selection(String value) {
        this.selection = value;
        this.selectionIndex = ElementsUtil.setSelectedIndexFromValue(value, this.items);
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void ShowFilterBar(boolean showFilter) {
        this.showFilter = showFilter;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current state of ShowFilterBar indicating if Search Filter Bar will be displayed on ListPicker or not")
    public boolean ShowFilterBar() {
        return this.showFilter;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The index of the currently selected item, starting at 1.  If no item is selected, the value will be 0.  If an attempt is made to set this to a number less than 1 or greater than the number of items in the ListPicker, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
    public int SelectionIndex() {
        return this.selectionIndex;
    }

    @SimpleProperty
    public void SelectionIndex(int index) {
        this.selectionIndex = ElementsUtil.selectionIndex(index, this.items);
        this.selection = ElementsUtil.setSelectionFromIndex(index, this.items);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public YailList Elements() {
        return this.items;
    }

    @SimpleProperty
    public void Elements(YailList itemList) {
        this.items = ElementsUtil.elements(itemList, "ListPicker");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void ElementsFromString(String itemstring) {
        this.items = ElementsUtil.elementsFromString(itemstring);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Optional title displayed at the top of the list of choices.")
    public String Title() {
        return this.title;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Title(String title) {
        this.title = title;
    }

    @Override // com.google.appinventor.components.runtime.Picker
    public Intent getIntent() {
        Intent intent = new Intent();
        intent.setClassName(this.container.$context(), LIST_ACTIVITY_CLASS);
        intent.putExtra(LIST_ACTIVITY_ARG_NAME, this.items.toStringArray());
        intent.putExtra(LIST_ACTIVITY_SHOW_SEARCH_BAR, String.valueOf(this.showFilter));
        if (!this.title.equals("")) {
            intent.putExtra(LIST_ACTIVITY_TITLE, this.title);
        }
        String openAnim = this.container.$form().getOpenAnimType();
        intent.putExtra(LIST_ACTIVITY_ANIM_TYPE, openAnim);
        return intent;
    }

    @Override // com.google.appinventor.components.runtime.ActivityResultListener
    public void resultReturned(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode && resultCode == -1) {
            if (data.hasExtra(LIST_ACTIVITY_RESULT_NAME)) {
                this.selection = data.getStringExtra(LIST_ACTIVITY_RESULT_NAME);
            } else {
                this.selection = "";
            }
            this.selectionIndex = data.getIntExtra(LIST_ACTIVITY_RESULT_INDEX, 0);
            AfterPicking();
            this.resumedFromListFlag = true;
        }
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        this.container.$form().unregisterForActivityResult(this);
    }
}
