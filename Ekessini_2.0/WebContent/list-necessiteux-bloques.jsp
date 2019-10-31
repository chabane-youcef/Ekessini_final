<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Necessiteux Bloques | Ekessini</title>
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
                    	<c:url var="HomeLink" value="AdminServlet">
	                        <c:param name="command" value="home"/>
	                    </c:url>
	                    
	                    <c:url var="LogoutLink" value="AdminServlet">
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
                    <a href="#">Les Necessiteux</a>
                </li>
                <li class="breadcrumb-item active">Bloques</li>
            </ol>

            <div class="row m-3">
            	<p class="text-center text-danger">${message }</p>
                <div class="table-responsive text-nowrap mt-5">
                    <table class="table table-striped">
                        <!--Table head-->
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nom</th>
                                <th>Prenom</th>
                                <th>Date Naissance</th>
                                <th>Genre</th>
                                <th>Situation Familiale</th>
                                <th>Enfants</th>
                                <th>Paix Mois</th>
                                <th>Priorite</th>
                                <th>Email</th>
                                <th>Telephone</th>
                                <th>Donations</th>
                                <th>Action</th>
                                
                            </tr>
                        </thead>
                        <!--Table head-->

                        <tbody>
	                        <c:forEach items="${ necessiteuxBlockedList}" var="tempD">
	                            <tr>
	                                <th scope="row">${tempD.idNecessiteux }</th>
	                                <th>${tempD.nom }</th>
	                                <th>${tempD.prenom }</th>
	                                <th>${tempD.dateNais }</th>
	                                <th>${tempD.sexe }</th>
	                                <th>${tempD.situationFam }</th>
	                                <th>${tempD.nbrEnfants }</th>
	                                <th>${tempD.paixMois }</th>
	                                <th>${tempD.priorite }</th>
	                                <th>${tempD.email }</th>
	                                <th>+213-${tempD.numTel }</th>
	                                <th>${tempD.nbrDemande }</th>
	                                
	                                <!-- Valider Link -->
	                                <c:url var="DebloqueNecessiteuxLink" value="AdminServlet">
	                        			<c:param name="command" value="DebloqueNecessiteux"/>
	                        			
	                        			<c:set scope="request" value="${tempD.idNecessiteux }" var="IdNecessiteux"/>
	                        			<c:param name="idNecessiteux" value="${ IdNecessiteux}" />
	                        			
	                        			<c:set scope="request" value="${tempD.nom }" var="Nom"/>
	                        			
	                        			
	                        		</c:url>
	                       			
	                                <th><a href="${DebloqueNecessiteuxLink }" data-toggle="modal" data-target="#exampleModal" class="btn btn-outline-success">Debloquer</a></th>
	                            </tr>
							</c:forEach>
                        </tbody>
                    </table>
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  	<div class="modal-dialog" role="document">
						<div class="modal-content">
					    	<div class="modal-header">
					        	<h5 class="modal-title" id="exampleModalLabel">Confiramtion</h5>
						       	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
						        </button>
							</div>
							<div class="modal-body">
								<p>êtes-vous sûr de Debloquer Donateur ${Nom }?</p>
							</div>
					      	<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
						        <a href="${DebloqueNecessiteuxLink }" class="btn btn-success">oui</a>
					      	</div>
					    </div>
					</div>
				</div>
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