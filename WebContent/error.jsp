<%@ page language="java" contentType="text/html;"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Welcome to Chemical and Petrochemical Portal</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/base_css.css">
	<script type="text/javascript" src="javascript/bootstrap.min.js"></script>

</head>
<body class="container">
	<!--Top-Header Section end-->
 <header>
    
        <div class="row container">
        	<span class="col-md-1 col-sm-1 col-lg-1">
        		<img class="img-responsive" src="image/nationalemblem.png">
        	</span>
            <h3 class="col-md-6 col-sm-6 col-lg-6">
            <!--img class="img-responsive" src="image/nationalemblem.png"-->
            <strong></strong></br> 
             Skill Development Portal of
            <span>Chemicals & Petrochemicals Department</span>
            </h3>
        </div>
    
</header>
<!-- nav bar -->
 <nav class="navbar navbar-inverse container">
  <ul class="nav navbar-nav">
    <li class="active"><a href="index">Home</a></li>
    <li><a href="about.html">About</a></li>
    <li><a href="gallery.html">Gallery</a></li>
    <li><a href="contact.html">Contact Us</a></li>
    <li><a href="notification.html">Notification</a></li>

  </ul>
</nav>
<!--nav bar end-->
<!--start of main body block-->
<div class="container">
<div class="row">
<!--login form -->
 <form class="col-sm-2 col-lg-2 col-md-2" action="LoginServlet" method="post">
  <div class="form-group">
  <span style="color:red;">Username and Password is not matched. Please try Again.</span></br>
    <label for="user_name">Username:</label>
    <input type="text" id="user_name" name="user_name" class="form-control">
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" id="pwd" name="password" class="form-control">
  </div>
  <button id="sbt" type="submit" class="btn btn-default">Submit</button>
  <!--for creating a new account -->

  <p class="text-right"><a href="#">forgot password?</a></p>
  <button type="submit" class="btn btn-default"><a href="sign_up.html">Create a new account</a></button>
</form>
<!--end of login form-->
<!--start of advertizement block-->
<div class="col-sm-8 col-lg-8 col-md-8 middle_part">
   <div class="container">
     <div class="col-sm-8">
       <div id="myCarousel" class="carousel slide" data-ride="carousel">
 <!-- Indicators -->
        <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
 <!-- <li data-target="#myCarousel" data-slide-to="2"></li>-->
        </ol>

 <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox">
              <div class="item active">
                <img src="image/rk.jpg" alt="" width="800px" height="300px">
              </div>

              <div class="item">
                <img src="image/2.jpg" alt="" width="800px" height="300px">  
              </div>
          </div>

 <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
             <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
             <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
       </div>
     </div>
   </div>
 </div>
<!--end of advertisement block-->

<!--start of newsfeed block--> 
<div class="row">
<div class="col-lg-2 col-sm-2 col-md-2 ">
    <div class="panel panel-info">
         <div class="panel-heading">News Feed</div>
           <div class="panel-body" >

  <!--div class="col-md-6 col-sm-6 col-lg-6 " style="font-size: 20px"-->
             <li>training for graduates.<a href="training.com"><i>details</i></a>
             </li>
             <li>summer training 2017. <a href="summertraining.com"><i><br>click here</i></a></li>
           </div>     
         </div>
       </div>
       </div>
</div>
<!--end of newsfeed block-->
</div>
</div>
<!--end of main body block-->
<!--startof sub body block-->
<div class="container" style="margin-top: 20px">
    <div class="row jumbotron" style="padding: 5px 5px 5px 5px;">
        <p>It is an online skill development and job resource serving employers and job seekers in Chemical 
        and Petrochemical Sector. The site is exclusively targeted towards entry level hiring and maintaining a
         national level database of students and skilled persons under this sector. It brings relevant employers 
         and prospective students in touch with each other. The online platform offers a focused, user friendly 
         connection point for highly qualified candidates and <a href="about.html"><i>more...</i></a>
        </p>
    </div>
</div>
<footer class="container footer">
<div>
    <p>Copyright &copy 2017 <a href="technoforensis.com">technoforensis</a> all rights reserved</p>
</div>
    
</footer>


<script>

</script>

</body>
</html>