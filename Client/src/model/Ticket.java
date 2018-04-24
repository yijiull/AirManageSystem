package model;

public class Ticket {
	private int level;
	private User user;
	
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ticket(int level, User user) {
		super();
		this.level = level;
		this.user = user;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
