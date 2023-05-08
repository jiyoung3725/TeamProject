package messages;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class MessageFrame extends JFrame implements ActionListener{
	protected JTextArea jtf_content;
	protected JButton btn_send;
	protected JButton btn_exit;
	protected JLabel jlb_recp;
	protected JPanel pan_recp;
	protected JScrollPane jsp;
	protected JButton btn_delete;
	protected JButton btn_reply;
	protected JTextArea jta_content;
	public MessageFrame() {
		jtf_content = new JTextArea(10, 20);
		jlb_recp = new JLabel("�޴� ��� : ");
		jsp = new JScrollPane(jtf_content);
		btn_send = new JButton("����");
		btn_delete = new JButton("����");
		btn_reply = new JButton("����");
		btn_exit = new JButton("�ݱ�");
		pan_recp = new JPanel();
		jta_content = new JTextArea(10,20);
		
		// ȭ�鼳��
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(250,300);
		
	}
}


