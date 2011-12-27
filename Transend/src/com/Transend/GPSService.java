package com.Transend;

import android.app.Service;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 12/27/11
 * Time: 8:36 AM
 * To change this template use File | Settings | File Templates.
 */



public class GPSService extends Service {
    private GpsStatus gpsStatus = null;
    private LocationManager locationManager = null;
    private Criteria criteria = null;
    private String locationProvider = null;
    private GpsStatus.Listener gpsListener;
    private double latitude;
    private double longitude;
    private float radius;
    private long expiration;
    private long minTime = 30000;
    private float minDistance = 1;
    private float time;
    private Looper looper;
    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            time = location.getTime();
        }

        public void onStatusChanged(String s, int i, Bundle bundle) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void onProviderEnabled(String s) {
            locationProvider = locationManager.getBestProvider(criteria,true);
        }

        public void onProviderDisabled(String s) {
            locationProvider = locationManager.getBestProvider(criteria,true);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        //Defining what happens when the service intent is bound to an activity
        criteria.setAccuracy(Criteria.ACCURACY_FINE); //setting accuracy
        criteria.setAltitudeRequired(false);//setting if altitude is required
        criteria.setBearingRequired(false);//setting if bearing is required
        criteria.setCostAllowed(false);
        criteria.setSpeedRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        String locationProvider = locationManager.getBestProvider(criteria, true);
        locationManager.getLastKnownLocation(locationProvider);
        locationManager.getGpsStatus(gpsStatus);
        locationManager.addGpsStatusListener(gpsListener);
        //locationManager.addProximityAlert(latitude,longitude,radius,expiration,intent);

        locationManager.requestLocationUpdates(locationProvider,minTime,minDistance,locationListener,looper);

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
