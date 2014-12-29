package com.annoymenot.logic;

import java.util.*;

/**
 * Created by ezargarpur on 12/28/2014.
 */
public class Contact_Group implements Iterable<Contact> {
    private String groupName;
    private LinkedHashSet<Contact> contacts;
    private boolean isCallBlocking, isTextBlocking;
    //TODO Add observer/listener to notify filter of changes

    private GregorianCalendar relativeStart, relativeEnd; //TODO
    private GregorianCalendar absoluteStart, absoluteEnd;
    private boolean repeatAbsolute;

    public Contact_Group(){
        contacts = new LinkedHashSet<Contact>();
    }

    public boolean addContact(Contact contact){
        boolean wasContained = contacts.add(contact);
        return wasContained;
    }
    public boolean contains(Contact contact){
        return contacts.contains(contact);
    }
    public boolean removeContact(Contact contact){
        boolean wasContained = contacts.remove(contact);
        return wasContained;
    }
    public void setCallBlocking(boolean value){
        isCallBlocking = value;
    }
    public void setTextBlocking(boolean value){
        isTextBlocking = value;
    }
    public void setAbsoluteRepeat(boolean value){
        repeatAbsolute = value;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.iterator();
    }
}
