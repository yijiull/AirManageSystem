package manager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class Root_of_Browse_City_of_Company_Frame extends JFrame  {
			
			JPanel Root_of_Browse_City_of_Company_Frame_panel=new JPanel();
			JMenuBar Root_of_Browse_City_of_Company_Frame_JMenuBar=new JMenuBar();
			JMenu Root_of_Browse_City_of_Company_Frame_JMenu=new JMenu("选择航空公司");
			JMenuItem[] Root_of_Browse_City_of_Company_Frame_JMenuItem=null;
			Root_of_Browse_City_of_Company_Frame()  {
				RandomAccessFile Root_of_Browse_City_of_Company_Frame_raf=null;
				int number=0;
				int i=0;
				try {
					Root_of_Browse_City_of_Company_Frame_raf=new RandomAccessFile("src\\managerfile\\Number_of_Company.txt","r");
					try {
						if(Root_of_Browse_City_of_Company_Frame_raf.length()==0)  {
							//没有公司的情况//
						}else  {
							number=Root_of_Browse_City_of_Company_Frame_raf.readInt();
							number--;
							Root_of_Browse_City_of_Company_Frame_raf.close();
							Root_of_Browse_City_of_Company_Frame_JMenuItem=new JMenuItem[number];
							for(i=0;i<=number-1;i++)  {
								Root_of_Browse_City_of_Company_Frame_JMenuItem[i]=new JMenuItem();
							}
							//初始化Menu//
							FileInputStream Root_of_Browse_City_of_Company_Frame_fis=new FileInputStream("src\\managerfile\\List_of_Company.txt");
							ObjectInputStream Root_of_Browse_City_of_Company_Frame_ois=new ObjectInputStream(Root_of_Browse_City_of_Company_Frame_fis);
					
							for(i=0;i<=number-1;i++)  {
								try {
									Root_of_Browse_City_of_Company_Frame_JMenuItem[i].setText(((Airplane_Company)Root_of_Browse_City_of_Company_Frame_ois.readObject()).getName_of_Company());
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							Root_of_Browse_City_of_Company_Frame_ois.close();
							for(i=0;i<=number-1;i++)  {
								Root_of_Browse_City_of_Company_Frame_JMenu.add(Root_of_Browse_City_of_Company_Frame_JMenuItem[i]);
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//航空公司加入JMenu//
				for(i=0;i<=number-1;i++)  {
					Root_of_Browse_City_of_Company_Frame_JMenuItem[i].addActionListener(new ActionListener()
							{
						public void actionPerformed(ActionEvent event)
						{
							JMenuItem clicked=(JMenuItem)event.getSource();
							JTable Root_of_Browse_City_of_Company_Frame_table=new JTable(new AbstractTableModel()
							{
						private String columnName[]= {"城市序号","城市名","经度","纬度","客运需求等级"};
						Airplane_Company read()  {
							Airplane_Company Root_of_Browse_City_of_Company_Frame_company=null;
							try {
								FileInputStream Root_of_Browse_City_of_Company_Frame_fis=new FileInputStream("src\\managerfile\\List_of_Company.txt");
								try {
									ObjectInputStream Root_of_Browse_City_of_Company_Frame_ois=new ObjectInputStream(Root_of_Browse_City_of_Company_Frame_fis);
									RandomAccessFile Root_of_Browse_City_of_Company_Frame_raf=new RandomAccessFile("src\\managerfile\\Number_of_Company.txt","r");
									int aP_number=Root_of_Browse_City_of_Company_Frame_raf.readInt();
									aP_number--;
									Root_of_Browse_City_of_Company_Frame_raf.close();
									int j=0;
									for(j=0;j<=aP_number-1;j++)  {
										try {
											Root_of_Browse_City_of_Company_Frame_company=(Airplane_Company)Root_of_Browse_City_of_Company_Frame_ois.readObject();
											if(Root_of_Browse_City_of_Company_Frame_company.getName_of_Company().equals(clicked.getText())==true)  {
												break;
											}
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									Root_of_Browse_City_of_Company_Frame_ois.close();
									//读取到正确的航空公司//
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return Root_of_Browse_City_of_Company_Frame_company;
						}
						public String getColumnName(int column)  {
							return columnName[column];
						}
						public int getColumnCount()  {
							return 5;
						}
						public int getRowCount()  {
							Airplane_Company Root_of_Browse_City_of_Company_Frame_company=null;
							try {
								FileInputStream Root_of_Browse_City_of_Company_Frame_fis=new FileInputStream("src\\managerfile\\List_of_Company.txt");
								try {
									ObjectInputStream Root_of_Browse_City_of_Company_Frame_ois=new ObjectInputStream(Root_of_Browse_City_of_Company_Frame_fis);
									RandomAccessFile Root_of_Browse_City_of_Company_Frame_raf=new RandomAccessFile("src\\managerfile\\Number_of_Company.txt","r");
									int aP_number=Root_of_Browse_City_of_Company_Frame_raf.readInt();
									aP_number--;
									Root_of_Browse_City_of_Company_Frame_raf.close();
									int j=0;
									for(j=0;j<=aP_number-1;j++)  {
										try {
											Root_of_Browse_City_of_Company_Frame_company=(Airplane_Company)Root_of_Browse_City_of_Company_Frame_ois.readObject();
											if(Root_of_Browse_City_of_Company_Frame_company.getName_of_Company().equals(clicked.getText())==true)  {
												break;
											}
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									Root_of_Browse_City_of_Company_Frame_ois.close();
									//读取到正确的航空公司//
									
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return Root_of_Browse_City_of_Company_Frame_company.getNumber_of_CityServed();
						}
						public Object getValueAt(int row,int col)  {
							Airplane_Company t=read();
							City[] getValue_city=new City[t.getNumber_of_CityServed()];
							try {
								FileInputStream getValue_fis=new FileInputStream("src\\managerfile\\List_of_City_of_"+t.getName_of_Company()+".txt");
								try {
									ObjectInputStream getValue_ois=new ObjectInputStream(getValue_fis);
									int i=0;
									for(i=0;i<=t.getNumber_of_CityServed()-1;i++)  {
										try {
											getValue_city[i]=(City)getValue_ois.readObject();
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(col==0)  {
								return getValue_city[row].getKey_of_City();
							}else if(col==1)  {
								return getValue_city[row].getName_of_City();
							}else if(col==2)  {
								return getValue_city[row].getLocation_E();
							}else if(col==3)  {
								return getValue_city[row].getLocation_N();
							}else  {
								return getValue_city[row].getLevel_of_City();
							}
						}
							});
							JScrollPane scrollpane=new JScrollPane(Root_of_Browse_City_of_Company_Frame_table);
							Root_of_Browse_City_of_Company_Frame_panel.removeAll();
							Root_of_Browse_City_of_Company_Frame_panel.setLayout(new BorderLayout());
							Root_of_Browse_City_of_Company_Frame_panel.add(scrollpane,BorderLayout.CENTER);
							Root_of_Browse_City_of_Company_Frame_panel.revalidate();
							Root_of_Browse_City_of_Company_Frame_panel.repaint();
						}
							});
				}
				setSize(400,300);
				setLocation(200,200);
				setLayout(new GridLayout(1,1));
				Root_of_Browse_City_of_Company_Frame_JMenuBar.add(Root_of_Browse_City_of_Company_Frame_JMenu);
				setJMenuBar(Root_of_Browse_City_of_Company_Frame_JMenuBar);
				Root_of_Browse_City_of_Company_Frame_panel.setSize(400,250);
				Root_of_Browse_City_of_Company_Frame_panel.setLocation(0,50);
				add(Root_of_Browse_City_of_Company_Frame_panel);
			}
		}