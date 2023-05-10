package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import customer.Chat;
import customer.ChatHandler;
import customer.Frequent_QnA;
import customer.Qna_List;
import first.LogInPage;



public class MainTest extends JPanel{
	//<�� ����ȭ��>
	JTextArea jta_content;
	JButton btn_chat;
	JTabbedPane pane;
	JTabbedPane inner_pane; //������ �� 2���� �̳����� �����ϴ� ��1 
	Frequent_QnA tab;		//������ �� ��2
	Qna_List tab02;			//�̳������� ���� Ŭ����2
	
	public MainTest() {
		JPanel p1 = new JPanel();
		JLabel label = new JLabel("�Ϻ�Ÿ�� ������");
		label.setFont(new Font("�������",getFont().BOLD,20));
		
		btn_chat = new JButton("ä�� ����ϱ�");
		jta_content = new JTextArea();
	
	    pane = new JTabbedPane();
		inner_pane = new JTabbedPane();
		tab02 = new Qna_List();
		tab = new Frequent_QnA();
		
		JPanel p = new JPanel();
		p.setBackground(new Color(221,234,255));
		p.setLayout(new BorderLayout());
		p.add(label, BorderLayout.CENTER);
		p.add(btn_chat,BorderLayout.EAST);
		add(p, BorderLayout.NORTH);
		
		pane.addTab("1:1����", inner_pane);
		pane.addTab("���ֹ��� ����", tab);
		inner_pane.addTab("���ǳ���", tab02);
		inner_pane.setPreferredSize(new Dimension(680,480));
		tab.setPreferredSize(new Dimension(680,480));
		tab02.setPreferredSize(new Dimension(680,480));
		
		add(pane, BorderLayout.CENTER);
		
		setSize(700,600);
		setVisible(true);
		
		
	
		
		//<���Ǳ� ��� �� ���ǳ����� ������ �� ��ϵ� ���� �߰��� ȭ���� �����ִ� �̺�Ʈ ������>
		inner_pane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int row = inner_pane.getSelectedIndex();
				if(row==0) {
				
				}else {
					
					tab02.loadData();
				}
			}
		});
		//<�ǽð� ä�ù�ư�� ������ ä��â ����Ǵ� �̺�Ʈ������>
		btn_chat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int no = LogInPage.getNO();
		            if(no==0) {
		               ChatHandler server = new ChatHandler();
		            }
		            else {
		            Chat chat = new Chat();
		            }
			}
		});
	}
}

