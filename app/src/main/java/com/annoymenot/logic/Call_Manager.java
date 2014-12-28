package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Call_Manager extends BroadcastReceiver
{
    private static final String managerType = "CALL";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        
    }

    /*@Override
    public void onCallStateChanged(int state, String incomingNumber)
    {
        if(state == TelephonyManager.CALL_STATE_RINGING)
        {
            Message callMessage = new Message(incomingNumber, managerType);
            if(Filter.checkMessage(callMessage))
            {
                //Hangup call.
            }
        }
    }*/
}
