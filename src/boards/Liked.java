package boards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

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
        
        jsp.setBounds(20, 80, 440, 230);
        c.add(jsp);


        setTitle("좋아요 게시글");
        setSize(500, 380);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
	
	public static void main(String[] args) {
		new Liked();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}




}
;