<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.technoforensis.skilldevelopment.model.*" %>
<%@ page import="com.technoforensis.skilldevelopment.database.*" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.sql.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Welcome Home</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <% String path = request.getContextPath(); %>
  <link rel="stylesheet" href="<%=path %>/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=path %>/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<%=path %>/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=path %>/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="<%=path %>/dist/css/skins/skin-blue.min.css">
  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<% 
	String firstName="";
	String lastName="";
	ArrayList<Job> job_his = new ArrayList<Job>();	
	User usr = new User();
	try
	{
		
		usr = (User) session.getAttribute("user");
		 firstName = usr.getFirst_name();
		 lastName = usr.getLast_name();
		if(lastName == null)
		{
			lastName ="";
		}
		UserDB usr_database = new UserDB();
		job_his = usr_database.getJobHis(usr);
	}catch(Exception e)
	{
		System.out.println(e.toString());
		response.sendRedirect("../index.jsp");
	}
	
%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>T</b>F</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Technoforensis</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">

          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="<%=path %>/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><% out.print(firstName+" "+lastName); %></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="<%=path %>/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="userProfile.jsp" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="<%=path %>/User/userLogout.jsp" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<%=path %>/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><% out.print(firstName+" "+lastName); %></p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
        
      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <!-- defferent links to access the web app -->
        <li class="active"><a href="userHome.jsp"><i class="fa fa-link"></i> <span>Dashboard</span></a></li>
        <li><a href="userProfile.jsp"><i class="fa fa-link"></i> <span>Profile</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content container-fluid">

	     <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Your Job History</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
              <table class="table table-hover">
                <tr>
                  <th>JOB TITLE</th>
                  <th>COMPANY</th>
                  <th>DATE</th>
                  <th>STATUS</th>
                </tr>
                <%
                	for(int i=0; i<job_his.size(); i++)
                	{
                		Job jb = new Job();
                		jb = job_his.get(i);
                		Company cmp = new Company();
                  		cmp.setCompany_id(jb.getCompany_id());
                  		CompanyDB database = new CompanyDB();
                  		cmp = database.getCompanyDetails(cmp);
                  		BasicDBUtility bdu = new BasicDBUtility();
                  		Date date = bdu.getJobApplyDate(usr.getUser_id(), jb.getJob_id());
                  		
                %>
                <tr>
                  <td><%=jb.getJob_title()%></td>
                  <td><%=cmp.getCompany_name() %></td>
                  <td><%=date%></td>
                  <td><span class="label label-success">Approved</span></td>
                </tr>
                <%
                	}
                %>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      Sponsored by Ministry of Chemical and Petrochemical
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2018 <a href="#">Technoforensis</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="<%=path %>/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=path %>/dist/js/adminlte.min.js"></script>
<!-- Slimscroll -->
<script src="<%=path %>/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=path %>/bower_components/fastclick/lib/fastclick.js"></script>
</body>
</html>