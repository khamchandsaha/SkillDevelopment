package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.technoforensis.skilldevelopment.model.Job;
import com.technoforensis.skilldevelopment.model.Qualification;
import com.technoforensis.skilldevelopment.model.Skill;
import com.technoforensis.skilldevelopment.model.User;
import com.technoforensis.skilldevelopment.utility.DBConnection;

/**
 * @author khamchand
 * This class contains the common database utility functions for all members 
 */
public class BasicDBUtility {
	/**
	 * @param user_name
	 * @return true if user is already present or false
	 */
	public boolean checkMember(String user_name)
	{
		int user_id = 0;
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("select member_id from login where user_name = ?");
			pstm.setString(1, user_name);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				user_id = Integer.parseInt(rs.getString("member_id"));
			}
			if(user_id>0)
			{
				return true;
			}
		}catch(Exception e)
		{
			System.out.println("error in UserDB.checkMember"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * @param user_name
	 * @param hashCode
	 * @return role_id
	 */
	public int authMember(String user_name, String hashCode)
	{
		int role_id = 0;
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("select role_id from login where user_name = ? and password = ?");
			pstm.setString(1, user_name);
			pstm.setString(2, hashCode);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				role_id = Integer.parseInt(rs.getString("role_id"));
			}
		}catch(Exception e)
		{
			System.out.println("error in UserDB.authMember"+e.getMessage());
		}
		
		return role_id;
	}
	
	/**
	 * @param user_name
	 * @param hashCode
	 * 
	 * This method will update the password
	 */
	public void updatePassword(String user_name, String hashCode)
	{
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("UPDATE login set password=? where user_name = ?");
			pstm.setString(2, user_name);
			pstm.setString(1, hashCode);
			pstm.execute();
		}catch(Exception e)
		{
			System.out.println("error in BasicDBUtility.updatePassword"+e.getMessage());
		}
	}
	
	/**
	 * @return The list of skills
	 */
	public ArrayList<Skill> getSkillListForJob()
	{
		ArrayList<Skill> skill_list = new ArrayList<Skill>();
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT * FROM skill_list");
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Skill sk = new Skill();
				sk.setSkill_id(Integer.parseInt(rs.getString("skill_id")));
				sk.setSkill_title(rs.getString("skill_title"));
				sk.setSkill_parent_category(rs.getString("skill_parent_category"));
				skill_list.add(sk);
			}
			
		}catch(Exception e)
		{
			System.out.println("error in BasicDB.getSkillListForJob"+e.getMessage());
		}
		
		return skill_list;
	}
	
	/**
	 * @return This will return the list of 
	 * Predefined qualification
	 */
	public ArrayList<Qualification> getQualificationList()
	{
		ArrayList<Qualification> q_list = new ArrayList<Qualification>();
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT * FROM qualification");
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Qualification qua = new Qualification();
				qua.setQualification_id(Integer.parseInt(rs.getString("qualification_id")));
				qua.setQualification(rs.getString("qualification"));
				q_list.add(qua);
			}
			
		}catch(Exception e)
		{
			System.out.println("error in BasicDB.getQualificationList"+e.getMessage());
		}
		
		return q_list;
	}

	public ArrayList<Job> getJobList()
	{
		ArrayList<Job> job_list = new ArrayList<Job>();
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT * FROM job_list");
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				Job jb = new Job();
				jb.setApplication_fee(rs.getInt("application_fee"));
				jb.setCompany_id(rs.getInt("company_id"));
				jb.setExperience_required(rs.getInt("experience_required"));
				jb.setJob_description(rs.getString("job_description"));
				jb.setJob_id(rs.getInt("job_id"));
				jb.setJob_location(rs.getString("job_location"));
				jb.setJob_title(rs.getString("job_title"));
				jb.setLast_date(rs.getDate("last_date").toString());
				jb.setQualification_id(rs.getInt("qualification_id"));
				jb.setStart_date(rs.getDate("start_date").toString());
				PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("SELECT * FROM job_skills where job_id=?");
				pstm1.setInt(1, jb.getJob_id());
				ResultSet rs1 = pstm1.executeQuery();
				int skill_list_ids[] = new int[20];
				int k=0;
				while(rs1.next())
				{
					skill_list_ids[k]=rs1.getInt("job_skills");
					k++;
				}
				jb.setSkill_list(skill_list_ids);
				job_list.add(jb);				
			}
		}catch(Exception e)
		{
			System.out.println("error in BasicDB.getJobList"+e.getMessage());
		}
		
		return job_list;
		
	}
	
	/**
	 * @param user_id
	 * @param job_id
	 * This will add the job applicants
	 */
	public void addJobApplicant(int user_id,int job_id)
	{
		try 
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("INSERT INTO job_applicants VALUE(?,?,?)");
			pstm.setInt(1, job_id);
			pstm.setInt(2, user_id);
			pstm.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstm.execute();		
		}catch(Exception e)
		{
			System.out.println("error in BasicDB.addJobApplicant"+e.getMessage());
		}
	}
	
}
