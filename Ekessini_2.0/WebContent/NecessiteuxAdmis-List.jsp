
</html><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Necessiteux Admis | Ekessini</title>
	
	<link rel="icon" type="imgs/png" href="imgs/icons/favicon.ico" />
    <!-- Bootstrap core CSS -->
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
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                
                	<c:url var="HomeLink" value="AdminServlet">
	                	<c:param name="command" value="home"/> 
	                </c:url>
	                    
	                <c:url var="LougoutLink" value="AdminServlet">
	                	<c:param name="command" value="logout"/>
	                </c:url>
	                
                    <li class="nav-item active">
                        <a class="nav-link" href="${HomeLink }">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${LougoutLink }">Log Out</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
        <!-- end of nav bar -->
        <div class="container-fluid">
            <ol class="breadcrumb m-4">
                <li class="breadcrumb-item">
                    <a href="#">Necessiteux</a>
                </li>
                <li class="breadcrumb-item active">Non Validé</li>
            </ol>

            <!-- Begin of table -->
			
			
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" class="text-center">Nom</th>
                            <th scope="col" class="text-center">Prenom</th>
                            <th scope="col" class="text-center">Date Naissance</th>
                            <th scope="col" class="text-center">Sexe</th>
                            <th scope="col" class="text-center">Situation Familiale</th>
                            <th scope="col" class="text-center">Piax Moix</th>
                            <th scope="col" class="text-center">Numero Telephone</th>
                            <th scope="col" class="text-center">Email</th>

                        </tr>
                    </thead>
                    <tbody>
                    	
						<c:forEach items="${ NecessiteuxAdmisList}" var="tempN">
                    	
	                        <tr>  
	                        	
	                            <td class="text-center">${tempN.nom }</td>
	                            <td class="text-center">${tempN.prenom }</td>
	                            <td class="text-center">${tempN.dateNais }</td>
	                            <td class="text-center">${tempN.sexe } </td>
	                            <td class="text-center">${tempN.situationFam }</td>
	                            <td class="text-center">${tempN.paixMois }</td>
	                            <td class="text-center">${tempN.numTel }</td>
	                            <td class="text-center">${tempN.email }</td>
	                            
	                        </tr>
	               		
	               		</c:forEach>         

                    </tbody>
                </table>
            </div>
            
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
    

</body>

</html>