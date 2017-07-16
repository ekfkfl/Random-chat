package ramchat.controller;

import java.util.List;

import ramchat.model.dto.UserInfoDTO;

public interface RankController {
	/**
	 * ÀÏ°£ ·©Å©
	 */
	public List<UserInfoDTO> dailyRank(int gender);
	/**
	 * ÁÖ°£ ·©Å©
	 */
	public List<UserInfoDTO> weeklyRank(int gender);
	/**
	 * ¿ù°£ ·©Å©
	 */
	public List<UserInfoDTO> monthlyRank(int gender);
}
