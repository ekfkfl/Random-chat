package ramchat.model.service;

import java.sql.SQLException;

public interface ChatService {
	
	int dailyHeart(String id) throws SQLException;
	
	/** ��Ʈ �������� */
	public int totalHeart(String id) throws SQLException;
	
	/**
	 * ä�� ��Ʈ ������
	 */
	public int sendHeart(String receiveId, String sendId)throws SQLException;
	/**
	 * ä�� ��Ʈ �ޱ�
	 */
	public int receiveHeart(String receiveId, String sendId)throws SQLException;
}
