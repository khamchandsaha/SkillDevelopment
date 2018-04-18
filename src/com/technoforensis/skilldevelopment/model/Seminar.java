package com.technoforensis.skilldevelopment.model;

public class Seminar {
	int seminar_id,institute_id,seminar_duration,seminar_fee;
	String seminar_name,seminar_description,start_date;
	public int getSeminar_id() {
		return seminar_id;
	}
	public void setSeminar_id(int seminar_id) {
		this.seminar_id = seminar_id;
	}
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getSeminar_duration() {
		return seminar_duration;
	}
	public void setSeminar_duration(int seminar_duration) {
		this.seminar_duration = seminar_duration;
	}
	public int getSeminar_fee() {
		return seminar_fee;
	}
	public void setSeminar_fee(int seminar_fee) {
		this.seminar_fee = seminar_fee;
	}
	public String getSeminar_name() {
		return seminar_name;
	}
	public void setSeminar_name(String seminar_name) {
		this.seminar_name = seminar_name;
	}
	public String getSeminar_description() {
		return seminar_description;
	}
	public void setSeminar_description(String seminar_description) {
		this.seminar_description = seminar_description;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
}
