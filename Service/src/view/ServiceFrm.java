package view;

import java.awt.BorderLayout; 
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BplusTree.BplusTree;
import BplusTree.Node;
import Util.FileUtil;
import Util.ServerListen;
import manager.My_System;
import model.User;

public class ServiceFrm extends JFrame implements ActionListener {

	public static int port = 8888; // 服务器的侦听端口
	public ServerSocket serverSocket;
	public static BplusTree bt;

	ServerListen listenThread;
	private JPanel contentPane;
	
	public List<User> userList = new ArrayList<User>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrm frame = new ServiceFrm();
					frame.setVisible(false);
					My_System.my_start(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame.
	 */
	public ServiceFrm() {
		bt = new BplusTree(100); // 设置阶数m
		new Init();
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				StopService();
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		startService();
	}

	public void startService() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("服务器已启动...\n正在侦听...");
			listenThread = new ServerListen(serverSocket, userList);
			listenThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void StopService() {
		listenThread.isStop = true;
		System.out.println("停止服务...");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	class Init extends Thread {
		public Init() {
			start();
		}

		public void run() {
			int cnt = 1;
			long cur = System.nanoTime();
			RandomAccessFile rf = null;
			try {
				rf = new RandomAccessFile("src\\File\\" + "result" + ".txt", "rw");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println(rf.length() / 130);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				for (int i = 0; i < rf.length() / 130 ; i++) {
					if (i % 10000 == 0)
						System.out.println(i);
					String com = rf.readUTF();
					String id = rf.readUTF();
					String st = rf.readUTF();
					String ed = rf.readUTF();
					Long t1 = rf.readLong();
					Long t2 = rf.readLong();
					int a[] = new int[4];
					int b[] = new int[4];
					for (int j = 0; j < 3; j++) {
						a[j] = rf.readInt();
						b[j] = rf.readInt();
					}

					bt.insertOrUpdate(t1, cnt++);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Node next = bt.getHead();
			System.out.println("node --------" + next.getParent());
			int count = 0;
			int temp = 0;
			boolean tr = true;
			while (tr) {
				if (next == null)
					break;
				++count;
				System.out.println("file id " + count);
				List<Entry<Comparable, Object>> entries = next.getEntries();

				/*StringBuilder sb = new StringBuilder();
				for (Entry entry : entries) {
					sb.append(entry.getKey());
					sb.append(" " + entry.getValue() + " ");
				}
				System.out.println(sb.toString());*/
				
				temp += entries.size();
				System.out.println("file Size  " + entries.size());
				System.out.println("cur == " + temp);

				File file = new File("src\\file\\index\\" + String.valueOf(count) + ".txt");
				next.setFile(file);
//				System.out.println(next.toString());
				FileUtil.newInstance().addFile("index\\" + String.valueOf(count), next.toString(), false);

				next = next.getNext();
			}
			cur = System.nanoTime() - cur;
			System.out.println("Done!" + cur);
		}
	}

}
