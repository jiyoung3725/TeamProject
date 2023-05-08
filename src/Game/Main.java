package Game;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game2.GamePage;
import dao.GameDAO;
import first.LogInPage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Main extends JFrame {
	
	private Image screenImage;
	private Graphics screenGraphic;
	private Image MainImage;
	private JLabel jlb_welcome;
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 400;
	
	Container c = getContentPane();
	
	public Main() {
		setTitle("Main");
		c.setLayout(null);
		
		
		//유저님 환영합니다! 문구와 함께, 데이터 받기
		jlb_welcome = new JLabel();
		jlb_welcome = new JLabel( new GameDAO().getUserInfo(LogInPage.getNO())+ "님 환영합니다! ");
		jlb_welcome.setBackground(Color.white);
		jlb_welcome.setOpaque(true);
		jlb_welcome.setFont(new Font("", Font.PLAIN, 10));
		jlb_welcome.setBounds(22, 66, 187, 30);
		c.add(jlb_welcome);
		
		// 타이틀 설정
		JLabel title_1to50 = new JLabel("1  to  50");
		title_1to50.setFont(new Font("굴림", Font.BOLD, 30));
		title_1to50.setBounds(214, 10, 135, 52);
		c.add(title_1to50);
		
		
		// 움짤 넣기
		try {
			JPanel panel = new JPanel() {
			    @Override
			    protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        ImageIcon icon = new ImageIcon("Start.gif");
			        icon.paintIcon(this, g, 0, 0);
			    }
			};
			panel.setBounds(343, 146, 170, 172);
			c.add(panel);
		} catch (Exception e) {
			System.out.println("예외발생" + e.getMessage());
		}
		
		//시작하기버튼, 게임시작과 연동
		JButton btn_Start = new JButton("시작하기");
		btn_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// GAME 페이지로 이동
				new GamePage();
			}
		});
		btn_Start.setBounds(22, 196, 91, 23);
		c.add(btn_Start);
		 
		//순위보기버튼, 순위보기페이지와 연동
		JButton btn_Ranking = new JButton("순위보기");
		btn_Ranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 순위보기 페이지로 연동
				new RankingPage();
			}
		});
		btn_Ranking.setBounds(70, 229, 91, 23);
		c.add(btn_Ranking);
		
		
		JButton btn_Setting = new JButton("설정하기");
		btn_Setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 설정 페이지로 이동
			}
		});
		btn_Setting.setBounds(112, 262, 91, 23);
		c.add(btn_Setting);
		
		
		JButton btn_Exit = new JButton("나가기");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			//나가기 페이지로 이동
		});
		btn_Exit.setBounds(155, 295, 91, 23);
		c.add(btn_Exit);

		
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
}
