<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Donation Anonyme | Ekessini</title>
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
    <link rel="stylesheet" type="text/css" href="css/login2-util.css">
    <link rel="stylesheet" type="text/css" href="css/login2.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="vendor/gijgo/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <link href="vendor/gijgo/css/gijgo.min.css" rel="stylesheet" type="text/css" />
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
                        <a class="nav-link js-scroll-trigger" href="inscription-page.jsp">Signin</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="login.jsp">Login</a>
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
                    style="background-image: url(imgs/donation-anonyme-bg.jpg);background-size: cover; background-position: center;height: 250px;">
                    <span class="login100-form-title-1">
                        Donation Anonyme
                    </span>
                </div>
				<h5 class="text-center text-danger">${message }</h5>
                <form class="login100-form validate-form" method="post" action="DonateurAnonymeServlet">
                	<input type="hidden" name="command" value="donationAnonyme">
                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Nom</span>
                        <input class="input100" type="text" name="nom" placeholder="Entrer Nom" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Prenom</span>
                        <input class="input100" type="text" name="prenom" placeholder="Entrer Prenom" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Birth Day</span>
                        <input class="input100" type="text" name="birthday" placeholder="year-month-day" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Gendre</span>

                        <select name="genre" class="form-control input100">
                            <option value="autre">Autre</option>
                            <option value="homme">Homme</option>
                            <option value="femme">Femme</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Email Adresse</span>
                        <input class="input100" type="text" name="email" placeholder="Entrer Email" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Nemuro de Telephone</span>
                        <input class="input100" type="text" name="numTel" placeholder="Entrer votre Numero" required>
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

                        <select name="genreType" class="form-control input100">
                            <option value="m">Homme</option>
                            <option value="f">Femme</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Quantite</span>
                           
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
                            <option value="20">20</option>                            
                        </select>    
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-26">
                        <span class="label-input100">Date</span>
                        <input name="date" id="input" width="312" placeholder="date" required/>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Enregistrer
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        $('#input').datepicker({
            footer: true,
            modal: true
        });
    </script>
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