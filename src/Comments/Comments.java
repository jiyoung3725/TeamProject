package Comments;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.commentDAO;
import vo.commentsVO;

public class Comments extends JPanel{
	JTextField jtf_comments;
	JPanel p_allComments;
	ArrayList<Vector<String>> list_comments;
	JLabel []comments;
	
	public Comments() {
		setLayout(new BorderLayout());
		JPanel p_comments_write = new JPanel();
		
		JLabel jlb_comment = new JLabel("댓글");
		jtf_comments = new JTextField(30);
		JButton btn_commentsApply = new JButton("등록");
		p_comments_write.add(jlb_comment);
		p_comments_write.add(jtf_comments);
		p_comments_write.add(btn_commentsApply);
		
		list_comments = new ArrayList<Vector<String>>();
		p_allComments = new JPanel();
		p_allComments.setLayout(new GridLayout(list_comments.size(),1));
		loadData();
			
		add(p_comments_write, BorderLayout.NORTH);
		add(p_allComments, BorderLayout.CENTER);
			
		setSize(400,200);
		setVisible(true);
		
		btn_commentsApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String c_content = jtf_comments.getText();
				if(c_content == "") {
					JOptionPane.showMessageDialog(null, "댓글을 입력해주세요.");
				}
				commentsVO voC = new commentsVO(c_content);
				commentDAO daoC = new commentDAO();
				int re = daoC.addComments(voC);
				if(re == 1) {
					JOptionPane.showMessageDialog(null, "댓글이 등록되었습니다.");
				}
				jtf_comments.setText("");
				loadData();
			}
		});
	}
	public void loadData() {
		commentDAO daoC = new commentDAO();
		list_comments = daoC.loadComments();
		JLabel []comments = new JLabel[list_comments.size()];
		p_allComments.removeAll();
		int i = 0;
		String commentInform="";
		while(i<list_comments.size()) {
			int j=0;
			JLabel jlb_comments = new JLabel("");
			commentInform="";
			while(j<3) {
				if(j==0) {
					commentInform = commentInform + list_comments.get(i).get(j)+"  ";
				}else if (j==1) {
					commentInform = commentInform + list_comments.get(i).get(j)+"(";
				}else {
					commentInform = commentInform + list_comments.get(i).get(j)+")";
				}
				j++;
			}
			jlb_comments.setText(commentInform);
			comments[i] = jlb_comments;
			p_allComments.add(comments[i]);
			i++;
		}
		p_allComments.updateUI();
	}
}
