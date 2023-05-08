package boards;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dao.BoardDAO;
import vo.BoardVO;
//���� ����
public class Board extends JFrame {
	JTextField jtf_search;
	JComboBox<String> jcb_option;	//�����ؿ�, �츮���� ����
	String []interest = {"�ǰ�/�","����/�丮","��ȭ/����/����","�̼�/����","�뷡/����","����ũ","��Ÿ"};
	JCheckBox []jcb = new JCheckBox[interest.length];
	JTable table;
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JRadioButton jrb_option1;
	JRadioButton jrb_option2;
	ArrayList<BoardVO> list;
		
	public Board() {
		JPanel p_main = new JPanel();	//�Խ��� ���� ȭ��
		p_main.setLayout(new BorderLayout());
		JPanel p_search1 = new JPanel();	//�˻�â ��ü �г�
		JPanel p_search2 = new JPanel();	//�˻�â ���
		JPanel p_search3 = new JPanel();	//�˻�â �ϴ�
		JPanel p_interest = new JPanel();	//���ɻ� �˻� �г�
		p_interest.setLayout(new GridLayout(7,1));
		JPanel p_etc = new JPanel();	//�۾���, ������ �ѱ�
		p_etc.setLayout(new GridLayout(1,3));
		JPanel p_etc1 = new JPanel();
		JPanel p_etc2 = new JPanel();
		JPanel p_etc3 = new JPanel();
		p_etc.add(p_etc1);
		p_etc.add(p_etc2);
		p_etc.add(p_etc3);
		
		p_search1.setLayout(new GridLayout(3,1));
		jcb_option = new JComboBox<String>();
		jcb_option.addItem("�Բ��ؿ�");
		jcb_option.addItem("���׻�Ȱ");
		
		jtf_search = new JTextField(20);
		JButton btn_search = new JButton("�˻�");
		JButton btn_clear = new JButton("�ʱ�ȭ");
		jrb_option1 = new JRadioButton("�α��") ;	//�α�� ����
		jrb_option2 = new JRadioButton("�ֽż�");	//�ֽż� ����
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb_option1);
		bg.add(jrb_option2);
		
		p_search2.add(jcb_option);
		p_search2.add(jtf_search);
		p_search2.add(btn_search);
		p_search2.add(btn_clear);
		p_search3.add(jrb_option1);
		p_search3.add(jrb_option2);
		p_search1.add(p_search2);
		p_search1.add(p_search3);
		
		p_main.add(p_search1, BorderLayout.NORTH);
	
		//üũ�ڽ� �����Ͽ� ���ɻ纰 ��� ���� (��ɱ���X)
		for(int i=0; i<jcb.length ; i++) {
			jcb[i] = new JCheckBox(interest[i]);
			p_interest.add(jcb[i]);
			
			jcb[i].addActionListener(new ActionListener() {
				ArrayList<String> Interests = new ArrayList<>();
				@Override
				public void actionPerformed(ActionEvent e) {
					
					 Interests.clear(); 
					for(int j = 0; j < jcb.length; j++) {
						if(jcb[j].isSelected()) {
							Interests.add(jcb[j].getText());
						}
					}
					
					BoardDAO dao = new BoardDAO();
					ArrayList<BoardVO> list = dao.interestList(Interests);
					// DefaultTableModel ��ü ����
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					for (BoardVO vo : list) {
			            model.addRow(new Object[]{vo.getNo(), vo.getAddress(), vo.getCategory(), vo.getInterest(), vo.getTitle(), vo.getDate_board(), 
			            		vo.getAppilcation(), vo.getB_cnt(), vo.getL_cnt()});
			           
			        }
					table.updateUI();
				}
			});
		}
		p_main.add(p_interest, BorderLayout.WEST);
		
		colNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		
		colNames.add("��ȣ");
		colNames.add("��ġ");
		colNames.add("ī�װ�");
		colNames.add("���ɻ�");
		colNames.add("����");
		colNames.add("�ۼ���¥");
		colNames.add("��������");
		colNames.add("��ȸ��");
		colNames.add("��");
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column4 = columnModel.getColumn(4);
		TableColumn column5 = columnModel.getColumn(5);
		column4.setPreferredWidth(350);
		column5.setPreferredWidth(110);
		table.revalidate();
		table.setRowHeight(35);
		p_main.add(jsp, BorderLayout.CENTER);
		
