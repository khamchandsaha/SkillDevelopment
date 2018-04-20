package com.technoforensis.skilldevelopment.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.technoforensis.skilldevelopment.appsecurity.HashAlgorithm;
import com.technoforensis.skilldevelopment.database.BasicDBUtility;
import com.technoforensis.skilldevelopment.database.UserDB;
import com.technoforensis.skilldevelopment.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		BasicDBUtility dbu = new BasicDBUtility();
		
		//check whether the user is present or not
		if(dbu.checkMember(user_name) == true)
		{
			//Generating Hash Code
			HashAlgorithm hash_algo = new HashAlgorithm();		
			String hashCode = hash_algo.createHash(password);
			//Geting the role_id
			int role_id = dbu.authMember(user_name, hashCode);
			
			//role_id 1 denotes individual user
			if(role_id == 1)
			{
				System.out.println("The user is present");
				UserDB database = new UserDB();
				int user_id;
				user_id = database.getUserID(user_name);
				User usr = new User();
				usr.setMobile(user_name);
				usr.setUser_id(user_id);				
				//This will assign a user object containing all the info of user
				usr = database.getUserDetails(usr);
				System.out.println("The user name is: "+usr.getFirst_name()+" "+usr.getLast_name());
			}
			
		}
		else
		{
			System.out.println("user is not presesnt");
		}
		doGet(request, response);
	}

}
