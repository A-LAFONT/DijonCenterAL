package com.example.antoinelafont.dijoncenteral.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Antoine LAFONT on 25/10/2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the object of SmsManager
        SmsManager sms = SmsManager.getDefault();
        Bundle bundle = intent.getExtras();
        SmsMessage [] messages = null;

        try {

            if (bundle != null) {

                final Object[] pdus = (Object[]) bundle.get("pdus");

                messages = new SmsMessage[pdus.length];

                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    }
                    else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }

                    String message = messages[i].getDisplayMessageBody();

                    if(message.startsWith("POI:")); {
                        String tmp_msg = message.replaceAll("POI:", "");
                        try {
                            int tmp_id = Integer.parseInt(tmp_msg);
                            Toast.makeText(context, "" + tmp_id, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("Parsing SMS Error", "Exception parsing SMS " + e);
                        }
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }
}
