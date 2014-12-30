package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Text_Manager extends BroadcastReceiver
{
    private static final FilterType managerType = FilterType.TEXT;
    private Filter tmFilter;

    public Text_Manager ()
    {
        this.tmFilter = Filter.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage smsSender = SmsMessage.createFromPdu((byte[])pdus[0]);
        String sender = smsSender.getOriginatingAddress();
        Message textMessage = new Message(sender, managerType);
        Log.d("Sender", sender); //debug

        if(tmFilter.isBlackListed(textMessage))
        {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int prevAudioState = audioManager.getRingerMode();

            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

            new Timer().schedule(new MyTimerTask(audioManager, prevAudioState), 5000);
        }
    }
}

class MyTimerTask extends TimerTask
{

    private final AudioManager audioManager;
    private final int prevAudioState;


    MyTimerTask ( AudioManager audioManager, int prevAudioState )
    {
        this.audioManager = audioManager;
        this.prevAudioState = prevAudioState;
    }

    public void run()
    {
        audioManager.setRingerMode(prevAudioState);
    }
}
