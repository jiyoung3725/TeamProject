package first;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UserDAO;
import jdk.javadoc.internal.doclets.formats.html.ModuleFrameWriter;
import utillist.RoundedButton;
import utillist.Utils;
import vo.UserVO;

public class Signup extends JFrame implements KeyListener {
	private JTextField jtf_id;
	private JPasswordField jtf_pwd;
	private JPasswordField jtf_pwdcheck;
	private JTextField jtf_name;
	private JTextField jtf_jumin1;
	private JPasswordField jtf_jumin2;
	private JTextField jtf_phone1;
	private JTextField jtf_phone2;
	private JTextField jtf_phone3;
	private JTextField jtf_email1;
	private JTextField jtf_email2;
	private boolean isCheckedID;
	private boolean isCheckedPWD;
	private JLabel jlb_pwdCheck;
	
	public Signup() {
		
		// ȸ�������� ���� �ʵ� ����... 
		jtf_id = new JTextField(10);
		jtf_pwd = new JPasswordField(10);
		jtf_pwdcheck = new JPasswordField(10);
		jtf_name = new JTextField(10);
		jtf_jumin1 = new JTextField(6);
		jtf_jumin2 = new JPasswordField(6);
		jtf_phone1 = new JTextField(3);
		jtf_phone2 = new JTextField(4);
		jtf_phone3 = new JTextField(4);
		jtf_email1 = new JTextField(6);
		jtf_email2 = new JTextField(6);
		// �޺��ڽ��� �̿��� ���ɻ�, ������ ���� 
		JComboBox<String> addr_list = new JComboBox<String>(Utils.address);
		JComboBox<String> interest_list = new JComboBox<String>(Utils.interest);
		
		// �� �гο� �� �ʵ� ����ֱ�
		JPanel pan_id = new JPanel();
		JPanel pan_pwd = new JPanel();
		JPanel pan_pwdcheck = new JPanel();
		JPanel pan_name = new JPanel();
		JPanel pan_interest = new JPanel();
		JPanel pan_jumin = new JPanel();
		JPanel pan_address = new JPanel();
		JPanel pan_phone = new JPanel();
		JPanel pan_email = new JPanel();
		pan_id.add(new JLabel(String.format("%-8s", "���̵�")));
		pan_id.add(jtf_id);
		pan_pwd.add(new JLabel(String.format("%-8s","��й�ȣ")));
		pan_pwd.add(jtf_pwd);
		pan_pwdcheck.add(new JLabel(String.format("%-8s","��й�ȣ Ȯ��")));
		pan_pwdcheck.add(jtf_pwdcheck);
		pan_name.add(new JLabel(String.format("%-8s","�̸�")));
		pan_name.add(jtf_name);
		pan_interest.add(new JLabel(String.format("%-8s","���ɻ�")));
		pan_interest.add(interest_list);
		pan_jumin.add(new JLabel(String.format("%-8s","�ֹι�ȣ")));
		pan_jumin.add(jtf_jumin1);
		pan_jumin.add(new JLabel("-"));
		pan_jumin.add(jtf_jumin2);
		pan_address.add(new JLabel(String.format("%-8s","������")));
		pan_address.add(addr_list);
		pan_phone.add(new JLabel(String.format("%-8s","��ȭ��ȣ")));
		pan_phone.add(jtf_phone1);
		pan_phone.add(new JLabel("-"));
		pan_phone.add(jtf_phone2);
		pan_phone.add(new JLabel("-"));
		pan_phone.add(jtf_phone3);
		pan_email.add(new JLabel(String.format("%-8s","�̸���")));
		pan_email.add(jtf_email1);
		pan_email.add(new JLabel("@"));
		pan_email.add(jtf_email2);
		 
		// ���̵� �ߺ� Ȯ�ι�ư �����
		JButton btn_idcheck = new RoundedButton("�ߺ� Ȯ��");
		btn_idcheck.setBounds(260,90, 80, 20);
		isCheckedID = false;	// �ߺ�Ȯ�� ���� boolean

		
		// ��й�ȣ Ȯ�� �� �����
		jlb_pwdCheck = new JLabel();
		jlb_pwdCheck.setForeground(Color.red);
		JPanel pan_checkjlb = new JPanel();
		pan_checkjlb.add(jlb_pwdCheck);
		
		// ������ �ʵ带 pan_input�� �־��ֱ�
		JPanel pan_input = new JPanel();
		pan_input.add(pan_id);
		pan_input.add(pan_pwd);
		pan_input.add(pan_pwdcheck);
		pan_input.add(pan_checkjlb);
		pan_input.add(pan_name);
		pan_input.add(pan_interest);
		pan_input.add(pan_address);
		pan_input.add(pan_jumin);
		pan_input.add(pan_phone);
		pan_input.add(pan_email);
		pan_input.setBounds(20, 70, 300, 350);
		
		// ���Թ�ư, ���� label �����
		JButton btn_signup = new RoundedButton("�����ϱ�");
		JLabel title_signup = new JLabel("ȸ������");
		title_signup.setBackground(Color.white);
		title_signup.setBounds(140, 30, 130, 30);
		title_signup.setFont(new Font("", Font.BOLD, 25));
		btn_signup.setBounds(140, 480, 100, 50);
		
		
		// ȭ�� ����
		setLayout(null);
		add(title_signup);
		add(btn_idcheck);
		add(pan_input);
		add(btn_signup);
		setSize(400,620);
		setVisible(true);
		
		// ���̵�, ��й�ȣ Ȯ�� ���� keylistener 
		jtf_pwdcheck.addKeyListener(this);
		jtf_pwd.addKeyListener(this);
		jtf_id.addKeyListener(this);
		
		// �ߺ�Ȯ�� ��ư actionlistener
		btn_idcheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtf_id.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
					return;
				}
				
