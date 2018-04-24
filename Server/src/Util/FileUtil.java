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
		RandomAccessFile rf = new RandomAccessFile("src\\file\\" + "result" + ".txt", "rw");
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
	 * 25 25 20 20 8 8 4 4 4 4 4 4 = 130   ÿ����¼130���ֽ�
	 * ������λ
	 * ���ڵ�n����¼
	 * ��㣺  pos = (n-1) * 130 + 50
	 * �յ㣺 pos = (n-1) * 130 + 70
	 * ʱ�䣺 pos = (n-1) * 130 + 90
	 * ͷ�Ȳ�  pos = (n-1) * 130 + 106
	 * ���ò�  pos = (n-1) * 130 + 114
	 * ���ò�   pos = (n-1) * 130 + 122
	 * 
	 * @throws Exception
	 */
	public synchronized void makePlane() throws Exception {
		
		RandomAccessFile rf = new RandomAccessFile("src\\file\\" + "result" + ".txt", "rw");
		rf.writeUTF("china air              ");  //���չ�˾   25
		rf.writeUTF("123412341234123412     "); //������  25
		rf.writeUTF("guangzhou         "); //���20
		rf.writeUTF("beijing           ");	//�յ�20
		rf.writeLong(201803280920L); 	//����ʱ��8
		rf.writeLong(201803281420L);//����ʱ��8
		rf.writeInt(20); //ͷ�Ȳ�����4
		rf.writeInt(2345);//ͷ�Ȳռ۸�4
		rf.writeInt(20);//���������4
		rf.writeInt(1345); //����ռ۸�4
		rf.writeInt(20);//���ò�����4
		rf.writeInt(345);//���òռ۸�4

		
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
	 * ��ȡ������Ʊ
	 * @param n ��n�˺���
	 * @param level  ��id����λ
	 * @return  ʣ��Ʊ��
	 * @throws Exception
	 */
	public synchronized int getCnt(int n, int level) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\file\\" + "result" + ".txt", "rw");
		rf.seek((n - 1) * 130 + 106 + level * 8);
		int temp = rf.readInt();
		rf.close();
		return temp;
	}
	/**
	 * �޸�ʣ��Ʊ��
	 * @param n
	 * @param level
	 * @param fg
	 * @throws Exception
	 */
	public synchronized void modifyCnt(int n, int level, int fg) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\file\\" + "result" + ".txt", "rw");
		rf.seek((n - 1) * 130 + 106 + level * 8);
		int temp = rf.readInt();
		rf.seek((n - 1) * 130 + 106 + level * 8);
		rf.writeInt(temp + fg);
		rf.close();
		return;
	}
	/**
	 * ���ص�n�˺������Ϣ
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public synchronized String getAirInfo(int n) throws Exception {
		RandomAccessFile rf = new RandomAccessFile("src\\file\\" + "result" + ".txt", "rw");
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
	 * ��ȡ�����ļ���Ϣ
	 * @param id  �����ļ��ı��
	 * @return
	 * @throws Exception
	 */
	public synchronized String getAllAirInfo(int id) throws Exception {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("src\\index\\" + id + ".txt"));
		return oin.readObject().toString();
		
	}
	
	/**
	 * ��ȡ�ļ�
	 * @param name "src\\File\\" + �ļ��� + ".txt"
	 * @return ����String
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
	 * ���ļ���׷������
	 * @param name  �ļ���
	 * @param str  ����
	 * @param append  �Ƿ�׷��
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
