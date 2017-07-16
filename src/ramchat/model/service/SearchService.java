package ramchat.model.service;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface SearchService {
	/**
	 * ID ã��
	 */
	public UserInfoDTO searchId(String name, String email)throws SQLException;
	/**
	 * Password ã��
	 */
	public UserInfoDTO searchPw(String id, String name, String email)throws SQLException;
}
