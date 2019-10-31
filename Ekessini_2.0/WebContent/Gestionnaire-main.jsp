<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Gestionnaire Profil | Ekessini</title>
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
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#">Home</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="login.jsp">Logout</a>
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
        	<h4 class="text-black text-center mt-4"> Bienvenue ${nom } ${prenom }</h4>
            <ol class="breadcrumb m-4">
                <li class="breadcrumb-item">
                    <a href="#">Gestionnaire de Depot</a>
                </li>
                <li class="breadcrumb-item active">Votre Tasks</li>
            </ol>

            <div class="row m-2">
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                	
                	<c:url var="GererDepotsLink" value="GestionnaireServlet">
	                	<c:param name="command" value="GererDepots"/>
	          			<c:set scope="request" value="${gestionnaire }" var="role"/>
	                    <c:param name="role" value="${ role}" />
	               	</c:url>
                	
                    <div class="card text-white bg-success o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-warehouse fa-2x"></i>
                            </div>
                            <div class="mr-5">
                                <h2>Gérer les Stocks</h2>
                            </div>
                        </div>
                        <br><br>
                        <a class="card-footer text-white clearfix medium z-1" href="${GererDepotsLink }">
                            <span class="float-left">voir les détails</span>
                            <span class="float-right">
                                <i class="fas fa-angle-right fa-lg"></i>
                            </span>
                        </a>
                    </div>
                </div>

                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                
                	<c:url var="GererDonationLink" value="GestionnaireServlet">
	                	<c:param name="command" value="GererDonations"/>
	          			<c:set scope="request" value="${gestionnaire }" var="role"/>
	                    <c:param name="role" value="${ role}" />
	               	</c:url>
	               	
                    <div class="card text-white bg-info o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-dolly fa-2x"></i>
                            </div>
                            <div class="mr-5">
                                <h2>Gérer les Donations</h2>
                            </div>
                        </div>
                        <br><br>
                        <a class="card-footer text-white clearfix medium z-1" href="${GererDonationLink }">
                            <span class="float-left">voir les détails</span>
                            <span class="float-right">
                                <i class="fas fa-angle-right fa-lg"></i>
                            </span>
                        </a>
                    </div>
                </div>

                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                	
                	<c:url var="GererDemandesLink" value="GestionnaireServlet">
	                	<c:param name="command" value="GererDemandes"/>
	          			<c:set scope="request" value="${gestionnaire }" var="role"/>
	                    <c:param name="role" value="${ role}" />
	               	</c:url>
                	
                    <div class="card text-white bg-warning o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-hand-holding-heart fa-2x"></i>
                            </div>
                            <div class="mr-5">
                                <h2>Gérer les Demands</h2>
                            </div>
                        </div>
                        <br><br>
                        <a class="card-footer text-white clearfix medium z-1" href="${GererDemandesLink }">
                            <span class="float-left">voir les détails</span>
                            <span class="float-right">
                                <i class="fas fa-angle-right fa-lg"></i>
                            </span>
                        </a>
                    </div>
                </div>

                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                	<c:url var="GererTransfertsLink" value="GestionnaireServlet">
	                	<c:param name="command" value="GererTransferts"/>
	          			<c:set scope="request" value="${gestionnaire }" var="role"/>
	                    <c:param name="role" value="${ role}" />
	               	</c:url>	
                    <div class="card text-white bg-secondary o-hidden h-100">
                        <div class="card-body">
                            <div class="card-body-icon">
                                <i class="fas fa-exchange-alt fa-2x"></i>
                            </div>
                            <div class="mr-5">
                                <h2>Gérer les Transfets</h2>
                            </div>
                            <br><br>
                        </div>
                        <a class="card-footer text-white clearfix medium z-1" href="${GererTransfertsLink }">
                            <span class="float-left">voir les détails</span>
                            <span class="float-right">
                                <i class="fas fa-angle-right fa-lg"></i>
                            </span>
                        </a>
                    </div>
                </div>
            </div>
            <ol class="breadcrumb mt-5 m-3">
                <li class="breadcrumb-item">
                    <a href="#">Les Donations</a>
                </li>
                <li class="breadcrumb-item active">Statiques</li>
            </ol>

            <div class="row m-3">
                <div class="table-responsive text-nowrap mt-5">
                    <table class="table table-striped">
                        <!--Table head-->
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Type </th>
                                <th>Genre</th>
                                <th>Quantite</th>
                                
                            </tr>
                        </thead>
                        <!--Table head-->

                        <tbody>
                        	<c:forEach items="${ stocks}" var="tempS">
	                            <tr>
	                                <th scope="row">${tempS.idDon }</th>
	                                <td>${tempS.type }</td>
	                                <td>${tempS.genre }</td>
	                                <td>${tempS.count }</td>
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