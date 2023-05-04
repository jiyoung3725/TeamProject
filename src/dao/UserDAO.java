package dao;

import java.sql.Statement;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.ConnectionProvider;
import vo.UserVO;

public class UserDAO {
	// 로그인
	public String getPWD(String ID){
		String sql = "select user_pwd from user_info where user_id = ?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	
	// user_no 가져오기
	public int getNO(UserVO u) {
		int re = -1;
		String sql = "select user_no from user_info where user_id = ? and user_pwd = ?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getId());
			pstmt.setString(2, u.getPwd());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				re=	rs.getInt(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {System.out.println("getNO() 예외 발생 :"+e.getMessage());}
		return re;
	}
	
	

	// 성별 찾기
	public String getGender(int NO) {
		String sql = "select decode(substr(jumin,7,1),1,'남',3,'남','여') from user_info where user_no=?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
		
	}
	// 미니룸 제목
	public String getTitle(int NO) {
		String sql = "select m_title from miniroom where user_no=?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
		
	}
	
	// 제목 수정
	public int saveTitle(String title, int NO) {
		String sql = "update miniroom setm_title=? where user_no = ?";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setInt(2, NO);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return re;
	}
	
	public int signup(UserVO u) {
		int re = -1;
		String sql = "insert into user_info values(no_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getId());
			pstmt.setString(2, u.getPwd());
			pstmt.setString(3, u.getName());
			pstmt.setString(4, u.getJumin());
			pstmt.setString(5, u.getEmail());
			pstmt.setString(6, u.getInterest().equals("")?"null":u.getInterest());
			pstmt.setString(7, u.getPhone().equals("")?"null":u.getPhone());
			pstmt.setString(8, u.getAddress().equals("")?"null":u.getAddress());
			re = pstmt.executeUpdate();
			
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {System.out.println("singup()예외발생 : "+e.getMessage());}
		
		return re;
	}
	public int makeMiniroom(UserVO u) {
		int re = -1;
		String sql = "insert into miniroom values (?, default, default, default, default, default, 0, default)";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u.getNo());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {System.out.println("makeMiniroom()예외발생 : "+e.getMessage());}
		
		return re;
	}
	
	public int saveAvatar(int x, int y, int NO) {
		int re = -1;
		String sql = "update miniroom set char_x=?, char_y=? where user_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, x);
			pstmt.setInt(2, y);
			pstmt.setInt(3, NO);
			re = pstmt.executeUpdate();
		} catch (Exception e) {System.out.println("savaAvatar() 예외 발생 : "+e.getMessage());}
		return re;
	}
	public int[] getAvatar(int NO) {
		int[] arr = new int[2];
		String sql = "select char_x, char_y from miniroom where user_no=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				arr[0] = rs.getInt(1);
				arr[1] = rs.getInt(2);
			}
		} catch (Exception e) {System.out.println("getAvatar() 예외 발생 : "+e.getMessage());}
		return arr;
	}
}
