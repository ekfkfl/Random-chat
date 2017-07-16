package ramchat.controller;

import java.sql.SQLException;

public interface ChatController {

	int dailyHeart(String id);

	/** 하트 개수 가져오기 */
	public int totalHeart(String id);

	/**
	 * 채팅 하트 보내기
	 */
	public int sendHeart(String receiveId, String sendId);

	/**
	 * 채팅 하트 받기
	 */
	public int receiveHeart(String receiveId, String sendId);

}
