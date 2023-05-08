package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import db.ConnectionProvider;
import first.LogInPage;
import vo.BoardVO;

public class LikedDAO {

	//좋아요 버튼을 누를 때마다 게시글의 정보를 좋아요 테이블에 추가하는 쿼리
	public int insertLiked(BoardVO b) {
		int re = -1;
		try {
			String sql = "insert into liked (l_no, date_liked, b_no, user_no) values (seq_liked.nextval, sysdate, ?, ?)";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNo());
			pstmt.setInt(2, LogInPage.getNO());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("예외발생 : " +e.getMessage());
		}
		return re;
	}
	
	//좋아요 수 쿼리
	public ArrayList<BoardVO> countLiked(BoardVO b) {
			ArrayList<BoardVO> list = new ArrayList<>();
		try {
			String sql = "select count(l_no) from liked l, board b where l.b_no = b.b_no and b.b_no = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNo());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
			}
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("예외발생 : " +e.getMessage());
		}
		return list;
	}
	//자신의 좋아요 목록을 조회하는 쿼리
		public ArrayList<BoardVO> myLikedlist() {
				ArrayList<BoardVO> list = new ArrayList<>();
			try {
				String sql = "select a.* from (select l.l_no, u.address, b.title, l.date_liked from board b, liked l, user_info u  "
						+ "where u.user_no= l.user_no and l.b_no = b.b_no and l.user_no = 2 order by date_liked desc) a where rownum <=5";
				
				Connection conn = ConnectionProvider.getConnection();
				Statement pstmt = conn.createStatement();
				ResultSet rs = pstmt.executeQuery(sql);
				
				while(rs.next()) {
					int l_no = rs.getInt(1);
					String address = rs.getString(2);
					String title = rs.getString(3);
					Date date_liked = rs.getDate(4);
					BoardVO v = new BoardVO();
					v.setNo(rs.getInt(1));
					v.setAddress(rs.getString(2));
					v.setTitle(rs.getString(3));
					v.setDate_board(rs.getDate(4));
					list.add(v);
				}
				ConnectionProvider.close(pstmt, conn);
			} catch (Exception e) {
				System.out.println("예외발생 : " +e.getMessage());
			}
			return list;
		}
}