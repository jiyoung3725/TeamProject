package home;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import dao.FriendDAO;
import dao.UserDAO;
import first.LogInPage;
import utillist.Utils;

public class FriendTab extends JTabbedPane {
	private Vector<String> colNames;
	private Vector<Vector<String>> rowData;
	private JTextField jtf_input_id;
	private ArrayList<String> list;
	private JTable jtb_friend;
	
	public FriendTab() {
		// 1st tab ģ�� ��� ���̺� ����
		colNames = new Vector<String>();
		colNames.add("ģ�� ���");
		rowData = new Vector<Vector<String>>();
		jtb_friend = new JTable(rowData, colNames);
		loadFriendList();
		JScrollPane jsp_friend = new JScrollPane(jtb_friend);
		
		// 2nd tab ��û��� �� ����
		JPanel pan_setting = new JPanel();
		pan_setting.setLayout(new BoxLayout(pan_setting, BoxLayout.Y_AXIS));
		ArrayList<String> w_list = new FriendDAO().getWaitinglist(LogInPage.getNO());
		String[] arr_w = w_list.toArray(new String[w_list.size()]);
		JComboBox<String> jcb_list_w = new JComboBox<String>(arr_w);
		JButton btn_accept = new JButton("����");
		JPanel pan_btn_accept = new JPanel();
		pan_btn_accept.add(btn_accept);
		JLabel jlb_setting = new JLabel("������ ������� ģ�� ����Դϴ�");
		jlb_setting.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan_setting.add(Box.createRigidArea(new Dimension(0, 30)));
		pan_setting.add(jlb_setting);
		pan_setting.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_setting.add(jcb_list_w);
		pan_setting.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_setting.add(pan_btn_accept);
		pan_setting.add(Box.createRigidArea(new Dimension(0, 50)));
		
		// 3rd tab ģ�� �߰� �� ����
		JButton btn_add = new JButton("ģ�� �߰�");
		btn_add.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel pan_add = new JPanel();
		pan_add.setLayout(new BoxLayout(pan_add, BoxLayout.Y_AXIS));
		jtf_input_id = new JTextField(10);
		JLabel jlb_add = new JLabel("ģ���� �߰��� ȸ����  ID�� �Է��ϼ���.");
		jlb_add.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan_add.add(Box.createRigidArea(new Dimension(0, 30)));
		pan_add.add(jlb_add);
		pan_add.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_add.add(jtf_input_id);
		pan_add.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_add.add(btn_add);
		pan_add.add(Box.createRigidArea(new Dimension(0, 50)));
		
		// 4th tab ģ�� ���� �� ����
		JButton btn_delete = new JButton("ģ�� ����");
		JPanel pan_btn_delete = new JPanel();
		pan_btn_delete.add(btn_delete);
		JPanel pan_delete = new JPanel();
		pan_delete.setLayout(new BoxLayout(pan_delete, BoxLayout.Y_AXIS));
		ArrayList<String> f_list = new FriendDAO().getFriendlist(LogInPage.getNO());
		String[] arr = f_list.toArray(new String[f_list.size()]);
		JComboBox<String> jcb_list = new JComboBox<String>(arr);
		JLabel jlb_delete = new JLabel("������ ģ���� �������ּ���.");
		jlb_add.setAlignmentX(Component.CENTER_ALIGNMENT);
		pan_delete.add(Box.createRigidArea(new Dimension(0, 30)));
		pan_delete.add(jlb_delete);
		pan_delete.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_delete.add(jcb_list);
		pan_delete.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_delete.add(pan_btn_delete);
		pan_delete.add(Box.createRigidArea(new Dimension(0, 50)));
		
		// �� �߰�
		addTab("ģ�� ���", jsp_friend);
		addTab("��û ���", pan_setting);
		addTab("ģ�� �߰�", pan_add);
		addTab("ģ�� ����", pan_delete);
		
		// ģ�� ���� ��ư actionlistener ����
		btn_accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ID = Utils.getID(arr_w[jcb_list_w.getSelectedIndex()]);
				String name = new UserDAO().getName(ID);
				if(JOptionPane.showConfirmDialog(null, name+"���� ģ���� �߰��Ͻðڽ��ϱ�?")!=0) return;
				// ģ�� �߰� dao ����
				int re = new FriendDAO().acceptFriend(LogInPage.getNO(), new UserDAO().getNO(ID));
				if(re==-1) {
					JOptionPane.showMessageDialog(null, "������ �߻��Ͽ� �۾��� ������� �ʾҽ��ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "ģ�� �߰��� �����߽��ϴ�.");
					loadFriendList();	// ģ����� ������Ʈ
				}
			 
			}
		});
		// ģ�� ���� ��ư actionlistener
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ID = Utils.getID(arr[jcb_list.getSelectedIndex()]);
				String name = new UserDAO().getName(ID);
				if(JOptionPane.showConfirmDialog(null, name+"���� ģ�� �����Ͻðڽ��ϱ�?")==1) return;
				// ģ�� ���� dao ����
				int re = new FriendDAO().deleteFriend(LogInPage.getNO(), new UserDAO().getNO(ID));
				if(re==-1) {
					JOptionPane.showMessageDialog(null, "������ �߻��Ͽ� �������� �ʾҽ��ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "ģ�� �����Ǿ����ϴ�.");
					loadFriendList();	// ģ�� ��� ������Ʈ
				}
			} 
		});
		
		// ģ�� �߰� ��ư actionlistener
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ID = jtf_input_id.getText();
				if(ID.length()==0) {
					JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���");
					return;
				}
				// ���̵� �˻� dao ���� ( �������� �ʴ� ���̵� -> �޼��� ���� )
				int re = new UserDAO().checkID(ID);
				if(re==0) {
					JOptionPane.showMessageDialog(null, "�������� �ʴ� ID �Դϴ�.");
					jtf_input_id.setText("");
					return;
				}
				String name = new UserDAO().getName(ID);
				int f_no = new UserDAO().getNO(ID);

				if(JOptionPane.showConfirmDialog(null, name+"���� ģ���� �߰��Ͻðڽ��ϱ�?")!=0) return;
				
				// ģ�� �߰� dao ����
				re = new FriendDAO().addFriend(LogInPage.getNO(), f_no);
				if(re==1) {
					JOptionPane.showMessageDialog(null, "ģ�� ��û�� �Ϸ�Ǿ����ϴ�.");
					loadFriendList();
				} else JOptionPane.showMessageDialog(null, "��û�� �����߽��ϴ�.");
			}
		});
		
		// ģ�� ��Ͽ��� ģ�� Ŭ�� �� ģ�� �̴Ϸ����� �̵���Ű�� actionlistener
		jtb_friend.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtb_friend.getSelectedRow();
				TableModel model = jtb_friend.getModel();
				String ID = Utils.getID((String)model.getValueAt(row, 0));
				new FriendRoom(new UserDAO().getNO(ID));
			}
		});
	
		
	}
	// ģ�� ����� ������Ʈ�ϴ� �޼���
	public void loadFriendList() {
		rowData.clear();
		list = new FriendDAO().getFriendlist(LogInPage.getNO());
		for(String s:list) {
			Vector<String> v = new Vector<String>();
			v.add(s);
			rowData.add(v);
		} 
		jtb_friend.updateUI();
	}
	
	
}
