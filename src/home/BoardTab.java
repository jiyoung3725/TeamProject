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
// 홈 화면에 들어가는 게시글 탭
public class BoardTab extends JTabbedPane {
	private Vector<String>AddrcolNames;
	private Vector<Vector<String>> AddrrowData;
	private Vector<String>InterestcolNames;
	private Vector<Vector<String>> InterestrowData;
	public BoardTab() {
		// 지역별 테이블 생성
		AddrcolNames = new Vector<String>();
		AddrcolNames.add("인기글 목록");
		AddrrowData = new Vector<Vector<String>>();
		ArrayList<BoardVO> Addrlist = new MiniroomDAO().getAddrBoard(LogInPage.getNO());
		for(BoardVO b:Addrlist) {
			Vector<String> v = new Vector<String>();
			v.add(b.getTitle());
			AddrrowData.add(v);
		}
		// 관심사별 테이블 생성
		InterestcolNames = new Vector<String>();
		InterestcolNames.add("인기글 목록");
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
		
		// 탭 추가
		addTab("관심사별", jsp_interest);
		addTab("지역별", jsp_addr);
		
		// 수정 예정
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
