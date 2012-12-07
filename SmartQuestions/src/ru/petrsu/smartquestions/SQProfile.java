package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SQProfile extends Activity implements OnClickListener{

	private User user;
	
	private Button ButtonPlaceQuestion;
	private Button ButtonAnswer;
	private Button ButtonMyQuestions;
	private Button ButtonMyAnswers;
	private Button ButtonExit;
	
	private TextView TextViewLogin;
	private TextView TextViewRating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		Log.d ("here", "prof");
		getUser();
		Log.d ("here", "prof + " + user.getNickname());
		
		initLayout();
	}
	
	private void initLayout() {
		ButtonPlaceQuestion = (Button)findViewById (R.id.ButtonPlaceQuestion);
		ButtonAnswer = (Button)findViewById (R.id.ButtonAnswer);
		ButtonMyQuestions = (Button)findViewById (R.id.ButtonMyQuestions);
		ButtonMyAnswers = (Button)findViewById (R.id.ButtonMyAnswers);
		ButtonExit = (Button)findViewById (R.id.ButtonExit);
		
		TextViewLogin = (TextView)findViewById (R.id.TextViewNickname);
		TextViewRating = (TextView)findViewById (R.id.TextViewRating);
		
		TextViewLogin.setText(TextViewLogin.getText() + ": " + user.getNickname());
		TextViewRating.setText(TextViewRating.getText() + ": " + user.getRate());
		
		ButtonPlaceQuestion.setOnClickListener(this);
		ButtonAnswer.setOnClickListener(this);
		ButtonMyQuestions.setOnClickListener(this);
		ButtonMyAnswers.setOnClickListener(this);
		ButtonExit.setOnClickListener(this);
	}
	
	/*
	 * gets logged user
	 */
	private void getUser() {
		SharedPreferences s = getSharedPreferences (getString(R.string.file_preferences), MODE_PRIVATE);
		
		String log = s.getString(getString(R.string.preferences_login_key), "");
		Log.d ("here", "prof + log = " + log);
		
		SQDBAdapter db = new SQDBAdapter(this);
		db.open();
		
		user = db.getUser(log);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonPlaceQuestion:
			//TODO
			break;
		case R.id.ButtonAnswer:
			//TODO
			break;
		case R.id.ButtonMyAnswers:
			//TODO
			break;
		case R.id.ButtonMyQuestions:
			//TODO
			break;
		case R.id.ButtonExit:
			//TODO
			break;
		}
	}
}
