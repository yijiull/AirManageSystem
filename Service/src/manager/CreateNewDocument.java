package manager;
import java.io.*;

public class CreateNewDocument {

	static int getNumberLine() throws IOException
	{
		RandomAccessFile raf=new RandomAccessFile("src\\managerfile\\Number_of_Line.txt","r");
		if(raf.length()==0)
		{
			raf.close();
			return 0;
		}else
		{
			int temp=raf.readInt();
			raf.close();
			return temp;
		}
	}
	
	static Long getLong(String str)
	{
		Long l=0L;
		l+=Long.parseLong(str.substring(15, 16));
		l+=Long.parseLong(str.substring(14, 15))*10L;
		l+=Long.parseLong(str.substring(12, 13))*100L;
		l+=Long.parseLong(str.substring(11, 12))*1000L;
		l+=Long.parseLong(str.substring(9, 10))*10000L;
		l+=Long.parseLong(str.substring(8, 9))*100000L;
		l+=Long.parseLong(str.substring(6, 7))*1000000L;
		l+=Long.parseLong(str.substring(5, 6))*10000000L;
		l+=Long.parseLong(str.substring(3, 4))*100000000L;
		l+=Long.parseLong(str.substring(2, 3))*1000000000L;
		l+=Long.parseLong(str.substring(1, 2))*10000000000L;
		l+=Long.parseLong(str.substring(0, 1))*100000000000L;
		return l;
	}
	
	static void writeLine(Airplane_Line t,RandomAccessFile rf) 
	{
		int l=t.get_Master_Company().length();
		while(l<23)
		{
			t.set_Master_Company(t.get_Master_Company()+" ");
			l++;
		}
		l=t.get_Number_of_Line().length();
		while(l<23)
		{
			t.set_Number_of_Line(t.get_Number_of_Line()+" ");
			l++;
		}
		l=t.get_Start_City().length();
		while(l<18)
		{
			t.set_Start_City(t.get_Start_City()+" ");
			l++;
		}
		l=t.get_End_City().length();
		while(l<18)
		{
			t.set_End_City(t.get_End_City()+" ");
			l++;
		}
				
		try {
			rf.writeUTF(t.get_Master_Company());
			rf.writeUTF(t.get_Number_of_Line());
			rf.writeUTF(t.get_Start_City());
			rf.writeUTF(t.get_End_City());
			rf.writeLong(getLong(t.get_Start_Time()));
			rf.writeLong(getLong(t.get_End_Time()));
			rf.writeInt(t.get_Rest_of_FirstClass());
			rf.writeInt(t.get_price_of_FirstClass());
			rf.writeInt(t.get_Rest_of_BussinessClass());
			rf.writeInt(t.get_price_of_BussinessClass());
			rf.writeInt(t.get_Rest_of_EconomyClass());
			rf.writeInt(t.get_price_of_EconomyClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub

		File file=new File("src\\managerfile\\List_of_Line.txt");
		
		int number=0;
		try {
			number = getNumberLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.lang.System.out.println(number);
		RandomAccessFile rf=null;
		try {
			rf = new RandomAccessFile("src\\file\\result.txt","rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.lang.System.out.println(ois==null);
		for(int i=1;i<=number;i++)
		{
			Airplane_Line temp=null;
			try {
				temp = (Airplane_Line)ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeLine(temp,rf);
		}
		try {
			rf.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
