package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Call_Manager extends BroadcastReceiver
{
    private static final FilterType managerType = FilterType.CALL;
    private Filter cmFilter;
    private int prevAudioState;

    public Call_Manager()
    {
        cmFilter = Filter.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if(phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            prevAudioState = audioManager.getRingerMode();
            String callNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Message callMessage = new Message(callNumber, managerType);

            if(cmFilter.isBlackListed(callMessage))
            {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Log.d("Audio Status during call", Integer.toString(prevAudioState));
            }
        }
        else if(phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE))
        {
            audioManager.setRingerMode(prevAudioState);
            Log.d("Audio Status after call", Integer.toString(prevAudioState));
        }
    }
}
