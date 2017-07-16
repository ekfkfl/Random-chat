package ramchat.controller;

import ramchat.model.dto.UserInfoDTO;

public interface SearchController {
	/**
	 * ID 찾기
	 */
	public boolean searchId(String name, String email);
	/**
	 * Password 찾기
	 */
	public boolean searchPw(String id, String name, String email);
}
