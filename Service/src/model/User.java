package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * 用户实体
 * @author yijiull
 *
 */
public class User implements Serializable{
	
	public User(String userName) {
		super();
		this.userName = userName;
	}
	private String name;
	private String ID;
	private String phone;
	
	private String userName;
	private String password;
	
	
	public Socket socket;
	public ObjectOutputStream cout;
	public ObjectInputStream cin;
	
	

	@Override
	public String toString() {
		return name + " " + ID + " " + phone + " " + userName + " " + password;
	}
	public User(String name, String iD, String phone, String userName, String password) {
		super();
		this.name = name;
		ID = iD;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
