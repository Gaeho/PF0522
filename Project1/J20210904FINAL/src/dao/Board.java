package dao;

import java.util.Date;

public class Board {
	
	private int brd_code;
	private String user_id;
	private Date brd_reg_date;
	private String brd_subject;
	private String brd_context;
	private int ref;
	private int ref_level;
	private int ref_step;
	private String brd_pd_code;
	public String getBrd_pd_code() {
		return brd_pd_code;
	}
	public void setBrd_pd_code(String brd_pd_code) {
		this.brd_pd_code = brd_pd_code;
	}
	private int num;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBrd_code() {
		return brd_code;
	}
	public void setBrd_code(int brd_code) {
		this.brd_code = brd_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getBrd_reg_date() {
		return brd_reg_date;
	}
	public void setBrd_reg_date(Date brd_reg_date) {
		this.brd_reg_date = brd_reg_date;
	}
	public String getBrd_subject() {
		return brd_subject;
	}
	public void setBrd_subject(String brd_subject) {
		this.brd_subject = brd_subject;
	}
	public String getBrd_context() {
		return brd_context;
	}
	public void setBrd_context(String brd_context) {
		this.brd_context = brd_context;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRef_level() {
		return ref_level;
	}
	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}
	public int getRef_step() {
		return ref_step;
	}
	public void setRef_step(int ref_step) {
		this.ref_step = ref_step;
	}
	
	

}
