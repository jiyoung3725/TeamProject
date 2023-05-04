package home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.IntPredicate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.FriendDAO;
import dao.UserDAO;
import first.LogInPage;
import oracle.net.aso.c;
import oracle.net.aso.j;
import vo.UserVO;


public class Home extends JPanel {
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	public Home() {
		// 제목 설정
		MainPage.jlb_title=new JLabel(new UserDAO().getTitle(LogInPage.NO));
		MainPage.jlb_title.setBackground(Color.white);
		MainPage.jlb_title.setOpaque(true);
		MainPage.jlb_title.setFont(new Font("던파 비트비트체 OTF 보통", Font.PLAIN, 15));
		
		colNames = new Vector<String>();
		colNames.add("친구 목록");
		rowData = new Vector<Vector<String>>();
		ArrayList<String> list = new FriendDAO().getFriendlist(LogInPage.NO);
		for(String s:list) {
			Vector<String> v = new Vector<String>();
			v.add(s);
			rowData.add(v);
		}
		JPanel pan_friend = new JPanel(new FlowLayout());
		JPanel pan_board = new JPanel();
		JTable jtb_friend = new JTable(rowData, colNames);
		JScrollPane jsp_friend = new JScrollPane(jtb_friend);
		JButton btn_friend = new JButton("친구 관리");
		pan_friend.add(jsp_friend);
		pan_friend.add(btn_friend);
		
		
		// 화면 설정
		setLayout(new BorderLayout());
		add(pan_friend, BorderLayout.EAST);
		add(MainPage.jlb_title, BorderLayout.NORTH);
		add(new Miniroom(), BorderLayout.CENTER);
		setBackground(new Color(206,212,192));
		
		btn_friend.addActionListener(e->new FriendSetting());
		jtb_friend.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtb_friend.getSelectedRow()+1;
				TableModel model = jtb_friend.getModel();
				String str = (String)model.getValueAt(row, 0);
				String ID = str.substring(str.indexOf("(")+1, str.length()-1);
				UserVO u = new FriendDAO().getFriendDetail(new FriendDAO().getFriendNo(ID));
				
			}
		});
	}


}

// 아바타 action 삽입을 위해 Home Panel 안에 Miniroom Panel 삽입
class Miniroom extends JPanel {
	Image homeimage;
	MyAvatar avatar;
	
	public Miniroom() {
		// 여성이면 여성 캐릭터, 남성이면 남성 캐릭터 삽입
		String gender = new UserDAO().getGender(LogInPage.NO).trim();
		if(gender.equals("여")) {gender="girl.png";} 
		else if(gender.equals("남")){gender="boy.png";}
		avatar = new MyAvatar(gender);
		
		// avatar의 움직임을 위해 avatar 클래스의 마우스리스너 삽입
		addMouseMotionListener(avatar);

		
		// avatar의 움직임을 위한 쓰레드 구현
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
		
		// 화면 설정
		setSize(750,450);
		setBackground(new Color(206,212,192));
		homeimage = new ImageIcon("image.gif").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
	} 

	// image를 그려주는 paint 메서드 
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(homeimage, 0, 0, null);
		avatar.draw(g);
	}
}

// avatar의 움직임을 위해 별도로 클래스 작성
class MyAvatar extends Thread implements MouseMotionListener{
	static int x, y;
	Image img = null;
	
	public MyAvatar(String name) {
		int[] arr = new UserDAO().getAvatar(LogInPage.NO);
		x = arr[0]; y = arr[1];
		try {
			img = new ImageIcon(name).getImage();
		} catch (Exception e) {System.out.println(" MyAvatar 예외발생 : "+e.getMessage());}
	}
	// 마우스로 아바타를 드래그하면 그 위치로 이동
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		new UserDAO().saveAvatar(x, y, LogInPage.NO);
	}
	public void mouseMoved(MouseEvent e) {}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, 50,90, null);
	}
	
	
}