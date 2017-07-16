package ramchat.model.service.impl;

import java.sql.SQLException;

import ramchat.model.dao.MypageDao;
import ramchat.model.dao.impl.MypageDaoImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.MypageService;

public class MypageServiceImpl implements MypageService {
	
	private MypageDao mypageDao = new MypageDaoImpl();
	@Override
	public int updateUserInfo(String id, String pw, String email, String cpw) throws SQLException {
		int result = mypageDao.updateUserInfo(id, pw, email, cpw);
		if(result==0) {
			throw new SQLException("수정되지 않았습니다.");
		}
		return result;
	}

}