		JButton btn_pre = new JButton("����");
		JLabel jlb_page = new JLabel("page");	//page ��ȣ�� ���� �ٲ�� ���� ����
		JButton btn_post = new JButton("����");
		p_etc2.add(btn_pre);
		p_etc2.add(jlb_page);
		p_etc2.add(btn_post);
		
		JButton btn_write = new JButton("�Խñ� �ۼ�");
		p_etc3.add(btn_write);
		p_main.add(p_etc, BorderLayout.SOUTH);
		add(p_main);
		
		loadAllList(); 
		setSize(800, 700);
		 setLocationRelativeTo(null);
		setVisible(true);
		
		// �Խñ� �ۼ� ��ư
		btn_write.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WriteBoard writepage = new WriteBoard();
				writepage.setVisible(true);
				
			}
		});
		
		// �ʱ�ȭ ��ư 
		btn_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadAllList();
				
			}
		});
		// �˻� �̺�Ʈ ����
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				BoardDAO dao = new BoardDAO();
				String search = jtf_search.getText();
				
				ArrayList<BoardVO> list = dao.dateSearchList(search);
				for( BoardVO b :list) {
					Vector<String> v = new Vector<>();
					v.add(b.getNo()+"");
					v.add(b.getAddress());
					v.add(b.getCategory());
					v.add(b.getInterest());
					v.add(b.getTitle());
					v.add(b.getDate_board()+"");
					v.add(b.getAppilcation());
					v.add(b.getB_cnt()+"");
					v.add(b.getL_cnt()+"");
					rowData.add(v);
				}
				table.updateUI();
			}
		});
		
		//���� ��ư �α�� ����
		jrb_option1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				if(jrb_option1.isSelected()) {
					BoardDAO dao = new BoardDAO();
					ArrayList<BoardVO> list = dao.viewLikedList();
					
					for( BoardVO b :list) {
						Vector<String> v = new Vector<>();
						v.add(b.getNo()+"");
						v.add(b.getAddress());
						v.add(b.getCategory());
						v.add(b.getInterest());
						v.add(b.getTitle());
						v.add(b.getDate_board()+"");
						v.add(b.getAppilcation());
						v.add(b.getB_cnt()+"");
						v.add(b.getL_cnt()+"");
						rowData.add(v);
					}
					table.updateUI();
				}
			}
		});
		
		//���� ��ư �ֽż� ����
		jrb_option2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				if(jrb_option2.isSelected()) {
					BoardDAO dao = new BoardDAO();
					ArrayList<BoardVO> list = dao.viewNewestList();
					
					for( BoardVO b :list) {
						Vector<String> v = new Vector<>();
						v.add(b.getNo()+"");
						v.add(b.getAddress());
						v.add(b.getCategory());
						v.add(b.getInterest());
						v.add(b.getTitle());
						v.add(b.getDate_board()+"");
						v.add(b.getAppilcation());
						v.add(b.getB_cnt()+"");
						v.add(b.getL_cnt()+"");
						rowData.add(v);
					}
					table.updateUI();
				}
			}
		});
		
		// ���� ����Ŭ�� �� ���������� ����
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
// ���������� ���� ����
				if(e.getClickCount() == 2 ) {
				int row = table.getSelectedRow();
//				BoardVO b = list.get(row);
//				int b_no = b.getNo();
				int col = table.getSelectedColumn();
		        Object data = table.getValueAt(row, col);
				Liked likedpage = new Liked();
				likedpage.setVisible(true);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	public void loadAllList() {
		rowData.clear();
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.viewAllList(1);
		
		for( BoardVO b :list) {
			Vector<String> v = new Vector<>();
			v.add(b.getNo()+"");
			v.add(b.getAddress());
			v.add(b.getCategory());
			v.add(b.getInterest());
			v.add(b.getTitle());
			v.add(b.getDate_board()+"");
			v.add(b.getAppilcation());
			v.add(b.getB_cnt()+"");
			v.add(b.getL_cnt()+"");
			rowData.add(v);
		}
		table.updateUI();
	}

	
	public static void main(String[] args) {
		new Board();
	} 

}
;