package Game;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;
import javax.swing.WindowConstants;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePage extends JFrame {

    private JButton[] btnarr;
    private Timer timer;
    private JLabel Time;
    private JLabel timeLabel = new JLabel("000.00");
    private int delay = 10; // 10ms 마다 실행될 작업
    private double[] timeElapsed = {000}; // 경과된 시간
    private double savedTime = 0;
    
    //00 기본 세팅
    int count = 1; // 눌러야되는 숫자
    int x = 0;
    int [] first = new int[9];
    int [] second = new int[9];
    int [] third = new int[9];
    int [] fourth = new int[9];
    int [] fifth = new int[9];

    JLabel countLabel = new JLabel(Integer.toString(count));
    Container c = getContentPane(); 


    //01 버튼, 배열 및, 랜덤숫자 List 생성,
    
    //02 3 * 3 로 만들며, 45까지만 예정
    ArrayList<Integer> list1 = new ArrayList<>(9);
    ArrayList<Integer> list2 = new ArrayList<>(9);
    ArrayList<Integer> list3 = new ArrayList<>(9);
    ArrayList<Integer> list4 = new ArrayList<>(9);
    ArrayList<Integer> list5 = new ArrayList<>(9);
    public GamePage() {
       setTitle("Game2");
       c.setLayout(null);
        
	  
        
	   JPanel panel = new JPanel();
	   panel.add(GameLogic());
       panel.setBounds(380, 100, 183, 205);
       c.add(panel);
      
       
       JLabel Count = new JLabel("다음 숫자");
       Count.setFont(new Font("굴림", Font.BOLD, 20));
       Count.setBounds(27, 10, 119, 46);
       c.add(Count);

       // 라벨은 메소드 때문에 멤버변수로 선언
       countLabel.setBounds(27, 56, 119, 46);
	   c.add(countLabel);

	   
       
	   JLabel Time = new JLabel("시 간");
       Time.setBounds(406, 10, 119, 46);
       c.add(Time);

       //Timer 출력
       startTimer();
       timeLabel.setBounds(406, 56, 119, 46);
       c.add(timeLabel);

       
       // Timer 객체 생성
       // 아진짜 이 개같은거 stop 기능은 final이라서 재할당이 안돼서 메소들 빼야된다
    
       
       JButton btn_restart = new JButton("다시하기");
       btn_restart.setBounds(222, 157, 91, 23);
       c.add(btn_restart);
        
        JButton btn_Exit = new JButton("나가기");
        btn_Exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_Exit.setBounds(222, 216, 91, 23);
       c.add(btn_Exit);
       
       Image_Start();

       addKeyListener(new MyKeyListener()); // KeyListener 등록
       setFocusable(true); // 포커스 요청
       setResizable(false);
       setSize(600,400);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    
    
    public Timer startTimer() {
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (count >= 2 && count < 46) {
                        timeElapsed[0] += (double) delay / 1000; // 1초 = 1000ms
                        String timeText = String.format("%06.2f", timeElapsed[0]);
                        timeLabel.setText(timeText); // 수정된 부분
                    } else if (count == 46) {
                        savedTime = timeElapsed[0]; // savedTime 변수를 갱신함
                        String timeText = String.format("%06.2f", timeElapsed[0]);
                        timeLabel.setText(timeText); // count가 45일 때 timeLabel의 텍스트를 변경함
                        //01
                        double score = Double.parseDouble(timeText); 
                        
                        JOptionPane.showMessageDialog(null, "축하합니다!");
                        count ++; //Dialog를 끄기위한 장치. 
                    } else {
                        timeLabel.setText("000.00"); // 수정된 부분
                    }
                } catch (Exception e2) {
                    System.out.println("예외발생" + e2.getMessage());
                }
            }
        });

        timer.start();
        return timer;
    }
    
    public void Image_Start() {
	    try {
	     
	   	 JPanel image = new JPanel() {
	 			    protected void paintComponent(Graphics g) {
	 			       super.paintComponent(g);
	 			        ImageIcon icon = new ImageIcon("Start.gif");
	 			        icon.paintIcon(this, g, 0, 0);
	 			    }
	 			};
	   	      
	   	       image.setBounds(27, 100, 159, 205);
	   	       c.add(image);
	   	        
		} catch (Exception e) {
			System.out.println("예외발생" + e.getMessage());
		}
    }
    
    public void Image_Correct() {
	    try {
	     
	   	 JPanel image = new JPanel() {
	 			    protected void paintComponent(Graphics g) {
	 			       super.paintComponent(g);
	 			        ImageIcon icon = new ImageIcon("Correct.gif");
	 			        icon.paintIcon(this, g, 0, 0);
	 			    }
	 			};
	   	      
	   	       image.setBounds(27, 100, 159, 205);
	   	       c.add(image);
	   	        
		} catch (Exception e) {
			System.out.println("예외발생" + e.getMessage());
		}
    }
    
    public void Image_Wrong() {
	    try {
	   	 JPanel image = new JPanel() {
	 			    protected void paintComponent(Graphics g) {
	 			       super.paintComponent(g);
	 			        ImageIcon icon = new ImageIcon("Worng.gif");
	 			        icon.paintIcon(this, g, 0, 0);
	 			    }
	 			};
	   	       image.setBounds(27, 100, 159, 205);
	   	       c.add(image);
	   	        
		} catch (Exception e) {
			System.out.println("예외발생" + e.getMessage());
		}
    }
    // savedTime을 반환하는 메소드
    public double getSavedTime() {
        return savedTime;
    }
    private void updateCountLabel() {
        countLabel.setText(Integer.toString(count));
        if(count == 46) {
        	 countLabel.setText("End!");
        }
    }
    
    //시간 멈춤 메소드
    private void stopTimer() {
        savedTime = timeElapsed[0]; // final 변수에 값을 할당할 수 없으므로 에러 발생
    	timer.stop();
    	// 종료 이벤트 처리 코드 추가
    }

    public JPanel GameLogic() {
        //03 숫자를 리스트에 추가
        for (int i = 1; i <= 9; i++) 
            list1.add(i);
        for (int i = 10; i <= 18; i++) 
            list2.add(i);
        for (int i = 19; i <= 27; i++) 
        	list3.add(i);
        for (int i = 28; i <= 36; i++) 
        	list4.add(i);
        for (int i = 37; i <= 45; i++) 
        	list5.add(i);

                //04 리스트를 랜덤하게 섞음
        Collections.shuffle(list1);
        Collections.shuffle(list2);
        Collections.shuffle(list3);
        Collections.shuffle(list4);
        Collections.shuffle(list5);
        System.out.println(list1);
        System.out.println(list2);

        //05 리스트를 배열과 연결
        for(int i = 0; i < 9 ; i++) {
           first[i]  = list1.get(i);
	       second[i] = list2.get(i);
	       third[i]  = list3.get(i);
	       fourth[i] = list4.get(i);
	       fifth[i]  = list5.get(i);
        }
        // 버튼에다가 배열 연결
        JPanel p = new JPanel();
        p.setBounds(294, 5, 0, 0);
        p.setLayout(new GridLayout(3,3));
        
        btnarr = new JButton[9];
        for (int i = 0 ; i < 9 ; i++) {
            btnarr[i] = new JButton(Integer.toString(first[i]));
            btnarr[i].setFont(new Font("Arial", Font.PLAIN, 15));
            btnarr[i].addKeyListener(new MyKeyListener());
            p.add(btnarr[i]);
        }
        return p;
    }
    
  
    
    private class MyKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
        		
        		try {
        	        // 소리 파일 로드
        	        File soundFile = new File("btn_sound.wav");
        	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        	        // 클립 생성 및 재생
        	        Clip clip = AudioSystem.getClip();
        	        clip.open(audioIn);
        	        clip.start();
        	    } catch (Exception ex) {
        	        ex.printStackTrace();
        	    }
        	
        	if(e.getKeyCode() == KeyEvent.VK_NUMPAD7){
        		btnarr[0].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
        		btnarr[1].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
        		btnarr[2].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
        		btnarr[3].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
        		btnarr[4].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
        		btnarr[5].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
        		btnarr[6].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
        		btnarr[7].doClick();
        	} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
        		btnarr[8].doClick();
        	} 

        	
        	
        	if (count <= 9) {
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 &&  
                	Integer.toString(count).equals(Integer.toString(first[0]))){
                    btnarr[0].setText(Integer.toString(second[0]));
                    count = count + 1;
  
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 && 
                    	Integer.toString(count).equals(Integer.toString(first[1]))){
                    btnarr[1].setText(Integer.toString(second[1]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 &&
                    	Integer.toString(count).equals(Integer.toString(first[2]))){
                    btnarr[2].setText(Integer.toString(second[2]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 &&
                    	Integer.toString(count).equals(Integer.toString(first[3]))){
                    btnarr[3].setText(Integer.toString(second[3]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 &&
                    	Integer.toString(count).equals(Integer.toString(first[4]))){
                    btnarr[4].setText(Integer.toString(second[4]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 &&
                    	Integer.toString(count).equals(Integer.toString(first[5]))){
                    btnarr[5].setText(Integer.toString(second[5]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 &&
                    	Integer.toString(count).equals(Integer.toString(first[6]))){
                    btnarr[6].setText(Integer.toString(second[6]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 &&
                    	Integer.toString(count).equals(Integer.toString(first[7]))){
                    btnarr[7].setText(Integer.toString(second[7]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 &&
                    	Integer.toString(count).equals(Integer.toString(first[8]))){
                    btnarr[8].setText(Integer.toString(second[8]));
                	count = count + 1;
                } 
                updateCountLabel();
                System.out.println(count);
            }
            
            else if (count >= 10 && count <= 18) {
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 &&  
                	Integer.toString(count).equals(Integer.toString(second[0]))){
                    btnarr[0].setText(Integer.toString(third[0]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 && 
                    	Integer.toString(count).equals(Integer.toString(second[1]))){
                    btnarr[1].setText(Integer.toString(third[1]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 &&
                    	Integer.toString(count).equals(Integer.toString(second[2]))){
                    btnarr[2].setText(Integer.toString(third[2]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 &&
                    	Integer.toString(count).equals(Integer.toString(second[3]))){
                    btnarr[3].setText(Integer.toString(third[3]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 &&
                    	Integer.toString(count).equals(Integer.toString(second[4]))){
                    btnarr[4].setText(Integer.toString(third[4]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 &&
                    	Integer.toString(count).equals(Integer.toString(second[5]))){
                    btnarr[5].setText(Integer.toString(third[5]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 &&
                    	Integer.toString(count).equals(Integer.toString(second[6]))){
                    btnarr[6].setText(Integer.toString(third[6]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 &&
                    	Integer.toString(count).equals(Integer.toString(second[7]))){
                    btnarr[7].setText(Integer.toString(third[7]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 &&
                    	Integer.toString(count).equals(Integer.toString(second[8]))){
                    btnarr[8].setText(Integer.toString(third[8]));
                    count = count + 1;
                } 
                updateCountLabel();
                System.out.println(count);
            }
            else if (count >= 19 && count <= 27) {
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 &&  
                	Integer.toString(count).equals(Integer.toString(third[0]))){
                    btnarr[0].setText(Integer.toString(fourth[0]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 && 
                    	Integer.toString(count).equals(Integer.toString(third[1]))){
                    btnarr[1].setText(Integer.toString(fourth[1]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 &&
                    	Integer.toString(count).equals(Integer.toString(third[2]))){
                    btnarr[2].setText(Integer.toString(fourth[2]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 &&
                    	Integer.toString(count).equals(Integer.toString(third[3]))){
                    btnarr[3].setText(Integer.toString(fourth[3]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 &&
                    	Integer.toString(count).equals(Integer.toString(third[4]))){
                    btnarr[4].setText(Integer.toString(fourth[4]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 &&
                    	Integer.toString(count).equals(Integer.toString(third[5]))){
                    btnarr[5].setText(Integer.toString(fourth[5]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 &&
                    	Integer.toString(count).equals(Integer.toString(third[6]))){
                    btnarr[6].setText(Integer.toString(fourth[6]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 &&
                    	Integer.toString(count).equals(Integer.toString(third[7]))){
                    btnarr[7].setText(Integer.toString(fourth[7]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 &&
                    	Integer.toString(count).equals(Integer.toString(third[8]))){
                    btnarr[8].setText(Integer.toString(fourth[8]));
                    count = count + 1;
                } 
                updateCountLabel();
                System.out.println(count);
            }
            
            else if (count >= 28 && count <= 36) {
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 &&  
                	Integer.toString(count).equals(Integer.toString(fourth[0]))){
                    btnarr[0].setText(Integer.toString(fifth[0]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 && 
                    	Integer.toString(count).equals(Integer.toString(fourth[1]))){
                    btnarr[1].setText(Integer.toString(fifth[1]));
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[2]))){
                    btnarr[2].setText(Integer.toString(fifth[2]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[3]))){
                    btnarr[3].setText(Integer.toString(fifth[3]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[4]))){
                    btnarr[4].setText(Integer.toString(fifth[4]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[5]))){
                    btnarr[5].setText(Integer.toString(fifth[5]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[6]))){
                    btnarr[6].setText(Integer.toString(fifth[6]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[7]))){
                    btnarr[7].setText(Integer.toString(fifth[7]));
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 &&
                    	Integer.toString(count).equals(Integer.toString(fourth[8]))){
                    btnarr[8].setText(Integer.toString(fifth[8]));
                    count = count + 1;
                } 
                updateCountLabel();
                System.out.println(count);
            }
            
            else if (count >= 37 && count <= 45) {
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 &&  
                	Integer.toString(count).equals(Integer.toString(fifth[0]))){
                    btnarr[0].setText("0");
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 && 
                    	Integer.toString(count).equals(Integer.toString(fifth[1]))){
                    btnarr[1].setText("0");
                	count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[2]))){
                    btnarr[2].setText("0");
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[3]))){
                    btnarr[3].setText("0");
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[4]))){
                    btnarr[4].setText("0");
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[5]))){
                    btnarr[5].setText("0");
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[6]))){
                    btnarr[6].setText("0");
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[7]))){
                    btnarr[7].setText("0");
                    count = count + 1;
                } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 &&
                    	Integer.toString(count).equals(Integer.toString(fifth[8]))){
                    btnarr[8].setText("0");
                    count = count + 1;
                } 
                updateCountLabel();
                System.out.println(count);
            }
        }
        // 다른 KeyListener 메소드들은 구현하지 않음

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }
}

        


