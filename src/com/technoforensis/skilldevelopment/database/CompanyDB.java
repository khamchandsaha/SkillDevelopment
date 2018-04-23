package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.technoforensis.skilldevelopment.model.Company;
import com.technoforensis.skilldevelopment.model.Job;
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
			pstm.setInt(3,1);
			pstm.execute();	
			
			int company_id = getCompanyID(cmp.getMobile());
			if(company_id!=0)
			{
				PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("INSERT INTO company_details (company_id,company_name,mobile) VALUES (?,?,?)");
				pstm1.setInt(1, company_id);
				pstm1.setString(2,cmp.getCompany_name());
				pstm1.setString(3,cmp.getMobile());
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
			PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("INSERT INTO job_list (job_title,company_id,job_description,experience_required,start_date,last_date,application_fee,job_location) VALUES (?,?,?,?,?,?,?,?)");
			pstm1.setString(1,ujob.getJob_title());
			pstm1.setInt(2, cmp.getCompany_id());
			pstm1.setString(3,ujob.getJob_description());
			pstm1.setInt(4, ujob.getExperience_required());
			pstm1.setString(5, ujob.getStart_date());
			pstm1.setString(6, ujob.getLast_date());
			pstm1.setString(8, ujob.getJob_location());
			pstm1.setInt(7, ujob.getApplication_fee());
			pstm1.execute();
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
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("UPDATE company_details SET company_name=?,established_year=?,address=?,registration=?,email=?,company_website=?,comapany_tin=? where company_id = ?");
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

}
