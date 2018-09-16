package com.gurobit5.model.bbs.entity;

import java.sql.Date;


public class DtoJobInfo {
	// done - sskim/2018-05-21
	
	private int rn; // 글번호용 
	
	private int num; // seq-pk
	private String title;
	private String id;
	private Date nalja;
	private String company;
	private int hireNum;
	private String hireType;
	private int sal;
	private String region;
	private String qualify;
	private Date dueDate;
	private String email;
	private String contents;
	private int viewNum;
	
	public DtoJobInfo() {
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getNum() {
		return num;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public Date getNalja() {
		return nalja;
	}

	public String getCompany() {
		return company;
	}

	public int getHireNum() {
		return hireNum;
	}

	public String getHireType() {
		return hireType;
	}

	public int getSal() {
		return sal;
	}

	public String getRegion() {
		return region;
	}

	public String getQualify() {
		return qualify;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public String getEmail() {
		return email;
	}

	public String getContents() {
		return contents;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNalja(Date nalja) {
		this.nalja = nalja;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setHireNum(int hireNum) {
		this.hireNum = hireNum;
	}

	public void setHireType(String hireType) {
		this.hireType = hireType;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setQualify(String qualify) {
		this.qualify = qualify;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	
}// DtoJobInfo
