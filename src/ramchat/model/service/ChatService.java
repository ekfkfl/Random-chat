package ramchat.model.service;

import java.sql.SQLException;

public interface ChatService {
	
	int dailyHeart(String id) throws SQLException;
	
	/** 하트 가져오기 */
	public int totalHeart(String id) throws SQLException;
	
	/**
	 * 채팅 하트 보내기
	 */
	public int sendHeart(String receiveId, String sendId)throws SQLException;
	/**
	 * 채팅 하트 받기
	 */
	public int receiveHeart(String receiveId, String sendId)throws SQLException;
}
