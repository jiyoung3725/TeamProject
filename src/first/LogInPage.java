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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.Admin;
import dao.UserDAO;
import vo.UserVO;

// �α��� ȭ��
public class LogInPage extends JFrame {
	private JTextField jtf_id;
	private JTextField jtf_pwd;
	private JButton btn_login;
	private JButton btn_signup;
	private static  int NO;

	public LogInPage() {
		// ��� �̹��� �ֱ� ( �󺧿� �־���)
		JLabel jlb = new JLabel(new ImageIcon("login.jpg"));
		jlb.setBounds(-10, -10, 790, 730);

		// �α��� �ǳ� ����
 		jtf_id = new JTextField(10);
		jtf_pwd = new JPasswordField(10);
		jtf_id.setBounds(350, 340, 200, 40);
		jtf_pwd.setBounds(350, 400, 200, 40);

		// ��ư �ǳ� ����
		btn_login = new JButton("�α���");
		btn_signup = new JButton("ȸ������");
		btn_login.setBounds(250, 500, 100, 50);
		btn_signup.setBounds(450, 500, 100, 50);
	
		
		// ��ü ������ ȭ�� ����
		setLayout(null);
		add(jlb);
		add(jtf_id);
		add(jtf_pwd);
		add(btn_login);
		add(btn_signup);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 730);
		
		// �α��� ��ư actionlistener
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input_id = jtf_id.getText();
				String input_pwd = jtf_pwd.getText();
				// id, pwd �ؽ�Ʈ �ʵ尡 ������ ��
				if(input_id.equals("")) {
					JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.");
					return;
				} else if(input_pwd.equals("")) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
					return;
				}
				// id ���� ���� �˻�
				if(new UserDAO().checkID(input_id)<1) {
					JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵��Դϴ�.");
					jtf_id.setText("");
					jtf_pwd.setText("");
					return;
				}
				
				if(input_id.equals("admin") && input_pwd.equals("admin")) {
					new Admin();
					dispose();
					return;
				}
				// id, pwd �α��� ��ȿ�� �˻�
				String PWD = new UserDAO().getPWD(input_id);
				if(input_pwd.equals(PWD)) {		// ���̵�� ��й�ȣ�� ������
					NO = new UserDAO().getNO(input_id);		// �α��� �� ���α׷� ������ ���� ȸ�� ��ȣ �ʱ�ȭ
					new MainPage();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �߸��Ǿ����ϴ�.");
					jtf_pwd.setText("");
				}
			}
		});
		
		// ȸ������ ��ư ������ �� ȸ������ frame ȣ��
		btn_signup.addActionListener(e->new Signup());
	}
	
	// main �޼���
	public static void main(String[] args) {
		new LogInPage();
	}
	
	public static int getNO() {
		return NO;
	}
	


}
