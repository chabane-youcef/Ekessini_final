<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Depot ${idDepot } Infos | Ekessini</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="imgs/png" href="imgs/icons/favicon.ico" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/login2-util.css">
    <link rel="stylesheet" type="text/css" href="css/login2.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
        integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">


</head>

<body>
    <!-- navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="#page-top">Ekessini Logo</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
            
            			<c:url var="HomeLink" value="GestionnaireServlet">
	                        <c:param name="command" value="home"/>
	                        
	                    </c:url>
	                    
	                    <c:url var="LogoutLink" value="GestionnaireServlet">
	                        <c:param name="command" value="logout"/>
	                        
	                    </c:url>
	                    
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${HomeLink }">Home</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${LogoutLink }">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- end of navigation bar-->
    <br>
    <br>

    <div class="limiter">
        <div class="container-fluid100">
            <h2 class="m-5 text-center">Depot : ${localisation }</h2>
            <div class="row m-2">
                <div class="table-responsive mt-5 mb-5" >
                    <table class="table w-75 p-3 m-auto " style="border: 1px solid black;">
                    	
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Type de Chambre</th>
                                <th scope="col">Permanent Male</th>
                                <th scope="col">Permanent Female</th>
                                <th scope="col">non-Permanent Male</th>
                                <th scope="col">non-Permanent Female</th>
                                <th scope="col">Capacite de Chambre</th>
                            </tr>
                        </thead>
                        <tbody>
	                        <c:forEach items="${ chambres}" var="tempD">
	                            <tr>
	                                <th scope="row">${tempD.idChambre }</th>
	                                <td>${tempD.type }</td>
	                                <td>${tempD.pmoc } / ${tempD.pm}</td>
	                                <td>${tempD.pfoc } / ${tempD.pf}</td>
	                                <td>${tempD.npmoc } / ${tempD.npm}</td>
	                                <td>${tempD.npfoc } / ${tempD.npf}</td>
	                                <td>${tempD.max }</td>
	                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
					
					<c:url var="ModifierDepotLink" value="GestionnaireServlet">
		                <c:param name="command" value="ModifierDepot"/>
		                        			
		                <c:set scope="request" value="${idDepot }" var="IdDepot"/>
		                <c:param name="idDepot" value="${ IdDepot}" />        			
	               	</c:url>
	               	
                <a href="${ModifierDepotLink }" class="btn btn-outline-danger m-auto "> Ajouter Chambres</a>
            </div>

        </div>
    </div>

    <!--===============================================================================================-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!--===============================================================================================-->
    <script src="js/scrolling-nav.js"></script>

</body>

</html>