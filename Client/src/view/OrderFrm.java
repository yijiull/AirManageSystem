package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Util.FileUtil;
import model.User;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class OrderFrm extends JFrame {

	private User user;
	private JPanel contentPane;
	private JTable OrderTable;
	private JTextField stPlaceTxt;
	private JTextField edPlaceTxt;
	private JTextField dateTxt;
	private JTextField levelTxt;
	private JTextField priceTxt;

	public List<Vector<String>> list = new ArrayList<Vector<String>>();

	private ClientFrm frm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrm frame = new OrderFrm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public OrderFrm(ClientFrm frm) throws IOException {
		this.frm = frm;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		setTitle("\u6211\u7684\u8BA2\u5355");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "\u8BE6\u7EC6\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel label = new JLabel("\u59CB \u53D1 \u5730\uFF1A");

		stPlaceTxt = new JTextField();
		stPlaceTxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u76EE\u7684\u5730\uFF1A");

		edPlaceTxt = new JTextField();
		edPlaceTxt.setColumns(10);

		JLabel label_1 = new JLabel("\u51FA\u53D1\u65F6\u95F4\uFF1A");

		dateTxt = new JTextField();
		dateTxt.setColumns(10);

		JLabel label_2 = new JLabel("\u8231  \u4F4D\uFF1A");

		levelTxt = new JTextField();
		levelTxt.setColumns(10);

		JLabel label_3 = new JLabel("\u7968  \u4EF7  \uFF1A");

		priceTxt = new JTextField();
		priceTxt.setColumns(10);

		JButton button = new JButton("\u53D6\u6D88\u8BA2\u5355");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					concelActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1).addComponent(label_3).addComponent(label))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(stPlaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(edPlaceTxt,
												GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(dateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(label_2).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(levelTxt, 0, 0, Short.MAX_VALUE)))
						.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE).addComponent(button)
								.addGap(38)))));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(stPlaceTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel)
												.addComponent(edPlaceTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
												.addComponent(dateTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_2).addComponent(levelTxt,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(button).addComponent(label_3))));
		panel.setLayout(gl_panel);

		OrderTable = new JTable();
		OrderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePressedActionPerformed(e);
			}
		});
		OrderTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u822A\u73ED\u7F16\u53F7",
				"\u822A\u7A7A\u516C\u53F8", "\u59CB\u53D1\u5730", "\u76EE\u7684\u5730" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(OrderTable);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 420, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		query();
	}

	/**
	 * 表格行点击事件处理
	 * 
	 * @param e
	 */
	private void mousePressedActionPerformed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = OrderTable.getSelectedRow();
		Vector<String> v = list.get(row);
		// System.out.println(v);
		stPlaceTxt.setText(v.get(2));
		edPlaceTxt.setText(v.get(3));
		dateTxt.setText(v.get(4));
		levelTxt.setText(v.get(5));
		priceTxt.setText(v.get(6));

	}

	/**
	 * 取消订单
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void concelActionPerformed(ActionEvent e) throws Exception {
		int row = OrderTable.getSelectedRow();
		Vector<String> temp = list.get(row);
		frm.cout.writeObject("取消订单");
		frm.cout.flush();
		frm.cout.writeInt(row);
		frm.cout.writeInt(Integer.parseInt(temp.get(7)));
		frm.cout.writeInt(Integer.parseInt(temp.get(8)));
		frm.cout.flush();
		list.remove(row);
	}

	/**
	 * 向服务端发送查询信息
	 * 
	 * @param user
	 * @throws IOException 
	 */
	private void query() throws IOException {
		frm.cout.writeObject("查询订单");
		frm.cout.flush();
		frm.cout.writeObject(frm.user);
	}
	
	/**
	 * 填充表格
	 */
	public void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel) OrderTable.getModel();
		dtm.setRowCount(0); // 清空表格
		
		for(int i = 0; i <list.size(); i++) {
			Vector<String> temp = new Vector<String>(list.get(i));
			dtm.addRow(temp);
		}
		stPlaceTxt.setText("");
		edPlaceTxt.setText("");
		dateTxt.setText("");
		priceTxt.setText("");
		levelTxt.setText("");
	}
}
