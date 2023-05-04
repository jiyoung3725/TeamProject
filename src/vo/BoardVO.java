package vo;

import java.util.Date;

public class BoardVO {
	private int no;
	private String address;
	private String category;
	private String interest;
	private String title;
	private String b_content;
	private Date date_create;
	private String appilcation;
	private int b_cnt;
	private int l_cnt;
	
	public BoardVO() {
		super();
	}

	
	public BoardVO(int no, String address, String category, String interest, String title, String b_content,
			Date date_create, String appilcation, int b_cnt, int l_cnt) {
		super();
		this.no = no;
		this.address = address;
		this.category = category;
		this.interest = interest;
		this.title = title;
		this.b_content = b_content;
		this.date_create = date_create;
		this.appilcation = appilcation;
		this.b_cnt = b_cnt;
		this.l_cnt = l_cnt;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public String getAppilcation() {
		return appilcation;
	}

	public void setAppilcation(String appilcation) {
		this.appilcation = appilcation;
	}

	public int getB_cnt() {
		return b_cnt;
	}

	public void setB_cnt(int b_cnt) {
		this.b_cnt = b_cnt;
	}

	public int getL_cnt() {
		return l_cnt;
	}

	public void setL_cnt(int l_cnt) {
		this.l_cnt = l_cnt;
	}
	
}
	
