package com.annoymenot.logic;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Call_Manager extends PhoneStateListener
{
    private final String managerType = "CALL";

    @Override
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
    }
}
