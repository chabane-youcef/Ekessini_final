<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Gerer Depots | Ekessini</title>
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
                <ul class="navbar-nav ml-auto">
                		<c:url var="HomeLink" value="GestionnaireServlet">
	                        <c:param name="command" value="home"/>
	                    </c:url>
	                    
	                    <c:url var="LogoutLink" value="GestionnaireServlet">
	                        <c:param name="command" value="logout"/>
	                    </c:url>
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

    
        <div class="container-fluid100">
            <ol class="breadcrumb m-4">
                <li class="breadcrumb-item">
                    <a href="#">Gestion de Depot</a>
                </li>
                <li class="breadcrumb-item active">Depots Disponible</li>
            </ol>

            <div class="row m-2">
				<c:forEach items="${ depots}" var="tempD">
	                <div class="col-sm-6 col-md-4 col-lg-4 mt-4 d-flex align-items-stretch">
	                
	                    <div class="card">
	                        <img src="imgs/gestion-depot-card-bg.jpg" alt="donation box image" class="card-img-top">
	
	                        <div class="card-block">
	                            <h4 class="card-title text-center mt-3">Depot informations</h4>
	                            <br>
	                            <ul class="list-group-flush">
	                                <li class="list-group-item">localisation :  ${tempD.localisation }</li>
	                                <li class="list-group-item">Nombre Chambres :  ${tempD.nbrChambres }</li>
	                                
	                            </ul>
	                        </div>
	
	                        <div class="card-footer">
	                            <!-- Delete Link -->
								<c:url var="deleteDepotLink" value="GestionnaireServlet">
	                				<c:param name="command" value="DeleteDepot"/>
	                				<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                        		<c:param name="idDepot" value="${ IdDepot}" />
	                				
	               				</c:url>
	               				<!-- update Link -->
	               				
	               				<c:url var="ajouterDepotLink" value="GestionnaireServlet">
	                				<c:param name="command" value="AjouterDepot"/>
	               				</c:url>
	               				
	               				<c:url var="ProfilDepotLink" value="GestionnaireServlet">
	                				<c:param name="command" value="ProfilDepot"/>
	                				
	                				<c:set scope="request" value="${tempD.idDepot }" var="IdDepot"/>
	                        		<c:param name="idDepot" value="${ IdDepot}" />
	               				</c:url>
	               				
	                            <div class="float-right">
	                                <a href="${ProfilDepotLink }" class="btn btn-success btn-sm ">Information</a>
	                                <a href="${deleteDepotLink }" class="btn btn-danger btn-sm">Supprimer</a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
				</c:forEach>
                

                <div class="col-sm-6 col-md-4 col-lg-4 mt-4 d-flex align-items-stretch">
                	<c:url var="ajouterDepotLink" value="GestionnaireServlet">
	                	<c:param name="command" value="AjouterDepot"/>
	               	</c:url>
                	
                    <div class="card">
                        <img src="imgs/gestion-depot-card-bg.jpg" alt="donation box image" class="card-img-top">

                        <div class="card-block text-center">

							
							<br><br>
							<a href="${ajouterDepotLink }" class="justify-content-center "><i class="far fa-plus-square fa-9x mt-5"></i></a>
                            
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