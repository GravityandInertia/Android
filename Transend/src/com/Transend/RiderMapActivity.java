package com.Transend;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.maps.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RiderMapActivity extends MapActivity
{
    private static MapView userMap;
    private DialogInterface.OnClickListener mProfileDialogListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent;
            intent = new Intent(RiderMapActivity.this, DriverProfileActivity.class);
            startActivity(intent);
        }
    };

    private DialogInterface.OnClickListener mUserDialogListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
        }
    };


    private GeoPoint myGeoPoint = new GeoPoint(33430000,-112020000);
    int zoomLevel = 15;

    @Override
    public void onCreate(final Bundle icicle)
    {
        super.onCreate(icicle);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000, 1, ll);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){
            double lat = location.getLatitude()* 1000000.0;
            double longitude = location.getLongitude()* 1000000.0;
            int geoLat =(int) (lat);
            int geoLong = (int) (longitude);
            myGeoPoint = new GeoPoint(geoLat,geoLong);
        }

        GeoPoint driverGeoPoint = new GeoPoint(myGeoPoint.getLatitudeE6()+
                5000,myGeoPoint.getLongitudeE6()+5000);
        setupMapView(driverGeoPoint,myGeoPoint);
    }

    private void setupMapView(GeoPoint driverGeoPoint, GeoPoint userGeoPoint) {
        setContentView(R.layout.map);

        userMap = (MapView) findViewById(R.id.mapView);
        userMap.displayZoomControls(true);
        userMap.setBuiltInZoomControls(true);

        MapController userMapController = userMap.getController();

        //set map controls
        userMapController.setZoom(zoomLevel);
        userMapController.setCenter(myGeoPoint);

        setOverlay(driverGeoPoint,userGeoPoint);

        //updates the view of the map
        userMap.invalidate();
    }


    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateUserMap(GeoPoint driverGeoPoint, GeoPoint userGeoPoint)
    {
        MapController userMapController = userMap.getController();
        setOverlay(driverGeoPoint,userGeoPoint);
        userMap.invalidate();
        JSONObject jsonObject = writeJSON(myGeoPoint,"Brandon");
        postMyLocationtoWeb(jsonObject);

    }

    private JSONObject writeJSON(GeoPoint geoPoint, String nickname) {
        JSONObject json = new JSONObject();
            try{
                json.put("Nickname",nickname);
                json.put("Latitude",geoPoint.getLatitudeE6());
                json.put("Longitude",geoPoint.getLongitudeE6());
            } 
            
            catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        
        Toast.makeText(RiderMapActivity.this,json.toString(),Toast.LENGTH_LONG).show();

        return json;
    }

    private class mylocationlistener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.d("LOCATION CHANGED", location.getLatitude() + "");
                Log.d("LOCATION CHANGED", location.getLongitude() + "");
                Toast.makeText(RiderMapActivity.this,
                        location.getLatitude() + "" + location.getLongitude(),
                        Toast.LENGTH_LONG).show();

                double lat = location.getLatitude()* 1000000.0;
                double longitude = location.getLongitude()* 1000000.0;
                int geoLat =(int) (lat);
                int geoLong = (int) (longitude);
                myGeoPoint = new GeoPoint(geoLat,geoLong);
                userMap.getOverlays().clear();
                Toast.makeText(RiderMapActivity.this,myGeoPoint.toString(),Toast.LENGTH_LONG).show();

                //setting mock driver location
                geoLat =(int) (myGeoPoint.getLatitudeE6()+5000);
                geoLong =(int) (myGeoPoint.getLongitudeE6()+5000);
                GeoPoint driverGeoPoint = new GeoPoint(geoLat,geoLong);
                updateUserMap(driverGeoPoint, myGeoPoint);
            }
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    private void setOverlay(GeoPoint driverGeoPoint, GeoPoint userGeoPoint){
        List<Overlay> drivermapOverlays = userMap.getOverlays();
        List<Overlay> mapOverlays = userMap.getOverlays();
        Drawable drawable = RiderMapActivity.this.getResources()
                .getDrawable(R.drawable.icon);

        UserItemizedOverlay useritemizedoverlay = new UserItemizedOverlay(drawable, RiderMapActivity.this);
        MyItemizedOverlay driveritemizedoverlay = new MyItemizedOverlay(drawable, RiderMapActivity.this);

        OverlayItem overlayitem = new OverlayItem(userGeoPoint, "I'm the First User!"
                , "I'm at Sky Harbor!");

        OverlayItem driveroverlayitem = new OverlayItem(driverGeoPoint,"Johnny Cab", "Time Rate: $10/Hr " +
                "\nMileage Rate: $2/Mile");

        useritemizedoverlay.addOverlay(overlayitem);
        driveritemizedoverlay.addOverlay(driveroverlayitem);
        mapOverlays.add(useritemizedoverlay);
        drivermapOverlays.add(driveritemizedoverlay);

        driveritemizedoverlay.setOnClickListener(mProfileDialogListener);
        useritemizedoverlay.setOnClickListener(mUserDialogListener);
    }

    private void postMyLocationtoWeb(JSONObject jsonObject) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://transendapp.appspot.com");//todo figure out how to post this

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("data",jsonObject.toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
}
