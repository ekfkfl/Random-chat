package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface LoginDao {
	/**
	 *�α��� - ���� ���� ��ȯ
	 */
	public UserInfoDTO getUserInfo(String id, String pw)throws SQLException;
	
	public int UserHeartSetting (String id) throws SQLException;
	
}
