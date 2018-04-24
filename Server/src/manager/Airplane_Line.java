package manager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;

public class Airplane_Line implements Serializable {
	private String Number_of_Line;
	private String Airplane;
	private String Master_Company;
	private String Start_City;
	private String End_City;  
	private String Start_Time;
	private String End_Time;
	private int Minutes_Spent;
	private int Rest_of_FirstClass;//头等舱
	private int price_of_FirstClass;
	private int Rest_of_BussinessClass;//商务舱
	private int price_of_BussinessClass;
	private int Rest_of_EconomyClass;//经济舱
	private int price_of_EconomyClass;
	Airplane_Line(String nol,String ap,String mc,String sc,String ec,String st,String et,int ms,int rofc,int pofc,int robc,int pobc,int roec,int poec)  {
		Number_of_Line=nol;
		Airplane=ap;
		Master_Company=mc;
		Start_City=sc;
	    End_City=ec;
		Start_Time=st;
		End_Time=et;
		Minutes_Spent=ms;
		Rest_of_FirstClass=rofc;//头等舱
		price_of_FirstClass=pofc;
		Rest_of_BussinessClass=robc;//商务舱
		price_of_BussinessClass=pobc;
		Rest_of_EconomyClass=roec;//经济舱
		price_of_EconomyClass=poec;
	}
	void set_Number_of_Line(String t)  {
		Number_of_Line=t;
	}
	String get_Number_of_Line()  {
		return Number_of_Line;
	}
	void set_Airplane(String t)  {
		Airplane=t;
	}
	String get_Airplane()  {
		return Airplane;
	}
	void set_Master_Company(String t)  {
		Master_Company=t;
	}
	String get_Master_Company()  {
		return Master_Company;
	}
	void set_Start_City(String t)  {
		Start_City=t;
	}
	String get_Start_City()  {
		return Start_City;
	}
	void set_End_City(String t)  {
		End_City=t;
	}
	String get_End_City()  {
		return End_City;
	}
	void set_Start_Time(String t)  {
		Start_Time=t;
	}
	String get_Start_Time()  {
		return Start_Time;
	}
	void set_End_Time(String t)  {
		End_Time=t;
	}
	String get_End_Time()  {
		return End_Time;
	}
	void set_Minutes_Spent(int t)  {
		Minutes_Spent=t;
	}
	int get_Minutes_Spent()  {
		return Minutes_Spent;
	}
	void set_Rest_of_FirstClass(int t)  {
		Rest_of_FirstClass=t;
	}
	int get_Rest_of_FirstClass()  {
		return Rest_of_FirstClass;
	}
	void set_Rest_of_BussinessClass(int t)  {
		Rest_of_BussinessClass=t;
	}
	int get_Rest_of_BussinessClass()  {
		return Rest_of_BussinessClass;
	}
	void set_Rest_of_EconomyClass(int t)  {
		Rest_of_EconomyClass=t;
	}
	int get_Rest_of_EconomyClass()  {
		return Rest_of_EconomyClass;
	}
	void set_price_of_FirstClass(int t)  {
		price_of_FirstClass=t;
	}
	int get_price_of_FirstClass()  {
		return price_of_FirstClass;
	}
	void set_price_of_BussinessClass(int t)  {
		price_of_BussinessClass=t;
	}
	int get_price_of_BussinessClass()  {
		return price_of_BussinessClass;
	}
	void set_price_of_EconomyClass(int t)  {
		price_of_EconomyClass=t;
	}
	int get_price_of_EconomyClass()  {
		return price_of_EconomyClass;
	}
	int get_Time_Month()
	{
		String str=Start_Time;
		str=str.substring(5,7);
		int n=Integer.parseInt(str);
		return n;
	}
	int get_Time_Day()
	{
		String str=Start_Time;
		str=str.substring(8,10);
		int n=Integer.parseInt(str);
		return n;
	}
	private void writeObject(ObjectOutputStream out)throws IOException  {
		out.writeUTF(Number_of_Line);
		out.writeUTF(Airplane);
		out.writeUTF(Master_Company);
		out.writeUTF(Start_City);
		out.writeUTF(End_City);
		out.writeUTF(Start_Time);
		out.writeUTF(End_Time);
		out.writeInt(Minutes_Spent);
		out.writeInt(Rest_of_FirstClass);
		out.writeInt(price_of_FirstClass);
		out.writeInt(Rest_of_BussinessClass);
		out.writeInt(price_of_BussinessClass);
		out.writeInt(Rest_of_EconomyClass);
		out.writeInt(price_of_EconomyClass);
	}
	private void readObject(ObjectInputStream in)throws IOException, ParseException, ClassNotFoundException  {
		Number_of_Line=in.readUTF();
		Airplane=in.readUTF();
		Master_Company=in.readUTF();
		Start_City=in.readUTF();
		End_City=in.readUTF();
		Start_Time=in.readUTF();
		End_Time=in.readUTF();
		Minutes_Spent=in.readInt();
		Rest_of_FirstClass=in.readInt();
		price_of_FirstClass=in.readInt();
		Rest_of_BussinessClass=in.readInt();
		price_of_BussinessClass=in.readInt();
		Rest_of_EconomyClass=in.readInt();
		price_of_EconomyClass=in.readInt();
	}
}
