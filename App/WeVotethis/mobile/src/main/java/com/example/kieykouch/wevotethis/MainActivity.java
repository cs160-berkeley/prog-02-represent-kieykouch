package com.example.kieykouch.wevotethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText count;
    private Button Find;
    private Button current;
    private String default_zip = "";

    private SimpleLocation location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Find = (Button) findViewById(R.id.find);
        current = (Button) findViewById(R.id.current);
        count = (EditText) findViewById(R.id.gather);

        location = new SimpleLocation(this);
        if (!location.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }

        final Data dc = Data.getInstance();

        current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final double latitude = location.getLatitude();
                final double longitude = location.getLongitude();

                try {
                    dc.setLongandLati(latitude, longitude);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Sunlight_Politicians Request_Po = new Sunlight_Politicians();
                Request_Po.setData(latitude, longitude);

                try {
                    dc.setJSONArray(Request_Po.execute("").get());
                    Intent current1 = new Intent(MainActivity.this, result_from_current.class);
                    startActivity(current1);

//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Current");
//                startService(sendIntent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String myinput = "";
                myinput = count.getText().toString();

                if (myinput.length() > 0 && getValidZIP(myinput)) {

                    try {
                        dc.setZipCode(myinput);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Sunlight_Politicians Request_Po = new Sunlight_Politicians();
                    Request_Po.setData(myinput);

                    try {
                        dc.setJSONArray(Request_Po.execute("").get());
                        Intent current1 = new Intent(MainActivity.this, result.class);
                        current1.putExtra("zip", myinput);
                        startActivity(current1);

//                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                    sendIntent.putExtra("CAT_NAME", myinput);
//                    startService(sendIntent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // make the device update its location
        location.beginUpdates();

    }

    @Override
    protected void onPause() {
        // stop location updates (saves battery)
        location.endUpdates();

        super.onPause();
    }

    private boolean getValidZIP(String zip) {
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(zip);
        return matcher.matches();
    }
}
