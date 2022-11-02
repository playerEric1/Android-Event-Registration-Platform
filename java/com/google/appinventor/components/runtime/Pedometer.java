package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.lang.reflect.Array;

@UsesPermissions(permissionNames = "android.permission.ACCESS_FINE_LOCATION")
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "Component that can count steps.", iconName = "images/pedometer.png", nonVisible = true, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class Pedometer extends AndroidNonvisibleComponent implements Component, LocationListener, SensorEventListener, Deleteable {
    private static final int DIMENSIONS = 3;
    private static final int INTERVAL_VARIATION = 250;
    private static final int MIN_SATELLITES = 4;
    private static final int NUM_INTERVALS = 2;
    private static final float PEAK_VALLEY_RANGE = 4.0f;
    private static final String PREFS_NAME = "PedometerPrefs";
    private static final float STRIDE_LENGTH = 0.73f;
    private static final String TAG = "Pedometer";
    private static final int WIN_SIZE = 20;
    private boolean calibrateSteps;
    private final Context context;
    private Location currentLocation;
    private float distWhenGPSLost;
    private long elapsedTimestamp;
    private boolean firstGpsReading;
    private boolean foundNonStep;
    private boolean[] foundValley;
    private boolean gpsAvailable;
    private float gpsDistance;
    private long gpsStepTime;
    private int intervalPos;
    private int lastNumSteps;
    private float[] lastValley;
    private float[][] lastValues;
    private final LocationManager locationManager;
    private Location locationWhenGPSLost;
    private int numStepsRaw;
    private int numStepsWithFilter;
    private int[] peak;
    private boolean pedometerPaused;
    private float[] prevDiff;
    private Location prevLocation;
    private long prevStopClockTime;
    private final SensorManager sensorManager;
    private boolean startPeaking;
    private long startTime;
    private boolean statusMoving;
    private long[] stepInterval;
    private long stepTimestamp;
    private int stopDetectionTimeout;
    private float strideLength;
    private float totalDistance;
    private boolean useGps;
    private int[] valley;
    private int winPos;

    public Pedometer(ComponentContainer container) {
        super(container.$form());
        this.stopDetectionTimeout = 2000;
        this.winPos = 0;
        this.intervalPos = 0;
        this.numStepsWithFilter = 0;
        this.numStepsRaw = 0;
        this.lastNumSteps = 0;
        this.peak = new int[3];
        this.valley = new int[3];
        this.lastValley = new float[3];
        this.lastValues = (float[][]) Array.newInstance(Float.TYPE, 20, 3);
        this.prevDiff = new float[3];
        this.strideLength = STRIDE_LENGTH;
        this.totalDistance = 0.0f;
        this.distWhenGPSLost = 0.0f;
        this.gpsDistance = 0.0f;
        this.stepInterval = new long[2];
        this.stepTimestamp = 0L;
        this.elapsedTimestamp = 0L;
        this.startTime = 0L;
        this.prevStopClockTime = 0L;
        this.gpsStepTime = 0L;
        this.foundValley = new boolean[3];
        this.startPeaking = false;
        this.foundNonStep = true;
        this.gpsAvailable = false;
        this.calibrateSteps = true;
        this.pedometerPaused = true;
        this.useGps = true;
        this.statusMoving = false;
        this.firstGpsReading = true;
        this.context = container.$context();
        this.winPos = 0;
        this.startPeaking = false;
        this.numStepsWithFilter = 0;
        this.numStepsRaw = 0;
        this.firstGpsReading = true;
        this.gpsDistance = 0.0f;
        for (int i = 0; i < 3; i++) {
            this.foundValley[i] = true;
            this.lastValley[i] = 0.0f;
        }
        this.sensorManager = (SensorManager) this.context.getSystemService("sensor");
        this.locationManager = (LocationManager) this.context.getSystemService("location");
        this.locationManager.requestLocationUpdates("gps", 0L, 0.0f, this);
        SharedPreferences settings = this.context.getSharedPreferences(PREFS_NAME, 0);
        this.strideLength = settings.getFloat("Pedometer.stridelength", STRIDE_LENGTH);
        this.totalDistance = settings.getFloat("Pedometer.distance", 0.0f);
        this.numStepsRaw = settings.getInt("Pedometer.prevStepCount", 0);
        this.prevStopClockTime = settings.getLong("Pedometer.clockTime", 0L);
        this.numStepsWithFilter = this.numStepsRaw;
        this.startTime = System.currentTimeMillis();
        Log.d(TAG, "Pedometer Created");
    }

    @SimpleFunction
    public void Start() {
        if (this.pedometerPaused) {
            this.pedometerPaused = false;
            this.sensorManager.registerListener(this, this.sensorManager.getSensorList(1).get(0), 0);
            this.startTime = System.currentTimeMillis();
        }
    }

    @SimpleFunction
    public void Stop() {
        Pause();
        this.locationManager.removeUpdates(this);
        this.useGps = false;
        this.calibrateSteps = false;
        setGpsAvailable(false);
    }

    @SimpleFunction
    public void Reset() {
        this.numStepsWithFilter = 0;
        this.numStepsRaw = 0;
        this.totalDistance = 0.0f;
        this.calibrateSteps = false;
        this.prevStopClockTime = 0L;
        this.startTime = System.currentTimeMillis();
    }

    @SimpleFunction
    public void Resume() {
        Start();
    }

    @SimpleFunction
    public void Pause() {
        if (!this.pedometerPaused) {
            this.pedometerPaused = true;
            this.statusMoving = false;
            this.sensorManager.unregisterListener(this);
            Log.d(TAG, "Unregistered listener on pause");
            this.prevStopClockTime += System.currentTimeMillis() - this.startTime;
        }
    }

    @SimpleFunction(description = "Saves the pedometer state to the phone")
    public void Save() {
        SharedPreferences settings = this.context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("Pedometer.stridelength", this.strideLength);
        editor.putFloat("Pedometer.distance", this.totalDistance);
        editor.putInt("Pedometer.prevStepCount", this.numStepsRaw);
        if (this.pedometerPaused) {
            editor.putLong("Pedometer.clockTime", this.prevStopClockTime);
        } else {
            editor.putLong("Pedometer.clockTime", this.prevStopClockTime + (System.currentTimeMillis() - this.startTime));
        }
        editor.putLong("Pedometer.closeTime", System.currentTimeMillis());
        editor.commit();
        Log.d(TAG, "Pedometer state saved.");
    }

    @SimpleEvent(description = "This event is run when a raw step is detected")
    public void SimpleStep(int simpleSteps, float distance) {
        EventDispatcher.dispatchEvent(this, "SimpleStep", Integer.valueOf(simpleSteps), Float.valueOf(distance));
    }

    @SimpleEvent(description = "This event is run when a walking step is detected")
    public void WalkStep(int walkSteps, float distance) {
        EventDispatcher.dispatchEvent(this, "WalkStep", Integer.valueOf(walkSteps), Float.valueOf(distance));
    }

    @SimpleEvent
    public void StartedMoving() {
        EventDispatcher.dispatchEvent(this, "StartedMoving", new Object[0]);
    }

    @SimpleEvent
    public void StoppedMoving() {
        EventDispatcher.dispatchEvent(this, "StoppedMoving", new Object[0]);
    }

    @SimpleEvent
    public void CalibrationFailed() {
        EventDispatcher.dispatchEvent(this, "CalibrationFailed", new Object[0]);
    }

    @SimpleEvent
    public void GPSAvailable() {
        EventDispatcher.dispatchEvent(this, "GPSAvailable", new Object[0]);
    }

    @SimpleEvent
    public void GPSLost() {
        EventDispatcher.dispatchEvent(this, "GPSLost", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "true", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void CalibrateStrideLength(boolean cal) {
        if (!this.gpsAvailable && cal) {
            CalibrationFailed();
            return;
        }
        if (cal) {
            this.useGps = true;
        }
        this.calibrateSteps = cal;
    }

    @SimpleProperty
    public boolean CalibrateStrideLength() {
        return this.calibrateSteps;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "0.73", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void StrideLength(float length) {
        CalibrateStrideLength(false);
        this.strideLength = length;
    }

    @SimpleProperty
    public float StrideLength() {
        return this.strideLength;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "2000", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void StopDetectionTimeout(int timeout) {
        this.stopDetectionTimeout = timeout;
    }

    @SimpleProperty
    public int StopDetectionTimeout() {
        return this.stopDetectionTimeout;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "true", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void UseGPS(boolean gps) {
        if (!gps && this.useGps) {
            this.locationManager.removeUpdates(this);
        } else if (gps && !this.useGps) {
            this.locationManager.requestLocationUpdates("gps", 0L, 0.0f, this);
        }
        this.useGps = gps;
    }

    @SimpleProperty
    public boolean UseGPS() {
        return this.useGps;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float Distance() {
        return this.totalDistance;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Moving() {
        return this.statusMoving;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public long ElapsedTime() {
        return this.pedometerPaused ? this.prevStopClockTime : this.prevStopClockTime + (System.currentTimeMillis() - this.startTime);
    }

    private boolean areStepsEquallySpaced() {
        float avg = 0.0f;
        int num = 0;
        long[] arr$ = this.stepInterval;
        for (long interval : arr$) {
            if (interval > 0) {
                num++;
                avg += (float) interval;
            }
        }
        float avg2 = avg / num;
        long[] arr$2 = this.stepInterval;
        int len$ = arr$2.length;
        for (int i$ = 0; i$ < len$; i$++) {
            if (Math.abs(((float) arr$2[i$]) - avg2) > 250.0f) {
                return false;
            }
        }
        return true;
    }

    private void getPeak() {
        int mid = (this.winPos + 10) % 20;
        for (int k = 0; k < 3; k++) {
            this.peak[k] = mid;
            int i = 0;
            while (true) {
                if (i >= 20) {
                    break;
                } else if (i == mid || this.lastValues[i][k] < this.lastValues[mid][k]) {
                    i++;
                } else {
                    this.peak[k] = -1;
                    break;
                }
            }
        }
    }

    private void getValley() {
        int mid = (this.winPos + 10) % 20;
        for (int k = 0; k < 3; k++) {
            this.valley[k] = mid;
            int i = 0;
            while (true) {
                if (i >= 20) {
                    break;
                } else if (i == mid || this.lastValues[i][k] > this.lastValues[mid][k]) {
                    i++;
                } else {
                    this.valley[k] = -1;
                    break;
                }
            }
        }
    }

    private void setGpsAvailable(boolean available) {
        if (!this.gpsAvailable && available) {
            this.gpsAvailable = true;
            GPSAvailable();
        } else if (this.gpsAvailable && !available) {
            this.gpsAvailable = false;
            GPSLost();
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "Accelerometer accuracy changed.");
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        float[] fArr;
        if (event.sensor.getType() == 1) {
            float[] values = event.values;
            if (this.startPeaking) {
                getPeak();
                getValley();
            }
            int argmax = this.prevDiff[0] > this.prevDiff[1] ? 0 : 1;
            if (this.prevDiff[2] > this.prevDiff[argmax]) {
                argmax = 2;
            }
            for (int k = 0; k < 3; k++) {
                if (this.startPeaking && this.peak[k] >= 0) {
                    if (this.foundValley[k] && this.lastValues[this.peak[k]][k] - this.lastValley[k] > PEAK_VALLEY_RANGE) {
                        if (argmax == k) {
                            long timestamp = System.currentTimeMillis();
                            this.stepInterval[this.intervalPos] = timestamp - this.stepTimestamp;
                            this.intervalPos = (this.intervalPos + 1) % 2;
                            this.stepTimestamp = timestamp;
                            if (areStepsEquallySpaced()) {
                                if (this.foundNonStep) {
                                    this.numStepsWithFilter += 2;
                                    if (!this.gpsAvailable) {
                                        this.totalDistance += this.strideLength * 2.0f;
                                    }
                                    this.foundNonStep = false;
                                }
                                this.numStepsWithFilter++;
                                WalkStep(this.numStepsWithFilter, this.totalDistance);
                                if (!this.gpsAvailable) {
                                    this.totalDistance += this.strideLength;
                                }
                            } else {
                                this.foundNonStep = true;
                            }
                            this.numStepsRaw++;
                            SimpleStep(this.numStepsRaw, this.totalDistance);
                            if (!this.statusMoving) {
                                this.statusMoving = true;
                                StartedMoving();
                            }
                        }
                        this.foundValley[k] = false;
                        this.prevDiff[k] = this.lastValues[this.peak[k]][k] - this.lastValley[k];
                    } else {
                        this.prevDiff[k] = 0.0f;
                    }
                }
                if (this.startPeaking && this.valley[k] >= 0) {
                    this.foundValley[k] = true;
                    this.lastValley[k] = this.lastValues[this.valley[k]][k];
                }
                this.lastValues[this.winPos][k] = values[k];
            }
            this.elapsedTimestamp = System.currentTimeMillis();
            if (this.elapsedTimestamp - this.stepTimestamp > this.stopDetectionTimeout) {
                if (this.statusMoving) {
                    this.statusMoving = false;
                    StoppedMoving();
                }
                this.stepTimestamp = this.elapsedTimestamp;
            }
            int prev = this.winPos + (-1) < 0 ? 19 : this.winPos - 1;
            for (int k2 = 0; k2 < 3; k2++) {
                if (this.lastValues[prev][k2] == this.lastValues[this.winPos][k2]) {
                    this.lastValues[this.winPos][k2] = (float) (fArr[k2] + 0.001d);
                }
            }
            if (this.winPos == 19 && !this.startPeaking) {
                this.startPeaking = true;
            }
            this.winPos = (this.winPos + 1) % 20;
        }
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location loc) {
        if (this.statusMoving && !this.pedometerPaused && this.useGps) {
            float distDelta = 0.0f;
            this.currentLocation = loc;
            if (this.currentLocation.getAccuracy() > 10.0f) {
                setGpsAvailable(false);
                return;
            }
            setGpsAvailable(true);
            if (this.prevLocation != null) {
                distDelta = this.currentLocation.distanceTo(this.prevLocation);
                if (distDelta > 0.1d && distDelta < 10.0f) {
                    this.totalDistance += distDelta;
                    this.prevLocation = this.currentLocation;
                }
            } else {
                if (this.locationWhenGPSLost != null) {
                    float distDarkness = this.currentLocation.distanceTo(this.locationWhenGPSLost);
                    this.totalDistance = this.distWhenGPSLost + (((this.totalDistance - this.distWhenGPSLost) + distDarkness) / 2.0f);
                }
                this.gpsStepTime = System.currentTimeMillis();
                this.prevLocation = this.currentLocation;
            }
            if (this.calibrateSteps) {
                if (!this.firstGpsReading) {
                    this.gpsDistance += distDelta;
                    int stepsTaken = this.numStepsRaw - this.lastNumSteps;
                    this.strideLength = this.gpsDistance / stepsTaken;
                    return;
                }
                this.firstGpsReading = false;
                this.lastNumSteps = this.numStepsRaw;
                return;
            }
            this.firstGpsReading = true;
            this.gpsDistance = 0.0f;
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String provider) {
        this.distWhenGPSLost = this.totalDistance;
        this.locationWhenGPSLost = this.currentLocation;
        this.firstGpsReading = true;
        this.prevLocation = null;
        setGpsAvailable(false);
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String provider) {
        setGpsAvailable(true);
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String provider, int status, Bundle data) {
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        this.sensorManager.unregisterListener(this);
        this.locationManager.removeUpdates(this);
    }
}
