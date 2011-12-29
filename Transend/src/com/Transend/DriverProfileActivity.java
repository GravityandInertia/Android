package com.Transend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 12/27/11
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class DriverProfileActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Initializing view  items for the driver profile
        TextView driverName = (TextView) findViewById(R.id.Driver_Name);

        //Setting up the generic driver profile picture
        ImageView driverPic = (ImageView) findViewById(R.id.DriverPicture);

        //Setting the view to the driver profile
        setContentView(R.layout.driverprofile);

        Button rateCalculator = (Button) findViewById(R.id.RateCalculatorButton);
        Button bookNow = (Button) findViewById(R.id.bookDriverButton);

        View.OnClickListener rateCalcListener = new View.OnClickListener(){
            public void onClick(View view){
                RateCalculatorActivity rateCalculatorActivity = new RateCalculatorActivity();
                Intent intent;
                intent = new Intent(DriverProfileActivity.this, RateCalculatorActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener bookDriverListener = new View.OnClickListener() {
            public void onClick(View view) {
               BookDriverActivity bookDriverActivity = new BookDriverActivity();
                Intent intent;
                intent = new Intent(DriverProfileActivity.this, BookDriverActivity.class);
                startActivity(intent);

            }
        };

        rateCalculator.setOnClickListener(rateCalcListener);
        bookNow.setOnClickListener(bookDriverListener);
    }
}


