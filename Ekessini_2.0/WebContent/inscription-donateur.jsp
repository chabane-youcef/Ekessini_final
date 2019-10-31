<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Inscription Donateur | Ekessini</title>
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
				<div class="login100-form-title" style="background-image: url(imgs/incription-bg.jpg);">
					<span class="login100-form-title-1">
						Inscription Donateur
					</span>
				</div>

				<form class="login100-form validate-form" method="post" action="DonateurServlet">
					<input type="hidden" name="command" value="inscription">
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
						<span class="label-input100">Date de Naissance</span>
						<input class="input100" type="text" name="dateNais" placeholder="year-month-day" required>
						<span class="focus-input100"></span>
                    </div>
                    
                    <div class="wrap-input100 validate-input m-b-26">
						<span class="label-input100">Sexe</span>

                        <select name="sexe" class="form-control input100">
                            <option value="autre">Autre</option>
                            <option value="m">Homme</option>
                            <option value="f">Femme</option>
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
                    
					<div class="wrap-input100 validate-input m-b-18">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="pass" placeholder="Entrer password" required>
						<span class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-b-30">


						<div>
							<a href="login.jsp" class="txt1">
								tu a deja un account ?
							</a><br>
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

</body>

</html>