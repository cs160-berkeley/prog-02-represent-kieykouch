package com.example.kieykouch.wevotethis;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kieykouch on 3/3/16.
 */
public class detail extends AppCompatActivity {


    private ImageView photo;
    private TextView name_detail;
    private TextView party_detail;
    private TextView status_detail;
    private TextView email_detial;
    private TextView phone;
    private TextView Term_end;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_curr);

        Bundle extras = getIntent().getExtras();
        String pass = "";
        if (extras != null){
            pass = (String) extras.get("Who");
        }

        photo = (ImageView) findViewById(R.id.imageView);
        name_detail = (TextView) findViewById(R.id.name_detail);
        party_detail = (TextView) findViewById(R.id.party_detail);
        status_detail = (TextView) findViewById(R.id.status_detail);
        email_detial = (TextView) findViewById(R.id.email_detial);
        phone = (TextView) findViewById(R.id.phone);
        Term_end = (TextView) findViewById(R.id.term);


       if (pass.equals("0")){
           photo.setImageResource(R.drawable.barbara_boxer);
           name_detail.setText("Barbara Boxer");
           party_detail.setText("Democrat");
           status_detail.setText("Senator");
           email_detial.setText("Barba@yes.gov");
           phone.setText("8182112222");
           Term_end.setText("Term_end 2016");

       }
       else if (pass.equals("1")) {
           photo.setImageResource(R.drawable.dianne);
           name_detail.setText("Dianne Feinstein");
           party_detail.setText("Republican");

           name_detail.setTextColor(Color.parseColor("#0025f6"));
           party_detail.setTextColor(Color.parseColor("#0025f6"));

           status_detail.setText("Senator");
           email_detial.setText("diane@yes.gov");
           phone.setText("8182112222");
           Term_end.setText("Term_end 2017");

       }
       else if (pass.equals("2")){
           photo.setImageResource(R.drawable.brad_sherman);
           name_detail.setText("Brad Sherman");
           party_detail.setText("Democrat");
           status_detail.setText("Rep");
           email_detial.setText("diane@yes.gov");
           phone.setText("8182112222");
           Term_end.setText("Term_end 2017");

       }

    }
}
