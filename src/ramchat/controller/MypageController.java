package ramchat.controller;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface MypageController {
	/**
	 * ȸ�� ���� ����
	 */
	public int updateUserInfo(String id, String pw, String email, String cpw)throws SQLException;
}
