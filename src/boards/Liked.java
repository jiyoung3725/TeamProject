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
//���� ����
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

        // Ÿ��Ʋ ���̺�
        title = new JLabel("���ƿ� �Խñ�");
        title.setFont(new Font("���� ���", Font.BOLD, 30));
        title.setBounds(150, 20, 300, 30);
        c.add(title);

        // ���ƿ� ��� ���̺�
        colname = new Vector<String>();
        colname.add("��ȣ");
        colname.add("��ġ");
        colname.add("����");
        colname.add("���ƿ���");
        
        rowdata = new Vector<Vector<String>>();
        table = new JTable(rowdata,colname);
        JScrollPane jsp = new JScrollPane(table);
        table.setRowHeight(30);
        
        jsp.setBounds(20, 80, 440, 230);
        c.add(jsp);

        loadliked();
        setTitle("���ƿ� �Խñ�");
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