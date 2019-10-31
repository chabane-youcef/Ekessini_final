<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Effectuer Transfert | Ekessini</title>
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
    <br>
    <br>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <div class="login100-form-title"
                    style="background-image: url(imgs/effectuer-transfert-bg.jpg); background-size: cover;">
                    <h1 class="text-center text-white">Effectuer Transfert</h1>
                    <br>
                </div>
				<h5 class="text-center text-danger">${message }</h5>
                <form class="login100-form validate-form" method="post" action="GestionnaireServlet">
                
                	<input type="hidden" name="command" value="EnregistrerTransfert">
                	
                	
                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Depot : </span>
	                        <select name="depot1" class="form-control input100">
	                        	<c:forEach items="${ listDepots}" var="tempD">
	                            	<option value="${ tempD.idDepot}">${tempD.localisation}</option>
	                        	</c:forEach>
	                        </select>
                        <span class="focus-input100"></span>
                    </div>

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

                        <select name="genre" class="form-control input100">
                            <option value="m">Homme</option>
                            <option value="f">Femme</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Quantite : </span>

                        <select name="quantite" class="form-control input100">
                            <option value="2">2</option>
                            <option value="4">4</option>
                            <option value="6">6</option>
                            <option value="8">8</option>
                            <option value="10">10</option>
                            <option value="12">12</option>
                            <option value="14">14</option>
                            <option value="16">16</option>
                            <option value="18">18</option>
                            <option value="20">18</option>
                            <option value="22">22</option>
                            <option value="24">24</option>
                            <option value="26">26</option>
                            <option value="28">28</option>
                            <option value="30">30</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Vers Depot : </span>
                        
	                        <select name="depot2" class="form-control input100">
	                        	<c:forEach items="${ listDepots}" var="tempD">
	                            	<option value="${ tempD.idDepot}">${tempD.localisation}</option>
	                            </c:forEach>	
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