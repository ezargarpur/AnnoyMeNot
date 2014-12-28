package com.annoymenot.logic;

/**
 * Created by ezargarpur on 12/28/2014.
 */
public class Contact {
    private String number;
    private String name;
    //private email + other fields

    public Contact(String number){
        this(number, null);
    }
    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber(){
        return number;
    }
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Contact){
            Contact otherContact = (Contact) other;
            return number.equals(otherContact);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return number.hashCode();
    }

}
