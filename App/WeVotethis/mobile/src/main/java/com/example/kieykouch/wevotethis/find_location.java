package com.example.kieykouch.wevotethis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by kieykouch on 3/4/16.
 */
public class find_location extends AppCompatActivity {

    private TextView zipcode;

    private ImageButton Email1;
    private ImageButton Email2;
    private ImageButton Email3;

    private ImageButton website1;
    private ImageButton website2;
    private ImageButton website3;

    private ImageButton more1;
    private ImageButton more2;
    private ImageButton more3;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_location_result);

        Bundle extras = getIntent().getExtras();
        String pass = "";
        if (extras != null){
            pass = (String) extras.get("zip");
        }

        zipcode = (TextView) findViewById(R.id.current_location_city);
        zipcode.setText("Zipcode:"+pass);


        Email1 = (ImageButton) findViewById(R.id.Email_1);
        Email2 = (ImageButton) findViewById(R.id.Email_2);
        Email3 = (ImageButton) findViewById(R.id.Email_3);

        website1 = (ImageButton) findViewById(R.id.web_1);
        website2 = (ImageButton) findViewById(R.id.web_2);
        website3 = (ImageButton) findViewById(R.id.Web_3);

        more1 = (ImageButton) findViewById(R.id.more_1);
        more2 = (ImageButton) findViewById(R.id.more_2);
        more3 = (ImageButton) findViewById(R.id.more_3);


        Email1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.barbaraboxer.com/contact"));
                startActivity(intent);
            }
        });

        Email2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.feinstein.senate.gov/public/index.cfm/e-mail-me"));
                startActivity(intent);
            }
        });

        Email3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://sherman.house.gov/contact/"));
                startActivity(intent);
            }
        });


        website1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.barbaraboxer.com/"));
                startActivity(intent);
            }
        });

        website2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.feinstein.senate.gov/public/"));
                startActivity(intent);
            }
        });

        website3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://sherman.house.gov/"));
                startActivity(intent);
            }
        });

        more1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent current1 = new Intent(find_location.this, detail.class);
                current1.putExtra("Who", "0");
                startActivity(current1);
            }
        });

        more2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent current1 = new Intent(find_location.this, detail.class);
                current1.putExtra("Who", "1");
                startActivity(current1);
            }
        });

        more3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent current1 = new Intent(find_location.this, detail.class);
                current1.putExtra("Who", "2");
                startActivity(current1);
            }
        });


    }
}