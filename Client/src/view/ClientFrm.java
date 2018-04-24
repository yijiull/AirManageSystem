package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.ClientReceive;
import model.User;

import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClientFrm extends JFrame implements ActionListener {

	String ip = "127.0.0.1"; //ip地址
	int port = 8888;  //端口
	
	Socket socket;
	ObjectOutputStream cout;
	ObjectInputStream cin;
	
	ClientReceive recvThread;
	
	private JPanel contentPane;

	private JMenuItem regist_menu;
	public JMenuItem logon_menu;
	public JMenuItem exit_menu;
	public JMenuItem buy_menu;
	public JMenuItem show_menu;
	private JMenuItem help_menu;
	public JMenuItem modify_menu;
	
	public JLabel infoTxt;
	
	public User user = null;
//	public boolean isRun = false;
	
	public SearchFrm searchFrm;
	public OrderFrm orderFrm;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrm frame = new ClientFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientFrm() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					 if(socket!= null && !socket.isClosed()) {
						 cout.writeObject("注销");
						 cout.flush();
					 }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
			
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 341);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("\u7BA1\u7406");
		menuBar.add(menu);

		regist_menu = new JMenuItem("\u6CE8\u518C");
		regist_menu.addActionListener(this);
		menu.add(regist_menu);

		JSeparator separator = new JSeparator();
		menu.add(separator);

		logon_menu = new JMenuItem("\u767B\u5F55");
		logon_menu.addActionListener(this);
		menu.add(logon_menu);

		exit_menu = new JMenuItem("\u6CE8\u9500");
		exit_menu.setEnabled(false);
		exit_menu.addActionListener(this);
		
		modify_menu = new JMenuItem("\u4FEE\u6539\u4FE1\u606F");
		modify_menu.setEnabled(false);
		modify_menu.addActionListener(this);
		menu.add(modify_menu);
		menu.add(exit_menu);

		JMenu menu_1 = new JMenu("\u529F\u80FD");
		menuBar.add(menu_1);

		buy_menu = new JMenuItem("\u8BA2\u7968");
		buy_menu.setEnabled(false);
		buy_menu.addActionListener(this);
		menu_1.add(buy_menu);

		show_menu = new JMenuItem("\u6211\u7684\u8BA2\u5355");
		show_menu.setEnabled(false);
		show_menu.addActionListener(this);
		menu_1.add(show_menu);

		JMenu menu_2 = new JMenu("\u5173\u4E8E");
		menuBar.add(menu_2);

		help_menu = new JMenuItem("\u5E2E\u52A9");
		help_menu.addActionListener(this);
		menu_2.add(help_menu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u72B6\u6001\uFF1A");
		
		infoTxt = new JLabel("\u672A\u767B\u5F55...");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoTxt)
					.addContainerGap(274, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(223, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(infoTxt)))
		);
		contentPane.setLayout(gl_contentPane);
		
		setLocationRelativeTo(null);
	}

	/**
	 * 事件处理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =  e.getSource();
		
		if(obj == regist_menu) {
			regist();
		}else if(obj == logon_menu) {
			logon();
		}else if(obj == modify_menu) {
			modify();
		}else if(obj == exit_menu) {
			try {
//				isRun = false;
				cout.writeObject("注销");
				cout.flush();
				show_menu.setEnabled(false);
				buy_menu.setEnabled(false);
				exit_menu.setEnabled(false);
				modify_menu.setEnabled(false);
				logon_menu.setEnabled(true);
				infoTxt.setText("未登录...");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(obj == buy_menu) {
			buy();
		}else if(obj == show_menu) {
			showOrder();
		}else if(obj == help_menu) {
			JOptionPane.showMessageDialog(this, "自己摸索...");
		}
	}

	private void showOrder() {
		try {
			orderFrm = new OrderFrm(this);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		orderFrm.setLocationRelativeTo(this);
		orderFrm.setVisible(true);
		
	}

	private void buy() {
		searchFrm = new SearchFrm(this);
		searchFrm.setLocationRelativeTo(null);
		searchFrm.setVisible(true);
		
	}

	private void modify() {
		ModifyInfoFrm frm = new ModifyInfoFrm(this);
		frm.setVisible(true);
	}

	private void logon() {
		LogonFrm frm = new LogonFrm(this);
		frm.setVisible(true);
		
	}

	private void regist() {
		RegistFrm frm = new RegistFrm(this);
		frm.setVisible(true);
	}
	public void connect() {
		try {
			socket = new Socket(ip, port);
		} catch (IOException e1) {
			System.out.println("连接bug。。。");
			e1.printStackTrace();
		}
//		isRun = true;
		try {
			cout = new ObjectOutputStream(socket.getOutputStream());
			cout.flush();
			cin = new ObjectInputStream(socket.getInputStream());
//			isRun = true;
			recvThread = new ClientReceive(socket, cout, cin, this);
			recvThread.start();
		} catch (IOException e1) {
			System.out.println("流。。。");
			e1.printStackTrace();
		}
	}
	
	public void disConnect() throws IOException {
		if(socket.isClosed()) {
			return;
		}
//		isRun = false;
		socket.close();
		cout.close();
		cin.close();
	}
}
