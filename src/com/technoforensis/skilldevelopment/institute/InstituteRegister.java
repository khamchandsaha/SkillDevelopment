package com.technoforensis.skilldevelopment.institute;

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
import com.technoforensis.skilldevelopment.database.InstituteDB;
import com.technoforensis.skilldevelopment.model.Company;
import com.technoforensis.skilldevelopment.model.Institution;

/**
 * Servlet implementation class InstituteRegister
 */
@WebServlet("/InstituteRegister")
public class InstituteRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstituteRegister() {
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
		String institutionName = (String) session.getAttribute("institutionName");
		String mobileNumber = (String) session.getAttribute("mobileNumber");
		String password = (String) session.getAttribute("password");
		String insRegNum = (String) session.getAttribute("insRegNum");
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
			Institution ins = new Institution();
			ins.setInstitute_name(institutionName);
			ins.setMobile(mobileNumber);
			ins.setEmail(email);
			ins.setRegistration(insRegNum);
			InstituteDB dataBase = new InstituteDB();
			//Store in Database
			dataBase.newRegister(ins, hashCode);
			session.setAttribute("institute", ins);
			request.getRequestDispatcher("Institution/institutionHome.jsp").forward(request, response);
			
		}
		else
		{
			Institution ins = new Institution();
			ins.setInstitute_name(institutionName);
			ins.setMobile(mobileNumber);
			ins.setEmail(email);
			ins.setRegistration(insRegNum);
			session.setAttribute("institute", ins);
			request.getRequestDispatcher("errorInstituteOTP.jsp").forward(request, response);
		}
	}

}
