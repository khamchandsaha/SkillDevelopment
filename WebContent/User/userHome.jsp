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
  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<% 
	String firstName="";
	String lastName="";
	ArrayList<Job> job_list = new ArrayList<Job>();
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
		
		
		BasicDBUtility bdu = new BasicDBUtility();
		job_list = bdu.getJobList();
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
        <li><a href="<%=path %>/User/userProfile.jsp"><i class="fa fa-link"></i> <span>Profile</span></a></li>
        <li><a href="<%=path %>/User/userJobHis.jsp"><i class="fa fa-link"></i> <span>Job History</span></a></li>
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
	<div class="col-lg-6">
      		<!-- /.tab-pane -->
              <div class="tab-pane" id="timeline">
                <!-- The timeline -->
                <ul class="timeline timeline-inverse">
                  <!-- timeline time label -->
                  <li class="time-label">
                        <span class="bg-red">
                          Job List
                        </span>
                  </li>
                  <!-- /.timeline-label -->
                  <!-- timeline item -->
                  <!-- timeline item -->
                  <%
                  	UserDB usr_database = new UserDB();
                  	for(int i=job_list.size()-1; i>=0; i--)
                  	{
                  		Job jb = new Job();
                  		jb = job_list.get(i);
                  		Company cmp = new Company();
                  		cmp.setCompany_id(jb.getCompany_id());
                  		CompanyDB database = new CompanyDB();
                  		cmp = database.getCompanyDetails(cmp);
                  		ArrayList<Skill> skill_list = new ArrayList<Skill>();
                  		skill_list = database.getSkillList(jb);
                  %>
            <li>
              <i class="fa fa-envelope bg-blue"></i>

              <div class="timeline-item">
              

                <h3 class="timeline-header"><a href="#"><%=cmp.getCompany_name() %></a> Posted A Job: <span style="color:#3c8dbc"> <%=jb.getJob_title() %></span></h3>

                <div class="timeline-body">
                <%=jb.getJob_description() %>
                </br>
                <span style="color:#3c8dbc">Experience Required</span> : <%=jb.getExperience_required() %> in Years
                </br>
                <span style="color:#3c8dbc">Start Date</span> : <%=jb.getStart_date() %>
                </br>
                <span style="color:#3c8dbc">Last Date</span> : <%=jb.getLast_date() %>
                </br>
                <span style="color:#3c8dbc">Application Fee</span> : <%=jb.getApplication_fee() %>
                </br>
                <span style="color:#3c8dbc">Job Location</span> : <%=jb.getJob_location() %>
                </br>
                <span style="color:#3c8dbc">Skills Required</span> : <% 
                	for(int j=0; j<skill_list.size(); j++)
                	{
                		Skill sk = new Skill();
                		sk = skill_list.get(j);
                		out.print(sk.getSkill_title()+"  ");
                	}
                %>
                </div>
                   
                 <div class="timeline-footer"> 
                  <% 
                  		if(job_his.contains(jb))
                  		{
                  %>
                  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#<%=jb.getJob_id()%> disabled" >
                Already Applied
              </button>
              	<%
                  		}
                  		else
                  		{
              	%>
              	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#<%=jb.getJob_id()%>" >
                Apply Here
              </button>
              <%
                  		}
              %>
                </div>
              </div>
            </li>
            
  		<div class="modal modal-info fade" id="<%=jb.getJob_id()%>">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Confirm you Job Apply</h4>
              </div>
              <div class="modal-body">
                <p>By Confirming You are agree to share your information with Company.</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                <form action="<%=path %>/JobApplyServlet" method="post">
                <input name="job_id" value="<%=jb.getJob_id()%>" type="hidden">
                <button type="submit" class="btn btn-outline">Confirm</button>
                </form>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
            
            <%
                  	}
            %>


             <!-- END timeline item -->
                 <!-- timeline time label -->
                  <li class="time-label">
                        <span class="bg-green">
                          3 Jan. 2014
                        </span>
                  </li>
                  <!-- /.timeline-label -->
                  <!-- timeline item -->
                  <li>
              <i class="fa fa-envelope bg-blue"></i>

              <div class="timeline-item">
              

                <h3 class="timeline-header"><a href="#">UPL ltd</a> Posted A Job</h3>

                <div class="timeline-body">
                  We are looking for Pump Operators / Electricians / Fitters for our storm water pumping stations located in Delhi.
                </div>
                
                 <div class="timeline-footer">
                  <a class="btn btn-primary btn-xs">Apply Here</a>
                 
                </div>
              </div>
            </li>
                  <!-- END timeline item -->
                  <li>
                    <i class="fa fa-clock-o bg-gray"></i>
                  </li>
                </ul>
              </div>
              <!-- /.tab-pane -->
              </div> <!-- column finished -->
              <div class="col-md-4 pull-right">
          <div class="box box-primary">
            <div class="box-header">
              <i class="ion ion-clipboard"></i>

              <h3 class="box-title">POPULAR SKILLSETS</h3>

              <div class="box-tools pull-right">
               
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <ul class="todo-list">
                <li>
                  <!-- drag handle -->
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <!-- checkbox -->
                
                  <!-- todo text -->
                  <span class="text"><a href="UserServlet?action=viewscada">SCADA</a></span>
                  <!-- Emphasis label -->
                  <small class="label label-danger"><i class="fa fa-clock-o"></i> 10 Jobs</small>
                  <!-- General tools such as edit or delete-->
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
        
                  <span class="text"> Chemist </span>
                  <small class="label label-info"><i class="fa fa-clock-o"></i> 7 Jobs</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  
                  <span class="text"> Production Engineering </span>
                  <small class="label label-warning"><i class="fa fa-clock-o"></i> 5 Jobs</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                 
                  <span class="text"> Chemical Plant Maintainance</span>
                  <small class="label label-success"><i class="fa fa-clock-o"></i> 3 Jobs</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
             
                  <span class="text">Safety</span>
                  <small class="label label-primary"><i class="fa fa-clock-o"></i> 1 Jobs</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                
              </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix no-border">
             
            </div>
          </div>
         </div> 
         </div> <!-- row finished -->

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

</body>
</html>