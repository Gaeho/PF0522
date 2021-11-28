package dao;

import java.util.Date;

public class Review {

	private String pd_code;
	private int rev_code;
	private String user_id;
	private int rev_avg;
	private String rev_context;
	private Date rev_reg_date;
//	private String img_code;
	
	
	public String getPd_code() {
		return pd_code;
	}
	public void setPd_code(String pd_code) {
		this.pd_code = pd_code;
	}
	public int getRev_code() {
		return rev_code;
	}
	public void setRev_code(int rev_code) {
		this.rev_code = rev_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getRev_avg() {
		return rev_avg;
	}
	public void setRev_avg(int rev_avg) {
		this.rev_avg = rev_avg;
	}
	public String getRev_context() {
		return rev_context;
	}
	public void setRev_context(String rev_context) {
		this.rev_context = rev_context;
	}
	public Date getRev_reg_date() {
		return rev_reg_date;
	}
	public void setRev_reg_date(Date rev_reg_date) {
		this.rev_reg_date = rev_reg_date;
	}

	
	
	
	
	
	
	
}
