package com.Transend;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.maps.*;

import java.util.List;

public class MyMapActivity extends MapActivity
{
    private static MapView userMap;
    private MapView driverMap;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        //Define two buttons
        Button userButton = (Button) findViewById(R.id.userButton);
        Button driverButton = (Button) findViewById(R.id.driverButton);

        userButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
            //Calls the login screen
            setContentView(R.layout.login);

            Button submit = (Button) findViewById(R.id.submitButton);
                submit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        setContentView(R.layout.map);

                        userMap = (MapView) findViewById(R.id.mapView);
                        userMap.displayZoomControls(true);
                        userMap.setBuiltInZoomControls(true);
                        MapController userMapController = userMap.getController();

                        userMapController.setZoom(15);

                        GeoPoint myGeoPoint = new GeoPoint(33430000,-112020000);
                        userMapController.setCenter(myGeoPoint);

                        List<Overlay> mapOverlays = userMap.getOverlays();
                        Resources resource = null;
                        Drawable drawable = MyMapActivity.this.getResources()
                                .getDrawable(R.drawable.icon);




                        MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable, MyMapActivity.this);

                        OverlayItem overlayitem = new OverlayItem(myGeoPoint, "Hola, Mundo!", "I'm in Phoenix!");

                        itemizedoverlay.addOverlay(overlayitem);
                        mapOverlays.add(itemizedoverlay);

                        userMap.invalidate();

                        launchUserService();
                    }


                });
            }
});

            driverButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
            //Calls the login screen
            setContentView(R.layout.login);

            Button submit = (Button) findViewById(R.id.submitButton);
                submit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        setContentView(R.layout.map);

                        driverMap = (MapView) findViewById(R.id.mapView);
                        driverMap.displayZoomControls(true);
                        driverMap.setBuiltInZoomControls(true);

                        launchDriverService();
                    }

                });
            }
});


    }

    private void launchDriverService() {
    }

    private void launchUserService() {
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void updateUserMap(Location location)
    {
        GeoPoint myPoint;

        //double latitude = location.getLatitude();
        //double longitude = location.getLongitude();


        //myPoint = new GeoPoint(35650000,-11200000);

        //todo program view update of the map in android
        //MapController mapController =  userMap.getController();

        //mapController.setZoom(19);
        //mapController.setCenter(myPoint);

        //userMap.invalidate();
    }

}
