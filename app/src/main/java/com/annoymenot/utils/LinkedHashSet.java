package com.annoymenot.utils;

import java.util.Iterator;

/**
 * Values must have a valid hashCode() and equals()
 *
 * Created by ezargarpur on 12/29/2014.
 */
public class LinkedHashSet<V> implements Iterable<V> {
    private LinkedHashtable<V, V> hashtable;

    public LinkedHashSet(){
        hashtable = new LinkedHashtable<V, V>();
    }
    public void add(V value){
        hashtable.add(value, value);
    }
    public void get(V value){
        hashtable.get(value);
    }
    public boolean contains(V value){
        return hashtable.containsKey(value);
    }
    public boolean remove(V value){
        return hashtable.remove(value);
    }

    @Override
    public Iterator<V> iterator() {
        return hashtable.iterator();
    }
}
