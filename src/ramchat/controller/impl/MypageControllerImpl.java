package ramchat.controller.impl;

import java.sql.SQLException;

import ramchat.controller.MypageController;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.MypageService;
import ramchat.model.service.impl.MypageServiceImpl;
import ramchat.view.joptionpane.FailView;
import ramchat.view.joptionpane.SuccessView;

public class MypageControllerImpl implements MypageController {
	private MypageService mypageService = new MypageServiceImpl();
	//private static MypageController mypageController = new MypageControllerImpl();

	
	//public static MypageController getMypageController() {
	//	return mypageController;
	//}

	public int updateUserInfo(String id, String pw, String email, String cpw) {
		
		int result = 0;
		try {	
			result = mypageService.updateUserInfo(id, pw , email, cpw);
			SuccessView.successMessage("수정 되었습니다.");
		} catch (SQLException e) {
			FailView.failMessage(e.getMessage());
		}
		
		return result;
	}
}
