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
       /* Intent intent;
        intent = new Intent(StartActivity.this,GPSService.class);
        startService(intent); */

        //Setting up buttons
        Button endUser_button = (Button) findViewById(R.id.userButton);
        Button driver_button = (Button) findViewById(R.id.driverButton);
        Button help_button = (Button) findViewById(R.id.help);
        Button about_button = (Button) findViewById(R.id.about);

        //Setting up OnClick listeners
        endUser_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Defines what happens when endUser button is clicked
                //LoginActivity loginActivity = new LoginActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        driver_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Defines what happens when driver button is clicked
                DriverMapActivity driveMapActivity = new DriverMapActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, DriverMapActivity.class);
                startActivity(intent);
            }
        });

        help_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              //Defines what happens when help button is clicked
                HelpActivity helpActivity = new HelpActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        about_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            //Defines what happens when about button is clicked
                AboutActivity aboutActivity = new AboutActivity();
                Intent intent;
                intent = new Intent(StartActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }


}

