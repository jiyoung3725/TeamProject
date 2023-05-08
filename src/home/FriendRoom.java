package home;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messages.MessageFrame;
import messages.MessageNew;
import utillist.RoundedButton;
// ģ�� �̴Ϸ� �湮 ȭ���� ���� ������
public class FriendRoom extends JFrame {
	FriendRoom(int NO){
		Home home= new Home(NO);
		// ģ�� �̴Ϸ� �湮�� ������ �� �ִ� �г� ����
		JPanel panel = new JPanel();
		JLabel jlb = new JLabel(new ImageIcon("froom.png"));	// ģ�� �̴Ϸ� �湮 �� �̹��� ����
		jlb.setBackground(Color.white);
		jlb.setOpaque(false);
		panel.add(jlb);
		panel.setBounds(150, 35, 620, 210);
		home.add(panel);
		
		// ȭ�� ����
		add(home);
		setVisible(true);
		setSize(800, 600);
	
	}
}
