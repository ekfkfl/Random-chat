package ramchat.model.service;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface MypageService {
	/**
	 * ȸ�� ���� ����
	 */
	public int updateUserInfo(String id, String pw, String email, String cpw)throws SQLException;
}
