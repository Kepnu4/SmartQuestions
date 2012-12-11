package ru.petrsu.smartquestions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
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
	
	private static final String ERROR_STRING_EMPTY_FIELDS = "Логин";
	private static final String ERROR_STRING_NOT_EXIST = "Неправильный логин или пароль";
	private static final String ERROR_STRING_NO_ERRORS = "Все идет по плану";
	
	private EditText login;
	private EditText password;
	
	private Button enter;
	private Button register;
	
	private String stringLogin;
	private String stringPassword;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        Log.d("auth", "here1");
        initLayout();
        Log.d("auth", "here2");
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
    	stringLogin = login.getText().toString();
    	stringPassword = password.getText().toString();
    	
    	if (stringLogin == null || stringLogin.equals("") || stringPassword == null || stringPassword.equals("")) {
    		return ERROR_EMPTY_FIELDS;
    	}
    	
    	SQDBAdapter db = new SQDBAdapter (this);
    	db.open();
    	
    	Log.d("auth", stringLogin + stringPassword);
    	if (!db.ifUserExist(stringLogin, stringPassword)) {
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
    		SharedPreferences s = getSharedPreferences(getString(R.string.file_preferences), MODE_PRIVATE);
    		Editor e = s.edit();
    		
    		e.putString(getString(R.string.preferences_login_key), stringLogin);
    		e.putString(getString(R.string.preferences_password_key), stringPassword);
    		
    		e.commit();
    		
    		Log.d ("here", s.getString(getString(R.string.preferences_login_key), ""));
    		startActivity (new Intent (this, SQProfile.class));
    		finish();
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
