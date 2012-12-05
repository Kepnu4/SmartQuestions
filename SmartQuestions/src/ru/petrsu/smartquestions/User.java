package ru.petrsu.smartquestions;

public class User {
	private int ID;
	private int rate;
	
	private String nickname;
	private String date;
	private String password;
	
	public User (String nick, String pass, String d, int r) {
		nickname = nick;
		password = pass;
		date = d;
		r = rate;
		ID = -1;
	}
	
	public User (String nick, String pass, String d, int r, int id) {
		ID = id;
		nickname = nick;
		password = pass;
		date = d;
		r = rate;
	}
	
	public void setID (int id) {
		ID = id;
	}
	
	public int getID () {
		return ID;
	}
	
	public void setRate (int nRate) {
		rate = nRate;
	}
	
	public int getRate () {
		return rate;
	}
	
	public String getNickname () {
		return nickname;
	}
	
	public String getPassword () {
		return password;
	}
	
	public String getDate () {
		return date;
	}
}
