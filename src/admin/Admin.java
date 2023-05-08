package admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.AdminDAO;
import dao.UserDAO;

public class Admin extends JFrame implements MouseListener{
	Vector<String> colNames_report;
	Vector<Vector<String>> rowData_report;
	JTable table_report;
	Vector<String> colNames_user;
	Vector<Vector<String>> rowData_user;
	JTable table_user;
	Vector<String> colNames_qna;
	Vector<Vector<String>> rowData_qna;
	JTable table_qna;
	ArrayList<HashMap<String, Object>> list;
	JTextField jtf_r_title;
	JTextArea jta_r_content;
	JTextArea jta_r_b_content;
	JTextField jtf_q_title;
	JTextArea jta_q_content;
	JTextArea jta_a_content;
	public Admin() {
		// 신고 게시글 테이블 생성
		colNames_report = new Vector<String>();
		String[] arr_report = {"신고 번호", "신고일", "신고 내용", "신고자", "게시글 번호", "처리 상태"};
		for(String s:arr_report) {
			colNames_report.add(s);
		}
		rowData_report = new Vector<Vector<String>>();
		table_report = new JTable(rowData_report, colNames_report);
		loadReportedBoard();
		table_report.addMouseListener(this);
		JScrollPane jsp_report = new JScrollPane(table_report);
		
		// 신고 게시글 상세 내역 
		jtf_r_title = new JTextField(20);
		jtf_r_title.setEnabled(true);
		jta_r_content = new JTextArea();
		jta_r_content.setEnabled(true);
		jta_r_b_content = new JTextArea();
		jta_r_b_content.setEnabled(true);
		JButton btn_report = new JButton("게시글 삭제");
		JPanel pan_detail_report = new JPanel();
		pan_detail_report.setLayout(new BoxLayout(pan_detail_report, BoxLayout.Y_AXIS));
		pan_detail_report.add(new JLabel("신고 내용 : "));
		pan_detail_report.add(jta_r_content);
		pan_detail_report.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_detail_report.add(new JLabel("신고된 게시글 : "));
		pan_detail_report.add(jtf_r_title);
		pan_detail_report.add(jta_r_b_content);
		pan_detail_report.add(Box.createRigidArea(new Dimension(0, 10)));
		pan_detail_report.add(btn_report);
		
		// 신고 내역 panel 생성
		JPanel pan_report = new JPanel(new GridLayout(2,1));
		pan_report.add(jsp_report);
		pan_report.add(pan_detail_report);
		
		
		// 문의 내역 테이블 생성
		colNames_qna = new Vector<String>();
		String[] arr_qna = {"문의 번호", "문의 유형","제목", "상태", "문의 일자"};
		for(String s:arr_qna) {
			colNames_qna.add(s);
		}
		rowData_qna = new Vector<Vector<String>>();
		table_qna = new JTable(rowData_qna, colNames_qna);
//		loadQna();
		table_qna.addMouseListener(this);
		JScrollPane jsp_qna = new JScrollPane(table_qna);
		// 문의 내역을 보여주는 panel 생성
		JPanel pan_detail_qna = new JPanel();
		pan_detail_qna.setLayout(new BoxLayout(pan_detail_qna, BoxLayout.Y_AXIS));
		jtf_q_title = new JTextField(20);
		jta_q_content = new JTextArea(10,10);
		jta_a_content = new JTextArea(10,10);
		pan_detail_qna.add(Box.createRigidArea(new Dimension(0,10)));
		pan_detail_qna.add(new JLabel("제목"));
		pan_detail_qna.add(jtf_q_title);
		pan_detail_qna.add(Box.createRigidArea(new Dimension(0,10)));
		pan_detail_qna.add(new JLabel("내용"));
		pan_detail_qna.add(new JScrollPane(jta_q_content));
		pan_detail_qna.add(Box.createRigidArea(new Dimension(0,10)));
		// 답변을 입력하는 panel 생성
		JPanel pan_answer = new JPanel();
		pan_answer.setLayout(new BoxLayout(pan_answer, BoxLayout.Y_AXIS));
		pan_answer.add(Box.createRigidArea(new Dimension(0,10)));
		pan_answer.add(new JLabel("답변"));
		pan_answer.add(new JScrollPane(jta_a_content));
		pan_answer.add(Box.createRigidArea(new Dimension(0,10)));
		JButton btn_answer = new JButton("답변하기");
		pan_answer.add(btn_answer);
		pan_answer.add(Box.createRigidArea(new Dimension(0,10)));
		
		// 문의내역 panel 생성
		JPanel pan_qna = new JPanel(new GridLayout(1,3));
		pan_qna.add(jsp_qna);
		pan_qna.add(pan_detail_qna);
		pan_qna.add(pan_answer);
		
		
		// 회원 목록 테이블 생성
		colNames_user = new Vector<String>();
		String[] arr_user = {"회원 번호", "회원 아이디","회원 이름", "가입일자", "신고 횟수"};
		for(String s:arr_user) {
			colNames_user.add(s);
		}
		rowData_user = new Vector<Vector<String>>();
		table_user = new JTable(rowData_user, colNames_user);
		loadUser();
		table_user.addMouseListener(this);
		JScrollPane jsp_user = new JScrollPane(table_user);
		
		
		JTabbedPane tab_admin = new JTabbedPane();
		tab_admin.add("신고 내역", pan_report);
		tab_admin.add("문의 내역", pan_qna);
		tab_admin.add("회원 관리", jsp_user);
		add(tab_admin);
		setSize(900, 650);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_report.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "정말 게시글을 삭제하시겠습니까?")!=JOptionPane.OK_OPTION) return;
				int row = table_report.getSelectedRow();
				int r_no = Integer.parseInt((String)table_report.getValueAt(row, 0));
				int re = new AdminDAO().updateReports(r_no);
				if(re==-1) {
					JOptionPane.showMessageDialog(null, "게시글이 삭제되지 않았습니다.");
					return;
				}
				JOptionPane.showMessageDialog(null, "게시글이 되었습니다.");
				loadReportedBoard();
			}
		});
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==table_user) {
			loadUser();
			int row = table_user.getSelectedRow();
			int NO	= Integer.parseInt((String) table_user.getValueAt(row, 0));
			String name = (String)table_user.getValueAt(row, 2);
			int re = JOptionPane.showConfirmDialog(null, name+"님을 삭제하시겠습니까?");
			if(re!=JOptionPane.OK_OPTION) return;
			re = new AdminDAO().deleteUser(NO);
			if(re==-1) JOptionPane.showMessageDialog(null, "회원 삭제에 실패했습니다.");
			else JOptionPane.showMessageDialog(null, "회원을 삭제했습니다.");
			loadUser();
		} else if(e.getSource()==table_report) {
			loadReportedBoard();
			int row = table_report.getSelectedRow();
			int NO = Integer.parseInt((String)table_report.getValueAt(row, 0));
			String rcontent = (String)table_report.getValueAt(row, 2);
			HashMap<String, Object> map = list.get(row);
			jta_r_content.setText((String)map.get("r_content"));
			jta_r_b_content.setText((String)map.get("b_content"));
			jtf_r_title.setText((String)map.get("b_title"));
			
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadReportedBoard() {
		list = new AdminDAO().viewReportList();
		rowData_report.clear(); 
		for(HashMap<String, Object> map : list) {
			Vector<String> v = new Vector<String>();
			v.add(map.get("r_no")+"");
			v.add(map.get("date_report").toString());
			v.add((String)map.get("r_content"));
			int user_no = (int)map.get("user_no");
			String id = new UserDAO().getID(user_no);
			v.add(new UserDAO().getName(id)+"("+id+")");
			v.add(map.get("b_no")+"");
			v.add((String)map.get("r_state"));
			rowData_report.add(v);
		}
		table_report.updateUI();
	}
	public void loadUser() {
		list = new AdminDAO().viewUserList();
		rowData_user.clear();
		for(HashMap<String, Object> map : list) {
			Vector<String> v = new Vector<String>();
			v.add(map.get("user_no")+"");
			v.add((String)map.get("user_id"));
			v.add((String)map.get("user_name"));
			v.add(map.get("date_update").toString());
			v.add(map.get("r_cnt")+"");
			rowData_user.add(v);
		}
		table_user.updateUI();
	}
}
