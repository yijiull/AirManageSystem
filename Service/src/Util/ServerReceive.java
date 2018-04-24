package Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Pair;
import model.User;
import view.ServiceFrm;

public class ServerReceive extends Thread {

	private User user;
	private List<Integer> listId;

	public List<User> userList = new ArrayList<User>();

	// 航班映射
	public Map<Pair, Integer> map;

	// 队列的链表
	public List<Queue> waitQueueList;

	// 当前排队数量
	public int curCnt;

	public ServerReceive() {
		curCnt = 0;
		waitQueueList = new ArrayList<>();
		map = new HashMap<>();
	}

	public ServerReceive(User user) {
		curCnt = 0;
		map = new HashMap<>();
		waitQueueList = new ArrayList<>();
		this.user = user;
	}

	public ServerReceive(User user, List<User> userList) {
		super();
		map = new HashMap<>();
		waitQueueList = new ArrayList<>();
		curCnt = 0;
		this.user = user;
		this.userList = userList;
	}

	

	public ServerReceive(User user, List<User> userList, Map<Pair, Integer> map, List<Queue> waitQueueList,
			int curCnt) {
		super();
		this.user = user;
		this.userList = userList;
		this.map = map;
		this.waitQueueList = waitQueueList;
		this.curCnt = curCnt;
	}

