package messages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.FriendDAO;
import dao.MessageDAO;
import dao.UserDAO;
import first.LogInPage;
import vo.MesssageVO;
// �� �޼����� �����ִ� jframe
public class MessageDetail extends MessageFrame {
	private MesssageVO m;
	public MessageDetail(String type, MesssageVO m) {
		this.m=m;
		// ���� ���� ���� textarea ����
		jta_content.setText(m.getContent());

		// ���� ����������, ���� ������������ ���� �ٸ� String ����
		String tmp = type.equals("send")?"���� ��� : ":"���� ��� : ";
		
		// ȭ�鼳��
		add(new JLabel(tmp+m.getSender_name()));
		add(new JScrollPane(jta_content));
		add(btn_delete);
		add(btn_reply);
		
		btn_reply.addActionListener(this);
		btn_delete.addActionListener(this);
	}
	// �� ��ư actionlistenr ����
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_reply) new MessageReply(m);
		if(e.getSource()==btn_delete) {
			int re = new MessageDAO().deleteMessage(m.getM_no()); 
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "���� ���� ����");
			} else {
				JOptionPane.showMessageDialog(null, "���� ���� ����");
				dispose();
			}
		}
	}
}
