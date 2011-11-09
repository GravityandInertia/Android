package com.Transend;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.IBinder;

import static android.location.Criteria.ACCURACY_FINE;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 11/8/11
 * Time: 8:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransendService extends Service {

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){

        getDriverLocations();

        getMyLocation();

        returnMyLocationToMaps();
    }

    private void returnMyLocationToMaps() {
    }

    private void getMyLocation() {
        LocationManager lm;
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Defining requirements for provider
        Criteria requirement = null;
        requirement.setAccuracy(ACCURACY_FINE);
        requirement.setCostAllowed(false);
        requirement.setBearingRequired(false);
        requirement.setSpeedRequired(false);

        //retrieving best provider for given requirements
        String gpsProvider = lm.getBestProvider(requirement, true);

        try{
        lm.requestLocationUpdates(gpsProvider, 1000, );
        }

        catch(IllegalArgumentException e){

        }

        catch(SecurityException){

        }
    }



    private void getDriverLocations() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
