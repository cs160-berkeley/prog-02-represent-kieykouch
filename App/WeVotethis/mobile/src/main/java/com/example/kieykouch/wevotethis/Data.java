package com.example.kieykouch.wevotethis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        County.setData(latitude,lontitude);

//        try {
//            county = new JSONObject(County.execute("").get());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }

    private void extract(JSONObject object) throws JSONException {
        JSONArray repJsonArray = object.getJSONArray("results");

    }

    public void setZipCode(String zip) throws ExecutionException, InterruptedException, JSONException {
        ZipCode = zip;
        Countyname County = new Countyname();
        County.setData(zip);

//        try {
//            county = new JSONObject(County.execute("").get());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }

    public void setJSONArray(String str) throws JSONException {
        list_poli =  new ArrayList<Apoliticians>();
        Politicians_Suck = new JSONObject(str);
        JSONArray repJsonArray = Politicians_Suck.getJSONArray("results");
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
        }
    }

    public static List<Apoliticians> getList_poli(){
        return list_poli;
    }

}
