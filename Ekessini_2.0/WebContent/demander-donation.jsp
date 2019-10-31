<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <!DOCTYPE html>
<html lang="en">

<head>
    <title>Demander Doantion | Ekessini</title>
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
    
    <!--===============================================================================================-->

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
            	<!-- home link -->
            	<c:url var="homeLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="home"/>
	            	<c:set scope="request" value="${necessiteux.idNecessiteux}" var="IdNec"/>
            		<c:param name="idNecessiteux" value="${ IdNec}" />
	            </c:url>
	            <!-- end home link -->
	            
	            <!-- logout link -->
	            <c:url var="logoutLink" value="NecessiteuxServlet">
	            	<c:param name="command" value="logout"/>
	            	
	            </c:url>
	            <!-- end logout link -->
	            
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${homeLink }">Home</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${logoutLink }">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br>
    <br>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <div class="login100-form-title"
                    style="background-image: url(imgs/demander-donation-bg.jpg); background-size: cover;">
                    <h1 class="text-center text-white">Demander un Donation</h1>
                    <br>
                </div>
				<h5 class="text-center text-danger">${message }</h5>
                <form class="login100-form validate-form" method="post" action="NecessiteuxServlet">
                	<input type="hidden" name="command" value="DemandeInformation">
                	<input type="hidden" name="idNecessiteux" value="${idNecessiteux }">
                   
                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Type de vêtement : </span>

                        <select name="type" class="form-control input100">
                            <option value="T-shirt">T-shirt</option>
                            <option value="pantalon">Pantalon</option>
                            <option value="chaussure">Chaussure</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Pour : </span>

                        <select name="gendre" class="form-control input100">
                            <option value="m">Homme</option>
                            <option value="f">Femme</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Quantite : </span>

                        <select name="quantite" class="form-control input100">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>
                                        

                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Enregistre
                        </button>
                    </div>
                </form>
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
    <!--===============================================================================================-->
 

</body>

</html>