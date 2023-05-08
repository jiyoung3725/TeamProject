package home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.MiniroomDAO;
import dao.UserDAO;
import first.LogInPage;
import oracle.net.aso.j;
import utillist.Utils;
import vo.UserVO;


public class Setting extends JPanel implements ActionListener, KeyListener {
	JTextArea jta_title;
	JTextArea jta_content;
	JFileChooser jfc_img;
	JComboBox<String> jcb_thema;
	String[] thema_arr = {"default", "a","b","c"};
	JPasswordField jpf_pwd;
	JPasswordField jpf_pwd_check;
	JLabel jlb_pwdCheck;
	JTextField jtf_email1;
	JTextField jtf_email2;
	JComboBox<String> jcb_interest;
	JTextField jtf_phone1;
	JTextField jtf_phone2;
	JTextField jtf_phone3;
	JComboBox<String> jcb_address;
	JButton btn_open;
	JButton[] btn_change;
	
	public Setting() {
		// ��ư �迭 ����
		btn_change = new JButton[7];
		for(int i=0; i<btn_change.length; i++) {
			btn_change[i] = new JButton("����");
			btn_change[i].addActionListener(this);
		}
		// Ȩȭ�� ���� ������Ʈ ����
		jta_title = new JTextArea(1,20);
		jta_content = new JTextArea(10, 20);
		jfc_img = new JFileChooser();
		jcb_thema = new JComboBox<String>(thema_arr);
		btn_open = new JButton("���� ã��");
		btn_open.addActionListener(this);
		jfc_img.setFileFilter(new FileNameExtensionFilter("�̹��� ����", "jpg", "jpeg", "png", "gif"));
		// Ȩȭ�� ���� panel ����
		JPanel setting_home = new JPanel();
		setting_home.setLayout(new BoxLayout(setting_home, BoxLayout.Y_AXIS));
		JPanel pan_title = new JPanel(new FlowLayout());
		pan_title.add(new JLabel("���� ���� : "));
		pan_title.add(new JScrollPane(jta_title));
		pan_title.add(btn_change[0]);
		pan_title.setOpaque(false);
		jta_title.setBackground(Color.white);
		JPanel pan_content = new JPanel(new FlowLayout());
		pan_content.add(new JLabel("�Ұ��� ���� : "));
		pan_content.add(new JScrollPane(jta_content,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		pan_content.add(btn_change[1]);
		pan_content.setOpaque(false);
		jta_content.setBackground(Color.white);
		jta_content.setLineWrap(true);
		JPanel pan_img = new JPanel(new FlowLayout());
		pan_img.add(new JLabel("�Ұ� �̹��� ���� : "));
		pan_img.add(btn_open);
		pan_img.setOpaque(false);
		
		JLabel jlb_title_home = new JLabel("Ȩ ȭ�� ����");
		jlb_title_home.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlb_title_home.setFont(new Font("", Font.BOLD, 15));
		setting_home.add(jlb_title_home);
		setting_home.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_home.add(pan_title);
		setting_home.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_home.add(pan_content);
		setting_home.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_home.add(pan_img);
		setting_home.add(Box.createRigidArea(new Dimension(0, 30)));
		setting_home.setBackground(Color.LIGHT_GRAY);
		setting_home.setPreferredSize(new Dimension(385, 545));

		// �������� ���� panel ����
		UserVO u =  new UserDAO().getUser(LogInPage.getNO());
		jpf_pwd = new JPasswordField(10);
		jpf_pwd_check = new JPasswordField(10);
		jlb_pwdCheck = new JLabel();
		jlb_pwdCheck.setForeground(Color.red);
		jpf_pwd.addKeyListener(this);
		jpf_pwd_check.addKeyListener(this);
		jcb_interest = Utils.getInterestList();
		jcb_interest.setSelectedIndex(Utils.getInterestIndex(u.getInterest()));
		String[] phone = u.getPhone().split("-");
		jtf_phone1 = new JTextField(phone[0]);
		jtf_phone2 = new JTextField(phone[1]);
		jtf_phone3 = new JTextField(phone[2]);
		String[] email = u.getEmail().split("@");
		jtf_email1 = new JTextField(email[0]);
		jtf_email2 = new JTextField(email[1]);
		jcb_address = Utils.getAddrList();
		jcb_address.setSelectedIndex(Utils.getAddrIndex(u.getAddress()));
		
		JPanel setting_personal = new JPanel();
		setting_personal.setLayout(new BoxLayout(setting_personal, BoxLayout.Y_AXIS));
		JPanel pan_pwd = new JPanel(new FlowLayout());
		pan_pwd.add(new JLabel("��й�ȣ ���� : "));
		pan_pwd.add(jpf_pwd);
		pan_pwd.setOpaque(false);
		jpf_pwd.setBackground(Color.WHITE);
		JPanel pan_pwd_check = new JPanel(new FlowLayout());
		pan_pwd_check.add(new JLabel("��к�ȣ ���� Ȯ�� : "));
		pan_pwd_check.add(jpf_pwd_check);
		pan_pwd_check.add(btn_change[2]);
		pan_pwd_check.setOpaque(false);
		jpf_pwd_check.setBackground(Color.WHITE);
		JPanel pan_email = new JPanel(new FlowLayout());
		pan_email.add(new JLabel("�̸��� ���� : "));
		pan_email.add(jtf_email1);
		pan_email.add(new JLabel("@"));
		pan_email.add(jtf_email2);
		pan_email.add(btn_change[3]);
		pan_email.setOpaque(false);
		JPanel pan_interest = new JPanel(new FlowLayout());
		pan_interest.add(new JLabel("���ɻ� ���� : "));
		pan_interest.add(jcb_interest);
		pan_interest.add(btn_change[4]);
		pan_interest.setOpaque(false);
		JPanel pan_phone = new JPanel(new FlowLayout());
		pan_phone.add(new JLabel("��ȭ��ȣ ���� : "));
		pan_phone.add(jtf_phone1);
		pan_phone.add(new JLabel("-"));
		pan_phone.add(jtf_phone2);
		pan_phone.add(new JLabel("-"));
		pan_phone.add(jtf_phone3);
		pan_phone.add(btn_change[5]);
		pan_phone.setOpaque(false);
		JPanel pan_addr = new JPanel(new FlowLayout());
		pan_addr.add(new JLabel("�ּ� ���� : "));
		pan_addr.add(jcb_address);
		pan_addr.add(btn_change[6]);
		pan_addr.setOpaque(false);
		JLabel jlb_title_personal = new JLabel("���� ȭ�� ����");
		jlb_title_personal.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlb_title_personal.setFont(new Font("", Font.BOLD, 15));
		JLabel jlb_name = new JLabel("�̸� : "+ u.getName());
		jlb_name.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel jlb_id = new JLabel("���̵� : "+ u.getId());
		jlb_id.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel jlb_date = new JLabel("���� ���� : "+ u.getUpdate());
		jlb_date.setAlignmentX(Component.CENTER_ALIGNMENT);
		setting_personal.add(jlb_title_personal);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(jlb_name);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(jlb_id);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(jlb_date);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(pan_pwd);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(pan_pwd_check);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(jlb_pwdCheck);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(pan_email);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(pan_interest);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(pan_phone);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.add(pan_addr);
		setting_personal.add(Box.createRigidArea(new Dimension(0, 10)));
		setting_personal.setBackground(Color.lightGray);
		setting_personal.setPreferredSize(new Dimension(385, 545));
		
		JPanel pan_setting = new JPanel(new FlowLayout());
		pan_setting.add(setting_home);
		pan_setting.add(setting_personal);
		add(pan_setting);
		setVisible(true);
		setSize(300,400);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_change[0]) {	// ���� ���� ��ư
			String title = jta_title.getText();
			int re = new MiniroomDAO().saveTitle(title, LogInPage.getNO());
			if(re!=1) {
				JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.");
				return;
			}
			JOptionPane.showMessageDialog(null, "������ �����Ǿ����ϴ�.");
			Home.setJlb_title(title);
		} else if(e.getSource()==btn_change[1]) {	// �Ұ��� ���� ��ư
			String content = jta_content.getText();
			int re = new MiniroomDAO().saveContent(content, LogInPage.getNO());
			if(re!=1) {
				JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.");
				return;
			}
			JOptionPane.showMessageDialog(null, "�Ұ����� �����Ǿ����ϴ�.");
			Home.setHome_content(content);
		} else if (e.getSource()==btn_change[2]) {	// ��й�ȣ üũ
			String PWD =jpf_pwd.getText();
			if(!jpf_pwd_check.getText().equals(PWD)) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�. \n �ٽ� �Է����ּ���.");
				jpf_pwd.setText("");
				jpf_pwd_check.setText("");
				return;
			}
			int re = new UserDAO().updatePWD(PWD, LogInPage.getNO());
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "��й�ȣ ���濡 �����߽��ϴ�.");
				return;
			} 
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �����߽��ϴ�.");
		} else if (e.getSource()==btn_change[3]) {	// �̸��� ����
			String email =jtf_email1.getText()+"@"+jtf_email2.getText();
			int re = new UserDAO().updateEmail(email, LogInPage.getNO());
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "�̸��� ���濡 �����߽��ϴ�.");
				return;
			} 
			JOptionPane.showMessageDialog(null, "�̸����� �����߽��ϴ�.");
		} else if (e.getSource()==btn_change[4]) {	// ���ɻ� ����
			String interest = Utils.interest[jcb_interest.getSelectedIndex()];
			int re = new UserDAO().updateInterest(interest, LogInPage.getNO());
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "���ɻ� ���濡 �����߽��ϴ�.");
				return;
			} 
			JOptionPane.showMessageDialog(null, "���ɻ縦 �����߽��ϴ�.");
		} else if (e.getSource()==btn_change[5]) {	// ��ȣ ����
			String phone =jtf_phone1.getText()+"-"+jtf_phone2.getText()+"-"+jtf_phone3.getText();
			int re = new UserDAO().updatePhone(phone, LogInPage.getNO());
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ ���濡 �����߽��ϴ�.");
				return;
			} 
			JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �����߽��ϴ�.");
		} else if (e.getSource()==btn_change[6]) {	// �ּ� ����
			String addr = Utils.address[jcb_address.getSelectedIndex()];
			int re = new UserDAO().updateAddr(addr, LogInPage.getNO());
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "�ּ� ���濡 �����߽��ϴ�.");
				return;
			} 
			JOptionPane.showMessageDialog(null, "�ּҸ� �����߽��ϴ�.");
		} else if (e.getSource()==btn_open) {	// Ȩ �̹��� ���� ��ư
			if(jfc_img.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = jfc_img.getSelectedFile();
				String img = file.getPath();
				int re = new MiniroomDAO().saveMImg(img, LogInPage.getNO());
				if(re==-1) {
					JOptionPane.showMessageDialog(null, "�̹��� ���濡 �����߽��ϴ�.");
					return;
				}
				JOptionPane.showMessageDialog(null, "�̹����� �����Ǿ����ϴ�.");
				Home.setHome_image(img);
			}
		} 
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		String tmp = jpf_pwd.getText();
		if(tmp.equals(jpf_pwd_check.getText())) {
			jlb_pwdCheck.setText("��й�ȣ�� ��ġ�մϴ�.");
		} else {
			jlb_pwdCheck.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
	}
	

}
