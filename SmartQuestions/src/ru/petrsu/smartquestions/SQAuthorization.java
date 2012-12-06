package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQAuthorization extends Activity implements OnClickListener{

	private static final int ERROR_EMPTY_FIELDS = 1;
	private static final int ERROR_NOT_EXIST = 2;

	private static final int NO_ERRORS = 0;
	
	private static final String ERROR_STRING_EMPTY_FIELDS = "Логин захуячь мудила";
	private static final String ERROR_STRING_NOT_EXIST = "Неправильный логин или пароль";
	private static final String ERROR_STRING_NO_ERRORS = "Все идет по плану";
	
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
    
    /*
     * validates text in login and password and checks all shit 
     */
    private int validate () {
    	String log = login.getText().toString();
    	String pass = password.getText().toString();
    	
    	if (log == null || log.equals("") || pass == null || pass.equals("")) {
    		return ERROR_EMPTY_FIELDS;
    	}
    	
    	SQDBAdapter db = new SQDBAdapter (this);
    	db.open();
    	
    	if (!db.ifUserExist(log, pass)) {
    		return ERROR_NOT_EXIST;
    	}
    	return NO_ERRORS;
    }
    
    private void tryToLogin () {
    	String message = "";
    	boolean ok = false;
    	
    	switch (validate()) {
    	case ERROR_EMPTY_FIELDS:
    		message = ERROR_STRING_EMPTY_FIELDS;
    		break;
    	case ERROR_NOT_EXIST:
    		message = ERROR_STRING_NOT_EXIST;
    		break;
    	case NO_ERRORS:
    		message = ERROR_STRING_NO_ERRORS;
    		ok = true;
    		break;
    	}
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    	
    	if (ok) {
    		//Start new activity here
    		//TODO;
    	}
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonEnter:
			tryToLogin();
			break;
		case R.id.ButtonRegister:
			Intent intent = new Intent (this, SQRegistration.class);
			startActivity(intent);
			break;
		}
	}
}
