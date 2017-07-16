package ramchat.controller.impl;

import java.nio.channels.ShutdownChannelGroupException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ramchat.controller.JoinController;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.JoinService;
import ramchat.model.service.impl.JoinServiceImpl;
import ramchat.view.joptionpane.FailView;
import ramchat.view.joptionpane.SuccessView;

public class JoinControllerImpl implements JoinController {

	JoinService joinService = new JoinServiceImpl();

	@Override
	public boolean getIdByCheck(String id) {
		try {
			joinService.getIdByCheck(id);
			SuccessView.successMessage(id + " 는 사용 가능한 아이디 입니다");
		} catch (SQLException e) {
			FailView.failMessage(id + " 는 사용 불가능한 아이디 입니다");
		}
		return true;
	}

	@Override
	public int insertUserInfo(UserInfoDTO userInfo) {
		int result=0;
		try {
			result=joinService.insertUserInfo(userInfo);
			SuccessView.successMessage("회원 가입에 성공 하였습니다.");
		} catch (SQLException e) {
			FailView.failMessage("회원 가입에 실패하였습니다.");
		}
		return result;
	}

}
