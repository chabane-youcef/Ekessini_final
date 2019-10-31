<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,control.*,control.*" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Admin | Ekessini</title>

  <!-- Bootstrap core CSS -->
    <link rel="icon" type="imgs/png" href="imgs/icons/favicon.ico" />
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
    integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

  <!-- Custom styles for this template -->
  <link href="css/dashboard.css" rel="stylesheet">

</head>

<body>
	

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-md navbar-light bg-light border-bottom">
        <div class="sidebar-heading ml-4">Ekessini Logo </div>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        	
        	<c:url var="HomeLink" value="AdminServlet">
	        	<c:param name="command" value="home"/>
	        </c:url>
	        
        	<c:url var="logoutLink" value="AdminServlet">
	        	<c:param name="command" value="logout"/>
	        </c:url>
	        
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="${HomeLink }">Home <span class="sr-only">(current)</span></a>
            </li>
            
            <li class="nav-item">
                  <a class="nav-link" href="${logoutLink }">Log Out</a>
            </li>
            
          </ul>
        </div>
      </nav>
      <!-- end of nav bar -->
      <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb m-4">
          <li class="breadcrumb-item">
            <a href="#">Dashboard</a>
          </li>
          <li class="breadcrumb-item active">Overview</li>
        </ol>

        <!-- cards -->			
			
        <div class="row m-2">
        
          <div class="col-xl-4 col-sm-4 mb-3">
            <div class="card text-white bg-sucess o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
                  <i class="fa fa-user"></i>
                </div>
                <div class="mr-5"><p>${NAdmCounter} Necessiteux</p></div>
              </div>
              
             	<c:url var="NecessiteuxAdmisLink" value="AdminServlet">
					<c:param name="command" value="NecessiteuxAdmisList" />				
				</c:url>
				
              <a class="card-footer text-white clearfix small z-1" href="${NecessiteuxAdmisLink }">
                <span class="float-left">voir les détails</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
			
			<!-- jstl link to servlet -->
			
			
			
          <div class="col-xl-4 col-sm-4 mb-3">
            <div class="card text-white bg-warning o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
                  <i class="fa fa-user-secret"></i>
                </div>
                <div class="mr-5"><p>${NecCounter } Necessiteux non-validé</p></div>
              </div>
              
	            <c:url var="NecessiteuxNonPLink" value="AdminServlet">
					<c:param name="command" value="NecessiteuxNonPList" />				
				</c:url>
			
              <a class="card-footer text-white clearfix small z-1" href="${ NecessiteuxNonPLink}">
                <span class="float-left">Voir les détails</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>

		<div class="col-xl-4 col-sm-4 mb-3">
        	<div class="card text-white bg-danger o-hidden h-100">
            	<div class="card-body">
                	<div class="card-body-icon">
                  		<i class="fas fa-user-lock"></i>
                	</div>
               <div class="mr-5"><p>comptes bloqués</p></div>
              </div>
              
              	<c:url var="CompteBlockesLink" value="AdminServlet">
					<c:param name="command" value="ComptesBlockes" />				
				</c:url>
				
              <a class="card-footer text-white clearfix small z-1" href="${CompteBlockesLink }">
                <span class="float-left">voir les détails</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
          

        </div>


        <ol class="breadcrumb m-4">
          <li class="breadcrumb-item">
            <a href="#">Top Donnateurs</a>
          </li>
          <li class="breadcrumb-item active">Overview</li>
        </ol>

        <!-- Begin of table -->

        <div class="table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nom</th>
                <th scope="col">Prenom</th>
                <th scope="col">sexe</th>
                <th scope="col">Telephone</th>
                <th scope="col">Email</th>
                <th scope="col">Donations</th>

              </tr>
            </thead>
            <tbody>
            <c:forEach items="${ topDonateur}" var="tempD">
		    	<tr>
			        <th scope="row">1</th>
			        <th >${tempD.nom }</th>
			        <th >${tempD.prenom }</th>
			        <th >${tempD.sexe }</th>
			        <th >${tempD.numTel }</th>
			        <th >${tempD.email }</th>
			        <th >${tempD.nbrDonations }</th>
		        </tr>
			</c:forEach>
              

            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function (e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

</body>

</html>