package ramchat.controller;

import ramchat.model.dto.UserInfoDTO;

public interface JoinController {
	/**
	 * ID �ߺ�üũ
	 * @return 
	 */
	public boolean getIdByCheck(String id);
	/**
	 *ȸ������ - int ��ȯ 
	 * @return 
	 */
	public int insertUserInfo(UserInfoDTO userInfo);
}
