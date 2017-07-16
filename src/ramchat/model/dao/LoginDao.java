package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface LoginDao {
	/**
	 *로그인 - 유저 정보 반환
	 */
	public UserInfoDTO getUserInfo(String id, String pw)throws SQLException;
	
	public int UserHeartSetting (String id) throws SQLException;
	
}
