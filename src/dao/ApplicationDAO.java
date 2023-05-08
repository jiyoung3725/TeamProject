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
import vo.UserVO;

public class ApplicationDAO {

	//����ڰ� ��û������ ��û������ �ۼ��ϴ� ����
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
			System.out.println("���ܹ߻� : " +e.getMessage());
		}
		return re;
	}
	
	//��û �� ����
	public ArrayList<BoardVO> countApplication(BoardVO b) {
			ArrayList<BoardVO> list = new ArrayList<>();
		try {
			String sql = "select count(ap_no) from application a, board b where a.b_no=b.b_no and b.b_no = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getB_no());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BoardVO v = new BoardVO();
				v.setB_no(rs.getInt(1));
				
			}
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("���ܹ߻� : " +e.getMessage());
		}
		return list;
	}
	//�ۼ��� ��û���� ��ȸ
		public ArrayList<BoardVO> applicationList() {
				ArrayList<BoardVO> list = new ArrayList<>();
			try {
				String sql = "select a.* from "
						+ "(select distinct u.user_no, u.user_name, decode(substr(jumin,8,1),1,'��',2,'��',3,'��',4,'��') gender, "
						+ "a.ap_content, a.date_application "
						+ "from board b, user_info u, application a "
						+ "where b.b_no=a.b_no and u.user_no=b.user_no "
						+ "and a.user_no <> 2 and u.user_no = 2 "
						+ "order by date_application desc) a where rownum <=10";
				
				Connection conn = ConnectionProvider.getConnection();
				Statement pstmt = conn.createStatement();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, LogInPage.NO);
//				pstmt.setInt(2, LogInPage.NO);
				
				ResultSet rs = pstmt.executeQuery(sql);
				
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