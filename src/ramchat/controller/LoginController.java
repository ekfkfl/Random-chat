package ramchat.controller;

import ramchat.model.dto.UserInfoDTO;

public interface LoginController {
	/**
	 *�α��� - ���� ���� ��ȯ
	 */
	public UserInfoDTO getUserInfo(String id, String pw);
	
	
	public void UserHeartSetting(String id);
	
	
}
