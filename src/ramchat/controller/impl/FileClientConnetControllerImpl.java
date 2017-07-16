package ramchat.controller.impl;

import java.awt.image.BufferedImage;

import ramchat.model.service.impl.FileClientConnectServiceImpl;
import ramchat.view.joptionpane.FailView;

public class FileClientConnetControllerImpl {
	private FileClientConnectServiceImpl fccs = new FileClientConnectServiceImpl();	

	public BufferedImage getImg(String id) {
		BufferedImage img=null;
		try{
			img = fccs.getImg(id);
		} catch(Exception e) {
			FailView.failMessage(e.getMessage());
		}
		return img;
	}
}
