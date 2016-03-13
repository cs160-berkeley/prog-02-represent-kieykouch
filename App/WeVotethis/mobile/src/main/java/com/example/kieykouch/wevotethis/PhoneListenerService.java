package com.example.kieykouch.wevotethis;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import org.json.JSONException;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
private static final String TOAST = "/send_toast";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());

        System.out.println("Hello_There, i'm here");

        if( messageEvent.getPath() != null ) {
            System.out.println("22222222222222");
            // Value contains the String we sent over in WatchToPhoneService, "good job"
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            System.out.println(value);

            if (!value.equals("Shake")) {
                Intent intent = new Intent(this, detail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Who", value);
                Log.d("T", "about to start watch Detail with CAT_NAME: " + value);
                startActivity(intent);
            }
            else{
                Random rand = new Random();
                int x = rand.nextInt(41);
                String validzipcode[] = {"36109", "24401", "14127", "35007", "46383", "30008", "84010"
                ,"45420", "03051", "49503", "02062", "23228", "73112", "56401", "30518", "33020", "19454"
                , "38017", "01906", "48867", "44601", "60411", "60142", "02816", "03301", "11542", "13501",
                        "31525", "30741", "24060", "60010", "11756", "28092", "32714", "02368", "21740", "49417",
                        "11375", "43035", "43147", "94709", "91423"
                };

                String y = validzipcode[x];
                callingfromshake(y);

                //Intent intent = new Intent(this, result.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtra("zip", y);
                //Log.d("T", "about to start watch find_location with CAT_NAME: " + value);
                //startActivity(intent);
            }

        } else {
            super.onMessageReceived(messageEvent);
        }

    }

    private void callingfromshake(String myinput){
            Data dc = Data.getInstance();

            try {
                dc.setZipCode(myinput);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Sunlight_Politicians Request_Po = new Sunlight_Politicians();
            Request_Po.setData(myinput);

            try {
                dc.setJSONArray(Request_Po.execute("").get());
                Intent current1 = new Intent(this, result.class);
                current1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                current1.putExtra("zip", myinput);
                startActivity(current1);

                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("CAT_NAME", myinput);
                startService(sendIntent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }
}
