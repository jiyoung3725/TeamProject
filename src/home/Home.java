package home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dao.MiniroomDAO;
import first.LogInPage;
import utillist.RoundedButton;


public class Home extends JPanel {
	private static JLabel jlb_title;
	private static JTextArea jta_home_content;	// �ձ� �𼭸��� ���� utillist�� roundedbutton ���
	private static JLabel home_image;
	private static int likes;
	public Home(int NO) {
		// ���� ���� (MiniroomDAO�� getMTitle() ȣ��)
		jlb_title = new JLabel();
		jlb_title=new JLabel("     "+new MiniroomDAO().getMTitle(NO));
		jlb_title.setBackground(Color.white);
		jlb_title.setOpaque(true);
		jlb_title.setFont(new Font("", Font.PLAIN, 15));
		jlb_title.setBounds(0, 0, 800, 30);
		
		// �̴�Ȩ�� ����
		JPanel miniroom = new Miniroom(NO);
		miniroom.setBounds(150, 250, 620, 300);
		
		// ģ������Ʈ, �α�� ����Ʈ ����
		JPanel panel = new JPanel(new GridLayout(1,2));
		JTabbedPane jtp_board = new BoardTab();
		JTabbedPane jtp_friend = new FriendTab();
		jtp_board.setPreferredSize(new Dimension(300, 190));
		jtp_friend.setPreferredSize(new Dimension(300, 190));
		panel.add(jtp_board);
		panel.add(jtp_friend);
		panel.setBounds(150, 35, 620, 210);
		
		// �Ұ��� ���� ( MiniroomDAO�� getMcontent ȣ�� )
		jta_home_content = new JTextArea(new MiniroomDAO().getMcontent(NO));
		jta_home_content.setBounds(15,235,120,200);
		JButton home_content = new RoundedButton();
		home_content.setBounds(10, 230, 130, 210);
		home_content.setEnabled(false);
		home_content.setBackground(Color.white);
		home_content.setVerticalAlignment(SwingConstants.TOP);
		
		// �Ұ��� ���� ( MiniroomDAO�� getMImg ȣ�� )
		home_image = new JLabel(new ImageIcon(new MiniroomDAO().getMImg(NO)));
		home_image.setBounds(10, 50, 130, 180);
		home_image.setBackground(Color.white);
		
		// ���ƿ� ��ư ���� ( MiniroomDAO�� getMLikes ȣ�� )
		likes = new MiniroomDAO().getMLikes(NO);
		RoundedButton home_likes = new RoundedButton("�� "+likes);
		home_likes.setBounds(10, 470, 130, 60);
		home_likes.setBackground(Color.white);
		home_likes.setForeground(Color.red);
		
		
		// ���ƿ� ��ư actionlistener
		home_likes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int re = new MiniroomDAO().updateMLikes(NO); // ���ƿ� ���� +1 ���ִ� update sql ����
				if(re==-1) System.out.println("home_likes ���� �߻�");
				else {
					Home.likes = new MiniroomDAO().getMLikes(NO);
					home_likes.setText("�� "+Home.likes);
					home_likes.updateUI();	// ������ ���ƿ� �� ������Ʈ
				}
				
			}
		});
		
		// ȯ�� ����
		setLayout(null);
		add(jlb_title);
		add(jta_home_content);
		add(home_content);
		add(home_image);
		add(home_likes);
		add(miniroom);
		// ģ���� ������ �� �ִ� panel�� �湮���� ��� ����ó��
		if(LogInPage.getNO()==NO) {
			add(panel);
		}
		setBackground(new Color(206,212,192));
	
	}
	public static void setJlb_title(String title) {
		Home.jlb_title.setText(title);
	}
	public static void setHome_content(String content) {
		Home.jta_home_content.setText(content);
	}
	public static void setHome_image(String img) {
		Home.home_image.setIcon(new ImageIcon(img));
	}
}

