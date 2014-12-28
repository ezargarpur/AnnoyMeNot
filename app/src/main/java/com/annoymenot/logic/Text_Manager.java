package com.annoymenot.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Text_Manager extends BroadcastReceiver{
    private Filter filter;

    public Text_Manager(Filter filter){
        this.filter = filter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String phoneNumber;
        FilterType type = FilterType.TEXT;
       // Message filterMessage = new Message();
    }
}
