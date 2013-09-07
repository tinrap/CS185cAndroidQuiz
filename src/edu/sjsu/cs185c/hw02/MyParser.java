/**
 * @author Parnit Sainion
 * @description my parser handler for Homework 2 for cs185c
 * @dueDate 2-13-2013
 */
package edu.sjsu.cs185c.hw02;

import java.util.ArrayList;
import org.xml.sax.Attributes;

/**
 * 
 * @author Parnit Sainion
 *@description MyParser sets the rules for parsing xml
 * based on the example provided in Android Recipes chapter 3.6
 */
public class MyParser extends BasicHandler
{
	private ArrayList<Question> quiz;
	private Question question;	
    private StringBuffer buffer;

    private boolean inItem = false;

    /** 
     * @return the quiz in the form of an ArrayList
     */
    public ArrayList<Question> getQuiz() 
    {
        return quiz;
    }

    //Called at the head of each new element
    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {       
    	//If this answer is the correct choice, store teh data
    	if ("true".equals(atts.getValue("value")))
           question.correctChoice = question.choices.size(); 
    	
    	//initialize a new quiz
    	if("quiz".equals(name)) {
    		{
    			quiz = new ArrayList<Question>();
    		}
    	//creates a new question object
        } else if("question".equals(name)) {
            question = new Question();
            inItem = true;
        } else if("text".equals(name) && inItem) {
            buffer = new StringBuffer();
        } else if("choice".equals(name) && inItem) {
            buffer = new StringBuffer();
        } 
    }

    //Called at the tail of each element end
    @Override
    public void endElement(String uri, String name, String qName) {
       //adds question to the quiz
    	if("question".equals(name)) {
            quiz.add(question);
            inItem = false;
            
        } else if("text".equals(name) && inItem) {
            question.text = buffer.toString();
        } else if("choice".equals(name) && inItem) {
            question.choices.add(buffer.toString());
        }
        buffer = null;
    }

    //Called with character data inside elements
    @Override
    public void characters(char ch[], int start, int length) {
        //Don't bother if buffer isn't initialized
        if(buffer != null) {
            for (int i=start; i<start+length; i++) {
                buffer.append(ch[i]);
            }
        }
    }
}
