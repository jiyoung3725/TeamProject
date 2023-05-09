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
	InputStream is;
	OutputStream os;
	JFrame f;
	
	public Chat() {		
		f = this;
		jtf = new JTextField();
		jta = new JTextArea();
		jta.setEditable(false);
		JScrollPane jsp = new JScrollPane(jta);
		jta.setEditable(false);
		JLabel label = new JLabel("1:1 채팅상담");
		label.setFont(new Font("맑은고딕", getFont().BOLD, 15));
		JPanel p  = new JPanel();
		p.setLayout(new BorderLayout());
		JButton btn_send = new JButton("전송");
		JButton btn_exit = new JButton("종료");
		
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
		
		//소켓 연결
		try {
			socket = new Socket("localhost",9003);
			jta.append("***상담사가 연결되었습니다***\n");
			
			is = socket.getInputStream();
			os = socket.getOutputStream();			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		setTitle("하비타운 실시간 채팅상담센터");
		setSize(450, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		btn_send.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = jtf.getText();				
				byte []data = msg.getBytes();
				try {
					os.write(data);	
					jta.append("유저 :" +msg+"\n");
					jtf.setText("");
					if(msg.equals("종료")) {
						jta.append("***채팅방을 나갑니다.***");
					}
				}catch (Exception ex) {
					System.out.println("예외발생:"+ex.getMessage());
				}
				
			}
		});
		
		//서버로 부터 수신되는 데이터 받기 
		class ClientThread extends Thread{
		
			public void run() {
				byte []data = new byte[100];
				while(true) {
					try {
						is.read(data);
						String msg = new String(data);
						jta.append("하비타운 채팅상담센터 :"+msg+"\n");
						Arrays.fill(data, (byte)0);
					}catch (Exception e) {
						System.out.println("예외발생:"+e.getMessage());
					}
				}
			}
		}
		
		new ClientThread().start();
		
	}
	public static void main(String[] args) {
		new Chat();
	}
}
