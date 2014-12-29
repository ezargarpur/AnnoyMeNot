package com.annoymenot.logic;

import com.annoymenot.utils.PrefixTree;

/**
 * Created by ezargarpur on 12/28/2014.
 */
public class Filter {

    private PrefixTree callBlacklist;
    private PrefixTree textBlacklist;

    public Filter(){
        callBlacklist = new PrefixTree();
        textBlacklist = new PrefixTree();
    }
    public boolean isBlackListed(Message message){
        String number = message.getPhoneNumber();

        switch (message.getType()) {
            case CALL:
                return callBlacklist.contains(number);
            case TEXT:
                return textBlacklist.contains(number);
            default:
                return false;
        }
    }
    public boolean addNumber(String number){
        callBlacklist.addNumber(number);
        textBlacklist.addNumber(number);
        //TODO
        return false;
    }
    public boolean removeNumber(String number){
        //TODO
        return false;
    }
    public boolean addGroup(Contact_Group group){
        //TODO
        return false;
    }
    public boolean removeGroup(Contact_Group group){
        //TODO
        return false;
    }
}
