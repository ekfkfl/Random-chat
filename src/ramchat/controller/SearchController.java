package ramchat.controller;

import ramchat.model.dto.UserInfoDTO;

public interface SearchController {
	/**
	 * ID ã��
	 */
	public boolean searchId(String name, String email);
	/**
	 * Password ã��
	 */
	public boolean searchPw(String id, String name, String email);
}
