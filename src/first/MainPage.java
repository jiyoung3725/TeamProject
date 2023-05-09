package first;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.GameMain;
import boards.Board;
import customer.MainTest;
import home.Home;
import home.Setting;
import messages.Message;
import utillist.RoundedButton;


public class MainPage extends JFrame implements ActionListener{
	private RoundedButton[] btn_arr;
	private JPanel pan_main;
	private CardLayout cadrdlayout = new CardLayout();
	
	public MainPage() {
		// ���� ȭ�� �� �� �Խ��� Panel ���� (CardLayout�� �̿��� ȭ�� ��ȯ)
		pan_main = new JPanel(cadrdlayout);
		JPanel pan_home = new Home(LogInPage.getNO());	// JPanel�� ����� Home Ŭ���� ȣ��
		JPanel pan_board = new Home(LogInPage.getNO());
		JPanel pan_memssage = new Message();	// JPanel�� ����� Memo Ŭ���� ȣ��
		JPanel pan_game = new Home(LogInPage.getNO());
		JPanel pan_cs = new MainTest();
		JPanel pan_setting = new Setting();	// JPanel�� ����� Setting Ŭ���� ȣ��
		
		pan_main.add("Ȩ", pan_home);
//		pan_main.add("Ŀ�´�Ƽ", pan_board);
		pan_main.add("������", pan_memssage);
//		pan_main.add("����",pan_game);
		pan_main.add("������",pan_cs);
		pan_main.add("����", pan_setting);
		
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
		} else if(e.getSource()==btn_arr[3]) {
			new GameMain();
		}
		
		// ī�� ���̾ƿ��� ����� ȭ�� ��ȯ
		cadrdlayout.show(pan_main, e.getActionCommand());
	}
	
	
	


}
