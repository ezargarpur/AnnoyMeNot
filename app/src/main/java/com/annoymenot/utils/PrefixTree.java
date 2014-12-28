package com.annoymenot.utils;

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
        char name;
        ArrayList<PrefixTreeNode> children;
    }

    private PrefixTreeNode root;

    public PrefixTree()
    {
        root = new PrefixTreeNode();
        root.name = 'r';
    }

    public boolean contains(String phoneNumber)
    {
        char[] charArray = phoneNumber.toCharArray();
        LinkedList charList = new LinkedList();

        for(int i = 0; i < charArray.length; i++)
        {
            charList.add(charArray[i]);
        }

        return checkContains(root, charList);
    }

    private boolean checkContains(PrefixTreeNode node, LinkedList characters)
    {
        //Check if the list of characters has been exhausted
        if(characters.size() == 0)
        {
            return true;
        }
        //Check if the current number exists in the tree, return true and continue if so
        for(PrefixTreeNode n : node.children)
        {
            if(n.name == characters.getFirst())
            {
                characters.removeFirst();
                return checkContains(n, characters);
            }
        }
        //The current number does not exist in the tree, so return false
        return false;
    }

    public void addNumber(String phoneNumber)
    {
        char[] charArray = phoneNumber.toCharArray();
        LinkedList charList = new LinkedList();

        for(int i = 0; i < charArray.length; i++)
        {
            charList.add(charArray[i]);
        }

        addNum(root, charList);
    }

    private void addNum(PrefixTreeNode node, LinkedList characters)
    {
        //Check if the list of characters has been exhausted, exit if so
        if(characters.size() == 0)
        {
            return;
        }
        //Check if the current number already has a child
        for(PrefixTreeNode n : node.children)
        {
            if(n.name == characters.getFirst())
            {
                characters.removeFirst();
                addNum(n, characters);
                return;
            }
        }
        //Reaching this point means the current number does not have a child

        //Create the child and continue moving down
        PrefixTreeNode child = new PrefixTreeNode();
        child.name = (char) characters.removeFirst();
        node.children.add(child);
        addNum(child, characters);
    }

    public void removeNumber(String phoneNumber)
    {
        if(this.contains(phoneNumber));
    }
}
