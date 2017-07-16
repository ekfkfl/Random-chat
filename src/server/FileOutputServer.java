package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileOutputServer {
	public FileOutputServer() {
		ServerSocket serverSocket = null;
		Socket sk = null;
		FileInputStream fis = null;
		OutputStream os = null;
		BufferedReader br = null;
		int length;
		byte[] data;

		try {
			serverSocket = new ServerSocket(7002);
			while (true) {
				System.out.println("7002 포트 접속 대기 중");
				sk = serverSocket.accept();

				os = sk.getOutputStream();
				br = new BufferedReader(new InputStreamReader(sk.getInputStream()));

				String str = br.readLine();

				try {
					fis = new FileInputStream("src/userResource/" + str + ".jpg");
					if (fis == null) {
						throw new Exception();
					}
					data = new byte[10000];
					length = fis.read(data);

					while (length != -1) {
						os.write(data, 0, length);
						length = fis.read(data);
					}

					if (br != null)
						br.close();
					if (fis != null)
						fis.close();
					if (os != null)
						os.close();
					if (sk != null)
						sk.close();
					System.out.println("전선");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (br != null)
						br.close();
					if (fis != null)
						fis.close();
					if (os != null)
						os.close();
					if (sk != null)
						sk.close();
				}
			} // while 끝
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileOutputServer();
	}

}
