package com.Transend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import org.json.JSONObject;

public class MyMapActivity extends MapActivity
{
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

                        MapView userMap = (MapView) findViewById(R.id.mapView);
                        userMap.displayZoomControls(true);
                        userMap.setBuiltInZoomControls(true);

                        JSONObject json = new
                    }

                });
            }
});


    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
