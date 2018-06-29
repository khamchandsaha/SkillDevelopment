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
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=path %>/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="<%=path %>/dist/css/skins/skin-blue.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="<%=path %>/bower_components/select2/dist/css/select2.min.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="<%=path %>/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<% 
	String firstName="";
	String lastName="";
	User usr = new User();
	ArrayList<Qualification> q_list = new ArrayList<Qualification>();
	ArrayList<Skill> skill_list = new ArrayList<Skill>();
	try
	{
		usr = (User) session.getAttribute("user");
		firstName = usr.getFirst_name();
		lastName = usr.getLast_name();
		if(lastName == null)
		{
			lastName ="";
		}
		BasicDBUtility bdu = new BasicDBUtility();
		q_list = bdu.getQualificationList();
		skill_list = bdu.getSkillListForJob();
	}catch(Exception e)
	{
		request.getRequestDispatcher("../index.jsp").forward(request, response);
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
              <img src="<%=path+"/File/User/Profile/"+usr.getUser_id()+"_profile_pic.jpg" %>" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><% out.print(firstName+" "+lastName); %></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="<%=path+"/File/User/Profile/"+usr.getUser_id()+"_profile_pic.jpg" %>" class="img-circle" alt="User Image">
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="userProfile.jsp" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="userLogout.jsp" class="btn btn-default btn-flat">Sign out</a>
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
          <img src="/File/User/Profile/uday.jpg" class="img-circle" alt="User Image">
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
        <li><a href="userHome.jsp"><i class="fa fa-link"></i> <span>Dashboard</span></a></li>
        <li class="active"><a href="userProfile.jsp"><i class="fa fa-link"></i> <span>Profile</span></a></li>
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
    <div class="tab-pane" id="settings">
    			<div class="row">
    			<div class="col-lg-offset-3 col-lg-6">
    			<!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="<%=path+"/File/User/Profile/"+usr.getUser_id()+"_profile_pic.jpg" %>" alt="User profile picture">
				<form action="<%=path %>/UserProfileImgServlet" method="post" enctype="multipart/form-data">
				Change Profile Image
				<input type="file" name="img">
				<button type="submit" class="btn btn-primary">Submit</button>
				</form>
              <h3 class="profile-username text-center"><% out.print(firstName+" "+lastName); %></h3>
            </div>
            </div> <!-- column end here -->
            </div> <!-- row end here -->
            <!-- /.box-body -->
          </div>
          <!-- Starting of form information -->
          <div class="row">
          <div class="col-lg-offset-3 col-lg-5">
                <form class="form-horizontal" action="UserProfileUpdateServlet" method="post">
                  <div class="form-group">
                    <label name="firstName" class="col-sm-2 control-label">First Name</label>

                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="firstName" value="<% out.print(firstName); %>">
                    </div>
                  </div>
                  <div class="form-group">
                    <label name="lastName" class="col-sm-2 control-label">Last Name</label>

                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="lastName" value="<% out.print(lastName); %>">
                    </div>
                  </div>
                  <div class="form-group">
                    <label name="mobile" class="col-sm-2 control-label">Mobile</label>

                    <div class="col-sm-10">
                      <input type="email" class="form-control" id="email" value="<%=usr.getMobile() %>" disabled>
                    </div>
                  </div>
                  <div class="form-group">
                  	<label class="col-sm-2 control-label">Date of Birth</label>
                	<div class="input-group date col-sm-10">
                  		<div class="input-group-addon">
                    		<i class="fa fa-calendar"></i>
                  		</div>
                  		<input type="text" class="form-control" id="date_of_birth" name="date_of_birth"  placeholder="<%=usr.getDob() %>">
                	</div>
              		</div>
                  <div class="form-group">
                    <label name="email" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-10">
                      <input type="email" class="form-control" id="email" value="<%=usr.getEmail() %>">
                    </div>
                  </div>
                  <div class="form-group">
                <label for="qualification" class="col-sm-2 control-label" name="qualification">Highest Qualification</label>
                <div class="col-sm-10">
                <select  name="qualification" class="form-control select2" style="width: 100%;">
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
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label" name="skills">Skills</label>
                <div class="col-sm-10">
                <select  name="skill_list" class="form-control select2" multiple="multiple" data-placeholder="<%="skills you posses" %>"
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
              </div>
                  
                  <div class="form-group">
                    <label for="address" class="col-sm-2 control-label">Address</label>

                    <div class="col-sm-10">
                      <textarea class="form-control" id="inputExperience" placeholder="<%=usr.getAddress() %>" name="Address"></textarea>
                    </div>
                  </div>
                  <div class="col-sm-2">
                  </div>
                  <div class="form-group col-sm-10">
                  Share your profile information with other companies.
                  <div class="radio">
                    <label>
                      <input type="radio" name="optionsRadios" id="optionsRadios1" value="no" checked>
                      No
                    </label>
                  </div>
                  <div class="radio">
                    <label>
                      <input type="radio" name="optionsRadios" id="optionsRadios2" value="yes">
                      Yes
                    </label>
                  </div>
                </div>

                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" class="btn btn-danger">Submit</button>
                    </div>
                  </div>
                </form>
              </div>
              </div> <!-- End column -->
              </div> <!-- End row -->
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
$('#date_of_birth').datepicker({
  autoclose: true
})
$('.select2').select2()
</script>
</body>
</html>