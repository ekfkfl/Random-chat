package ramchat.model.dao;

import java.sql.SQLException;

public interface ChatDao {
	
	int dailyHeart(String id) throws SQLException;
	
	/** ��ü ��Ʈ �������� */
	public int totalHeart(String id) throws SQLException;
	
	/**
	 * ä�� ��Ʈ ������
	 * �޴� ��� , ������ ��� id
	 */
	public int sendHeart(String receiveId, String sendId)throws SQLException;
	/**
	 * ä�� ��Ʈ �ޱ�
	 */
	public int receiveHeart(String receiveId, String sendId)throws SQLException;
}
