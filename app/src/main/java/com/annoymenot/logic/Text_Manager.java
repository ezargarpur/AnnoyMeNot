package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by ezargarpur on 12/28/2014.
 */

/**
 * Modified by Aman on 12/29/2014
 */
public class Text_Manager extends BroadcastReceiver{
    private Filter filter;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    public Text_Manager(Filter filter){
        this.filter = filter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SMS_RECEIVED.equals(intent.getAction())) {

            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                abortBroadcast();
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();

                Log.d("sms", sender);
                Log.d("sms", message);
            }
        }
        String phoneNumber = null;
        FilterType type = FilterType.TEXT;

        Message filterMessage = new Message(phoneNumber, type);
    }
}
