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
// 상세 메세지를 보여주는 jframe
public class MessageDetail extends MessageFrame {
	private MesssageVO m;
	public MessageDetail(String type, MesssageVO m) {
		this.m=m;
		// 쪽지 내용 담을 textarea 생성
		jta_content.setText(m.getContent());

		// 보낸 쪽지함인지, 받은 쪽지함인지에 따라 다른 String 설정
		String tmp = type.equals("send")?"받은 사람 : ":"보낸 사람 : ";
		
		// 화면설정
		add(new JLabel(tmp+m.getSender_name()));
		add(new JScrollPane(jta_content));
		add(btn_delete);
		add(btn_reply);
		
		btn_reply.addActionListener(this);
		btn_delete.addActionListener(this);
	}
	// 각 버튼 actionlistenr 삽입
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_reply) new MessageReply(m);
		if(e.getSource()==btn_delete) {
			int re = new MessageDAO().deleteMessage(m.getM_no()); 
			if(re==-1) {
				JOptionPane.showMessageDialog(null, "쪽지 삭제 실패");
			} else {
				JOptionPane.showMessageDialog(null, "쪽지 삭제 성공");
				dispose();
			}
		}
	}
}
