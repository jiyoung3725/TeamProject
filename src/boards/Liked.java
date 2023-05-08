package boards;

import javax.swing.*;

import dao.BoardDAO;
import dao.LikedDAO;
import vo.BoardVO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
//수정 예정
public class Liked extends JFrame implements ActionListener {
    JLabel title, content;
    JTable table;
    Vector<Vector<String>> rowdata;
    Vector<String> colname;
    
    JTextArea contentTextArea;
    JButton applyButton;

    public Liked() {

        Container c = getContentPane();
        c.setLayout(null);

        // 타이틀 레이블
        title = new JLabel("좋아요 게시글");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        title.setBounds(150, 20, 300, 30);
        c.add(title);

        // 좋아요 목록 테이블
        colname = new Vector<String>();
        colname.add("번호");
        colname.add("위치");
        colname.add("제목");
        colname.add("좋아요일");
        
        rowdata = new Vector<Vector<String>>();
        table = new JTable(rowdata,colname);
        JScrollPane jsp = new JScrollPane(table);
        table.setRowHeight(30);
        
        jsp.setBounds(20, 80, 440, 230);
        c.add(jsp);

        loadliked();
        setTitle("좋아요 게시글");
        setSize(500, 380);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
	
    public void loadliked () {
    	rowdata.clear();
    	LikedDAO dao = new LikedDAO();
    	ArrayList<BoardVO> list = dao.myLikedlist();
    	for(BoardVO b:list) {
    		Vector<String> v = new Vector<>();
    		v.add(b.getNo()+"");
    		v.add(b.getAddress());
    		v.add(b.getTitle());
    		v.add(b.getDate_board()+"");
    		rowdata.add(v);
    	}
    	table.updateUI();
    }
	public static void main(String[] args) {
		new Liked();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
