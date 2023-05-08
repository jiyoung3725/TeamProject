package dao;

import java.awt.Checkbox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import db.ConnectionProvider;
import first.LogInPage;
import vo.BoardVO;

public class BoardDAO {
	
	// ��� �Խñ� ��ȸ
	public ArrayList<BoardVO> viewAllList(int page){
		ArrayList<BoardVO> list = new ArrayList<>();
		int start = 1 + (page-1)*10;
		int end = 10*page;
		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by u.address, u.interest, date_board desc) a where rownum between ? and ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(no);
				v.setAddress(address);
				v.setCategory(category);
				v.setInterest(interest);
				v.setTitle(title);
				v.setDate_board(date_board);
				v.setAppilcation(application);
				v.setB_cnt(b_cnt);
				v.setL_cnt(l_cnt);
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	
	// �α�� ������ư ���� �� �α�� ��ȸ 
	public ArrayList<BoardVO> viewLikedList(){
		ArrayList<BoardVO> list = new ArrayList<>();
		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_no "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by  u.user_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by nvl(count(l.l_no), 0) desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	// �ֽż� ������ư ���� �� �α�� ��ȸ 
	public ArrayList<BoardVO> viewNewestList(){
		ArrayList<BoardVO> list = new ArrayList<>();
		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_no "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by  u.user_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	
	// �˻� ��ȸ
	public ArrayList<BoardVO> SearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by u.interest, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	
	// �α���� ���� �� �˻��ϴ� ����
	public ArrayList<BoardVO> likedSearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by nvl(count(l.l_no), 0) desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	// �ֱټ��� ���� �� �˻��ϴ� ����
	public ArrayList<BoardVO> dateSearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	// �����ɼǸ� ���� �� �˻��ϴ� ����
	public ArrayList<BoardVO> dateSearchList(String search, String addr){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? and u.address = ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, addr);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	//���ɻ� üũ�ڽ��� ���� �� �˻��ϴ� ����
	public ArrayList<BoardVO> interestList(ArrayList<String> interests){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where b.interest like ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+interests+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	
	//ī�װ�(���׻�Ȱ/�Բ��ؿ�)�� ���� �� �˻��ϴ� ����
	public ArrayList<BoardVO> categorySearchList(String search, String categorys){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? and b.category = ? "
					+ "group by u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, categorys);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String address = rs.getString(2);
				String category = rs.getString(3);
				String interest = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}

	//����, ī�װ�, ���ɻ�, �α�� ��� ���� �� �˻��ϴ� ����
	public ArrayList<BoardVO> AlloptionSearchList(String search, String categorys, String address, String interest){
		ArrayList<BoardVO> list = new ArrayList<>();
		
		try {
			String sql = "select a.* "
					+ "from (select u.user_no, u.address, b.category, u.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "where lower(trim(b.title)) like ? and b.category = ? and u.address = ? and b.interest like ? "
					+ "group by u.user_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) \r\n"
					+ "order by nvl(count(l.l_no), 0) desc, date_board desc) a where rownum <=10";
			
		
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, categorys);
			pstmt.setString(3, address);
			pstmt.setString(4, "%"+interest+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String addr = rs.getString(2);
				String category = rs.getString(3);
				String interested = rs.getString(4);
				String title = rs.getString(5);
				Date date_board = rs.getDate(6);
				String application = rs.getString(7);
				int b_cnt = rs.getInt(8);
				int l_cnt = rs.getInt(9);
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setAddress(rs.getString(2));
				v.setCategory(rs.getString(3));
				v.setInterest(rs.getString(4));
				v.setTitle(rs.getString(5));
				v.setDate_board(rs.getDate(6));
				v.setAppilcation(rs.getString(7));
				v.setB_cnt(rs.getInt(8));
				v.setL_cnt(rs.getInt(9));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		return list;
	}
	
}