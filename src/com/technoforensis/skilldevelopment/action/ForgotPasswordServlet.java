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
import com.technoforensis.skilldevelopment.database.UserDB;
import com.technoforensis.skilldevelopment.model.User;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
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
		HttpSession session =request.getSession();
		String mobileNumber = (String) session.getAttribute("mobileNumber");
		String password = (String) request.getParameter("password");
		int gen_otp = (int) session.getAttribute("generated_otp");
		
		int sub_otp = Integer.parseInt(request.getParameter("submited_otp"));
		//Generating Hash Code
		HashAlgorithm hash_algo = new HashAlgorithm();		
		String hashCode = hash_algo.createHash(password);
		if(sub_otp == gen_otp)
		{
			BasicDBUtility bdu = new BasicDBUtility();
			bdu.updatePassword(mobileNumber, hashCode);
			request.getRequestDispatcher("updatePassThank.jsp").forward(request, response);
			
		}
		else
		{
			request.getRequestDispatcher("errorForgotPass.jsp").forward(request, response);
		}
	}

}
