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

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Util.StringUtil;
import model.User;

public class RegistFrm extends JFrame implements ActionListener{
	
	private ClientFrm frm;
	private JPanel contentPane;
	
	private JTextField nameTxt;
	private JTextField IDTxt;
	private JTextField phoneTxt;
	private JTextField userNameTxt;
	private JTextField passwordTxt;
	
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistFrm frame = new RegistFrm(null);
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
	public RegistFrm(ClientFrm _frm) {
		frm = _frm;
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("\u6CE8\u518C");
		
		setBounds(100, 100, 427, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u59D3  \u540D\uFF1A");
		
		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
		
		JLabel label_2 = new JLabel("\u624B  \u673A\uFF1A");
		
		JLabel label_3 = new JLabel("\u7528\u6237\u540D\uFF1A");
		
		JLabel label_4 = new JLabel("\u5BC6  \u7801\uFF1A");
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		
		IDTxt = new JTextField();
		IDTxt.setColumns(10);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		
		button = new JButton("\u5B8C\u6210");
		button.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_4)
							.addGap(18)
							.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_3)
							.addGap(18)
							.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2)
							.addGap(18)
							.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(170, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(274, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(75))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(IDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(58)
					.addComponent(button)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		//居中
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == button) {
			
			if(StringUtil.isEmpty(nameTxt.getText()) || StringUtil.isEmpty(IDTxt.getText())
					|| StringUtil.isEmpty(phoneTxt.getText()) || StringUtil.isEmpty(userNameTxt.getText())
					|| StringUtil.isEmpty(passwordTxt.getText())) {
				JOptionPane.showMessageDialog(frm, "请填完整信息！");	
				return ;
			}
			
			User user = new User(nameTxt.getText(), IDTxt.getText(), 
					phoneTxt.getText(), userNameTxt.getText(), passwordTxt.getText());
			try {
				frm.connect();
				frm.cout.writeObject("注册");
				frm.cout.flush();
				frm.cout.writeObject(user);
				frm.cout.flush();
			} catch (IOException e1) {
				System.out.println("流。。。");
				e1.printStackTrace();
			}finally{
			}
			dispose();
		}
		
	}
}
