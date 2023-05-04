package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class GAME_LOGIC {

	public static void main(String[] args) {
		
		// 01 로딩화면 구JPANEL 3, 2, 1 구현
		// 02 JLABEL 2개 다음숫자, 시간
		// 03 JBUTTON 나가기
		
		// JPANEL 타임 줄어드는 METHOD 하나 설계
		
		Scanner sc = new Scanner(System.in);
		
		
		// 00 기본 세팅
		int count = 1; // 눌러야되는 숫자
		int x = 0;
		//01 버튼 배열 및, 랜덤숫자 List 생성,
		int [] ThreeXThree = new int[9];
		
	    ArrayList<Integer> list1 = new ArrayList<>(9);
	    ArrayList<Integer> list2 = new ArrayList<>(9);
	   
	    
        //02 1부터 9까지의 숫자를 리스트에 추가
        for (int i = 1; i <= 9; i++) 
            list1.add(i);
        for (int i = 10; i <= 18; i++) 
            list2.add(i);
        
        
        //03 리스트를 랜덤하게 섞음
        Collections.shuffle(list1);
        Collections.shuffle(list2);

        //04 리스트를 배열과 연결
        for(int i = 0; i < 9 ; i++)
        	ThreeXThree[i] = list1.get(i);
  
        while(x == 0) {
	        for (int i = 0; i < 9; i++) {
	        		if(i % 3 == 0 && i != 0) {
	        			System.out.println();
	        		}
	        		System.out.print(ThreeXThree[i] + "\t");
	        }
	        System.out.println(count +"값을 입력하세요" );
	        int answer = sc.nextInt();
	        if (answer == count) {
	            for (int i = 0; i < 9; i++) {
	                if (ThreeXThree[i] == count) {
	                    ThreeXThree[i] = list2.get(i);
	                    break;
	                }
	            }
	            count++;
	        }
	        if ( count == 19)
	        	x = 1;
        }
        System.out.println("게임종료");
	}
}

        
        
        //05 count와 ThreeXThree의 숫자가 같다면 그 배열에는 list2를 부른다	
        
        //System.out.println(Arrays.toString(ThreeXThree));
        // 결과 출력
//        System.out.println(list1);
//        System.out.println(list2);
//        
        // 
		
		
		
		
		//04 게임화면 
		//04 - 1 JBUTTON 2개 다시하기 나가기 클릭 리스너로 ㅇ녀결
		//04 - 2 JUBTTON 12개 -> 버튼 리스너로 연결
		//04 - JLABEL 똑같이 다음숫자, 시간
		//05 - LABEL 밑에 시간, 숫자 나오는 Jtable? JTEXTfield
		

		
		//01 게임 로직 구현
		

		//1) 랜덤으로 숫자를 입력받아서 각 버튼에다가 넣는다. 12/24/36/48
		//2) 버튼을 누른 순간 13~24 숫자가 랜덤으로 입력된다. 12이 정확하게 클릭된 후 . 36~48이 나온다.
		//3) 다 클릭되고 삭제가 되면 빈칸을 출력한다.

