/*
 * @description Question Class provided Professor Horstmann for Homework 2 for cs185c
 * @dueDate 2-13-2013
 */
package edu.sjsu.cs185c.hw02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String text;
    protected List<String> choices = new ArrayList<String>();
    protected int correctChoice;
    
    public String toString()
    {
    	return text;
    }
}
