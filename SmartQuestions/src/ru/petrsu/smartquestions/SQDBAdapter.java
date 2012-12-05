package ru.petrsu.smartquestions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQDBAdapter {
	protected static String DATABASE_NAME = "SimpleQuestions";
	protected static int DATABASE_VERSION = 1;
	
	//tblUsers constants
	public static String USER_TABLE_NAME = "tblUsers";
	
	protected static int USER_COL_ID = 0;
	protected static int USER_COL_NICKNAME = 1;
	protected static int USER_COL_DATE = 2;
	protected static int USER_COL_PASSWORD = 3;
	protected static int USER_COL_RATE = 4;
	
	protected static String USER_KEY_ID = "_id";
	protected static String USER_KEY_NICKNAME = "nickname";
	protected static String USER_KEY_DATE = "date";
	protected static String USER_KEY_PASSWORD = "password";
	protected static String USER_KEY_RATE = "rate";
	protected static String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
							USER_KEY_ID + " INT PRIMARY KEY AUTOINCREMENT, " + 
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
							TOPIC_KEY_ID + " INT PRIMARY KEY AUTOINCREMENT, " + 
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
							QUESTION_KEY_ID + " INT PRIMARY KEY AUTOINCREMENT, " + 
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
							ANSWER_KEY_ID + " INT PRIMARY KEY AUTOINCREMENT, " + 
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
							TOPICUSER_KEY_ID + " INT PRIMARY KEY AUTOINCREMENT, " +
							TOPICUSER_KEY_USERID + " INT REFERENCES " + USER_TABLE_NAME + " ( "
												 + USER_KEY_ID + " ) " + "ON UPDATE CASCADE, " + 
							TOPICUSER_KEY_TOPICID + " INT REFERENCES " + TOPICUSER_TABLE_NAME + " ( "
												  + TOPIC_KEY_ID + " ) " + "ON UPDATE CASCADE);";
	protected static String TOPICUSER_TABLE_DROP = "DROP TABLE " + TOPICUSER_TABLE_NAME + ";";
	
	
	protected SQLiteDatabase db;
	protected SQDBHelper dbHelper;
	
	public void open () {
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		db.close();
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
