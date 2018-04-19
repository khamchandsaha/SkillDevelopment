package com.technoforensis.skilldevelopment.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.technoforensis.skilldevelopment.appsecurity.HashAlgorithm;
import com.technoforensis.skilldevelopment.database.UserDB;
import com.technoforensis.skilldevelopment.model.User;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session =request.getSession();
		//Parameters from requesting client
		String firstName = (String) session.getAttribute("firstName");
		String lastName = (String) session.getAttribute("lastName");
		String mobileNumber = (String) session.getAttribute("mobileNumber");
		String password = (String) session.getAttribute("password");
		int gen_otp = (int) session.getAttribute("generated_otp");
		
		int sub_otp = Integer.parseInt(request.getParameter("submited_otp"));
		PrintWriter out = response.getWriter();				
		//Generating Hash Code
		HashAlgorithm hash_algo = new HashAlgorithm();		
		String hashCode = hash_algo.createHash(password);
		out.println(hashCode);
		
		//test
				
		if(sub_otp == gen_otp)
		{
			out.println(firstName);
			out.println(lastName);
			out.println(mobileNumber);
			out.println(password);
			User usr = new User();
			usr.setFirst_name(firstName);
			usr.setLast_name(lastName);
			usr.setMobile(mobileNumber);
			UserDB dataBase = new UserDB();
			//Store in Database
			dataBase.newRegister(usr, hashCode);			
		}
		
	}

}
