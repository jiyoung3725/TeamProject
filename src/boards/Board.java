package boards;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.BoardDAO;
import vo.BoardVO;

public class Board extends JFrame {
	JTextField jtf_search;
	JComboBox<String> jcb_option;	//같이해요, 우리동네 선택
	String []interest = {"건강/운동","음식/요리","영화/공연/전시","미술/공예","노래/음악","재테크","기타"};
	JCheckBox []jcb = new JCheckBox[interest.length];
	JTable table;
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JRadioButton jrb_option1;
	JRadioButton jrb_option2;
		
	public Board() {
		JPanel p_main = new JPanel();	//게시판 메인 화면
		p_main.setLayout(new BorderLayout());
		JPanel p_search1 = new JPanel();	//검색창 전체 패널
		JPanel p_search2 = new JPanel();	//검색창 상단
		JPanel p_search3 = new JPanel();	//검색창 하단
		JPanel p_interest = new JPanel();	//관심사 검색 패널
		p_interest.setLayout(new GridLayout(7,1));
		JPanel p_etc = new JPanel();	//글쓰기, 페이지 넘김
		p_etc.setLayout(new GridLayout(1,3));
		JPanel p_etc1 = new JPanel();
		JPanel p_etc2 = new JPanel();
		JPanel p_etc3 = new JPanel();
		p_etc.add(p_etc1);
		p_etc.add(p_etc2);
		p_etc.add(p_etc3);
		
		p_search1.setLayout(new GridLayout(3,1));
		jcb_option = new JComboBox<String>();
		jcb_option.addItem("함께해요");
		jcb_option.addItem("동네생활");
		
		jtf_search = new JTextField(20);
		JButton btn_search = new JButton("검색");
		JButton btn_clear = new JButton("초기화");
		jrb_option1 = new JRadioButton("인기순") ;	//인기순 정렬
		jrb_option2 = new JRadioButton("최신순");	//최신순 정렬
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb_option1);
		bg.add(jrb_option2);
		
		
		p_search2.add(jcb_option);
		p_search2.add(jtf_search);
		p_search2.add(btn_search);
		p_search2.add(btn_clear);
		p_search3.add(jrb_option1);
		p_search3.add(jrb_option2);
		p_search1.add(p_search2);
		p_search1.add(p_search3);
		
		p_main.add(p_search1, BorderLayout.NORTH);
		
		for(int i=0; i<interest.length ; i++) {
			jcb[i] = new JCheckBox(interest[i]);
			p_interest.add(jcb[i]);			
		}
		p_main.add(p_interest, BorderLayout.WEST);
		
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		
		colNames.add("번호");
		colNames.add("고객번호");
		colNames.add("위치");
		colNames.add("카테고리");
		colNames.add("관심사");
		
		colNames.add("작성날짜");
		colNames.add("모집여부");
		colNames.add("조회수");
		colNames.add("♥");
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		p_main.add(jsp, BorderLayout.CENTER);
		
		JButton btn_pre = new JButton("이전");
		JLabel jlb_page = new JLabel("page");	//page 번호에 따라 바뀌도록 수정 예정
		JButton btn_post = new JButton("다음");
		p_etc2.add(btn_pre);
		p_etc2.add(jlb_page);
		p_etc2.add(btn_post);
		
		JButton btn_write = new JButton("게시글 작성");
		p_etc3.add(btn_write);
		p_main.add(p_etc, BorderLayout.SOUTH);
		add(p_main);
		
		loadAllList(); 
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jrb_option1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = jrb_option1.getActionCommand();
				if(l.equals("인기순")) loadlikedList();
			}
		});
	}
	
	
	
	public void loadAllList() {
		
		rowData.clear();
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.viewAllList();
		
		for( BoardVO b :list) {
			Vector<String> v = new Vector<>();
			v.add(b.getNo()+"");
			v.add(b.getAddress());
			v.add(b.getCategory());
			v.add(b.getInterest());
			v.add(b.getTitle());
			v.add(b.getDate_create()+"");
			v.add(b.getAppilcation());
			v.add(b.getB_cnt()+"");
			v.add(b.getL_cnt()+"");
			rowData.add(v);
		}
		table.updateUI();
	}
	
public void loadlikedList() {
		rowData.clear();
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.viewLikedList();
		
		for( BoardVO b :list) {
			Vector<String> v = new Vector<>();
			v.add(b.getNo()+"");
			v.add(b.getAddress());
			v.add(b.getCategory());
			v.add(b.getInterest());
			v.add(b.getTitle());
			v.add(b.getDate_create()+"");
			v.add(b.getAppilcation());
			v.add(b.getB_cnt()+"");
			v.add(b.getL_cnt()+"");
			rowData.add(v);
		}
		table.updateUI();
	}
	
	public static void main(String[] args) {
		new Board();
	} 

}
;