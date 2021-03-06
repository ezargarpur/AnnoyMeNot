package com.annoymenot.logic;

import android.util.Log;

import com.annoymenot.utils.PrefixTree;

import java.util.Iterator;

/**
 * Created by ezargarpur on 12/28/2014.
 */
public class Filter {

    private PrefixTree callBlacklist;
    private PrefixTree textBlacklist;
    private static Filter singleton;

    public static Filter getInstance(){
        if(singleton == null){
            singleton = new Filter();
        }
        return singleton;
    }
    private Filter(){
        callBlacklist = new PrefixTree();
        textBlacklist = new PrefixTree();
    }

    public boolean isBlackListed(Message message){
        String number = message.getPhoneNumber();

        switch (message.getType()) {
            case CALL:
                System.out.println(callBlacklist.getGroups(number).size());
                for(Contact_Group group : callBlacklist.getGroups(number)) {
                    if(group.getTimeInterval().isWithinInterval()){
                        Log.d("Interval", "Is within");
                        return true;
                    } else {

                    }
                }

                return false;
            case TEXT:
                for(Contact_Group group : textBlacklist.getGroups(number)){
                    if(group.getTimeInterval().isWithinInterval()){
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }


    public boolean addNumber(String number, Contact_Group group){
        callBlacklist.addNumber(number, group);
        textBlacklist.addNumber(number, group);
        //TODO
        return false;
    }
    public boolean remove(String phoneNumber, int groupID){
        //TODO

        callBlacklist.removeNumber(phoneNumber, groupID);
        textBlacklist.removeNumber(phoneNumber, groupID);
        return false;
    }
    public boolean addGroup(Contact_Group group){
        //TODO
        callBlacklist.addGroup(group);
        textBlacklist.addGroup(group);
        return false;
    }
    public boolean removeGroup(Contact_Group group){
        //TODO
        throw new UnsupportedOperationException();
    }
}
