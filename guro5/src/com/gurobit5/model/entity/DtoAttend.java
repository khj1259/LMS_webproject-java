package com.gurobit5.model.entity;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class DtoAttend {
	// done - hyunjung/2018-05-21
	// modify - hyunjung/2018-05-28
	// modify - hyunjung/2018-05-29
	private String id;
	private String name;
	private Date nalja;
	private Time inDate;
	private Time outDate;
	private String state;
	private int lecCode;
	
	//필드 추가
	private int late;	//지각횟수
	private int absent;	//결석횟수
	private int max_ab;	//최대 결석일
	
	public DtoAttend() {}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getNalja() {
		return nalja;
	}


	public void setNalja(Date nalja) {
		this.nalja = nalja;
	}


	public Time getInDate() {
		return inDate;
	}


	public void setInDate(Time inDate) {
		this.inDate = inDate;
	}


	public Time getOutDate() {
		return outDate;
	}


	public void setOutDate(Time outDate) {
		this.outDate = outDate;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getLecCode() {
		return lecCode;
	}


	public void setLecCode(int lecCode) {
		this.lecCode = lecCode;
	}


	public int getLate() {
		return late;
	}


	public void setLate(int late) {
		this.late = late;
	}


	public int getAbsent() {
		return absent;
	}


	public void setAbsent(int absent) {
		this.absent = absent;
	}


	public int getMax_ab() {
		return max_ab;
	}


	public void setMax_ab(int max_ab) {
		this.max_ab = max_ab;
	}
	
	
	
}// DtoAttend
