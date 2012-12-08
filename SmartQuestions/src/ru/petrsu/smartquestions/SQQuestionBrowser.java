package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SQQuestionBrowser extends Activity implements OnClickListener{
	
	private TextView TextViewTitle;
	private TextView TextViewTopic;
	private TextView TextViewAward;
	private TextView TextViewTimeleft;
	
	private Button ButtonShowAnswer;
	private Button ButtonBack;
	
	private SQDBAdapter db;
	
	private Question question;
	private String topic;
	
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_browser);
		
		db = new SQDBAdapter (this);
		db.open();
		
		prefs = getSharedPreferences (getString (R.string.file_preferences), MODE_PRIVATE);
		
		getQuestion ();
		getTopic ();
		
		initLayout();
	}
	
	private void getQuestion () {
		int id = prefs.getInt(getString (R.string.preferences_question_id_key), -1);
		
		question = db.getQuestionById(id);
	}
	
	private void getTopic () {
		topic = db.getTopicById (question.getID());
	}
	
	private void initLayout() {
		TextViewTitle = (TextView)findViewById (R.id.TextViewTitle);
		TextViewTopic = (TextView)findViewById (R.id.TextViewTopic);
		TextViewAward = (TextView)findViewById (R.id.TextViewAward);
		TextViewTimeleft = (TextView)findViewById (R.id.TextViewTimeleft);
		
		TextViewTitle.setText(TextViewTitle.getText() + ": " + question.getTitle());
		TextViewTopic.setText(TextViewTopic.getText() + ": " + topic);
		TextViewAward.setText(TextViewAward.getText() + ": " + question.getRate());
		TextViewTimeleft.setText(TextViewTimeleft.getText() + ": doesn't work lol");
		
		ButtonShowAnswer = (Button)findViewById (R.id.ButtonShowAnswer);
		ButtonBack = (Button)findViewById (R.id.ButtonBack);
		
		switch (question.getStatus()) {
		case Question.STATUS_NO_ANSWER:
		case Question.STATUS_TIMEOUT:
			ButtonShowAnswer.setVisibility(View.GONE);
			break;
		case Question.STATUS_WITH_ANSWER:
			ButtonShowAnswer.setOnClickListener(this);
			break;
		}
		ButtonBack.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_question_browser, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonShowAnswer:
			//TODO
			break;
		case R.id.ButtonBack:
			finish();
			break;
		}	
	}
}
