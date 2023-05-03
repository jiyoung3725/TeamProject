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
import dao.MemoDAO;
import first.LogInPage;
import vo.MemoVO;

public class MessageDetail extends JFrame {
	static String sender;
	
	public MessageDetail(MemoVO m) {
		
		sender = m.getSender_name();
		JButton btn_delete = new JButton("삭제");
		JButton btn_reply = new JButton("답장");
		setLayout(new FlowLayout());
		add(new JLabel("보낸 사람 : "+sender));
		JTextArea jta = new JTextArea(10,20);
		jta.setText(m.getContent());
		add(new JScrollPane(jta));
		add(btn_delete);
		add(btn_reply);
		setVisible(true);
		setSize(300,300);
		
		btn_reply.addActionListener(e->new MemoReply());
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int re = new MemoDAO().deleteMessage(m);
				if(re==-1) {
					JOptionPane.showMessageDialog(null, "쪽지 삭제 실패");
				} else JOptionPane.showMessageDialog(null, "쪽지 삭제 성공");
			}
		});
	}
}

class MemoReply extends JFrame{
	JTextArea jtf_content;
	MemoReply(){
		jtf_content = new JTextArea(10, 20);
		JScrollPane jsp = new JScrollPane(jtf_content);
		JButton btn_send = new JButton("전송");
		JButton btn_exit = new JButton("취소");
		add(new JLabel("보낼 사람 : "+MessageDetail.sender));
		add(jsp);
		add(btn_send);
		add(btn_exit);
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(300,300);
		
		btn_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoVO m = new MemoVO();
				m.setContent(jtf_content.getText());
				m.setSender_no(LogInPage.NO);
				m.setRecipient_no(new MemoDAO().getSenderNO(MessageDetail.sender));
				int re = new MemoDAO().sendMessages(m);
				if(re==-1) JOptionPane.showMessageDialog(null, "실패");
				else JOptionPane.showMessageDialog(null, "성공");
			}
		});
	}
}

class MemoNew extends JFrame{
	JTextArea jtf_content;
	MemoNew(){
		jtf_content = new JTextArea(10, 20);
		JScrollPane jsp = new JScrollPane(jtf_content);
		JButton btn_send = new JButton("전송");
		JButton btn_exit = new JButton("취소");
		ArrayList<String> list = new FriendDAO().getFriendlist(LogInPage.NO);
		String[] arr = list.toArray(new String[list.size()]);
		JComboBox<String> jcb_list = new JComboBox<String>(arr);
		add(new JLabel("보낼 사람 : "));
		add(jcb_list);
		add(jsp);
		add(btn_send);
		add(btn_exit);
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(300,300);
		
		btn_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoVO m = new MemoVO();
				m.setContent(jtf_content.getText());
				m.setSender_no(LogInPage.NO);
				m.setRecipient_no(new MemoDAO().getSenderNO(arr[jcb_list.getSelectedIndex()]));
				int re = new MemoDAO().sendMessages(m);
				if(re==-1) JOptionPane.showMessageDialog(null, "실패");
				else JOptionPane.showMessageDialog(null, "성공");
			}
		});
	}
}