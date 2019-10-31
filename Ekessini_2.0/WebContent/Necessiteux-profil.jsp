<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Necessiteux | Ekessini</title>
	<link rel="icon" type="imgs/png" href="imgs/icons/favicon.ico" />
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/necessiteux.css">

</head>

<body>

    <div class="d-flex" id="wrapper">
        <!-- Page Content -->
        <div id="page-content-wrapper">

            <nav class="navbar navbar-expand-md navbar-light bg-light border-bottom">
                <div class="sidebar-heading ml-4">Ekessini Logo </div>

                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
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
						
						<li class="nav-item">
                            <a class="nav-link" href="${HomeLink }">Home</a>
                        </li>
							
                        <li class="nav-item">
                            <a class="nav-link" href="${LougoutLink }">Log Out</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- end of nav bar -->

            <!-- Breadcrumbs-->
            <ol class="breadcrumb m-4">
                <li class="breadcrumb-item">
                    <a href="#">Necessiteux non Validé</a>
                </li>
                <li class="breadcrumb-item active">Informations</li>
            </ol>
            <!-- cards -->

            <div class="row m-5 justify-content-center">
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <div class="row">
                                <div class="col-md-4 text-center">
                                    <img class="img-circle avatar avatar-original" style="-webkit-user-select:none; 
                                        display:block; margin:auto;"
                                        src="imgs/avatar-necessiteux.png">
                                </div>
                                <div class="col-md-8">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h2 class="only-bottom-margin">Necessiteux Informations</h2>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                        	<span class="text-muted">id:</span> <c:out value="${NecProfil.idNecessiteux}"/><br>
                                            <span class="text-muted">Nom:</span> <c:out value="${NecProfil.nom}"/><br>
                                            <span class="text-muted">Prenom:</span> <c:out value="${NecProfil.prenom}"/><br>
                                            <span class="text-muted">Date de Naissance:</span> ${NecProfil.dateNais }<br>
                                            <span class="text-muted">Sexe:</span> ${NecProfil.sexe }<br>
											<span class="text-muted">Numero de Telephone:</span> +213 - ${NecProfil.numTel }<br>
											<span class="text-muted">Email:</span> ${NecProfil.email }<br><br>
                                        </div>
                                        <div class="col-md-6">
                                            <small class="text-muted font-weight-bold">Informations sur situation de Necessiteux:</small>
                                            <div class="activity-mini">
                                                <i class="glyphicon glyphicon-comment text-muted">situation Familiale:</i> ${NecProfil.situationFam }
                                            </div>
                                            <div class="activity-mini">
                                                <i class="glyphicon glyphicon-thumbs-up text-muted">Salaire: </i> ${NecProfil.paixMois }
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-md-4 mt-5">
                                	<!-- JSTL link to Refuse necessiteux -->
                                	<c:url var="refuserLink" value="AdminServlet">
	                        			<c:param name="command" value="RefuserNecessiteux"/>
	                        			<c:set scope="request" value="${NecProfil.idNecessiteux }" var="IdNecP"/>
	                        			<c:param name="IdNecessiteuxProfil" value="${ IdNecP}" />
	                        			<c:set scope="request" value="${NecProfil.numTel }" var="NecNumTel"/>
	                        			<c:param name="NecessiteuxNumTel" value="${ NecNumTel}" />
	                        		</c:url>
	                        		
	                        		<!-- JSTL link to validate necessiteux -->
	                        		
	                        		
                                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#Priorite">Valider</button>
                                    <a href="${refuserLink }" class="btn btn-danger" role="button">Refuser</a>
                                    
                                    <!-- modele to select priorite -->
                                    <div class="modal" id="Priorite">
							            <div class="modal-dialog">
							                <div class="modal-content">
				                        		
							                    <!-- Modal Header -->
							                      
							                    <div class="modal-header">
							                        <h4 class="modal-title">Priorite Selection</h4>
							                        <button type="button" class="close" data-dismiss="modal">&times;</button>
							                    </div>
							                    
							
							                    <!-- Modal body -->
							                    <div class="modal-body">
							                        <span class="text-muted"></span> Selectionner la Priorite de Necessiteux<br><br>
							                        <form method="get" action="AdminServlet">
							                        	<input type="hidden"  name="command" value="ValiderNecessiteux">
								                        <input type="hidden"  name="IdNecessiteuxvalider" value="${NecProfil.idNecessiteux }">
								                        <input type="hidden"  name="NecessiteuxNumTel" value="${NecProfil.numTel }">
								                        
								                        <select name="prioriteSelection" id="prioriteSelection" class="form-control form-control-sm">
								                            <option value="X">Selection Priorite ...</option>
								                            <option value="A">sous le seuil de pauvreté : Priorite "A"</option>
								                            <option value="B">sur le seuil de pauvreté : Priorite "B"</option>
								                            <option value="C">au-dessus du seuil de pauvreté : Priorite "C"</option>
								                        </select>
								                        <br><br>
								                        <button class="btn btn-success">valider</button>
								                        
							                        </form>
							                    </div>
							
							                    <!-- Modal footer -->
							                    
							                </div>
							            </div>
							        </div>
                                    <!-- end of modele -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>