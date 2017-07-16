package ramchat.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ramchat.model.dao.RankDao;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.util.DbUtil;

public class RankDaoImpl implements RankDao {

	@Override
	public List<UserInfoDTO> dailyRank(int gender) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select ranking, id, heart from (select rank() over(order by count(r_id) desc) as ranking, r_id as id, count(r_id) as heart from heart where to_char(r_date,'YYYY/MM/DD')=to_char(sysdate, 'YYYY/MM/DD') and r_id in (select id from userinfo where gender=?) group by r_id) where ranking <= 3";
		
		UserInfoDTO userInfo=null;
		
		List<UserInfoDTO> list=new ArrayList<>();
		
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, gender);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				userInfo = new UserInfoDTO(rs.getString("id"),rs.getInt("heart"));
				System.out.println(userInfo);
				list.add(userInfo);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<UserInfoDTO> weeklyRank(int gender) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select ranking, id, heart from (select rank() over(order by count(r_id) desc) as ranking, r_id as id, count(r_id) as heart from heart where r_date between trunc(sysdate,'d') and trunc(sysdate,'d')+6 and r_id in (select id from userinfo where gender=?) group by r_id) where ranking <= 3";
		
		UserInfoDTO userInfo=null;
		
		List<UserInfoDTO> list=new ArrayList<>();
		
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, gender);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				userInfo = new UserInfoDTO(rs.getString("id"),rs.getInt("heart"));
				list.add(userInfo);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<UserInfoDTO> monthlyRank(int gender) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select ranking, id, heart from (select rank() over(order by count(r_id) desc) as ranking, r_id as id, count(r_id) as heart from heart where to_char(r_date,'YYYY/MM')=to_char(sysdate, 'YYYY/MM') and r_id in (select id from userinfo where gender=?) group by r_id) where ranking <= 3";
		
		UserInfoDTO userInfo=null;
		
		List<UserInfoDTO> list=new ArrayList<>();
		
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, gender);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				userInfo = new UserInfoDTO(rs.getString("id"),rs.getInt("heart"));
				list.add(userInfo);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

}
