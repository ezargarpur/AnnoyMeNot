package com.annoymenot.logic;

/**
 * Created by Mattin on 12/28/2014.
 * This class stores necessary information about incoming calls, texts, etc. Used to deliver that
 * information to the Filter class.
 */
public class Message
{
    private String phoneNumber;
    private FilterType type; //Likely unnecessary. Needs discussion.

    public Message(String pN, FilterType t)
    {
        phoneNumber = pN;
        type = t;
    }

    public void setPhoneNumber(String pN)
    {
        phoneNumber = pN;
    }
    public void setType(FilterType t)
    {
        type = t;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public FilterType setType()
    {
        return type;
    }
}
