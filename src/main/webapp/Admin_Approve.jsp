 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.in.bean.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>admin_approve</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/NavBar.css">
    <link rel="stylesheet" href="css/table_style.css">
    <link rel="stylesheet" href="css/background_waves.css">
</head>
<body>
<!--Navbar Start-->
<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-lg-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="Admin_Home.html">HOME</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Admin_Submit.html">Submit</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Admin_Approve.jsp">Approve</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Admin_Viewing.jsp">View Image</a>
        </li>
        
        <li class="nav-item">

        </li>
      </ul>
      <span class="navbar-text">
        <a class="nav-link active mylogout" aria-current="page" href="index.html">Logout</a>
      </span>
    </div>
  </div>
</nav>
<!--Navbar End-->
<!--Background Start-->

<div class="container1">
  <div class="header">
      <!--Content Start-->
      <br>
      <div class="heading pb-5 pt-2 text-center">
        <h3>Medical Condition Image Search and Viewer</h3>
    </div>

    <div class="container submittable text-center approval_container searchtable">
        <table class="table content-table table-hover">
            
              <tr>
                <th scope="col">No</th>
                <th scope="col">Details of Image</th>
                <th scope="col">Requested Authority</th>
                <th scope="col">Approval</th>
                <th scope="col">Reject</th>
              </tr>
           
<% ArrayList<Register>ulist=(ArrayList<Register>)session.getAttribute("ulist");
for(Register robj:ulist)
{
%>
        
              <tr>
                <th scope="row" class="text-white"><%=robj.getFid() %></th>
                <td class="text-start text-white"><%=robj.getUn() %></td>
                <td class="text-start text-white"><%=robj.getDes() %></td>
              
           <td>
      <form action="Controller" method="post">
 
		 	<input type="hidden" value="<%=robj.getUn() %>" name="un">
 			<input type="hidden" value="<%=robj.getFid() %>" name="fid">
 			<input type="hidden" value="<%=robj.getDes() %>" name="des">
          	<input type="hidden" value="app2" name="action">
          	<input type="submit" value="Approve">
          
     </form>
     </td>
     
<td><form action="Controller" method="post">
 
 <input type="hidden" value="<%=robj.getUn() %>" name="un">
 <input type="hidden" value="<%=robj.getFid() %>" name="fid">
 <input type="hidden" value="<%=robj.getDes() %>" name="des">
          <input type="hidden" value="ap" name="action">
          <input type="submit" value="Reject">
          
 </form></td>
</tr>
<%} %>
          </table>
    </div>

      <!--Content End-->
      <!--Content before waves-->
      <div class="inner-header flex">
        
      <!--Just the logo.. Don't mind this-->
      <svg version="1.1" class="logo visually-hidden" baseProfile="tiny" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
      xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 500 500" xml:space="preserve">
      <path fill="#FFFFFF" stroke="#000000" stroke-width="10" stroke-miterlimit="10" d="M57,283" />
      <g><path fill="#fff"
      d="M250.4,0.8C112.7,0.8,1,112.4,1,250.2c0,137.7,111.7,249.4,249.4,249.4c137.7,0,249.4-111.7,249.4-249.4
      C499.8,112.4,388.1,0.8,250.4,0.8z M383.8,326.3c-62,0-101.4-14.1-117.6-46.3c-17.1-34.1-2.3-75.4,13.2-104.1
      c-22.4,3-38.4,9.2-47.8,18.3c-11.2,10.9-13.6,26.7-16.3,45c-3.1,20.8-6.6,44.4-25.3,62.4c-19.8,19.1-51.6,26.9-100.2,24.6l1.8-39.7		c35.9,1.6,59.7-2.9,70.8-13.6c8.9-8.6,11.1-22.9,13.5-39.6c6.3-42,14.8-99.4,141.4-99.4h41L333,166c-12.6,16-45.4,68.2-31.2,96.2	c9.2,18.3,41.5,25.6,91.2,24.2l1.1,39.8C390.5,326.2,387.1,326.3,383.8,326.3z" />
      </g>
      </svg>
      <h1></h1>
      </div>
      
      <!--Waves Container-->
      <div>
      <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
      viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
      <defs>
      <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
      </defs>
      <g class="parallax">
      <use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(255,255,255,0.7" />
      <use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(255,255,255,0.5)" />
      <use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(255,255,255,0.3)" />
      <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" />
      </g>
      </svg>
      </div>
      <!--Waves end-->
      
      </div>
      <!--Header ends-->
      
      <!--Content starts-->
      <div class="content flex">
        <p> </p>
      </div>
      <!--Content ends-->
</div>
<!--Background ends-->
       

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>