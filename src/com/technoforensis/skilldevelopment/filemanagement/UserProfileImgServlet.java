package com.technoforensis.skilldevelopment.filemanagement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.technoforensis.skilldevelopment.model.User;

/**
 * Servlet implementation class UserProfileImgServlet
 */
@WebServlet("/UserProfileImgServlet")
@MultipartConfig
public class UserProfileImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileImgServlet() {
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
		Part filePart = request.getPart("img"); 
	    //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		HttpSession session =request.getSession();
		User usr = new User();
		usr = (User) session.getAttribute("user");
		String fileName = usr.getUser_id() +"_profile_pic.jpg";
	    InputStream fileContent = filePart.getInputStream();
	    File uploads = new File("C:\\Users\\khamchand\\MyEclipse\\SkillDevelopment\\WebContent\\File\\User\\Profile\\");
	    File file = new File(uploads, fileName);

	    try (InputStream input = filePart.getInputStream()) {
	        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    }
	    request.getRequestDispatcher("User/userProfile.jsp").forward(request, response);
	}

}
