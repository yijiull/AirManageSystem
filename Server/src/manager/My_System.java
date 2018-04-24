package manager;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import view.ServerFrm;

import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import java.awt.*;

class Main_Page extends JFrame {
	JMenuBar MenuBar = new JMenuBar();
	JMenu Home_Page = new JMenu(" 首页");
	JMenu Help = new JMenu(" 帮助");
	JMenu Log_In = new JMenu(" 登录");
	JMenu Register = new JMenu(" 注册");
	JMenu Time_Table = new JMenu(" 航班时刻表");
	JMenu Company_List = new JMenu(" 航空公司列表");
	JMenu City_List = new JMenu(" 城市列表");
	Airplane_Company Login_company = null;
	// 管理系统主页菜单//
	JLabel Help_Label = new JLabel("<html><body>·首页：航空航线管理系统欢迎页<br>" + "·航班时刻表：查看已有的航线信息<br>"
			+ "·航空公司列表：查看已有的航空公司的信息<br>" + "·城市列表：查看所有已开通航线的城市及其信息<br>" + "·登录：已注册的航空公司的登录入口，以便航空公司修改其航空计划或其他信息<br>"
			+ "·注册：未注册航空公司的注册入口<br>" + "·帮助：本航空航线管理系统的操作说明</body></html>");
	Font Help_Label_Font = new Font("微软雅黑", 1, 21);
	// 帮助//
	JLabel Welcome = new JLabel(
			"<html><body>" + "<h1 style='text-align:center'>欢迎使用航空航线管理系统</h1>"
					+ "<h1 style='text-align:center'>Welcome to Airplane Management System</h1>" + "</body></html>",
			JLabel.CENTER);
	// 首页//
	JLabel Name_of_Company_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">公司注册名:</p></body></html>", JLabel.CENTER);
	JTextField Name_of_Company_Textfield = new JTextField();
	JLabel Password_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">密码:</p></body></html>", JLabel.CENTER);
	JPasswordField Password_Textfield = new JPasswordField();
	JLabel Confirm_Password_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">再次输入密码:</p></body></html>", JLabel.CENTER);
	JPasswordField Confirm_Password_Textfield = new JPasswordField();
	JLabel Core_City_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">总部:</p></body></html>", JLabel.CENTER);
	JTextField Core_City_Textfield = new JTextField();
	JLabel Boeing737_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">波音737数量:</p></body></html>", JLabel.CENTER);
	JTextField Boeing737_Textfield = new JTextField();
	JLabel Boeing777_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">波音777数量:</p></body></html>", JLabel.CENTER);
	JTextField Boeing777_Textfield = new JTextField();
	JLabel A380_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">空客A380数量:</p></body></html>", JLabel.CENTER);
	JTextField A380_Textfield = new JTextField();
	JLabel Code_of_Company_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">公司编号:</p></body></html>", JLabel.CENTER);
	JTextField Code_of_Company_Textfield = new JTextField();
	JButton Confirm_Register_button = new JButton(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">注册</p></body></html>");
	JButton Concel_Register_button = new JButton(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">取消</p></body></html>");
	JPanel Name_Panel = new JPanel();
	JPanel Password_Panel = new JPanel();
	JPanel ConfirmPassword_Panel = new JPanel();
	JPanel CoreCity_Panel = new JPanel();
	JPanel Boeing737_Panel = new JPanel();
	JPanel Boeing777_Panel = new JPanel();
	JPanel A380_Panel = new JPanel();
	JPanel Code_of_Company_Panel = new JPanel();
	JPanel Register_Panel = new JPanel();
	// 注册//
	public static int State_of_Login_Var = -1;
	JLabel Login_Company_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">公司账号:</p></body></html>");
	JLabel Login_Password_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">密码:</p></body></html>");
	JTextField Login_Company_Textfield = new JTextField();
	JPasswordField Login_Password_Textfield = new JPasswordField();
	JButton Login_Confirm_Button = new JButton(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">登录</p></body></html>");
	JButton Login_Concel_Button = new JButton(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">取消</p></body></html>");
	JMenuItem Log_Out = new JMenuItem("退出");
	JMenuItem Create_AirplaneLine = new JMenuItem("生成航空计划");
	JMenuItem Change_Line = new JMenuItem("更改航班信息");

	JLabel Logined_Name_of_Company_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">公司注册名:</p></body></html>", JLabel.CENTER);
	JTextField Logined_Name_of_Company_Textfield = new JTextField();

	JLabel Logined_Password_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">密码:</p></body></html>", JLabel.CENTER);
	JPasswordField Logined_Password_Textfield = new JPasswordField();

	JLabel Logined_Core_City_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">总部:</p></body></html>", JLabel.CENTER);
	JTextField Logined_Core_City_Textfield = new JTextField();

	JLabel Logined_Number_of_CityServed_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">服务城市数量:</p></body></html>", JLabel.CENTER);
	JTextField Logined_Number_of_CityServed_Textfield = new JTextField();

	JLabel Logined_Boeing737_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">波音737数量:</p></body></html>", JLabel.CENTER);
	JTextField Logined_Boeing737_Textfield = new JTextField();

	JLabel Logined_Boeing777_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">波音777数量:</p></body></html>", JLabel.CENTER);
	JTextField Logined_Boeing777_Textfield = new JTextField();

	JLabel Logined_A380_Label = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">空客A380数量:</p></body></html>", JLabel.CENTER);
	JTextField Logined_A380_Textfield = new JTextField();

	JPanel Logined_Name_Panel = new JPanel();
	JPanel Logined_Password_Panel = new JPanel();
	JPanel Logined_CoreCity_Panel = new JPanel();
	JPanel Logined_NumberCity_Panel = new JPanel();
	JPanel Logined_Boeing737_Panel = new JPanel();
	JPanel Logined_Boeing777_Panel = new JPanel();
	JPanel Logined_A380_Panel = new JPanel();

	JButton Save_Change_of_BasicInformation = new JButton(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">保存基本资料修改</p></body></html>");
	JButton Add_CityServed = new JButton(
			"<html><body><p style=\"font-family:arial;font-size:16px;\">添加服务城市</p></body></html>");
	JPanel Button_Panel = new JPanel();
	// 登录//
	JMenuItem City_List_LevelSorting = new JMenuItem("按城市客运需求等级排序");
	JMenuItem City_List_RankSorting = new JMenuItem("按序号排序");
	public int City_List_Rank_mark = 0;
	public int number_of_city = 0;
	JLabel Hips_of_City = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:20px;\">没有城市信息</p></body></html>", JLabel.CENTER);
	JTable List_of_City_JTable = new JTable(new AbstractTableModel() {
		private String columnName[] = { "城市序号", "城市名", "经度", "纬度", "客运需求等级" };

		public String getColumnName(int column) {
			return columnName[column];
		}

		public int getColumnCount() {
			return 5;
		}

		public int getRowCount() {
			try {
				RandomAccessFile List_of_City_raf = new RandomAccessFile("src\\managerfile\\Number_of_City.txt", "r");
				try {
					if (List_of_City_raf.length() != 0) {
						try {
							number_of_city = List_of_City_raf.readInt();
							List_of_City_raf.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						number_of_city = 0;
						List_of_City_raf.close();
						return number_of_city;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			number_of_city = number_of_city - 1;
			return number_of_city;
		}

		public Object getValueAt(int row, int col) {
			int number = 0;
			City[] list = null;
			try {
				FileInputStream List_of_City_getValue_stream = new FileInputStream(
						"src\\managerfile\\List_of_City.txt");
				try {
					ObjectInputStream List_of_City_getValue_reader = new ObjectInputStream(
							List_of_City_getValue_stream);
					RandomAccessFile raf = new RandomAccessFile("src\\managerfile\\Number_of_City.txt", "r");
					number = raf.readInt() - 1;
					list = new City[number];
					int i = 0;
					for (i = 0; i <= number - 1; i++) {
						try {
							list[i] = (City) List_of_City_getValue_reader.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					List_of_City_getValue_reader.close();
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (City_List_Rank_mark == 0) {
				if (col == 0) {
					return list[row].getKey_of_City();
				} else if (col == 1) {
					return list[row].getName_of_City();
				} else if (col == 2) {
					return list[row].getLocation_E();
				} else if (col == 3) {
					return list[row].getLocation_N();
				} else if (col == 4) {
					return list[row].getLevel_of_City();
				} else {
					return 0;
				}
			} else if (City_List_Rank_mark == 1) {
				City[] list_of_Levelone = new City[number];
				City[] list_of_Leveltwo = new City[number];
				City[] list_of_Levelthree = new City[number];
				int one = 0;
				int two = 0;
				int three = 0;
				int j = 0;
				for (j = 0; j <= number - 1; j++) {
					if (list[j].getLevel_of_City() == 1) {
						list_of_Levelone[one] = list[j];
						one++;
					}
				}
				for (j = 0; j <= number - 1; j++) {
					if (list[j].getLevel_of_City() == 2) {
						list_of_Leveltwo[two] = list[j];
						two++;
					}
				}
				for (j = 0; j <= number - 1; j++) {
					if (list[j].getLevel_of_City() == 3) {
						list_of_Levelthree[three] = list[j];
						three++;
					}
				}
				City[] listRank = new City[number];
				for (j = 0; j <= one - 1; j++) {
					listRank[j] = list_of_Levelone[j];
				}
				for (j = 0; j <= two - 1; j++) {
					listRank[j + one] = list_of_Leveltwo[j];
				}
				for (j = 0; j <= three - 1; j++) {
					listRank[j + one + two] = list_of_Levelthree[j];
				}
				if (col == 0) {
					return listRank[row].getKey_of_City();
				} else if (col == 1) {
					return listRank[row].getName_of_City();
				} else if (col == 2) {
					return listRank[row].getLocation_E();
				} else if (col == 3) {
					return listRank[row].getLocation_N();
				} else if (col == 4) {
					return listRank[row].getLevel_of_City();
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}

	});
	// 城市列表//

	JMenuItem Company_List_RankSorting = new JMenuItem("根据序号排序");
	JMenuItem Company_List_CSSorting = new JMenuItem("根据服务城市数量排序");
	JMenuItem Company_List_737Sorting = new JMenuItem("根据波音737数量排序");
	JMenuItem Company_List_777Sorting = new JMenuItem("根据波音777数量排序");
	JMenuItem Company_List_A380Sorting = new JMenuItem("根据A380数量排序");
	public int Company_List_Rank_mark = 0;
	public int number_of_company = 0;

	void swap(Airplane_Company[] E, int i, int j) {
		Airplane_Company temp = null;
		temp = E[i];
		E[i] = E[j];
		E[j] = temp;
	};

	void CS_inssort(Airplane_Company[] A, int n) {
		for (int i = 1; i < n; i++) {
			for (int j = i; (j > 0) && (A[j].getNumber_of_CityServed() < A[j - 1].getNumber_of_CityServed()); j--) {
				swap(A, j, j - 1);
			}
		}
	};

	void STS_inssort(Airplane_Company[] A, int n) {
		for (int i = 1; i < n; i++) {
			for (int j = i; (j > 0) && (A[j].getBoeing737() < A[j - 1].getBoeing737()); j--) {
				swap(A, j, j - 1);
			}
		}
	};

	void SSS_inssort(Airplane_Company[] A, int n) {
		for (int i = 1; i < n; i++) {
			for (int j = i; (j > 0) && (A[j].getBoeing777() < A[j - 1].getBoeing777()); j--) {
				swap(A, j, j - 1);
			}
		}
	};

	void A380_inssort(Airplane_Company[] A, int n) {
		for (int i = 1; i < n; i++) {
			for (int j = i; (j > 0) && (A[j].getA380() < A[j - 1].getA380()); j--) {
				swap(A, j, j - 1);
			}
		}
	};

	JTable List_of_Company_JTable = new JTable(new AbstractTableModel() {
		private String columnName[] = { "航空公司序号", "航空公司编号", "航空公司名", "公司总部", "服务城市数", "波音737数量", "波音777数量", "A380数量" };

		public String getColumnName(int column) {
			return columnName[column];
		}

		public int getColumnCount() {
			return 8;
		}

		public int getRowCount() {
			try {
				RandomAccessFile raf = new RandomAccessFile("src\\managerfile\\Number_of_Company.txt", "r");
				try {
					if (raf.length() != 0) {
						number_of_company = raf.readInt();
					} else {
						number_of_company = 0;
					}
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return number_of_company - 1;
		}

		public Object getValueAt(int row, int col) {
			int number = 0;
			try {
				RandomAccessFile raf = new RandomAccessFile("src\\managerfile\\Number_of_Company.txt", "r");
				try {
					number = raf.readInt();
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			number = number - 1;
			ObjectInputStream ois = null;
			try {
				FileInputStream fis = new FileInputStream("src\\managerfile\\List_of_Company.txt");
				try {
					ois = new ObjectInputStream(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Airplane_Company[] list = new Airplane_Company[number];
			int i = 0;
			for (i = 0; i <= number - 1; i++) {
				try {
					list[i] = (Airplane_Company) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Company_List_Rank_mark == 0) {

			} else if (Company_List_Rank_mark == 1) {
				CS_inssort(list, number);
			} else if (Company_List_Rank_mark == 2) {
				STS_inssort(list, number);
			} else if (Company_List_Rank_mark == 3) {
				SSS_inssort(list, number);
			} else if (Company_List_Rank_mark == 4) {
				A380_inssort(list, number);
			}
			if (col == 0) {
				return list[row].getKey_of_Company();
			} else if (col == 1) {
				return list[row].getCode_of_Company();
			} else if (col == 2) {
				return list[row].getName_of_Company();
			} else if (col == 3) {
				return list[row].getCore_City();
			} else if (col == 4) {
				return list[row].getNumber_of_CityServed();
			} else if (col == 5) {
				return list[row].getBoeing737();
			} else if (col == 6) {
				return list[row].getBoeing777();
			} else if (col == 7) {
				return list[row].getA380();
			} else {
				return 0;
			}
		}
	});
	JMenuItem Root_of_Browse_City_of_Company = new JMenuItem("查看各个公司的服务城市");
	JLabel Hips_of_Company = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:20px;\">没有航空公司信息</p></body></html>", JLabel.CENTER);
	// 航空公司列表//
	Airplane_Line[] airplane_line = null;
	public int number_of_line = 0;
	JLabel Hips_of_Line = new JLabel(
			"<html><body><p style=\"font-family:arial;font-size:20px;\">没有航班信息</p></body></html>", JLabel.CENTER);
	JPanel Main_Panel = new JPanel();

	// 主容器//
	void readNumberofCity() {
		try {
			RandomAccessFile List_of_City_raf = new RandomAccessFile("src\\managerfile\\Number_of_City.txt", "r");
			try {
				if (List_of_City_raf.length() != 0) {
					try {
						number_of_city = List_of_City_raf.readInt();
						number_of_city = number_of_city - 1;
						List_of_City_raf.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					number_of_city = 0;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void readNumberofCompany() {
		try {
			RandomAccessFile raf = new RandomAccessFile("src\\managerfile\\Number_of_Company.txt", "r");
			try {
				if (raf.length() != 0) {
					number_of_company = raf.readInt();
					number_of_company = number_of_company - 1;
				} else {
					number_of_company = 0;
				}
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServerFrm frm;

	public Main_Page(ServerFrm frm) {
		this.frm = frm;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frm.StopService();
				frm.dispose();
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 550);
		setLocation(300, 80);
		setTitle("航空航线管理系统");
		// 整体界面大小、位置、标题设置//
		Main_Panel.setSize(780, 500);
		Main_Panel.setLocation(10, 50);
		// 设置主容器//
		Help.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				Main_Panel.removeAll();
				Main_Panel.setLayout(new GridLayout(1, 1));
				Help_Label.setFont(Help_Label_Font);
				Main_Panel.add(Help_Label);
				Main_Panel.revalidate();
				Main_Panel.repaint();
			}
		});
		// “帮助”的按键事件//

		Home_Page.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				Main_Panel.removeAll();
				Main_Panel.setLayout(new GridLayout(1, 1));
				Main_Panel.add(Welcome);
				Main_Panel.revalidate();
				Main_Panel.repaint();
			}
		});
		// “首页”的按键事件//
		Register.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				Main_Panel.removeAll();
				Main_Panel.setLayout(new GridLayout(9, 1));

				Name_Panel.setLayout(new GridLayout(1, 2));
				Name_Panel.add(Name_of_Company_Label);
				Name_Panel.add(Name_of_Company_Textfield);

				Password_Panel.setLayout(new GridLayout(1, 2));
				Password_Panel.add(Password_Label);
				Password_Panel.add(Password_Textfield);

				ConfirmPassword_Panel.setLayout(new GridLayout(1, 2));
				ConfirmPassword_Panel.add(Confirm_Password_Label);
				ConfirmPassword_Panel.add(Confirm_Password_Textfield);

				CoreCity_Panel.setLayout(new GridLayout(1, 2));
				CoreCity_Panel.add(Core_City_Label);
				CoreCity_Panel.add(Core_City_Textfield);

				Boeing737_Panel.setLayout(new GridLayout(1, 2));
				Boeing737_Panel.add(Boeing737_Label);
				Boeing737_Panel.add(Boeing737_Textfield);

				Boeing777_Panel.setLayout(new GridLayout(1, 2));
				Boeing777_Panel.add(Boeing777_Label);
				Boeing777_Panel.add(Boeing777_Textfield);

				A380_Panel.setLayout(new GridLayout(1, 2));
				A380_Panel.add(A380_Label);
				A380_Panel.add(A380_Textfield);

				Code_of_Company_Panel.setLayout(new GridLayout(1, 2));
				Code_of_Company_Panel.add(Code_of_Company_Label);
				Code_of_Company_Panel.add(Code_of_Company_Textfield);

				Register_Panel.setLayout(new GridLayout(1, 2));
				Register_Panel.add(Confirm_Register_button);
				Register_Panel.add(Concel_Register_button);

				Main_Panel.add(Name_Panel);
				Main_Panel.add(Password_Panel);
				Main_Panel.add(ConfirmPassword_Panel);
				Main_Panel.add(CoreCity_Panel);
				Main_Panel.add(Boeing737_Panel);
				Main_Panel.add(Boeing777_Panel);
				Main_Panel.add(A380_Panel);
				Main_Panel.add(Code_of_Company_Panel);
				Main_Panel.add(Register_Panel);
				Main_Panel.revalidate();
				Main_Panel.repaint();
			}
		});

		Concel_Register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Name_of_Company_Textfield.setText(null);
				Password_Textfield.setText(null);
				Confirm_Password_Textfield.setText(null);
				Core_City_Textfield.setText(null);
				Boeing737_Textfield.setText(null);
				Boeing777_Textfield.setText(null);
				A380_Textfield.setText(null);
				Code_of_Company_Textfield.setText(null);
			}
		});

		Confirm_Register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Name_of_Company_Textfield.getText().length() == 0
						|| new String(Password_Textfield.getPassword()).length() == 0
						|| new String(Confirm_Password_Textfield.getPassword()).length() == 0
						|| Core_City_Textfield.getText().length() == 0 || Boeing737_Textfield.getText().length() == 0
						|| Boeing777_Textfield.getText().length() == 0 || A380_Textfield.getText().length() == 0
						|| Code_of_Company_Textfield.getText().length() == 0)
						|| (new String(Password_Textfield.getPassword())
								.equals(new String(Confirm_Password_Textfield.getPassword())) == false)) {
					JOptionPane.showMessageDialog(null, "请确保所有信息填写完整并且密码输入正确!");
				} else {
					try {
						RandomAccessFile Number_of_Company_out = new RandomAccessFile(
								"src\\managerfile\\Number_of_Company.txt", "rw");
						RandomAccessFile Number_of_Company_in = new RandomAccessFile(
								"src\\managerfile\\Number_of_Company.txt", "r");
						int number = 0;
						long l;
						try {
							l = Number_of_Company_out.length();
							if (l == 0) {
								number = 1;
								int temp = number + 1;
								Number_of_Company_out.writeInt(temp);
								Number_of_Company_out.close();
								Number_of_Company_in.close();
							} else {
								number = Number_of_Company_in.readInt();
								int temp = number + 1;
								Number_of_Company_out.writeInt(temp);
								Number_of_Company_out.close();
								Number_of_Company_in.close();
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// 利用文件记录注册的公司数目,文件里面是实际注册的公司数目+1//
						Airplane_Company company = new Airplane_Company(number, Name_of_Company_Textfield.getText(),
								new String(Password_Textfield.getPassword()), 0, Core_City_Textfield.getText(),
								Integer.parseInt(Boeing737_Textfield.getText()),
								Integer.parseInt(Boeing777_Textfield.getText()),
								Integer.parseInt(A380_Textfield.getText()), Code_of_Company_Textfield.getText());
						ObjectOutputStream LoCout = null;
						File file = new File("src\\managerfile\\List_of_Company.txt");
						FileOutputStream fout = new FileOutputStream("src\\managerfile\\List_of_Company.txt", true);
						if (file.length() < 1) {
							try {
								LoCout = new ObjectOutputStream(fout);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							try {
								LoCout = new MyObjectOutputStream(fout);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						// 根据文件是否第一次操作选择串行化的类//
						try {
							LoCout.writeObject(company);
							LoCout.close();
							// 写入注册航空公司//
							Name_of_Company_Textfield.setText(null);
							Password_Textfield.setText(null);
							Confirm_Password_Textfield.setText(null);
							Core_City_Textfield.setText(null);
							Boeing737_Textfield.setText(null);
							Boeing777_Textfield.setText(null);
							A380_Textfield.setText(null);
							Code_of_Company_Textfield.setText(null);
							// 清空输入框//
							File Register_Company_City_List = new File(
									"src\\managerfile\\List_of_City_of_" + company.getName_of_Company() + ".txt");
							Register_Company_City_List.createNewFile();
							Register_Company_City_List = null;
							JOptionPane.showMessageDialog(null, "注册成功!");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		// "注册"的按键事件//

		Log_In.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (State_of_Login_Var == -1) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(null);
					Login_Company_Label.setSize(100, 40);
					Login_Password_Label.setSize(100, 40);
					Login_Company_Textfield.setSize(280, 40);
					Login_Password_Textfield.setSize(280, 40);
					Login_Confirm_Button.setSize(80, 40);
					Login_Concel_Button.setSize(80, 40);
					Login_Company_Label.setLocation(190, 170);
					Login_Password_Label.setLocation(190, 230);
					Login_Company_Textfield.setLocation(310, 170);
					Login_Password_Textfield.setLocation(310, 230);
					Login_Confirm_Button.setLocation(300, 290);
					Login_Concel_Button.setLocation(400, 290);
					// 设置位置//
					Main_Panel.add(Login_Company_Label);
					Main_Panel.add(Login_Password_Label);
					Main_Panel.add(Login_Company_Textfield);
					Main_Panel.add(Login_Password_Textfield);
					Main_Panel.add(Login_Confirm_Button);
					Main_Panel.add(Login_Concel_Button);
					// 加入控件//
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new GridLayout(8, 1));

					Logined_Name_Panel.setLayout(new GridLayout(1, 2));
					Logined_Name_of_Company_Textfield.setText(Login_company.getName_of_Company());
					Logined_Name_Panel.add(Logined_Name_of_Company_Label);
					Logined_Name_Panel.add(Logined_Name_of_Company_Textfield);

					Logined_Password_Panel.setLayout(new GridLayout(1, 2));
					Logined_Password_Textfield.setText(Login_company.getPassword());
					Logined_Password_Panel.add(Logined_Password_Label);
					Logined_Password_Panel.add(Logined_Password_Textfield);

					Logined_CoreCity_Panel.setLayout(new GridLayout(1, 2));
					Logined_Core_City_Textfield.setText(Login_company.getCore_City());
					Logined_CoreCity_Panel.add(Logined_Core_City_Label);
					Logined_CoreCity_Panel.add(Logined_Core_City_Textfield);

					Logined_NumberCity_Panel.setLayout(new GridLayout(1, 2));
					Logined_Number_of_CityServed_Textfield
							.setText(Integer.toString(Login_company.getNumber_of_CityServed()));
					Logined_NumberCity_Panel.add(Logined_Number_of_CityServed_Label);
					Logined_NumberCity_Panel.add(Logined_Number_of_CityServed_Textfield);

					Logined_Boeing737_Panel.setLayout(new GridLayout(1, 2));
					Logined_Boeing737_Textfield.setText(Integer.toString(Login_company.getBoeing737()));
					Logined_Boeing737_Panel.add(Logined_Boeing737_Label);
					Logined_Boeing737_Panel.add(Logined_Boeing737_Textfield);

					Logined_Boeing777_Panel.setLayout(new GridLayout(1, 2));
					Logined_Boeing777_Textfield.setText(Integer.toString(Login_company.getBoeing777()));
					Logined_Boeing777_Panel.add(Logined_Boeing777_Label);
					Logined_Boeing777_Panel.add(Logined_Boeing777_Textfield);

					Logined_A380_Panel.setLayout(new GridLayout(1, 2));
					Logined_A380_Textfield.setText(Integer.toString(Login_company.getA380()));
					Logined_A380_Panel.add(Logined_A380_Label);
					Logined_A380_Panel.add(Logined_A380_Textfield);

					Button_Panel.setLayout(new GridLayout(1, 2));
					Button_Panel.add(Save_Change_of_BasicInformation);
					Button_Panel.add(Add_CityServed);

					Main_Panel.add(Logined_Name_Panel);
					Main_Panel.add(Logined_Password_Panel);
					Main_Panel.add(Logined_CoreCity_Panel);
					Main_Panel.add(Logined_NumberCity_Panel);
					Main_Panel.add(Logined_Boeing737_Panel);
					Main_Panel.add(Logined_Boeing777_Panel);
					Main_Panel.add(Logined_A380_Panel);
					Main_Panel.add(Button_Panel);

					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
				setResizable(false);
			}
		});

		Login_Confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Login_Company_Textfield.getText().length() == 0
						|| new String(Login_Password_Textfield.getPassword()).length() == 0) {
					JOptionPane.showMessageDialog(null, "请确保信息填写完整!");
				} else {
					File file = new File("src\\managerfile\\List_of_Company.txt");
					if (file.length() == 0) {
						JOptionPane.showMessageDialog(null, "此用户未注册!");
					} else {
						try {
							FileInputStream Login_File_reader = new FileInputStream(file);
							try {
								ObjectInputStream Login_reader = new ObjectInputStream(Login_File_reader);
								RandomAccessFile Login_number_reader = new RandomAccessFile(
										"src\\managerfile\\Number_of_Company.txt", "rw");
								int Login_number = Login_number_reader.readInt();
								Login_number_reader.close();
								int i;
								for (i = 1; i <= Login_number - 1; i++) {
									try {
										Login_company = (Airplane_Company) Login_reader.readObject();
										if (Login_company.getName_of_Company()
												.equals(Login_Company_Textfield.getText()) == true) {
											break;
										}
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
								Login_reader.close();
								// 找到名字相同的公司//
								if (i == Login_number) {
									JOptionPane.showMessageDialog(null, "此用户未注册!");
									// 没有找到相同名字的//
								} else {
									if (Login_company.getPassword()
											.equals(new String(Login_Password_Textfield.getPassword())) == false) {
										JOptionPane.showMessageDialog(null, "密码错误!");
										// 密码错误//
									} else {
										Main_Panel.removeAll();
										JOptionPane.showMessageDialog(null, "登录成功!");
										Login_Company_Textfield.setText(null);
										Login_Password_Textfield.setText(null);
										State_of_Login_Var = 1;
										// 改为已有用户登录的状态//
										Log_In.setText(Login_company.getName_of_Company());
										RandomAccessFile raf = new RandomAccessFile("src\\managerfile\\mark_of_"
												+ Login_company.getName_of_Company() + ".txt", "rw");
										if (raf.length() == 0L) {
											Log_In.add(Create_AirplaneLine);
										} else {
											Log_In.add(Change_Line);
										}
										raf.close();
										Log_In.add(Log_Out);
										// 加入退出,更改航班信息按键//
										Main_Panel.setLayout(new GridLayout(8, 1));

										Logined_Name_Panel.setLayout(new GridLayout(1, 2));
										Logined_Name_of_Company_Textfield.setText(Login_company.getName_of_Company());
										Logined_Name_Panel.add(Logined_Name_of_Company_Label);
										Logined_Name_Panel.add(Logined_Name_of_Company_Textfield);

										Logined_Password_Panel.setLayout(new GridLayout(1, 2));
										Logined_Password_Textfield.setText(Login_company.getPassword());
										Logined_Password_Panel.add(Logined_Password_Label);
										Logined_Password_Panel.add(Logined_Password_Textfield);

										Logined_CoreCity_Panel.setLayout(new GridLayout(1, 2));
										Logined_Core_City_Textfield.setText(Login_company.getCore_City());
										Logined_CoreCity_Panel.add(Logined_Core_City_Label);
										Logined_CoreCity_Panel.add(Logined_Core_City_Textfield);

										Logined_NumberCity_Panel.setLayout(new GridLayout(1, 2));
										Logined_Number_of_CityServed_Textfield
												.setText(Integer.toString(Login_company.getNumber_of_CityServed()));
										Logined_NumberCity_Panel.add(Logined_Number_of_CityServed_Label);
										Logined_NumberCity_Panel.add(Logined_Number_of_CityServed_Textfield);

										Logined_Boeing737_Panel.setLayout(new GridLayout(1, 2));
										Logined_Boeing737_Textfield
												.setText(Integer.toString(Login_company.getBoeing737()));
										Logined_Boeing737_Panel.add(Logined_Boeing737_Label);
										Logined_Boeing737_Panel.add(Logined_Boeing737_Textfield);

										Logined_Boeing777_Panel.setLayout(new GridLayout(1, 2));
										Logined_Boeing777_Textfield
												.setText(Integer.toString(Login_company.getBoeing777()));
										Logined_Boeing777_Panel.add(Logined_Boeing777_Label);
										Logined_Boeing777_Panel.add(Logined_Boeing777_Textfield);

										Logined_A380_Panel.setLayout(new GridLayout(1, 2));
										Logined_A380_Textfield.setText(Integer.toString(Login_company.getA380()));
										Logined_A380_Panel.add(Logined_A380_Label);
										Logined_A380_Panel.add(Logined_A380_Textfield);

										Button_Panel.setLayout(new GridLayout(1, 2));
										Button_Panel.add(Save_Change_of_BasicInformation);
										Button_Panel.add(Add_CityServed);

										Main_Panel.add(Logined_Name_Panel);
										Main_Panel.add(Logined_Password_Panel);
										Main_Panel.add(Logined_CoreCity_Panel);
										Main_Panel.add(Logined_NumberCity_Panel);
										Main_Panel.add(Logined_Boeing737_Panel);
										Main_Panel.add(Logined_Boeing777_Panel);
										Main_Panel.add(Logined_A380_Panel);
										Main_Panel.add(Button_Panel);

										Main_Panel.revalidate();
										Main_Panel.repaint();
									}
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});

		Login_Concel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Company_Textfield.setText(null);
				Login_Password_Textfield.setText(null);
			}
		});

		Log_Out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State_of_Login_Var = -1;
				Login_company = null;
				// 改为未登录状态//
				JOptionPane.showMessageDialog(null, "退出成功!");
				Main_Panel.removeAll();
				Main_Panel.setLayout(null);
				Login_Company_Label.setSize(100, 40);
				Login_Password_Label.setSize(100, 40);
				Login_Company_Textfield.setSize(280, 40);
				Login_Password_Textfield.setSize(280, 40);
				Login_Confirm_Button.setSize(80, 40);
				Login_Concel_Button.setSize(80, 40);
				Login_Company_Label.setLocation(190, 170);
				Login_Password_Label.setLocation(190, 230);
				Login_Company_Textfield.setLocation(310, 170);
				Login_Password_Textfield.setLocation(310, 230);
				Login_Confirm_Button.setLocation(300, 290);
				Login_Concel_Button.setLocation(400, 290);
				// 设置位置//
				Main_Panel.add(Login_Company_Label);
				Main_Panel.add(Login_Password_Label);
				Main_Panel.add(Login_Company_Textfield);
				Main_Panel.add(Login_Password_Textfield);
				Main_Panel.add(Login_Confirm_Button);
				Main_Panel.add(Login_Concel_Button);
				Log_In.setText("登录");
				Log_In.remove(Change_Line);
				Log_In.remove(Create_AirplaneLine);
				Log_In.remove(Log_Out);
				// 加入控件//
				Main_Panel.revalidate();
				Main_Panel.repaint();
			}
		});

		Change_Line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Change_Line_Frame change_line;
				try {
					change_line = new Change_Line_Frame(Login_company.getName_of_Company());
					change_line.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Create_AirplaneLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				class CreateHelper {
					double[][] Matrix;
					City[] city;
					int amount;
					int base = 0;
					int previous;

					void buildMap(String t) throws IOException {
						int i = 0;
						int j = 0;
						int number_cityserved = 0;
						number_cityserved = Login_company.getNumber_of_CityServed();
						Matrix = new double[number_cityserved][number_cityserved];
						for (i = 0; i <= number_cityserved - 1; i++) {
							for (j = 0; j <= number_cityserved - 1; j++) {
								Matrix[i][j] = 0;
							}
						}
						// 初始化Matrix//
						city = new City[number_cityserved];
						FileInputStream city_fis = new FileInputStream(
								"src//managerfile//List_of_City_of_" + t + ".txt");
						ObjectInputStream city_ois = new ObjectInputStream(city_fis);
						for (i = 0; i <= number_cityserved - 1; i++) {
							try {
								city[i] = (City) city_ois.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						city_ois.close();
						// 城市读进数组中//
						for (i = 0; i <= number_cityserved - 1; i++) {
							if (city[i].getLevel_of_City() != 3) {
								for (j = 0; j <= number_cityserved - 1; j++) {
									if (i != j) {
										if (city[j].getLevel_of_City() != 3) {
											double x1 = city[i].getLocation_E();
											double y1 = city[i].getLocation_N();
											double x2 = city[j].getLocation_E();
											double y2 = city[j].getLocation_N();
											if (Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) >= 2.2) {
												Matrix[i][j] = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
											}
										}
									}
								}
							} else {
								double[] distance = new double[number_cityserved];
								for (int n = 0; n <= number_cityserved - 1; n++) {
									distance[n] = 0;
								}
								for (int n = 0; n <= number_cityserved - 1; n++) {
									if (city[n].getLevel_of_City() != 3 && n != i) {
										double x1 = city[i].getLocation_E();
										double y1 = city[i].getLocation_N();
										double x2 = city[n].getLocation_E();
										double y2 = city[n].getLocation_N();
										distance[n] = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
									}
								}
								double min = 999999999;
								int mark = 0;
								double tooclose = 2.2;
								for (int n = 0; n <= number_cityserved - 1; n++) {
									if (distance[n] <= min && distance[n] >= tooclose) {
										min = distance[n];
										mark = n;
									}
								}
								Matrix[i][mark] = min;
								Matrix[mark][i] = min;
							}
						}
					}

					// 生成矩阵//
					int getNextOneConnected(int head, int previous) {
						while (true) {
							if (city[(previous + 1) % Login_company.getNumber_of_CityServed()].getLevel_of_City() == 1
									&& (previous + 1) % Login_company.getNumber_of_CityServed() != head
									&& Matrix[head][(previous + 1) % Login_company.getNumber_of_CityServed()] != 0) {
								return (previous + 1) % Login_company.getNumber_of_CityServed();
							}
							previous++;
						}
					}

					int getNextOne(int head, int previous) {
						while (true) {
							if (city[(previous + 1) % Login_company.getNumber_of_CityServed()].getLevel_of_City() == 1
									&& (previous + 1) % Login_company.getNumber_of_CityServed() != head) {
								return (previous + 1) % Login_company.getNumber_of_CityServed();
							}
							previous++;
						}
					}

					int getNextThree(int head, int previous) {
						while (true) {
							if (city[(previous + 1) % Login_company.getNumber_of_CityServed()].getLevel_of_City() == 3
									&& (previous + 1) % Login_company.getNumber_of_CityServed() != head
									&& Matrix[head][(previous + 1) % Login_company.getNumber_of_CityServed()] != 0) {
								return (previous + 1) % Login_company.getNumber_of_CityServed();
							}
							previous++;
						}
					}

					int getNextOneorTwoConnected(int head, int previous) {
						while (true) {
							if ((city[(previous + 1) % Login_company.getNumber_of_CityServed()].getLevel_of_City() == 1
									|| city[(previous + 1) % Login_company.getNumber_of_CityServed()]
											.getLevel_of_City() == 2)
									&& (previous + 1) % Login_company.getNumber_of_CityServed() != head
									&& Matrix[head][(previous + 1) % Login_company.getNumber_of_CityServed()] != 0) {
								return (previous + 1) % Login_company.getNumber_of_CityServed();
							}
							previous++;
						}
					}

					int getNextOneorTwo(int head, int previous) {
						while (true) {
							if ((city[(previous + 1) % Login_company.getNumber_of_CityServed()].getLevel_of_City() == 1
									|| city[(previous + 1) % Login_company.getNumber_of_CityServed()]
											.getLevel_of_City() == 2)
									&& (previous + 1) % Login_company.getNumber_of_CityServed() != head) {
								return (previous + 1) % Login_company.getNumber_of_CityServed();
							}
							previous++;
						}
					}

					int getConnected(int t) {
						if (city[t].getLevel_of_City() != 3) {
							return -1;
						} else {
							for (int i = 0; i <= Login_company.getNumber_of_CityServed() - 1; i++) {
								if (Matrix[t][i] != 0) {
									return i;
								}
							}
							return -1;
						}
					}

					int getNumber_of_three(int t) {
						int n = 0;
						if (city[t].getLevel_of_City() == 3) {
							return -1;
						} else {
							for (int i = 0; i <= Login_company.getNumber_of_CityServed() - 1; i++) {
								if (city[i].getLevel_of_City() == 3 && Matrix[t][i] != 0) {
									n++;
								}
							}
							return n;
						}
					}

					void CreateLine() throws IOException {
						int i = 0;
						Calendar first_time = Calendar.getInstance();
						first_time.set(2018, 0, 1, 0, 0);
						Calendar last_time = Calendar.getInstance();
						last_time.set(2018, 5, 30, 23, 59);
						// 航空计划开始的时间为2018.1.1.0.0//
						int t = 0;
						int counter = 0;
						for (int n = 0; n <= Login_company.getNumber_of_CityServed() - 1; n++) {
							if (city[n].getLevel_of_City() == 1) {
								counter++;
							}
						}

						for (i = 0; i <= Login_company.getA380() - 1; i++) {
							Calendar truetime = Calendar.getInstance();
							truetime.setTime(first_time.getTime());
							truetime.add(Calendar.MINUTE, (i / counter) * 300);
							if (i == 0) {
								base = i;
							} else {
								base = getNextOne(base, base);
							}
							previous = -1;
							if (t == 0 && city[0].getLevel_of_City() != 1) {
								t = getNextOneConnected(0, -1);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(0, getNextOneConnected(0, -1), "A380", truetime,
										Login_company.getName_of_Company());
								truetime.add(Calendar.MINUTE, amount + 300);
								base = t;
							}
							// 如果总部不是一级城市//
							while (truetime.before(last_time) == true) {
								previous = getNextOneConnected(base, previous);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(base, previous, "A380", truetime, Login_company.getName_of_Company());
								truetime.add(Calendar.MINUTE, amount + 300);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(previous, base, "A380", truetime, Login_company.getName_of_Company());
								truetime.add(Calendar.MINUTE, amount + 300);
							}
						}
						// A380//
						i = 0;
						base = 0;
						previous = -1;
						for (int n = 0; n <= Login_company.getNumber_of_CityServed() - 1; n++) {
							if (city[n].getLevel_of_City() == 1 || city[n].getLevel_of_City() == 2) {
								counter++;
							}
						}
						for (i = 0; i <= Login_company.getBoeing777() - 1; i++) {
							Calendar truetime = Calendar.getInstance();
							truetime.setTime(first_time.getTime());
							truetime.add(Calendar.MINUTE, (i / counter) * 30);
							if (i == 0) {
								base = i;
								truetime.add(Calendar.MINUTE, 10);
							} else {
								base = getNextOneorTwo(base, base);
							}
							previous = -1;
							if (t == 0 && city[0].getLevel_of_City() == 3) {
								t = getNextOneorTwoConnected(0, -1);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(0, t, "777", truetime, Login_company.getName_of_Company());
								truetime.add(Calendar.MINUTE, amount + 300);
								base = t;
							}
							// 如果总部是3级城市//
							while (truetime.before(last_time) == true) {
								previous = getNextOneorTwoConnected(base, previous);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(base, previous, "777", truetime, Login_company.getName_of_Company());
								truetime.add(Calendar.MINUTE, amount + 300);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(previous, base, "777", truetime, Login_company.getName_of_Company());
								truetime.add(Calendar.MINUTE, amount + 300);
							}

						}
						// 777//
						base = 0;
						previous = -1;
						for (i = 0; i <= Login_company.getBoeing737() - 1; i++) {
							if (city[0].getLevel_of_City() == 3) {
								int connect_zero = getConnected(0);
								Calendar truetime = Calendar.getInstance();
								truetime.setTime(first_time.getTime());
								truetime.add(Calendar.MINUTE, (i) * 30);
								if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
										&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
									truetime.set(Calendar.HOUR_OF_DAY, 8);
								}
								Initial(0, connect_zero, "737", truetime, Login_company.getName_of_Company());
								// 进入主线网络//
								base = connect_zero;
								counter = 0;
								int n = getNumber_of_three(base);
								while (truetime.before(last_time) == true && counter <= (n - 1)) {
									previous = getNextThree(base, previous);
									if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
											&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
										truetime.set(Calendar.HOUR_OF_DAY, 8);
									}
									Initial(base, previous, "737", truetime, Login_company.getName_of_Company());
									truetime.add(Calendar.MINUTE, amount + 300);
									if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
											&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
										truetime.set(Calendar.HOUR_OF_DAY, 8);
									}
									Initial(previous, base, "737", truetime, Login_company.getName_of_Company());
									truetime.add(Calendar.MINUTE, amount + 300);
									counter++;
								}
								////
								while (truetime.before(last_time) == true) {
									int next = getNextOneorTwo(base, base);
									while (getNumber_of_three(next) == 0) {
										next = getNextOneorTwo(next, next);
									}
									base = next;
									previous = -1;
									counter = 0;
									while (truetime.before(last_time) == true
											&& counter <= getNumber_of_three(base) - 1) {
										previous = getNextThree(base, previous);
										if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
												&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
											truetime.set(Calendar.HOUR_OF_DAY, 8);
										}
										Initial(base, previous, "737", truetime, Login_company.getName_of_Company());
										truetime.add(Calendar.MINUTE, amount + 300);
										if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
												&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
											truetime.set(Calendar.HOUR_OF_DAY, 8);
										}
										Initial(previous, base, "737", truetime, Login_company.getName_of_Company());
										truetime.add(Calendar.MINUTE, amount + 300);
										counter++;
									}
								}
							} else {
								Calendar truetime = Calendar.getInstance();
								truetime.setTime(first_time.getTime());
								truetime.add(Calendar.MINUTE, (i) * 30);
								if (getNumber_of_three(0) != 0) {
									int n = getNumber_of_three(0);
									counter = 0;
									while (truetime.before(last_time) == true && counter <= (n - 1)) {
										previous = getNextThree(base, previous);
										if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
												&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
											truetime.set(Calendar.HOUR_OF_DAY, 8);
										}
										Initial(base, previous, "737", truetime, Login_company.getName_of_Company());
										truetime.add(Calendar.MINUTE, amount + 300);
										if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
												&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
											truetime.set(Calendar.HOUR_OF_DAY, 8);
										}
										Initial(previous, base, "737", truetime, Login_company.getName_of_Company());
										truetime.add(Calendar.MINUTE, amount + 300);
										counter++;
									}
								}
								// 先把0的跑完//
								base = 0;
								while (truetime.before(last_time) == true) {
									int next = getNextOneorTwo(base, base);
									while (getNumber_of_three(next) == 0) {
										next = getNextOneorTwo(next, next);
									}
									if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
											&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
										truetime.set(Calendar.HOUR_OF_DAY, 8);
									}
									Initial(base, next, "737", truetime, Login_company.getName_of_Company());
									base = next;
									previous = -1;
									counter = 0;
									while (truetime.before(last_time) == true
											&& counter <= getNumber_of_three(base) - 1) {
										previous = getNextThree(base, previous);
										if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
												&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
											truetime.set(Calendar.HOUR_OF_DAY, 8);
										}
										Initial(base, previous, "737", truetime, Login_company.getName_of_Company());
										truetime.add(Calendar.MINUTE, amount + 300);
										if (truetime.get(Calendar.HOUR_OF_DAY) >= 0
												&& truetime.get(Calendar.HOUR_OF_DAY) <= 8) {
											truetime.set(Calendar.HOUR_OF_DAY, 8);
										}
										Initial(previous, base, "737", truetime, Login_company.getName_of_Company());
										truetime.add(Calendar.MINUTE, amount + 300);
										counter++;
									}
								}

							}
						}
						// 737//
					}

					void Initial(int i, int t, String plane, Calendar c, String aircom) throws IOException {
						File file = new File("src\\managerfile\\List_of_Line.txt");
						FileOutputStream fos = new FileOutputStream("src\\managerfile\\List_of_Line.txt", true);
						ObjectOutputStream oos = null;
						if (file.length() == 0) {
							oos = new ObjectOutputStream(fos);
						} else {
							oos = new MyObjectOutputStream(fos);
						}
						// 准备写入文件//
						int number_of_line = 0;
						File number_of_line_file = new File("src\\managerfile\\Number_of_Line.txt");
						if (number_of_line_file.length() == 0) {
							number_of_line = 1;
						} else {
							RandomAccessFile number_of_line_raf = new RandomAccessFile(
									"src\\managerfile\\Number_of_Line.txt", "r");
							number_of_line = number_of_line_raf.readInt();
							number_of_line++;
							number_of_line_raf.close();
						}
						RandomAccessFile number_of_line_raf_writer = new RandomAccessFile(
								"src\\managerfile\\Number_of_Line.txt", "rw");
						number_of_line_raf_writer.writeInt(number_of_line);
						number_of_line_raf_writer.close();
						// 更新航线数，文件中即是正确数目//
						double distance = Matrix[i][t];
						Airplane_Line airplane_line = null;
						if (plane.equals("A380") == true) {
							double min_spent = distance / 0.075;
							amount = (int) min_spent;
							Calendar temp = Calendar.getInstance();
							temp.setTime(c.getTime());
							temp.add(Calendar.MINUTE, amount);
							String nol = null;
							String strcity = null;
							if (Integer.toString(city[i].getKey_of_City()).length() == 1) {
								strcity = "00" + Integer.toString(city[i].getKey_of_City());
							} else if (Integer.toString(city[i].getKey_of_City()).length() == 2) {
								strcity = "0" + Integer.toString(city[i].getKey_of_City());
							} else {
								strcity = Integer.toString(city[i].getKey_of_City());
							}
							// 出发城市编号//
							String strcity_second = null;
							if (Integer.toString(city[t].getKey_of_City()).length() == 1) {
								strcity_second = "00" + Integer.toString(city[t].getKey_of_City());
							} else if (Integer.toString(city[t].getKey_of_City()).length() == 2) {
								strcity_second = "0" + Integer.toString(city[t].getKey_of_City());
							} else {
								strcity_second = Integer.toString(city[t].getKey_of_City());
							}
							// 目的城市编号//
							Date date = c.getTime();
							SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
							String datesdf = sdf.format(date);
							sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
							String c_sdf = sdf.format(c.getTime());
							String temp_sdf = sdf.format(temp.getTime());
							nol = Login_company.getCode_of_Company() + strcity + strcity_second + datesdf + "1";
							airplane_line = new Airplane_Line(nol, "A380", aircom, city[i].getName_of_City(),
									city[t].getName_of_City(), c_sdf, temp_sdf, amount, 21, (int) (350 * distance), 106,
									(int) (225 * distance), 423, (int) (110 * distance));
							oos.writeObject(airplane_line);
						} else if (plane.equals("777") == true) {
							double min_spent = distance / 0.08;
							amount = (int) min_spent;
							Calendar temp = Calendar.getInstance();
							temp.setTime(c.getTime());
							temp.add(Calendar.MINUTE, amount);
							String nol = null;
							String strcity = null;
							if (Integer.toString(city[i].getKey_of_City()).length() == 1) {
								strcity = "00" + Integer.toString(city[i].getKey_of_City());
							} else if (Integer.toString(city[i].getKey_of_City()).length() == 2) {
								strcity = "0" + Integer.toString(city[i].getKey_of_City());
							} else {
								strcity = Integer.toString(city[i].getKey_of_City());
							}
							// 出发城市编号//
							String strcity_second = null;
							if (Integer.toString(city[t].getKey_of_City()).length() == 1) {
								strcity_second = "00" + Integer.toString(city[t].getKey_of_City());
							} else if (Integer.toString(city[t].getKey_of_City()).length() == 2) {
								strcity_second = "0" + Integer.toString(city[t].getKey_of_City());
							} else {
								strcity_second = Integer.toString(city[t].getKey_of_City());
							}
							// 目的城市编号//
							Date date = c.getTime();
							SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
							String datesdf = sdf.format(date);
							sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
							String c_sdf = sdf.format(c.getTime());
							String temp_sdf = sdf.format(temp.getTime());
							nol = Login_company.getCode_of_Company() + strcity + strcity_second + datesdf + "2";
							airplane_line = new Airplane_Line(nol, "波音777", aircom, city[i].getName_of_City(),
									city[t].getName_of_City(), c_sdf, temp_sdf, amount, 14, (int) (320 * distance), 71,
									(int) (200 * distance), 283, (int) (100 * distance));
							oos.writeObject(airplane_line);
						} else {
							double min_spent = distance / 0.085;
							amount = (int) min_spent;
							Calendar temp = Calendar.getInstance();
							temp.setTime(c.getTime());
							temp.add(Calendar.MINUTE, amount);
							String nol = null;
							String strcity = null;
							if (Integer.toString(city[i].getKey_of_City()).length() == 1) {
								strcity = "00" + Integer.toString(city[i].getKey_of_City());
							} else if (Integer.toString(city[i].getKey_of_City()).length() == 2) {
								strcity = "0" + Integer.toString(city[i].getKey_of_City());
							} else {
								strcity = Integer.toString(city[i].getKey_of_City());
							}
							// 出发城市编号//
							String strcity_second = null;
							if (Integer.toString(city[t].getKey_of_City()).length() == 1) {
								strcity_second = "00" + Integer.toString(city[t].getKey_of_City());
							} else if (Integer.toString(city[t].getKey_of_City()).length() == 2) {
								strcity_second = "0" + Integer.toString(city[t].getKey_of_City());
							} else {
								strcity_second = Integer.toString(city[t].getKey_of_City());
							}
							// 目的城市编号//
							Date date = c.getTime();
							SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
							String datesdf = sdf.format(date);
							sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
							String c_sdf = sdf.format(c.getTime());
							String temp_sdf = sdf.format(temp.getTime());
							nol = Login_company.getCode_of_Company() + strcity + strcity_second + datesdf + "3";
							airplane_line = new Airplane_Line(nol, "波音737", aircom, city[i].getName_of_City(),
									city[t].getName_of_City(), c_sdf, temp_sdf, amount, 6, (int) (280 * distance), 29,
									(int) (190 * distance), 115, (int) (90 * distance));

							oos.writeObject(airplane_line);
						}
						oos.close();
					}
				}
				CreateHelper createhelper = new CreateHelper();
				try {
					JOptionPane.showMessageDialog(null, "确定生成?");
					createhelper.buildMap(Login_company.getName_of_Company());
					createhelper.CreateLine();
					RandomAccessFile raf = new RandomAccessFile(
							"src\\managerfile\\mark_of_" + Login_company.getName_of_Company() + ".txt", "rw");
					String s = "writen";
					raf.writeChars(s);
					raf.close();
					Log_In.remove(Create_AirplaneLine);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// 生成航空计划//

		class Save_Change_of_BasicInformation_Frame extends JFrame {
			JLabel Save_Change_of_BasicInformation_Label = new JLabel("输入密码:", JLabel.CENTER);
			JPasswordField Save_Change_of_BasicInformation_Textfield = new JPasswordField();
			JButton Save_Change_of_BasicInformation_Confirm_Button = new JButton("确定");

			Save_Change_of_BasicInformation_Frame() {
				setSize(300, 200);
				setTitle("验证");
				setLocation(400, 200);
				setLayout(null);
				Save_Change_of_BasicInformation_Label.setSize(70, 35);
				Save_Change_of_BasicInformation_Textfield.setSize(140, 35);
				Save_Change_of_BasicInformation_Confirm_Button.setSize(80, 35);

				Save_Change_of_BasicInformation_Label.setLocation(30, 40);
				Save_Change_of_BasicInformation_Textfield.setLocation(120, 40);
				Save_Change_of_BasicInformation_Confirm_Button.setLocation(110, 90);

				add(Save_Change_of_BasicInformation_Label);
				add(Save_Change_of_BasicInformation_Textfield);
				add(Save_Change_of_BasicInformation_Confirm_Button);

				Save_Change_of_BasicInformation_Confirm_Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (new String(Save_Change_of_BasicInformation_Textfield.getPassword())
								.equals(Login_company.getPassword()) == true) {
							try {
								RandomAccessFile tempreader = new RandomAccessFile(
										"src\\managerfile\\Number_of_Company.txt", "rw");
								try {
									int numberofcompany = tempreader.readInt();
									tempreader.close();
									Airplane_Company[] cache = new Airplane_Company[numberofcompany];
									FileInputStream Save_Change_of_BasicInformation_tempFileInputStream = new FileInputStream(
											"src\\managerfile\\List_of_Company.txt");
									ObjectInputStream Save_Change_of_BasicInformation_tempObjectInputStream = new ObjectInputStream(
											Save_Change_of_BasicInformation_tempFileInputStream);
									int j;
									for (j = 0; j <= numberofcompany - 2; j++) {
										try {
											cache[j] = (Airplane_Company) Save_Change_of_BasicInformation_tempObjectInputStream
													.readObject();
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									// 存储的航空公司数据读入起缓存作用的cache//
									Save_Change_of_BasicInformation_tempObjectInputStream.close();
									int find = Login_company.getKey_of_Company();
									cache[find - 1].setNumber_of_CityServed(
											Integer.parseInt(Logined_Number_of_CityServed_Textfield.getText()));
									cache[find - 1].setPassword(new String(Logined_Password_Textfield.getPassword()));
									cache[find - 1].setCore_City(Logined_Core_City_Textfield.getText());
									cache[find - 1]
											.setBoeing777(Integer.parseInt(Logined_Boeing777_Textfield.getText()));
									cache[find - 1]
											.setBoeing737(Integer.parseInt(Logined_Boeing737_Textfield.getText()));
									cache[find - 1].setA380(Integer.parseInt(Logined_A380_Textfield.getText()));
									// 在cache中修改//

									FileOutputStream Save_Change_of_BasicInformation_tempFileOutputStream = new FileOutputStream(
											"src\\managerfile\\List_of_Company.txt");
									ObjectOutputStream Save_Change_of_BasicInformation_tempObjectOutputStream = new ObjectOutputStream(
											Save_Change_of_BasicInformation_tempFileOutputStream);
									for (j = 0; j <= numberofcompany - 2; j++) {
										Save_Change_of_BasicInformation_tempObjectOutputStream.writeObject(cache[j]);
									}
									// 缓存写入文件//
									Login_company = cache[find - 1];
									// 更新Login_company//
									JOptionPane.showMessageDialog(null, "修改成功!");

									Logined_Name_of_Company_Textfield.setText(Login_company.getName_of_Company());
									Logined_Password_Textfield.setText(Login_company.getPassword());
									Logined_Core_City_Textfield.setText(Login_company.getCore_City());
									Logined_Number_of_CityServed_Textfield
											.setText(Integer.toString(Login_company.getNumber_of_CityServed()));
									Logined_Boeing737_Textfield.setText(Integer.toString(Login_company.getBoeing737()));
									Logined_Boeing777_Textfield.setText(Integer.toString(Login_company.getBoeing777()));
									Logined_A380_Textfield.setText(Integer.toString(Login_company.getA380()));
									// 更新显示//
									Save_Change_of_BasicInformation_tempObjectOutputStream.close();

									dispose();
									// 关闭辅助窗口//
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else {
							JOptionPane.showMessageDialog(null, "验证失败!");
						}
					}
				});
			}
		}
		// 修改资料辅助窗口//

		Save_Change_of_BasicInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save_Change_of_BasicInformation_Frame save_change_of_basicinformation_frame = new Save_Change_of_BasicInformation_Frame();
				save_change_of_basicinformation_frame.setVisible(true);
			}
		});
		// 修改资料//

		class Add_CityServed_Frame extends JFrame {

			JLabel Add_CityServed_Frame_CityName_Label = new JLabel(
					"<html><body><p style=\"font-family:arial;font-size:16px;\">服务城市名:</p></body></html>",
					JLabel.CENTER);
			JTextField Add_CityServed_Frame_CityName_Textfield = new JTextField();
			JPanel Add_CityServed_Frame_CityName_Panel = new JPanel();

			JLabel Add_CityServed_Frame_LocationE_Label = new JLabel(
					"<html><body><p style=\"font-family:arial;font-size:16px;\">位置.经:</p></body></html>",
					JLabel.CENTER);
			JTextField Add_CityServed_Frame_LocationE_Textfield = new JTextField();
			JPanel Add_CityServed_Frame_LocationE_Panel = new JPanel();

			JLabel Add_CityServed_Frame_LocationN_Label = new JLabel(
					"<html><body><p style=\"font-family:arial;font-size:16px;\">位置.纬:</p></body></html>",
					JLabel.CENTER);
			JTextField Add_CityServed_Frame_LocationN_Textfield = new JTextField();
			JPanel Add_CityServed_Frame_LocationN_Panel = new JPanel();

			JLabel Add_CityServed_Frame_LevelofCity_Label = new JLabel(
					"<html><body><p style=\"font-family:arial;font-size:16px;\">城市客运需求等级:</p></body></html>",
					JLabel.CENTER);
			JTextField Add_CityServed_Frame_LevelofCity_Textfield = new JTextField();
			JPanel Add_CityServed_Frame_LevelofCity_Panel = new JPanel();

			JButton Add_CityServed_Frame_ConfirmAdd_Button = new JButton(
					"<html><body><p style=\"font-family:arial;font-size:16px;\">添加</p></body></html>");
			JButton Add_CityServed_Frame_Done_Button = new JButton(
					"<html><body><p style=\"font-family:arial;font-size:16px;\">完成</p></body></html>");
			JPanel Add_CityServed_Frame_Button_Panel = new JPanel();

			City[] addCity = new City[1000];

			boolean isWriten_inAll(String t) throws IOException, ClassNotFoundException {
				t = Add_CityServed_Frame_CityName_Textfield.getText();
				// 读取城市名//
				RandomAccessFile isWriten_inAll_raf = new RandomAccessFile("src\\managerfile\\Number_of_City.txt",
						"rw");
				if (isWriten_inAll_raf.length() == 0) {
					isWriten_inAll_raf.close();
					return false;
				} else {
					int isWriten_inAll_counter = isWriten_inAll_raf.readInt();
					isWriten_inAll_raf.close();
					City[] isWriten_inAll_array = new City[isWriten_inAll_counter - 1];
					// 城市数组//
					FileInputStream isWriten_inAll_stream = new FileInputStream("src\\managerfile\\List_of_City.txt");
					ObjectInputStream isWriten_inAll_reader = new ObjectInputStream(isWriten_inAll_stream);
					int isWriten_inAll_i = 0;
					for (isWriten_inAll_i = 0; isWriten_inAll_i <= isWriten_inAll_counter - 2; isWriten_inAll_i++) {
						isWriten_inAll_array[isWriten_inAll_i] = (City) isWriten_inAll_reader.readObject();
					}
					isWriten_inAll_reader.close();
					// 读入已写入的城市//
					for (isWriten_inAll_i = 0; isWriten_inAll_i <= isWriten_inAll_counter - 2; isWriten_inAll_i++) {
						if ((isWriten_inAll_array[isWriten_inAll_i].getName_of_City()).equals(t) == true) {
							return true;
						}
					}
					return false;
				}

			}
			// return true说明之前已有相同的城市写入，return false说明没有相同城市写入//

			boolean isWriten_inPart(String t) throws IOException, ClassNotFoundException {
				int isWriten_inPart_number = Login_company.getNumber_of_CityServed();
				t = Add_CityServed_Frame_CityName_Textfield.getText();
				if (isWriten_inPart_number == 0) {
					return false;
				} else {
					City[] isWriten_inPart_array = new City[isWriten_inPart_number];
					FileInputStream isWriten_inPart_stream = new FileInputStream(
							"src\\managerfile\\List_of_City_of_" + Login_company.getName_of_Company() + ".txt");
					ObjectInputStream isWriten_inPart_reader = new ObjectInputStream(isWriten_inPart_stream);
					int isWriten_inPart_i = 0;
					for (isWriten_inPart_i = 0; isWriten_inPart_i <= isWriten_inPart_number - 1; isWriten_inPart_i++) {
						isWriten_inPart_array[isWriten_inPart_i] = (City) isWriten_inPart_reader.readObject();
					}
					// 读入数组//
					isWriten_inPart_reader.close();
					for (isWriten_inPart_i = 0; isWriten_inPart_i <= isWriten_inPart_number - 1; isWriten_inPart_i++) {
						if ((isWriten_inPart_array[isWriten_inPart_i].getName_of_City()).equals(t) == true) {
							return true;
						}
					}
					return false;
				}
			}
			// true表示已有重复，false表示没有重复//

			void Update_Number_of_CityServed(int t) {
				try {
					RandomAccessFile Update_temp_reader = new RandomAccessFile(
							"src\\managerfile\\Number_of_Company.txt", "rw");
					try {
						int Update_numberofcompany = Update_temp_reader.readInt();
						Update_temp_reader.close();
						Airplane_Company[] Update_cache = new Airplane_Company[Update_numberofcompany - 1];
						FileInputStream Update_tempFileInputStream = new FileInputStream(
								"src\\managerfile\\List_of_Company.txt");
						ObjectInputStream Update_tempObjectInputStream = new ObjectInputStream(
								Update_tempFileInputStream);
						int j;
						for (j = 0; j <= Update_numberofcompany - 2; j++) {
							try {
								Update_cache[j] = (Airplane_Company) Update_tempObjectInputStream.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// 存储的航空公司数据读入起缓存作用的cache//
						Update_tempObjectInputStream.close();
						int Update_find = Login_company.getKey_of_Company();

						Update_cache[Update_find - 1].setNumber_of_CityServed(t);
						// 在cache中修改//

						FileOutputStream Update_tempFileOutputStream = new FileOutputStream(
								"src\\managerfile\\List_of_Company.txt");
						ObjectOutputStream Update_tempObjectOutputStream = new ObjectOutputStream(
								Update_tempFileOutputStream);
						for (j = 0; j <= Update_numberofcompany - 2; j++) {
							Update_tempObjectOutputStream.writeObject(Update_cache[j]);
						}
						// 缓存写入文件//
						Login_company = Update_cache[Update_find - 1];
						// 更新Login_company//

						Logined_Name_of_Company_Textfield.setText(Login_company.getName_of_Company());
						Logined_Password_Textfield.setText(Login_company.getPassword());
						Logined_Core_City_Textfield.setText(Login_company.getCore_City());
						Logined_Number_of_CityServed_Textfield
								.setText(Integer.toString(Login_company.getNumber_of_CityServed()));
						Logined_Boeing737_Textfield.setText(Integer.toString(Login_company.getBoeing737()));
						Logined_Boeing777_Textfield.setText(Integer.toString(Login_company.getBoeing777()));
						Logined_A380_Textfield.setText(Integer.toString(Login_company.getA380()));
						// 更新显示//
						Update_tempObjectOutputStream.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Add_CityServed_Frame() {
				setSize(500, 300);
				setTitle("添加服务城市");
				setLocation(400, 200);
				setLayout(new GridLayout(5, 1));

				Add_CityServed_Frame_CityName_Panel.setLayout(new GridLayout(1, 2));
				Add_CityServed_Frame_CityName_Panel.add(Add_CityServed_Frame_CityName_Label);
				Add_CityServed_Frame_CityName_Panel.add(Add_CityServed_Frame_CityName_Textfield);

				Add_CityServed_Frame_LocationE_Panel.setLayout(new GridLayout(1, 2));
				Add_CityServed_Frame_LocationE_Panel.add(Add_CityServed_Frame_LocationE_Label);
				Add_CityServed_Frame_LocationE_Panel.add(Add_CityServed_Frame_LocationE_Textfield);

				Add_CityServed_Frame_LocationN_Panel.setLayout(new GridLayout(1, 2));
				Add_CityServed_Frame_LocationN_Panel.add(Add_CityServed_Frame_LocationN_Label);
				Add_CityServed_Frame_LocationN_Panel.add(Add_CityServed_Frame_LocationN_Textfield);

				Add_CityServed_Frame_LevelofCity_Panel.setLayout(new GridLayout(1, 2));
				Add_CityServed_Frame_LevelofCity_Panel.add(Add_CityServed_Frame_LevelofCity_Label);
				Add_CityServed_Frame_LevelofCity_Panel.add(Add_CityServed_Frame_LevelofCity_Textfield);

				Add_CityServed_Frame_Button_Panel.add(Add_CityServed_Frame_ConfirmAdd_Button);
				Add_CityServed_Frame_Button_Panel.add(Add_CityServed_Frame_Done_Button);

				Add_CityServed_Frame_ConfirmAdd_Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (Add_CityServed_Frame_CityName_Textfield.getText().length() == 0
								|| Add_CityServed_Frame_LocationE_Textfield.getText().length() == 0
								|| Add_CityServed_Frame_LocationN_Textfield.getText().length() == 0
								|| Add_CityServed_Frame_LevelofCity_Textfield.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "请确保信息填写完整!");
						} else {
							try {
								RandomAccessFile Add_CityServed_Frame_ConfirmAdd_raf_out = new RandomAccessFile(
										"src\\managerfile\\Number_of_City.txt", "rw");
								RandomAccessFile Add_CityServed_Frame_ConfirmAdd_raf_in = new RandomAccessFile(
										"src\\managerfile\\Number_of_City.txt", "r");

								FileOutputStream Add_CityServed_Frame_ConfirmAddinAll_stream = null;
								ObjectOutputStream Add_CityServed_Frame_ConfirmAddinAll_writer = null;

								FileInputStream Add_CityServed_Frame_ConfirmAddinAll_readstream = null;
								ObjectInputStream Add_CityServed_Frame_ConfirmAddinAll_reader = null;

								City CityAdded = null;
								int addCity_key = 0;
								long LofNoC = 0;
								try {
									LofNoC = Add_CityServed_Frame_ConfirmAdd_raf_out.length();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}

								if (LofNoC == 0) {
									addCity_key = 1;
									try {
										int temp = addCity_key + 1;
										Add_CityServed_Frame_ConfirmAdd_raf_out.writeInt(temp);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									CityAdded = new City(addCity_key, Add_CityServed_Frame_CityName_Textfield.getText(),
											Double.parseDouble(Add_CityServed_Frame_LocationE_Textfield.getText()),
											Double.parseDouble(Add_CityServed_Frame_LocationN_Textfield.getText()),
											Integer.parseInt(Add_CityServed_Frame_LevelofCity_Textfield.getText()));
									try {
										Add_CityServed_Frame_ConfirmAddinAll_stream = new FileOutputStream(
												"src\\managerfile\\List_of_City.txt", true);
										Add_CityServed_Frame_ConfirmAddinAll_writer = new ObjectOutputStream(
												Add_CityServed_Frame_ConfirmAddinAll_stream);
										Add_CityServed_Frame_ConfirmAddinAll_writer.writeObject(CityAdded);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									// 未有城市注册//
								} else {
									try {
										addCity_key = Add_CityServed_Frame_ConfirmAdd_raf_in.readInt();
										try {
											if (isWriten_inAll(
													Add_CityServed_Frame_CityName_Textfield.getText()) == false) {
												int temp = addCity_key + 1;
												Add_CityServed_Frame_ConfirmAdd_raf_out.writeInt(temp);
												CityAdded = new City(addCity_key,
														Add_CityServed_Frame_CityName_Textfield.getText(),
														Double.parseDouble(
																Add_CityServed_Frame_LocationE_Textfield.getText()),
														Double.parseDouble(
																Add_CityServed_Frame_LocationN_Textfield.getText()),
														Integer.parseInt(
																Add_CityServed_Frame_LevelofCity_Textfield.getText()));
												Add_CityServed_Frame_ConfirmAddinAll_stream = new FileOutputStream(
														"src\\managerfile\\List_of_City.txt", true);
												Add_CityServed_Frame_ConfirmAddinAll_writer = new MyObjectOutputStream(
														Add_CityServed_Frame_ConfirmAddinAll_stream);
												Add_CityServed_Frame_ConfirmAddinAll_writer.writeObject(CityAdded);
												// 没有重复//
											} else {
												int i = 0;
												Add_CityServed_Frame_ConfirmAddinAll_readstream = new FileInputStream(
														"src\\managerfile\\List_of_City.txt");
												Add_CityServed_Frame_ConfirmAddinAll_reader = new ObjectInputStream(
														Add_CityServed_Frame_ConfirmAddinAll_readstream);

												for (i = 0; i <= addCity_key - 2; i++) {
													addCity[i] = (City) Add_CityServed_Frame_ConfirmAddinAll_reader
															.readObject();
												}
												int temp = addCity_key;
												for (int j = 0; j <= temp - 2; j++) {
													if (addCity[j].getName_of_City()
															.equals(Add_CityServed_Frame_CityName_Textfield
																	.getText()) == true) {
														addCity_key = addCity[j].getKey_of_City();
														break;
													}
												}
												// 有重复//
											}
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								try {
									Add_CityServed_Frame_ConfirmAdd_raf_out.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								if (Add_CityServed_Frame_ConfirmAdd_raf_in != null) {
									try {
										Add_CityServed_Frame_ConfirmAdd_raf_in.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								if (Add_CityServed_Frame_ConfirmAddinAll_reader != null) {
									try {
										Add_CityServed_Frame_ConfirmAddinAll_reader.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								if (Add_CityServed_Frame_ConfirmAddinAll_writer != null) {
									try {
										Add_CityServed_Frame_ConfirmAddinAll_writer.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								// 在城市总表中检查是否重复，并得到key//

								FileOutputStream Add_CityServed_Frame_ConfirmAddinPart_stream = null;
								ObjectOutputStream Add_CityServed_Frame_ConfirmAddinPart_writer = null;

								if (Login_company.getNumber_of_CityServed() == 0) {
									Update_Number_of_CityServed(1 + Login_company.getNumber_of_CityServed());
									CityAdded = new City(addCity_key, Add_CityServed_Frame_CityName_Textfield.getText(),
											Double.parseDouble(Add_CityServed_Frame_LocationE_Textfield.getText()),
											Double.parseDouble(Add_CityServed_Frame_LocationN_Textfield.getText()),
											Integer.parseInt(Add_CityServed_Frame_LevelofCity_Textfield.getText()));
									Add_CityServed_Frame_ConfirmAddinPart_stream = new FileOutputStream(
											"src\\managerfile\\List_of_City_of_" + Login_company.getName_of_Company()
													+ ".txt",
											true);
									try {
										Add_CityServed_Frame_ConfirmAddinPart_writer = new ObjectOutputStream(
												Add_CityServed_Frame_ConfirmAddinPart_stream);
										Add_CityServed_Frame_ConfirmAddinPart_writer.writeObject(CityAdded);
										JOptionPane.showMessageDialog(null, "添加成功!");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									// 之前未有注册//
								} else {
									try {
										if (isWriten_inPart(
												Add_CityServed_Frame_CityName_Textfield.getText()) == false) {
											Add_CityServed_Frame_ConfirmAddinPart_stream = new FileOutputStream(
													"src\\managerfile\\List_of_City_of_"
															+ Login_company.getName_of_Company() + ".txt",
													true);
											Update_Number_of_CityServed(1 + Login_company.getNumber_of_CityServed());
											CityAdded = new City(addCity_key,
													Add_CityServed_Frame_CityName_Textfield.getText(),
													Double.parseDouble(
															Add_CityServed_Frame_LocationE_Textfield.getText()),
													Double.parseDouble(
															Add_CityServed_Frame_LocationN_Textfield.getText()),
													Integer.parseInt(
															Add_CityServed_Frame_LevelofCity_Textfield.getText()));
											Add_CityServed_Frame_ConfirmAddinPart_writer = new MyObjectOutputStream(
													Add_CityServed_Frame_ConfirmAddinPart_stream);
											Add_CityServed_Frame_ConfirmAddinPart_writer.writeObject(CityAdded);
											JOptionPane.showMessageDialog(null, "添加成功!");
										} else {
											JOptionPane.showMessageDialog(null, "此城市已添加!");
										}
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								if (Add_CityServed_Frame_ConfirmAddinPart_writer != null) {
									try {
										Add_CityServed_Frame_ConfirmAddinPart_writer.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								// 在公司城市列表中//
								Add_CityServed_Frame_CityName_Textfield.setText(null);
								Add_CityServed_Frame_LocationE_Textfield.setText(null);
								Add_CityServed_Frame_LocationN_Textfield.setText(null);
								Add_CityServed_Frame_LevelofCity_Textfield.setText(null);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});
				// 添加//

				Add_CityServed_Frame_Done_Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						dispose();
					}
				});
				// 关闭窗口//

				add(Add_CityServed_Frame_CityName_Panel);
				add(Add_CityServed_Frame_LocationE_Panel);
				add(Add_CityServed_Frame_LocationN_Panel);
				add(Add_CityServed_Frame_LevelofCity_Panel);
				add(Add_CityServed_Frame_Button_Panel);
			}
		}
		// 添加服务城市辅助窗口//

		Add_CityServed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_CityServed_Frame add_cityserved_frame = new Add_CityServed_Frame();
				add_cityserved_frame.setVisible(true);
			}
		});
		// 添加服务城市//

		// “登录”的按键事件//

		City_List_LevelSorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readNumberofCity();
				City_List_Rank_mark = 1;
				if (number_of_city != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_City_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_City, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});

		City_List_RankSorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readNumberofCity();
				City_List_Rank_mark = 0;
				if (number_of_city != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_City_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_City, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});

		City_List.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				readNumberofCity();
				if (number_of_city != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_City_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_City, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		// "城市列表"的按键事件//

		Company_List.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				readNumberofCompany();
				if (number_of_company != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Company_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Company, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		Company_List_RankSorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				readNumberofCompany();
				Company_List_Rank_mark = 0;
				if (number_of_company != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Company_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Company, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		Company_List_CSSorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				readNumberofCompany();
				Company_List_Rank_mark = 1;
				if (number_of_company != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Company_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Company, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		Company_List_737Sorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				readNumberofCompany();
				Company_List_Rank_mark = 2;
				if (number_of_company != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Company_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Company, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		Company_List_777Sorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				readNumberofCompany();
				Company_List_Rank_mark = 3;
				if (number_of_company != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Company_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Company, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		Company_List_A380Sorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				readNumberofCompany();
				Company_List_Rank_mark = 4;
				if (number_of_company != 0) {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Company_JTable);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Company, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		Root_of_Browse_City_of_Company.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Root_of_Browse_City_of_Company_Frame root_of_browse_city_of_company_frame = new Root_of_Browse_City_of_Company_Frame();
				root_of_browse_city_of_company_frame.setVisible(true);
			}
		});
		// "航空公司"的按键事件//
		Time_Table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				setResizable(true);
				try {
					RandomAccessFile raf = new RandomAccessFile("src\\managerfile\\Number_of_Line.txt", "rw");
					try {
						if (raf.length() == 0) {
							number_of_line = 0;
						} else {
							number_of_line = raf.readInt();
						}
						raf.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (number_of_line != 0) {
					airplane_line = new Airplane_Line[number_of_line];

					try {
						FileInputStream fis = new FileInputStream("src\\managerfile\\List_of_Line.txt");
						try {
							ObjectInputStream ois = new ObjectInputStream(fis);
							for (int i = 0; i <= number_of_line - 1; i++) {
								try {
									airplane_line[i] = (Airplane_Line) ois.readObject();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							ois.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JTable List_of_Line = new JTable(new AbstractTableModel() {
						private String columnName[] = { "航班编号", "机型", "航空公司", "出发地", "目的地", "出发时间", "抵达时间", "头等舱余量",
								"头等舱价格", "商务舱余量", "商务舱价格", "经济舱余量", "经济舱价格" };

						public String getColumnName(int column) {
							return columnName[column];
						}

						public int getColumnCount() {
							return 13;
						}

						public int getRowCount() {
							return number_of_line;
						}

						public Object getValueAt(int row, int col) {
							if (col == 0) {
								return airplane_line[row].get_Number_of_Line();
							} else if (col == 1) {
								return airplane_line[row].get_Airplane();
							} else if (col == 2) {
								return airplane_line[row].get_Master_Company();
							} else if (col == 3) {
								return airplane_line[row].get_Start_City();
							} else if (col == 4) {
								return airplane_line[row].get_End_City();
							} else if (col == 5) {
								return airplane_line[row].get_Start_Time();
							} else if (col == 6) {
								return airplane_line[row].get_End_Time();
							} else if (col == 7) {
								return airplane_line[row].get_Rest_of_FirstClass();
							} else if (col == 8) {
								return airplane_line[row].get_price_of_FirstClass();
							} else if (col == 9) {
								return airplane_line[row].get_Rest_of_BussinessClass();
							} else if (col == 10) {
								return airplane_line[row].get_price_of_BussinessClass();
							} else if (col == 11) {
								return airplane_line[row].get_Rest_of_EconomyClass();
							} else {
								return airplane_line[row].get_price_of_EconomyClass();
							}
						}
					});

					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					JScrollPane scrollpane = new JScrollPane(List_of_Line);
					Main_Panel.add(scrollpane, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				} else {
					Main_Panel.removeAll();
					Main_Panel.setLayout(new BorderLayout());
					Main_Panel.add(Hips_of_Line, BorderLayout.CENTER);
					Main_Panel.revalidate();
					Main_Panel.repaint();
				}
			}
		});
		// 航线列表//
		// 航线时刻表的按键事件//

		Company_List.add(Company_List_RankSorting);
		Company_List.add(Company_List_CSSorting);
		Company_List.add(Company_List_737Sorting);
		Company_List.add(Company_List_777Sorting);
		Company_List.add(Company_List_A380Sorting);
		Company_List.add(Root_of_Browse_City_of_Company);
		City_List.add(City_List_LevelSorting);
		City_List.add(City_List_RankSorting);
		MenuBar.add(Home_Page);
		MenuBar.add(Time_Table);
		MenuBar.add(Company_List);
		MenuBar.add(City_List);
		MenuBar.add(Log_In);
		MenuBar.add(Register);
		MenuBar.add(Help);
		setJMenuBar(MenuBar);
		// 组件加入菜单栏//
		Welcome.setFont(Help_Label_Font);
		Main_Panel.setLayout(new BorderLayout(1, 1));
		Main_Panel.add(Welcome);
		// 首页嵌入主容器中//
		add(Main_Panel);
		// 加入主容器//
	}

}

public class My_System {

	public static void my_start(ServerFrm frm) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Main_Page main_page = new Main_Page(frm);
		main_page.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Main_Page main_page = new Main_Page(null);
		main_page.setVisible(true);
	}

}
