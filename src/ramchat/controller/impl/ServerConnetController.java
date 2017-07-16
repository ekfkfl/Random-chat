package ramchat.controller.impl;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ramchat.model.service.impl.ServerConnectService;

public class ServerConnetController {
	ServerConnectService serverConn;
	
	public ServerConnetController() {
		serverConn = new ServerConnectService();
	}
	
	public Socket getSocket(){
		return serverConn.getSocket();
	}
	
	public PrintWriter getPrintWriter(){
		return serverConn.getPrintWriter();
	}
	public BufferedReader getBufferedReader(){
		return serverConn.getBufferedReader();
	}
}
