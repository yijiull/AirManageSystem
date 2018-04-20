package view;

import java.awt.BorderLayout; 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.StringUtil;
import model.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
/**
 * 登录界面
 * @author yijiull
 *
 */
public class LogonFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	
	private ClientFrm frm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogonFrm frame = new LogonFrm(null);
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
	public LogonFrm(ClientFrm frm) {
		this.frm = frm;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setTitle("\u767B\u5F55");
		setBounds(100, 100, 435, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("华文隶书", Font.BOLD, 24));
		lblNewLabel.setIcon(new ImageIcon(LogonFrm.class.getResource("/image/plane_64px_1164879_easyicon.net.png")));
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logonActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		passwordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordTxt)
								.addComponent(userNameTxt))))
					.addContainerGap(119, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(306, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(button)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		//居中
		setLocationRelativeTo(null);
		
		userNameTxt.setText("yijiull");
		passwordTxt.setText("123456");
	}

	private void logonActionPerformed(ActionEvent evt) throws Exception {
			String userName = userNameTxt.getText();
			String password = new String(passwordTxt.getPassword());
			if(StringUtil.isEmpty(userName)) {
				JOptionPane.showMessageDialog(null, "用户名不能为空！");
				return;
			}else if(StringUtil.isEmpty(password)){
				JOptionPane.showMessageDialog(null, "密码不能为空");
				return;
			}
			User user = new User(userName, password);
			try {
				frm.connect();
				frm.cout.writeObject("登录");
				frm.cout.flush();
				frm.cout.writeObject(user);
				frm.cout.flush();
			} catch (IOException e1) {
				System.out.println("登录流。。。");
				e1.printStackTrace();
			}finally{
			}
			dispose();
	}
}
