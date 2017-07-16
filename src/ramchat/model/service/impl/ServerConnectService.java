package ramchat.model.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import usersession.UserSession;

public class ServerConnectService {
	
	Socket sk;
	PrintWriter pw;
	BufferedReader br;
	
	public ServerConnectService() {
		// TODO Auto-generated constructor stub
		try{
			sk = new Socket("192.168.0.36", 7000);
			
			pw = new PrintWriter(sk.getOutputStream(),true);
			
			br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			
			pw.println(UserSession.getId());
			pw.println(UserSession.getGender());
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Socket getSocket(){
		return sk;
	}
	
	public PrintWriter getPrintWriter(){
		return pw;
	}
	public BufferedReader getBufferedReader(){
		return br;
	}
}
