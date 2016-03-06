package com.example.kieykouch.wevotethis;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by kieykouch on 3/4/16.
 */
public class swap2 extends PagerAdapter {

    private int[] resource = new int[]{0, 1, 2,3};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private Button k;

    public swap2(Context x){
        this.ctx = x;


    }


    public int getCount(){
        return resource.length;
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

        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(v.getContext(), WatchToPhoneService.class);
                sendIntent.putExtra("CAT_NAME", Integer.toString(position));
                System.out.println("0000000000000000");
                v.getContext().startService(sendIntent);
            }
        });

        if (position == 0){
            status.setText("Senator");
            name.setText("Barbara Boxer");
            party.setText("Democat");
        }

        else if (position == 1){
            status.setText("Senator");
            name.setText("Dianne Feinstein");
            party.setText("Republican");
        }

        else if (position == 2){
            status.setText("Reprentative");
            name.setText("Brad Sherman");
            party.setText("Democat");
        }
        else{
            status.setText("Election 2012");
            name.setText("Barak Obama 90%");
            party.setText("Mitt Romney 10%");
            k.setVisibility(View.GONE);
        }


//        k.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(v.getContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("CAT_NAME", Integer.toString(position));
//                v.getContext().startService(sendIntent);
//            }
//        });





        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }


}
