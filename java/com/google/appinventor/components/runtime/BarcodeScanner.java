package com.google.appinventor.components.runtime;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "Component for using the Barcode Scanner to read a barcode", iconName = "images/barcodeScanner.png", nonVisible = true, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class BarcodeScanner extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private static final String SCANNER_RESULT_NAME = "SCAN_RESULT";
    private static final String SCAN_INTENT = "com.google.zxing.client.android.SCAN";
    private final ComponentContainer container;
    private int requestCode;
    private String result;

    public BarcodeScanner(ComponentContainer container) {
        super(container.$form());
        this.result = "";
        this.container = container;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Text result of the previous scan.")
    public String Result() {
        return this.result;
    }

    @SimpleFunction(description = "Begins a barcode scan, using the camera. When the scan is complete, the AfterScan event will be raised.")
    public void DoScan() {
        Intent intent = new Intent(SCAN_INTENT);
        if (this.requestCode == 0) {
            this.requestCode = this.form.registerForActivityResult(this);
        }
        try {
            this.container.$context().startActivityForResult(intent, this.requestCode);
        } catch (ActivityNotFoundException e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "BarcodeScanner", ErrorMessages.ERROR_NO_SCANNER_FOUND, "");
        }
    }

    @Override // com.google.appinventor.components.runtime.ActivityResultListener
    public void resultReturned(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode && resultCode == -1) {
            if (data.hasExtra(SCANNER_RESULT_NAME)) {
                this.result = data.getStringExtra(SCANNER_RESULT_NAME);
            } else {
                this.result = "";
            }
            AfterScan(this.result);
        }
    }

    @SimpleEvent
    public void AfterScan(String result) {
        EventDispatcher.dispatchEvent(this, "AfterScan", result);
    }
}
