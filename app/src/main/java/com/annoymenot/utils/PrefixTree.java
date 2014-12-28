package com.annoymenot.utils;

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

        PrefixTreeNode()
        {
            children = new PrefixTreeNode[10];
        }
    }

    private PrefixTreeNode root;

    public PrefixTree()
    {
        root = new PrefixTreeNode();
    }

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

    public void addNumber(String phoneNumber)
    {
        char[] charArray = phoneNumber.toCharArray();
        LinkedList<Character> charList = new LinkedList<Character>();

        for(int i = 0; i < charArray.length; i++)
        {
            charList.add(charArray[i]);
        }

        addNum(root, charList);
    }

    private void addNum(PrefixTreeNode node, LinkedList<Character> characters)
    {
        //Check if the list of characters has been exhausted, exit if so
        if(characters.size() == 0)
        {
            return;
        }
        //Check if the current number already has a child
        int index = Character.getNumericValue((char) characters.getFirst());
        if(node.children[index] != null)
        {
            characters.removeFirst();
            addNum(node.children[index], characters);
            return;
        }
        //Reaching this point means the current number does not have a child

        //Create the child and continue moving down
        PrefixTreeNode child = new PrefixTreeNode();
        node.children[index] = child;
        addNum(child, characters);
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

            for(int i = charList.size() - 1; i >=0; i--)
            {

            }
        }
    }
}
