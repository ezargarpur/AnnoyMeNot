package com.annoymenot.logic;

import com.annoymenot.utils.PrefixTree;

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
                return callBlacklist.contains(number);
            case TEXT:
                return textBlacklist.contains(number);
            default:
                return false;
        }
    }

    //Deprecated
    public boolean addNumber(String number){
        Contact_Group group = new Contact_Group();
        group.addContact(new Contact(number));

        callBlacklist.addGroup(group);
        textBlacklist.addGroup(group);
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
