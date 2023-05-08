package messages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.FriendDAO;
import dao.MessageDAO;
import dao.UserDAO;
import first.LogInPage;
import utillist.RoundedButton;
import vo.MesssageVO;

public class Message extends JPanel implements MouseListener {
	private Vector<Vector<String>> receive_rowData;
	private Vector<String> receive_colName;
	private static JTable receive_table;
	private Vector<Vector<String>> send_rowData;
	private Vector<String> send_colName;
	private static JTable send_table;
	private static ArrayList<MesssageVO> list;
	private static JCheckBox jcb_check;
	private JTabbedPane jtp;
	public Message() {
		// �������� ������ ���� checkbox ����
		jcb_check = new JCheckBox("���� ���� ������ ����");
		jcb_check.setBounds(590, 100, 170, 20);
		
		// ���� ������ ���̺� ����
		receive_colName = new Vector<String>();
		receive_colName.add("���� ���");
		receive_colName.add("����");
		receive_colName.add("���� �ð�");
		receive_colName.add("���� ����");
		receive_rowData = new Vector<Vector<String>>();
		receive_table = new JTable(receive_rowData, receive_colName);
		JScrollPane jsp_receive = new JScrollPane(receive_table);
		loadMessages("receive", receive_colName, receive_rowData, receive_table);
		jsp_receive.setBounds(20, 100, 750, 400);
		
		// ���� ������ ����
		send_colName = new Vector<String>();
		send_colName.add("����");
		send_colName.add("���� �ð�");
		send_colName.add("���� ����");
		send_rowData = new Vector<Vector<String>>();
		send_table = new JTable(send_rowData, send_colName);
		JScrollPane jsp_send = new JScrollPane(send_table);
		loadMessages("send", send_colName, send_rowData, send_table);
		jsp_receive.setBounds(20, 100, 750, 400);
		
		
		
		// ������ ��ư ����
		JButton btn_new = new RoundedButton("�� ����");
		btn_new.setBounds(360, 505, 100,40);
		
		
		// ���� �� ���� 
		JLabel jlb_messages = new JLabel("������");
		jlb_messages.setFont(new Font("", Font.BOLD, 30));
		jlb_messages.setBounds(370, 20, 100, 80);
		
		jtp = new JTabbedPane();
		jtp.addTab("���� ������", jsp_receive);
		jtp.addTab("���� ������", jsp_send);
		jtp.setBounds(20, 100, 750, 400);
		// Message panel ȯ�� ����
		setLayout(null);
		add(jcb_check);
		add(btn_new);
		add(jlb_messages);
		add(jtp);
		setBackground(new Color(206, 212,192));
		
		
		// ���� table ���콺 ������ ����
		receive_table.addMouseListener(this);
		send_table.addMouseListener(this);
		
		// ������ ���� üũ�ڽ� actionlistener ����
		jcb_check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jtp.getSelectedIndex()==0) {
					loadMessages("receive", receive_colName, receive_rowData, receive_table);
				} else if(jtp.getSelectedIndex()==1) {
					loadMessages("send", send_colName, send_rowData, send_table);
				}
			}
		});
		btn_new.addActionListener(e->new MessageNew());
	}
	
	// ���� ����Ʈ�� �ҷ����� �޼���
	public static void loadMessages(String type,Vector<String> colNames, Vector<Vector<String>> rowData, JTable table) {
		rowData.clear();
		// jcb_check�� ���� 
		if(type.equals("receive")) {
			list = new MessageDAO().viewReceivedMessages(LogInPage.getNO());
		} else {
			list = new MessageDAO().viewSentMessages(LogInPage.getNO());
		}
		for(MesssageVO m:list) {
			if(jcb_check.isSelected() && m.getState().equals("Y")) continue;
			Vector<String> v = new Vector<String>();
			if(type.equals("receive")) {
				v.add(m.getSender_name());
			} 
			v.add(m.getContent());
			v.add(String.valueOf(m.getDate_sent()));
			v.add(m.getState().equals("Y")?"����":"������");
			rowData.add(v);
		}
		table.updateUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int n = jtp.getSelectedIndex();
		if(n==0) {
			list = new MessageDAO().viewReceivedMessages(LogInPage.getNO());
			list = new MessageDAO().viewReceivedMessages(LogInPage.getNO());
			int row = receive_table.getSelectedRow();
			MesssageVO m = list.get(row);
			if(new MessageDAO().changeState(m)==1) {
				loadMessages("receive", receive_colName, receive_rowData, receive_table);
				try {
					Thread.sleep(500);
				} catch (Exception e2) {}
			}
			new MessageDetail("receive",m);		
		} else if(n==1) {
			list = new MessageDAO().viewSentMessages(LogInPage.getNO());
			int row = send_table.getSelectedRow();
			MesssageVO m = list.get(row);
			new MessageDetail("send",m);	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
