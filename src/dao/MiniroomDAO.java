package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectionProvider;
import vo.BoardVO;

public class MiniroomDAO {
	// �α�� ��� ��ȯ �޼��� (������ ��ó)
	public ArrayList<BoardVO> getAddrBoard(int NO){
		ArrayList<BoardVO> list = new ArrayList<>();
		String sql = "select b_no, title from "
				+ "(select b_no,title,address from user_info u, board b where u.user_no=b.user_no) a, user_info u "
				+ "where a.address=u.address and u.user_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString(2));
				list.add(b);
			}
		} catch (Exception e) {System.out.println("getAddrBoard() ���� �߻� : "+e.getMessage());}
		return list;
	}
	
	// �α�� ��� ��ȯ �޼��� (���ɻ�)
	public ArrayList<BoardVO> getInterestBoard(int NO){
		ArrayList<BoardVO> list = new ArrayList<>();
		String sql = "select b_no, title from board b, user_info u "
				+ "where u.interest=b.interest and u.user_no=?" + 
				"";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString(2));
				list.add(b);
			}
		} catch (Exception e) {System.out.println("getInterestBoard() ���� �߻� : "+e.getMessage());}
		return list;
	}
	
	// ������ ������ ��ȯ�ϴ� �޼���
	public String getGender(int NO) {
		String sql = "select decode(substr(jumin,7,1),1,'��',3,'��','��') from user_info where user_no=?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
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
	// �̴Ϸ� ������ ��ȯ�ϴ� �޼���
	public String getMTitle(int NO) {
		String sql = "select m_title from miniroom where user_no=?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
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
	// �̴Ϸ� �Ұ����� ��ȯ�ϴ� �޼���
	public String getMcontent(int NO) {
		String sql = "select m_content from miniroom where user_no=?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
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
	// �̴Ϸ� �Ұ��̹����� ��ȯ�ϴ� �޼���
	public String getMImg(int NO) {
		String sql = "select m_image from miniroom where user_no=?";
		String result = "";
		try {
			Connection conn = ConnectionProvider.getConnection();
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
	// �̴Ϸ� ���ƿ� ���� ��ȯ�ϴ� �޼���
	public int getMLikes(int NO) {
		String sql = "select m_likes from miniroom where user_no=?";
		int result = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	// �̴Ϸ� ���ƿ� ���� ������Ʈ�ϴ� �޼���
	public int updateMLikes(int NO) {
		String sql = "update miniroom set m_likes = m_likes+1 where user_no=?";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return re;
	}
	// �̴Ϸ� ������ �����ϴ� �޼���
	public int saveTitle(String title, int NO) {
		String sql = "update miniroom set m_title=? where user_no = ?";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
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
	// �̴Ϸ� �Ұ����� �����ϴ� �޼���
	public int saveContent(String content, int NO) {
		String sql = "update miniroom set m_content=? where user_no = ?";
		int re = -1;
		try { 
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, NO);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return re;
	}
	// �̴Ϸ� �Ұ� �̹����� �����ϴ� �޼���
	public int saveMImg(String MImg, int NO) {
		String sql = "update miniroom set m_image=? where user_no = ?";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MImg);
			pstmt.setInt(2, NO);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return re;
	}
	// �ƹ�Ÿ ��ġ�� �����ϴ� �޼���
	public int saveAvatar(int x, int y, int NO) {
		int re = -1;
		String sql = "update miniroom set char_x=?, char_y=? where user_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, x);
			pstmt.setInt(2, y);
			pstmt.setInt(3, NO);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {System.out.println("savaAvatar() ���� �߻� : "+e.getMessage());}
		return re;
	}
	// �ƹ�Ÿ ��ġ�� ��ȯ�ϴ� �޼���
	public int[] getAvatar(int NO) {
		int[] arr = new int[2];
		String sql = "select char_x, char_y from miniroom where user_no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NO);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				arr[0] = rs.getInt(1);
				arr[1] = rs.getInt(2);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {System.out.println("getAvatar() ���� �߻� : "+e.getMessage());}
		return arr;
	}
}
