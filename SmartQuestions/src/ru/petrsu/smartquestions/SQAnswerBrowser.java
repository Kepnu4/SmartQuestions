package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SQAnswerBrowser extends Activity implements OnClickListener{
	
	private Answer answer;
	
	private User user;
	
	private TextView TextViewTitle;
	private TextView TextViewText;
	
	private Button ButtonAccept;
	private Button ButtonDecline;
	
	private SQDBAdapter db;
	
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_browser);  
		
		db = new SQDBAdapter (this);
		db.open();
		
		prefs = getSharedPreferences (getString (R.string.file_preferences), MODE_PRIVATE);
		
		getAnswer ();
		getUser ();
		initLayout ();
	}
	
	@Override
	protected void onDestroy () {
		super.onDestroy();
		
		db.close();
	}
	
	private void getAnswer () {
		answer = db.getAnswerById (prefs.getInt(getString (R.string.preferences_answer_id_key), -1));
	}
	
	private void getUser () {
		user = db.getUserByNickname(prefs.getString(getString (R.string.preferences_login_key), ""));
	}
	
	private void initLayout() {
		TextViewTitle = (TextView)findViewById (R.id.TextViewTitle);
		TextViewText = (TextView)findViewById (R.id.TextViewText);
		
		TextViewTitle.setText(TextViewTitle.getText() + ": " + answer.getQuestion().getTitle());
		TextViewText.setText(answer.getText());
		
		ButtonAccept = (Button)findViewById (R.id.ButtonAccept);
		ButtonDecline = (Button)findViewById (R.id.ButtonDecline);
		
		if (user.getID() == answer.getUser().getID() || answer.getStatus() != Answer.STATUS_NO_RESPONCE) {
			ButtonAccept.setVisibility(View.GONE);
			ButtonDecline.setVisibility(View.GONE);
		}
		else {
			ButtonAccept.setOnClickListener(this);
			ButtonDecline.setOnClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_answer_browser, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonAccept:
			db.updateAnswerStatus (answer, Answer.STATUS_ACCEPTED);
			ButtonAccept.setVisibility(View.GONE);
			ButtonDecline.setVisibility(View.GONE);
			
			Toast.makeText(this, "Answer is accepted", Toast.LENGTH_SHORT).show();
			break;
		case R.id.ButtonDecline:
			db.updateAnswerStatus(answer, Answer.STATUS_NOT_ACCEPTED);
			db.updateQuestionStatus(answer.getQuestion(), Question.STATUS_NO_ANSWER);
			finish();
			break;
		}
		
	}

}
