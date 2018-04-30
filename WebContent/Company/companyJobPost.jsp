<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.technoforensis.skilldevelopment.model.*" %>
<%@ page import="com.technoforensis.skilldevelopment.database.*" %>
<%@ page import= "java.util.ArrayList" %>
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
    <!-- Select2 -->
  <link rel="stylesheet" href="<%=path %>/bower_components/select2/dist/css/select2.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=path %>/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="<%=path %>/dist/css/skins/skin-blue.min.css">
    <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="<%=path %>/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<% 
	String companyName="";
	Company cmp = new Company();
	ArrayList<Skill> skill_list = new ArrayList<Skill>();
	ArrayList<Qualification> q_list = new ArrayList<Qualification>();
	try
	{
		cmp = (Company) session.getAttribute("company");
		companyName = cmp.getCompany_name();		
		BasicDBUtility bdu = new BasicDBUtility();
		skill_list = bdu.getSkillListForJob();
		q_list = bdu.getQualificationList();
	}catch(Exception e) 
	{
		response.sendRedirect(path+"/index.jsp");
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
              <img src="<%= path %>/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><% out.print(companyName); %></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="<%= path %>/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="userProfile.jsp" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="User/userLogout.jsp" class="btn btn-default btn-flat">Sign out</a>
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
          <p><%=companyName %></p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
        
      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <!-- defferent links to access the web app -->
        <li class="active"><a href="companyHome.jsp"><i class="fa fa-link"></i> <span>Dashboard</span></a></li>
        <li><a href="companyProfile.jsp"><i class="fa fa-link"></i> <span>Profile</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Post A JOB</span></a></li>
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
    <div class="col-lg-offset-3 col-lg-6">
	<!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h2 style="text-align: center;">Post A JOB</h2>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" action="<%=path %>/JobPostServlet" method="post">
              <div class="box-body">
                <div class="form-group">
                  <label>Job Title</label>
                  <input type="text" name="jobTitle" class="form-control" placeholder="Job Title">
                </div>
                <div class="form-group">
                  <label>Job Description</label>
                  <textarea  name="jobDescription" class="form-control" placeholder="Job Description"></textarea>
                </div>
                <div class="form-group">
                  <label>Experience</label>
                  <input type="text" name="experience_required" class="form-control" placeholder="Experience Required in Years">
                </div>
                <div class="form-group">
                  <label>Application Fee</label>
                  <input type="text" name="application_fee" class="form-control" placeholder="Application Fee">
                </div>
                <div class="form-group">
                <label>Job Location</label>
                <select class="form-control select2" name="job_location" style="width: 100%;" >
                  <option value="Guwahati">Guwahati</option>
                  <option value="Kolkata">Kolkata</option>
                  <option value="New Delhi">New Delhi</option>
                  <option value="Bengaluru">Bengaluru</option>
                  <option value="Tezpur">Tezpur</option>
                  <option value="Jorhat">Jorhat</option>
                  <option value="Shillong">Shillong</option>
                </select>
              </div>
              <div class="form-group">
                <label>Minimum Qualification</label>
                <select  name="qualification" class="form-control select2"  data-placeholder="Minimum Qualification Required for the Job"
                        style="width: 100%;">
                  <%
                  		for(int i=0; i<q_list.size(); i++)
                  		{
                  			Qualification qua = new Qualification();
                  			qua = q_list.get(i);
                  			out.print("<option value="+qua.getQualification_id()+">"+qua.getQualification()+"</option>");
                  		}
                  %>
                  
                </select>
              </div>
              <div class="form-group">
                <label>Skills Required</label>
                <select  name="skill_list" class="form-control select2" multiple="multiple" data-placeholder="Select Skills Required for the Job"
                        style="width: 100%;">
                  <%
                  		for(int i=0; i<skill_list.size(); i++)
                  		{
                  			Skill sk = new Skill();
                  			sk = skill_list.get(i);
                  			out.print("<option value="+sk.getSkill_id()+">"+sk.getSkill_title()+"</option>");
                  		}
                  %>
                  
                </select>
              </div>
                <div class="form-group">
                  <label>Start Date</label>
                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" id="startDate" name="start_date">
                </div>
              </div>
              <div class="form-group">
                  <label>Last Date</label>
                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" class="form-control pull-right" id="lastDate" name="last_date">
                </div>
              </div>
                
                </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
              </div>
            </form>
          </div>
          </div> <!--  col end here -->
          </div> <!-- row end here -->
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
<!-- bootstrap datepicker -->
<script src="<%=path %>/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- Select2 -->
<script src="<%=path %>/bower_components/select2/dist/js/select2.full.min.js"></script>
<script>
//Date picker
$('#startDate').datepicker({
  autoclose: true
})
$('#lastDate').datepicker({
  autoclose: true
})
    $('.select2').select2()
</script>
</body></html>