	@Override
	public void run() {
		while (!user.socket.isClosed()) {
			String op;
			try {
				op = (String) user.cin.readObject();
				// System.out.println(op);
				if (op.equals("修改信息")) {
					User u = (User) user.cin.readObject();

					/**
					 * 修改user文件
					 */
					StringBuffer sb = new StringBuffer();
					Scanner cin = new Scanner(FileUtil.newInstance().readFile("user"));
					while (cin.hasNext()) {
						String temp = cin.next();
						if (temp.equals(u.getUserName())) {
							sb.append(u.getUserName() + " ");
							cin.next();
							sb.append(u.getPassword() + "\r\n");
						} else {
							sb.append(temp + " " + cin.next() + "\r\n");
						}
					}
					cin.close();
					FileUtil.newInstance().addFile("user", sb.substring(0, sb.length() - 2), false);

					/**
					 * 修改个人信息文件
					 */
					sb = new StringBuffer(u.toString() + "\r\n");
					cin = new Scanner(FileUtil.newInstance().readFile(u.getUserName()));
					for (int i = 0; i < 5; i++)
						cin.next();
					while (cin.hasNext()) {
						sb.append(cin.next());
					}
					FileUtil.newInstance().addFile(u.getUserName(), sb.substring(0, sb.length() - 2), false);
					user.cout.writeObject("修改成功");
					user.cout.flush();
				} else if (op.equals("注销")) {
					user.cout.writeObject("注销");
					user.cout.flush();
					
					break;
				} else if (op.equals("查询")) {
					String stPlace = (String) user.cin.readObject();
					String edPlace = (String) user.cin.readObject();
					long date = user.cin.readLong();

					File file = (File) ServiceFrm.bt.get(date);
					if (file == null) {
						user.cout.writeObject("查询为空");
						continue;
					}
					System.out.println(file.getName());
					String temp = file.getName();
					int id = Integer.parseInt(temp.substring(0, temp.length() - 4));
					System.out.println("文件名  " + id);
					listId = new ArrayList<>();
					int ok = 1;
					List<Vector<String>> list = new ArrayList<>();
					while (true) {
						File tempFile = new File("src\\file\\index\\" + id + ".txt");
						if (tempFile.exists() == false)
							break;
						Scanner cin = new Scanner(FileUtil.newInstance().readFile("index\\"+id));
						while (cin.hasNext()) {
							String time = cin.next();
							System.out.println(time);
							int n = cin.nextInt();
							System.out.println("第n趟航班：" + n);
							long fg = Long.parseLong(time.substring(0, 8));
							if (fg > Long.parseLong(new String(String.valueOf(date)).substring(0, 8))) {
								ok = 0;
								break;
							}
							Scanner info = new Scanner(FileUtil.newInstance().getAirInfo(n));
							Vector<String> v = new Vector<>();
							for (int i = 0; i < 12; i++)
								v.add(info.next());
							info.close();
							if (Long.parseLong(v.get(4)) >= date && v.get(2).equals(stPlace)
									&& v.get(3).equals(edPlace)) {
								list.add(v);
								listId.add(n);
							}
						}
						cin.close();
						if (ok == 0)
							break;
						id++;
					}
					user.cout.writeObject("查询航班结果");
					user.cout.flush();
					user.cout.writeObject(list);
					user.cout.writeObject(listId);
					user.cout.flush();
				}else if(op.equals("排队抢票")){
					int id = user.cin.readInt();
					int level = user.cin.readInt();
					Pair pair = new Pair(id,level);
					System.out.println("map = " + map);
					if(!map.containsKey(pair)) {
						System.out.println("不含！！！！");
						map.put(pair, curCnt++);
						//新建侯票队列
						Queue<User> queue = new LinkedList<>();
						queue.add(user);
						waitQueueList.add(queue);
						System.out.println(queue.toString());
					}else {
						int temp = map.get(pair);
						Queue<User> queue = waitQueueList.get(temp);
						queue.add(user);
					}
					System.out.println(pair + "  " + waitQueueList.size());
					
				}else if (op.equals("购买")) {
					int id = user.cin.readInt();
					int level = user.cin.readInt();
					int left = FileUtil.newInstance().getCnt(id, level);
					System.out.println("剩余    = "+left);
					if(left <= 0) {
						
						user.cout.writeObject("售罄");
						user.cout.flush();
						continue;
					}
					FileUtil.newInstance().modifyCnt(id, level, -1);
					Scanner cin = new Scanner(FileUtil.newInstance().getAirInfo(id));
					Vector<String> v = new Vector<>();
					String lv[] = { "First_class", "Business_Class", "Economy_class" };
					while (cin.hasNext())
						v.add(cin.next());
					cin.close();
					FileUtil.newInstance().addFile(
							user.getUserName(), v.get(1) + " " + v.get(0) + " " + v.get(2) + " " + v.get(3) + " "
									+ v.get(4) + " " + lv[level] + " " + v.get(7 + level * 2) + " " + id + " " + level,
							true);
					user.cout.writeObject("购买成功");
					user.cout.flush();
			
				}else if(op.equals("查询订单")) {
					User u = (User) user.cin.readObject();
					List<Vector<String>> list = new ArrayList<>();
					Scanner cin = new Scanner(FileUtil.newInstance().readFile(u.getUserName()));
					for (int i = 0; i < 5; i++)
						cin.next();
					while (cin.hasNext()) {
						Vector<String> v = new Vector<String>();
						for (int i = 0; i < 9; i++) {
							v.add(cin.next());
						}
						list.add(v);
					}
					cin.close();
					user.cout.writeObject("返回订单");
					user.cout.flush();
					user.cout.writeObject(list);
					user.cout.flush();
				}else if(op.equals("取消订单")) {
					
					int row = user.cin.readInt();
					int n = user.cin.readInt();
					int level = user.cin.readInt();
					
					Scanner cin = new Scanner(FileUtil.newInstance().readFile(user.getUserName()));
					
					user.setName(cin.next());
					user.setID(cin.next());
					user.setPhone(cin.next());
					cin.next();
					user.setPassword(cin.next());
					cin.close();
					List<Vector<String>> list = new ArrayList<>();
					cin = new Scanner(FileUtil.newInstance().readFile(user.getUserName()));
					for (int i = 0; i < 5; i++)
						cin.next();
					while (cin.hasNext()) {
						Vector<String> v = new Vector<String>();
						for (int i = 0; i < 9; i++) {
							v.add(cin.next());
						}
						list.add(v);
					}
					cin.close();
					
					FileUtil.newInstance().addFile(user.getUserName(), user.toString(), false);
					FileUtil.newInstance().modifyCnt(n, level, 1);
					
					for (int i = 0; i < list.size(); i++) {
						if (i != row) {
							Vector<String> v = list.get(i);
							FileUtil.newInstance().addFile(user.getUserName(), v.get(0) + " " + v.get(1) + " " + v.get(2) + " " + v.get(3) + " "
									+ v.get(4) + " " + v.get(5) + " " + v.get(6) + " " + v.get(7) + " " + v.get(8), true);
						}
					}
					user.cout.writeObject("取消成功");
					user.cout.flush();
					
					int left = FileUtil.newInstance().getCnt(n, level);
					
					System.out.println("left after concel = " + left);
					if(left == 1) {
						Pair pair = new Pair(n, level);
						if(map.containsKey(pair)) {
							System.out.println("抢票 pair = "+pair);
							int temp = map.get(pair);
							Queue<User> queue = waitQueueList.get(temp);
							while(!queue.isEmpty()) {
								User frontUser = queue.poll();
								//如果队首客户端已经关闭，则往后取
								if(frontUser.socket.isClosed()) {
									continue;
								}
								System.out.println("抢票  name  = "+frontUser.getName());
								/*if(queue.size() == 0) {
									waitQueueList.remove(queue);
									map.remove(pair);
								}	*/
								FileUtil.newInstance().modifyCnt(n, level, -1);
								cin  = new Scanner(FileUtil.newInstance().getAirInfo(n));
								Vector<String> v = new Vector<>();
								String lv[] = { "First_class", "Business_Class", "Economy_class" };
								while (cin.hasNext())
									v.add(cin.next());
								cin.close();
								FileUtil.newInstance().addFile(
										frontUser.getUserName(), v.get(1) + " " + v.get(0) + " " + v.get(2) + " " + v.get(3) + " "
												+ v.get(4) + " " + lv[level] + " " + v.get(7 + level * 2) + " " + n + " " + level,
										true);	
								
								frontUser.cout.writeObject("抢票成功");
								frontUser.cout.flush();
								frontUser.cout.writeObject(v);
								frontUser.cout.flush();	
								break;
							}
						}else {
							System.out.println("no wait  :" + pair);
						}
						
						
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			user.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
