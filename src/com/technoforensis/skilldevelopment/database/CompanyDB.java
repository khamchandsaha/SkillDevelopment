package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.technoforensis.skilldevelopment.model.Company;
import com.technoforensis.skilldevelopment.model.Job;
import com.technoforensis.skilldevelopment.model.Skill;
import com.technoforensis.skilldevelopment.model.User;
import com.technoforensis.skilldevelopment.utility.DBConnection;

/**
 * @author khamchand
 * This class will provide the methods
 * to store/retrive the data of company to/from database
 */
public class CompanyDB {
	/**
	 * @param user_name
	 * @return company id
	 * This will return the id of the company
	 */
	public int getCompanyID(String user_name)
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
			
		}catch(Exception e)
		{
			System.out.println("Error in CompanyDB.getUserID"+e.toString());
		}
		return user_id;
	}
	
	/**
	 * @param comapny
	 * @param hashCode
	 * This method will be called for company registration
	 */
	public void newRegister(Company cmp,String hashCode)
	{
		try 
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("INSERT INTO login (user_name,password,role_id) VALUES (?,?,?)");
			pstm.setString(1,cmp.getMobile());
			pstm.setString(2,hashCode);
			pstm.setInt(3,2);
			pstm.execute();	
			
			int company_id = getCompanyID(cmp.getMobile());
			if(company_id!=0)
			{
				PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("INSERT INTO company_details (company_id,company_name,mobile,registration,email) VALUES (?,?,?,?,?)");
				pstm1.setInt(1, company_id);
				pstm1.setString(2,cmp.getCompany_name());
				pstm1.setString(3,cmp.getMobile());
				pstm1.setString(4, cmp.getRegistration());
				pstm1.setString(5, cmp.getEmail());
				pstm1.execute();
			}
			
				
		}catch (Exception e)
		{
			System.out.println("e : in CompanyDB.newRegister "+e.toString());
		}
	}
	
	/**
	 * @param company
	 * @return company 
	 * The return object company contains all the details of the company
	 */
	public Company getCompanyDetails(Company cmp)
	{
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("select * from company_details where company_id = ?");
			pstm.setInt(1, cmp.getCompany_id());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				cmp.setAddress(rs.getString("address"));
				cmp.setCompany_logo_url(rs.getString("company_logo_url"));
				cmp.setCompany_name(rs.getString("company_name"));
				cmp.setCompany_tin(rs.getInt("company_tin"));
				cmp.setCompany_website(rs.getString("company_website"));
				cmp.setEmail(rs.getString("email"));
				cmp.setEstablished_year(rs.getString("established_year"));
				cmp.setRegistration(rs.getString("registration"));

			}
			
		}catch(Exception e)
		{
			System.out.println("Error in ComapnyDB.getCompanyDetails"+e.toString());
		}
		
		return cmp;
	}
	
	/**
	 * @param ujob
	 * @param cmp
	 * @return 1 for not successful and 0 for successful
	 */
	public int jobPost(Job ujob, Company cmp)
	{
		try
		{
			Connection conn = DBConnection.getConnection();
			SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
			
			java.util.Date start_date = date.parse(ujob.getStart_date());
			java.util.Date last_date = date.parse(ujob.getStart_date());
			
			PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("INSERT INTO job_list (job_title,company_id,job_description,experience_required,start_date,last_date,application_fee,job_location,qualification_id) VALUES (?,?,?,?,?,?,?,?,?)");
			pstm1.setString(1,ujob.getJob_title());
			pstm1.setInt(2, cmp.getCompany_id());
			pstm1.setString(3,ujob.getJob_description());
			pstm1.setInt(4, ujob.getExperience_required());
			pstm1.setDate(5, new Date(start_date.getTime()));
			pstm1.setDate(6, new Date(last_date.getTime()));
			pstm1.setString(8, ujob.getJob_location());
			pstm1.setInt(7, ujob.getApplication_fee());
			pstm1.setInt(9, ujob.getQualification_id());
			pstm1.execute();
			
			//job id
			int job_id=1;
			PreparedStatement pstm2 = (PreparedStatement) conn.prepareStatement("SELECT LAST_INSERT_ID() FROM job_list");
			ResultSet rs = pstm2.executeQuery();
			while(rs.next())
			{
				job_id = Integer.parseInt(rs.getString("LAST_INSERT_ID()"));
				System.out.println("job id is: "+job_id);
			}
			
			//for job skills
			PreparedStatement pstm3 = (PreparedStatement) conn.prepareStatement("INSERT INTO job_skills (job_id,job_skills) value(?,?)");
			int job_skill_ids[] = ujob.getSkill_list();
			pstm3.setInt(1, job_id);
			for(int i=0; i<job_skill_ids.length && job_skill_ids[i]>0; i++)
			{
				pstm3.setInt(2, job_skill_ids[i]);
				pstm3.execute();
			}
		}catch (Exception e)
		{
			System.out.println("e : in CompanyDB.jobPost "+e.toString());
			return 1; //not successful
		}
		return 0; //for successful
	}
	
	/**
	 * @param cmp
	 * @return true or false
	 * This will update the profile of the company
	 */
	public int updateProfile(Company cmp)
	{
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("UPDATE company_details SET company_name=?,established_year=?,address=?,registration=?,email=?,company_website=?,company_tin=? where company_id = ?");
			pstm.setInt(1, cmp.getCompany_id());
			pstm.setString(1, cmp.getCompany_name());
			pstm.setString(2, cmp.getEstablished_year());
			pstm.setString(3, cmp.getAddress());
			pstm.setString(4, cmp.getRegistration());
			pstm.setString(5, cmp.getEmail());
			pstm.setString(6, cmp.getCompany_website());
			pstm.setInt(7, cmp.getCompany_tin());
			pstm.execute();
			
		}catch(Exception e)
		{
			System.out.println("Error in ComapnyDB.updateprofile"+e.toString());
			return 1;
		}
		
		return 0;
		
	}
	
	/**
	 * @param jobID
	 * @return The user list applied for that job
	 */
	public ArrayList<User> getApplicantListForAJob(int jobID)
	{
		ArrayList<User> usrList = new ArrayList<User>();
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT * FROM job_applicants where job_id = ?");
			pstm.setInt(1, jobID);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("SELECT * FROM user_details where user_id = ?");
				pstm1.setInt(1, rs.getInt("user_id"));
				ResultSet rs1 = pstm1.executeQuery();
				while(rs1.next())
				{
					User usr = new User();
					usr.setFirst_name(rs1.getString("first_name"));
					usr.setLast_name(rs1.getString("last_name"));
					usr.setAddress(rs1.getString("address"));
					usr.setDob(rs1.getString("dob"));
					usr.setYear_of_experience(rs1.getInt("year_of_experience"));
					usr.setEmail(rs1.getString("email"));
					usr.setUser_imagea_url(rs1.getString("user_image_url"));
					usr.setResume_url(rs1.getString("resume_url"));
					usr.setQualification(rs1.getInt("qualification"));
					usr.setProfile_percentage(rs1.getInt("profile_percentage"));
					usr.setShare_with_company(rs1.getInt("share_with_company"));
					usrList.add(usr);

				}
			}
		}catch(Exception e) {
			System.out.println("Error in ComapnyDB.getApplicantListForAJob"+e.toString());
		}
		return usrList;
		
	}
	
	public ArrayList<Skill> getSkillList(Job jb)
	{
		ArrayList<Skill> skill_list = new ArrayList<Skill>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT * FROM skill_list where skill_id = ?");
			int[] skill_list_ids = jb.getSkill_list();
			for(int i=0; i<skill_list_ids.length; i++)
			{
				pstm.setInt(1, skill_list_ids[i]);
				ResultSet rs = pstm.executeQuery();
				while(rs.next())
				{
					Skill sk = new Skill();
					sk.setSkill_id(skill_list_ids[i]);
					sk.setSkill_title(rs.getString("skill_title"));
					skill_list.add(sk);
				}
			}			
			
		}catch(Exception e) {
			System.out.println("Error in ComapnyDB.getSkillListForAJob"+e.toString());
		}
		
		return skill_list;
	}
	
	/**
	 * @param job
	 * @return job with all the details
	 */
	public Job getJobDetails(Job jb)
	{
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT * FROM job_list where job_id=?");
			pstm.setInt(1, jb.getJob_id());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
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
			}
		}catch(Exception e)
		{
			System.out.println("error in CompanyDB.getJobDetails"+e.getMessage());
		}
		return jb;
	}

}
