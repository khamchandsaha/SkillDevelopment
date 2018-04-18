package com.technoforensis.skilldevelopment.model;

public class Skill {
	int skill_id;
	String skill_title,Skill_parent_category;
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_title() {
		return skill_title;
	}
	public void setSkill_title(String skill_title) {
		this.skill_title = skill_title;
	}
	public String getSkill_parent_category() {
		return Skill_parent_category;
	}
	public void setSkill_parent_category(String skill_parent_category) {
		Skill_parent_category = skill_parent_category;
	}
	

}
