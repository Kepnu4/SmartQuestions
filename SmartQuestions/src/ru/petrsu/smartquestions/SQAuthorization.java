package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SQAuthorization extends Activity implements OnClickListener{

	private EditText login;
	private EditText password;
	
	private Button enter;
	private Button register;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        
        initLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_authorization, menu);
        return true;
    }
    
    private void initLayout() {
    	login = (EditText)findViewById(R.id.EditTextLogin);
    	password = (EditText)findViewById(R.id.EditTextPassword);
    	
    	enter = (Button)findViewById (R.id.ButtonEnter);
    	register = (Button)findViewById (R.id.ButtonRegister);
    	
    	enter.setOnClickListener(this);
    	register.setOnClickListener(this);
    }

	public void onClick(View v) {
		
	}
}
