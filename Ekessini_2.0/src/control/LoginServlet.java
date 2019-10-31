package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Demande;
import model.Donateur;
import model.DonateurDBA;
import model.Donation;
import model.Emplacement;
import model.GestionnaireDBA;
import model.Necessiteux;
import model.NecessiteuxDBA;
import model.Necessiteux_AdmisDBA;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public LoginServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionLogin = request.getSession(true);
		String email= request.getParameter("email");
		String pswrd = request.getParameter("pswrd");
		
		DonateurDBA DDBA = new DonateurDBA();
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		String message="compte n'existe pas! ou bien il est blocké";
		
		if(email.equals("tarekBoufar@gmail.com") && pswrd.equals("123456")) {
			
			Login_Admin(request,response);
			
		}else if(email.equals("youcefChabane@gmail.com") && pswrd.equals("123456")){
			Login_Gestionnaire(request,response);
		
		}else if(DDBA.LoginDonateur(email,pswrd) == false){
			Login_Donateur(response,request);
			
		}else if(NDBA.LoginNecessiteux(email,pswrd) == false) {
			Login_Necessiteux(request,response);
			
		}else {
			
			
			sessionLogin.setAttribute("loginmessage", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}


	


	private void Login_Necessiteux(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		String email = request.getParameter("email");
		int IdNecessiteux = NDBA.GetIdNecessiteux(email);
		 Necessiteux necessiteux = NDBA.GetNecessiteuxInformations(IdNecessiteux);
		 List<Demande> demandes = NDBA.getListDemandes(IdNecessiteux);
		 
		 
		 NecessiteuxSession.setAttribute("necessiteux", necessiteux);
		 NecessiteuxSession.setAttribute("demandesList", demandes);
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("Necessiteux-main.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	private void Login_Gestionnaire(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		List<Emplacement> stocks = GDBA.ListEmplacementValider();
		List<Demande> demandesAttente = GDBA.ListDemandesWaiting();
		
		
		if(demandesAttente.isEmpty() == true) {
			System.out.println("no demande en attente");
		}else {
			for(Demande x : demandesAttente) {
				
				
				
				if(GDBA.EmplacementDisponible(x.getIdDepot(),x.getIdDon(),x.getQuantite())== true) {
					if(GDBA.ReserveEmplacement(x.getIdDepot(),x.getIdDon(),x.getQuantite()) == true) {
						
						LocalDate futureDate = LocalDate.now().plusDays(3);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
						String date = futureDate.format(formatter);
						GDBA.UpdateDemande(x.getIdDemande(),date);
						
						String email = GDBA.GetEmail(x.getIdNecessiteux());
						GDBA.NotifyDemande_Attent_eNecessiteux(email,date);
					}
				}else {
					System.out.println("mafihach demande en attente");
				}
			}
		}
		
		GestionnaireSession.setAttribute("nom", "Chabane");
		GestionnaireSession.setAttribute("prenom", "Youcef");
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		GestionnaireSession.setAttribute("stocks", stocks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Gestionnaire-main.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Login_Admin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionAdmin = request.getSession(true);
		
		DonateurDBA DDBA = new DonateurDBA();
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		Necessiteux_AdmisDBA NADBA = new Necessiteux_AdmisDBA();
		List<Donateur> TopDonateur = DDBA.TopDonateurs();
		
		int NCounter = NDBA.getNecCounter();
		int NACounter = NADBA.getNAdmCounter();
		
		sessionAdmin.setAttribute("nom", "Boufar");
		sessionAdmin.setAttribute("prenom", "Tarek");
		sessionAdmin.setAttribute("NecCounter", NCounter);
		sessionAdmin.setAttribute("NAdmCounter", NACounter);
		sessionAdmin.setAttribute("topDonateur", TopDonateur);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin-main.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void Login_Donateur(HttpServletResponse response, HttpServletRequest request) {
		HttpSession sessionDonateur = request.getSession(true);
		
		DonateurDBA DDBA = new DonateurDBA();
		String email = request.getParameter("email");
		int idDonateur = DDBA.getID(email);
		System.out.println("id donateur login : "+idDonateur);
		List<Donation> donationList = DDBA.getDonateur_Donations(idDonateur);
		
		
		
		sessionDonateur.setAttribute("idDonateur", idDonateur);
		sessionDonateur.setAttribute("donationList", donationList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Donateur-profil.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}


