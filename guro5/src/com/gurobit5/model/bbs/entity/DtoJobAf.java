package com.gurobit5.model.bbs.entity;

import java.sql.Date;

public class DtoJobAf {
	//2018-05-24 김형준
	private int rn; //글번호,조회수,강의코드
	private int num;
	private int view_num;
	private int lec_code;
	private Date nalja; //등록일
	private String title;
	private String contents;
	private String company;
	private String id;
	private String field;
	private String name; //제목,내용,취업업체명,아이디,취업분야,취업자명
	
	private String class_name; // 강의명 (강의코드를 사용하여 불러옴.)
	
	
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
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
	public void setNum(int num) {
		this.num = num;
	}
	public int getView_num() {
		return view_num;
	}
	public void setView_num(int view_num) {
		this.view_num = view_num;
	}
	public int getLec_code() {
		return lec_code;
	}
	public void setLec_code(int lec_code) {
		this.lec_code = lec_code;
	}
	public Date getNalja() {
		return nalja;
	}
	public void setNalja(Date nalja) {
		this.nalja = nalja;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
