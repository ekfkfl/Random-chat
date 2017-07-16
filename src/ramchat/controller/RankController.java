package ramchat.controller;

import java.util.List;

import ramchat.model.dto.UserInfoDTO;

public interface RankController {
	/**
	 * �ϰ� ��ũ
	 */
	public List<UserInfoDTO> dailyRank(int gender);
	/**
	 * �ְ� ��ũ
	 */
	public List<UserInfoDTO> weeklyRank(int gender);
	/**
	 * ���� ��ũ
	 */
	public List<UserInfoDTO> monthlyRank(int gender);
}
