package first;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UserDAO;
import vo.UserVO;

public class Signup extends JFrame {
	JTextField jtf_id;
	JTextField jtf_pwd;
	JTextField jtf_pwdcheck;
	JTextField jtf_name;
	JTextField jtf_jumin1;
	JTextField jtf_jumin2;
	JTextField jtf_phone1;
	JTextField jtf_phone2;
	JTextField jtf_phone3;
	JTextField jtf_email1;
	JTextField jtf_email2;
	public Signup() {
		String[] address = {"����", "����"};
		add(new JLabel("ȸ������"));
		JPanel pan_input = new JPanel(new FlowLayout());
		jtf_id = new JTextField(10);
		jtf_pwd = new JTextField(10);
		jtf_pwdcheck = new JTextField(10);
		jtf_name = new JTextField(10);
		jtf_jumin1 = new JTextField(6);
		jtf_jumin2 = new JTextField(6);
		jtf_phone1 = new JTextField(3);
		jtf_phone2 = new JTextField(4);
		jtf_phone3 = new JTextField(4);
		jtf_email1 = new JTextField(6);
		jtf_email2 = new JTextField(6);
		JComboBox<String> jcb_list = new JComboBox<String>(address);
		JPanel pan_id = new JPanel();
		JPanel pan_pwd = new JPanel();
		JPanel pan_pwdcheck = new JPanel();
		JPanel pan_name = new JPanel();
		JPanel pan_address = new JPanel();
		JPanel pan_jumin = new JPanel();
		JPanel pan_phone = new JPanel();
		JPanel pan_email = new JPanel();
		pan_id.add(new JLabel("���̵�"));
		pan_id.add(jtf_id);
		pan_pwd.add(new JLabel("��й�ȣ"));
		pan_pwd.add(jtf_pwd);
		pan_pwdcheck.add(new JLabel("��й�ȣ Ȯ��"));
		pan_pwdcheck.add(jtf_pwdcheck);
		pan_name.add(new JLabel("�̸�"));
		pan_name.add(jtf_name);
		pan_address.add(new JLabel("������"));
		pan_address.add(jcb_list);
		pan_jumin.add(new JLabel("�ֹι�ȣ"));
		pan_jumin.add(jtf_jumin1);
		pan_jumin.add(new JLabel("-"));
		pan_jumin.add(jtf_jumin2);
		pan_phone.add(new JLabel("��ȭ��ȣ"));
		pan_phone.add(jtf_phone1);
		pan_phone.add(new JLabel("-"));
		pan_phone.add(jtf_phone2);
		pan_phone.add(new JLabel("-"));
		pan_phone.add(jtf_phone3);
		pan_email.add(new JLabel("�̸���"));
		pan_email.add(jtf_email1);
		pan_email.add(new JLabel("@"));
		pan_email.add(jtf_email2);
		
		pan_input.add(new JLabel("ȸ������"));
		pan_input.add(pan_id);
		pan_input.add(pan_pwd);
		pan_input.add(pan_pwdcheck);
		pan_input.add(pan_name);
		pan_input.add(pan_address);
		pan_input.add(pan_jumin);
		pan_input.add(pan_phone);
		pan_input.add(pan_email);
		
		JButton btn_signup = new JButton("�����ϱ�");
		
		add(pan_input, BorderLayout.CENTER);
		add(btn_signup, BorderLayout.SOUTH);
		setSize(400,400);
		setVisible(true);
		
		btn_signup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtf_id.getText().equals("") || jtf_pwd.getText().equals("") || 
				jtf_pwdcheck.getText().equals("") || jtf_name.getText().equals("") || 
				jtf_jumin1.getText().equals("") || jtf_jumin2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���.");
					return;
				}
				
				if(!jtf_pwd.getText().equals(jtf_pwdcheck.getText())) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					jtf_pwd.setText("");
					jtf_pwdcheck.setText("");
					return;
				}
				
				if(jtf_jumin1.getText().length()!=6 || jtf_jumin2.getText().length()!=7) {
					JOptionPane.showMessageDialog(null, "��Ȯ�� �ֹι�ȣ�� �Է��ϼ���.");
					return;
				}
				
				UserVO u = new UserVO();
				u.setName(jtf_name.getText());
				u.setId(jtf_id.getText());
				u.setPwd(jtf_pwd.getText());
				u.setJumin(jtf_jumin1.getText()+jtf_jumin2.getText());
				u.setEmail(jtf_email1.getText()+"@"+jtf_email2.getText());
				u.setPhone(jtf_phone1.getText()+jtf_phone2.getText()+jtf_phone3.getText());
				u.setAddress(address[jcb_list.getSelectedIndex()]);
				
				int re = new UserDAO().signup(u);
				if(re==-1) JOptionPane.showMessageDialog(null, "ȸ������ ����");
				else {
					JOptionPane.showMessageDialog(null, "ȸ������ ����");
					u.setNo(new UserDAO().getNO(u));
				}
				re = new UserDAO().makeMiniroom(u);
				if(re==-1) JOptionPane.showMessageDialog(null, "����");
				else JOptionPane.showMessageDialog(null, "����");
			}
		});
	}
}
