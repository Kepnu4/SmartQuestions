package ru.petrsu.smartquestions;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQDBAdapter {
	protected static String DATABASE_NAME = "SimpleQuestions";
	protected static int DATABASE_VERSION = 5;
	
	//tblUsers constants
	public static String USER_TABLE_NAME = "tblUsers";
	
	protected static int USER_COL_ID = 0;
	protected static int USER_COL_NICKNAME = 1;
	protected static int USER_COL_PASSWORD = 2;
	protected static int USER_COL_DATE = 3;
	protected static int USER_COL_RATE = 4;
	
	protected static String USER_KEY_ID = "_id";
	protected static String USER_KEY_NICKNAME = "nickname";
	protected static String USER_KEY_DATE = "date";
	protected static String USER_KEY_PASSWORD = "password";
	protected static String USER_KEY_RATE = "rate";
	protected static String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
							USER_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							USER_KEY_NICKNAME + " TEXT, " + 
							USER_KEY_DATE + " TEXT, " + 
							USER_KEY_PASSWORD + " TEXT, " +
							USER_KEY_RATE + " INT);";
	protected static String USER_TABLE_DROP = "DROP TABLE " + USER_TABLE_NAME + ";";
	
	//tblTopics constants
	public static String TOPIC_TABLE_NAME = "tblTopics";
	
	protected static int TOPIC_COL_ID = 0;
	protected static int TOPIC_COL_NAME = 1;
	
	protected static String TOPIC_KEY_ID = "_id";
	protected static String TOPIC_KEY_NAME = "name";
	protected static String TOPIC_TABLE_CREATE = "CREATE TABLE " + TOPIC_TABLE_NAME + " ( " +
							TOPIC_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							TOPIC_KEY_NAME + " TEXT);";
	protected static String TOPIC_TABLE_DROP = "DROP TABLE " + TOPIC_TABLE_NAME + ";";
	
	//tblQuestions constants
	public static String QUESTION_TABLE_NAME = "tblQuestions";
	
	protected static int QUESTION_COL_ID = 0;
	protected static int QUESTION_COL_TOPICID = 1;
	protected static int QUESTION_COL_TITLE = 2;
	protected static int QUESTION_COL_USERID = 3;
	protected static int QUESTION_COL_TIMECREATE = 4;
	protected static int QUESTION_COL_TIMEOUT = 5;
	protected static int QUESTION_COL_STATUS = 6;
	protected static int QUESTION_COL_RATE = 7;
	protected static int QUESTION_COL_TEXT = 8;
	
	protected static String QUESTION_KEY_ID = "_id";
	protected static String QUESTION_KEY_TOPICID = "topicID";
	protected static String QUESTION_KEY_TITLE = "title";
	protected static String QUESTION_KEY_USERID = "userID";
	protected static String QUESTION_KEY_TIMECREATE = "timeCreate";
	protected static String QUESTION_KEY_TIMEOUT = "timeout";
	protected static String QUESTION_KEY_STATUS = "status";
	protected static String QUESTION_KEY_RATE = "rate";
	protected static String QUESTION_KEY_TEXT = "text";
	protected static String QUESTION_TABLE_CREATE = "CREATE TABLE " + QUESTION_TABLE_NAME + " ( " +
							QUESTION_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							QUESTION_KEY_TOPICID + " INT REFERENCES " + TOPIC_TABLE_NAME + " ( " 
												 + TOPIC_KEY_ID + " ) " + " ON UPDATE CASCADE, " +
							QUESTION_KEY_TITLE + " TEXT, " +
							QUESTION_KEY_USERID + " INT REFERENCES " + USER_TABLE_NAME + " ( " 
									 			+ USER_KEY_ID + " ) " + " ON UPDATE CASCADE, " +
							QUESTION_KEY_TIMECREATE + " TEXT, " +
							QUESTION_KEY_TIMEOUT + " INT, " +
							QUESTION_KEY_STATUS + " INT, " +
							QUESTION_KEY_RATE + " INT, " +
							QUESTION_KEY_TEXT + " TEXT);";
	protected static String QUESTION_TABLE_DROP = "DROP TABLE " + QUESTION_TABLE_NAME + ";";
	
	//tblAnswer
	public static String ANSWER_TABLE_NAME = "tblAnswers";
	
	protected static int ANSWER_COL_ID = 0;
	protected static int ANSWER_COL_QUESTIONID = 1;
	protected static int ANSWER_COL_USERID = 2;
	protected static int ANSWER_COL_TIMECREATE = 3;
	protected static int ANSWER_COL_STATUS = 4;
	protected static int ANSWER_COL_RATE = 5;
	protected static int ANSWER_COL_TEXT = 6;
	
	protected static String ANSWER_KEY_ID = "_id";
	protected static String ANSWER_KEY_QUESTIONID = "questionID";
	protected static String ANSWER_KEY_USERID = "userID";
	protected static String ANSWER_KEY_TIMECREATE = "timeCreate";
	protected static String ANSWER_KEY_STATUS = "status";
	protected static String ANSWER_KEY_RATE = "rate";
	protected static String ANSWER_KEY_TEXT = "text";
	protected static String ANSWER_TABLE_CREATE = "CREATE TABLE " + ANSWER_TABLE_NAME + " ( " +
							ANSWER_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							ANSWER_KEY_QUESTIONID + " INT REFERENCES " + QUESTION_TABLE_NAME + " ( " 
						 						  + QUESTION_KEY_ID + " ) " + "ON UPDATE CASCADE, " +
							ANSWER_KEY_USERID + " INT REFERENCES " + USER_TABLE_NAME + " ( " 
						 					  + USER_KEY_ID + " ) " + " ON UPDATE CASCADE, " +
							ANSWER_KEY_TIMECREATE + " TEXT, " +
							ANSWER_KEY_STATUS + " INT, " +
							ANSWER_KEY_RATE + " INT, " +
							ANSWER_KEY_TEXT + " TEXT);";
	protected static String ANSWER_TABLE_DROP = "DROP TABLE " + ANSWER_TABLE_NAME + ";";
	
	//tblTopicsUsers constants
	public static String TOPICUSER_TABLE_NAME = "tblTopicsUsers";
	
	protected static int TOPICUSER_COL_ID = 0;
	protected static int TOPICUSER_COL_USERID = 1;
	protected static int TOPICUSER_COL_TOPICID = 2;
	
	protected static String TOPICUSER_KEY_ID = "_id";
	protected static String TOPICUSER_KEY_USERID = "userID";
	protected static String TOPICUSER_KEY_TOPICID = "topicID";
	protected static String TOPICUSER_TABLE_CREATE = "CREATE TABLE " + TOPICUSER_TABLE_NAME + " ( " +
							TOPICUSER_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							TOPICUSER_KEY_USERID + " INT REFERENCES " + USER_TABLE_NAME + " ( "
												 + USER_KEY_ID + " ) " + "ON UPDATE CASCADE, " + 
							TOPICUSER_KEY_TOPICID + " INT REFERENCES " + TOPICUSER_TABLE_NAME + " ( "
												  + TOPIC_KEY_ID + " ) " + "ON UPDATE CASCADE);";
	protected static String TOPICUSER_TABLE_DROP = "DROP TABLE " + TOPICUSER_TABLE_NAME + ";";
	
	
	protected SQLiteDatabase db;
	protected SQDBHelper dbHelper;
	
	/*
	 * Opens database, call before use database
	 */
	public void open () {
		db = dbHelper.getWritableDatabase();
	}
	
	/*
	 * closes database, call when work is done
	 */
	public void close() {
		db.close();
	}
	
	/*
	 * Checks if user with such nickname exists
	 */
	public boolean ifUserExist (String nickname) {
		User a = getUserByNickname (nickname);
		
		if (a == null) {
			return false;
		}
		return true;
	}
	
	/*
	 * Checks if user with such nickname and password exists
	 */
	public boolean ifUserExist (String nickname, String password) {
		User a = getUserByNickname (nickname);
		
		Log.d("db", "start checkin " + a.getNickname() + a.getPassword());
		if (a == null || !password.equals(a.getPassword())) {
			Log.d("db", "check:false");
			return false;
		}
		Log.d("db", "check:true");
		return true;
	}
	
	
	/*
	 * returns User with such nickname and password
	 */
	public User getUserByNickname (String nickname) {
		String selection = USER_KEY_NICKNAME + "=\"" + nickname + "\"";
		
		Cursor c = db.query(USER_TABLE_NAME, new String[] { USER_KEY_ID,
															USER_KEY_NICKNAME,
															USER_KEY_PASSWORD,
															USER_KEY_DATE, 
															USER_KEY_RATE }, selection, null, null, null, null);
		c.moveToNext();
		
		if (c == null || c.isAfterLast()) {
			return null;
		}
		
		Log.d("db", "gere");
		return new User (c.getString(USER_COL_NICKNAME), c.getString(USER_COL_PASSWORD), 
						 c.getString(USER_COL_DATE), c.getInt(USER_COL_RATE), c.getInt(USER_COL_ID));
	}
	
	/*
	 * returns user from userid
	 */
	
	public User getUserById (int id) {
		String selection = USER_KEY_ID + "=" + id;
		
		Cursor c = db.query(USER_TABLE_NAME, new String[] { USER_KEY_ID,
															USER_KEY_NICKNAME,
															USER_KEY_PASSWORD,
															USER_KEY_DATE, 
															USER_KEY_RATE }, selection, null, null, null, null);
		if (c == null || c.isLast()) {
			return null;
		}
		c.moveToNext();
		
		return new User (c.getString(USER_COL_NICKNAME), c.getString(USER_COL_PASSWORD), 
				 c.getString(USER_COL_DATE), c.getInt(USER_COL_RATE), c.getInt(USER_COL_ID));
	}
	
	/*
	 * add User in the database
	 * @param	topics
	 * 				topics in witch the user is fucking pro
	 */
	public void addUser (User user, String[] topics) {
		ContentValues a = new ContentValues();
		a.put(USER_KEY_NICKNAME, user.getNickname());
		a.put(USER_KEY_PASSWORD, user.getPassword());
		a.put(USER_KEY_DATE, user.getDate());
		a.put(USER_KEY_RATE, user.getRate());
		
		db.insert(USER_TABLE_NAME, null, a);
	}
	
	/*
	 * returns question by question id
	 */
	
	public Question getQuestionById (int id) {
		String selection = QUESTION_KEY_ID + "=" + id;
		
		Cursor c = db.query(QUESTION_TABLE_NAME, new String[] {QUESTION_KEY_ID,
				   											   QUESTION_KEY_TOPICID,
				   											   QUESTION_KEY_TITLE,
				   											   QUESTION_KEY_USERID,
				   											   QUESTION_KEY_TIMECREATE,
				   											   QUESTION_KEY_TIMEOUT,
				   											   QUESTION_KEY_STATUS,
				   											   QUESTION_KEY_RATE,
				   											   QUESTION_KEY_TEXT },
				   											   selection, null, null, null, null);
		if (c == null || !c.moveToNext()) {
			return null;
		}
		return new Question (c.getInt(QUESTION_COL_ID), c.getInt(QUESTION_COL_TOPICID),
				   			 c.getString(QUESTION_COL_TITLE), getUserById (c.getInt(QUESTION_COL_USERID)),
				   			 c.getString(QUESTION_COL_TIMECREATE), c.getInt(QUESTION_COL_TIMEOUT),
				   			 c.getInt(QUESTION_COL_STATUS), c.getInt(QUESTION_COL_RATE), 
				   			 c.getString(QUESTION_COL_TEXT));
	}
	
	/*
	 * Returns all questions that was ever asked by the user
	 * @param him true if need to get question of this user 
	 * 			  false if need to get all question but not from this user
	 */
	public Question[] getAllQuestions (User user, boolean him) {
		String selection;
		
		if (him) {
			selection = QUESTION_KEY_USERID + "=" + user.getID();
		} else {
			selection = QUESTION_KEY_USERID + "!=" + user.getID();
		}
		
		Cursor c = db.query(QUESTION_TABLE_NAME, new String[] {QUESTION_KEY_ID,
															   QUESTION_KEY_TOPICID,
															   QUESTION_KEY_TITLE,
															   QUESTION_KEY_USERID,
															   QUESTION_KEY_TIMECREATE,
															   QUESTION_KEY_TIMEOUT,
															   QUESTION_KEY_STATUS,
															   QUESTION_KEY_RATE,
															   QUESTION_KEY_TEXT },
															   selection, null, null, null, null);
		if (c == null) {
			return null;
		}
		
		Question[] ret = new Question[c.getCount()];
		
		Log.d("db", ret.length + " " + user.getID());
		
		for (int i = 0;i < ret.length;i++) {
			c.moveToNext();
			ret[i] = new Question (c.getInt(QUESTION_COL_ID), c.getInt(QUESTION_COL_TOPICID),
								   c.getString(QUESTION_COL_TITLE), getUserById (c.getInt(QUESTION_COL_USERID)),
								   c.getString(QUESTION_COL_TIMECREATE), c.getInt(QUESTION_COL_TIMEOUT),
								   c.getInt(QUESTION_COL_STATUS), c.getInt(QUESTION_COL_RATE), 
								   c.getString(QUESTION_COL_TEXT));
		}
		return ret;
	}
	
	/*
	 * Returns all answers that was ever given by the user
	 */
	public ArrayList <Answer> getAllAnswersFromUser (User user) {
		//TODO
		return null;
	}
	
	/*
	 * Returns all answers that was ever given for the question
	 */
	public ArrayList <Answer> getAllAnswersFromQuestion (Question q) {
		//TODO
		return null;
	}
	
	/*
	 * Adds the question in the database
	 */
	public void addQuestion (Question q) {
		ContentValues a = new ContentValues ();
		a.put(QUESTION_KEY_TOPICID, q.getTopicID());
		a.put(QUESTION_KEY_TITLE, q.getTitle());
		a.put(QUESTION_KEY_USERID, q.getUser().getID());
		a.put(QUESTION_KEY_TIMECREATE, q.getTimeCreate());
		a.put(QUESTION_KEY_TIMEOUT, q.getTimeout());
		a.put(QUESTION_KEY_STATUS, q.getStatus());
		a.put(QUESTION_KEY_RATE, q.getRate());
		a.put(QUESTION_KEY_TEXT, q.getText());
		
		Log.d("db", "adding " + q.getTitle());
		db.insert(QUESTION_TABLE_NAME, null, a);
	}
	
	/*
	 * Adds the answer in the database
	 */
	public void addAnswer (Answer answer) {
		//TODO
	}
	
	/*
	 * returns topic by id
	 */
	public String getTopicById (int id) {
		String selection = TOPIC_KEY_ID + "=" + id;
		
		Cursor c = db.query(TOPIC_TABLE_NAME, new String[] {TOPIC_KEY_NAME}, selection, null, null, null, null);
		
		if (c == null || !c.moveToNext()) {
			return null;
		}
		return c.getString(0);
	}
	
	/*
	 * returns topic id by topic name
	 */
	public int getTopicId (String name) {
		String selection = TOPIC_KEY_NAME + "=" + "\"" + name + "\"";
		
		Cursor c = db.query(TOPIC_TABLE_NAME, new String[] {TOPIC_KEY_ID}, selection, null, null, null, null);
		
		
		if (c == null || !c.moveToNext()) {
			return -1;
		}
		
		return c.getInt(TOPICUSER_COL_ID);
	}
	
	/*
	 * Returns all existing topics
	 */
	public String[] getAllTopics () {
		Cursor c = db.query(TOPIC_TABLE_NAME, new String[] { TOPIC_KEY_ID, TOPIC_KEY_NAME }, null, null, null, null, null);
		
		Log.d("db", "here");
		if (c == null || c.isAfterLast()) {
			return null;
		}
		Log.d("db", c.getCount() + "");
		
		String ret[] = new String[c.getCount()];
		
		for (int i = 0;i < ret.length;i++) {
			c.moveToNext();
			Log.d("db", i + " " + c.isLast() +  " " + c.isAfterLast());
			ret[i] = c.getString(TOPIC_COL_NAME);
			Log.d("db", ret[i]);
		}
		
		c.close();
		
		return ret;
	}

	
	public SQDBAdapter (Context context) {
		dbHelper = new SQDBHelper (context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private class SQDBHelper extends SQLiteOpenHelper {
		
		public SQDBHelper (Context context, String name, CursorFactory factory, int version) {
			super (context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(USER_TABLE_CREATE);
			_db.execSQL(QUESTION_TABLE_CREATE);
			_db.execSQL(ANSWER_TABLE_CREATE);
			_db.execSQL(TOPIC_TABLE_CREATE);
			_db.execSQL(TOPICUSER_TABLE_CREATE);
			
			_db.execSQL("INSERT INTO " + TOPIC_TABLE_NAME + " ( " + TOPIC_KEY_NAME + " ) VALUES " + " (\"Математика\");");
			_db.execSQL("INSERT INTO " + TOPIC_TABLE_NAME + " ( " + TOPIC_KEY_NAME + " ) VALUES " + " (\"Физика\");");
			_db.execSQL("INSERT INTO " + TOPIC_TABLE_NAME + " ( " + TOPIC_KEY_NAME + " ) VALUES " + " (\"Химия\");");
			_db.execSQL("INSERT INTO " + TOPIC_TABLE_NAME + " ( " + TOPIC_KEY_NAME + " ) VALUES " + " (\"История\");");
			_db.execSQL("INSERT INTO " + TOPIC_TABLE_NAME + " ( " + TOPIC_KEY_NAME + " ) VALUES " + " (\"Жиртрес\");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			_db.execSQL(USER_TABLE_DROP);
			_db.execSQL(QUESTION_TABLE_DROP);
			_db.execSQL(ANSWER_TABLE_DROP);
			_db.execSQL(TOPIC_TABLE_DROP);
			_db.execSQL(TOPICUSER_TABLE_DROP);
			onCreate (_db);
		}
	}
}
