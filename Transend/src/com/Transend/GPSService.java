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
    private Location mpreviousLocation;
    private Location mlocation;
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
            if(isBetterLocation(mlocation,mpreviousLocation)==true)
            {
                mpreviousLocation = mlocation;
                mlocation = location;
            }
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
        mpreviousLocation = locationManager.getLastKnownLocation(locationProvider);
        locationManager.getGpsStatus(gpsStatus);
        locationManager.addGpsStatusListener(gpsListener);
        //locationManager.addProximityAlert(latitude,longitude,radius,expiration,intent);

        locationManager.requestLocationUpdates(locationProvider,minTime,minDistance,locationListener,looper);



        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private static final int TWO_MINUTES = 1000 * 60 * 2;

    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
}
