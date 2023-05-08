package home;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dao.MiniroomDAO;
import first.LogInPage;

//�ƹ�Ÿ action ������ ���� Home Panel �ȿ� Miniroom Panel ����
public class Miniroom extends JPanel {
	private Image homeimage;
	private MyAvatar avatar;
	public Miniroom(int NO) { 
		// �����̸� ���� ĳ����, �����̸� ���� ĳ���� ����
		String gender = new MiniroomDAO().getGender(NO).trim();
		if(gender.equals("��")) gender="girl.png";
		else if(gender.equals("��")) gender="boy.png";
		avatar = new MyAvatar(gender);
		
		// avatar�� �������� ���� avatar Ŭ������ ���콺������ ����
		addMouseMotionListener(avatar);
		
		// avatar�� �������� ���� ������ ����
		class MyThread extends Thread{
			public void run() {
				while(true) {
					repaint();
					try { Thread.sleep(100);} 
					catch (InterruptedException e) {}
				}
			}
		}
		new MyThread().start();
		
		// ȭ�� ���� ( Ȩ �̹��� ���� �ʿ� )
		setSize(620,300);
		setBackground(new Color(206,212,192));
		homeimage = new ImageIcon("image.gif").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	} 

	// image�� �׷��ִ� paint �޼��� 
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(homeimage, 0, 0, null);
		avatar.draw(g);
	}
}
