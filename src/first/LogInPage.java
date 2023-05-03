package first;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserDAO;
import home.MainPage;
import vo.UserVO;

// 로그인 화면
public class LogInPage extends JFrame {
	JTextField input_id;
	JTextField input_pwd;
	JButton btn_login;
	JButton btn_signup;
	public static int NO;
	Image background = new ImageIcon("login.jpg").getImage();
	public LogInPage() {
		
		JPanel pan_background = new JPanel() {
			public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        // 배경 이미지 그리기
		        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		    }
		};
		pan_background.setBounds(0, 0, 790, 730);
		
		// 로그인 판넬 구성
 		input_id = new JTextField(10);
		input_pwd = new JTextField(10);
		input_id.setBounds(350, 340, 200, 40);
		input_pwd.setBounds(350, 400, 200, 40);

		// 버튼 판넬 구성
		btn_login = new JButton("로그인");
		btn_signup = new JButton("회원가입");
		btn_login.setBounds(250, 500, 100, 50);
		btn_signup.setBounds(450, 500, 100, 50);
	
		
		// 전체 프레임 화면 구성
		setLayout(null);
		add(pan_background);
		add(input_id);
		add(input_pwd);
		add(btn_login);
		add(btn_signup);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 730);
		
		// 로그인 버튼 actionlistener
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = input_id.getText();
				// id, pwd 텍스트 필드가 공란일 때
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
					return;
				} else if(input_pwd.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
					return;
				}
				
				// id, pwd 로그인 유효성 검사
				String PWD = new UserDAO().getPWD(input_id.getText());
				if(input_pwd.getText().equals(PWD)) {
					UserVO u = new UserVO();
					u.setId(input_id.getText());
					u.setPwd(input_pwd.getText());
					NO = new UserDAO().getNO(u);
					new MainPage();
					setVisible(false); // 로그인 성공하면 First 프레임 invisible
				} else if(PWD.equals("")){JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
				} else {JOptionPane.showMessageDialog(null, "비밀번호가 잘못되었습니다.");}
			}
		});
		
		// 회원가입 버튼 눌렀을 때 회원가입 frame 호출
		btn_signup.addActionListener(e->new Signup());
	}
	
	// main 메서드
	public static void main(String[] args) {
		new LogInPage();
	}
	
	


}
