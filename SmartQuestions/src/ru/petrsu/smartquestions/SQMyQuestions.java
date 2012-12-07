package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SQMyQuestions extends Activity {
	
	private User user;
	
	private SQDBAdapter db;
	
	private Question[] questions;
	
	private ListView layout;
	
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_questions);
		
		db = new SQDBAdapter (this);
		db.open();
		
		getUser();
		getQuestions();
		
		initLayout ();
	}
	
	private void getUser() {
		prefs = getSharedPreferences (getString (R.string.file_preferences), MODE_PRIVATE);
		
		String log = prefs.getString(getString(R.string.preferences_login_key), "");
		
		user = db.getUserByNickname(log);
	}
	
	private void getQuestions () {
		questions = db.getAllQuestions(user);
		Log.d("myq", questions.length + "");
		
		for (int i = 0;i < questions.length;i++) {
			Log.d ("myq", questions[i].toString());
		}
	}
	
	private void initLayout () {
		layout = (ListView)findViewById (R.id.ListViewQuestions);
		
		ArrayAdapter<Question> adapter = new ArrayAdapter <Question> (this, R.layout.linearlayout_buttonitem, 
																	  questions);
		layout.setAdapter(adapter);
		
		layout.setOnItemClickListener(new OnItemClickListener () {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Editor e = prefs.edit();
				e.putInt(getString (R.string.preferences_question_id_key), questions[position].getID());
				e.commit();
				
				//Start question browser activity
				//TODO
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_questions, menu);
		return true;
	}

}
