package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface MypageDao {
	/**
	 * ȸ�� ���� ����
	 */
	public int updateUserInfo(String id, String pw, String email, String cpw)throws SQLException;
}
