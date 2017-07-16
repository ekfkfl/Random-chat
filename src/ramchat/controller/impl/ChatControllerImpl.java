package ramchat.controller.impl;

import java.sql.SQLException;

import javax.xml.ws.FaultAction;

import ramchat.controller.ChatController;
import ramchat.model.service.ChatService;
import ramchat.model.service.impl.ChatServiceImpl;
import ramchat.view.joptionpane.FailView;

public class ChatControllerImpl implements ChatController {

	ChatService chatService =new ChatServiceImpl();
	
	 @Override
	   public int dailyHeart(String id)  {
	      int result=-1;
	      try {
	         result=chatService.dailyHeart(id);
	      } catch (SQLException e) {
	         FailView.failMessage("������Ʈ�� �� �Ҹ��ϼ̽��ϴ�");
	      }
	      
	      return result;
	   }

	
	@Override
	public int totalHeart(String id)  {
		int result=-1;
		try {
			result=chatService.totalHeart(id);
		} catch (SQLException e) {
			FailView.failMessage("��Ż ��Ʈ ����");
		}
		
		
		return result;
	}

	@Override
	public int sendHeart(String receiveId, String sendId) {
		int result=0;
		try {
			result=chatService.sendHeart(receiveId, sendId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public int receiveHeart(String receiveId, String sendId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
