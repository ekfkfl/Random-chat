package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface JoinDao {
	/**
	 * ID �ߺ�üũ
	 * false = ����(�ߺ� o)
	 * true = ����(�ߺ� X)
	 */
	public boolean getIdByCheck(String id)throws SQLException;
	/**
	 *ȸ������ - int ��ȯ 
	 */
	public int insertUserInfo(UserInfoDTO userInfo)throws SQLException;
}
