package socket;

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
 * ʵ�ֿͻ��˵���Ϣ�շ�
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
				//TODO  BUG socket is closed!!!  ��������
				op = (String)cin.readObject();
//				System.out.println(frm.isRun);
				if(op.equals("ע��ɹ�")) {
					frm.user = (User) cin.readObject();
					JOptionPane.showMessageDialog(frm, "ע��ɹ�");
					frm.disConnect();
				}else if(op.equals("��¼�ɹ�")) {
					frm.user = (User)cin.readObject();
					frm.infoTxt.setText(frm.user.getUserName()+" �ѵ�¼...");
					frm.modify_menu.setEnabled(true);
					frm.exit_menu.setEnabled(true);
					frm.buy_menu.setEnabled(true);
					frm.show_menu.setEnabled(true);
					frm.logon_menu.setEnabled(false);
					System.out.println(frm.user);
					JOptionPane.showMessageDialog(frm, "��¼�ɹ�");
				}else if(op.equals("��¼ʧ��")) {
					frm.disConnect();
					JOptionPane.showMessageDialog(frm, "�û�������������\n�����µ�¼...");
				}else if(op.equals("�޸ĳɹ�")) {
					JOptionPane.showMessageDialog(frm, "�޸ĳɹ���");
				}else if(op.equals("ע��")) {
					try {
						frm.disConnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}else if(op.equals("��ѯΪ��")) {
					JOptionPane.showMessageDialog(frm.searchFrm, "û�в�ѯ������Ҫ��ĺ���");
				}else if(op.equals("��ѯ������")) {
					frm.searchFrm.list = (List<Vector<String>>) cin.readObject();
					frm.searchFrm.listId = (List<Integer>) cin.readObject();
					frm.searchFrm.freshTable();
				}else if(op.equals("����")) {
					
					JOptionPane.showMessageDialog(null, "�Բ��𣬸ú����λ�Ѿ�����");
				}else if(op.equals("����ɹ�")) {
					JOptionPane.showMessageDialog(frm.searchFrm, "����ɹ���");
					frm.searchFrm.upd(null);
				}else if(op.equals("���ض���")) {
					frm.orderFrm.list = (List<Vector<String>>) cin.readObject();
					frm.orderFrm.fillTable();
				}else if(op.equals("ȡ���ɹ�")) {
					frm.orderFrm.fillTable();
					JOptionPane.showMessageDialog(frm.orderFrm, "ȡ���ɹ�");
				}else if(op.equals("��Ʊ�ɹ�")) {
					Vector<String> v = (Vector<String>) cin.readObject();
					JOptionPane.showMessageDialog(frm, "���ã�Ϊ����Ʊ�ɹ�~" + v);
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
