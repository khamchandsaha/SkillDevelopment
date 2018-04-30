package com.technoforensis.skilldevelopment.company;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class CompanyRegister
 */
@WebServlet("/CompanyRegister")
public class CompanyRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyRegister() {
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
		HttpSession session =request.getSession();
		//Parameters from requesting client
		String companyName = (String) session.getAttribute("companyName");
		String mobileNumber = (String) session.getAttribute("mobileNumber");
		String password = (String) session.getAttribute("password");
		String companyRegNum = (String) session.getAttribute("companyRegNum");
		String email = (String) session.getAttribute("email");
		int gen_otp = (int) session.getAttribute("generated_otp");
		
		int sub_otp = Integer.parseInt(request.getParameter("submited_otp"));
		PrintWriter out = response.getWriter();				
		//Generating Hash Code
		HashAlgorithm hash_algo = new HashAlgorithm();		
		String hashCode = hash_algo.createHash(password);
		
		BasicDBUtility dbu = new BasicDBUtility();
		if(dbu.checkMember(mobileNumber))
		{
			request.getRequestDispatcher("existingMember.jsp").forward(request, response);
		}
						
		if(sub_otp == gen_otp)
		{
			if(dbu.checkMember(mobileNumber))
			{
				request.getRequestDispatcher("existingMember.jsp").forward(request, response);
			}
			Company cmp = new Company();
			cmp.setCompany_name(companyName);
			cmp.setMobile(mobileNumber);
			cmp.setEmail(email);
			cmp.setRegistration(companyRegNum);
			CompanyDB dataBase = new CompanyDB();
			//Store in Database
			dataBase.newRegister(cmp, hashCode);
			session.setAttribute("company", cmp);
			request.getRequestDispatcher("Company/companyHome.jsp").forward(request, response);
			
		}
		else
		{
			Company cmp = new Company();
			cmp.setCompany_name(companyName);
			cmp.setMobile(mobileNumber);
			cmp.setEmail(email);
			cmp.setRegistration(companyRegNum);
			session.setAttribute("company", cmp);
			request.getRequestDispatcher("errorCompanyOTP.jsp").forward(request, response);
		}
	}

}
