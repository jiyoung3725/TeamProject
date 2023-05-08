package boards;

import javax.swing.*;

import dao.ApplicationDAO;
import vo.BoardVO;

import java.awt.*;
import java.awt.event.*;
//���� ����
public class Application extends JFrame implements ActionListener {
    JLabel title, content;
    JTextArea jta_application;
    JButton applyButton;

    public Application() {

        Container c = getContentPane();
        c.setLayout(null);

        // Ÿ��Ʋ ���̺�
        title = new JLabel("��û�ϱ�");
        title.setFont(new Font("���� ���", Font.BOLD, 30));
        title.setBounds(20, 20, 120, 30);
        c.add(title);

        // ��û���� ���̺�� �ʵ�
        content = new JLabel("��û����");
        content.setFont(new Font("���� ���", Font.TRUETYPE_FONT, 12));
        content.setBounds(20, 70, 50, 30);
        c.add(content);

        jta_application = new JTextArea();
        jta_application.setBounds(20, 100, 440, 150);
        c.add(jta_application);

        // ��û�ϱ� ��ư
        applyButton = new JButton("��û�ϱ�");
        applyButton.setBounds(200, 280, 100, 30);
        c.add(applyButton);
        
        setTitle("��û�ϱ�");
        setSize(500, 380);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        applyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = jta_application.getText();
				BoardVO vo = new BoardVO();
				vo.setAp_content(content);
				ApplicationDAO dao = new ApplicationDAO();
				int re = dao.insertApplication(vo);
	            JOptionPane.showMessageDialog(null, "��û�� �Ϸ�Ǿ����ϴ�.");
	            dispose();  // ���� �ݽ��ϴ�.
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