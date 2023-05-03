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

// �α��� ȭ��
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
		        // ��� �̹��� �׸���
		        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		    }
		};
		pan_background.setBounds(0, 0, 790, 730);
		
		// �α��� �ǳ� ����
 		input_id = new JTextField(10);
		input_pwd = new JTextField(10);
		input_id.setBounds(350, 340, 200, 40);
		input_pwd.setBounds(350, 400, 200, 40);

		// ��ư �ǳ� ����
		btn_login = new JButton("�α���");
		btn_signup = new JButton("ȸ������");
		btn_login.setBounds(250, 500, 100, 50);
		btn_signup.setBounds(450, 500, 100, 50);
	
		
		// ��ü ������ ȭ�� ����
		setLayout(null);
		add(pan_background);
		add(input_id);
		add(input_pwd);
		add(btn_login);
		add(btn_signup);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 730);
		
		// �α��� ��ư actionlistener
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = input_id.getText();
				// id, pwd �ؽ�Ʈ �ʵ尡 ������ ��
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.");
					return;
				} else if(input_pwd.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
					return;
				}
				
				// id, pwd �α��� ��ȿ�� �˻�
				String PWD = new UserDAO().getPWD(input_id.getText());
				if(input_pwd.getText().equals(PWD)) {
					UserVO u = new UserVO();
					u.setId(input_id.getText());
					u.setPwd(input_pwd.getText());
					NO = new UserDAO().getNO(u);
					new MainPage();
					setVisible(false); // �α��� �����ϸ� First ������ invisible
				} else if(PWD.equals("")){JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵��Դϴ�.");
				} else {JOptionPane.showMessageDialog(null, "��й�ȣ�� �߸��Ǿ����ϴ�.");}
			}
		});
		
		// ȸ������ ��ư ������ �� ȸ������ frame ȣ��
		btn_signup.addActionListener(e->new Signup());
	}
	
	// main �޼���
	public static void main(String[] args) {
		new LogInPage();
	}
	
	


}
