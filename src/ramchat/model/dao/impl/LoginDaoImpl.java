package ramchat.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ramchat.model.dao.LoginDao;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.util.DbUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public UserInfoDTO getUserInfo(String id, String pw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from userinfo where id=? and pw=?";

		UserInfoDTO userInfo = null;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();

			if (rs.next()) {
				userInfo = new UserInfoDTO(rs.getString("id"), rs.getString("pw"), rs.getString("name"),
						rs.getInt("gender"), rs.getInt("birth"), rs.getString("email"), rs.getInt("heart"),
						rs.getInt("total_heart"));
			}

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return userInfo;
	}

	@Override
	public int UserHeartSetting(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String time = null;
		//±âº»°ª 0
		int result = 0;
		try {

			con = DbUtil.getConnection();

			pstmt = con.prepareStatement("select sysdate from dual");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				time = rs.getString("sysdate");
			}
			System.out.println(time);
			String hour= time.substring(11,13);
			System.out.println(hour);
			if(hour.equals("00")){
				pstmt = con.prepareStatement("update userinfo set heart=5 where id=?");
				pstmt.setString(1, id);
				result = pstmt.executeUpdate();
			}
			
		} finally {
			DbUtil.dbClose(con, pstmt,rs);
		}

		return result;
	}

}
