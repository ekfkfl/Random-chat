package ramchat.model.dao;

import java.sql.SQLException;
import java.util.List;

import ramchat.model.dto.UserInfoDTO;

public interface RankDao {
	/**
	 * ÀÏ°£ ·©Å©
	 */
	public List<UserInfoDTO> dailyRank(int gender)throws SQLException;
	/**
	 * ÁÖ°£ ·©Å©
	 */
	public List<UserInfoDTO> weeklyRank(int gender)throws SQLException;
	/**
	 * ¿ù°£ ·©Å©
	 */
	public List<UserInfoDTO> monthlyRank(int gender)throws SQLException;
}
