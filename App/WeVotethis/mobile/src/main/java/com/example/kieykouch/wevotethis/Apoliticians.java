package com.example.kieykouch.wevotethis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by kieykouch on 3/11/16.
 */
public class Apoliticians {
    private String name ="";
    private String pic = "";
    private String status = "";
    private String party = "";
    private String email = "";
    private String website = "";
    private String TermEnd = "";
    private String bioguide_id = "";
    private String twitterid = "";
    private String phone = "";
    private String last_tweet;
    private ArrayList<String> activeComittee = null;
    private ArrayList<String> recentBills= null;

    public void setGroup(String name, String status, String party, String email, String website, String TermEnd,
                         String bioguide_id, String twitterid, String phone){
        this.name = name;
        this.status = status;
        this.party = party;
        this.email = email;
        this.website = website;
        this.TermEnd = TermEnd;
        this.bioguide_id = bioguide_id;
        this.twitterid = twitterid;
        this.phone = phone;
    }

    public String getName(){
        return name;
    }

    public String getPic(){
        return pic;
    }
    public String getStatus(){
        return status;
    }
    public String getParty(){
        return party;
    }
    public String getEmail(){
        return email;
    }
    public String getWebsite(){
        return website;
    }
    public String getTermEnd(){
        return TermEnd;
    }
    public String getBioguide_id(){
        return bioguide_id;
    }
    public String getTwitterid(){
        return twitterid;
    }
    public String getPhone(){
        return phone;
    }

    public ArrayList<String> getActiveComittee() {
        if (activeComittee != null){
            return activeComittee;
        }

        activeComittee = new ArrayList<String>();
        Sunlight_Committee Request_Commite = new Sunlight_Committee();

        try {
            setJSONArray_Commmite(Request_Commite.execute(bioguide_id).get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return activeComittee;
    }
    public ArrayList<String> getRecentBills(){
        if (recentBills != null){
            return recentBills;
        }

        recentBills = new ArrayList<String>();

        Sunlight_Bills Request_Bills = new Sunlight_Bills();
        try {
            setJSONArray_Bills(Request_Bills.execute(bioguide_id).get());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return recentBills;
    }

    private void setJSONArray_Commmite(String str) throws JSONException {
        JSONObject AllCommitees = new JSONObject(str);
        JSONArray repJsonArray = AllCommitees.getJSONArray("results");

        for(int i = 0; i < repJsonArray.length(); i++){
            JSONObject repObject = (JSONObject)repJsonArray.get(i);
            String committeename = repObject.get("name").toString();
            //System.out.println(committeename);
            activeComittee.add(committeename);
        }
    }

    private void setJSONArray_Bills(String str) throws JSONException {
        JSONObject AllCommitees = new JSONObject(str);
        JSONArray repJsonArray = AllCommitees.getJSONArray("results");

        for(int i = 0; i < repJsonArray.length(); i++){
            JSONObject repObject = (JSONObject)repJsonArray.get(i);
            String introduced_on = repObject.get("introduced_on").toString();
            String official_title = repObject.get("official_title").toString();
            //System.out.println(introduced_on + " "+official_title);
            recentBills.add(introduced_on + " " + official_title);
        }
    }
}
