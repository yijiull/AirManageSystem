package Util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileUtil {
	
	public static FileUtil fu= new FileUtil();
	
	public static FileUtil newInstance() {
		return fu;
	}
	
	private FileUtil() {
	
	}
	public static void main(String[] args) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\File\\" + "result" + ".txt", "rw");
		for(int i = 0; i < 1; i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(rf.readUTF() + " ");
			System.out.println(rf.getFilePointer());
			sb.append(rf.readUTF() + " ");
			System.out.println(rf.getFilePointer());
			sb.append(rf.readUTF() + " ");
			System.out.println(rf.getFilePointer());
			sb.append(rf.readUTF() + " ");
			System.out.println(rf.getFilePointer());
			sb.append(rf.readLong() + " ");
			sb.append(rf.readLong() + " ");
			sb.append(rf.readInt() + " ");
			sb.append(rf.readInt() + " ");
			sb.append(rf.readInt() + " ");
			sb.append(rf.readInt() + " ");
			sb.append(rf.readInt() + " ");
			sb.append(rf.readInt() + " ");
			System.out.println(rf.getFilePointer());
			System.out.println(sb.toString());
		}
		
	}
	/**
	 * 25 25 20 20 8 8 4 4 4 4 4 4 = 130   每条记录130个字节
	 * 三个舱位
	 * 对于第n条记录
	 * 起点：  pos = (n-1) * 130 + 50
	 * 终点： pos = (n-1) * 130 + 70
	 * 时间： pos = (n-1) * 130 + 90
	 * 头等舱  pos = (n-1) * 130 + 106
	 * 经济舱  pos = (n-1) * 130 + 114
	 * 经济舱   pos = (n-1) * 130 + 122
	 * 
	 * @throws Exception
	 */
	public synchronized void makePlane() throws Exception {
		
		RandomAccessFile rf = new RandomAccessFile("src\\File\\" + "result" + ".txt", "rw");
		rf.writeUTF("china air              ");  //航空公司   25
		rf.writeUTF("123412341234123412     "); //航班编号  25
		rf.writeUTF("guangzhou         "); //起点20
		rf.writeUTF("beijing           ");	//终点20
		rf.writeLong(201803280920L); 	//出发时间8
		rf.writeLong(201803281420L);//到达时间8
		rf.writeInt(20); //头等舱余量4
		rf.writeInt(2345);//头等舱价格4
		rf.writeInt(20);//商务舱余量4
		rf.writeInt(1345); //商务舱价格4
		rf.writeInt(20);//经济舱余量4
		rf.writeInt(345);//经济舱价格4

		
		rf.writeUTF("china air              ");  
		System.out.println(rf.getFilePointer());
		rf.writeUTF("123412341234123412     ");
		System.out.println(rf.getFilePointer());
		rf.writeUTF("huangzhou         ");
		System.out.println(rf.getFilePointer());
		rf.writeUTF("tianjing          ");
		System.out.println(rf.getFilePointer());
		rf.writeLong(201804280920L);
		System.out.println(rf.getFilePointer());
		rf.writeLong(201804281420L);
		System.out.println(rf.getFilePointer());
		rf.writeInt(20);
		System.out.println(rf.getFilePointer());
		rf.writeInt(2345);
		System.out.println(rf.getFilePointer());
		rf.writeInt(20);
		System.out.println(rf.getFilePointer());
		rf.writeInt(1345);
		System.out.println(rf.getFilePointer());
		rf.writeInt(20);
		System.out.println(rf.getFilePointer());
		rf.writeInt(345);
		System.out.println(rf.getFilePointer());
		
		rf.seek(136);
		rf.writeInt(12345);
		System.out.println(rf.getFilePointer());
		rf.seek(136);
		System.out.println(rf.readInt());
		rf.seek(140);
		System.out.println(rf.readUTF());
		rf.close();
		
	}
	/**
	 * 读取航班余票
	 * @param n 第n趟航班
	 * @param level  第id级舱位
	 * @return  剩余票数
	 * @throws Exception
	 */
	public synchronized int getCnt(int n, int level) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\File\\" + "result" + ".txt", "rw");
		rf.seek((n - 1) * 130 + 106 + level * 8);
		int temp = rf.readInt();
		rf.close();
		return temp;
	}
	/**
	 * 修改剩余票数
	 * @param n
	 * @param level
	 * @param fg
	 * @throws Exception
	 */
	public synchronized void modifyCnt(int n, int level, int fg) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\File\\" + "result" + ".txt", "rw");
		rf.seek((n - 1) * 130 + 106 + level * 8);
		int temp = rf.readInt();
		rf.seek((n - 1) * 130 + 106 + level * 8);
		rf.writeInt(temp + fg);
		rf.close();
		return;
	}
	/**
	 * 返回第n趟航班的信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public synchronized String getAirInfo(int n) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\File\\" + "result" + ".txt", "rw");
		StringBuffer sb = new StringBuffer();
		rf.seek(130L * (n-1));
		sb.append(rf.readUTF() + " ");
		sb.append(rf.readUTF() + " ");
		sb.append(rf.readUTF() + " ");
		sb.append(rf.readUTF() + " ");
		sb.append(rf.readLong() + " ");
		sb.append(rf.readLong() + " ");
		sb.append(rf.readInt() + " ");
		sb.append(rf.readInt() + " ");
		sb.append(rf.readInt() + " ");
		sb.append(rf.readInt() + " ");
		sb.append(rf.readInt() + " ");
		sb.append(rf.readInt() + " ");
		rf.close();
		return sb.toString();
	}
	/**
	 * 读取索引文件信息
	 * @param id  索引文件的编号
	 * @return
	 * @throws Exception
	 */
	public synchronized String getAllAirInfo(int id) throws Exception {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("src\\index\\" + id + ".txt"));
		return oin.readObject().toString();
		
	}
	
	/**
	 * 读取文件
	 * @param name "src\\File\\" + 文件名 + ".txt"
	 * @return 返回String
	 */
	public synchronized String readFile(String name) {
		StringBuffer res = new StringBuffer();
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader("src\\file\\" + name + ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int temp = 0;
		try {
			while((temp = bf.read()) != -1) {
				res.append((char)temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res.toString();
	}
	/**
	 * 向文件中追加内容
	 * @param name  文件名
	 * @param str  内容
	 * @param append  是否追加
	 */
	
	public synchronized void addFile(String name, String str, boolean append) {
		BufferedOutputStream bf = null;
		OutputStreamWriter cout = null;
		try {
			bf = new BufferedOutputStream(new FileOutputStream(new File("src\\file\\" + name + ".txt"), append));
			cout = new OutputStreamWriter(bf);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < str.length(); i++) {
			try {
				bf.write(str.charAt(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bf.write('\r');
			bf.write('\n');
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
