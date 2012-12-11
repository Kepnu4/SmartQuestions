package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQAnswer extends Activity implements OnClickListener{
	
	private EditText EditTextAnswer;
	
	private TextView TextViewNickname;
	private TextView TextViewTitle;
	private TextView TextViewAward;
	private TextView TextViewText;
	
	private Button ButtonBack;
	private Button ButtonAccept;
	private Button ButtonNext;

	private SQDBAdapter db;
	
	private User user;
	
	private Question[] questions;
	private Question currentQuestion;
	
	private SharedPreferences prefs;
	
	private int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		
		db = new SQDBAdapter (this);
		db.open();
		
		getUser();
		
		getQuestions ();
		
		initLayout();
		
		setNextQuestion();
	}
	
	private void getUser() {
		prefs = getSharedPreferences (getString (R.string.file_preferences), MODE_PRIVATE);
		
		String log = prefs.getString(getString(R.string.preferences_login_key), "");
		
		user = db.getUserByNickname(log);
	}
	
	private void getQuestions () {
		questions = db.getAllQuestions(user, false);
		position = 0;
	}
	
	private void initLayout() {
		EditTextAnswer = (EditText) findViewById (R.id.EditTextAnswer);
		
		TextViewNickname = (TextView)findViewById (R.id.TextViewNickname);
		TextViewTitle = (TextView)findViewById (R.id.TextViewTitle);
		TextViewAward = (TextView)findViewById (R.id.TextViewAward);
		TextViewText = (TextView)findViewById (R.id.TextViewText);
		
		ButtonBack = (Button)findViewById (R.id.ButtonBack);
		ButtonAccept = (Button)findViewById (R.id.ButtonAccept);
		ButtonNext = (Button)findViewById (R.id.ButtonNext);
		
		ButtonBack.setOnClickListener(this);
		ButtonAccept.setOnClickListener(this);
		ButtonNext.setOnClickListener(this);
	}
	
	private void setNextQuestion () {
		if (position == questions.length) {
			Toast.makeText(this, "No more questions :(", Toast.LENGTH_SHORT).show();
			return;
		}
		
		TextViewNickname.setText(getString(R.string.RegistrationLoginString) + ": " + 
							     questions[position].getUser().getID());
		TextViewTitle.setText(getString (R.string.title) + ": " + questions[position].getTitle());
		TextViewAward.setText(getString (R.string.award) + ": " + questions[position].getRate());
		TextViewText.setText(questions[position].getText());
		
		EditTextAnswer.setText("");
		
		currentQuestion = questions[position];
		position++;
	}
	
	private void tryToSend () {
		Answer a = new Answer (currentQuestion, user,
								"", Answer.STATUS_NO_RESPONCE, currentQuestion.getRate(), 
								EditTextAnswer.getText().toString());
		db.addAnswer(a);
		db.updateQuestionStatus (currentQuestion, Question.STATUS_WITH_ANSWER);
		
		Toast.makeText(this, "The answer is sent", Toast.LENGTH_SHORT).show();
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_answer, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonAccept:
			tryToSend ();
		case R.id.ButtonNext:
			setNextQuestion();
			break;
		case R.id.ButtonBack:
			finish();
			break;
		}
	}

}
