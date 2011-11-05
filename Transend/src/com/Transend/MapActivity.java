package com.Transend;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity
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

            TextView username = (TextView) findViewById(R.id.username);

            final EditText username_text = (EditText) findViewById(R.id.username_text);
            }
});


    }
}
