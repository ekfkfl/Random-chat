package ramchat.model.dao;

import java.sql.SQLException;

public interface ChatDao {
	
	int dailyHeart(String id) throws SQLException;
	
	/** 전체 하트 가져오기 */
	public int totalHeart(String id) throws SQLException;
	
	/**
	 * 채팅 하트 보내기
	 * 받는 사람 , 보낸는 사람 id
	 */
	public int sendHeart(String receiveId, String sendId)throws SQLException;
	/**
	 * 채팅 하트 받기
	 */
	public int receiveHeart(String receiveId, String sendId)throws SQLException;
}
