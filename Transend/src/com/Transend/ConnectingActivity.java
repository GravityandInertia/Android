package com.Transend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 12/29/11
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 */

public class ConnectingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connecting);

        final ProgressDialog dialog = ProgressDialog.show(ConnectingActivity.this, "",
                "Sending Driver Request...", true);

        final Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
                timer.purge();
            }
        };

        timer.schedule(timerTask,10000);
    }
}