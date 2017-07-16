package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface SearchDao {
	/**
	 * ID ã��
	 */
	public UserInfoDTO searchId(String name, String email)throws SQLException;
	/**
	 * Password ã��
	 */
	public UserInfoDTO searchPw(String id, String name, String email)throws SQLException;
}
