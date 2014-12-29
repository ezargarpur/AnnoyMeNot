package com.annoymenot.utils;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ezargarpur on 12/29/2014.
 */
public class TimeInterval {

    private GregorianCalendar start, end;
    private static final Locale locale = Locale.US;
    //private boolean repeat; //TODO (eg. 5:00 - 7:00 mon, tues, weds)

    public TimeInterval(){
        start = new GregorianCalendar(locale);
        end = new GregorianCalendar(locale);
    }

    /**
     * Checks to see if the current time is within the specified start and end time
     * @return
     */
    public boolean isWithinInterval(){
        GregorianCalendar now = new GregorianCalendar(locale);

        return now.after(start) && now.before(end);
    }

    public void setStart(GregorianCalendar start){
        this.start = start;

    }
    public void setEnd(GregorianCalendar end){
        this.end = end;
    }
}
