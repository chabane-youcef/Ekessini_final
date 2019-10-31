<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,control.*,control.*" %>


<!DOCTYPE html>
<html lang="en">

<head>
	<title>Inscription Necessiteux | Ekessini</title>
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
					style="background-image: url(imgs/inscription-necessiteux-bg.jpg);background-size: cover; background-position: center;">
					<span class="login100-form-title-1">
						Inscription Necessiteux
					</span>
				</div>

				<form class="login100-form validate-form" method="post" action="NecessiteuxServlet">
					<input type="hidden" name="command" value="InscriptionNecessiteux">
					
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

						<select name="gendre" class="form-control input100">
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
						<input class="input100" type="text" name="numero" placeholder="Entrer votre Numero" required>
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26">
						<span class="label-input100">Situation Familiale</span>

						<select name="situationFamiliale" onchange="ChildrenFunction()" class="form-control input100">
							<option value="celebataire">célibataire</option>
							<option value="marier">marié(e)</option>
							<option value="divorce">divorcé(e)veuf(ve)</option>
							<option value="veuf">veuf(ve)</option>
						</select>
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26" id="childrenCount" style="visibility: hidden;">
						<span class="label-input100">Nombre D'enfants</span>
						<input class="input100" type="number" name="NbrEnfants" placeholder="Combien D'enfats ?">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26">
						<span class="label-input100">salaire (DZD)</span>
						<input class="input100" type="number" name="paixMoix" placeholder="Entrer votre salaire" required>
						<span class="focus-input100"></span>
					</div>



					<div class="flex-sb-m w-full p-b-30">


						<div>
							<a href="login.jsp" class="txt1">
								tu a deja un account ?
							</a><br>
							<p class="text-danger">${message }</p>
						</div>
						
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Inscrire
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
	<script type="text/javascript">
		function ChildrenFunction() {
			var childrenCountInput = document.getElementById("childrenCount");
			if (document.forms[0].situationFamiliale.options[document.forms[0].situationFamiliale.selectedIndex].value ==
				"veuf" || "marie" || "divorce") {
				childrenCountInput.style.visibility = "visible";
			} else {
				childrenCountInput.style.visibility = "hidden";
			}
		}
	</script>

</body>

</html>