package com.gurobit5.model.bbs.entity;

import java.sql.Date;


public class DtoLecNotice{
	// try - hyunjung/2018-05-21
	// try - hyunjung/2018-05-24
	private int num;
	private String title;
	private String id;
	private Date nalja;
	private int lecCode;
	private String contents;
	private int viewNum;
	
	private int rn; //rownum 추가
	
	public DtoLecNotice() {	}
	
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
	public int getLecCode() {
		return lecCode;
	}
	public void setLecCode(int lecCode) {
		this.lecCode = lecCode;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}
	
	
}// DtoLecNotice
