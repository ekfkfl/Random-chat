package ramchat.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ramchat.model.dao.MypageDao;
import ramchat.model.util.DbUtil;

public class MypageDaoImpl implements MypageDao {

	@Override
	public int updateUserInfo(String id, String pw, String email, String cpw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update userinfo set pw=?, email=? where id=? and pw=?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
				ps.setString(1, cpw);
				ps.setString(2, email);
				ps.setString(3, id);
				ps.setString(4, pw);

				result = ps.executeUpdate();
			
		
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	
	}

}
