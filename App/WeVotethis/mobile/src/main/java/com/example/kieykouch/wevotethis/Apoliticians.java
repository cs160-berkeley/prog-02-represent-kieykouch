package com.example.kieykouch.wevotethis;

import java.util.ArrayList;

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

    public ArrayList<String> getActiveComittee(){
        if (activeComittee != null){
            return activeComittee;
        }

        return activeComittee;
    }
    public ArrayList<String> getRecentBills(){
        if (recentBills != null){
            return recentBills;
        }
        return recentBills;
    }
}
