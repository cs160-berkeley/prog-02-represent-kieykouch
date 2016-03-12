package com.example.kieykouch.wevotethis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kieykouch on 3/11/16.
 */
public class result extends AppCompatActivity {
    private  Data dc;
    private ArrayList<Apoliticians> all;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView locationx = (TextView) findViewById(R.id.current_location_city);

        Bundle extras = getIntent().getExtras();
        String pass = "";
        if (extras != null){
            pass = (String) extras.get("zip");
            if (!pass.equals("")){
                locationx.setText("Zipcode: "+pass);
            }
        }

        populateViewList();
    }



    private void populateViewList() {
        ArrayAdapter<Apoliticians> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Apoliticians> {

        public MyListAdapter() {
            super(result.this, R.layout.info, Data.getList_poli());
        }


        public View getView(int position, final View convertView, final ViewGroup parent){
            View itemView = convertView;

            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.info, parent, false);
            }

            Data dc = Data.getInstance();
            Apoliticians currentInfo = dc.getList_poli().get(position);


            ImageButton Email1 = (ImageButton) itemView.findViewById(R.id.Email_1);
            ImageButton website1 = (ImageButton) itemView.findViewById(R.id.web_1);
            ImageButton more1 = (ImageButton) itemView.findViewById(R.id.more_1);

            TextView name = (TextView) itemView.findViewById(R.id.textView23);
            TextView party = (TextView) itemView.findViewById(R.id.textView24);
            TextView twitt = (TextView) itemView.findViewById(R.id.textView25);

            name.setText(currentInfo.getName());
            party.setText(currentInfo.getParty());
            twitt.setText("");


            System.out.println("33333");

            return itemView;
        }
    }

}
