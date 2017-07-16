package ramchat.model.service;

import java.sql.SQLException;
import java.util.List;

import ramchat.model.dto.UserInfoDTO;

public interface RankService {
	/**
	 * �ϰ� ��ũ
	 */
	public List<UserInfoDTO> dailyRank(int gender)throws SQLException;
	/**
	 * �ְ� ��ũ
	 */
	public List<UserInfoDTO> weeklyRank(int gender)throws SQLException;
	/**
	 * ���� ��ũ
	 */
	public List<UserInfoDTO> monthlyRank(int gender)throws SQLException;
}
