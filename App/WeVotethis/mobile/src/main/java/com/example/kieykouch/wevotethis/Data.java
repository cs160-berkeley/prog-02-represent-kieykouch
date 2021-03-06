package com.example.kieykouch.wevotethis;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by kieykouch on 3/11/16.
 */
public class Data {
    private static Data mInstance = null;
    private JSONObject Politicians_Suck = null;

    //private static List<Representative> representativeList = new ArrayList<Representative>();
    private static String ZipCode = null;
    private static Double lontitude = 0.0;
    private static Double latitude = 0.0;
    private static List<Apoliticians> list_poli = new ArrayList<Apoliticians>();

    private static JSONObject county = null;
    private static String mycounty = "";
    private static String State = "";
    private static String cityname = "";
    private static String toWatch = "";

    private static String Obama = "";
    private static String Mit = "";

    public static Data getInstance(){
        if(mInstance == null){
            mInstance = new Data();
        }
        return mInstance;
    }

    public void setLongandLati(double lat, double lon) throws ExecutionException, InterruptedException, JSONException {
        lontitude = lon;
        latitude = lat;
        Countyname County = new Countyname();
        County.setData(latitude, lontitude);
        county = null;
        try {
            county = new JSONObject(County.execute("").get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        extract(county);
    }

    private void extract(JSONObject object) throws JSONException {
        JSONArray repJsonArray = object.getJSONArray("results");
        //System.out.println(repJsonArray);
        JSONObject repObject = (JSONObject) repJsonArray.get(0);
        JSONArray addr_2 = (JSONArray) repObject.get("address_components");
        for (int k=0; k<addr_2.length(); k++) {
            JSONObject obj = addr_2.getJSONObject(k);
            if (obj.getString("types").contains("locality")){
                cityname = obj.getString("short_name");
            }
            if (obj.getString("long_name").contains("County")) {
                String temp = obj.getString("long_name");
                mycounty = temp.substring(0,temp.indexOf(" County"));
                State = addr_2.getJSONObject(k+1).getString("short_name");
                break;
            }
        }

//        System.out.println(cityname);
//        System.out.println(mycounty);
//        System.out.println(State);
    }

    public static void loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("election-county-2012.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JSONArray jarray = null;
        try {
            jarray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for(int i = 0; i < jarray.length(); i++){
                JSONObject j = (JSONObject) jarray.get(i);
                if(j.get("county-name").equals(mycounty)
                        && j.get("state-postal").equals(State)){
                    Obama = j.getString("obama-percentage");
                    Mit = j.getString("romney-percentage");
                    System.out.println("Obama"+Obama);
                    System.out.println("Mit"+Mit);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setZipCode(String zip) throws ExecutionException, InterruptedException, JSONException {
        ZipCode = zip;
        Countyname County = new Countyname();
        County.setData(zip);

        try {
            county = new JSONObject(County.execute("").get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        extract(county);

    }

    public void setJSONArray(String str) throws JSONException {
        list_poli =  new ArrayList<Apoliticians>();
        Politicians_Suck = new JSONObject(str);
        JSONArray repJsonArray = Politicians_Suck.getJSONArray("results");
        toWatch = "";
        for(int i = 0; i < repJsonArray.length(); i++){
            JSONObject repObject = (JSONObject)repJsonArray.get(i);

            final Apoliticians r = new Apoliticians();
            String name = repObject.get("first_name").toString() + " " + repObject.get("last_name").toString();
            String email = repObject.get("oc_email").toString();
            String website = repObject.get("website").toString();
            String twitterid = repObject.get("twitter_id").toString();
            String phone = repObject.get("phone").toString();
            String termend = repObject.get("term_end").toString();
            String bioguild = repObject.get("bioguide_id").toString();

            String chamber = repObject.get("chamber").toString();
            if (chamber.equals("house")){
                chamber = "Representative";
            }
            else{
                chamber = "Senator";
            }

            String party = repObject.get("party").toString();
            if (party.equals("D")){
                party = "Democrat";
            }
            else{
                party = "Republican";
            }
            //System.out.println(name+chamber+party+email+website+termend+bioguild+twitterid+phone);
            r.setGroup(name, chamber, party, email, website, termend, bioguild, twitterid, phone);
            list_poli.add(r);
            toWatch += name + ",";
            toWatch += party + ",";
            toWatch += chamber + ",";
            toWatch += i + ",";
        }
    }

    public static List<Apoliticians> getList_poli(){
        return list_poli;
    }

    public String getNamewithParty(){
     return toWatch;
    }

    public static String getMycounty(){
        return mycounty;
    }
    public static String getState(){
        return State;
    }
    public static String getCityname(){
        return cityname;
    }

    public static String getObama(){
        return Obama;
    }
    public static String getMit(){
        return Mit;
    }

}
