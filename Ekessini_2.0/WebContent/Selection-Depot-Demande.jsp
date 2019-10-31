<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <meta charset="utf-8" />
    <title>Selectionner Depot | Ekessini</title>
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
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="vendor/gijgo/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <link href="vendor/gijgo/css/gijgo.min.css" rel="stylesheet" type="text/css" />
</head>

<body>
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
                        <a class="nav-link js-scroll-trigger" href="Necessiteux-main.jsp">Home</a>
                    </li>
				                   
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="login.jsp">Logout</a>
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
                    style="background-image: url(imgs/donation-bg.jpg); background-position: bottom;">

                </div>

                <form class="login100-form validate-form" method="post" action="NecessiteuxServlet">
					<input type="hidden" name="command" value="selectionDepotDemande"/>
					<input type="hidden" name="idNecessiteux" value="${ idNecessiteux}"/>
					<input type="hidden" name="idDon" value="${ idDon}"/>
					<input type="hidden" name="quantite" value="${quantite }"/>
					
					<div class="wrap-input100 validate-input m-b-26">
					 	<span class="label-input100">Date : </span>
                        
                        <input name="time" id="input" width="312" />
                        <span class="focus-input100"></span>
                    </div>
					
                    <div class="wrap-input100 validate-input m-b-26 mb-5">
                        <span class="label-input100">Depot : </span>
						
                        <select name="depot" class="form-control input100">
                        	<c:forEach items="${ Depots}" var="tempDepots">
                            	<option value="${tempDepots.idDepot }">${tempDepots.localisation }</option>
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

    

    <!--Script section-->
    <script>
        $('#input').datepicker({
            footer: true,
            modal: true
        });
    </script>
    <!--==============================================================================================-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!--===============================================================================================-->
    <script src="js/scrolling-nav.js"></script>
</body>

</html>