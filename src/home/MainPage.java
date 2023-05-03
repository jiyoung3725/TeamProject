package home;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boards.Board;
import messages.Message;
import utillist.RoundedButton;


public class MainPage extends JFrame implements ActionListener{
	static JLabel jlb_title;
	RoundedButton[] btn_arr;
	JPanel pan_main;
	CardLayout cadrdlayout = new CardLayout();
	
	public MainPage() {
		// 메인 화면 및 각 게시판 Panel 생성 (CardLayout을 이용한 화면 전환)
		pan_main = new JPanel(cadrdlayout);
		JPanel pan_home = new Home();	// JPanel을 상속한 Home 클래스 호출
		pan_main.setBackground(new Color(206,212,192));
		JPanel pan_community = new JPanel();
		JPanel pan_memo = new Message();	// JPanel을 상속한 Memo 클래스 호출
		JPanel pan_game = new JPanel();
		JPanel pan_cs = new JPanel();
		JPanel pan_setting = new Setting();	// JPanel을 상속한 Setting 클래스 호출
		// 커뮤니티, game, cs Panel을 식별하기 위한 임의 설정 Label
		pan_community.add(new JLabel("소모임커뮤니티입니다."));
		pan_game.add(new JLabel("게임입니다."));
		pan_cs.add(new JLabel("고객센터입니다."));
		
		pan_main.add("홈", pan_home);
		pan_main.add("커뮤니티", pan_community);
		pan_main.add("쪽지함", pan_memo);
		pan_main.add("게임",pan_game);
		pan_main.add("고객센터",pan_cs);
		pan_main.add("관리", pan_setting);
		
		// 기본으로 보여지는 화면은 홈 Panel
		cadrdlayout.show(pan_main, "홈");
		
		
		// 배열을 사용한 버튼 생성
		JPanel pan_btn = new JPanel(new GridLayout(7, 1));
		String[] btn_name = {"홈", "커뮤니티", "쪽지함", "게임", "고객센터", "관리", "로그아웃"};
		btn_arr =new RoundedButton[7];
		for(int i=0; i<7; i++) {
			btn_arr[i] = new RoundedButton(btn_name[i]);
			btn_arr[i].setFont(new Font("HY헤드라인", Font.BOLD, 15));
			btn_arr[i].addActionListener(this);
			pan_btn.add(btn_arr[i]);
		}
		pan_btn.setBackground(new Color(206,212,192));
		btn_arr[1].addActionListener(e->new Board());
		
		// 화면 설정
		add(pan_main, BorderLayout.CENTER);
		add(pan_btn, BorderLayout.EAST);
		setVisible(true);
		setSize(900,550);
		setBackground(new Color(206,212,192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// 버튼 액션 생성
	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그아웃 버튼 눌렀을 때 프로그램 종료
		if(e.getSource()==btn_arr[6]) {
			System.exit(0);
		}
		
		// 카드 레이아웃을 사용한 화면 전환
		cadrdlayout.show(pan_main, e.getActionCommand());
	}
	
	// 홈피 제목 변경 메서드
	public void setTitle(String s) {
		jlb_title.setText(s);
	}
	


}
