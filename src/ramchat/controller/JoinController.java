package ramchat.controller;

import ramchat.model.dto.UserInfoDTO;

public interface JoinController {
	/**
	 * ID 중복체크
	 * @return 
	 */
	public boolean getIdByCheck(String id);
	/**
	 *회원가입 - int 반환 
	 * @return 
	 */
	public int insertUserInfo(UserInfoDTO userInfo);
}
