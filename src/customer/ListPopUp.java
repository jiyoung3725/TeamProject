package customer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.sist.dao.QnaDAO;
import com.sist.vo.QnAVo;

public class ListPopUp extends JFrame {
	JTextArea answer;
	JButton btn_add;
	JTextArea jta = new JTextArea(20,47);
	JScrollPane jsp2 = new JScrollPane(jta);
	
	public ListPopUp() {
	
		answer = new JTextArea(5,40);
		JScrollPane jsp = new JScrollPane(answer);
		btn_add = new JButton("등록");
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		p.add(jsp2);
		p2.add(jsp);
		p2.add(btn_add);

		add(p, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		setTitle("답변이 등록되었습니다");
		setSize(500,400);
		setVisible(true);
		
	}


	
	
	public static void main(String[] args) {
		new ListPopUp();
		
		
	}

}
