package first;

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
import dao.UserDAO;
import home.Home;
import home.Setting;
import messages.Message;
import utillist.RoundedButton;
import vo.UserVO;


public class MainPage extends JFrame implements ActionListener{
	private RoundedButton[] btn_arr;
	private JPanel pan_main;
	private CardLayout cadrdlayout = new CardLayout();
	
	public MainPage() {
		// ���� ȭ�� �� �� �Խ��� Panel ���� (CardLayout�� �̿��� ȭ�� ��ȯ)
		pan_main = new JPanel(cadrdlayout);
		JPanel pan_home = new Home(LogInPage.getNO());	// JPanel�� ����� Home Ŭ���� ȣ��
		JPanel pan_board = new JPanel();
		JPanel pan_memssage = new Message();	// JPanel�� ����� Memo Ŭ���� ȣ��
		JPanel pan_game = new JPanel();
		JPanel pan_cs = new JPanel();
		JPanel pan_setting = new Setting();	// JPanel�� ����� Setting Ŭ���� ȣ��
		
		pan_main.add("Ȩ", pan_home);
		pan_main.add("������", pan_memssage);
		pan_main.add("����",pan_game);
		pan_main.add("������",pan_cs);
		pan_main.add("����", pan_setting);
		pan_main.setBackground(new Color(206,212,192));
		
		// �⺻���� �������� ȭ���� Ȩ Panel
		cadrdlayout.show(pan_main, "Ȩ");
		
		// �迭�� ����� ���� �޴� ��ư ����
		JPanel pan_btn = new JPanel(new GridLayout(7, 1));
		String[] btn_name = {"Ȩ", "Ŀ�´�Ƽ", "������", "����", "������", "����", "�α׾ƿ�"};
		btn_arr =new RoundedButton[7];
		for(int i=0; i<7; i++) {
			btn_arr[i] = new RoundedButton(btn_name[i]);
			btn_arr[i].setFont(new Font("HY������", Font.BOLD, 15));
			btn_arr[i].addActionListener(this);
			pan_btn.add(btn_arr[i]);
		}
		pan_btn.setBackground(new Color(206,212,192));
		
		// ȭ�� ����
		add(pan_main, BorderLayout.CENTER);
		add(pan_btn, BorderLayout.EAST);
		setVisible(true);
		setSize(900,600);
		setBackground(new Color(206,212,192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// ��ư �׼� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_arr[6]) {	// �α׾ƿ� ��ư ������ �� �α��� �������� ���ư���
			dispose();
			new LogInPage();
		} else if(e.getSource()==btn_arr[1]) {	// Ŀ�´�Ƽ ��ư ������ �� board frame ȣ��
			new Board();	
		}
		
		// ī�� ���̾ƿ��� ����� ȭ�� ��ȯ
		cadrdlayout.show(pan_main, e.getActionCommand());
	}
	
	
	


}
