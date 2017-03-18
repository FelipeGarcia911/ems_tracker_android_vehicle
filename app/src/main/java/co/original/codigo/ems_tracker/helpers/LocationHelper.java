package co.original.codigo.ems_tracker.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class LocationHelper implements LocationListener {
    private LocationManager mLocationManager;
    private Location mLastLocation;
    private Context context;
    private String provider;

    private int timeUpdate;
    private int distanceUpdate;

    private String TAG = "LocationHelper";

    public LocationHelper(Context context, int timeUpdate, int distanceUpdate) {
        this.context = context;
        this.timeUpdate = timeUpdate;
        this.distanceUpdate = distanceUpdate;

        initLocationManager();
    }

    private void initLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            updateProvider();
            updateLocationListener();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "onLocationChanged: " + location);
        mLastLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(TAG, "onStatusChanged: " + provider);
        updateProvider();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(TAG, "onProviderEnabled: " + provider);
        updateProvider();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(TAG, "onProviderDisabled: " + provider);
        updateProvider();
    }

    public Location getLastKnownLocation() {
        return mLastLocation;
    }

    private void updateLocationListener() {
        mLocationManager.removeUpdates(this);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(provider, timeUpdate, distanceUpdate, this);
        }
    }

    private void updateProvider(){
        provider = getProviderName();
    }

    private String getProviderName() {
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM); // Chose your desired power consumption level.
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy requirement.
        criteria.setSpeedRequired(true); // Chose if speed for first location fix is required.
        criteria.setAltitudeRequired(false); // Choose if you use altitude.
        criteria.setBearingRequired(false); // Choose if you use bearing.
        criteria.setCostAllowed(false); // Choose if this provider can waste money :-)

        // Provide your criteria and flag enabledOnly that tells
        // LocationManager only to return active providers.
        return mLocationManager.getBestProvider(criteria, true);
    }
}
