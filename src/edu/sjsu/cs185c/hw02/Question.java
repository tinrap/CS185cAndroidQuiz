/*
 * @description Question Class provided Professor Horstmann for Homework 2 for cs185c
 * @dueDate 2-13-2013
 */
package edu.sjsu.cs185c.hw02;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Question object has a question with 4 possible answers
 * @author Parnit
 *
 */
public class Question implements Serializable
{
    /**
	 * Declare my variables
	 */
	private static final long serialVersionUID = 1L;	
	private String text;
	private List<String> choices = new ArrayList<String>();
	private int correctChoice;
	private int answered;
   
    public Question()
    {
    	//sets status of answered to unanswered
    	answered = 0;
    }
    
    
    /**
     * Set question's question text
     * @param text the question
     */
    public void setQuestion(String text)
    {
    	this.text = text;
    }
    
    /**
     * @return question text for this object
     */
    public String getQuestion()
    {
    	return text;
    }
    
    /**
     * Adds a possible answer to the list of choices
     * @param choice possible answer to the question
     */
    public void addChoice(String choice)
    {
    	choices.add(choice);
    }
    
    /**
     * @param position position of choice required
     * @return the answer choice for the desired position
     */
    public String getChoice(int position)
    {
    	return choices.get(position);
    }
    
    /**
     * @return number of choices possible for this question
     */
    public int numberOfChoices()
    {
    	return choices.size();
    }
    /**
     * @return the list of choices for this question object
     */
    public List<String> getChoices()
    {
    	return choices;
    }
    
    /**
     * @return the position of the correct answer to this question
     */
    public int getCorrectChoice()
    {
    	return correctChoice;
    }
    
    /**
     * sets the correct answers position
     * @param correctChoice position of the correct answer
     */
    public void setCorrectChoice(int correctChoice)
    {
    	this.correctChoice = correctChoice;
    }
   
    /**
     * sets whether the question has been answered. 
     * 0 = unanswered, 1 = correct, 2 = incorrect
     * @param num number indicating if the question has has been answered
     */
    public void setAnswered(int num)
    {
    	
    	answered = num;
    }
    
    /**
     * @return status of question
     */
    public int getAnswered()
    {
    	return answered;
    }
    
    public String toString()
    {
    	return text;
    }

}
