package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Call_Manager extends BroadcastReceiver
{
    private static final FilterType managerType = FilterType.CALL;
    private Filter cmFilter;

    public Call_Manager(Filter filter)
    {
        cmFilter = filter;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            String callNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Message callMessage = new Message(callNumber, managerType);

            if(cmFilter.isBlackListed(callMessage))
            {
                //Block Call
            }
        }
    }
}
