package com.technoforensis.skilldevelopment.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.technoforensis.skilldevelopment.database.BasicDBUtility;
import com.technoforensis.skilldevelopment.database.CompanyDB;
import com.technoforensis.skilldevelopment.model.*;
import com.technoforensis.skilldevelopment.utility.SMSSender1;

/**
 * Servlet implementation class JobPostServlet
 */
@WebServlet("/JobPostServlet")
public class JobPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobPostServlet() {
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
		Job ujob = new Job();
		HttpSession session = request.getSession();
		Company cmp = (Company) session.getAttribute("company");
		ujob.setJob_title(request.getParameter("jobTitle"));
		ujob.setCompany_id(cmp.getCompany_id());
		ujob.setApplication_fee(Integer.parseInt(request.getParameter("application_fee")));
		ujob.setExperience_required(Integer.parseInt(request.getParameter("experience_required")));
		ujob.setJob_description(request.getParameter("jobDescription"));
		ujob.setJob_location(request.getParameter("job_location"));
		int skill_list_ids[] = new int[10];
		String skill_list[];
		skill_list = request.getParameterValues("skill_list");
		for(int i=0; i<skill_list.length; i++)
		{
			skill_list_ids[i]=Integer.parseInt(skill_list[i]);
		}
		ujob.setSkill_list(skill_list_ids);
		ujob.setQualification_id(Integer.parseInt(request.getParameter("qualification")));
		ujob.setLast_date(request.getParameter("last_date"));
		ujob.setStart_date(request.getParameter("start_date"));	
		
		CompanyDB database = new CompanyDB();
		database.jobPost(ujob, cmp);
		SMSSender1 sms = new SMSSender1();
		String msg;
		msg = "Dear "+cmp.getCompany_name()+" your job has been successfully posted.\nThank you \nTeam Technoforensis";
		String result = sms.sendSms(cmp.getMobile(), msg);
		request.getRequestDispatcher("Company/companyHome.jsp").forward(request, response);
	}

}
