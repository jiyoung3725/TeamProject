package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Vector;

import db.ConnectionProvider;
import first.LogInPage;
import vo.BoardVO;
import vo.UserVO;

public class ApplicationDAO {

	//사용자가 신청란에서 신청내용을 작성하는 쿼리
	public int insertApplication(BoardVO b) {
		int re = -1;
		try {
			String sql = "insert into application (ap_no, ap_content, date_application, b_no, user_no) "
					+ "values (seq_application.nextval, ?,sysdate,?,?)";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getAp_content());
			pstmt.setInt(2, b.getB_no());
			pstmt.setInt(3, LogInPage.getNO());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("예외발생: " +e.getMessage());
		}
		return re;
	}
	
	//신청 수 쿼리
	//0508_16:44 수정_전체 신청 가능 인원과 현재 신청 인원 출력
	public HashMap<String, Object> countApplication(BoardVO b) {
		HashMap<String, Object> map_apply = new HashMap<String, Object>();
		try {
			String sql = "select count(ap_no)+1, b.recruit_no from application a, board b where a.b_no=b.b_no and b.b_no = "+b.getB_no();
			//게시글 작성자 수까지 +1로 수정 0508_15:38
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				map_apply.put("cnt_apply",rs.getInt(1));
				map_apply.put("recruit_no",rs.getInt(2));
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("예외: " +e.getMessage());
		}
		return map_apply;
	}
	
	//작성자가 신청정보 조회
		public ArrayList<BoardVO> applicationList() {
				ArrayList<BoardVO> list = new ArrayList<>();
			try {
				String sql = "select a.* from "
						+ "(select distinct u.user_no, u.user_name, decode(substr(jumin,8,1),1,'남',2,'여',3,'남',4,'여') gender, "
						+ "a.ap_content, a.date_application "
						+ "from board b, user_info u, application a "
						+ "where b.b_no=a.b_no and u.user_no=b.user_no "
						+ "and a.user_no <> ? and u.user_no = ? "
						+ "order by date_application desc) a where rownum <=10";
				
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, LogInPage.getNO());
				pstmt.setInt(2, LogInPage.getNO());
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO v = new BoardVO();
					v.setB_no(rs.getInt(1));
					v.setName(rs.getString(2));
					v.setGender(rs.getString(3));
					v.setAp_content(rs.getString(4));
					v.setDate_board(rs.getDate(5));
					list.add(v);
					
				}
				ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
				System.out.println("���ܹ߻� : " +e.getMessage());
			}
			return list;
		}
}
