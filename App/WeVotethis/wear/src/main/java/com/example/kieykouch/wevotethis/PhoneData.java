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
        String[] data = str.split(";");
        System.out.println("Data"+ data);

        String[] data1 = data[0].split(",");
        mydata = new ArrayList<String>();
        Collections.addAll(mydata, data1);

        mydata.add(data[1]);
        mydata.add(data[2]);
        mydata.add(data[3]);
        mydata.add(data[4]);

        count = data1.length/4 + 1;
        System.out.println("Data1"+ data1);
    }
}
