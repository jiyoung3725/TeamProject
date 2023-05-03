package home;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.UserDAO;
import first.LogInPage;

public class Setting extends JPanel {
	JTextField jtf_title;
	public Setting() {
		jtf_title = new JTextField(10);
		JButton btn_change = new JButton("수정");
		
		
		add(new JLabel("수정할 제목"));
		add(jtf_title);
		add(btn_change);
		btn_change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = jtf_title.getText();
				int re = new UserDAO().saveTitle(title, LogInPage.NO);
				if(re==1) JOptionPane.showMessageDialog(null, "수정되었습니다.");
				else JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
				MainPage.jlb_title.setText(new UserDAO().getTitle(LogInPage.NO));
			}
			
		});
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(400,400);
				
	}
	

}
