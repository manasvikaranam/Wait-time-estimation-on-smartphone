package com.example.waitnwatch.layouts;

import android.content.BroadcastReceiver;

/**
 * Created by manasvi on 3/23/15.
 */
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;


public class ProximityIntentReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1000;
    public static long startTime=0;
    public static long endTime =1;
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        Boolean entering = intent.getBooleanExtra(key, false);
        if(entering)
        {
            Log.d(getClass().getSimpleName(), "entering");
            System.out.println("Enetring");
            startTime=System.currentTimeMillis();

        }
        else
        {
        	MainActivity.uploadData(startTime, endTime);
            Log.d(getClass().getSimpleName(), "exiting");
            System.out.println("Exiting");
            endTime=System.currentTimeMillis();
        }
    }

}
