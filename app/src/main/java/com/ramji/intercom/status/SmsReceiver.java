package com.ramji.intercom.status;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;

import java.util.Objects;

public class SmsReceiver extends BroadcastReceiver {
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                msgs = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], bundle.getString("format"));
                    } else {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                    if (Objects.requireNonNull(msgs[i].getOriginatingAddress()).contains(PreferenceManager.getDefaultSharedPreferences(context).getString("MobileNumber", "9894008739"))) {
                        if (msgs[i].getMessageBody().startsWith("%") && msgs[i].getMessageBody().endsWith("$") && msgs[i].getMessageBody().length() > 12) {
                            PreferenceManager.getDefaultSharedPreferences(context).edit().putString("message", msgs[i].getMessageBody()).apply();
                            break;
                        }
                    }
                }
            }
        }
    }
}