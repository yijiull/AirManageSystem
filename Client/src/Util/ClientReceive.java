package Util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.User;
import view.ClientFrm;

/**
 * 实现客户端的消息收发
 * @author yijiull
 *
 */
public class ClientReceive extends Thread{
	private static final String User = null;
	private Socket socket;
	private ObjectOutputStream cout;
	private ObjectInputStream cin;
	
	private ClientFrm frm;
	
	
	public ClientReceive(Socket socket, ObjectOutputStream cout, ObjectInputStream cin, ClientFrm frm) {
		super();
		this.socket = socket;
		this.cout = cout;
		this.cin = cin;
		this.frm = frm;
	}
	
	public ClientFrm getFrm() {
		return frm;
	}

	public void setFrm(ClientFrm frm) {
		this.frm = frm;
	}

	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ObjectOutputStream getCout() {
		return cout;
	}
	public void setCout(ObjectOutputStream cout) {
		this.cout = cout;
	}
	public ObjectInputStream getCin() {
		return cin;
	}
	public void setCin(ObjectInputStream cin) {
		this.cin = cin;
	}
	@Override
	public void run() {
		while(/*frm.isRun && */!socket.isClosed()) {
			String op;
			try {
				//TODO  BUG socket is closed!!!  进程问题
				op = (String)cin.readObject();
//				System.out.println(frm.isRun);
				if(op.equals("注册成功")) {
					frm.user = (User) cin.readObject();
					JOptionPane.showMessageDialog(frm, "注册成功");
					frm.disConnect();
				}else if(op.equals("登录成功")) {
					frm.user = (User)cin.readObject();
					frm.infoTxt.setText(frm.user.getUserName()+" 已登录...");
					frm.modify_menu.setEnabled(true);
					frm.exit_menu.setEnabled(true);
					frm.buy_menu.setEnabled(true);
					frm.show_menu.setEnabled(true);
					frm.logon_menu.setEnabled(false);
					System.out.println(frm.user);
					JOptionPane.showMessageDialog(frm, "登录成功");
				}else if(op.equals("登录失败")) {
					frm.disConnect();
					JOptionPane.showMessageDialog(frm, "用户名或密码有误！\n请重新登录...");
				}else if(op.equals("修改成功")) {
					JOptionPane.showMessageDialog(frm, "修改成功！");
				}else if(op.equals("注销")) {
					try {
						frm.disConnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}else if(op.equals("查询为空")) {
					JOptionPane.showMessageDialog(frm.searchFrm, "没有查询到符合要求的航班");
				}else if(op.equals("查询航班结果")) {
					frm.searchFrm.list = (List<Vector<String>>) cin.readObject();
					frm.searchFrm.listId = (List<Integer>) cin.readObject();
					frm.searchFrm.freshTable();
				}else if(op.equals("售罄")) {
					JOptionPane.showMessageDialog(null, "对不起，该航班舱位已经售完");
				}else if(op.equals("购买成功")) {
					JOptionPane.showMessageDialog(frm.searchFrm, "购买成功！");
					frm.searchFrm.upd(null);
				}else if(op.equals("返回订单")) {
					frm.orderFrm.list = (List<Vector<String>>) cin.readObject();
					frm.orderFrm.fillTable();
				}else if(op.equals("取消成功")) {
					frm.orderFrm.fillTable();
					JOptionPane.showMessageDialog(frm.orderFrm, "取消成功");
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
			}
			
			
		}
		
	}
	
}
