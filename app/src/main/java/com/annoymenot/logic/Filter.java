package com.annoymenot.logic;

import com.annoymenot.utils.PrefixTree;

/**
 * Created by ezargarpur on 12/28/2014.
 */
public class Filter {

    private PrefixTree blacklist;
    private Filter singleton;

    public Filter getFilterInstance(){
        if(singleton == null){
            singleton = new Filter();
        }
        return singleton;
    }
    private Filter(){
        blacklist = new PrefixTree();
    }
    public boolean isBlackListed(Message message){
        String number = message.getPhoneNumber();
        return blacklist.contains(number);
    }
    public boolean addNumber(String number){
        blacklist.addNumber(number);
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
