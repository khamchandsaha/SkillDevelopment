package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.technoforensis.skilldevelopment.model.Company;
import com.technoforensis.skilldevelopment.model.Institution;
import com.technoforensis.skilldevelopment.utility.DBConnection;

public class InstituteDB {
	
	public int getInstituteID(String user_name)
	{
		int user_id = 0;
		try
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("SELECT member_id FROM login WHERE user_name = ?");
			pstm.setString(1, user_name);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				user_id = Integer.parseInt(rs.getString("member_id"));
			}
			
		}catch(Exception e)
		{
			System.out.println("Error in InstituteDB.getInstituteID"+e.toString());
		}
		return user_id;
	}
	
	public void newRegister(Institution ins,String hashCode)
	{
		try 
		{
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement("INSERT INTO login (user_name,password,role_id) VALUES (?,?,?)");
			pstm.setString(1,ins.getMobile());
			pstm.setString(2,hashCode);
			pstm.setInt(3,3); //role 3 for company
			pstm.execute();	
			
			int ins_id = getInstituteID(ins.getMobile());
			if(ins_id!=0)
			{
				PreparedStatement pstm1 = (PreparedStatement) conn.prepareStatement("INSERT INTO institute_details (institute_id,institute_name,mobile,registration,email) VALUES (?,?,?,?,?)");
				pstm1.setInt(1, ins_id);
				pstm1.setString(2,ins.getInstitute_name());
				pstm1.setString(3,ins.getMobile());
				pstm1.setString(4, ins.getRegistration());
				pstm1.setString(5, ins.getEmail());
				pstm1.execute();
			}
			
				
		}catch (Exception e)
		{
			System.out.println("e : in InstituteDB.newRegister "+e.toString());
		}
	}

}
