package ramchat.model.dao.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;

public class FileClientConnectDaoImpl {

	public BufferedImage getImg(String id) throws IOException {
		Socket sk = null;
		InputStream is = null;
		BufferedImage img = null;
		PrintWriter pw = null;

		try {
			sk= new Socket("192.168.0.36", 7002);
			pw = new PrintWriter(sk.getOutputStream(), true);
			pw.println(id);
			
			is = sk.getInputStream();
			img=ImageIO.read(is);
		} finally {
			if (is != null)
				is.close();
			if (sk != null)
				sk.close();
		}

		return img;
	}
}
