package manager;
import java.io.*;
public class read {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RandomAccessFile a=new RandomAccessFile("src\\managerfile\\result.txt","rw");
			String company=null;
			String n=null;
			String sc=null;
			String ec=null;
			Long st=0L;
			Long et=0L;
			int fcn=0;
			int fcp=0;
			int bcn=0;
			int bcp=0;
			int ecn=0;
			int ecp=0;
			for(int i=0;i<=1000;i++)
			{
				try {
					company=a.readUTF();
					n=a.readUTF();
					sc=a.readUTF();
					ec=a.readUTF();
					st=a.readLong();
					et=a.readLong();
					fcn=a.readInt();
					fcp=a.readInt();
					bcn=a.readInt();
					bcp=a.readInt();
					ecn=a.readInt();
					ecp=a.readInt();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.lang.System.out.println(company+" "+n+" "+sc+" "+ec+" "+st+" "+et+" "+fcn+" "+fcp+" "+bcn+" "+bcp+" "+ecn+" "+ecp);
			}
			try {
				a.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
