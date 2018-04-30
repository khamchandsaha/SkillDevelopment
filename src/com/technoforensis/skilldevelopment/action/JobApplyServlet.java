package com.technoforensis.skilldevelopment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.technoforensis.skilldevelopment.database.BasicDBUtility;
import com.technoforensis.skilldevelopment.model.User;
import com.technoforensis.skilldevelopment.utility.SMSSender1;

/**
 * Servlet implementation class JobApplyServlet
 */
@WebServlet("/JobApplyServlet")
public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append("Error: GENT Will not be accepted");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User usr = new User();
		HttpSession session = request.getSession();
		usr = (User) session.getAttribute("user");
		int job_id;
		job_id = Integer.parseInt(request.getParameter("job_id"));
		BasicDBUtility bdu = new BasicDBUtility();
		bdu.addJobApplicant(usr.getUser_id(), job_id);
		request.getRequestDispatcher("User/userHome.jsp").forward(request, response);
		SMSSender1 sms = new SMSSender1();
		String msg;
		msg = "Dear "+usr.getFirst_name()+" "+usr.getLast_name()+" you have successfully applied for the job..\nThank you \nTeam Technoforensis";
		sms.sendSms(usr.getMobile(), msg);
		
	}

}
