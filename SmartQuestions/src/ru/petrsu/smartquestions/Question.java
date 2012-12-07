package ru.petrsu.smartquestions;

public class Question {
	public static final int STATUS_NO_ANSWER = 0;
	public static final int STATUS_WITH_ANSWER = 1;
	public static final int STATUS_TIMEOUT = 2;
	
	private int ID;
	private int topicID;
	private int timeout;
	private int status;
	private int rate;
	
	private User user;
	
	private String text;
	private String title;
	private String timeCreate;
	
	public Question (int ID, int topicID, String title, User user, String timeCreate, 
					 int timeout, int status, int rate, String text) {
		this.ID = ID;
		this.topicID = topicID;
		this.title = title;
		this.user = user;
		this.timeCreate = timeCreate;
		this.timeout = timeout;
		this.status = status;
		this.rate = rate;
		this.text = text;
	}
	
	public Question (int topicID, String title, User user, String timeCreate, 
			 int timeout, int status, int rate, String text) {
		this.ID = -1;
		this.topicID = topicID;
		this.title = title;
		this.user = user;
		this.timeCreate = timeCreate;
		this.timeout = timeout;
		this.status = status;
		this.rate = rate;
		this.text = text;
}
	
	public void setID (int id) {
		this.ID = id;
	}
	
	public int getID () {
		return ID;
	}
	
	public void setTopicID (int topicID){
		this.topicID = topicID;
	}
	
	public int getTopicID () {
		return topicID;
	}
	
	public int getTimeout () {
		return timeout;
	}
	
	public void setStatus (int status) {
		this.status = status;
	}
	
	public int getStatus () {
		return status;
	}
	
	public int getRate () {
		return rate;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getText () {
		return text;
	}
	
	public String getTitle () {
		return title;
	}
	
	public String getTimeCreate () {
		return timeCreate;
	}
	
	public String toString() {
		return getTitle();
	}
}
