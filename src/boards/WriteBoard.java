package boards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteBoard extends JFrame {
    private JLabel title, titleLabel, categoryLabel, contentLabel, locationLabel, applicationLabel;
    private JComboBox<String> categoryComboBox, locationComboBox, interestComboBox;
    private JTextField titleField, applyField;
    private JCheckBox anonymousCheckBox;
    private JTextArea contentArea;
    private JButton postButton, cancelButton;
    
    public WriteBoard() {
        super("게시글 작성");
        // 상단 패널 생성
        JPanel topPanel = new JPanel(new GridLayout(7, 1));

        // 타이틀 레이블
        
        title = new JLabel("  게시글 작성");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        
        
        topPanel.add(title);
        
        // 게시판 카테고리 선택 콤보박스
        JPanel p_top1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel categoryLabel = new JLabel("  카테고리  ");
        categoryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        String[] categoryOptions = {"함께해요", "동네생활"};
        categoryComboBox = new JComboBox<>(categoryOptions);
        p_top1.add(categoryLabel);
        p_top1.add(categoryComboBox);
        topPanel.add(p_top1);

        // 위치 선택 콤보박스
        JPanel p_top2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        locationLabel = new JLabel("  위치        ");
        locationLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        String[] locationOptions = {"서울       ", "경기", "인천", "부산", "대구", "광주", "대전", "울산", "기타"};
        locationComboBox = new JComboBox<>(locationOptions);
        p_top2.add(locationLabel);
        p_top2.add(locationComboBox);
        topPanel.add(p_top2);
        
        // 게시판 관심사 선택 콤보박스
        JPanel p_top3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryLabel = new JLabel("  관심사     ");
        categoryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        String[] interestOptions = {"일반", "유머", "스포츠   ", "게임", "IT"};
        categoryComboBox = new JComboBox<>(interestOptions);
        p_top3.add(categoryLabel);
        p_top3.add(categoryComboBox);
        topPanel.add(p_top3);
        
        // 게시판 신청인원 선택 필드
        JPanel p_top5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        applicationLabel = new JLabel("  신청인원  ");
        applicationLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        applyField = new JTextField();
        applyField.setPreferredSize(new Dimension(80, 25));
        applyField.setFont(new Font("SansSerif", Font.PLAIN, 20));
       
        p_top5.add(applicationLabel);
        p_top5.add(applyField);
        p_top5.add(new JLabel("명"));
        topPanel.add(p_top5);
        
        // 제목 입력 필드
        JPanel p_top4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleLabel = new JLabel("  제목        ");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(680, 30));
        titleField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        p_top4.add(titleLabel);
        p_top4.add(titleField);
        topPanel.add(p_top4);

        // 상단 패널 추가
        add(topPanel, BorderLayout.NORTH);

        // 중단 패널 생성
        JPanel middlePanel = new JPanel(new BorderLayout());

        // 내용 입력 필드
        contentLabel = new JLabel("   내용");
        contentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        contentArea = new JTextArea();
        contentArea.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        middlePanel.add(contentLabel, BorderLayout.NORTH);
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        // 중단 패널 추가
        add(middlePanel, BorderLayout.CENTER);

        // 하단 패널 생성
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // 작성 완료 버튼
        postButton = new JButton("작성");
        bottomPanel.add(postButton);
        
        // 작성 취소 버튼
        cancelButton = new JButton("취소");
        bottomPanel.add(cancelButton);

        // 하단 패널 추가
        add(bottomPanel, BorderLayout.SOUTH);
        
        // 프레임 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        
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

