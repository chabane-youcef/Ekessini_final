<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>List Donations Non Permanent | Ekessini</title>
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

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">


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
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item text-white">
                    	<c:url var="HomeLink" value="GestionnaireServlet">
	                        <c:param name="command" value="home"/>
	                        
	                    </c:url>
	                    
	                    <c:url var="LogoutLink" value="GestionnaireServlet">
	                        <c:param name="command" value="logout"/>
	                        
	                    </c:url>
	                    
                        <a class="nav-link js-scroll-trigger" href="${HomeLink }">Home</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger text-white" href="${LogoutLink }">Logout</a>
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
            
            <ol class="breadcrumb mt-5 m-3">
                <li class="breadcrumb-item">
                    <a href="#">Les Donations</a>
                </li>
                <li class="breadcrumb-item active">Non Permanant</li>
            </ol>

            <div class="row m-3">
                <div class="table-responsive text-nowrap mt-5">
                    <table class="table table-striped">
                        <!--Table head-->
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nom</th>
                                <th>Prenom</th>
                                <th>Type</th>
                                <th>Genre</th>
                                <th>Quantite</th>
                                <th>Date</th>
                                <th>Localisation</th>
                                <th>Email</th>
                                <th>Telephone</th>
                                <th>Valider</th>
                                <th>Refuser</th>
                            </tr>
                        </thead>
                        <!--Table head-->

                        <tbody>
	                        <c:forEach items="${ donationsNonP}" var="tempD">
	                            <tr>
	                                <th scope="row">${tempD.idDonation }</th>
	                                <th>${tempD.nom }</th>
	                                <th>${tempD.prenom }</th>
	                                <th>${tempD.type}</th>
	                                <th>${tempD.genre }</th>
	                                <th>${tempD.quantite }</th>
	                                <th>${tempD.date }</th>
	                                <th>${tempD.localisation }</th>
	                                <th>${tempD.email }</th>
	                                <th>+213-${tempD.numTel }</th>
	                                
	                                <!-- Valider Link -->
	                                <c:url var="ValiderDonationNonPLink" value="GestionnaireServlet">
	                        			<c:param name="command" value="validerDonationNonP"/>
	                        			
	                        			<c:set scope="request" value="${tempD.idDonation }" var="IdDonation"/>
	                        			<c:param name="idDonation" value="${ IdDonation}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDonateur }" var="IdDonateur"/>
	                        			<c:param name="idDonateur" value="${ IdDonateur}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDon }" var="IdDon"/>
	                        			<c:param name="idDon" value="${ IdDon}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                        			<c:param name="idDepot" value="${ IdDepot}" />
	                        			
	                        			<c:set scope="request" value="${tempD.quantite }" var="Quantite"/>
	                        			<c:param name="quantite" value="${ Quantite}" />
	                        			
	                        		</c:url>
	                        		
	                        		<!-- rfuser Link -->
	                        		<c:url var="RefuserDonationNonPLink" value="GestionnaireServlet">
	                        			<c:param name="command" value="RefuserDonationNonP"/>
	                        			
	                        			<c:set scope="request" value="${tempD.idDonation }" var="IdDonation"/>
	                        			<c:param name="idDonation" value="${ IdDonation}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDonateur }" var="IdDonateur"/>
	                        			<c:param name="idDonateur" value="${ IdDonateur}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDon }" var="IdDon"/>
	                        			<c:param name="idDon" value="${ IdDon}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                        			<c:param name="idDepot" value="${ IdDepot}" />
	                        			
	                        			<c:set scope="request" value="${tempD.quantite }" var="Quantite"/>
	                        			<c:param name="quantite" value="${ Quantite}" />
	                        			
	                        			
	                        			
	                        			
	                        		</c:url>
	                        		
	                        		
	                                <th><a href="${ValiderDonationNonPLink }" class="btn btn-outline-success">Valider</a></th>
                                	<th><a href="${RefuserDonationNonPLink }" class="btn btn-outline-danger">Refuser</a></th>
                                	
	                            </tr>
	                            
							</c:forEach>
							
                        </tbody>
                    </table>
                    
                </div>
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