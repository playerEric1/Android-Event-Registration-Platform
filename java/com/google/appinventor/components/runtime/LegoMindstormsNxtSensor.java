package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.ErrorMessages;

@SimpleObject
/* loaded from: classes.dex */
public abstract class LegoMindstormsNxtSensor extends LegoMindstormsNxtBase {
    static final int SENSOR_MODE_ANGLESTEPMODE = 224;
    static final int SENSOR_MODE_BOOLEANMODE = 32;
    static final int SENSOR_MODE_CELSIUSMODE = 160;
    static final int SENSOR_MODE_FAHRENHEITMODE = 192;
    static final int SENSOR_MODE_MASK_MODE = 224;
    static final int SENSOR_MODE_MASK_SLOPE = 31;
    static final int SENSOR_MODE_PCTFULLSCALEMODE = 128;
    static final int SENSOR_MODE_PERIODCOUNTERMODE = 96;
    static final int SENSOR_MODE_RAWMODE = 0;
    static final int SENSOR_MODE_TRANSITIONCNTMODE = 64;
    static final int SENSOR_TYPE_ANGLE = 4;
    static final int SENSOR_TYPE_CUSTOM = 9;
    static final int SENSOR_TYPE_LIGHT_ACTIVE = 5;
    static final int SENSOR_TYPE_LIGHT_INACTIVE = 6;
    static final int SENSOR_TYPE_LOWSPEED = 10;
    static final int SENSOR_TYPE_LOWSPEED_9V = 11;
    static final int SENSOR_TYPE_NO_SENSOR = 0;
    static final int SENSOR_TYPE_REFLECTION = 3;
    static final int SENSOR_TYPE_SOUND_DB = 7;
    static final int SENSOR_TYPE_SOUND_DBA = 8;
    static final int SENSOR_TYPE_SWITCH = 1;
    static final int SENSOR_TYPE_TEMPERATURE = 2;
    protected int port;
    private String sensorPortLetter;

    public abstract void SensorPort(String str);

    protected abstract void initializeSensor(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SensorValue<T> {
        final boolean valid;
        final T value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SensorValue(boolean valid, T value) {
            this.valid = valid;
            this.value = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LegoMindstormsNxtSensor(ComponentContainer container, String logTag) {
        super(container, logTag);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The sensor port that the sensor is connected to.", userVisible = false)
    public String SensorPort() {
        return this.sensorPortLetter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setSensorPort(String sensorPortLetter) {
        try {
            int port = convertSensorPortLetterToNumber(sensorPortLetter);
            this.sensorPortLetter = sensorPortLetter;
            this.port = port;
            if (this.bluetooth != null && this.bluetooth.IsConnected()) {
                initializeSensor("SensorPort");
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "SensorPort", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, sensorPortLetter);
        }
    }

    @Override // com.google.appinventor.components.runtime.LegoMindstormsNxtBase, com.google.appinventor.components.runtime.BluetoothConnectionListener
    public void afterConnect(BluetoothConnectionBase bluetoothConnection) {
        initializeSensor("Connect");
    }
}
