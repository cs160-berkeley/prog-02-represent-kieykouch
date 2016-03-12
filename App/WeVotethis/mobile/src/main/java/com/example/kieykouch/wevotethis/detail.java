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
    private TextView Term_end;
    private Data dc;

    private TextView Committee;
    private TextView Bills;

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
        Term_end = (TextView) findViewById(R.id.term);

        dc = Data.getInstance();
        final Apoliticians currentInfo = dc.getList_poli().get(Integer.parseInt(pass));


        photo.setImageResource(R.drawable.barbara_boxer);
        name_detail.setText(currentInfo.getName());
        party_detail.setText(currentInfo.getParty());
        status_detail.setText(currentInfo.getStatus());
        email_detial.setText(currentInfo.getEmail());
        Term_end.setText("Term_end: "+ currentInfo.getTermEnd());
        Committee = (TextView) findViewById(R.id.textView19);
        Bills = (TextView) findViewById(R.id.textView13);

        if (currentInfo.getParty().equals("Republican")){
            name_detail.setTextColor(Color.parseColor("#0025f6"));
            party_detail.setTextColor(Color.parseColor("#0025f6"));
            Committee.setTextColor(Color.parseColor("#0025f6"));
            Bills.setTextColor(Color.parseColor("#0025f6"));
        }else{
            name_detail.setTextColor(Color.parseColor("#ff0000"));
            party_detail.setTextColor(Color.parseColor("#ff0000"));
            Committee.setTextColor(Color.parseColor("#ff0000"));
            Bills.setTextColor(Color.parseColor("#ff0000"));
        }

        String mycommiteebaby = "";
        for (String acommitee: currentInfo.getActiveComittee()){
            mycommiteebaby += acommitee + "\n";
        }
        Committee.setText(mycommiteebaby);


        String mybillsbaby = "";
        for (String bill: currentInfo.getRecentBills()){
            mybillsbaby += "-" + bill + "\n";
        }
        Bills.setText(mybillsbaby);
    }
}
