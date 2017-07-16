package ramchat.model.service.impl;

import java.sql.SQLException;
import java.util.Calendar;

import ramchat.controller.impl.LoginControllerImpl;
import ramchat.model.dao.LoginDao;
import ramchat.model.dao.impl.LoginDaoImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.LoginService;
import usersession.UserSession;

public class LoginServiceImpl implements LoginService {
	private LoginDao loginDao = new LoginDaoImpl();

	@Override
	public UserInfoDTO getUserInfo(String id, String pw) throws SQLException {
		UserInfoDTO userInfo = loginDao.getUserInfo(id, pw);
		if(userInfo==null) {
			throw new SQLException("유저 정보를 찾을 수 없습니다.");
		}
		UserSession.setId(userInfo.getId());
		UserSession.setName(userInfo.getName());
		UserSession.setGender(userInfo.getGender());
		UserSession.setBirthDate(Integer.toString(userInfo.getBirth()));
		UserSession.setTotalHeart(userInfo.getTotalHeart());
		UserSession.setDailyHeart(userInfo.getHeart());
		
		return userInfo;
	}

	@Override
	public void UserHeartSetting(String id) throws SQLException {
		
		loginDao.UserHeartSetting(id);
		
	}
	
	
	
	
}
