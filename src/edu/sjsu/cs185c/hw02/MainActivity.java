/**
 * @author Parnit Sainion
 * @description MainActiviy for Homework 2 for cs185c
 * @dueDate 2-13-2013
 */
package edu.sjsu.cs185c.hw02;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 
 * @author Parnit Sainion
 *@description Main activity create a quiz, which list questions fro the user to select and answer
 */
public class MainActivity extends Activity {

	//Variable Declared
	private final String urlLink = "http://horstmann.com/sjsu/spring2013/cs185c/hw02/quiz.xml";
	private ListView myListView;
    private static ArrayAdapter<Question> myAdapter;
    private static ArrayList<Question> myQuizList;
    private Question selectedQuestion;
    private int selectedPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//AsynTask to get quiz from URL in another thread
		new AsyncTask<String,Void, ArrayList<Question>>()
		{	
			protected ArrayList<Question> doInBackground(String... params) {				        
	        try {
	        	//Create the parser
	            SAXParserFactory factory = SAXParserFactory.newInstance();
	            SAXParser parser = factory.newSAXParser(); 
	            XMLReader reader = parser.getXMLReader();
	            
	            //set the parser to my handler
	            MyParser myParser = new MyParser();
	            reader.setContentHandler(myParser);
	            
	            //open the url link and get xml data
	            URL url = new URL(urlLink);
	            URLConnection connection = url.openConnection();
	            connection.connect();
	            reader.parse(new InputSource(url.openStream()));
	           
	            //returns auiz int eh form of  an arraylist 
	            return myParser.getQuiz();
	        } catch (Exception ex) {
	            Log.e("msg", ex.toString());
	        }       
	          return new ArrayList<Question>();
			}

			//takes the Quiz take and intilizes the listview
			protected void onPostExecute(ArrayList<Question> quiz) {		       
		       
		       MainActivity.myQuizList = quiz;
		       //clears any inital data in the adapter
		        myAdapter.clear();
		        
		        //adds the questions to the adapter
				for(Question q : myQuizList) 
				{		          
					myAdapter.add(q);				
		        }
		        myAdapter.notifyDataSetChanged();	
		        }
			}.execute(urlLink);
        
		myListView = (ListView) findViewById(R.id.question_list);
	    myAdapter = new ArrayAdapter<Question>(this,  android.R.layout.simple_list_item_1, android.R.id.text1);
        
        myListView.setAdapter(myAdapter);
        
        //Creates the click listener
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {           
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				selectedQuestion = (Question) myAdapter.getItem(position);
				selectedPosition = position;
				Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
				intent.putExtra("question", selectedQuestion);
				final int intentMarker = 0;
				startActivityForResult(intent, intentMarker);
			}
        });
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	//modified from http://mobileorchard.com/android-app-development-using-intents-to-pass-data-and-return-results-between-activities/
	//Gets the response data from finished activity
	@Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
     super.onActivityResult(requestCode, resultCode, data);

     	boolean answer= data.getBooleanExtra("chosenAnswer", false);     	
     	
     	//If the answer was correct the question's background will be green, else false
		if(answer)
     	 myListView.getChildAt(selectedPosition).setBackgroundColor(Color.GREEN);
		else
	     myListView.getChildAt(selectedPosition).setBackgroundColor(Color.RED);
		
		//removes the onClick listener
		myListView.getChildAt(selectedPosition).setOnClickListener(null);
    }

}
