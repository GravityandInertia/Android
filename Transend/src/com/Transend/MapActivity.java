package com.Transend;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MapActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Define two buttons
        Button userButton = (Button) findViewById(R.id.userButton);
        Button driverButton = (Button) findViewById(R.id.driverButton);

    }
}
