package home;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.FriendDAO;
import dao.MiniroomDAO;
import dao.UserDAO;
import first.LogInPage;
import vo.BoardVO;
import vo.UserVO;
// Ȩ ȭ�鿡 ���� �Խñ� ��
public class BoardTab extends JTabbedPane {
	private Vector<String>AddrcolNames;
	private Vector<Vector<String>> AddrrowData;
	private Vector<String>InterestcolNames;
	private Vector<Vector<String>> InterestrowData;
	public BoardTab() {
		// ������ ���̺� ����
		AddrcolNames = new Vector<String>();
		AddrcolNames.add("�α�� ���");
		AddrrowData = new Vector<Vector<String>>();
		ArrayList<BoardVO> Addrlist = new MiniroomDAO().getAddrBoard(LogInPage.getNO());
		for(BoardVO b:Addrlist) {
			Vector<String> v = new Vector<String>();
			v.add(b.getTitle());
			AddrrowData.add(v);
		}
		// ���ɻ纰 ���̺� ����
		InterestcolNames = new Vector<String>();
		InterestcolNames.add("�α�� ���");
		InterestrowData = new Vector<Vector<String>>();
		ArrayList<BoardVO> Interestlist = new MiniroomDAO().getInterestBoard(LogInPage.getNO());
		for(BoardVO b:Interestlist) {
			Vector<String> v = new Vector<String>();
			v.add(b.getTitle());
			InterestrowData.add(v);
		}
		
		JTable jtb_addr = new JTable(AddrrowData, AddrcolNames);
		JScrollPane jsp_addr = new JScrollPane(jtb_addr);
		JTable jtb_interest = new JTable(InterestrowData, InterestcolNames);
		JScrollPane jsp_interest = new JScrollPane(jtb_interest);
		
		// �� �߰�
		addTab("���ɻ纰", jsp_interest);
		addTab("������", jsp_addr);
		
		// ���� ����
		jtb_addr.addMouseListener(new MouseListener() {
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
				int row = jtb_addr.getSelectedRow()+1;
				TableModel model = jtb_addr.getModel();
				
			}
		});
	
		
	}
}
