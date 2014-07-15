/**
 * @author Parnit Sainion
 * @description AnswerActiviy for Homework 2 for cs185c
 * @dueDate 2-13-2013
 */
package edu.sjsu.cs185c.hw02;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author Parnit Sainion
 * @description The AnswerActivity displays a question and choices and allows the user to choose and answer
 */
public class AnswerActivity extends Activity 
{
	private ListView myListView;
    private static ArrayAdapter<String> myAdapter;
    private Question selectedQuestion;
    private int correctAnswer;
    private TextView questionText;
        
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_view);
		
		//retrieves the questions
		Intent intent = getIntent();
		selectedQuestion = (Question) intent.getSerializableExtra("question");
		final int selectedPosition = intent.getIntExtra( "selectedPosition",101);
		correctAnswer = selectedQuestion.getCorrectChoice();
		
		//gets the listview
		myListView = (ListView) findViewById(R.id.answer_list);
	    myAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, android.R.id.text1);
        
	    //Sets the question textview
	    questionText = (TextView) findViewById(R.id.questionText);
	    questionText.setText(selectedQuestion.getQuestion());
	    
	    //sets the answers choices list and adds them to the adapter
	    List<String> choices= selectedQuestion.getChoices()	;    
	    int size =choices.size();
	    for(int i=0;i<size;i++)
	    {
	    	myAdapter.add(choices.get(i).toString());
	    }
        myListView.setAdapter(myAdapter);;
        
        //OnClickListener is set for the list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {           
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// intent returns the chosen answer to the callign activity
				Intent intent=new Intent();	
				if(position==correctAnswer)
					selectedQuestion.setAnswered(1);
				else
					selectedQuestion.setAnswered(2);
				
				if(position==correctAnswer)
					intent.putExtra("chosenAnswer", 1);
				else
					intent.putExtra("chosenAnswer", 2);
			    intent.putExtra("position", selectedPosition);
			    setResult(RESULT_OK, intent);
			    finish();
			}
        });

	}
}
