package dao;

public class CartDetail {
	private String user_id;
	private String pd_code;
	private int pd_qty;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPd_code() {
		return pd_code;
	}
	public void setPd_code(String pd_code) {
		this.pd_code = pd_code;
	}
	public int getPd_qty() {
		return pd_qty;
	}
	public void setPd_qty(int pd_qty) {
		this.pd_qty = pd_qty;
	}
}
