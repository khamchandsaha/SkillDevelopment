package com.technoforensis.skilldevelopment.model;

public class Job {
	int job_id,company_id,application_fee,experience_required;
	String job_title,job_description,start_date,last_date,job_location;
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getApplication_fee() {
		return application_fee;
	}
	public void setApplication_fee(int application_fee) {
		this.application_fee = application_fee;
	}
	public int getExperience_required() {
		return experience_required;
	}
	public void setExperience_required(int experience_required) {
		this.experience_required = experience_required;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}
	public String getJob_location() {
		return job_location;
	}
	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}
	
}
