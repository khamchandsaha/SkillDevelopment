package com.technoforensis.skilldevelopment.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.technoforensis.skilldevelopment.model.Company;
import com.technoforensis.skilldevelopment.model.Job;

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
		ujob.setApplication_fee(Integer.parseInt(request.getParameter("applicationFee")));
		ujob.setExperience_required(Integer.parseInt(request.getParameter("expRequired")));
		ujob.setJob_description(request.getParameter("jobDescription"));
		ujob.setJob_location(request.getParameter("jobLocation"));
		ujob.setLast_date(request.getParameter("lastDate"));
		ujob.setStart_date(request.getParameter("startDate"));
		
		doGet(request, response);
	}

}
