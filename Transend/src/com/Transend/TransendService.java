package com.Transend;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.location.Criteria.ACCURACY_FINE;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 11/8/11
 * Time: 8:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransendService extends Service {

    private ArrayList<Location> driversLocations;
    private Location myLocation;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){

        try {
            getMyLocation();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Does this need to be changed?
        }

        getDriverLocationsfromWeb();

        returnMyLocationToMaps(myLocation);//todo program this method
        
        returnDriverLocationstoMaps();

        postMyLocationtoWeb();
    }

    private void returnDriverLocationstoMaps() {
        //todo program this method
    }

    private void postMyLocationtoWeb() {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://transendapp.appspot.com");//todo figure out how to post this

        try {
        // Add your data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("longitude", "This is Awesome!"));
        nameValuePairs.add(new BasicNameValuePair("latitude", "AndDev is Cool!"));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        // Execute HTTP Post Request
        HttpResponse response = httpclient.execute(httppost);

    } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
    } catch (IOException e) {
        // TODO Auto-generated catch block
    }
    }

    private void returnMyLocationToMaps(Location location) {
        //todo is this calling the updatemap method for the already running instance of MyMapActivity
        MyMapActivity.updateUserMap(myLocation);
    }


    private void getMyLocation() throws IOException {
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

        //Defining a Location Listener
        LocationListener ll = new LocationListener() {
            public void onLocationChanged(Location location) {
                myLocation.setLatitude(location.getLatitude());
                myLocation.setLongitude(location.getLongitude());
            }

            public void onStatusChanged(String s, int i, Bundle bundle) {


            }

            public void onProviderEnabled(String s) {

            }

            public void onProviderDisabled(String s) {

            }
        };

        //Defining a looper variable
        Looper looper = null;
        looper.getThread();

        try{
        lm.requestLocationUpdates(gpsProvider,30000,100,ll, looper );
        }

        catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.toString());
        }

        catch(SecurityException e){
            throw new IOException(e.toString());
        }
    }

    private void getDriverLocationsfromWeb() {

    }
}
