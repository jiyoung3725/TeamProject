package customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sist.dao.QnaDAO;

import com.sist.vo.QnAVo;


public class Qna_List extends JPanel {
	  JTable table;
	   Vector<String> colNames;
	   Vector<Vector<String>> rowData;
	   private int row;
	  
	public Qna_List() {
		
		rowData = new Vector<Vector<String>>();
		colNames = new Vector<String>();
		colNames.add("문의번호");
		colNames.add("문의유형");
		colNames.add("문의제목");
		colNames.add("작성일");
		colNames.add("처리상태");
		colNames.add("답변일");

		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		add(jsp);
		
	
		setSize(700,500);
		setVisible(true);
		loadData();
		
		table.addMouseListener(new MouseListener() {
			
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
			//<선택한 rowData에 해당하는 데이터를 팝업으로 띄우기>
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				Vector<String> v = rowData.get(row);
				 ListPopUp p = new ListPopUp();
				 
				 //<저장되어있는 관리자답변을 댓글창(jta)에 불러오는 sql문 호출>
				 int no = row+1;
				 QnaDAO dao = new QnaDAO();
				 ArrayList<QnAVo> list = dao.searchAnswer(no);
				 for(QnAVo vo : list) {
					 String content = vo.getA_content();
					 p.jta.setText(content);
					 p.jta.repaint();
				 }
				
				 //<ListPopUp객체 생성 후 '등록'버튼을 눌렀을 때 답변을 등록하는 클래스>	
					// listPopUp클래스에서는 row값을 가져올 수 없어서 여기에 넣음.
				p.btn_add.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int q_no = row+1;
						String content = p.answer.getText();
						QnAVo q = new QnAVo();
						QnaDAO dao = new QnaDAO();
						int re = dao.addAnswer(content, q_no);
						if(re==1) {
							JOptionPane.showMessageDialog(null, "답변이 등록되었습니다");
							p.jta.setText(content);
							p.jta.repaint();
							p.answer.setText("");
							
							//<q_status 변경하는 sql문 호출>
							int no = row+1;
							QnaDAO Dao = new QnaDAO();
							int r = dao.updateStatus(no);
							loadData();
						}
					}
			
				});
				
			}
		});
	}
	//Jtable의 rowData에 값 띄우기
	public void loadData() {
		rowData.clear();
		
		QnaDAO dao = new QnaDAO();
		ArrayList<QnAVo> list = dao.addList();
		for(QnAVo v : list) {
			Vector<String> c = new Vector<String>();
			c.add(v.getQ_no()+"");
			c.add(v.getQ_type());
			c.add(v.getQ_title());
			c.add(v.getQ_inquirdate()+"");
			c.add(v.getQ_status());
			c.add(v.getA_date()+"");
			rowData.add(c);
			
		}table.updateUI();
		
	}	


	 public static void main(String[] args) {
		new Qna_List();
	}
}
