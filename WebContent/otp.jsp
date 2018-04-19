<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.technoforensis.skilldevelopment.utility.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String mobileNumber = request.getParameter("mobileNumber");
	String password = request.getParameter("password");
	session.setAttribute("firstName", firstName);
	session.setAttribute("lastName", lastName);
	session.setAttribute("mobileNumber", mobileNumber);
	session.setAttribute("password", password);
	
	OTP_Gen otp_gen = new OTP_Gen();
	int otp = otp_gen.gen_otp();
	
	session.setAttribute("generated_otp", otp);
	SMSSender1 sms = new SMSSender1();
	String msg;
	msg = "Your OTP is: "+otp;
	String result = sms.sendSms(mobileNumber, msg);
	session.setAttribute("sms_result", result);
%>
<form action="UserRegister" method="post">
	Phone: <% out.print(mobileNumber); %><br>
	OTP:
	<input type="text" name="submited_otp"><br>
	<button type="submit">Submit</button>
</form>
</body>
</html>