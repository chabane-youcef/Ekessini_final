<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<html>

<head>
    <meta charset="utf-8" />
    <title>desolé | Ekessini</title>

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
                	<c:url var="HomeLink" value="NecessiteuxServlet">
		            	<c:param name="command" value="home"/>
		            </c:url>
	                <c:url var="logoutLink" value="NecessiteuxServlet">
		            	<c:param name="command" value="logout"/>
		            </c:url>
		            
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${HomeLink }">Home</a>
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
                <div class="login100-form-title" style="background-image: url(imgs/error-bg.png);">
                    <span class="login100-form-title-1">
                        Demande Erreur
                    </span>
                </div>

                <div class="m-5 ">
                    <h5 class="text-black text-center mb-5">quantite demander n'est pas disponible</h5>
                    <p class="text-center text-danger bold">tu dois faire quoi ?</p>

                    <div class="container-login100-form-btn justify-content-center">
                    
                    	<!-- new Demande link  -->
                    		<c:url var="NewDemandeLink" value="NecessiteuxServlet">
		            			<c:param name="command" value="NewDemande"/>
		            			
		            			<c:set scope="request" value="${idNecessiteux}" var="IdNec"/>
            					<c:param name="idNecessiteux" value="${ IdNec}" />
		            		</c:url>
		            
                        <a class="login100-form-btn m-3 text-white" href="${NewDemandeLink }">
                            Autre Demande
                        </a>
                        
                        <!-- Demande en attente link  -->
                        	<c:url var="DemandeAttenteLink" value="NecessiteuxServlet">
		            			<c:param name="command" value="DemandeAttente"/>
		            			
		            			<c:set scope="request" value="${idNecessiteux}" var="IdNec"/>
            					<c:param name="idNecessiteux" value="${ IdNec}" />
            					
            					<c:set scope="request" value="${idDon}" var="IdDon"/>
            					<c:param name="idDon" value="${ IdDon}" />
            					
            					<c:set scope="request" value="${quantite}" var="Quantite"/>
            					<c:param name="quantite" value="${ Quantite}" />
		            		</c:url>
		            		
                        <a class="login100-form-btn m-3 text-white" href="${DemandeAttenteLink }">
                            Demande en Attente
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </div>



    <!--Script section-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!--===============================================================================================-->
    <script src="js/scrolling-nav.js"></script>
</body>

</html>