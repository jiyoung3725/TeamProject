package customerservice;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sist.vo.QnAVO;

import om.sist.dao.QnADAO;

public class Qna_List extends JPanel {
	private static JTable table;
	private static DefaultTableModel model1;
	private int q_no;
	private String q_type;
	private String q_title;
	private String q_content;
	private Date q_inquirdate;
	private String q_status;
	private Date a_date;
	
	@Override
	public String toString() {
		return "Qna_List [q_no=" + q_no + ", q_type=" + q_type + ", q_title=" + q_title + ", q_content=" + q_content
				+ ", q_inquirdate=" + q_inquirdate + ", q_status=" + q_status + ", a_date=" + a_date + "]";
	}
	public static JTable getTable() {
		return table;
	}
	public static void setTable(JTable table) {
		Qna_List.table = table;
	}
	public static DefaultTableModel getModel1() {
		return model1;
	}
	public static void setModel1(DefaultTableModel model1) {
		Qna_List.model1 = model1;
	}
	public int getQ_no() {
		return q_no;
	}
	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}
	public String getQ_type() {
		return q_type;
	}
	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public Date getQ_inquirdate() {
		return q_inquirdate;
	}
	public void setQ_inquirdate(Date q_inquirdate) {
		this.q_inquirdate = q_inquirdate;
	}
	public String getQ_status() {
		return q_status;
	}
	public void setQ_status(String q_status) {
		this.q_status = q_status;
	}
	public Date getA_date() {
		return a_date;
	}
	public void setA_date(Date a_date) {
		this.a_date = a_date;
	}
	public Qna_List(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	public Qna_List(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}  
	public Qna_List(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}
	public Qna_List() {
		model1 = new DefaultTableModel();
		model1.addColumn("문의번호");
		model1.addColumn("문의유형");
		model1.addColumn("제목");
		model1.addColumn("작성일시");
		model1.addColumn("처리상태");
		model1.addColumn("답변일시");
		table = new JTable(model1);
		JScrollPane jsp = new JScrollPane(table);
		add(jsp, BorderLayout.CENTER);
		
		table.setSize(600, 500);
		setVisible(true);

	}
	public void addRowData(Qna_List qnalist) {
		DefaultTableModel mdel1 = (DefaultTableModel) table.getModel();
		Object[] rowData = {qnalist.getQ_no(), qnalist.getQ_type(),qnalist.getQ_title(), 
							qnalist.getQ_inquirdate(), "접수",null};
		model1.addRow(rowData);
	}
		
 
	public static void main(String[] args) {
		new Qna_List();
	}

}
