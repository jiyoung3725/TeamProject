package home;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.FriendDAO;
import first.LogInPage;

public class FriendSetting extends JFrame {
	public FriendSetting() {
		
		JPanel pan_delete = new JPanel();
		ArrayList<String> list = new FriendDAO().getFriendlist(LogInPage.NO);
		String[] arr = list.toArray(new String[list.size()]);
		System.out.println(Arrays.toString(arr));
		JComboBox<String> jcb_list = new JComboBox<String>(arr);
		JButton btn_delete = new JButton("昏力");
		pan_delete.add(new JLabel("模备 昏力"));
		pan_delete.add(jcb_list);
		pan_delete.add(btn_delete);
		
		
		JPanel pan_add = new JPanel();
		pan_add.add(new JLabel("模备 眠啊"));
		
		setVisible(true);
		setSize(400,400);
	}
}
