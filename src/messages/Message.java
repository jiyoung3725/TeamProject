package messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.MemoDAO;
import first.LogInPage;
import vo.MemoVO;

public class Message extends JPanel {
	Vector<Vector<String>> rowData;
	Vector<String> colName;
	static JTable table;
	public Message() {
		colName = new Vector<String>();
		colName.add("���� ���");
		colName.add("����");
		colName.add("���� �ð�");
		colName.add("���� ����");
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData, colName);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setSize(400, 200);
		loadMessages();
		
		JCheckBox jcb_check = new JCheckBox("���� ���� ������ ����");
		JButton btn_new = new JButton("�� ����");
		JPanel pan_north = new JPanel();
		pan_north.add(jcb_check);
		pan_north.add(btn_new);
		JPanel pan_message = new JPanel(new BorderLayout());
		pan_message.add(pan_north, BorderLayout.NORTH);
		pan_message.add(jsp, BorderLayout.CENTER);
		
		add(new JLabel("������"), BorderLayout.NORTH);
		add(pan_message, BorderLayout.CENTER);
		setBackground(new Color(206, 212,192));
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				TableModel model = table.getModel();
				String str = (String)model.getValueAt(row, 0);
				System.out.println(str);
				MemoVO m = new MemoDAO().viewDetailMessage(LogInPage.NO, row);
				int re = new MemoDAO().changeState(m);
				if(re==-1) {
				} else {
					loadMessages();
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				new MessageDetail(m);		
				}
		});
		jcb_check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jcb_check.isSelected()) {
					rowData.clear();
					ArrayList<MemoVO> list = new MemoDAO().viewUnCheckedMessages(LogInPage.NO);
					for(MemoVO m:list) {
						Vector<String> v = new Vector<String>();
						v.add(m.getSender_name());
						v.add(m.getContent());
						v.add(m.getDate_sent().toString());
						v.add(m.getState());
						rowData.add(v);
					}
					table.updateUI();
				} else loadMessages();
			}
		});
		btn_new.addActionListener(e->new MemoNew());
	}
	 
	public void loadMessages() {
		rowData.clear();
		ArrayList<MemoVO> list = new MemoDAO().viewMessages(LogInPage.NO);
		for(MemoVO m:list) {
			Vector<String> v = new Vector<String>();
			v.add(m.getSender_name());
			v.add(m.getContent());
			v.add(m.getDate_sent().toString());
			v.add(m.getState());
			rowData.add(v);
		}
		table.updateUI();
	}

}
