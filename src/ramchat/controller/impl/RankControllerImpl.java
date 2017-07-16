package ramchat.controller.impl;

import java.util.List;

import ramchat.controller.RankController;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.RankService;
import ramchat.model.service.impl.RankServiceImpl;
import ramchat.view.joptionpane.FailView;
import ramchat.view.joptionpane.SuccessView;

public class RankControllerImpl implements RankController {
	private RankService rankService = new RankServiceImpl();
	
	private static RankController rankController = new RankControllerImpl(); 
	
	private RankControllerImpl() {}
	
	public static RankController getRankController() {
		return rankController;
	}
	
	public List<UserInfoDTO> dailyRank(int gender) {
		List<UserInfoDTO> list=null;
		try {
			list=rankService.dailyRank(gender);
		} catch(Exception e) {
		}
		return list;
	}

	public List<UserInfoDTO> weeklyRank(int gender) {
		List<UserInfoDTO> list=null;
		try {
			list=rankService.weeklyRank(gender);
		} catch(Exception e) {
		}
		return list;
	}

	public List<UserInfoDTO> monthlyRank(int gender) {
		List<UserInfoDTO> list=null;
		try {
			list=rankService.monthlyRank(gender);
		} catch(Exception e) {
		}
		return list;
	}

}
