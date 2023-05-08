package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import db.ConnectionProvider;
import first.LogInPage;
import vo.BoardVO;

public class BoardDAO {
	
	//게시글 작성
	public int insertBoard(BoardVO b) {
	    int re = -1;
	    try {
	        String sql = "insert into board (b_no, category, title, b_content, interest, date_board, application, user_no)"
	                + "values (seq_board.nextval, ?, ?, ?, ?, sysdate, ?, ?)";

	        Connection conn = ConnectionProvider.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, b.getCategory());
	        pstmt.setString(2, b.getTitle());
	        pstmt.setString(3, b.getB_content());
	        pstmt.setString(4, b.getInterest());
	        pstmt.setString(5, b.getAppilcation());
	        pstmt.setInt(6, LogInPage.getNO());
	        

	        re = pstmt.executeUpdate();
	        ConnectionProvider.close(pstmt, conn);
	    } catch (Exception e) {
	        System.out.println("예외발생 : " + e.getMessage());
	    }
	    return re;
	}
	
	// 모든 게시글 조회
	public ArrayList<BoardVO> viewAllList(int page){
		ArrayList<BoardVO> list = new ArrayList<>();
		int start = 1 + (page-1)*10;
		int end = 10*page;
		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, u.address, b.interest, date_board desc) a where rownum between ? and ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
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
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_no "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, nvl(count(l.l_no), 0) desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	// 최신순 라디오버튼 선택 시 인기순 조회 
	public ArrayList<BoardVO> viewNewestList(){
		ArrayList<BoardVO> list = new ArrayList<>();
		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_no "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "group by  b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
	// 검색 조회
	public ArrayList<BoardVO> SearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, u.interest, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
	// 인기순만 선택 후 검색하는 쿼리 (오류발생)
	public ArrayList<BoardVO> likedSearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, nvl(count(l.l_no), 0) desc, date_board desc) a where rownum <=10";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	// 최근순만 선택 후 검색하는 쿼리
	public ArrayList<BoardVO> dateSearchList(String search){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	// 지역옵션만 선택 후 검색하는 쿼리
	public ArrayList<BoardVO> dateSearchList(String search, String addr){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? and u.address = ? "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, addr);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	//관심사 체크박스만 선택 후 검색하는 쿼리 (기능구현x)
	public ArrayList<BoardVO> interestList(String interest) {
	    ArrayList<BoardVO> list = new ArrayList<>();
	    
	    try {
	        String sql = "SELECT rownum, a.* " +
	                     "FROM (SELECT b.b_no, u.address, b.category, b.interest, b.title, " +
	                            "b.date_board, b.application, NVL(b.b_cnt, 0) b_cnt, NVL(COUNT(l.l_no), 0) l_cnt " +
	                            "FROM board b LEFT OUTER JOIN user_info u ON u.user_no = b.user_no " +
	                            "LEFT OUTER JOIN liked l ON l.b_no=b.b_no WHERE b.interest LIKE ? " +
	                            "GROUP BY b.b_no, u.address, b.category, b.interest, b.title, b.date_board, " +
	                            "b.application, NVL(b.b_cnt, 0) " +
	                            "ORDER BY b.b_no desc, date_board DESC) a " +
	                     "WHERE ROWNUM <= 10";
	        
	        Connection conn = ConnectionProvider.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + String.join(",", interest) + "%");
	        ResultSet rs = pstmt.executeQuery();
	        
	        while(rs.next()) {
	            BoardVO v = new BoardVO();
	        	v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
	        }
	        
	        ConnectionProvider.close(rs, pstmt, conn);
	    } catch (Exception e) {
	        System.out.println("예외 발생: " + e.getMessage());
	    }
	    
	    return list;
	}
	
	//카테고리(동네생활/함께해요)만 선택 후 검색하는 쿼리
	public ArrayList<BoardVO> categorySearchList(String search, String categorys){
		ArrayList<BoardVO> list = new ArrayList<>();

		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no where lower(trim(b.title)) like ? and b.category = ? "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, date_board desc) a where rownum <=10";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, categorys);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}

	//지역, 카테고리, 관심사, 인기순 모두 선택 후 검색하는 쿼리
	public ArrayList<BoardVO> AlloptionSearchList(String search, String categorys, String address, String interest){
		ArrayList<BoardVO> list = new ArrayList<>();
		
		try {
			String sql = "select rownum, a.* "
					+ "from (select b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) b_cnt, nvl(count(l.l_no), 0) l_cnt "
					+ "from board b left outer join user_info u on u.user_no = b.user_no left outer join liked l on l.b_no=b.b_no "
					+ "where lower(trim(b.title)) like ? and b.category = ? and u.address = ? and b.interest like ? "
					+ "group by b.b_no, u.address, b.category, b.interest, b.title, b.date_board, b.application, nvl(b.b_cnt,0) "
					+ "order by b.b_no desc, nvl(count(l.l_no), 0) desc, date_board desc) a where rownum <=10";
			
		
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, categorys);
			pstmt.setString(3, address);
			pstmt.setString(4, "%"+interest+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				BoardVO v = new BoardVO();
				v.setNo(rs.getInt(1));
				v.setB_no(rs.getInt(2));
				v.setAddress(rs.getString(3));
				v.setCategory(rs.getString(4));
				v.setInterest(rs.getString(5));
				v.setTitle(rs.getString(6));
				v.setDate_board(rs.getDate(7));
				v.setAppilcation(rs.getString(8));
				v.setB_cnt(rs.getInt(9));
				v.setL_cnt(rs.getInt(10));
				list.add(v);
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
}
