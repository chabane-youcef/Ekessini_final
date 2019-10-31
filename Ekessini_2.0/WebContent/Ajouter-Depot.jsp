<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.*,control.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Ajouter Depot | Ekessini</title>
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
  <link rel="stylesheet" href="css/depot.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
      
      					<c:url var="HomeLink" value="GestionnaireServlet">
	                        <c:param name="command" value="home"/>
	                        
	                    </c:url>
	                    
	                    <c:url var="LogoutLink" value="GestionnaireServlet">
	                        <c:param name="command" value="logout"/>
	                        
	                    </c:url>
	                    
        <ul class="navbar-nav ml-auto">
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



  <!-- HIDDEN DYNAMIC ELEMENT TO CLONE -->
  <!-- you can replace it with any other elements -->
  <div class="form-group dynamic-element mb-3" style="display:none">
    <div class="row">
      <div class="col-md-2"></div>

      <!-- Replace these fields -->
      <div class="col-md-3">
        <span class="label-input100"> Chambre</span>
        <select id="chambre" name="chambre" class="form-control">
          <option value="T-shirt">T-shrit</option>
          
          <option value="pantlon">Pantlon</option>
          
          <option value="chaussure">Chaussure</option>
          
        </select>
      </div>
      <div class="col-md-2">
        
        <select id="capacite-permanant" name="capacite-permanant" class="form-control">
        	<option value="10">10</option>
            <option value="50">50</option>
            <option value="100">100</option>
            <option value="150">150</option>
            <option value="200">200</option>
            <option value="250">250</option>
            <option value="300">300</option>
            <option value="350">350</option>
            <option value="400">400</option>
            <option value="450">450</option>
            <option value="500">500</option>
            
        </select>
      </div>
      
      	<div class="col-md-2">
			        
			<select id="capacite-non-permanant" name="capacite-non-permanant" class="form-control">
				<option value="8">8</option>
				<option value="20">20</option>
			    <option value="40">40</option>
			    <option value="80">80</option>
			    <option value="100">100</option>

			</select>
		</div>
      <!-- End of fields-->
      <div class="col-md-1">
        <p class="delete">x</p>
      </div>
    </div>
  </div>
  <!-- END OF HIDDEN ELEMENT -->





  <div class="form-container">
    <form class="form-horizontal" method="post" action="GestionnaireServlet">
    <input type="hidden" name="command" value="EnregistrerDepot">
    <input type="hidden" name="role" value="${role }">
      <fieldset>
        <!-- Form Name -->
        <legend class="title">Ajouter Depot</legend>
        <div class="form-group">
          <div class="row align-content-center">
          
            <input type="text" name="depot" class="form-control m-5" placeholder="Localisation de Depot" required>
            
          </div>
          
          <h3 class="text-black text-center">Ajouter Chambres</h3>
        </div>
		<div class="form-group" >
    		<div class="row">
      			<div class="col-md-2"></div>

			      <!-- Replace these fields -->
			      <div class="col-md-3">
			        <span class="label-input100"> Chambre</span>
			        <select id="chambre" name="chambre" class="form-control">
			          <option value="T-shirt">T-shrit</option>
			          
			          <option value="pantlon">Pantlon</option>
			          
			          <option value="chaussure">Chaussure</option>
			          
			        </select>
			      </div>
      
      
			      <div class="col-md-2">
			        <select id="capacite-permanant" name="capacite-permanant" class="form-control">
			        	<option value="10">10</option>
			            <option value="50">50</option>
			            <option value="100">100</option>
			            <option value="150">150</option>
			            <option value="200">200</option>
			            <option value="250">250</option>
			            <option value="300">300</option>
			            <option value="350">350</option>
			            <option value="400">400</option>
			            <option value="450">450</option>
			            <option value="500">500</option>
			        </select>
			      </div>
      
			      <div class="col-md-2">
			        
			        <select id="capacite-non-permanant" name="capacite-non-permanant" class="form-control">
			        	<option value="8">8</option>
			            <option value="20">20</option>
			            <option value="40">40</option>
			            <option value="80">80</option>
			            <option value="100">100</option>

			        </select>
			      </div>
		      <!-- End of fields-->
		      <div class="col-md-1">
		        <p class="delete">x</p>
		      </div>
		    </div>
		</div>
		
        <div class="dynamic-stuff">
          <!-- Dynamic element will be cloned here -->
          <!-- You can call clone function once if you want it to show it a first element-->
        </div>

        <!-- Button -->
        <div class="form-group">
          <div class="row">
            <div class="col-md-12">
              <p class="add-one">+ Chambre</p>
            </div>
            <div class="col-md-5"></div>
            <div class="col-md-6 ">
              <button id="singlebutton" name="singlebutton" class="btn btn-success ">Enregistrer</button>
            </div>
          </div>
        </div>
      </fieldset>
    </form>
  </div>
<script>
  $('.add-one').click(function(){
    $('.dynamic-element').first().clone().appendTo('.dynamic-stuff').show();
    attach_delete();
  });
  
  
  //Attach functionality to delete buttons
  function attach_delete(){
    $('.delete').off();
    $('.delete').click(function(){
      console.log("click");
      $(this).closest('.form-group').remove();
    });
  }
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
  <script src="js/depot.js>"></script>
</body>

</html>