package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class GAME_LOGIC {

	public static void main(String[] args) {
		
		// 01 �ε�ȭ�� ��JPANEL 3, 2, 1 ����
		// 02 JLABEL 2�� ��������, �ð�
		// 03 JBUTTON ������
		
		// JPANEL Ÿ�� �پ��� METHOD �ϳ� ����
		
		Scanner sc = new Scanner(System.in);
		
		
		// 00 �⺻ ����
		int count = 1; // �����ߵǴ� ����
		int x = 0;
		//01 ��ư �迭 ��, �������� List ����,
		int [] ThreeXThree = new int[9];
		
	    ArrayList<Integer> list1 = new ArrayList<>(9);
	    ArrayList<Integer> list2 = new ArrayList<>(9);
	   
	    
        //02 1���� 9������ ���ڸ� ����Ʈ�� �߰�
        for (int i = 1; i <= 9; i++) 
            list1.add(i);
        for (int i = 10; i <= 18; i++) 
            list2.add(i);
        
        
        //03 ����Ʈ�� �����ϰ� ����
        Collections.shuffle(list1);
        Collections.shuffle(list2);

        //04 ����Ʈ�� �迭�� ����
        for(int i = 0; i < 9 ; i++)
        	ThreeXThree[i] = list1.get(i);
  
        while(x == 0) {
	        for (int i = 0; i < 9; i++) {
	        		if(i % 3 == 0 && i != 0) {
	        			System.out.println();
	        		}
	        		System.out.print(ThreeXThree[i] + "\t");
	        }
	        System.out.println(count +"���� �Է��ϼ���" );
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
        System.out.println("��������");
	}
}

        
        
        //05 count�� ThreeXThree�� ���ڰ� ���ٸ� �� �迭���� list2�� �θ���	
        
        //System.out.println(Arrays.toString(ThreeXThree));
        // ��� ���
//        System.out.println(list1);
//        System.out.println(list2);
//        
        // 
		
		
		
		
		//04 ����ȭ�� 
		//04 - 1 JBUTTON 2�� �ٽ��ϱ� ������ Ŭ�� �����ʷ� �����
		//04 - 2 JUBTTON 12�� -> ��ư �����ʷ� ����
		//04 - JLABEL �Ȱ��� ��������, �ð�
		//05 - LABEL �ؿ� �ð�, ���� ������ Jtable? JTEXTfield
		

		
		//01 ���� ���� ����
		

		//1) �������� ���ڸ� �Է¹޾Ƽ� �� ��ư���ٰ� �ִ´�. 12/24/36/48
		//2) ��ư�� ���� ���� 13~24 ���ڰ� �������� �Էµȴ�. 12�� ��Ȯ�ϰ� Ŭ���� �� . 36~48�� ���´�.
		//3) �� Ŭ���ǰ� ������ �Ǹ� ��ĭ�� ����Ѵ�.

