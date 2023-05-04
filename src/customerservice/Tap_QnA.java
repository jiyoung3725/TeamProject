package customerservice;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Tap_QnA extends JPanel {
	JTabbedPane pane_mainQnA;
	
	JTextField jtf_mail;
	JTextField jtf_title;
	JTextArea jta_content;
	JButton btn_send;
	
	
	public Tap_QnA() {
		pane_mainQnA = new JTabbedPane();
		jtf_mail = new JTextField(15);
		jtf_title = new JTextField(15);
		btn_send = new JButton("보내기");
		jta_content = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta_content);
		JCheckBox jcb = new JCheckBox();
		
		JPanel qna = new JPanel();
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		qna.setLayout(new GridLayout(4,2));
		qna.add(new JLabel("이  메  일"));
		qna.add(jtf_mail);
		qna.add(new JLabel("제       목"));
		qna.add(jtf_title);
		qna.add(new JLabel("문의내용"));
		qna.add(jsp);
		
		pane_mainQnA.addTab("문의하기", new JPanel());
		pane_mainQnA.addTab("문의내역",new JPanel() );
		add(pane_mainQnA, BorderLayout.CENTER);
		
		
		pane_mainQnA.setSize(850,650);
		pane_mainQnA.setVisible(true);
	
	
	
	}
	public void question() {
		
	}
	
	public void main(String[] args) {

	}
}
