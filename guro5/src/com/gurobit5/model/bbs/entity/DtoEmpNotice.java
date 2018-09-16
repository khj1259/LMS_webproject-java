package com.gurobit5.model.bbs.entity;

import java.sql.Date;


public class DtoEmpNotice{
	// done - sskim/2018-05-21
	//수정 - 김형준 2018-05-29
	private int rn;
	private int num; // seq - pk
	private String title;
	private String contents;
	private String id; // fk
	private Date nalja;
	private int view_num;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getNalja() {
		return nalja;
	}
	public void setNalja(Date nalja) {
		this.nalja = nalja;
	}
	public int getView_num() {
		return view_num;
	}
	public void setView_num(int view_num) {
		this.view_num = view_num;
	}
	
	
	
}// DtoEmpNotice
