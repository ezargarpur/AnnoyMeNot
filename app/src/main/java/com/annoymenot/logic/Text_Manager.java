package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ezargarpur on 12/28/2014.
 */

/**
 * Modified by Aman on 12/29/2014
 */
public class Text_Manager extends BroadcastReceiver{
    private Filter filter;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    public Text_Manager ()
    {
        this.filter = Filter.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String MSG_TYPE=intent.getAction();
        if(MSG_TYPE.equals("android.provider.Telephony.SMS_RECEIVED"))
        {
//          Toast toast = Toast.makeText(context,"SMS Received: "+MSG_TYPE , Toast.LENGTH_LONG);
//          toast.show();

            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];
            for (int n = 0; n < messages.length; n++)
            {
                smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }

            // show first message
            Toast toast = Toast.makeText(context,"Phone numberBLOCKED Received SMS: " + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
            toast.show();
            abortBroadcast();
            for(int i=0;i<8;i++)
            {
                System.out.println("Blocking SMS **********************");
            }

        }
        else if(MSG_TYPE.equals("android.provider.Telephony.SEND_SMS"))
        {
            Toast toast = Toast.makeText(context,"SMS SENT: "+MSG_TYPE , Toast.LENGTH_LONG);
            toast.show();
            abortBroadcast();
            for(int i=0;i<8;i++)
            {
                System.out.println("Blocking SMS **********************");
            }

        }
        else
        {

            Toast toast = Toast.makeText(context,"SIN ELSE: "+MSG_TYPE , Toast.LENGTH_LONG);
            toast.show();
            abortBroadcast();
            for(int i=0;i<8;i++)
            {
                System.out.println("Blocking SMS **********************");
            }

        }

        /*if (SMS_RECEIVED.equals(intent.getAction())) {

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
        }*/
        String phoneNumber = null;
        FilterType type = FilterType.TEXT;

        Message filterMessage = new Message(phoneNumber, type);
    }
}
