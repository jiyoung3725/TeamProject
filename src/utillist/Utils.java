package utillist;

import java.util.Arrays;

import javax.swing.JComboBox;

public class Utils {
	public final static String []interest = {"�ǰ�/�","����/�丮","��ȭ/����/����","�̼�/����","�뷡/����","����ũ","��Ÿ"};
	public final static String[] address = 
		{"����", "�λ�", "�뱸", "��õ", "����", "����", "���", "����", "���", "����", "��û�ϵ�", "��û����", "����ϵ�", "���󳲵�", "���ϵ�", "��󳲵�", "����"};
	private final static JComboBox<String> addr_list = new JComboBox<String>(address);
	private final static JComboBox<String> interest_list = new JComboBox<String>(interest);
	private Utils() {}
	// name(id)������ String���� id�� �����ϴ� �޼���
	public static String getID(String s) {
		return s.substring(s.indexOf("(")+1, s.length()-1);
	}
	public static JComboBox<String> getAddrList() {
		return addr_list;
	}
	public static JComboBox<String> getInterestList() {
		return interest_list;
	}
	public static int getAddrIndex(String addr) {
		return Arrays.asList(address).indexOf(addr);
	}
	public static int getInterestIndex(String inter) {
		return Arrays.asList(interest).indexOf(inter);
	}
}
