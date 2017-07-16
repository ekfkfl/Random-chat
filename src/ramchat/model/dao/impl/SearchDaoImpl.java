package ramchat.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ramchat.model.dao.SearchDao;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.util.DbUtil;

public class SearchDaoImpl implements SearchDao {

	@Override
	public UserInfoDTO searchId(String name, String email) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoDTO userInfoDTO = null;
		String sql = "select * from userinfo where name = ? and email = ? " ;

		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				userInfoDTO = new UserInfoDTO(
							rs.getString("id"),
							rs.getString("pw"),
							rs.getString("name"),
							rs.getInt("gender"),
							rs.getInt("birth"),
							rs.getString("email"),
							rs.getInt("heart"),
							rs.getInt("total_heart")
						);
			}
		} finally {
			DbUtil.dbClose(con, pstmt, rs);
		}
		return userInfoDTO;
	}

	@Override
	public UserInfoDTO searchPw(String id, String name, String email) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoDTO userInfoDTO = null;
		String sql = "select * from userinfo where id = ? and name = ? and email = ?";

		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userInfoDTO = new UserInfoDTO(
							rs.getString("id"),
							rs.getString("pw"),
							rs.getString("name"),
							rs.getInt("gender"),
							rs.getInt("birth"),
							rs.getString("email"),
							rs.getInt("heart"),
							rs.getInt("total_heart")
						);
			}
		} finally {
			DbUtil.dbClose(con, pstmt, rs);
		}
		return userInfoDTO;
	}
}
