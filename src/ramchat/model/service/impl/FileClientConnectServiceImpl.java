package ramchat.model.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import ramchat.model.dao.impl.FileClientConnectDaoImpl;

public class FileClientConnectServiceImpl {
	private FileClientConnectDaoImpl fccd = new FileClientConnectDaoImpl();
	
	public BufferedImage getImg(String id) throws IOException {
		BufferedImage img = fccd.getImg(id);
		if(img==null) {
			throw new IOException("해당 아이디는 사진이 존재하지 않습니다.");
		}
		return img;
	}
}
