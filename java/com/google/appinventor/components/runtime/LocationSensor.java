package com.google.appinventor.components.runtime;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UsesPermissions(permissionNames = "android.permission.ACCESS_FINE_LOCATION,android.permission.ACCESS_COARSE_LOCATION,android.permission.ACCESS_MOCK_LOCATION,android.permission.ACCESS_LOCATION_EXTRA_COMMANDS")
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component providing location information, including longitude, latitude, altitude (if supported by the device), and address.  This can also perform \"geocoding\", converting a given address (not necessarily the current one) to a latitude (with the <code>LatitudeFromAddress</code> method) and a longitude (with the <code>LongitudeFromAddress</code> method).</p>\n<p>In order to function, the component must have its <code>Enabled</code> property set to True, and the device must have location sensing enabled through wireless networks or GPS satellites (if outdoors).</p>\nLocation information might not be immediately available when an app starts.  You'll have to wait a short time for a location provider to be found and used, or wait for the OnLocationChanged event", iconName = "images/locationSensor.png", nonVisible = true, version = 2)
@SimpleObject
/* loaded from: classes.dex */
public class LocationSensor extends AndroidNonvisibleComponent implements Component, OnStopListener, OnResumeListener, Deleteable {
    public static final int UNKNOWN_VALUE = 0;
    private List<String> allProviders;
    private double altitude;
    private int distanceInterval;
    private boolean enabled;
    private Geocoder geocoder;
    private final Handler handler;
    private boolean hasAltitude;
    private boolean hasLocationData;
    private Location lastLocation;
    private double latitude;
    private boolean listening;
    private final Criteria locationCriteria;
    private final LocationManager locationManager;
    private LocationProvider locationProvider;
    private double longitude;
    private MyLocationListener myLocationListener;
    private boolean providerLocked;
    private String providerName;
    private int timeInterval;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyLocationListener implements LocationListener {
        private MyLocationListener() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            LocationSensor.this.lastLocation = location;
            LocationSensor.this.longitude = location.getLongitude();
            LocationSensor.this.latitude = location.getLatitude();
            if (location.hasAltitude()) {
                LocationSensor.this.hasAltitude = true;
                LocationSensor.this.altitude = location.getAltitude();
            }
            LocationSensor.this.hasLocationData = true;
            LocationSensor.this.LocationChanged(LocationSensor.this.latitude, LocationSensor.this.longitude, LocationSensor.this.altitude);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
            LocationSensor.this.StatusChanged(provider, "Disabled");
            LocationSensor.this.stopListening();
            if (LocationSensor.this.enabled) {
                LocationSensor.this.RefreshProvider();
            }
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
            LocationSensor.this.StatusChanged(provider, "Enabled");
            LocationSensor.this.RefreshProvider();
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case 0:
                    LocationSensor.this.StatusChanged(provider, "OUT_OF_SERVICE");
                    if (provider.equals(LocationSensor.this.providerName)) {
                        LocationSensor.this.stopListening();
                        LocationSensor.this.RefreshProvider();
                        return;
                    }
                    return;
                case 1:
                    LocationSensor.this.StatusChanged(provider, "TEMPORARILY_UNAVAILABLE");
                    return;
                case 2:
                    LocationSensor.this.StatusChanged(provider, "AVAILABLE");
                    if (!provider.equals(LocationSensor.this.providerName) && !LocationSensor.this.allProviders.contains(provider)) {
                        LocationSensor.this.RefreshProvider();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public LocationSensor(ComponentContainer container) {
        super(container.$form());
        this.providerLocked = false;
        this.listening = false;
        this.longitude = 0.0d;
        this.latitude = 0.0d;
        this.altitude = 0.0d;
        this.hasLocationData = false;
        this.hasAltitude = false;
        this.enabled = true;
        this.handler = new Handler();
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.timeInterval = 60000;
        this.distanceInterval = 5;
        Context context = container.$context();
        this.geocoder = new Geocoder(context);
        this.locationManager = (LocationManager) context.getSystemService("location");
        this.locationCriteria = new Criteria();
        this.myLocationListener = new MyLocationListener();
        this.allProviders = new ArrayList();
        Enabled(this.enabled);
    }

    @SimpleEvent
    public void LocationChanged(double latitude, double longitude, double altitude) {
        if (this.enabled) {
            EventDispatcher.dispatchEvent(this, "LocationChanged", Double.valueOf(latitude), Double.valueOf(longitude), Double.valueOf(altitude));
        }
    }

    @SimpleEvent
    public void StatusChanged(String provider, String status) {
        if (this.enabled) {
            EventDispatcher.dispatchEvent(this, "StatusChanged", provider, status);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ProviderName() {
        return this.providerName == null ? "NO PROVIDER" : this.providerName;
    }

    @SimpleProperty
    public void ProviderName(String providerName) {
        this.providerName = providerName;
        if (empty(providerName) || !startProvider(providerName)) {
            RefreshProvider();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean ProviderLocked() {
        return this.providerLocked;
    }

    @SimpleProperty
    public void ProviderLocked(boolean lock) {
        this.providerLocked = lock;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "60000", editorType = PropertyTypeConstants.PROPERTY_TYPE_SENSOR_TIME_INTERVAL)
    public void TimeInterval(int interval) {
        if (interval >= 0 && interval <= 1000000) {
            this.timeInterval = interval;
            if (this.enabled) {
                RefreshProvider();
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum time interval, in milliseconds, that the sensor will try to use for sending out location updates. However, location updates will only be received when the location of the phone actually changes, and use of the specified time interval is not guaranteed. For example, if 1000 is used as the time interval, location updates will never be fired sooner than 1000ms, but they may be fired anytime after.")
    public int TimeInterval() {
        return this.timeInterval;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "5", editorType = PropertyTypeConstants.PROPERTY_TYPE_SENSOR_DIST_INTERVAL)
    public void DistanceInterval(int interval) {
        if (interval >= 0 && interval <= 1000) {
            this.distanceInterval = interval;
            if (this.enabled) {
                RefreshProvider();
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum distance interval, in meters, that the sensor will try to use for sending out location updates. For example, if this is set to 5, then the sensor will fire a LocationChanged event only after 5 meters have been traversed. However, the sensor does not guarantee that an update will be received at exactly the distance interval. It may take more than 5 meters to fire an event, for instance.")
    public int DistanceInterval() {
        return this.distanceInterval;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasLongitudeLatitude() {
        return this.hasLocationData && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasAltitude() {
        return this.hasAltitude && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasAccuracy() {
        return Accuracy() != 0.0d && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Longitude() {
        return this.longitude;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Latitude() {
        return this.latitude;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Altitude() {
        return this.altitude;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Accuracy() {
        if (this.lastLocation != null && this.lastLocation.hasAccuracy()) {
            return this.lastLocation.getAccuracy();
        }
        if (this.locationProvider != null) {
            return this.locationProvider.getAccuracy();
        }
        return 0.0d;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Enabled(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) {
            stopListening();
        } else {
            RefreshProvider();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String CurrentAddress() {
        Address address;
        if ((this.hasLocationData && this.latitude <= 90.0d && this.latitude >= -90.0d && this.longitude <= 180.0d) || this.longitude >= -180.0d) {
            try {
                List<Address> addresses = this.geocoder.getFromLocation(this.latitude, this.longitude, 1);
                if (addresses != null && addresses.size() == 1 && (address = addresses.get(0)) != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                        sb.append(address.getAddressLine(i));
                        sb.append("\n");
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                if ((e instanceof IllegalArgumentException) || (e instanceof IOException) || (e instanceof IndexOutOfBoundsException)) {
                    Log.e("LocationSensor", "Exception thrown by getting current address " + e.getMessage());
                } else {
                    Log.e("LocationSensor", "Unexpected exception thrown by getting current address " + e.getMessage());
                }
            }
        }
        return "No address available";
    }

    @SimpleFunction(description = "Derives latitude of given address")
    public double LatitudeFromAddress(String locationName) {
        try {
            List<Address> addressObjs = this.geocoder.getFromLocationName(locationName, 1);
            Log.i("LocationSensor", "latitude addressObjs size is " + addressObjs.size() + " for " + locationName);
            if (addressObjs == null || addressObjs.size() == 0) {
                throw new IOException("");
            }
            return addressObjs.get(0).getLatitude();
        } catch (IOException e) {
            this.form.dispatchErrorOccurredEvent(this, "LatitudeFromAddress", ErrorMessages.ERROR_LOCATION_SENSOR_LATITUDE_NOT_FOUND, locationName);
            return 0.0d;
        }
    }

    @SimpleFunction(description = "Derives longitude of given address")
    public double LongitudeFromAddress(String locationName) {
        try {
            List<Address> addressObjs = this.geocoder.getFromLocationName(locationName, 1);
            Log.i("LocationSensor", "longitude addressObjs size is " + addressObjs.size() + " for " + locationName);
            if (addressObjs == null || addressObjs.size() == 0) {
                throw new IOException("");
            }
            return addressObjs.get(0).getLongitude();
        } catch (IOException e) {
            this.form.dispatchErrorOccurredEvent(this, "LongitudeFromAddress", ErrorMessages.ERROR_LOCATION_SENSOR_LONGITUDE_NOT_FOUND, locationName);
            return 0.0d;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public List<String> AvailableProviders() {
        return this.allProviders;
    }

    public void RefreshProvider() {
        stopListening();
        if (this.providerLocked && !empty(this.providerName)) {
            this.listening = startProvider(this.providerName);
            return;
        }
        this.allProviders = this.locationManager.getProviders(true);
        String bProviderName = this.locationManager.getBestProvider(this.locationCriteria, true);
        if (bProviderName != null && !bProviderName.equals(this.allProviders.get(0))) {
            this.allProviders.add(0, bProviderName);
        }
        for (String providerN : this.allProviders) {
            this.listening = startProvider(providerN);
            if (this.listening) {
                if (!this.providerLocked) {
                    this.providerName = providerN;
                    return;
                }
                return;
            }
        }
    }

    private boolean startProvider(String providerName) {
        this.providerName = providerName;
        LocationProvider tLocationProvider = this.locationManager.getProvider(providerName);
        if (tLocationProvider == null) {
            Log.d("LocationSensor", "getProvider(" + providerName + ") returned null");
            return false;
        }
        stopListening();
        this.locationProvider = tLocationProvider;
        this.locationManager.requestLocationUpdates(providerName, this.timeInterval, this.distanceInterval, this.myLocationListener);
        this.listening = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListening() {
        if (this.listening) {
            this.locationManager.removeUpdates(this.myLocationListener);
            this.locationProvider = null;
            this.listening = false;
        }
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        if (this.enabled) {
            RefreshProvider();
        }
    }

    @Override // com.google.appinventor.components.runtime.OnStopListener
    public void onStop() {
        stopListening();
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        stopListening();
    }

    private boolean empty(String s) {
        return s == null || s.length() == 0;
    }
}
