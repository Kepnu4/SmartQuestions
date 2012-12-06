package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.support.v4.app.NavUtils;

public class SQRegistration extends Activity implements OnClickListener{
	
	private EditText login;
	private EditText password;
	
	private Button send;
	
	GridView topics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        
        initLayout();
    }
    
    private void initLayout() {
    	login = (EditText)findViewById(R.id.EditTextLogin);
    	password = (EditText)findViewById (R.id.EditTextPassword);
    	
    	send = (Button)findViewById (R.id.ButtonSend);
    	send.setOnClickListener(this);
    	
    	SQDBAdapter db = new SQDBAdapter (this);
    	db.open();
    	
    	topics = (GridView)findViewById (R.id.GridViewTopics);
    	
    	try {
    		topics.setAdapter(new ArrayAdapter <String> (this, R.layout.gridview_item, db.getAllTopics()));
    	}
    	catch (NullPointerException e) {
    		Log.d("null", "happend");
    	}
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
