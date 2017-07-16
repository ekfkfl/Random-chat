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
				System.out.println("7000 포트 접속 대기중");
				sk = server.accept();
				System.out.println(sk.getInetAddress()+"님이 접속되었습니다.");
				
//				if( emptyRoom.size()-10 < 0){
//					for(int i=1;i<=100;i++){
//						emptyRoom.add(emptyRoom.get(emptyRoom.size()-1)+i);
//					}
//				}

				st = new ServerThread();
				
				//남자,여자 리스트에 저장
				if(st.gender.equals("남자"))
					maleList.add(st);
				else
					femaleList.add(st);
				
				//남여 매칭
				match();
				System.out.println(map);
				
				st.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}//생성자 끝
	
	/**
	 * 접속된 모든 클라이언트에게 데이터 보내기(전송)
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
	 * 남자, 여자 매칭하기
	 */
	public synchronized void match(){
		
		if(map.size() ==0){ //방이 없으면 방 생성 
			st.roomNo = emptyRoom.get(0);
			usingRoom.add(emptyRoom.get(0));
			emptyRoom.remove(0);
			if(st.gender.equals("남자")){
				userList.add(maleList.get(0));
				maleList.remove(0);
			} else{
				userList.add(femaleList.get(0));
				femaleList.remove(0);
			}
			map.put(st.roomNo,userList );
		}
		else if(st.gender.equals("남자")){	//들어온 사람이 남자이면
			Set s = map.keySet();
			Iterator<Integer> it = s.iterator();
			while(it.hasNext()){
				int key = it.next();
				CopyOnWriteArrayList<ServerThread> l =map.get(key);
				
				if(l.size() ==1 &&  l.get(0).gender.equals("여자")){ //방에 혼자 여자가 있으면
					st.randomList.add(key);
				} 
			}
			if(st.randomList.size() == 0){ //들어갈 방이 없으면
				st.roomNo = emptyRoom.get(0);
				usingRoom.add(emptyRoom.get(0));
				emptyRoom.remove(0);
				userList = new CopyOnWriteArrayList<>();
				userList.add(maleList.get(0));
				maleList.remove(0);
				map.put(st.roomNo, userList);
			} else {										//randomList가 0이 아니면
				int randomResult = (int)(Math.random()*st.randomList.size());
				int randomkey = st.randomList.get(randomResult);
				CopyOnWriteArrayList<ServerThread> l = map.get(randomkey);
				l.add(maleList.get(0));
				maleList.remove(0);
				st.roomNo = randomkey;
				map.put(randomkey, l);
			}
			
		} //남자 방 집어넣기 끝
		
		else if(st.gender.equals("여자")){ //들어온 사람이 여자면
			Set<Integer> keySet = map.keySet();
			Iterator<Integer> it = keySet.iterator();
			while(it.hasNext()){
				int key = it.next();
				CopyOnWriteArrayList<ServerThread> l =map.get(key);
				if(l.size() == 1 && l.get(0).gender.equals("남자")){   //방에 남자 혼자있으면

					st.randomList.add(key);
				} 
			}
			if(st.randomList.size() == 0) { //들어갈 방이 없으면
				st.roomNo = emptyRoom.get(0);
				usingRoom.add(emptyRoom.get(0));
				emptyRoom.remove(0);
				userList = new CopyOnWriteArrayList<>();
				userList.add(femaleList.get(0));
				femaleList.remove(0);
				map.put(st.roomNo, userList);
			} else {											//randomList가 0이 아니면
				int randomResult = (int)(Math.random()*st.randomList.size());
				int randomkey = st.randomList.get(randomResult);
				CopyOnWriteArrayList<ServerThread> l = map.get(randomkey);
				l.add(femaleList.get(0));
				femaleList.remove(0);
				st.roomNo = randomkey;
				map.put(randomkey, l);
			}
		}
	}//match 끝
	
	/**
	 * 접속된 클라이언트를 스레드로 관리하기 위한 class
	 */
	class ServerThread extends Thread {
		BufferedReader br;
		PrintWriter pw;
		String id; //대화명 저장
		int roomNo;
		String gender;
		String readGender;
		List<Integer> randomList = new ArrayList<>();
		String firstId;
		String secondId;
		
		/**
		 * 생성자
		 */
		public ServerThread() {
			try{
				//클라이언트가 보내오는 데이터 읽기
				br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				
				//접속된 클라리언트에게 데이터 보내기
				pw = new PrintWriter(sk.getOutputStream(),true);
				
				
				id = br.readLine();
				readGender = br.readLine();
				
				
				if(readGender != null){
					if(readGender.equals("1")){
						gender = "남자";
					} else {
						gender = "여자";
					}
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		} //생성자 끝
		
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
						sendMessage("["+firstId+"("+list.get(0).gender+")"+"] 님 입장하였습니다.",roomNo);
		                sendMessage("["+secondId+"("+list.get(1).gender+")"+"] 님 입장하였습니다.",roomNo);
					}
				} //request 끝
				//채팅시작
				while((data=br.readLine())!=null){
					if(data.equals("님이 시간연장 요청을 했습니다.")){
						List<ServerThread> ll = map.get(roomNo);
						ll.get(0).pw.println("시간이 추가되었습니다.");
						ll.get(1).pw.println("시간이 추가되었습니다.");
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
				// 클라이언트에게 알려줌.
				sendMessage("["+id+"]님 퇴장하셨습니다. 채팅이 종료됩니다.",roomNo);
				
				//현재 스레드를 list에서 제거
				//방번호 반환
				for(int i=0;i<usingRoom.size();i++){
					if(usingRoom.get(i) == this.roomNo){
						emptyRoom.add(usingRoom.get(i));
						usingRoom.remove(i);
						break;
					}
				}
				try{
					//list에서 제거
					CopyOnWriteArrayList<ServerThread> cowa = map.get(this.roomNo);
					cowa.removeAll(cowa);
					//map에서 제거
					map.remove(this.roomNo);
				}catch(NullPointerException ne){
					System.out.println(this.roomNo+"번방 깨짐");
					System.out.println(map);
				}
			}
		}//run() 끝
	}//serverThread 끝
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OneToOneChat();
	}

}//클래스끝
