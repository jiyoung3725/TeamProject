package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import boards.Board;
import db.ConnectionProvider;



public class PostDAO {
	//게시글 삭제
	public int deletePost() {
		int re = -1;
		String sql = "DELETE FROM board WHERE b_no="+Board.postNum;
		try {
			Connection conn = new ConnectionProvider().getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(stmt, conn);
		} catch (Exception e) {
			System.out.println("예외:"+e.getMessage());
		}
		return re;		
	}
	
	//게시글 수정
	public int updatePost() {
		int re = -1;
		String sql = "";
		try {
			Connection conn = new ConnectionProvider().getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(stmt, conn);
		} catch (Exception e) {
			System.out.println("예외:"+e.getMessage());
		}
		return re;		
	}
	
//	public ArrayList<PostVO> showPost(){
//		ArrayList<PostVO> list = new ArrayList<PostVO>();
//		String sql = "SELECT b_no, category, title, user_id, b_content, b.interest, date_board, "
//				+ "application, b_cnt "
//				+ "FROM board b, user_info "
//				+ "WHERE b.b_no=1";	//게시판에서 클릭한 보드 넘버
//		try {
//			Connection conn = ConnectionProvider.getConnection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				int b_no = rs.getInt(1);
//				String category = rs.getString(2);
//				String title = rs.getString(3);
//				String user_id = rs.getString(4);
//				String b_content = rs.getString(5);
//				String interest = rs.getString(6);
//				String date_board = rs.getString(7);
//				String application = rs.getString(8);
//				int b_cnt = rs.getInt(9);
//				PostVO p = new PostVO(b_no, category, title, user_id, b_content, interest, 
//						date_board, application, b_cnt);
//				list.add(p);			
//			}
//			
//		} catch (Exception e) {
//			System.out.println("예외: "+e.getMessage());
//		}
//		return list;
//	}
}
