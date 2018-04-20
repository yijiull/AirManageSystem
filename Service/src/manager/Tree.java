package manager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tree {
	public int[] CompanyArray;
	public int[][]PlaneArray;
	public Airplane_Line[] LineArray;
	public City[] CityArray;
	private int n_of_city=-1;
	private int number;//number of lines
	public String company;
	public int mark=-1;//标记查找到的位置
	
	private void ReadCityArray() throws IOException, ClassNotFoundException
	{
		RandomAccessFile raf=new RandomAccessFile("src\\managerfile\\Number_of_City.txt","rw");
		if(raf.length()!=0L) 
		{
			n_of_city=raf.readInt();
			n_of_city--;
		}
		raf.close();
		CityArray=new City[n_of_city];
		FileInputStream fis=new FileInputStream("src\\managerfile\\List_of_City.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		for(int i=0;i<=n_of_city-1;i++)
		{
			CityArray[i]=(City)ois.readObject();
		}
		ois.close();
	}
	
	private int ReadNumberofLine() throws IOException
	{
		int n=-1;
		RandomAccessFile raf=new RandomAccessFile("src\\managerfile\\Number_of_Line.txt","rw");
		if(raf.length()!=0)
		{
			n=raf.readInt();
		}
		raf.close();
		return n;
	}

	private int getNumberofCompany() throws IOException
	{
		RandomAccessFile raf=new RandomAccessFile("src\\managerfile\\Number_of_Company.txt","rw");
		int numcompany=-1;
		if(raf.length()!=0L)
		{
			numcompany=raf.readInt();
		}
		numcompany--;
		raf.close();
		return numcompany;
	}
	
	private int getNumberofPlane() throws IOException, ClassNotFoundException
	{
		RandomAccessFile raf=new RandomAccessFile("src\\managerfile\\Number_of_Company.txt","rw");
		int numcompany=-1;
		if(raf.length()!=0L)
		{
			numcompany=raf.readInt();
		}
		numcompany--;
		FileInputStream fis=new FileInputStream("src\\managerfile\\List_of_Company.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		Airplane_Company ac=null;
		int numplane=0;
		for(int i=0;i<=numcompany-1;i++)
		{
			ac=(Airplane_Company)ois.readObject();
			numplane+=ac.getA380();
			numplane+=ac.getBoeing777();
			numplane+=ac.getBoeing737();
		}
		ois.close();
		raf.close();
		return numplane;
	}
	
	private void BuildAirplaneLine(Airplane_Line[] LineArray) throws IOException
	{
		FileInputStream fis=new FileInputStream("src\\managerfile\\List_of_Line.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		for(int i=0;i<=number-1;i++)
		{
			try {
				LineArray[i]=(Airplane_Line)ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ois.close();
	}
	
	private void BuildPlaneArray() throws ClassNotFoundException, IOException
	{
		PlaneArray=new int[7][getNumberofPlane()];
		int m=0;int n=0;
		int j=1;
		PlaneArray[0][0]=0;
		m=1;
		for(int i=0;i<=number-2;i++)
		{
			j=i+1;
			if(LineArray[i].get_Time_Month()==m&&LineArray[j].get_Time_Month()==m+1)
			{
				PlaneArray[m][n]=j;
				m++;
			}
			if(LineArray[i].get_Time_Month()!=1&&LineArray[j].get_Time_Month()==1)
			{
				m=0;
				n++;
				PlaneArray[m][n]=j;
				m++;
			}
		}
	}
	
	private void BuildCompanyArray() throws IOException, ClassNotFoundException
	{
		int n=this.getNumberofCompany();
		CompanyArray=new int[n];
		String standard=LineArray[PlaneArray[0][0]].get_Master_Company();
		String str=null;
		CompanyArray[0]=0;
		int m=1;
		for(int i=1;i<=getNumberofPlane()-1;i++)
		{
			str=LineArray[PlaneArray[0][i]].get_Master_Company();
			if(str.equals(standard)==false)
			{
				standard=str;
				CompanyArray[m]=i;
				m++;
			}
		}
	}
	
	public Airplane_Line Search(String start,String end,String time) throws IOException, ClassNotFoundException
	{
		Airplane_Line al=null;
		int n=getNumberofCompany();
		int recordCom=-1;
		for(int i=0;i<=n-1;i++)
		{
			if(LineArray[PlaneArray[0][CompanyArray[i]]].get_Master_Company().equals(company)==true)
			{
				recordCom=i;
				break;
			}
		}
		java.lang.System.out.println(recordCom);
		//find company
		String mon=time.substring(5,7);
		String da=time.substring(8);
		int month=Integer.parseInt(mon);
		int day=Integer.parseInt(da);
		java.lang.System.out.println(month);
		java.lang.System.out.println(day);
		java.lang.System.out.println(getNumberofPlane());
		int base=CompanyArray[recordCom];
		if(recordCom==n-1)
		{
			for(int i=base;i<=getNumberofPlane()-1;i++)
			{
				for(int j=PlaneArray[month-1][i];LineArray[j].get_Time_Month()==month;j++)
				{
					if(LineArray[j].get_Time_Day()==day&&LineArray[j].get_Start_City().equals(start)==true&&LineArray[j].get_End_City().equals(end)==true)
					{
						al=LineArray[j];
						mark=j;
						break;
					}
					java.lang.System.out.println(j);
				}
				if(al!=null)
				{
					break;
				}
				java.lang.System.out.println(" "+i);
			}
		}else  {
				for(int i=base;i<=CompanyArray[recordCom+1]-1;i++)
				{
					for(int j=PlaneArray[month-1][i];LineArray[j].get_Time_Month()==month;j++)
					{
						if(LineArray[j].get_Time_Day()==day&&LineArray[j].get_Start_City().equals(start)==true&&LineArray[j].get_End_City().equals(end)==true)
						{
							al=LineArray[j];
							mark=j;
							break;
						}
					}
					if(al!=null)
					{
						break;
					}
				}
			}
		return al;
	}
	
	public City FindCity(String str)
	{
		City c=null;
		for(int i=0;i<=n_of_city-1;i++)
		{
			if(CityArray[i].getName_of_City().equals(str))
			{
				c=CityArray[i];
				break;
			}
		}
		return c;
	}
	
	public void Change(String end,String time) throws ParseException, IOException
	{
		City startCity=null;
		City endCity=null;
		endCity=FindCity(end);
		startCity=FindCity(LineArray[mark].get_Start_City());
		double distance=Math.sqrt((startCity.getLocation_E()-endCity.getLocation_E())*(startCity.getLocation_E()-endCity.getLocation_E())+(startCity.getLocation_N()-endCity.getLocation_N())*(startCity.getLocation_N()-endCity.getLocation_N()));
		double min_spent=0;
		int amount=0;
		if(LineArray[mark].get_Airplane().equals("A380")==true)
		{
			min_spent=distance/0.075;
		}else if(LineArray[mark].get_Airplane().equals("波音777")==true)  {
			min_spent=distance/0.08;
		}else {
			min_spent=distance/0.085;
		}
		amount=(int)min_spent;
		Calendar starttime=Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Date date =sdf.parse(time);
		starttime.setTime(date);
		LineArray[mark].set_End_City(end);
		LineArray[mark].set_Start_Time(time);
		Calendar laststarttime=Calendar.getInstance();
		laststarttime=starttime;
		starttime.add(Calendar.MINUTE, amount);
		String endtime=sdf.format(starttime.getTime());
		LineArray[mark].set_End_Time(endtime);
		//修改目的地出发时间到达时间
		int next=mark+1;
		int counter=0;
		while(next<=number-1)
		{
			Calendar compare=Calendar.getInstance();
			date=sdf.parse(LineArray[next].get_Start_Time());
			compare.setTime(date);
			if(compare.before(laststarttime)==true)
			{
				break;
			}
			City temp=FindCity(LineArray[next].get_End_City());
			double dis=Math.sqrt((endCity.getLocation_E()-temp.getLocation_E())*(endCity.getLocation_E()-temp.getLocation_E())+(endCity.getLocation_N()-temp.getLocation_N())*(endCity.getLocation_N()-temp.getLocation_N()));
			amount=0;
			if(LineArray[mark].get_Airplane().equals("A380")==true)
			{
				min_spent=dis/0.075;
			}else if(LineArray[mark].get_Airplane().equals("波音777")==true)  {
				min_spent=dis/0.08;
			}else {
				min_spent=dis/0.085;
			}
			amount=(int)min_spent;
			Calendar cal=starttime;
			cal.add(Calendar.MINUTE, amount);
			Calendar nexttime=Calendar.getInstance();
			Date tempdate=sdf.parse(LineArray[next].get_End_Time());
			nexttime.setTime(tempdate);
			if(cal.before(nexttime))
			{
				LineArray[next]=null;
				counter++;
				break;
			}else  {
				LineArray[next]=null;
				counter++;
				next++;
			}
		}
		RandomAccessFile raf=new RandomAccessFile("src\\managerfile\\Number_of_Line.txt","rw");
		int num=number;
		num=number-counter;
		raf.writeInt(num);
		raf.close();
	}
	
	public void WriteToTXT() throws IOException
	{
		FileOutputStream fos=new FileOutputStream("src\\managerfile\\List_of_Line.txt",false);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		for(int i=0;i<=number-1;i++)
		{
			if(LineArray[i]!=null)
			{
				oos.writeObject(LineArray[i]);
			}
		}
		oos.close();
	}
	
	Tree(String company) throws IOException, ClassNotFoundException
	{
		this.company=company;
		number=ReadNumberofLine();
		ReadCityArray();
		LineArray=new Airplane_Line[number];
		BuildAirplaneLine(LineArray);
		BuildPlaneArray();
		BuildCompanyArray();
		//Build Tree
	}
}
