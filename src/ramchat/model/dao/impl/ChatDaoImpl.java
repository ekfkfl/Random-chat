package ramchat.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ramchat.model.dao.ChatDao;
import ramchat.model.util.DbUtil;

public class ChatDaoImpl implements ChatDao {

	@Override
	public int dailyHeart(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement("select heart from userinfo where id = ? ");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("heart");
			}
			System.out.println("chatDao"+result+"잔여하트");
			return result;
		} finally {
			DbUtil.dbClose(con, pstmt, rs);
		}

	}

	@Override
	public int totalHeart(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			con = DbUtil.getConnection();
			pstmt = con.prepareStatement("select count(r_id) as heartcount from heart where r_id = ? ");
			System.out.println(id + "받은사람아이디"); 
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("heartcount");
			}
			System.out.println("resultresultresultresultresult"+result);
			return result;

		} finally {
			DbUtil.dbClose(con, pstmt, rs);
		}

	}

	@Override
	public int sendHeart(String receiveId, String sendId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		int sendHeartCnt = 0;
		int receiveHeartCnt = 0;

		try {
			con = DbUtil.getConnection();
			// 0. 오토커밋 취소
			//
			con.setAutoCommit(false);
			// 1. 보내는 사람 데일리 하트개수 확인
			pstmt = con.prepareStatement("select heart from userinfo where id= ?");
			pstmt.setString(1, sendId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sendHeartCnt = rs.getInt("heart");
			}
			System.out.println("1");
			System.out.println("보내는 사람 하트 개수 : "+sendHeartCnt);
			// 2.0개보다 크면
			if (sendHeartCnt > 0) {
				// 3.보내는 사람 데일리 하트 1개 빼고 DB에 집어넣기
				System.out.println("2");
				sendHeartCnt--;
				pstmt = con.prepareStatement("update userinfo set heart= ? where id=?");
				System.out.println("보내는 사람 하트 뺀 값:" + sendHeartCnt);
				pstmt.setInt(1, sendHeartCnt);
				pstmt.setString(2, sendId);
				int result2=pstmt.executeUpdate();
				System.out.println("보내는 사람 하트빼고 집어넣기 결과 : "+result2);
				
				
				// 4.받는 사람 토탈 하트 가져와서 1개 추가하여 저장
				System.out.println("3");
				pstmt = con.prepareStatement("select total_heart from userinfo where id= ?");
				pstmt.setString(1, receiveId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					receiveHeartCnt = rs.getInt("total_heart");
				}
				System.out.println("받는 사람 하트 기존 개수 :"+receiveHeartCnt);

				System.out.println("4");
				pstmt = con.prepareStatement("update userinfo set total_heart= ? where id=?");
				pstmt.setInt(1, ++receiveHeartCnt);
				System.out.println("받는 사람 하트 추가한 개수 :"+receiveHeartCnt);
				pstmt.setString(2, receiveId);
				result2=pstmt.executeUpdate();
				System.out.println("받는사람 하트 업데이트 결과 :"+result2);

				// 5.토탈 하트 테이블에 저장
				System.out.println("5");
				pstmt = con.prepareStatement("insert into heart values(?,?,sysdate)");
				pstmt.setString(1, receiveId);
				pstmt.setString(2, sendId);
				result2=pstmt.executeUpdate();
				System.out.println("토탈테이블 업데이트 결과 :"+result2);
				
				
				System.out.println("6");

				//con.commit();
			} else {
				throw new SQLException();
			}

		} finally {
			DbUtil.dbClose(con, pstmt);
		}

		return 1;
	}

	@Override
	public int receiveHeart(String receiveId, String sendId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}