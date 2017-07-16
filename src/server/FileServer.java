package server;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	ServerSocket serverSocket = null;
	Socket sk = null;
	PrintWriter pw;
	BufferedReader br;
	FileOutputStream fos = null;
	InputStreamReader is = null;
	String id;
	
	public FileServer() {
		// TODO Auto-generated constructor stub
		try {
			System.out.println("7001 포트 접속 대기 중");
			serverSocket = new ServerSocket(7001);
            while(true){
            	
	            sk = serverSocket.accept();
	            
				br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				
				id = br.readLine();
				
	            InputStream inputStream = sk.getInputStream();
	            fos = new FileOutputStream("src/userResource/"+id+".jpg");
	            
	            byte[] dataBuff = new byte[10000];
	            int length = inputStream.read(dataBuff);
	            System.out.println(" 이미지 다운중");
	            while(length != -1){
	            	System.out.println(".");
	            	fos.write(dataBuff,0,length);
	            	length = inputStream.read(dataBuff);
	            	
		            
	            }
	            System.out.println();
	            System.out.println("파일 저장 성공");
	            
	            try{
	            	throw new Exception();
	            }catch(Exception e){
	            	e.printStackTrace();
	            }finally{
	            	if (fos != null) {
		                try {
		                    fos.close();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		 
		            }
		            if (is != null) {
		                try {
		                    is.close();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            }
		            if (sk != null) {
		                try {
		                    sk.close();
		                } catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            }
	            }
	            
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
			 if (fos != null) {
	                try {
	                    fos.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	 
	            }
	            if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            if (sk != null) {
	                try {
	                    sk.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
		}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FileServer();
        
	}
}
