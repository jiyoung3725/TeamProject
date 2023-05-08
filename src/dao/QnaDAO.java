package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.xpath.XPathEvaluationResult.XPathResultType;

import vo.QnAVo;

import db.ConnectionProvider;

public class QnaDAO {
	
	//<�˻���� ����>
	public ArrayList<QnAVo> findAll(String q_title, String q_content){
		ArrayList<QnAVo> list = new ArrayList<QnAVo>();
		String sql = "select q_title, q_ content from qna where q_title like '%?%' and q_content like '%?%'";
		try {
		Connection conn = ConnectionProvider.getConnecton();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, q_title);
		pstmt.setString(2, q_content);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
		String title = rs.getString(1);
		String content = rs.getString(2);
		QnAVo v = new QnAVo();
		v.setQ_title(q_title);
		v.setQ_content(q_content);
		}ConnectionProvider.close(rs, pstmt, conn);
		}catch(Exception t) {
			System.out.println("���ܹ߻�" + t.getMessage());
		}
		return list;
	}
	
	
	//<�������� �亯 ��� �� jtable�� ó�����°� ����Ǵ� �޼ҵ�>
	public int updateStatus(int no) {
		int re = -1;
		String sql = "update qna set q_status='�亯�Ϸ�' where q_no = ?";
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception v) {
			System.out.println("���ܹ߻� : "+ v.getMessage());
		}
		return re;
	}
	
	
	//<�亯�� JTextArea���� ��ȸ�ϴ� �޼ҵ�>
		public ArrayList<QnAVo> searchAnswer(int q_no){
			ArrayList<QnAVo> list = new ArrayList<QnAVo>();
			String sql = "select a_content from answer where q_no=?";
			try {
				Connection conn = ConnectionProvider.getConnecton();
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
				System.out.println("���ܹ߻�" + q.getMessage());
			}
			return list;
		}
	
	
	//<�������� �亯�� ���(������ �̹� �� �� ���ڵ尡 �־� ����)�ϴ� �޼��� (ListPopUpâ ��� ��ư ���� ��)>
		public int addAnswer(String a_content, int q_no) {
			int re = -1;
			String sql = "update answer set a_date = default, a_content = ? where q_no = ? and user_no = 0 ";
			try {
				Connection conn = ConnectionProvider.getConnecton();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, a_content);
				pstmt.setInt(2, q_no);
				re=pstmt.executeUpdate();
				ConnectionProvider.close(pstmt, conn);
			}catch(Exception g) {
				System.out.println("���ܹ߻� :" + g.getMessage());
			}
			return  re;
		}
	
	//<����ڰ� ���Ǳ��� �ۼ��� �� �亯���̺� ������ �߰��Ǵ� �޼ҵ�> �亯�� ������ rowData�� �߰��� �ȵǼ� ����.
		public int blankAnswer() {
			int re = -1;
			String sql = "insert into answer values((SELECT NVL( MAX(a_no), 0) + 1 FROM answer ),(select max(q_no) from qna),null,null)";
			try {
				Connection conn = ConnectionProvider.getConnecton();
				Statement stmt = conn.createStatement();
				re = stmt.executeUpdate(sql);
				ConnectionProvider.close(stmt, conn);
			}catch(Exception t) {
				System.out.println("���ܹ߻� :" +t.getMessage());
			}
			return re;
		}
	
	//<����ڰ� �Է��� ������ ����ϴ� �޼ҵ�>
	public int addData(String q_type, String q_mail, String q_title, String q_content){
		String sql ="insert into qna values(( SELECT NVL( MAX(q_no) , 0) + 1 FROM qna ),?,?,?,?,null,default,default,3)";
		int count =-1;
		try {
			Connection conn = ConnectionProvider.getConnecton();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q_type);
			pstmt.setString(2, q_mail);
			pstmt.setString(3, q_title);
			pstmt.setString(4, q_content);

			count = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		}catch(Exception d) {
			System.out.println("���ܹ߻� :" +d.getMessage());
		}
		return count;
	}

	

//<���ǳ����� JTable rowData�� �� ����ϴ� �޼ҵ�>
	public ArrayList<QnAVo> addList(){
		ArrayList<QnAVo> list = new ArrayList<QnAVo>();
		String sql = "select q.q_no,q_type, q_title, q_inquirdate, q_status, a.a_date from qna q, answer a where q.q_no = a.q_no order by q.q_no";
		try{
			Connection conn = db.ConnectionProvider.getConnecton();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
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
			}db.ConnectionProvider.close(rs, stmt, conn);
			
		}catch(Exception c) {
			System.out.println("���ܹ߻� :" + c.getMessage());
		}
		return list;
		
	}

}