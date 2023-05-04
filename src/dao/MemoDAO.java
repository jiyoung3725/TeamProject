package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectionProvider;
import vo.MemoVO;
 
public class MemoDAO {
	public ArrayList<MemoVO> viewMessages(int recipient_no){
		ArrayList<MemoVO> list = new ArrayList<MemoVO>();
		String sql = "select m_content, date_sent,m_state, user_name from messages m, user_info u where u.user_no=m.sender_no and recipient_no=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipient_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoVO m = new MemoVO();
				m.setContent(rs.getString(1));
				m.setDate_sent(rs.getDate(2));
				m.setState(rs.getString(3));
				m.setSender_name(rs.getString(4));
				list.add(m);
			}
		} catch (Exception e) {System.out.println("viewMessages() 예외 발생 : "+e.getMessage());}
		return list;
	}
	public ArrayList<MemoVO> viewUnCheckedMessages(int recipient_no){
		ArrayList<MemoVO> list = new ArrayList<MemoVO>();
		String sql = "select m_content, date_sent,m_state, user_name from messages m, user_info u where u.user_no=m.sender_no and recipient_no=? and m_state ='N'";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipient_no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoVO m = new MemoVO();
				m.setContent(rs.getString(1));
				m.setDate_sent(rs.getDate(2));
				m.setState(rs.getString(3));
				m.setSender_name(rs.getString(4));
				list.add(m);
			}
		} catch (Exception e) {System.out.println("viewMessages() 예외 발생 : "+e.getMessage());}
		return list;
	}
	
	public MemoVO viewDetailMessage(int NO, int row) {
		MemoVO m = new MemoVO();
		String sql = "select * from (select rownum r, m_no, m_content, date_sent, user_name, sender_no from messages m, user_info u where u.user_no=m.sender_no and recipient_no=?) where r=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			pstmt.setInt(2, row);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setM_no(rs.getInt(2));
				m.setContent(rs.getString(3));
				m.setDate_sent(rs.getDate(4));
				m.setSender_name(rs.getString(5));
				m.setSender_no(rs.getInt(6));
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {System.out.println("viewDetailMessage() 예외 발생 : "+e.getMessage());}
		return m;
	}
	
	public int sendMessages(MemoVO m) {
		int re = -1;
		String sql = "insert into messages values(msg_seq.NEXTVAL, ?, sysdate, 'N', ?,?)";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getContent());
			pstmt.setInt(2, m.getSender_no());
			pstmt.setInt(3, m.getRecipient_no());
			re = pstmt.executeUpdate();
		} catch (Exception e) {System.out.println("sendMessages() 예외 발생 : "+e.getMessage());}
		return re;
	}
	
	public int getSenderNO(String name) {
		int no = -1;
		String sql = "select user_no from user_info where user_name=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {System.out.println("viewDetailMessage() 예외 발생 : "+e.getMessage());}
		return no;
	}
	public int changeState(MemoVO m) {
		int re = -1;
		String sql = "update messages set m_state = 'Y' where m_no=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getM_no());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {System.out.println("viewDetailMessage() 예외 발생 : "+e.getMessage());}
		return re;
	}
	
	public int deleteMessage(MemoVO m) {
		int re = -1;
		String sql = "delete from messages where m_no=?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getM_no());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {	System.out.println("deleteMessage() 예외 발생 : "+e.getMessage());}
		return re;
	}
	
	
}
