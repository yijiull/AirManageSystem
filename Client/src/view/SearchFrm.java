package view;

import java.awt.BorderLayout; 
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Util.FileUtil;
import model.User;

import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchFrm extends JFrame {

	private JPanel contentPane;
	private JTextField stPlaceTxt;
	private JTextField edPlaceTxt;
	private JTextField dateTxt;
	public JTable resultTable;
	private JButton searchBt;
	private JComboBox levelBox;
	private JButton buyBt;
	public List<Integer> listId;  //待选的航班列表
	public List<Vector<String>> list;
	int id;  //选择的第几趟航班
	int left; //剩余票数
	private ClientFrm frm;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrm frame = new SearchFrm(null);
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
	public SearchFrm(ClientFrm frm) {
		this.frm = frm;
		setTitle("\u822A\u73ED\u67E5\u8BE2");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();

		
		JLabel label = new JLabel("\u8D77\u70B9\uFF1A");
		
		stPlaceTxt = new JTextField();
		stPlaceTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7EC8\u70B9\uFF1A");
		
		edPlaceTxt = new JTextField();
		edPlaceTxt.setColumns(10);
		
		searchBt = new JButton("\u641C\u7D22");
		searchBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel label_2 = new JLabel("\u51FA\u53D1\u65E5\u671F\uFF1A");
		
		dateTxt = new JTextField();
		dateTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(stPlaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(15))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(edPlaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(searchBt)
							.addGap(34))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(stPlaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(dateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(edPlaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(searchBt)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		buyBt = new JButton("\u786E\u8BA4\u8D2D\u4E70");
		buyBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buyActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		levelBox = new JComboBox();
		levelBox.setModel(new DefaultComboBoxModel(new String[] {"\u5934\u7B49\u8231", "\u5546\u52A1\u8231", "\u7ECF\u6D4E\u8231"}));
		
		JLabel label_3 = new JLabel("\u8BF7\u9009\u62E9\u8231\u4F4D\uFF1A");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(levelBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(buyBt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 393, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buyBt)
						.addComponent(levelBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3)))
		);
		
		resultTable = new JTable();
		resultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableSelectActionperformed(e);
			}
		});
		resultTable.setFont(UIManager.getFont("CheckBox.font"));
		resultTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u822A\u7A7A\u516C\u53F8", "\u822A\u73ED\u7F16\u53F7", "\u8D77\u70B9", "\u7EC8\u70B9", "\u51FA\u53D1\u65F6\u95F4", "\u5230\u8FBE\u65F6\u95F4", "\u5934\u7B49\u8231\u4F59\u91CF", "\u5934\u7B49\u8231\u4EF7\u683C", "\u5546\u52A1\u8231\u4F59\u91CF", "\u5546\u52A1\u8231\u4EF7\u683C", "\u7ECF\u6D4E\u8231\u4F59\u91CF", "\u7ECF\u6D4E\u8231\u4EF7\u683C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(resultTable);
		contentPane.setLayout(gl_contentPane);
		stPlaceTxt.setText("beijing");
		edPlaceTxt.setText("guangzhou");
		dateTxt.setText("20180101");
	}
	
	/**
	 * 点击表格的行
	 * @param evt
	 */
	private void tableSelectActionperformed(MouseEvent evt) {
		id = listId.get(resultTable.getSelectedRow());
		System.out.println("第几趟航班？  "+id);
		
	}

	/**
	 * 确认购买
	 * @param e
	 * @throws Exception 
	 */
	private void buyActionPerformed(ActionEvent evt) throws Exception {
		int level = levelBox.getSelectedIndex();
		
		int left = FileUtil.newInstance().getCnt(id, level);
		if(left == 0) {
			int ans = JOptionPane.showConfirmDialog(this, "对不起，票已售完。请问是否排队抢票？");
			if(ans == 1) {
				frm.cout.writeObject("排队抢票");
				frm.cout.flush();
				frm.cout.writeInt(id);
				frm.cout.writeInt(level);
				frm.cout.flush();
			}else {
				return;
			}
			
		}else {
			System.out.println("舱位等级？" + level);
			frm.cout.writeObject("购买");
			frm.cout.flush();
			frm.cout.writeInt(id);
			frm.cout.writeInt(level);
			frm.cout.flush();
		}
	}
	
	public void upd(ActionEvent e) throws Exception {
		ListActionPerformed(e);
	}
	
	/**
	 * 刷新查询结果
	 * @throws Exception
	 */
	public void freshTable() throws Exception {
		DefaultTableModel dtm = (DefaultTableModel) resultTable.getModel();
		dtm.setRowCount(0); //清空表格
		for(Vector<String> v : list) {
			dtm.addRow(v);
		}
	}

	/**
	 * 向服务端发送查询条件
	 * @param evt
	 * @throws Exception 
	 */
	protected void ListActionPerformed(ActionEvent evt) throws Exception {
		String stPlace = stPlaceTxt.getText().trim();
		String edPlace = edPlaceTxt.getText().trim();
		long date = Long.parseLong(dateTxt.getText().trim() + "0000");
		frm.cout.writeObject("查询");
		frm.cout.flush();
		frm.cout.writeObject(stPlace);
		frm.cout.writeObject(edPlace);
		frm.cout.writeLong(date);
		frm.cout.flush();
	}
}
