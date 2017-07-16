package ramchat.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ramchat.model.dao.JoinDao;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.util.DbUtil;

public class JoinDaoImpl implements JoinDao {

	@Override
	public boolean getIdByCheck(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement("select id from userinfo");
			rs = pstmt.executeQuery();
			while (rs.next()){
				if(rs.getString("id").equals(id)) return false;
			}

		} finally {
			DbUtil.dbClose(con, pstmt, rs);
		}
		return true;
	}

	@Override
	public int insertUserInfo(UserInfoDTO userInfo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement("insert into userinfo values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, userInfo.getId());
			pstmt.setString(2, userInfo.getPw());
			pstmt.setString(3, userInfo.getName());
			pstmt.setInt(4, userInfo.getGender());
			pstmt.setInt(5, userInfo.getBirth());
			pstmt.setString(6, userInfo.getEmail());
			pstmt.setInt(7, 5);
			pstmt.setInt(8, 0);

			result = pstmt.executeUpdate();
			con.commit();
			return result;
		} finally {
			DbUtil.dbClose(con, pstmt);
		}

	}

}
