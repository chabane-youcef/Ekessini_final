<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>List Demandes | Ekessini</title>
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
	                    
                        <a class="nav-link js-scroll-trigger" href="${HomeLink }">Home</a>
                    </li>

                    <li class="nav-item">
	                    	<c:url var="LogoutLink" value="GestionnaireServlet">
		                        <c:param name="command" value="logout"/>   
		                    </c:url>
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
                    <a href="#">Les Demandes</a>
                </li>
                <li class="breadcrumb-item active">List Demandes</li>
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
                                <th>Date Naissance</th>
                                <th>Sexe</th>
                                <th>Situation Familiale</th>
                                <th>Enfants</th>
                                <th>Salaire</th>
                                <th>Priorite</th>
                                <th>Email</th>
                                <th>Telephone</th>
                                <th>Type</th>
                                <th>Genre</th>
                                <th>Quantite</th>
                                <th>Localisation</th>
                                <th>Rendez-vous</th>
                                <th>Valider</th>
                                <th>Refuser</th>
                            </tr>
                        </thead>
                        <!--Table head-->

                        <tbody>
	                        <c:forEach items="${ demandes}" var="tempD">
	                            <tr>
	                                <th scope="row">${tempD.idDemande}</th>
	                                <th>${tempD.nom}</th>
	                                <th>${tempD.prenom}</th>
	                                <th>${tempD.dateNais}</th>
	                                <th>${tempD.sexe}</th>
	                                <th>${tempD.sitFam}</th>
	                                <th>${tempD.nbrEnfants}</th>
	                                <th>${tempD.salaire}</th>
	                                <th>${tempD.priorite}</th>
	                                <th>${tempD.email}</th>
	                                <th>${tempD.numTel}</th>
	                                <th>${tempD.type}</th>
	                                <th>${tempD.genre}</th>
	                                <th>${tempD.quantite}</th>
	                                <th>${tempD.localisation}</th>
	                                <th>${tempD.date}</th>
	                                
	                                <!-- Valider Link -->
	                                <c:url var="ValiderDemandeLink" value="GestionnaireServlet">
	                        			<c:param name="command" value="ValiderDemande"/>
	                        			
	                        			<c:set scope="request" value="${tempD.idDemande }" var="IdDemande"/>
	                        			<c:param name="idDemande" value="${ IdDemande}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idNecessiteux }" var="IdNecessiteux"/>
	                        			<c:param name="idNecessiteux" value="${ IdNecessiteux}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDon }" var="IdDon"/>
	                        			<c:param name="idDon" value="${ IdDon}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                        			<c:param name="idDepot" value="${ IdDepot}" />
	                        			
	                        			<c:set scope="request" value="${tempD.quantite }" var="Quantite"/>
	                        			<c:param name="quantite" value="${ Quantite}" />
	                        			
	                        			<c:set scope="request" value="${tempD.nbrAvertissement }" var="NbrAvertissement"/>
	                        			<c:param name="nbrAvertissement" value="${ NbrAvertissement}" />
	                        			
	                        			<!-- add the value of 'nbrAvertissement' -->
	                        		</c:url>
	                        		
	                        		
	                        		<!-- rfuser Link -->
	                        		<c:url var="RefuserDemandeLink" value="GestionnaireServlet">
	                        			<c:param name="command" value="RefuserDemande"/>
	                        			
	                        			<c:set scope="request" value="${tempD.idDemande }" var="IdDemande"/>
	                        			<c:param name="idDemande" value="${ IdDemande}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idNecessiteux }" var="IdNecessiteux"/>
	                        			<c:param name="idNecessiteux" value="${ IdNecessiteux}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDon }" var="IdDon"/>
	                        			<c:param name="idDon" value="${ IdDon}" />
	                        			
	                        			<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                        			<c:param name="idDepot" value="${ IdDepot}" />
	                        			
	                        			<c:set scope="request" value="${tempD.quantite }" var="Quantite"/>
	                        			<c:param name="quantite" value="${ Quantite}" />
	                        			
	                        			<c:set scope="request" value="${tempD.nbrAvertissement }" var="NbrAvertissement"/>
	                        			<c:param name="nbrAvertissement" value="${ NbrAvertissement}" />
	                        			
	                        			<c:set scope="request" value="${tempD.email }" var="Email"/>
	                        			<c:param name="email" value="${ Email}" />
	                        			
	                        			<c:set scope="request" value="${tempD.numTel }" var="NumTel"/>
	                        			<c:param name="numTel" value="${ NumTel}" />
	                        			
	                        			<!-- add the value of 'nbrAvertissement' -->
	                        		</c:url>
	                        		
	                        		
	                                <th><a href="${ValiderDemandeLink }" class="btn btn-outline-success">Valider</a></th>
                                	<th>
                                		<button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal">
										  Refuser
										</button>
									</th>
                                	
									<!-- Modal -->
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
											<p>êtes-vous sûr de vouloir supprimer mr.${tempD.nom} demande</p>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
									        <a href="${RefuserDemandeLink }" class="btn btn-danger">oui</a>
									      </div>
									    </div>
									  </div>
									</div>
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