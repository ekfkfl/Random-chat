package ramchat.controller.impl;

import java.sql.SQLException;

import ramchat.controller.LoginController;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.LoginService;
import ramchat.model.service.impl.LoginServiceImpl;
import ramchat.view.joptionpane.FailView;
import ramchat.view.joptionpane.SuccessView;

public class LoginControllerImpl implements LoginController {

	private LoginService loginService = new LoginServiceImpl();

	public UserInfoDTO getUserInfo(String id, String pw) {
		UserInfoDTO userInfoDto = null;
		try {
			UserHeartSetting(id);
			userInfoDto = loginService.getUserInfo(id, pw);
			SuccessView.successMessage("로그인에 성공하였습니다.");

		} catch (Exception e) {
			FailView.failMessage(e.getMessage());
		}
		return userInfoDto;
	}

	public void UserHeartSetting(String id) {
		try {
			loginService.UserHeartSetting(id);
		} catch (SQLException e) {
			FailView.failMessage(e.getMessage());
		}
	}

}
