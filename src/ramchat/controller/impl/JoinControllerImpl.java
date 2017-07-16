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
			SuccessView.successMessage(id + " �� ��� ������ ���̵� �Դϴ�");
		} catch (SQLException e) {
			FailView.failMessage(id + " �� ��� �Ұ����� ���̵� �Դϴ�");
		}
		return true;
	}

	@Override
	public int insertUserInfo(UserInfoDTO userInfo) {
		int result=0;
		try {
			result=joinService.insertUserInfo(userInfo);
			SuccessView.successMessage("ȸ�� ���Կ� ���� �Ͽ����ϴ�.");
		} catch (SQLException e) {
			FailView.failMessage("ȸ�� ���Կ� �����Ͽ����ϴ�.");
		}
		return result;
	}

}
