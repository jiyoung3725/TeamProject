package boards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application extends JFrame implements ActionListener {
    JLabel title, content;
    JTextArea contentTextArea;
    JButton applyButton;

    public Application() {

        Container c = getContentPane();
        c.setLayout(null);

        // 타이틀 레이블
        title = new JLabel("신청하기");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        title.setBounds(20, 20, 120, 30);
        c.add(title);

        // 신청내용 레이블과 필드
        content = new JLabel("신청내용");
        content.setFont(new Font("맑은 고딕", Font.TRUETYPE_FONT, 12));
        content.setBounds(20, 70, 50, 30);
        c.add(content);

        contentTextArea = new JTextArea();
        contentTextArea.setBounds(20, 100, 440, 150);
        c.add(contentTextArea);

        // 신청하기 버튼
        applyButton = new JButton("신청하기");
        applyButton.setBounds(200, 280, 100, 30);
        c.add(applyButton);
        
        setTitle("신청하기");
        setSize(500, 380);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        applyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = contentTextArea.getText();

	            JOptionPane.showMessageDialog(null, "신청이 완료되었습니다.");
	            dispose();  // 폼을 닫습니다.
			}
		});
    }
	
	public static void main(String[] args) {
		new Application();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}




}
;