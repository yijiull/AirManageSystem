package Util;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.User;

public class ServerListen extends Thread {

	private ServerSocket server;
	public boolean isStop;
	
	private ServerReceive recvThread;
	private User user;
	
	
	
	public ServerListen(ServerSocket server) {
		super();
		isStop = false;
		this.server = server;
	}


	public ServerSocket getServer() {
		return server;
	}


	public void setServer(ServerSocket server) {
		this.server = server;
	}


	@Override
	public void run() {
		while(!isStop && !server.isClosed()) {
			user = new User();
			try {
				user.socket = server.accept();
				user.cin = new ObjectInputStream(user.socket.getInputStream());
				user.cout = new ObjectOutputStream(user.socket.getOutputStream());
				
				String op = (String) user.cin.readObject();
				if(op.equals("ע��")) {
					User u = (User) user.cin.readObject();
					String userName = u.getUserName();
					String password = u.getPassword();
					Scanner cin = new Scanner(FileUtil.readFile("user"));
					while(cin.hasNext()) {
						if(cin.next().equals(userName)) {
							JOptionPane.showMessageDialog(null, "���û����ѱ�ע�ᣡ");
							cin.close();
							return;
						}
						cin.next();
					}
					File file = new File("src\\file\\" + userName + ".txt");
					try {
						file.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					FileUtil.addFile(userName, u.toString(), false);
					System.out.println(FileUtil.readFile(userName));
					
					FileUtil.addFile("user", userName + " " + password, true);
					
					System.out.println("ע�᣺  " + u);
					user.cout.writeObject("ע��ɹ�");
					user.cout.flush();
					user.cout.writeObject(u);
					user.cout.flush();
				}else if(op.equals("��¼")) {
					User u = (User) user.cin.readObject();
					Scanner cin = new Scanner(FileUtil.readFile("user"));
					int ok = 1;
					while(cin.hasNext()) {
						String userName = cin.next();
						String password = cin.next();
						if(u.getUserName().equals(userName) &&u.getPassword().equals(password)) {
							ok = 0;
							Scanner c = new Scanner(FileUtil.readFile(u.getUserName()));
							User temp = new User();
							temp.setName(c.next());
							temp.setID(c.next());
							temp.setPhone(c.next());
							temp.setUserName(c.next());
							temp.setPassword(c.next());
							c.close();
							user.cout.writeObject("��¼�ɹ�");
							user.cout.flush();
							user.cout.writeObject(temp);
							user.cout.flush();
							temp.cin = user.cin;
							temp.cout = user.cout;
							temp.socket = user.socket;
							recvThread = new ServerReceive(temp);
							recvThread.start();
							break;
						}
					}
					if(ok == 1) {
						user.cout.writeObject("��¼ʧ��");
						user.cout.flush();
					}
					cin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