				if(jtf_id.getText().length()<5) {
					JOptionPane.showMessageDialog(null, "���̵�� 5���� �̻��̾�� �մϴ�.");
					return;
				}
				
				int re = new UserDAO().checkID(jtf_id.getText());
				if(re>0) {
					JOptionPane.showMessageDialog(null, "�̹� ������� ���̵��Դϴ�. \n�ٽ� �Է����ּ���.");
					jtf_id.setText("");
					return;
				}
				JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
				isCheckedID = true;
			}
		});
		
		// ȸ������ ��ư actionlistener
		btn_signup.addActionListener(new ActionListener() {
			// ȸ������ ����
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!isCheckedID) checkSignUp("���̵� �ߺ�Ȯ���� ���ּ���.");
					if(!isCheckedPWD) checkSignUp("��й�ȣ�� Ȯ�� ���ּ���.");
					if(jtf_name.getText().equals("")) {
						checkSignUp("�̸��� �Է����ּ���.");
					} else if(jtf_jumin1.getText().equals("") || jtf_jumin2.getText().equals("")) {
						checkSignUp("�ֹι�ȣ�� �Է����ּ���.");
					} else if(jtf_phone1.getText().equals("") || jtf_phone2.getText().equals("") || jtf_phone3.getText().equals("")) {
						checkSignUp("��ȭ��ȣ�� �Է����ּ���.");
					} else if(jtf_email1.getText().equals("") || jtf_email2.getText().equals("")) {
						checkSignUp("�̸����� �Է����ּ���.");
					}
					if(jtf_jumin1.getText().length()!=6 || jtf_jumin2.getText().length()!=7) {
						checkSignUp("��Ȯ�� �ֹι�ȣ�� �Է��ϼ���.");
						jtf_jumin1.setText("");
						jtf_jumin2.setText("");
					}
					Integer.parseInt(jtf_phone1.getText());
					Integer.parseInt(jtf_phone2.getText());
					Integer.parseInt(jtf_phone3.getText());
					Integer.parseInt(jtf_jumin1.getText());
					Integer.parseInt(jtf_jumin2.getText());
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �ֹι�ȣ�� ���ڸ� �Է��ϼ���.");
					return;
				} catch (RuntimeException e3) {
					return;
				}
				
				// �Է� ���� uservo�� ���
				UserVO u = new UserVO();
				u.setName(jtf_name.getText());
				u.setId(jtf_id.getText());
				u.setPwd(jtf_pwd.getText());
				u.setJumin(jtf_jumin1.getText()+jtf_jumin2.getText());
				u.setEmail(jtf_email1.getText()+"@"+jtf_email2.getText());
				u.setPhone(jtf_phone1.getText()+"-"+jtf_phone2.getText()+"-"+jtf_phone3.getText());
				u.setAddress(Utils.address[addr_list.getSelectedIndex()]);
				u.setInterest(Utils.interest[interest_list.getSelectedIndex()]);
				
				// db�� ȸ�� ���� ����
				int re = new UserDAO().signup(u);
				if(re==-1) JOptionPane.showMessageDialog(null, "ȸ������ ����");
				else {
					JOptionPane.showMessageDialog(null, "ȸ������ ����");
				}
			}
		});
	}
	
	
	// ��й�ȣ �ߺ� Ȯ���� ���� ��ӹ��� keylistener
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == jtf_id) {
			isCheckedID = false;
			return;
		} 
		if(jtf_pwd.getText().length()<8) {
			jlb_pwdCheck.setText("��й�ȣ�� 8�� �̻��̾�� �մϴ�.");
		} else {
			String tmp = jtf_pwdcheck.getText();
			if(tmp.equals(jtf_pwd.getText())) {
				jlb_pwdCheck.setText("��й�ȣ�� ��ġ�մϴ�.");
				isCheckedPWD = true;
			} else {
				jlb_pwdCheck.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				isCheckedPWD = false;
			}
		}
	}
	
	
	// �Է� ���� �߸��� �� runtimeexception �߻�
	public void checkSignUp(String s) {
		JOptionPane.showMessageDialog(null, s);
		throw new RuntimeException();
	}
}
