package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.technoforensis.skilldevelopment.model.User;
import com.technoforensis.skilldevelopment.utility.DBConnection;

/**
 * @author khamchand
 * This class will be used to store/retrieve user info from database
 */
public class UserDB {
	
	/**
	 * @param user_name
	 * @return user_id
	 */
	public int getUserID(String user_name)
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
			System.out.println("Error in UserDB.getUserID"+e.toString());
		}
		return user_id;
	}
	
	/**
	 * @param usr
	 * @param hashCode
	 * This method will be called for user registration
	 */
	public void newRegister(User usr,String hashCode)
	{
		try 
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("INSERT INTO login (user_name,password,role_id) VALUES (?,?,?)");
			pstm.setString(1,usr.getMobile());
			pstm.setString(2,hashCode);
			pstm.setInt(3,1);
			pstm.execute();	
			
			int user_id = getUserID(usr.getMobile());
			if(user_id!=0)
			{
				PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("INSERT INTO user_details (user_id,first_name,last_name,mobile) VALUES (?,?,?,?)");
				pstm1.setInt(1, user_id);
				pstm1.setString(2,usr.getFirst_name());
				pstm1.setString(3,usr.getLast_name());
				pstm1.setString(4,usr.getMobile());
				pstm1.execute();
			}
			
				
		}catch (Exception e)
		{
			System.out.println("e : in UserDB.newRegister "+e.toString());
		}
	}
	
	/**
	 * @param usr
	 * @return usr 
	 * The return object user contains all the details of the user
	 */
	public User getUserDetails(User usr)
	{
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("select * from user_details where user_id = ?");
			pstm.setInt(1, usr.getUser_id());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				usr.setFirst_name(rs.getString("first_name"));
				usr.setLast_name(rs.getString("last_name"));
				usr.setAddress(rs.getString("address"));
				usr.setDob(rs.getString("dob"));
				usr.setYear_of_experience(rs.getInt("year_of_experience"));
				usr.setEmail(rs.getString("email"));
				usr.setUser_imagea_url(rs.getString("user_image_url"));
				usr.setResume_url(rs.getString("resume_url"));
				usr.setQualification(rs.getInt("qualification"));
				usr.setProfile_percentage(rs.getInt("profile_percentage"));
				usr.setShare_with_company(rs.getInt("share_with_company"));

			}
			
		}catch(Exception e)
		{
			System.out.println("Error in UserDB.getUserID"+e.toString());
		}
		
		return usr;
	}

}
