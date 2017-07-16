package ramchat.model.service;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface MypageService {
	/**
	 * 회원 정보 수정
	 */
	public int updateUserInfo(String id, String pw, String email, String cpw)throws SQLException;
}
