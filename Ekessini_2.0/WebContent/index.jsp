<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Home | Ekessini</title>
	<link rel="icon" type="imgs/png" href="imgs/icons/favicon.ico" />
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/landing-page.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Navigation -->
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
            <a class="nav-link js-scroll-trigger" href="#home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#aider">Aider</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#produits">Produits</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#emplacement">Emplacement</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="login.jsp">Log In</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="inscription-page.jsp">Sign Up</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <header class="bg-primary text-white" id="home">
    <div class="container text-center mt-5">
      <h1 class="title">Welcome To Ekessini</h1>
      <p class="lead ">Pour ceux qui en besoin de nous</p>
      <a class="btn btn-primary btn-lg mt-5" href="inscription-page.jsp">Rejoignez-nous</a>
      <a class="btn btn-primary btn-lg mt-5" href="donation-anonyme.jsp"> Faire promisse</a>
    </div>
  </header>

  <section id="about">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2 class="text-center mb-5">Nos buts</h2>
          <div class="card-deck">
            <div class="card">
              <img src="imgs/expand (1).png" alt="help" class="img-fluid">
              <h3 class="card-title text-center">élargir notre charité</h3>
            </div>

            <div class="card">
              <img src="imgs/care (1).png" alt="help" class="img-fluid">
              <h3 class="card-title text-center">répandre l'amour</h3>
            </div>

            <div class="card">
              <img src="imgs/help.png" alt="help" class="img-fluid">
              <h3 class="card-title text-center">S'entraider</h3>
            </div>
          </div>
          <div class="text-center">
            <a href="inscription-page.jsp"><button type="button"
                class="btn btn-primary btn-lg mt-5 center-block pb-2 pt-2 pr-4 pl-4">Rejoignez-nous</button></a>
                
            <a href="donation-nonp.jsp"><button type="button" class="btn btn-primary btn-lg mt-5 center-block pb-2 pt-2 pr-4 pl-4">Faire
                promesse</button></a>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section id="aider" class="bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2 class="text-center mb-5">Nous Aidons</h2>
          <div class="card-deck">
            <div class="card">
              <img src="imgs/man.png" alt="man photo" class="img-fluid">
              <h3 class="card-title text-center">Hommes</h3>
            </div>
            <div class="card">
              <img src="imgs/girl.png" alt="man photo" class="img-fluid">
              <h3 class="card-title text-center">Femmes</h3>
            </div>
            <div class="card">
              <img src="imgs/child.png" alt="man photo" class="img-fluid">
              <h3 class="card-title text-center">Les Enfants</h3>
            </div>
          </div>
          <div class="text-center">
            <a  href="inscription-necessiteux.jsp" class="btn btn-primary btn-lg mt-5 center-block pb-2 pt-2 pr-4 pl-4 text-white">demande un rendez-vous</a>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section id="produits">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h2 class="text-center mb-5">Nos Produits</h2>
          <div class="card-deck">
            <div class="card">
              <img src="imgs/shoe.png" alt="man photo" class="img-fluid">
              <a href="ekessini-produits.jsp" class="btn btn-primary btn-sm mt-5 center-block pb-2 pt-2 pr-4 pl4 ml-3">voir les details</a>
            </div>
            <div class="card">
              <img src="imgs/trousers.png" alt="man photo" class="img-fluid">
              <a href="ekessini-produits.jsp" class="btn btn-primary btn-sm mt-5 center-block pb-2 pt-2 pr-4 pl4 ml-3">voir les details</a>
            </div>
            <div class="card">
              <img src="imgs/tshirt.png" alt="man photo" class="img-fluid">
              <a href="ekessini-produits.jsp" class="btn btn-primary btn-sm mt-5 center-block pb-2 pt-2 pr-4 pl4 ml-3">voir les details</a>
            </div>  
          </div>
        </div>
      </div>
    </div>
  </section>

  <section id="emplacement" class="bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <h1 class="text-center">Notre Emplacement</h1>

          <div id="map-container-google-2" class="map-container">

            <iframe src="https://maps.google.com/maps?q=chicago&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0"
              allowfullscreen></iframe>
          </div>

        </div>


      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Ekessini 2019</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom JavaScript for this theme -->
  <script src="js/scrolling-nav.js"></script>

</body>

</html>