package customer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.sist.dao.QnaDAO;
import com.sist.vo.QnAVo;



public class MainTest extends JFrame {
	//<탭 생성화면>
	JTextArea jta_content;
	JButton btn_search;
	JButton btn_chat;
	JTabbedPane pane;
	JTabbedPane inner_pane; 
	JTextField jtf_search;
	Frequent_QnA tab;
	
	Inner_qna tab01;
	Qna_List tab02;
	
	public MainTest() {
		JPanel p1 = new JPanel();
		JLabel label = new JLabel("하비타운 고객센터");
		label.setFont(new Font("맑은고딕",getFont().BOLD,20));
		btn_search = new JButton("검색");
		btn_chat = new JButton("채팅 상담하기");
		jta_content = new JTextArea();
		jtf_search = new JTextField(30);
	    pane = new JTabbedPane();
		inner_pane = new JTabbedPane();
		tab01 = new Inner_qna();
		tab02 = new Qna_List();
		tab = new Frequent_QnA();
		JPanel p = new JPanel();
		p.add(label);
		p.add(jtf_search);
		p.add(btn_search);
		p.add(btn_chat);
		add(p, BorderLayout.NORTH);
		
		
		pane.addTab("1:1문의", inner_pane);
		pane.addTab("자주묻는 질문", tab);
		inner_pane.addTab("문의하기", tab01);
		inner_pane.addTab("문의내역", tab02);
		
		add(pane, BorderLayout.CENTER);
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//<문의글 등록 후 문의내역탭 눌렀을 때 등록된 값을 추가한 화면을 보여주는 이벤트 리스너>
		inner_pane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int row = inner_pane.getSelectedIndex();
				if(row==0) {
				
				}else {
					tab02.loadData();
				}
			}
		});
//		//<검색버튼 누르면 해당하는 문의글 나오는 기능 >
//		btn_search.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Inner_qna i = new Inner_qna();
//				String title = i.jtf_title.getText();
//				String content = i.jta_content.getText();
//				QnaDAO dao = new QnaDAO();
//				ArrayList<QnAVo> list = dao.findAll(title, content);
//				for(QnAVo vo : list) {
//					String allTitle = vo.getQ_title();
//					String allContent = vo.getQ_content();
//					QnAVo v = new QnAVo();
//					v.setQ_title(title);
//					v.setQ_content(content);
//					list.add(v);
//				}
//			}
//		});
	}
	
	public static void main(String[] args) {
		new MainTest();
	
		
	}

}

