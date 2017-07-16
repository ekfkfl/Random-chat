package ramchat.model.service.impl;

import java.sql.SQLException;

import javax.mail.Session;

import ramchat.model.dao.JoinDao;
import ramchat.model.dao.impl.JoinDaoImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.JoinService;
import usersession.UserSession;

public class JoinServiceImpl implements JoinService {
	
	
	JoinDao joinDao= new JoinDaoImpl();
	@Override
	public boolean getIdByCheck(String id) throws SQLException {
		
		if(!joinDao.getIdByCheck(id)) throw new SQLException();
		
		return true;
	}

	@Override
	public int insertUserInfo(UserInfoDTO userInfo) throws SQLException {
		int result= joinDao.insertUserInfo(userInfo);
		if(result==0) throw new SQLException();
		return 1;
	}

}
