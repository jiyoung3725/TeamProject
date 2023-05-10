package boards;

import javax.swing.*;

import dao.BoardDAO;
import vo.BoardVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WriteBoard extends JFrame {
	private int row = 1;
    private JLabel title, titleLabel, categoryLabel, contentLabel, applicationLabel, interestLabel;
    private JComboBox<String> categoryComboBox, interestComboBox;
    private JTextField titleField, applyField;
    private JTextArea contentArea;
    private JButton postButton, cancelButton;
    
    public WriteBoard() {
        super("�Խñ� �ۼ�");
        // ��� �г� ����
        JPanel topPanel = new JPanel(new GridLayout(5, 1));

        // Ÿ��Ʋ ���̺�
        
        title = new JLabel("  �Խñ� �ۼ�");
        title.setFont(new Font("���� ���", Font.BOLD, 25));
        topPanel.add(title);
        
        
        // �Խ��� ī�װ� ���� �޺��ڽ�
        JPanel p_top1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel categoryLabel = new JLabel("  ī�װ�  ");
        categoryLabel.setFont(new Font("���� ���", Font.BOLD, 15));
        String[] categoryOptions = {"�Բ��ؿ�", "���׻�Ȱ"};
        categoryComboBox = new JComboBox<>(categoryOptions);
        p_top1.add(categoryLabel);
        p_top1.add(categoryComboBox);
        topPanel.add(p_top1);

        
        // �Խ��� ���ɻ� ���� �޺��ڽ�
        JPanel p_top3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        interestLabel = new JLabel("  ���ɻ�     ");
        interestLabel.setFont(new Font("���� ���", Font.BOLD, 15));
        String[] interestOptions = {"�ǰ�/�","����/�丮","��ȭ/����/����","�̼�/����","�뷡/����","����ũ","��Ÿ"};
        interestComboBox = new JComboBox<>(interestOptions);
        p_top3.add(interestLabel);
        p_top3.add(interestComboBox);
        topPanel.add(p_top3);
        
        // �Խ��� ��û�ο� ���� �ʵ�
        JPanel p_top5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        applicationLabel = new JLabel("  ��û�ο�  ");
        applicationLabel.setFont(new Font("���� ���", Font.BOLD, 15));
        applyField = new JTextField();
        applyField.setPreferredSize(new Dimension(80, 25));
        applyField.setFont(new Font("SansSerif", Font.PLAIN, 15));
       
        p_top5.add(applicationLabel);
        p_top5.add(applyField);
        p_top5.add(new JLabel("��"));
        topPanel.add(p_top5);
        
        // ���� �Է� �ʵ�
        JPanel p_top4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleLabel = new JLabel("  ����        ");
        titleLabel.setFont(new Font("���� ���", Font.BOLD, 15));
        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(680, 30));
        titleField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        p_top4.add(titleLabel);
        p_top4.add(titleField);
        topPanel.add(p_top4);

        // ��� �г� �߰�
        add(topPanel, BorderLayout.NORTH);

        // �ߴ� �г� ����
        JPanel middlePanel = new JPanel(new BorderLayout());

        // ���� �Է� �ʵ�
        contentLabel = new JLabel("   ����");
        contentLabel.setFont(new Font("���� ���", Font.BOLD, 15));
        contentArea = new JTextArea();
        contentArea.setFont(new Font("���� ���", Font.PLAIN, 15));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        middlePanel.add(contentLabel, BorderLayout.NORTH);
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        // �ߴ� �г� �߰�
        add(middlePanel, BorderLayout.CENTER);

        // �ϴ� �г� ����
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // �ۼ� �Ϸ� ��ư
        postButton = new JButton("�ۼ�");
        bottomPanel.add(postButton);
        
        // �ۼ� ��� ��ư
        cancelButton = new JButton("���");
        bottomPanel.add(cancelButton);

        // �ϴ� �г� �߰�
        add(bottomPanel, BorderLayout.SOUTH);
        
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        //�Խñ� �߰�
        postButton.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				String category = (String) categoryComboBox.getSelectedItem();
				String interest = (String) interestComboBox.getSelectedItem();
				String title = titleField.getText();
				String apply = applyField.getText();
				String content = contentArea.getText();
				try {
				    BoardDAO dao = new BoardDAO();
				    BoardVO vo = new BoardVO();
				    
				    vo.setCategory(category);
				    vo.setTitle(title);
				    vo.setB_content(content);
				    vo.setInterest(interest);
				    vo.setAppilcation(apply);
				    int re = dao.insertBoard(vo);
				    if (re > 0) {
				        JOptionPane.showMessageDialog(null, "�Խñ��� ��ϵǾ����ϴ�.");
				        Board boardpage = new Board();
				        boardpage.loadList(row);
				        dispose();
				    } else {
				        JOptionPane.showMessageDialog(null, "�Խñ� ��Ͽ� �����Ͽ����ϴ�.");
				    }
				} catch (Exception ex) {
				    ex.printStackTrace();
				    JOptionPane.showMessageDialog(null, "�Խñ� ��� �� ���ܰ� �߻��Ͽ����ϴ�.");
				}
			}
		});
        
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }
    
    public static void main(String[] args) {
        new WriteBoard();
    }
} 
