package com.technoforensis.skilldevelopment.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.technoforensis.skilldevelopment.appsecurity.HashAlgorithm;
import com.technoforensis.skilldevelopment.database.BasicDBUtility;
import com.technoforensis.skilldevelopment.database.CompanyDB;
import com.technoforensis.skilldevelopment.database.UserDB;
import com.technoforensis.skilldevelopment.model.Company;
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
				
				UserDB database = new UserDB();
				int user_id;
				user_id = database.getUserID(user_name);
				User usr = new User();
				usr.setMobile(user_name);
				usr.setUser_id(user_id);				
				//This will assign a user object containing all the info of user
				usr = database.getUserDetails(usr);
				HttpSession session = request.getSession();
				session.setAttribute("user", usr);
				response.sendRedirect("User/userHome.jsp");
			}
			
			else if(role_id== 2)
			{
				CompanyDB database = new CompanyDB();
				int com_id;
				com_id = database.getCompanyID(user_name);
				Company cmp = new Company();
				cmp.setMobile(user_name);
				cmp.setCompany_id(com_id);
				cmp = database.getCompanyDetails(cmp);
				HttpSession session = request.getSession();
				session.setAttribute("company", cmp);
				request.getRequestDispatcher("Company/companyHome.jsp").forward(request, response);
			}
			else
			{
				response.sendRedirect("error.jsp");
			}
			
		}
		else
		{
			response.sendRedirect("error.jsp");
		}
		doGet(request, response);
	}

}
