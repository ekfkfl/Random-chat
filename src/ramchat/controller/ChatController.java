package ramchat.controller;

import java.sql.SQLException;

public interface ChatController {

	int dailyHeart(String id);

	/** ��Ʈ ���� �������� */
	public int totalHeart(String id);

	/**
	 * ä�� ��Ʈ ������
	 */
	public int sendHeart(String receiveId, String sendId);

	/**
	 * ä�� ��Ʈ �ޱ�
	 */
	public int receiveHeart(String receiveId, String sendId);

}
