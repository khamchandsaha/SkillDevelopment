<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.technoforensis.skilldevelopment.utility.*" %>
<%@ page import="com.technoforensis.skilldevelopment.database.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Registration Page</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <% String path = request.getContextPath(); %>
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<%=path %>/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=path %>/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<%=path %>/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=path %>/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="<%=path %>/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body>
<%
	BasicDBUtility dbu = new BasicDBUtility();
	String firstName="",lastName="",mobileNumber="",password;
	try
	{
		mobileNumber = request.getParameter("mobileNumber");
		session.setAttribute("mobileNumber", mobileNumber);
	}catch (Exception e){
		response.sendRedirect("index.jsp");
	}
	if(dbu.checkMember(mobileNumber))
	{
		request.getRequestDispatcher("existingMember.jsp").forward(request, response);
	}
	else{

	try{
	firstName = request.getParameter("firstName");
	lastName = request.getParameter("lastName");
	password = request.getParameter("password");
	session.setAttribute("firstName", firstName);
	session.setAttribute("lastName", lastName);
	session.setAttribute("mobileNumber", mobileNumber);
	session.setAttribute("password", password);
	
	OTP_Gen otp_gen = new OTP_Gen();
	int otp = otp_gen.gen_otp();
	
	session.setAttribute("generated_otp", otp);
	SMSSender1 sms = new SMSSender1();
	String msg;
	msg = "Dear "+firstName+" Your OTP is: "+otp;
	String result = sms.sendSms(mobileNumber, msg);
	session.setAttribute("sms_result", result);
	}
	catch (Exception e){
		response.sendRedirect("index.jsp");
	}
	}
%>

<div class="register-box">
  <div class="register-logo">
    <a href="<%=path %>/index.jsp"><b>Welcome</b>USER</a>
  </div>

  <div class="register-box-body">
    <p class="login-box-msg">Please Enter the OTP you received in your Mobile Number</p>

    <form action="UserRegister" method="post">
      <div class="form-group has-feedback">
        <label class="form-control">+91<%=mobileNumber %>
        <span class="glyphicon glyphicon-phone form-control-feedback"></span></label>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Enter the OTP" name="submited_otp">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
         <button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
    </form>
</body>
</html>