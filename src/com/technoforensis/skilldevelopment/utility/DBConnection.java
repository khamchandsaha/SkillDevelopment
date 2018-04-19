package com.technoforensis.skilldevelopment.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	//for dB connection 

	private static Connection connection = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/skill_portal";
	//static final String DB_URL = "jdbc:mysql://aa1joq7jhd69aqu.cecswnovjdox.ap-south-1.rds.amazonaws.com:3306/fyndoc";
	
	//Database credentials
	static final String USER ="root";
	
	static final String PASS ="326445";
	
	public static Connection getConnection(){
		
			try{
				//register driver
				Class.forName(JDBC_DRIVER);
				
				//register or open a connection with driver manager
				connection = DriverManager.getConnection(DB_URL,USER,PASS);
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			return connection;
		}
	}
