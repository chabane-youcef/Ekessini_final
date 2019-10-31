<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Gerer Transferts | Ekessini</title>
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
                        <a class="nav-link js-scroll-trigger" href="${LogoutLink }">logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- end of navigation bar-->
    <br>
    <br>

    <div class="container">
        <h1 class="m-5 text-center">Gerer Les Transferts</h1>
        <div class="row justify-content-center align-items-center mt-5">
            <div class="col-sm-6 col-md-4 col-lg-4 text-center mb-4">
            
            		<c:url var="EffectuerTransfertLink" value="GestionnaireServlet">
	                	<c:param name="command" value="EffectuerTransfert"/>
	          			<c:set scope="request" value="${role }" var="Role"/>
	                    <c:param name="role" value="${ Role}" />
	               	</c:url>
	               	
                <div class="card bg-light p-4">
                    <a href="${EffectuerTransfertLink }"><img src="imgs/effectuer-transfert.png" alt="effectuer Transfert icon"
                            class="card-img-top"></a>
                    <div class="card-footer bg-light">
                        <a href="${EffectuerTransfertLink }" class="btn btn-outline-success">Effevtuer Transfert</a>
                    </div>
                </div>

            </div><br><br>


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