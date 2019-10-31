<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,control.*,control.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>comptes bloques | Ekessini</title>
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
                        <a class="nav-link js-scroll-trigger" href="index.jsp">Home</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="login.jsp">logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- end of navigation bar-->
    <br>
    <br>

    <div class="container">
        <h1 class="m-5 text-center">Gerer Les Comptes Bloques</h1>
        <div class="row justify-content-center align-items-center mt-5">
            <div class="col-sm-6 col-md-4 col-lg-4 text-center mb-4">
                <div class="card bg-light p-4">
                
		                <c:url var="DonateursBloquesLink" value="AdminServlet">
							<c:param name="command" value="DonateursBloques" />				
						</c:url>
						
                    <a href="${DonateursBloquesLink }"><img src="imgs/blocked-donateur.png" alt="effectuer Transfert icon"
                            class="card-img-top"></a>
                    <div class="card-footer bg-light">
                        <a href="${DonateursBloquesLink }" class="btn btn-outline-success">Donateurs Bloqués</a>
                    </div>
                </div>

            </div><br><br>

            <div class="col-sm-6 col-md-4 col-lg-4 text-center mb-4">
                <div class="card bg-light p-4">
                
                		<c:url var="NecessiteuxBloquesLink" value="AdminServlet">
							<c:param name="command" value="NecessiteuxBloques" />				
						</c:url>
						
                    <a href="${NecessiteuxBloquesLink }"><img src="imgs/blocked-necessiteux.png" alt="valider Transfert icon"
                            class="card-img-top"></a>
                    <div class="card-footer bg-light">
                        <a href="${NecessiteuxBloquesLink }" class="btn btn-outline-success">Necessiteux Bloqés</a>
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