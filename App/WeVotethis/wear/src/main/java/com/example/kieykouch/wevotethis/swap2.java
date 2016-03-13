package com.example.kieykouch.wevotethis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kieykouch on 3/4/16.
 */
public class swap2 extends PagerAdapter {

    private Context ctx;
    private LayoutInflater layoutInflater;
    private Button k;

    public swap2(Context x){
        this.ctx = x;
    }


    public int getCount(){
        return PhoneData.count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swap_template, container,false);

        TextView status = (TextView) item_view.findViewById(R.id.textView2);
        TextView name = (TextView) item_view.findViewById(R.id.Name);
        TextView party = (TextView) item_view.findViewById(R.id.textView);
        k = (Button) item_view.findViewById(R.id.button);
        //ImageView flag = (ImageView) item_view.findViewById(R.id.flag);


        ArrayList<String> currentvalue = PhoneData.mydata;

        int newindex = position*4;

        name.setText(currentvalue.get(newindex));
        party.setText(currentvalue.get(newindex+1));
        status.setText(currentvalue.get(newindex + 2));

        final String currentpositioninarray = currentvalue.get(newindex + 3);

        if (currentvalue.get(newindex + 1).equals("Republican")){
            party.setTextColor(Color.parseColor("#0025f6"));
            name.setTextColor(Color.parseColor("#0025f6"));
            status.setTextColor(Color.parseColor("#0025f6"));
            //flag.setImageResource(R.drawable.repppp);
        }else{
            party.setTextColor(Color.parseColor("#ff0000"));
            name.setTextColor(Color.parseColor("#ff0000"));
            status.setTextColor(Color.parseColor("#ff0000"));
            //flag.setImageResource(R.drawable.dooom);
        }

        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(v.getContext(), WatchToPhoneService.class);
                sendIntent.putExtra("CAT_NAME", currentpositioninarray);
                System.out.println("0000000000000000");
                v.getContext().startService(sendIntent);
            }
        });

//        else{
//            status.setText("Election 2012");
//            name.setText("Barak Obama 90%");
//            party.setText("Mitt Romney 10%");
//            k.setVisibility(View.GONE);
//
//            TextView helloworld = (TextView) item_view.findViewById(R.id.textView3);
//            helloworld.setText("LA County");
//        }

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }


}
