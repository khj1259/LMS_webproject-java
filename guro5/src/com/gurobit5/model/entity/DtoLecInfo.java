package com.gurobit5.model.entity;

import java.sql.Date;

public class DtoLecInfo {
	// done - sskim/2018-05-21
	// modify - sskim/2018-05-23 / 글번호용
	
	private int rn;
	
	private int lecCode; //자동입력
	private String className; // 수동입력
	private String teacher; // 조회 입력
	private Date openDate; // 수동입력
	private Date endDate; // 수동입력
	private int maxAB; // 자동
	private String keyword; // 입력
	private String preView;  // 추가 1.0ver
	private String contents; // 추가 1.0ver
	private int status; // 추가 1.0ver
	private int classno; // 강의실코드
	
	private int classMaxNum; // 강의실 제한인원
	
	public DtoLecInfo() {}

	public int getClassMaxNum() {
		return classMaxNum;
	}

	public void setClassMaxNum(int classMaxNum) {
		this.classMaxNum = classMaxNum;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getLecCode() {
		return lecCode;
	}

	public void setLecCode(int lecCode) {
		this.lecCode = lecCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMaxAB() {
		return maxAB;
	}

	public void setMaxAB(int maxAB) {
		this.maxAB = maxAB;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPreView() {
		return preView;
	}

	public void setPreView(String preView) {
		this.preView = preView;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getClassno() {
		return classno;
	}

	public void setClassno(int classno) {
		this.classno = classno;
	}

	
	
}// DtoLecInfo
