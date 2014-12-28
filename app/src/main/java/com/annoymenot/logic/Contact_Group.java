package com.annoymenot.logic;

import java.util.*;

/**
 * Created by Mattin on 12/28/2014.
 */
public class Contact_Group {
    private HashSet<Contact> contacts;

    public Contact_Group(){
        contacts = new HashSet<Contact>();
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

}
