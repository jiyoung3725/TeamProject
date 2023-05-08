package home;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import dao.MiniroomDAO;
import dao.UserDAO;
import first.LogInPage;

// avatar�� �������� ���� ������ Ŭ���� �ۼ�
public class MyAvatar extends Thread implements MouseMotionListener{
	private static int x, y;
	private Image img = null;
	
	// �ƹ�Ÿ ����
	public MyAvatar(String gender) {
		int[] arr = new MiniroomDAO().getAvatar(LogInPage.getNO());
		x = arr[0]; y = arr[1];
		try {
			img = new ImageIcon(gender).getImage();
		} catch (Exception e) {System.out.println(" MyAvatar ���ܹ߻� : "+e.getMessage());}
	}
	
	// ���콺�� �ƹ�Ÿ�� �巡���ϸ� �� ��ġ�� �̵�
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		x = Math.max(0, x);	// �ƹ�Ÿ�� �̴Ϸ� ������ ����� �ʰ� ����
	    y = Math.max(0, y);
	    x = Math.min(570, x);
	    y = Math.min(210, y);
		new MiniroomDAO().saveAvatar(x, y, LogInPage.getNO());
	}
	
	public void mouseMoved(MouseEvent e) {}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, 50,90, null);
	}
}