package ramchat.model.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
	private static Properties proFile = new Properties();
	
	public static Properties getProFile() {
		return proFile;
	}

	/**
	 * 로드
	 */
	static {
		try {
			proFile.load(new FileInputStream("src/resource/dbInfo.properties"));
			Class.forName(proFile.getProperty("driverName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 연결
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(proFile.getProperty("url"), proFile.getProperty("userName"), proFile.getProperty("userPass"));

	}

	/**
	 * 닫기
	 */
	
	public static void dbClose(Connection con, Statement ps) {
		try {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void dbClose(Connection con, Statement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
