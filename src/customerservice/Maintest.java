package customerservice;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Maintest extends JFrame {
	
	JTextArea jta_content;
	JButton btn_search;
	JButton btn_chat;
	JTabbedPane pane;
	JTabbedPane inner_pane;
	JTextField jtf_search;
	Inner_qna tab01;
	Qna_List tab02;
	 
	public Maintest() {
		JPanel frequent_qna = new JPanel();
		btn_search = new JButton("검색");
		btn_chat = new JButton("채팅 상담하기");
		jta_content = new JTextArea();
		jtf_search = new JTextField(30);
	    pane = new JTabbedPane();
		inner_pane = new JTabbedPane();
		tab01 = new Inner_qna();
		tab02 = new Qna_List();
		JPanel p = new JPanel();
		p.add(new JLabel("고객센터"));
		p.add(jtf_search);
		p.add(btn_search);
		p.add(btn_chat);
		add(p, BorderLayout.NORTH);
		
		
		pane.addTab("1:1문의", inner_pane);
		pane.addTab("자주묻는 질문",frequent_qna);
		inner_pane.addTab("문의하기", tab01);
		inner_pane.addTab("문의내역", tab02);
		
		add(pane, BorderLayout.CENTER);
		
		setSize(700,600);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new Maintest();
	
		
	}

}
