package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
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

}
