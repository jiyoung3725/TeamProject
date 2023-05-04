package om.sist.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
 
import com.sist.vo.QnAVO;

import DB.ConnectionProvider;
import customerservice.Inner_qna;
import customerservice.Qna_List;

public class QnADAO {

	public int qnaList(){
		int re = -1;
		String sql = "insert into q_no, q_type, q_mail, q_title, q_content, q_attachedfile, q_inquirdate, userno from qna";
		try {
			Connection conn = ConnectionProvider.getconnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(stmt, conn);
		}catch(Exception e) {
			System.out.println("예외발생 : " + e.getMessage());
		}
		return re;
	}
	

}

