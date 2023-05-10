package customer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dao.QnaDAO;
import first.LogInPage;
import vo.QnAVo;


public class Qna_List extends JPanel {
	  JTable table;
	   Vector<String> colNames;
	   Vector<Vector<String>> rowData;
	   JButton btn;
	   private int row;
	public Qna_List() {
		btn = new JButton("����");
		
		rowData = new Vector<Vector<String>>();
		colNames = new Vector<String>();
		colNames.add("���ǹ�ȣ");
		colNames.add("��������");
		colNames.add("��������");
		colNames.add("�ۼ���");
		colNames.add("ó������");
		colNames.add("�亯��");
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		JPanel p =new JPanel();
		p.setLayout(new BorderLayout());
		p.add(jsp, BorderLayout.CENTER);
		p.add(btn,BorderLayout.SOUTH);
		add(p);
	
	
		setSize(700,500);
		setVisible(true);
		loadData();
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				int row = table.getSelectedRow();
				int no = Integer.parseInt((String)table.getValueAt(row, 0));
				int re = new QnaDAO().deleteList(no);
				if(re==1) {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
					loadData();
				}else {
					System.out.println("����");
				}
			}
		});
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
			//<������ rowData�� �ش��ϴ� �����͸� �˾����� ����>
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				Vector<String> v = rowData.get(row);
				 ListPopUp p = new ListPopUp();
				 //<����Ǿ��ִ� �����ڴ亯�� ���â(jta)�� �ҷ����� sql�� ȣ��>
				 int no = row+1;
				 QnaDAO dao = new QnaDAO();
				 ArrayList<QnAVo> list = dao.searchAnswer(no);
				 for(QnAVo vo : list) {
					 String content = vo.getA_content();
					 p.jta.setText(content);
					 p.jta.repaint();
				 }
				
				 //<ListPopUp��ü ���� �� '���'��ư�� ������ �� �亯�� ����ϴ� Ŭ����>	
					// listPopUpŬ���������� row���� ������ �� ��� ���⿡ ����.
				p.btn_add.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int row = table.getSelectedRow();
						int q_no = Integer.parseInt((String)table.getValueAt(row, 0));
						String content = p.answer.getText();
						int u_no = LogInPage.getNO();
						int re = new QnaDAO().addAnswer(content, q_no, u_no);
						if(u_no==0) {	
							JOptionPane.showMessageDialog(null, "�亯�� ��ϵǾ����ϴ�");
							p.jta.setText(content);
							p.jta.repaint();
							p.answer.setText("");
							//<q_status �����ϴ� sql�� ȣ��>
							QnaDAO Dao = new QnaDAO();
							int r = dao.updateStatus(q_no);
							loadData();
						}else{
							JOptionPane.showMessageDialog(null, "�����ڸ� �Է��� �� �ֽ��ϴ�");
							
						}
					}
			
				});
				
			}
		});
	}
	//Jtable�� rowData�� �� ����
	public void loadData() {
		rowData.clear();
		int no = LogInPage.getNO();
		if(no==0) {
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
		return;
		}else if(no!=0){
			QnaDAO dao = new QnaDAO();
			ArrayList<QnAVo> list = dao.addList(no);
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
		
	}	


	 public static void main(String[] args) {
		new Qna_List();
	}
}
