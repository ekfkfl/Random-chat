package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface SearchDao {
	/**
	 * ID 찾기
	 */
	public UserInfoDTO searchId(String name, String email)throws SQLException;
	/**
	 * Password 찾기
	 */
	public UserInfoDTO searchPw(String id, String name, String email)throws SQLException;
}
