package com.annoymenot.logic;

import com.annoymenot.utils.TimeInterval;

import java.util.*;

/**
 * Created by ezargarpur on 12/28/2014.
 */
public class Contact_Group implements Iterable<Contact> {
    private String groupName;
    private LinkedHashSet<Contact> contacts;
    private boolean isCallBlocking, isTextBlocking;
    private int groupID;
    private static int lastID = 0;
    //TODO Add observer/listener to notify filter of changes

    private TimeInterval interval;

    public Contact_Group(){
        groupID = lastID++;
        contacts = new LinkedHashSet<Contact>();
    }

    public boolean addContact(Contact contact){
        boolean wasContained = contacts.add(contact);

        Filter.getInstance().addNumber(contact.getNumber(), this);
        return wasContained;
    }
    public boolean contains(Contact contact){
        return contacts.contains(contact);
    }
    public boolean removeContact(Contact contact){
        boolean wasContained = contacts.remove(contact);

        Filter.getInstance().remove(contact.getNumber(), groupID);
        return wasContained;
    }
    public TimeInterval getTimeInterval(){
        return interval;
    }
    public int getID(){
        return groupID;
    }
    public void setCallBlocking(boolean value){
        isCallBlocking = value;
    }
    public void setTextBlocking(boolean value){
        isTextBlocking = value;
    }
    public void setTimeInterval(TimeInterval interval){
        this.interval = interval;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.iterator();
    }
}
