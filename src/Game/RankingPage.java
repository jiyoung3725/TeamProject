package Game;

import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.GameDAO;
import db.ConnectionProvider;
import vo.GameVO;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RankingPage extends JFrame{
	JTable table;
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTextField jtf;
	
	public RankingPage(){
		
		JPanel p = new JPanel();
		Container c = getContentPane();
		setTitle("RANKINGPAGE");
		getContentPane().setLayout(null);
		
		// 타이틀 설정 - STARTPAGE와 같음.
		JLabel title_1to50 = new JLabel("1  to  50");
		title_1to50.setFont(new Font("굴림", Font.BOLD, 30));
		title_1to50.setBounds(214, 10, 135, 52);
		c.add(title_1to50);
		
		//rowData 설정
		rowData = new Vector<Vector<String>>();
		
		
		// 컬럼 설정
		colNames = new Vector<String>();
		colNames.add("순위");
		colNames.add("닉네임");
		colNames.add("거주지");
		colNames.add("시간");

		//테이블 생성
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(49, 112, 500, 200); // JScrollPane 위치와 크기 설정
		c.add(jsp);

		JButton btn_Exit = new JButton("나가기");
		btn_Exit.setBounds(444, 30, 91, 23);
		c.add(btn_Exit);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loadRecord();
	}
			
	
	public void loadRecord() {
	    rowData.clear();
	    GameDAO dao = new GameDAO();
	    ArrayList<HashMap<String, Object>> list = dao.recordlist();
	    for(HashMap<String, Object> map :list) {
	    	Vector<String> v= new Vector<String>();
	    	v.add(map.get("user_no")+"");
	    	v.add((String)map.get("name"));
	    	v.add((String)map.get("address"));
	    	v.add((String)map.get("score"));
	    	rowData.add(v);
	    }
	    
	    table.updateUI();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RankingPage();
	}
}
