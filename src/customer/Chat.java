package customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Chat extends JFrame {
	JTextField jtf;
	JTextArea jta;
	Socket socket;
	DataInputStream is;
	DataOutputStream os;
	JFrame f;
	
	public Chat() {		
		f = this;
		jtf = new JTextField();
		jta = new JTextArea();
		jta.setEditable(false);
		JScrollPane jsp = new JScrollPane(jta);
		jta.setEditable(false);
		JLabel label = new JLabel("1:1 ä�û��");
		label.setFont(new Font("�������", getFont().BOLD, 15));
		JPanel p  = new JPanel();
		p.setLayout(new BorderLayout());
		JButton btn_send = new JButton("����");
		JButton btn_exit = new JButton("����");
		
		JPanel p_btn = new JPanel();
		p_btn.add(btn_send);
	
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.setBackground(new Color(221,234,255));
		p2.add(label, BorderLayout.CENTER);
		p2.add(btn_exit, BorderLayout.EAST);
		
		p.setLayout(new BorderLayout());
		p.add(jtf, BorderLayout.CENTER);
		p.add(p_btn, BorderLayout.EAST);
		
		add(p2, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		
		//���� ����
		try {
			socket = new Socket("localhost",9003);
			jta.append("***���簡 ����Ǿ����ϴ�***\n");
			
			is = new DataInputStream(socket.getInputStream());
			os = new DataOutputStream( socket.getOutputStream());			
		}catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
		setTitle("�Ϻ�Ÿ�� �ǽð� ä�û�㼾��");
		setSize(450, 600);
		setVisible(true);
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		btn_send.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = jtf.getText();				
				try {
					os.writeUTF(msg);
					os.flush();
					jta.append("���� :" +msg+"\n");
					jtf.setText("");
					if(msg.equals("����")) {
						jta.append("***ä�ù��� �����ϴ�.***");
					}
				}catch (Exception ex) {
					System.out.println("���ܹ߻�:"+ex.getMessage());
				}
				
			}
		});
		
		//������ ���� ���ŵǴ� ������ �ޱ� 
		class ClientThread extends Thread{
		
			public void run() {
				while(true) {
					try {
						String msg = is.readUTF();
						jta.append("�Ϻ�Ÿ�� ä�û�㼾�� :"+msg+"\n");
					}catch (Exception e) {
						System.out.println("���ܹ߻�:"+e.getMessage());
					}
				}
			}
		}
		
		new ClientThread().start();
		
	}
}
