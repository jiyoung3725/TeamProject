package Post;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Comments.Comments;
import Reports.reports;
import dao.LikedDAO;
import dao.PostDAO;
import dao.commentDAO;
import vo.commentsVO;
import vo.PostVO;
import db.ConnectionProvider;
	
public class post_Reader extends JFrame{
	
	reports rp;	//신고 버튼 클릭시 나타날 창 세팅위한 변수
	Vector<String> post = new Vector<String>();
	
	//전체적으로 위치 수정하기
	public post_Reader(int postNum) {
		loadData(postNum);

		String category = post.get(1);
		String title = post.get(2);
		String user_id = post.get(3);
		String b_content = post.get(4);
		String interest = post.get(5);
		String date_board = post.get(6);	
		
		JPanel p_infrom = new JPanel();	//키워드, 제목, 글쓴이
		p_infrom.setLayout(new GridLayout(3,1));
		JLabel jlb_keyword = new JLabel(category+" | "+interest);
		JLabel jlb_title = new JLabel(title);
		jlb_title.setFont(new Font("Serif", Font.BOLD, 20));
		JLabel jlb_writer = new JLabel(user_id);
		p_infrom.add(jlb_keyword);
		p_infrom.add(jlb_title);
		p_infrom.add(jlb_writer);
		
		JButton btn_apply;
		JButton btn_liked;
		
		JPanel p_apply = new JPanel();	//인원, 참가신청 관련 (위치 변경 예정)
		JLabel jlb_personnel = new JLabel("현재 인원 :    명");	//어떻게 구현?, count
		btn_apply = new JButton("참가신청");
		p_apply.add(jlb_personnel);
		p_apply.add(btn_apply);
		
		JPanel p_liked = new JPanel();	//좋아요 관련 (위치 변경 예정)
		JLabel jlb_liked = new JLabel("관심 인원 :    명");
		btn_liked = new JButton("♥");
		p_liked.add(jlb_liked);
		p_liked.add(btn_liked);
		
		JPanel p_AandL = new JPanel();
		p_AandL.add(p_apply);
		p_AandL.add(p_liked);

		JPanel p_upperPart = new JPanel();
		p_upperPart.setLayout(new BorderLayout());
		p_upperPart.add(p_infrom, BorderLayout.CENTER);
		p_upperPart.add(p_AandL, BorderLayout.SOUTH);
		
		JPanel p_contents = new JPanel();
		p_contents.setLayout(new BorderLayout());
		JTextArea jta_contents = new JTextArea();
		jta_contents.setText(b_content);
		JScrollPane jsp = new JScrollPane(jta_contents);
		
		JPanel p_lowerPart = new JPanel();
		
		JPanel p_updateDelete = new JPanel();
		JButton btn_update=new JButton("수정");
		JButton btn_delete=new JButton("삭제");
		p_updateDelete.add(btn_update);
		p_updateDelete.add(btn_delete);
		
		JPanel p_report = new JPanel();
		JButton btn_report;
		btn_report = new JButton("신고");	//신고 버튼 위치 변경 할 것 
		p_report.add(btn_report);
		
		p_lowerPart.add(p_updateDelete);
		p_lowerPart.add(p_report);
		
		p_contents.add(jsp, BorderLayout.CENTER);
		p_contents.add(p_lowerPart, BorderLayout.SOUTH);
		
		Comments cms = new Comments();
		
		add(p_upperPart, BorderLayout.NORTH);
		add(p_contents, BorderLayout.CENTER);
		add(cms, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PostDAO daoP = new PostDAO();
				int chk = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "게시글 삭제", JOptionPane.YES_NO_OPTION);
					if(chk == 0) {
						int re = daoP.deletePost();
						JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다.");
						setVisible(false);
						//게시글 새로고침
					}
			}
		});
		
		btn_report.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rp = new reports();
				rp.setVisible(true);
			}
		});
		
		btn_liked.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LikedDAO lDAO = new LikedDAO();
				
				int chk = lDAO.checkAlready();
				System.out.println(chk);
				if(chk==1) {
					JOptionPane.showMessageDialog(null, "이미 나의 좋아요에 추가하셨습니다.");
					return;
				}else {
					int re = lDAO.addLiked();
					if(re==1) {
						JOptionPane.showMessageDialog(null, "나의 좋아요에 추가되었습니다.");
					}
				}
			}
		});
		
	}
	//게시글 출력
	public void loadData(int p_b_no) {
		String sql = "SELECT b_no, category, title, user_id, b_content, b.interest, date_board, "
				+ "application, b_cnt "
				+ "FROM board b, user_info u "
				+ "WHERE b.user_no=u.user_no and b.b_no="+p_b_no;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int b_no = rs.getInt(1);
				String category = rs.getString(2);
				String title = rs.getString(3);
				String user_id = rs.getString(4);
				String b_content = rs.getString(5);
				String interest = rs.getString(6);
				String date_board = rs.getString(7);
				String application = rs.getString(8);
				int b_cnt = rs.getInt(9);
				post.add(b_no+"");
				post.add(category);
				post.add(title);
				post.add(user_id);
				post.add(b_content);
				post.add(interest);
				post.add(date_board);
				post.add(application);
				post.add(b_cnt+"");

			}
			ConnectionProvider.close(rs, stmt, conn);			
		} catch (Exception e) {
			System.out.println("예외:"+e.getMessage());
		}
		
	}	
	public static void main(String[] args) {
//		new post_Reader2();
	}
	
	
}
