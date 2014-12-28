package com.annoymenot.logic;

/**
 * Created by Mattin on 12/28/2014.
 * This class stores necessary information about incoming calls, texts, etc. Used to deliver that
 * information to the Filter class.
 */
public class Message
{
    private String phoneNumber;
    private String type;

    public Message(String pN, String t)
    {
        phoneNumber = pN;
        type = t;
    }

    public void setPhoneNumber(String pN)
    {
        phoneNumber = pN;
    }
    public void setType(String t)
    {
        type = t;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String setType()
    {
        return type;
    }
}
