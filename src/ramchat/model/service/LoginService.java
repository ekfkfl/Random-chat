package ramchat.model.service;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface LoginService {
	/**
	 *�α��� - ���� ���� ��ȯ
	 */
	public UserInfoDTO getUserInfo(String id, String pw)throws SQLException;


	public void UserHeartSetting(String id) throws SQLException;
}
