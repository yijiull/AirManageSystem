package manager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class City implements Serializable {
	private int Key_of_City;
	private String Name_of_City;
	private double Location_E;
	private double Location_N;
	private int Level_of_City;
	
	private void writeObject(ObjectOutputStream out)throws IOException  {
		out.writeInt(Key_of_City);
		out.writeUTF(Name_of_City);
		out.writeDouble(Location_E);
		out.writeDouble(Location_N);
		out.writeInt(Level_of_City);
	}
	private void readObject(ObjectInputStream in)throws IOException  {
		Key_of_City=in.readInt();
		Name_of_City=in.readUTF();
		Location_E=in.readDouble();
		Location_N=in.readDouble();
		Level_of_City=in.readInt();
	}
	//定义串行化的控制函数//
	public City(int kc,String NoC,double LE,double LN,int LoC)  {
		Key_of_City=kc;
		Name_of_City=NoC;
		Location_E=LE;
		Location_N=LN;
		Level_of_City=LoC;
	}
	public int getKey_of_City()  {
		return Key_of_City;
	}
	public String getName_of_City()  {
		return Name_of_City;
	}
	public double getLocation_E()  {
		return Location_E;
	}
	public double getLocation_N()  {
		return Location_N;
	}
	public int getLevel_of_City()  {
		return Level_of_City;
	}
	public void setKey_of_City(int t)  {
		Key_of_City=t;
	}
	public void setName_of_City(String t)  {
		Name_of_City=t;
	}
	public void setLocation_E(double t)  {
		Location_E=t;
	}
	public void setLocation_N(double t)  {
		Location_N=t;
	}
	public void setLevel_of_City(int t)  {
		Level_of_City=t;
	}
}
//城市类//

