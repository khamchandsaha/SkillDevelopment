<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.technoforensis.skilldevelopment.utility.*" %>
<%@ page import="com.technoforensis.skilldevelopment.model.User" %>
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

<div class="register-box">
  <div class="register-logo">
    <a href="<%=path %>/index.jsp"><b>SELECT</b>MEMBER TYPE</a>
  </div>

  <div class="register-box-body">
  	<form">
  	 <div class="form-group">
     	<a href="userRegistration.jsp" class="btn btn-primary btn-lg active" role="button">USER</a>
     </div>
     <div class="form-group">
     	<a href="companyRegistration.jsp" class="btn btn-primary btn-lg active" role="button">COMPANY</a>
     	</div>
     <div class="form-group">
     	<a href="institutionRegistration.jsp" class="btn btn-primary btn-lg active" role="button">INSTITUTE</a>
     	</div>
     </form>
</body>
</html>