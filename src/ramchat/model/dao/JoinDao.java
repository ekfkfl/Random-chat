package ramchat.model.dao;

import java.sql.SQLException;

import ramchat.model.dto.UserInfoDTO;

public interface JoinDao {
	/**
	 * ID 중복체크
	 * false = 실패(중복 o)
	 * true = 성공(중복 X)
	 */
	public boolean getIdByCheck(String id)throws SQLException;
	/**
	 *회원가입 - int 반환 
	 */
	public int insertUserInfo(UserInfoDTO userInfo)throws SQLException;
}
