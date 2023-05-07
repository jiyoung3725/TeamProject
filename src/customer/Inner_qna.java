package customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sist.dao.QnaDAO;
import com.sist.vo.QnAVo;



	public class Inner_qna extends JPanel {
		JComboBox<String> combo_b;
		JTextField jtf_mail;
		JTextField jtf_title;
		JTextField jtf_file;
		JTextArea jta_content;
		JCheckBox check_b;
		Date sysDate;
		private Date inquiredate;
	 
		public Inner_qna() {
			String[] combo = {"소모임","쪽지함","게임"};
			combo_b = new JComboBox<String>(combo);
			
			jtf_mail = new JTextField(50);
			jtf_title = new JTextField(60);
			jtf_file = new JTextField(50);
			jta_content = new JTextArea(10,60);
			JScrollPane jsp = new JScrollPane(jta_content);
			check_b = new JCheckBox();
			JButton btn_add = new JButton("등록");
			JButton btn_send = new JButton("보내기");
			
			JPanel p = new JPanel();
			p.setSize(600,10);
			p.add(new JLabel("문의유형"));
			p.add(combo_b);
			p.add(new JLabel("이메일"));
			p.add(jtf_mail);
			add(p, BorderLayout.NORTH);
			 
			JPanel p2 = new JPanel();
			p2.add(new JLabel("제목"));
			p2.add(jtf_title);
			JPanel p3 = new JPanel();
			p3.add(new JLabel("문의내용"));
			p3.add(jsp);
			JPanel p4 = new JPanel();
			p4.add(new JLabel("첨부파일"));
			p4.add(jtf_file);
			p4.add(btn_add);
			JPanel p5 = new JPanel();
			p5.add(check_b);
			p5.add(new JLabel("(필수) 개인정보 수집 및 이용동의"));

			JPanel last = new JPanel();
			last.setLayout(new BoxLayout(last, BoxLayout.Y_AXIS));
			last.add(Box.createRigidArea(new Dimension(0,10)));
			last.add(p2);
			last.add(Box.createRigidArea(new Dimension(0,10)));
			last.add(p3);
			last.add(Box.createRigidArea(new Dimension(0,10)));
			last.add(p4);
			last.add(Box.createRigidArea(new Dimension(0,10)));
			last.add(p5);
			add(last);
			add(last, BorderLayout.CENTER);
			
			JPanel p9 = new JPanel();
			p9.add(btn_send);
			add(p9, BorderLayout.SOUTH);
			

			setSize(600,500);
			setVisible(true);
			//<보내기 버튼을 눌러 사용자가 입력한 내용을 추가하는 메소드>
			btn_send.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String type = (String)combo_b.getSelectedItem();
					String mail = jtf_mail.getText();
					String title = jtf_title.getText();
					String content = jta_content.getText();
					QnAVo v = new QnAVo();
					QnaDAO dao = new QnaDAO();
					int count = dao.addData(type, mail, title, content);
					if(count==1) {
						JOptionPane.showMessageDialog(null, "문의가 등록되었습니다.");
						dao.blankAnswer(); // 문의글이 정상적으로 등록됐을 때 내용이 빈 답변새성 메소드 호출
						jtf_mail.setText("");
						jtf_title.setText("");
						jta_content.setText("");
						jtf_file.setText("");
						
						
					}else {
						JOptionPane.showMessageDialog(null, "빈칸이 있습니다.");
					}
					
				}
				
				
				
			});
		}

	
		public static void main(String[] args) {
			new Inner_qna();
		}

		
	
}
