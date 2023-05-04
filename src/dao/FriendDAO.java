package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.ConnectionProvider;
import vo.UserVO;

public class FriendDAO {
	public ArrayList<String> getFriendlist(int NO){
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select user_name, user_id from user_info, friend "
				+ "where user_no1 = user_no and user_no2=? union select user_name, user_id "
				+ "from user_info, friend where user_no2=user_no and user_no1=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			pstmt.setInt(2, NO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1)+"("+rs.getString(2)+")");
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {System.out.println("getFriendlist() 예외 발생 : "+e.getMessage());}
		return list;
	}
	
	public int deleteFriend(String name, int NO) {
		int re = -1;
		String sql = "delete from friend where user_no1"
				+ "=(select user_no from user_info where user_name = ?) "
				+ "and user_no2=?";
		try {
			
		} catch (Exception e) {System.out.println("deleteFriend() 예외 발생 : "+e.getMessage());}
		return re;
	}
	
	public int getFriendNo(String ID) {
		int re = -1;
		System.out.println(ID);
		String sql = "select user_no from user_info where user_id=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				re = rs.getInt(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {System.out.println("getFriendNo() 예외 발생 : "+e.getMessage());}
		return re;
	}
	
	public UserVO getFriendDetail(int NO) {
		UserVO u = new UserVO();
		String sql = "select * from user_info where user_no ="+NO;
		try {
			Connection conn = ConnectionProvider.getConnecton();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				
			}
		} catch (Exception e) {System.out.println("getFriendDetail() 예외 발생 : "+e.getMessage());}
		return u;
	}
}
