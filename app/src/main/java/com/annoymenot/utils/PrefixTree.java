package com.annoymenot.utils;

import com.annoymenot.logic.Contact;
import com.annoymenot.logic.Contact_Group;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Aman on 12/28/2014.
 */
public class PrefixTree
{
    //Implementation of the node for the tree
    public static class PrefixTreeNode
    {
        PrefixTreeNode[] children;
        ArrayList<Contact_Group> groups;

        PrefixTreeNode()
        {
            children = new PrefixTreeNode[10];
            groups = new ArrayList<Contact_Group>();
        }

        public PrefixTreeNode addChild(int digit){
            if(children[digit] == null){
                children[digit] = new PrefixTreeNode();
            }

            return children[digit];
        }
        public PrefixTreeNode getChild(int digit){
            return children[digit];
        }
        public void addGroup(Contact_Group group){
            groups.add(group);
        }
        public ArrayList<Contact_Group> getGroups(){
            return groups;
        }
    }

    private PrefixTreeNode root;

    public PrefixTree()
    {
        root = new PrefixTreeNode();
    }

    /*
    public boolean contains(String phoneNumber){
        char[] characters = phoneNumber.toCharArray();

        PrefixTreeNode node = root;
        for(char number : characters){
            int digit = Character.getNumericValue(number);

            PrefixTreeNode child = node.getChild(digit);

            if(child == null){
                return false;
            }
            node = child;
        }
        return true;
    }
    */

    public boolean contains(String phoneNumber)
    {
        char[] charArray = phoneNumber.toCharArray();
        LinkedList<Character> charList = new LinkedList<Character>();

        for(int i = 0; i < charArray.length; i++)
        {
            charList.add(charArray[i]);
        }

        return checkContains(root, charList);
    }


    private boolean checkContains(PrefixTreeNode node, LinkedList<Character> characters)
    {
        //Check if the list of characters has been exhausted
        if(characters.size() == 0)
        {
            return true;
        }
        //Check if the current number exists in the tree, return true and continue if so
        int index = Character.getNumericValue((char) characters.getFirst());
        if(node.children[index] != null)
        {
            characters.removeFirst();
            return checkContains(node.children[index], characters);
        }
        //The current number does not exist in the tree, so return false
        return false;
    }
    public void addGroup(Contact_Group group){
        for(Contact contact : group){
            addNumber(contact.getNumber(), group);
        }
    }

    /**
     * Gets the Contact Groups where the number exists
     * @param phoneNumber
     * @return the groups where the number is located or an empty list if the number isn't within the tree
     */
    public ArrayList<Contact_Group> getGroups(String phoneNumber){
        ArrayList<Contact_Group> groups = new ArrayList<Contact_Group>();
        char[] characters = phoneNumber.toCharArray();

        PrefixTreeNode node = root;
        for(char number : characters){
            int digit = Character.getNumericValue(number);

            PrefixTreeNode child = node.getChild(digit);

            if(child == null){
                //Invalid number
                return new ArrayList<Contact_Group>();
            }
            node = child;
        }
        return node.getGroups();
    }
    private void addNumber(String phoneNumber, Contact_Group group)
    {
        char[] characters = phoneNumber.toCharArray();

        PrefixTreeNode node = root;
        for(char number : characters){
            int digit = Character.getNumericValue(number);

            PrefixTreeNode child = node.addChild(digit);
            node = child;
        }

        node.addGroup(group);
    }

    private void getSequence(PrefixTreeNode node, LinkedList<Character> characters, LinkedList<PrefixTreeNode> sequence)
    {
        //Check if the list of characters has been exhausted
        if(characters.size() == 0)
        {
            return;
        }
        //Add the current node to the sequence and move to the next node
        int index = Character.getNumericValue((char) characters.getFirst());
        if(node.children[index] != null)
        {
            sequence.add(node);
            characters.removeFirst();
            getSequence(node.children[index], characters, sequence);
        }
    }

    private boolean hasOtherChildren(PrefixTreeNode n, int index)
    {
        for(int i = 0; i < n.children.length; i++)
        {
            if(i == index)
            {
                continue;
            }
            else if(n.children[i] != null)
            {
                return true;
            }
        }
        return false;
    }

    public void removeNumber(String phoneNumber)
    {
        if(this.contains(phoneNumber))
        {
            LinkedList<Character> charList = new LinkedList<Character>();
            char[] charArray = phoneNumber.toCharArray();

            for(int i = 0; i < charArray.length; i++)
            {
                charList.add(charArray[i]);
            }

            LinkedList<PrefixTreeNode> sequence = new LinkedList<PrefixTreeNode>();
            getSequence(root, charList, sequence);

            sequence.removeLast();

            int index = Character.getNumericValue(charArray[charArray.length-1]);
            sequence.getLast().children[index] = null;
            boolean hasChildren = hasOtherChildren(sequence.getLast(), index);

            for(int i = charArray.length - 2; i >=0; i--)
            {
                sequence.removeLast();
                if(hasChildren)
                {
                    break;
                }
                index = Character.getNumericValue(charArray[i]);
                sequence.getLast().children[index] = null;
                hasChildren = hasOtherChildren(sequence.getLast(), index);
            }
        }
    }
}
