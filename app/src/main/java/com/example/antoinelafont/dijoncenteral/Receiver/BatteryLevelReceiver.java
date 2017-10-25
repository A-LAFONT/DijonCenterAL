package com.example.antoinelafont.dijoncenteral.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * Created by Antoine LAFONT on 25/10/2017.
 */

public class BatteryLevelReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Battery is too low :^)", Toast.LENGTH_SHORT).show();
    }
}
