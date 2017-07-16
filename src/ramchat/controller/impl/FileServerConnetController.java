package ramchat.controller.impl;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ramchat.model.service.impl.FileServerConnectService;

public class FileServerConnetController {
	FileServerConnectService fileServerConn;
	
	public FileServerConnetController() {
		fileServerConn = new FileServerConnectService();
	}
	
	public Socket getSocket(){
		return fileServerConn.getSocket();
	}
	
	public PrintWriter getPrintWriter(){
		return fileServerConn.getPrintWriter();
	}
	public BufferedReader getBufferedReader(){
		return fileServerConn.getBufferedReader();
	}
}
