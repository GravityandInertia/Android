package com.Transend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.login);

        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RiderMapActivity riderMapActivity = new RiderMapActivity();
                Intent intent;
                intent = new Intent(LoginActivity.this, RiderMapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
