package manager;

import java.io.IOException; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Airplane_Company implements Serializable  {
	private int Key_of_Company;
	private String Name_of_Company;
	private String Password;
	private City[] CityServed;
	private int Number_of_CityServed;
	private String Core_City;
	private int Boeing737;  //载客量150人  6  29  115   v=0.085
	private int Boeing777;  //载客量368人  14  71  283  v=0.08
	private int A380;       //载客量550人  21  106  423  v=0.075
	private String Code_of_Company;  //编号//
	
	private void writeObject(ObjectOutputStream out)throws IOException  {
		out.writeInt(Key_of_Company);
		out.writeUTF(Name_of_Company);
		out.writeUTF(Password);
		out.writeInt(Number_of_CityServed);
		out.writeUTF(Core_City);
		out.writeInt(Boeing737);
		out.writeInt(Boeing777);
		out.writeInt(A380);
		out.writeUTF(Code_of_Company);
	} 
	private void readObject(ObjectInputStream in)throws IOException  {
		Key_of_Company=in.readInt();
		Name_of_Company=in.readUTF();
		Password=in.readUTF();
		Number_of_CityServed=in.readInt();
		Core_City=in.readUTF();
		Boeing737=in.readInt();
		Boeing777=in.readInt();
		A380=in.readInt();
		Code_of_Company=in.readUTF();
	}
	//定义串行化控制函数//
	public Airplane_Company(int KoC,String NoC,String P,int NoCS,String C_C,int B737,int B777,int A,String str)  {
		Key_of_Company=KoC;
		Name_of_Company=NoC;
		Password=P;
		Number_of_CityServed=NoCS;
		Core_City=C_C;
		Boeing737=B737;
		Boeing777=B777;
		A380=A;
		Code_of_Company=str;
	}
	public int getKey_of_Company()  {
		return Key_of_Company;
	}
	public String getName_of_Company()  {
		return Name_of_Company;
	}
	public String getPassword()  {
		return Password;
	}
	public City[] getCityServed()  {

		return CityServed;
	}
	public int getNumber_of_CityServed()  {
		return Number_of_CityServed;
	}
	public String getCore_City()  {
		return Core_City;
	}
	public int getBoeing777()  {
		return Boeing777;
	}
	public int getBoeing737()  {
		return Boeing737;
	}
	public int getA380()  {
		return A380;
	}
	public String getCode_of_Company()  {
		return Code_of_Company;
	}
	public void setKey_of_Company(int t)  {
		Key_of_Company=t;
	}
	public void setName_of_Company(String t)  {
		Name_of_Company=t;
	}
	public void setPassword(String t)  {
		Password=t;
	}
	public void setCityServed(City[] t)  {
		CityServed=t;
	}
	public void setNumber_of_CityServed(int t)  {
		Number_of_CityServed=t;
	}
	public void setCore_City(String t)  {
		Core_City=t;
	}
	public void setBoeing777(int t)  {
		Boeing777=t;
	}
	public void setBoeing737(int t)  {
		Boeing737=t;
	}
	public void setA380(int t)  {
		A380=t;
	}
	public void setCode_of_Company(String str)  {
		Code_of_Company=str;
	}
}
//航空公司类//
