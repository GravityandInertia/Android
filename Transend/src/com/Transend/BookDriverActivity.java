package com.Transend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 12/27/11
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookDriverActivity extends Activity{
    private GeoPoint driverGeoPoint;
    private OverlayItem driveroverlayitem;
    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    private View.OnClickListener bookListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(BookDriverActivity.this, ConnectingActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdriver);

        Spinner spinner = (Spinner) findViewById(R.id.destinationspinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.Places_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

         spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Button backButton = (Button) findViewById(R.id.BackButton);
        Button driverButton = (Button) findViewById(R.id.BookButton);

        backButton.setOnClickListener(backListener);
        driverButton.setOnClickListener(bookListener);
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent,
        View view, int pos, long id) {
      Toast.makeText(parent.getContext(), "The destination is " +
              parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView parent) {
      // Do nothing.
    }
}
}
