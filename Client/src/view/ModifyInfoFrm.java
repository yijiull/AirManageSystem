package view;

import java.awt.EventQueue; 

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import Util.StringUtil;
import model.User;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class ModifyInfoFrm extends JFrame {
	private JTextField nameTxt;
	private JTextField IDTxt;
	private JTextField phoneTxt;
	private JTextField userNameTxt;
	private JTextField passwordTxt;
	
	private ClientFrm frm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyInfoFrm frame = new ModifyInfoFrm(null);
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
	public ModifyInfoFrm(ClientFrm frm) {
		this.frm = frm;

		setTitle("\u4E2A\u4EBA\u4FE1\u606F");
		setBounds(100, 100, 383, 512);
		
		JLabel label = new JLabel("\u59D3  \u540D\uFF1A");
		
		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
		
		JLabel label_2 = new JLabel("\u624B  \u673A\uFF1A");
		
		JLabel label_3 = new JLabel("\u7528\u6237\u540D\uFF1A");
		
		JLabel label_4 = new JLabel("\u5BC6  \u7801\uFF1A");
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		
		IDTxt = new JTextField();
		IDTxt.setEditable(false);
		IDTxt.setColumns(10);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		
		userNameTxt = new JTextField();
		userNameTxt.setEditable(false);
		userNameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		
		JButton button = new JButton("\u4FDD\u5B58");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(299, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(66))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(32))
		);
		getContentPane().setLayout(groupLayout);
		
		init(frm.user);
	}
	
	/**
	 * 初始化个人信息
	 */
	private void init(User user) {
		nameTxt.setText(user.getName());
		IDTxt.setText(user.getID());
		phoneTxt.setText(user.getPhone());
		userNameTxt.setText(user.getUserName());
		passwordTxt.setText(user.getPassword());
	}

	/**
	 * 保存个人信息的修改
	 * @param e
	 * @throws IOException 
	 */
	private void saveActionPerformed(ActionEvent e) throws IOException {
		// TODO Auto-generated method stub
		String name = nameTxt.getText();
		String ID = IDTxt.getText();
		String phone = phoneTxt.getText();
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		User user = new User(name, ID, phone, userName, password);
		if(StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "电话不能为空");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		frm.cout.writeObject("修改信息");
		frm.cout.flush();
		frm.cout.writeObject(user);
		frm.cout.flush();
		dispose();
	}

}
