package ramchat.controller;

import ramchat.model.dto.UserInfoDTO;

public interface LoginController {
	/**
	 *로그인 - 유저 정보 반환
	 */
	public UserInfoDTO getUserInfo(String id, String pw);
	
	
	public void UserHeartSetting(String id);
	
	
}
