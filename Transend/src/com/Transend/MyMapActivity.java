package com.Transend;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.maps.*;

import java.util.List;

public class MyMapActivity extends MapActivity
{
    private static MapView userMap;
    private MapView driverMap;
    private DialogInterface.OnClickListener mProfileDialogListener;

    {
        mProfileDialogListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {

                //Initializing view  items for the driver profile
                TextView driverName = (TextView) findViewById(R.id.Driver_Name);
                //TextView rating = (TextView) findViewById(R.id.Rating);
                //RatingBar ratingBar = (RatingBar) findViewById(R.id.Rating_Bar);

                //Setting up the generic driver profile picture

                ImageView driverPic = (ImageView) findViewById(R.id.DriverPicture);

                //Setting the view to the driver profile
                setContentView(R.layout.driverprofile);
            }
        };
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        //Define two buttons
        Button userButton = (Button) findViewById(R.id.userButton);
        Button driverButton = (Button) findViewById(R.id.driverButton);

        //Defining the onclicklistener
        userButton.setOnClickListener(new View.OnClickListener(){

            //Defining what happens when the user button is clicked
            public void onClick(View v){
            //Calls the login screen
            setContentView(R.layout.login);

            //Telling the system what data to read to know what the submit button looks like
            Button submit = (Button) findViewById(R.id.submitButton);
                //Defining the OnClickListener for the submit button
                submit.setOnClickListener(new View.OnClickListener() {
                    //Defining what happens when the submit button is clicked
                    public void onClick(View view) {
                        //Sets the layout for the map
                        setContentView(R.layout.map);

                        //fiind the xml attributes of the mapview
                        userMap = (MapView) findViewById(R.id.mapView);

                        //displays the zoom controls
                        userMap.displayZoomControls(true);
                        userMap.setBuiltInZoomControls(true);

                        //defines the map controller
                        MapController userMapController = userMap.getController();

                        //sets the zoom level of the map
                        userMapController.setZoom(15);

                        //temporary point being used as default location of the user
                        GeoPoint myGeoPoint = new GeoPoint(33430000,-112020000);

                        //sets the center of the map on the user
                        userMapController.setCenter(myGeoPoint);

                        //getting a trivial location on the map to show a temporary driver
                        int latSpan = userMap.getLatitudeSpan();
                        int longSpan = userMap.getLongitudeSpan();

                        GeoPoint driver1GeoPoint = new GeoPoint(33440000, -112030000);

                        List<Overlay> mapOverlays = userMap.getOverlays();
                        Drawable drawable = MyMapActivity.this.getResources()
                                .getDrawable(R.drawable.icon);

                        MyItemizedOverlay useritemizedoverlay = new MyItemizedOverlay(drawable, MyMapActivity.this);

                        OverlayItem overlayitem = new OverlayItem(myGeoPoint, "I'm the First User!"
                                , "I'm at Sky Harbor!");

                        OverlayItem driveroverlayitem = new OverlayItem(driver1GeoPoint,"Johnny Cab", "Time Rate: $10/Hr " +
                                "\nMileage Rate: $2/Mile");

                        useritemizedoverlay.addOverlay(overlayitem);
                        useritemizedoverlay.addOverlay(driveroverlayitem);
                        mapOverlays.add(useritemizedoverlay);

                        useritemizedoverlay.setOnClickListener(mProfileDialogListener);

                        //updates the view of the map
                        userMap.invalidate();

                        //launches the service that runs check your location and retrieving driver location updates
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

    public void showDriverProfile(){
        setContentView(R.layout.driverprofile);
    }


}
