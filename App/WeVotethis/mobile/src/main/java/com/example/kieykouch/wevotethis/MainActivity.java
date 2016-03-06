package com.example.kieykouch.wevotethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText count;
    private Button Find;
    private Button current;
    private String default_zip = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Find = (Button) findViewById(R.id.find);
        current = (Button) findViewById(R.id.current);
        count = (EditText) findViewById(R.id.gather);

        current.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent current1 = new Intent(MainActivity.this, current_location.class);
                current1.putExtra("zip", "91234");
                startActivity(current1);

                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("CAT_NAME", "Current");
                startService(sendIntent);
            }
        });


        Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String myinput = "";
                myinput = count.getText().toString();

                if (myinput.length() > 0) {
                    Intent current1 = new Intent(MainActivity.this, find_location.class);
                    System.out.println(myinput);
                    current1.putExtra("zip", myinput);
                    startActivity(current1);


                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendIntent.putExtra("CAT_NAME", myinput);
                    startService(sendIntent);
                }
            }
        });


    }
}
