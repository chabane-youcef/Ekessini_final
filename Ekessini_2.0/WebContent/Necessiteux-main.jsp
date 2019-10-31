<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Necessiteux Profil | Ekessini</title>
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
			
				<c:url var="ajouteDemandeLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="AjouteDemande"/>
	            	<c:set scope="request" value="${necessiteux.idNecessiteux}" var="IdNec"/>
            		<c:param name="idNecessiteux" value="${ IdNec}" />
	            </c:url>
	            
	            <c:url var="oldDemandessLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="oldDemandess"/>
	            	<c:set scope="request" value="${necessiteux.idNecessiteux}" var="IdNec"/>
            		<c:param name="idNecessiteux" value="${ IdNec}" />
	            </c:url>
	            
	            <c:url var="DeleteNecessiteuxAccountLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="DeleteNecessiteuxAccount"/>
	            	<c:set scope="request" value="${necessiteux.idNecessiteux}" var="IdNec"/>
            		<c:param name="idNecessiteux" value="${ IdNec}" />
	            </c:url>
	            
	            <c:url var="homeLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="home"/>
	            	<c:set scope="request" value="${necessiteux.idNecessiteux}" var="IdNec"/>
            		<c:param name="idNecessiteux" value="${ IdNec}" />
	            </c:url>
	            
	            <c:url var="logoutLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="logout"/>
	            	
	            </c:url>
	                        	
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link js-scroll-trigger" href="${homeLink }">Home</a>
					</li>
					
					<li class="nav-item dropdown js-scroll-trigger">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Actions
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          	<a class="dropdown-item" href="${ajouteDemandeLink}" >Ajoute Demande</a>
				          	<a class="dropdown-item" href="${oldDemandessLink }">vieux dons</a>
				        	<div class="dropdown-divider"></div>
				        	<a class="dropdown-item "   data-toggle="modal" data-target="#exampleModal" href="#"><i class="fas fa-trash-alt" style="color:red"></i>  Effacer Compte</a>
				    	</div>
				    </li>
					<li class="nav-item">
						<a class="nav-link js-scroll-trigger" href="${ logoutLink}">Logout</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
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
								<p>êtes-vous sûr de supprimer votre compte</p>
							</div>
					      	<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
						        <a href="${DeleteNecessiteuxAccountLink }" class="btn btn-danger">oui</a>
					      	</div>
					    </div>
					</div>
				</div>
	<!-- end of navigation bar-->
	<br>
	<br>

	<div class="limiter">
		<div class="container-fluid100">
			<ol class="breadcrumb m-4">
                <li class="breadcrumb-item">
                    <a href="#">${necessiteux.nom } ${necessiteux.prenom } Profil</a>
                </li>
                <li class="breadcrumb-item active">Les Demandes</li>
            </ol>
			<div class="row m-2">
            
            	<c:forEach items="${ demandesList}" var="tempD">
					<div class="col-sm-6 col-md-4 col-lg-3 mt-4">
						<div class="card">
							<img src="imgs/Demand-card-bd.jpeg" alt="donation box image" class="card-img-top">
	
							<div class="card-block">
								<h4 class="card-title text-center mt-3">Demand informations</h4>
								<br>
								<ul class="list-group-flush">
									<li class="list-group-item">Type : ${tempD.type }</li>
									<li class="list-group-item">Genre: ${tempD.genre }</li>
									<li class="list-group-item">Quantite: ${tempD.quantite }</li>
									<li class="list-group-item">localisation: ${tempD.localisation }</li>
									<li class="list-group-item">Etat Demande: ${tempD.etat }</li>
									<li class="list-group-item">idDon: ${tempD.idDepot }</li>
								</ul>
							</div>
	
							<div class="card-footer">
								<span class="float-right mb-2">Rendez-vous : ${tempD.date }</span>
								
								<!-- link to delete -->
								<c:url var="DeleteDemandeLink" value="NecessiteuxServlet">
									<c:param name="command" value="deleteDemande" />
									
									<c:set scope="request" value="${tempD.idNecessiteux }" var="IdNecessiteux"/>
	                				<c:param name="idNecessiteuxAdmis" value="${ IdNecessiteux}" />
	                				
	                				<c:set scope="request" value="${tempD.idDon }" var="IdDon"/>
	                				<c:param name="idDon" value="${ IdDon}" />
	                				
	                				<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                				<c:param name="idDepot" value="${ IdDepot}" />
	                				
	                				<c:set scope="request" value="${tempD.idDemande}" var="IdDemande"/>
				            		<c:param name="idDemande" value="${ IdDemande}" />
				            		
				            		<c:set scope="request" value="${tempD.quantite}" var="Quantite"/>
				            		<c:param name="quantite" value="${ Quantite}" />
								</c:url>
								
								<c:url var="UpdateDemandeLink" value="NecessiteuxServlet">
									<c:param name="command" value="updateDemande" />
									
									<c:set scope="request" value="${tempD.idDemande}" var="IdDemande"/>
				            		<c:param name="idDemande" value="${ IdDemande}" />
				            		
									<c:set scope="request" value="${tempD.idNecessiteux }" var="IdNecessiteux"/>
	                				<c:param name="idNecessiteuxAdmis" value="${ IdNecessiteux}" />
	                				
	                				<c:set scope="request" value="${tempD.idDon }" var="IdDon"/>
	                				<c:param name="idDon" value="${ IdDon}" />
	                				
	                				<c:set scope="request" value="${tempN.idDepot }" var="IdDepot"/>
	                				<c:param name="idDepot" value="${ IdDepot}" />
	                				
	                				<c:set scope="request" value="${tempD.quantite}" var="Quantite"/>
				            		<c:param name="quantite" value="${ Quantite}" />
								</c:url>
					            
								<div class="float-right">
									<a href="${UpdateDemandeLink }" class="btn btn-success btn-sm ">Modifier</a>
									<a href="${DeleteDemandeLink }" class="btn btn-danger btn-sm">Supprimer</a>
								</div>
								
								
								
								
							</div>
						</div>
					</div>
				</c:forEach>

				<div class="col-sm-6 col-md-4 col-lg-3 mt-4 d-flex align-items-stretch">
					<div class="card">
						<img src="imgs/Demand-card-bd.jpeg" alt="donation box image" class="card-img-top">

						<div class="card-block text-center">

							<br>
							<br><br>
							<a href="${ajouteDemandeLink }" class="justify-content-center "><i class="far fa-plus-square fa-9x mt-5"></i></a>

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