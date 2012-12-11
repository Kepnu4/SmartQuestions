package ru.petrsu.smartquestions;

public class Answer {
	
	public static final int STATUS_NO_RESPONCE = 0;
	public static final int STATUS_ACCEPTED = 1;
	public static final int STATUS_NOT_ACCEPTED = 2;
	
	private int ID;
	private int status;
	private int rate;
	
	private Question question;
	
	private User user;
	
	private String timeCreate;
	private String text;
	
	public Answer (int id, Question question, User user, String timeCreate, int status, int rate, String text) {
		this.ID = id;
		this.question = question;
		this.user = user;
		this.timeCreate = timeCreate;
		this.status = status;
		this.rate = rate;
		this.text = text;
	}
	
	public Answer (Question question, User user, String timeCreate, int status, int rate, String text) {
		this.ID = -1;
		this.question = question;
		this.user = user;
		this.timeCreate = timeCreate;
		this.status = status;
		this.rate = rate;
		this.text = text;
	}
	
	public int getID () {
		return ID;
	}
	
	public void setID (int id) {
		ID = id;
	}
	
	public Question getQuestion () {
		return question;
	}
	
	public User getUser () {
		return user;
	}
	
	public int getStatus () {
		return status;
	}
	
	public int getRate () {
		return rate;
	}
	
	public String getTimeCreate () {
		return timeCreate;
	}
	
	public String getText () {
		return text;
	}
}
