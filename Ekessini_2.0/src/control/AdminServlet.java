package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Donateur;
import model.DonateurDBA;
import model.Necessiteux;
import model.NecessiteuxDBA;
import model.Necessiteux_Admis;
import model.Necessiteux_AdmisDBA;
import model.PasswordGenerator;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
			case "NecessiteuxNonPList":
				ViewNecessiteuxNonP(request,response);
				break;
			case "NecessiteuxProfil":
				ViewNecessiteuxProfil(request,response);
				break;
			case "RefuserNecessiteux":
				SendRefusedEmail(request,response);
				break;
			case "ValiderNecessiteux":
				SendValidationEmail(request,response);
				break;
			case "NecessiteuxAdmisList":
				ViewNecessiteuxAdmis(request,response);
				break;
			case "ComptesBlockes":
				Gerer_Comptes_Blockes(request,response);
				break;
			case "DonateursBloques":
				Gerer_Donateurs_Bloques(request,response);
				break;
			case "DebloqueDonateur":
				Debloquer_Donateur(request,response);
				break;
			case "NecessiteuxBloques":
				Gerer_Necessiteux_Bloques(request,response);
				break;
			case "DebloqueNecessiteux":
				Debloquer_Necessiteux(request,response);
				break;
			case "home":
				Admin_profil(request,response);
				break;
			case "logout":
				Lougout_Admin(request,response);
				break;
			default :
				
				break;
		}
	}

	


	private void Debloquer_Necessiteux(HttpServletRequest request, HttpServletResponse response) {
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));		
		String email = NDBA.EmailNecessiteuxBloque(idNecessiteux);
		
		if(NDBA.DebloquerNecessiteuxCompte(idNecessiteux) == true) {
			NDBA.NotifyNecessiteuxBlocked(email);
			Gerer_Necessiteux_Bloques(request,response);
		}else {
			Gerer_Necessiteux_Bloques(request,response);
		}
		

		
		
	}


	private void Gerer_Necessiteux_Bloques(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionAdmin = request.getSession(true);
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		List<Necessiteux_Admis> necessiteux = NDBA.NecessiteuxBlocked();
		List<Necessiteux_Admis> necessiteuxBlocked = new ArrayList<Necessiteux_Admis>();
		
		for(Necessiteux_Admis x : necessiteux) {
			int nbrDemande = NDBA.CountDemandeNecessiteuxBloque(x.getIdNecessiteux());
			necessiteuxBlocked.add(new Necessiteux_Admis(x.getIdNecessiteux(),x.getNom(),x.getPrenom(),x.getSexe(),x.getNumTel(),x.getDateNais(),x.getEmail(),x.getSituationFam(),x.getPaixMois(),x.getPriorite(),x.getNbrEnfants(),nbrDemande));
		}
		
		sessionAdmin.setAttribute("role", "admin");
		sessionAdmin.setAttribute("necessiteuxBlockedList", necessiteuxBlocked);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-necessiteux-bloques.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Debloquer_Donateur(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		String email = DDBA.EmailDonateur(idDonateur);
		if(DDBA.DebloquerDonateur(idDonateur) == true) {
			DDBA.NotifyBlockedDonateur(email);
			Gerer_Donateurs_Bloques(request,response);
		}else {
			Gerer_Donateurs_Bloques(request,response);
		}	
	}


	private void Gerer_Donateurs_Bloques(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionAdmin = request.getSession(true);
		DonateurDBA DDBA = new DonateurDBA();
		
		List<Donateur> donateurs = DDBA.DonateursBlocked();
		List<Donateur> donateurBloquesDonations = new ArrayList<Donateur>();
		
		for(Donateur x : donateurs) {
			int nbrDonation = DDBA.CountDonationsDonateurBloque(x.getIdDonateur());
			donateurBloquesDonations.add(new Donateur(x.getIdDonateur(), x.getNom(),x.getPrenom(),x.getSexe(),x.getNumTel(),x.getDateNais(), x.getEmail(),nbrDonation));
		}
		
		sessionAdmin.setAttribute("role", "admin");
		sessionAdmin.setAttribute("donateursBloquesList", donateurBloquesDonations);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-donateurs-bloques.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Gerer_Comptes_Blockes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionAdmin = request.getSession(true);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("comptes-bloques-page.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Lougout_Admin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionAdmin = request.getSession(true);
		
		sessionAdmin.setAttribute("role", "admin");
		sessionAdmin.invalidate();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Admin_profil(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		Necessiteux_AdmisDBA NADBA = new Necessiteux_AdmisDBA();
		List<Donateur> TopDonateur = DDBA.TopDonateurs();
		
		int NCounter = NDBA.getNecCounter();
		int NACounter = NADBA.getNAdmCounter();
		
		HttpSession sessionAdmin = request.getSession(true);
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	//view Necessiteux's List
	private void ViewNecessiteuxNonP(HttpServletRequest request, HttpServletResponse response) {
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		List<Necessiteux> necessiteuxList = NDBA.getNecessiteuxList();
		HttpSession sessionAdmin = request.getSession(true);
		sessionAdmin.setAttribute("NecNonPerList", necessiteuxList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("NecessiteuxNonP-list.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// view Necessiteux profil
	private void ViewNecessiteuxProfil(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("IdNecessiteux");
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		Necessiteux necessiteux = NDBA.getNecessiteuxInfo(id);
		System.out.println(necessiteux.getIdNecessiteux());
		HttpSession sessionAdmin = request.getSession(true);
		sessionAdmin.setAttribute("NecProfil", necessiteux);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Necessiteux-profil.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Send refused email to necessiteux
	private void SendRefusedEmail(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("IdNecessiteuxProfil"));
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		System.out.println("id :"+id);
		String email = NDBA.getEmailNecessiteux(id);
		String numTel = request.getParameter("NecessiteuxNumTel");
		
		NDBA.sendRefusedEmail(email);
		ViewNecessiteuxNonP(request, response);	
	}
	
	
	//send Accepted Email to "Necessiteux non Admis"
	private void SendValidationEmail(HttpServletRequest request, HttpServletResponse response) {
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		int id = Integer.parseInt(request.getParameter("IdNecessiteuxvalider"));
		System.out.println("id :"+id);
		String priorite = request.getParameter("prioriteSelection");
		
		
		
		PasswordGenerator pswrdGen = new PasswordGenerator();
		String password = pswrdGen.MakePassword();
		String email = NDBA.getEmailNecessiteux(id);
		String numTel = request.getParameter("NecessiteuxNumTel");
		System.out.println("email necessiteux: "+email);
		
		NDBA.sendValiderEmail(email,priorite,password);
		NDBA.sendValiderSMS(numTel,email,password);
		NDBA.InsertIntoNecessiteuxAdmis(id,priorite,password);
		
		
		ViewNecessiteuxNonP(request,response);	
	}
	
	private void ViewNecessiteuxAdmis(HttpServletRequest request, HttpServletResponse response) {
		Necessiteux_AdmisDBA NADBA = new Necessiteux_AdmisDBA();
		List<Necessiteux_Admis> NecessiteuxAdmisList = NADBA.getNecessiteuxAdmisList();
		HttpSession sessionAdmin = request.getSession(true);
		sessionAdmin.setAttribute("NecessiteuxAdmisList", NecessiteuxAdmisList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("NecessiteuxAdmis-List.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
