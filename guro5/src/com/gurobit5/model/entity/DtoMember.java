package com.gurobit5.model.entity;

public class DtoMember {
	// done - sskim/2018-05-21
	// try - sskim / 2018-05-24

	
	private int rn; 
	
	private String id; // pk
	private String pw; // after process need
	private String name;
	private int phone; 
	private String email;
	// memType : 0-학생, 1-교사, 2-취업 3-영업 4-행정 5-관리자 99-임시
	private int memType;
	private int lecCode;// 18000, 18001, 18002, ~~~ 19001 기본갑 11111
	
	//추가
	private String memTypeName;// 05-30에 추가
	private String lecCodeName;// 05-30에 추가
	
	
	public DtoMember() {}

	public String getLecCodeName() {
		return lecCodeName;
	}

	public void setLecCodeName(String lecCodeName) {
		this.lecCodeName = lecCodeName;
	}

	public String getMemTypeName() {
		return memTypeName;
	}

	public void setMemTypeName(String memTypeName) {
		this.memTypeName = memTypeName;
	}

	public int getRn() {
		return rn;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public int getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public int getMemType() {
		return memType;
	}

	public int getLecCode() {
		return lecCode;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMemType(int memType) {
		this.memType = memType;
	}

	public void setLecCode(int lecCode) {
		this.lecCode = lecCode;
	}

	
}// DtoMember
