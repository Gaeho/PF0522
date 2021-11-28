package dao;

import java.util.Date;

public class Orders {

	
	private int ord_code; // 주문코드
	private String user_id; // 아이디
	private Date ord_date; // 주문일 
	private String ord_status; // 처리상태 
	private String user_tel; //전화번호
	private String user_addr; //주소 
	private String ord_receiver; //수령인 
	private String ord_memo; //배송메모 
	
	
	//orderDetail 구현 위해서 몇 가지 다른 곳에서 가져왔습니다
	private String pd_code;
	private int pd_qty; 
	private int pd_price; //order_detail 
	private String pd_name; //product
	private String rn;
	
	//--추가한 게터,세터
	
	//orderdetail 사진 넣기 위해서 추가했습니다. 필드값,게터,세터
	  private String img_path;
	  
	  public String getImg_path() { 
		  return img_path; 
	  }
	  
	  public void setImg_path(String img_path) { 
		  this.img_path = img_path; 
		  
	  }
	
	
	public int getOrd_code() {
		return ord_code;
	}
	public String getPd_code() {
		return pd_code;
	}
	public void setPd_code(String pd_code) {
		this.pd_code = pd_code;
	}
	public void setOrd_code(int ord_code) {
		this.ord_code = ord_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	public String getOrd_status() {
		return ord_status;
	}
	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getOrd_receiver() {
		return ord_receiver;
	}
	public void setOrd_receiver(String ord_receiver) {
		this.ord_receiver = ord_receiver;
	}
	public String getOrd_memo() {
		return ord_memo;
	}
	public void setOrd_memo(String ord_memo) {
		this.ord_memo = ord_memo;
	}
	public int getPd_qty() {
		return pd_qty;
	}
	public void setPd_qty(int pd_qty) {
		this.pd_qty = pd_qty;
	}
	public int getPd_price() {
		return pd_price;
	}
	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	
	
	
	
	
	
	
	
	
	
}