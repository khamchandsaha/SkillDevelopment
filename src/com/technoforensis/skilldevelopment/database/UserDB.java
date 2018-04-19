package com.technoforensis.skilldevelopment.database;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;
import com.technoforensis.skilldevelopment.model.User;
import com.technoforensis.skilldevelopment.utility.DBConnection;

public class UserDB {
	
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
				
			}catch (Exception e)
			{
				System.out.println("e : in UserDB.newRegister "+e.toString());
			}
	}

}
