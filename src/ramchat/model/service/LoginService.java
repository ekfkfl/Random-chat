package ramchat.model.service;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface LoginService {
	/**
	 *로그인 - 유저 정보 반환
	 */
	public UserInfoDTO getUserInfo(String id, String pw)throws SQLException;


	public void UserHeartSetting(String id) throws SQLException;
}
