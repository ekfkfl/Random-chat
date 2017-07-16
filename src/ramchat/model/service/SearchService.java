package ramchat.model.service;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface SearchService {
	/**
	 * ID 찾기
	 */
	public UserInfoDTO searchId(String name, String email)throws SQLException;
	/**
	 * Password 찾기
	 */
	public UserInfoDTO searchPw(String id, String name, String email)throws SQLException;
}
