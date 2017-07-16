package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import server.OneToOneChat.ServerThread;

public class OneToOneChat {
	ServerSocket server;
	Socket sk;
	CopyOnWriteArrayList<ServerThread> userList = new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<ServerThread> maleList = new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<ServerThread> femaleList = new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Integer> emptyRoom = new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Integer> usingRoom = new CopyOnWriteArrayList<>();
	ConcurrentHashMap<Integer, CopyOnWriteArrayList<ServerThread>> map = new ConcurrentHashMap<>();
	ServerThread st;
	Map<Integer,String> nameMap = new HashMap<>();
	
	public OneToOneChat() {
		
		for(int i=1;i<=300;i++){
			emptyRoom.add(i);
		}
		try{
			server = new ServerSocket(7000);
			while(true){
				System.out.println("7000 ��Ʈ ���� �����");
				sk = server.accept();
				System.out.println(sk.getInetAddress()+"���� ���ӵǾ����ϴ�.");
				
//				if( emptyRoom.size()-10 < 0){
//					for(int i=1;i<=100;i++){
//						emptyRoom.add(emptyRoom.get(emptyRoom.size()-1)+i);
//					}
//				}

				st = new ServerThread();
				
				//����,���� ����Ʈ�� ����
				if(st.gender.equals("����"))
					maleList.add(st);
				else
					femaleList.add(st);
				
				//���� ��Ī
				match();
				System.out.println(map);
				
				st.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}//������ ��
	
	/**
	 * ���ӵ� ��� Ŭ���̾�Ʈ���� ������ ������(����)
	 */
	public void sendMessage(String message,int roomNo) {
		
		if(map.containsKey(roomNo)){
			List<ServerThread> list = map.get(roomNo);
			
			for(ServerThread st : list){
				st.pw.println(message);
			}
		}
	}
	
	/**
	 * ����, ���� ��Ī�ϱ�
	 */
	public synchronized void match(){
		
		if(map.size() ==0){ //���� ������ �� ���� 
			st.roomNo = emptyRoom.get(0);
			usingRoom.add(emptyRoom.get(0));
			emptyRoom.remove(0);
			if(st.gender.equals("����")){
				userList.add(maleList.get(0));
				maleList.remove(0);
			} else{
				userList.add(femaleList.get(0));
				femaleList.remove(0);
			}
			map.put(st.roomNo,userList );
		}
		else if(st.gender.equals("����")){	//���� ����� �����̸�
			Set s = map.keySet();
			Iterator<Integer> it = s.iterator();
			while(it.hasNext()){
				int key = it.next();
				CopyOnWriteArrayList<ServerThread> l =map.get(key);
				
				if(l.size() ==1 &&  l.get(0).gender.equals("����")){ //�濡 ȥ�� ���ڰ� ������
					st.randomList.add(key);
				} 
			}
			if(st.randomList.size() == 0){ //�� ���� ������
				st.roomNo = emptyRoom.get(0);
				usingRoom.add(emptyRoom.get(0));
				emptyRoom.remove(0);
				userList = new CopyOnWriteArrayList<>();
				userList.add(maleList.get(0));
				maleList.remove(0);
				map.put(st.roomNo, userList);
			} else {										//randomList�� 0�� �ƴϸ�
				int randomResult = (int)(Math.random()*st.randomList.size());
				int randomkey = st.randomList.get(randomResult);
				CopyOnWriteArrayList<ServerThread> l = map.get(randomkey);
				l.add(maleList.get(0));
				maleList.remove(0);
				st.roomNo = randomkey;
				map.put(randomkey, l);
			}
			
		} //���� �� ����ֱ� ��
		
		else if(st.gender.equals("����")){ //���� ����� ���ڸ�
			Set<Integer> keySet = map.keySet();
			Iterator<Integer> it = keySet.iterator();
			while(it.hasNext()){
				int key = it.next();
				CopyOnWriteArrayList<ServerThread> l =map.get(key);
				if(l.size() == 1 && l.get(0).gender.equals("����")){   //�濡 ���� ȥ��������

					st.randomList.add(key);
				} 
			}
			if(st.randomList.size() == 0) { //�� ���� ������
				st.roomNo = emptyRoom.get(0);
				usingRoom.add(emptyRoom.get(0));
				emptyRoom.remove(0);
				userList = new CopyOnWriteArrayList<>();
				userList.add(femaleList.get(0));
				femaleList.remove(0);
				map.put(st.roomNo, userList);
			} else {											//randomList�� 0�� �ƴϸ�
				int randomResult = (int)(Math.random()*st.randomList.size());
				int randomkey = st.randomList.get(randomResult);
				CopyOnWriteArrayList<ServerThread> l = map.get(randomkey);
				l.add(femaleList.get(0));
				femaleList.remove(0);
				st.roomNo = randomkey;
				map.put(randomkey, l);
			}
		}
	}//match ��
	
	/**
	 * ���ӵ� Ŭ���̾�Ʈ�� ������� �����ϱ� ���� class
	 */
	class ServerThread extends Thread {
		BufferedReader br;
		PrintWriter pw;
		String id; //��ȭ�� ����
		int roomNo;
		String gender;
		String readGender;
		List<Integer> randomList = new ArrayList<>();
		String firstId;
		String secondId;
		
		/**
		 * ������
		 */
		public ServerThread() {
			try{
				//Ŭ���̾�Ʈ�� �������� ������ �б�
				br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				
				//���ӵ� Ŭ�󸮾�Ʈ���� ������ ������
				pw = new PrintWriter(sk.getOutputStream(),true);
				
				
				id = br.readLine();
				readGender = br.readLine();
				
				
				if(readGender != null){
					if(readGender.equals("1")){
						gender = "����";
					} else {
						gender = "����";
					}
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		} //������ ��
		
		@Override
		public void run() {
			try{
				String data = "";
				if((data=br.readLine()).equals("request")){
					List<ServerThread> list = map.get(roomNo);
					
					if(list.size() == 1){
						firstId = list.get(0).id;
						nameMap.put(roomNo, firstId);
					}
					if(list.size() == 2){
						firstId = nameMap.get(roomNo);
						secondId = list.get(1).id;
						list.get(0).pw.println(firstId);
						list.get(0).pw.println(secondId);
						list.get(1).pw.println(firstId);
						list.get(1).pw.println(secondId);
						sendMessage("["+firstId+"("+list.get(0).gender+")"+"] �� �����Ͽ����ϴ�.",roomNo);
		                sendMessage("["+secondId+"("+list.get(1).gender+")"+"] �� �����Ͽ����ϴ�.",roomNo);
					}
				} //request ��
				//ä�ý���
				while((data=br.readLine())!=null){
					if(data.equals("���� �ð����� ��û�� �߽��ϴ�.")){
						List<ServerThread> ll = map.get(roomNo);
						ll.get(0).pw.println("�ð��� �߰��Ǿ����ϴ�.");
						ll.get(1).pw.println("�ð��� �߰��Ǿ����ϴ�.");
					}
					if(data.equals("exit")){
						throw new Exception();
					}
					else if(data.equals("heart")){
						List<ServerThread> ll = map.get(roomNo);
		                  if(ll.get(0).id.equals(id)){
		                     ll.get(1).pw.println("heartup");
		                  } else if(ll.get(1).id.equals(id)){
		                     ll.get(0).pw.println("heartup");
		                  }
		            }
					else{
						sendMessage("["+id+"("+gender+")"+"]"+data,roomNo);
					}
					
					
				}
			} catch(Exception e){
				// Ŭ���̾�Ʈ���� �˷���.
				sendMessage("["+id+"]�� �����ϼ̽��ϴ�. ä���� ����˴ϴ�.",roomNo);
				
				//���� �����带 list���� ����
				//���ȣ ��ȯ
				for(int i=0;i<usingRoom.size();i++){
					if(usingRoom.get(i) == this.roomNo){
						emptyRoom.add(usingRoom.get(i));
						usingRoom.remove(i);
						break;
					}
				}
				try{
					//list���� ����
					CopyOnWriteArrayList<ServerThread> cowa = map.get(this.roomNo);
					cowa.removeAll(cowa);
					//map���� ����
					map.remove(this.roomNo);
				}catch(NullPointerException ne){
					System.out.println(this.roomNo+"���� ����");
					System.out.println(map);
				}
			}
		}//run() ��
	}//serverThread ��
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OneToOneChat();
	}

}//Ŭ������
