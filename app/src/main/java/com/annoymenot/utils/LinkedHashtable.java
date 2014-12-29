package com.annoymenot.utils;


import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Created by ezargarpur on 12/29/2014.
 */
public class LinkedHashtable<K, V> implements Iterable<V>{
    private Hashtable<K, Node<K, V>> nodes;
    private Node<K, V> prologue; //dummy value to have a constant head
    private Node<K, V> head, tail;

    /**
     *
     */
    public LinkedHashtable(){
        nodes = new Hashtable<K, Node<K, V>>();
        prologue = new Node<K, V>(null, null, null);
    }
    public void add(K key, V value){
        Node<K, V> node;
        if(nodes.containsKey(key)){
            node = nodes.get(key);
            node.setValue(value);
        } else {
            node = new Node<K, V>(key, value, this);
            nodes.put(key, node);
        }
        if(!prologue.hasNext()){

        }
        if(head == null){
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }
    public V get(K key){
        return nodes.get(key).getValue();
    }
    public boolean containsKey(K key){
        return nodes.containsKey(key);
    }
    public boolean remove(K key){
        Node<K, V> node = nodes.get(key);
        head = node.remove();

        return (nodes.remove(key) != null);
    }

    @Override
    public Iterator<V> iterator() {
        Iterator<V> iterator = new Iterator<V>() {
            Node<K, V> next = head;
            @Override
            public boolean hasNext() {
                return (next != null);
            }

            @Override
            public V next() {
                V value = next.getValue();
                next = next.getNext();

                return value;
            }

            @Override
            public void remove() {
                Node<K, V> prev = (next != null) ? next.getPrev() : null;

                if(prev != null){
                    getThis().remove(prev.getKey());
                }
            }
        };

        return iterator;
    }

    /**
     * Work around for getting LinkedHashtable instance in the iterator
     * @return this
     */
    private LinkedHashtable<K, V> getThis(){
        return this;
    }
}
class Node<K, V> {
    Node<K, V> next, prev;
    LinkedHashtable<K, V> hashtable;
    K key;
    V value;

    public Node(K key, V value, LinkedHashtable<K, V> hashtable){
        setValue(value);
        setKey(key);
        this.hashtable = hashtable;
    }
    public Node<K, V> remove(){
        Node<K, V> node = null;

        if(next != null){
            next.setPrev(prev);
            node = next;
        }
        if(prev != null){
            prev.setNext(next);
            node = prev;
        }

        return node;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node node){
        next = node;
    }
    public boolean hasNext(){
        return (next.getNext() != null);
    }
    public Node getPrev(){
        return prev;
    }
    public void setPrev(Node node){
        prev = node;
    }
    public boolean hasPrev(){
        return (prev.getPrev() != null);
    }
    public K getKey(){
        return key;
    }
    public void setKey(K key){
        this.key = key;
    }
    public V getValue(){
        return value;
    }
    public void setValue(V value){
        this.value = value;
    }
}
