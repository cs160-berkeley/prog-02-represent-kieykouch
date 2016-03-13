package com.example.kieykouch.wevotethis;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kieykouch on 3/12/16.
 */
public class PhoneData {
    public static ArrayList<String> mydata;
    public static int count = 0;
    public static void SetupData(String str){
        String[] data = str.split(",");
        mydata = new ArrayList<String>();
        Collections.addAll(mydata, data);
        count = data.length/4;
    }
}
