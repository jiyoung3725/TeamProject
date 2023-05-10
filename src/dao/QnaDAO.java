package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import customer.Inner_qna;
import vo.QnAVo;

import db.ConnectionProvider;
import first.LogInPage;

public class QnaDAO {
	
	//������ QnA_list���� ������ư�� ������ �� answer, qna���̺����� �����Ͱ� �����Ǵ� �޼ҵ�
	public int deleteList(int no) {
		int re = -1;
		String sql = "delete qna where q_no = ? ";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception e) {
			System.out.println("deleteList() ���ܹ߻� :" + e.getMessage());
		}
		return re;
	}
	 
	
	//<�������� �亯 ��� �� jtable�� ó�����°� ����Ǵ� �޼ҵ�>
	public int updateStatus(int no) {
		int re = -1;
		String sql = "update qna set q_status='�亯�Ϸ�' where q_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception v) {
			System.out.println("updateStatus() ���ܹ߻� : "+ v.getMessage());
		}
		return re;
	}
	
	
	//<�亯�� JTextArea���� ��ȸ�ϴ� �޼ҵ�>
		public ArrayList<QnAVo> searchAnswer(int q_no){
			ArrayList<QnAVo> list = new ArrayList<QnAVo>();
			String sql = "select a_content from answer where q_no=? ";
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, q_no);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String content = rs.getString(1);
					QnAVo v = new QnAVo();
					v.setA_content(content);
					list.add(v);
				} ConnectionProvider.close(rs, pstmt, conn);
			}catch(Exception q) {
				System.out.println("searchAnswer() ���ܹ߻�" + q.getMessage());
			}
			return list;
		}
	
	
	//<�������� �亯�� ���(������ �̹� �� �� ���ڵ尡 �־� ����)�ϴ� �޼��� (ListPopUpâ ��� ��ư ���� ��)>
		public int addAnswer(String a_content, int q_no, int u_no) {
			
			int re = -1;
			String sql = "update answer set a_date = default, a_content = ? where q_no = ? and user_no=? ";
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, a_content);
				pstmt.setInt(2, q_no);
				pstmt.setInt(3, u_no);
				re=pstmt.executeUpdate();
				ConnectionProvider.close(pstmt, conn);
			}catch(Exception g) {
				System.out.println("addanswer() ���ܹ߻� :" + g.getMessage());
			}
			return  re;
		}
	
	//<����ڰ� ���Ǳ��� �ۼ��� �� �亯���̺� ������ �߰��Ǵ� �޼ҵ�> �亯�� ������ rowData�� �߰��� �ȵż� ����.
		public int blankAnswer() {
			int re = -1;
			String sql = "insert into answer values((SELECT NVL( MAX(a_no), 0) + 1 FROM answer ),(select max(q_no) from qna),null,null,0)";
			try {
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				re = pstmt.executeUpdate();
				ConnectionProvider.close(pstmt, conn);
			}catch(Exception t) {
				System.out.println("blank answer() ���ܹ߻� :" +t.getMessage());
			}
			return re;
		}
	
	//<����ڰ� �Է��� ������ ����ϴ� �޼ҵ�>
	public int addData(String type, String mail, String title, String content, int no){
		String sql ="insert into qna values((SELECT NVL( MAX(q_no), 0) + 1 FROM qna),?,?,?,?,null,default,default,?)";
	
		if(type.equals("") || mail.equals("") || title.equals("") || content.equals("") ||Inner_qna.check_b.isSelected()==false) {
		        return 0;
		 }
		int count =-1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2, mail);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setInt(5, no);
			count = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception d) {
			System.out.println("addData() �����߻� :" +d.getMessage());
			d.printStackTrace();
		}
		return count;
	}

//<������ �� ������ �ۼ��� ��ϸ� ���̴� �޼ҵ�>
		public ArrayList<QnAVo> addList(int no){
			ArrayList<QnAVo> list = new ArrayList<QnAVo>();
			String sql = "select q.q_no, q_type, q_title, q_inquirdate, q_status, a.a_date from qna q, answer a where q.q_no = a.q_no and q.user_no = ? order by q.q_no";
			try{
				Connection conn = db.ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int q_no = rs.getInt(1);
					String q_type = rs.getString(2);
					String q_title = rs.getString(3);
					Date q_inquirdate = rs.getDate(4);
					String q_status = rs.getString(5);
					Date a_date = rs.getDate(6);
					QnAVo v =new QnAVo();
					v.setQ_no(q_no);
					v.setQ_type(q_type);
					v.setQ_title(q_title);
					v.setQ_inquirdate(q_inquirdate);
					v.setQ_status(q_status);
					v.setA_date(a_date);
					list.add(v);
				}db.ConnectionProvider.close(rs, pstmt, conn);
				
			}catch(Exception c) {
				System.out.println("addList(no) ���ܹ߻� :" + c.getMessage());
			}
			return list;
			
		}

//<�������� �� ���ǳ����� JTable rowData�� �� ����ϴ� �޼ҵ�>
	public ArrayList<QnAVo> addList(){
		ArrayList<QnAVo> list = new ArrayList<QnAVo>();
		String sql = "select q.q_no, q_type, q_title, q_inquirdate, q_status, a.a_date from qna q, answer a where q.q_no = a.q_no order by q.q_no";
		try{
			Connection conn = db.ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int q_no = rs.getInt(1);
				String q_type = rs.getString(2);
				String q_title = rs.getString(3);
				Date q_inquirdate = rs.getDate(4);
				String q_status = rs.getString(5);
				Date a_date = rs.getDate(6);
				QnAVo v =new QnAVo();
				v.setQ_no(q_no);
				v.setQ_type(q_type);
				v.setQ_title(q_title);
				v.setQ_inquirdate(q_inquirdate);
				v.setQ_status(q_status);
				v.setA_date(a_date);
				list.add(v);
			}db.ConnectionProvider.close(rs, pstmt, conn);
			
		}catch(Exception c) {
			System.out.println("addList() ���ܹ߻� :" + c.getMessage());
		}
		return list;
		
	}

}
