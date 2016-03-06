package com.example.kieykouch.wevotethis;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;

/**
 * Created by kieykouch on 3/4/16.
 */
public class swap1 extends Activity {

    ViewPager viewPager;
    swap2 adapter;
    ImageButton k;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swap1);
        viewPager = (ViewPager) findViewById(R.id.viewpager22);
        adapter = new swap2(this);
        viewPager.setAdapter(adapter);

    }
}
