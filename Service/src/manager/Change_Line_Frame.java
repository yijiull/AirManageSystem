package manager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Change_Line_Frame extends JFrame{
	
	private JLabel start_text=new JLabel("<html><body><p style=\"font-family:arial;font-size:11px;\">出发地:</p></body></html>",JLabel.RIGHT);
	private JLabel end_text=new JLabel("<html><body><p style=\"font-family:arial;font-size:11px;\">原目的地:</p></body></html>",JLabel.RIGHT);
	private JLabel time_text=new JLabel("<html><body><p style=\"font-family:arial;font-size:11px;\">原出发时间:</p></body></html>",JLabel.RIGHT);
	private JTextField start_textfield=new JTextField();
	private JTextField end_textfield=new JTextField();
	private JTextField time_textfield=new JTextField();
	private JButton search=new JButton("<html><body><p style=\"font-family:arial;font-size:11px;\">搜索</p></body></html>");
	
	private JLabel cstart_text=new JLabel("<html><body><p style=\"font-family:arial;font-size:11px;\">出发地:</p></body></html>",JLabel.RIGHT);
	private JLabel cend_text=new JLabel("<html><body><p style=\"font-family:arial;font-size:11px;\">目的地:</p></body></html>",JLabel.RIGHT);
	private JLabel ctime_text=new JLabel("<html><body><p style=\"font-family:arial;font-size:11px;\">出发时间:</p></body></html>",JLabel.RIGHT);
	private JTextField cstart_textfield=new JTextField();
	private JTextField cend_textfield=new JTextField();
	private JTextField ctime_textfield=new JTextField();
	private JButton change=new JButton("<html><body><p style=\"font-family:arial;font-size:11px;\">修改</p></body></html>");
	Tree tree;
	Change_Line_Frame(String company) throws ClassNotFoundException, IOException
	{
		JOptionPane.showMessageDialog(null, "出发时间填写格式：XXXX-XX-XX,如2018-01-01");
		setSize(420,340); 
		setTitle("修改航班信息");
		setLayout(null);
		setResizable(false);
		
		start_text.setSize(80,55);
		start_text.setLocation(10, 10);
		
		end_text.setSize(80,55);
		end_text.setLocation(10, 85);
	
		time_text.setSize(80,55);
		time_text.setLocation(10, 160);
		
		start_textfield.setSize(80,55);
		start_textfield.setLocation(110, 10);
		
		end_textfield.setSize(80,55);
		end_textfield.setLocation(110, 85);
		
		time_textfield.setSize(80,55);
		time_textfield.setLocation(110, 160);
		
		cstart_text.setSize(80,55);
		cstart_text.setLocation(210, 10);
		
		cend_text.setSize(80,55);
		cend_text.setLocation(210, 85);
		
		ctime_text.setSize(80,55);
		ctime_text.setLocation(210, 160);
		
		cstart_textfield.setSize(80,55);
		cstart_textfield.setLocation(310, 10);
		
		cend_textfield.setSize(80,55);
		cend_textfield.setLocation(310, 85);
		
		ctime_textfield.setSize(80,55);
		ctime_textfield.setLocation(310, 160);
		
		search.setSize(80, 55);
		search.setLocation(60, 235);
		search.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				tree.mark=-1;
				String start=start_textfield.getText();
				String end=end_textfield.getText();
				String time=time_textfield.getText();
				if(time.substring(4, 5).equals("-")==true&&
				   time.substring(7, 8).equals("-")==true&&
				   Integer.parseInt(time.substring(5, 7))>=1&&Integer.parseInt(time.substring(5, 7))<=12&&
				   Integer.parseInt(time.substring(8))>=1&&Integer.parseInt(time.substring(8))<=31&&
				   Integer.parseInt(time.substring(0,4))==2018
				   )
				{
					try {
						Airplane_Line al=null;
						try {
							al = tree.Search(start, end, time);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(al!=null)
						{
							cstart_textfield.setText(al.get_Start_City());
							cend_textfield.setText(al.get_End_City());
							ctime_textfield.setText(al.get_Start_Time());
							cstart_textfield.setEnabled(false);
							JOptionPane.showMessageDialog(null, "查找成功!在右侧界面修改航班信息后点击‘修改’完成修改");
						}else  {
							JOptionPane.showMessageDialog(null, "此航班不存在");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else  {
					JOptionPane.showMessageDialog(null, "输入信息不合法");
				}
			}
				});
		
		change.setSize(80, 55);
		change.setLocation(260, 235);
		change.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				if(tree.mark!=-1)
				{
					String end=cend_textfield.getText();
					String time=ctime_textfield.getText();
					try {
						tree.Change(end,time);
						JOptionPane.showMessageDialog(null, "修改成功!");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else  {
					JOptionPane.showConfirmDialog(null, "无法修改");
				}
			}
				});

		this.addWindowListener(new  java.awt.event.WindowAdapter()
				{
			public void windowClosing(java.awt.event.WindowEvent e) 
			{
				try {
					tree.WriteToTXT();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				});
		add(start_text);
		add(end_text);
		add(time_text);
		add(start_textfield);
		add(end_textfield);
		add(time_textfield);
		add(cstart_text);
		add(cend_text);
		add(ctime_text);
		add(cstart_textfield);
		add(cend_textfield);
		add(ctime_textfield);
		add(search);
		add(change);
		
		tree=new Tree(company);
	}
	//构造函数//
}
