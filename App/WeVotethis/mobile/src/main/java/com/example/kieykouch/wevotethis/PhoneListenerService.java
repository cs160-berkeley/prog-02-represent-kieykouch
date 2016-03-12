package com.example.kieykouch.wevotethis;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.Random;

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
                int x = rand.nextInt(99999);
                String y = Integer.toString(x);

                Intent intent = new Intent(this, result.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("zip", y);
                Log.d("T", "about to start watch find_location with CAT_NAME: " + value);
                startActivity(intent);
            }

            // Make a toast with the String
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, value, duration);
//            toast.show();

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions




        } else {
            super.onMessageReceived(messageEvent);
        }

    }
}
