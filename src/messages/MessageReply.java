package messages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.Subject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.MessageDAO;
import first.LogInPage;
import vo.MesssageVO;

public class MessageReply extends MessageFrame{
	private int s_no;
	MessageReply(MesssageVO m){
		this.s_no=s_no;
		// 받는 사람 설정
		jlb_recp.setText(jlb_recp.getText()+m.getSender_name());
		// 화면 설정
		add(jlb_recp);
		add(jsp);
		add(btn_send);
		add(btn_exit);
	}
	// btn_send에 적용될 actionlistener 생성
	@Override
	public void actionPerformed(ActionEvent e) {
			MesssageVO m = new MesssageVO();
			m.setContent(jtf_content.getText());
			m.setSender_no(LogInPage.getNO());
			m.setRecipient_no(s_no);
			int re = new MessageDAO().sendMessages(m);
			if(re==-1) JOptionPane.showMessageDialog(null, "쪽지 전송이 실패했습니다.");
			else {
				JOptionPane.showMessageDialog(null, "쪽지 전송에 성공했습니다.");
				dispose();
		}		
	}
}
