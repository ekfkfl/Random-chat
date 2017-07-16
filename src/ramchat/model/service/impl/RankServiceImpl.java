package ramchat.model.service.impl;

import java.sql.SQLException;
import java.util.List;

import ramchat.model.dao.RankDao;
import ramchat.model.dao.impl.RankDaoImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.RankService;

public class RankServiceImpl implements RankService {
	private RankDao rankDao = new RankDaoImpl();
	@Override
	public List<UserInfoDTO> dailyRank(int gender) throws SQLException {
		List<UserInfoDTO> list = rankDao.dailyRank(gender);
		if(list==null || list.size()==0) {
			throw new SQLException("유저가 존재하지 않습니다.");	
		}
		return list;
	}

	@Override
	public List<UserInfoDTO> weeklyRank(int gender) throws SQLException {
		List<UserInfoDTO> list = rankDao.weeklyRank(gender);
		System.out.println(list);
		if(list==null || list.size()==0) {
			throw new SQLException("유저가 존재하지 않습니다.");	
		}
		return list;
	}

	@Override
	public List<UserInfoDTO> monthlyRank(int gender) throws SQLException {
		List<UserInfoDTO> list = rankDao.monthlyRank(gender);
		if(list==null || list.size()==0) {
			throw new SQLException("유저가 존재하지 않습니다.");	
		}
		return list;
	}

}
