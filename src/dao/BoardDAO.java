package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import db.ConnectionProvider;
import vo.BoardVO;

public class BoardDAO {
	// 모든 게시글 조회
	public ArrayList<BoardVO> viewAllList(){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_create, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_create, b.application, nvl(b.b_cnt,0) "
					+ "order by u.address, u.interest, date_create desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnecton();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_create = rs.getDate(7);
				String application = rs.getString(8);
				int b_cnt = rs.getInt(9);
				int l_cnt = rs.getInt(10);
				
				BoardVO v = new BoardVO();
				v.setNo(no);
				v.setAddress(address);
				v.setCategory(category);
				v.setInterest(interest);
				v.setTitle(title);
				v.setDate_create(date_create);
				v.setAppilcation(application);
				v.setB_cnt(b_cnt);
				v.setL_cnt(l_cnt);
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
	// 인기순 라디오버튼 선택 시 인기순 조회 
	public ArrayList<BoardVO> viewLikedList(){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select u.user_no, u.address, b.category, b.interest, b.title, b.date_create, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_no "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by  u.user_no, u.address, b.category, b.interest, b.title, b.date_create, b.application, nvl(b.b_cnt,0) "
					+ "order by nvl(count(l.l_no), 0) desc, date_create desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnecton();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_create = rs.getDate(7);
				String application = rs.getString(8);
				int b_cnt = rs.getInt(9);
				int l_cnt = rs.getInt(10);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_create(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
	// 검색 조회
	public ArrayList<BoardVO> viewSearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_create, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_create, b.application, nvl(b.b_cnt,0) "
					+ "order by u.interest, date_create desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_create = rs.getDate(7);
				String application = rs.getString(8);
				int b_cnt = rs.getInt(9);
				int l_cnt = rs.getInt(10);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_create(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
}
