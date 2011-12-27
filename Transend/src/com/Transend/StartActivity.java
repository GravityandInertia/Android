package com.Transend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 11/23/11
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Setting up buttons
        Button endUser_button = (Button) findViewById(R.id.userButton);
        Button driver_button = (Button) findViewById(R.id.driverButton);
        Button help_button = (Button) findViewById(R.id.help);
        Button about_button = (Button) findViewById(R.id.about);

        //Setting up OnClick listeners
        endUser_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Defines what happens when endUser button is clicked
                RiderMapActivity riderMapActivity = new RiderMapActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, RiderMapActivity.class);
                startActivity(intent);
            }
        });

        driver_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Defines what happens when driver button is clicked
                DriverMapActivity riderMapActivity = new DriverMapActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, DriverMapActivity.class);
                startActivity(intent);
            }
        });

        help_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              //Defines what happens when help button is clicked
                HelpActivity riderMapActivity = new HelpActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        about_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            //Defines what happens when about button is clicked
            }
        });
    }


}

