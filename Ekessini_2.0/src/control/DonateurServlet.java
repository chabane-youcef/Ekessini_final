package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Chambre;
import model.Depot;
import model.Donateur;
import model.DonateurDBA;
import model.Donation;

/**
 * Servlet implementation class DonateurServlet
 */
@WebServlet("/DonateurServlet")
public class DonateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonateurServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
		case "deleteDonation":
			delete_Donation(request,response);
			break;
		case "ajouterDonation":
			ajoute_Donation(request,response);
			break;
		case "oldDonation":
			List_Old_Donations(request,response);
			break;
		case "DeleteDonateurAccount":
			Delete_Donateur(request,response);
			break;
		case "home":
			profil_donateur(request,response);
			break;
		case "logout":
			log_out(request,response);
			break;
		default:
			break;
		}
	}

	
	private void Delete_Donateur(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		
		if(DDBA.ReleaseEmplacements(idDonateur) == true) {
			DDBA.DeleteDonationNonValider(idDonateur);
			DDBA.BloquerDonateur(idDonateur);
			log_out(request,response);
		}else {
			profil_donateur(request,response);
		}
		

	}


	private void log_out(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateur = request.getSession(true);
		
		sessionDonateur.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void List_Old_Donations(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateur = request.getSession(true);
		
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		
		DonateurDBA DDBA = new DonateurDBA();
		
		List<Donation> oldDonations = DDBA.GetOldDonations(idDonateur);
		
		sessionDonateur.setAttribute("oldDonations", oldDonations);
		sessionDonateur.setAttribute("idDonateur", idDonateur);
		RequestDispatcher dispatcher = request.getRequestDispatcher("donateur-old-donations.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void ajoute_Donation(HttpServletRequest request, HttpServletResponse response) {
		String idDonateur = request.getParameter("idDonateur");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ajouteDonation.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	private void delete_Donation(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		
		int idDonation = Integer.parseInt(request.getParameter("idDonation"));
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		
		System.out.println("idDonation:"+idDonation+",idDonateur: "+idDonateur+",idDon: "+idDon+",idDepot: "+idDepot+",quantite: "+quantite);
		
		
		if(DDBA.ResetEmplacement(idDonateur,idDon,idDepot,quantite) == true) {
			if (DDBA.deleteDoantion(idDonation) == true){
				profil_donateur(request,response);
			}
		}else {
			profil_donateur(request,response);
		}
		
	}


	private void profil_donateur(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		System.out.println("id donateur login : "+idDonateur);
		List<Donation> donationList = DDBA.getDonateur_Donations(idDonateur);
		
		HttpSession sessionDonateur = request.getSession(true);
		
		sessionDonateur.setAttribute("donationList", donationList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Donateur-profil.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
		case "inscription":
			InscriptionDonateur(request,response);
			break;
		case "DonationDonateur":
			DonationDonateur(request,response);
			break;
		case "selectionDepot":
			SelectionDepot(request,response);
			break;
		case "ajoutAutreDonation":
			ajouter_Autre_Donation(request,response);
			break;
		case "selectionDepot2":
			Selection_Autre_Depot(request,response);
			break;
		default :
			break;
				
		}
	}

	


	/*inscription de donateur*/
	private void InscriptionDonateur(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String dateNais = request.getParameter("dateNais");
		String Sexe = request.getParameter("sexe");
		String email = request.getParameter("email");
		String numTel = request.getParameter("numero");
		
		numTel = numTel.substring(1);
		
		String  pswrd = request.getParameter("pass");
		
		Donateur donateur = new Donateur(nom,prenom,Sexe,numTel,dateNais,email);
			if(!DDBA.CheckEmail(email)) {
				if(DDBA.InsertMainInfos(donateur)) {
					int id = DDBA.getID(email);
					if(DDBA.registerPassword(id,pswrd)) {
						HttpSession sessionDonateur = request.getSession(true);
						
						DDBA.sendCompteInformationsViaEmail(donateur,pswrd);
						
						DDBA.sendCompteInformationsViaSMS(numTel,donateur);
						
						sessionDonateur.setAttribute("idDonateur",id );
						RequestDispatcher dispatcher = request.getRequestDispatcher("donation-donateur.jsp");
						try {
							dispatcher.forward(request, response);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("inscription-donateur.jsp");
					try {
						dispatcher.forward(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("inscription-donateur.jsp");
				try {
					dispatcher.forward(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	}
	
	
	/* fair un donation pour la 1ere fois*/
	private void DonationDonateur(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateur = request.getSession(true);
		
		DonateurDBA DDBA = new DonateurDBA();
		String type = request.getParameter("type");
		String genre = request.getParameter("gendre");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int period = Integer.parseInt(request.getParameter("period"));
		String time = request.getParameter("time");
		
		int idDonateur = Integer.parseInt(request.getParameter("DonateurId"));
		
		int idDon = DDBA.getIdType(type,genre);
		List<Depot> depots = DDBA.getListDepot();
		
		sessionDonateur.setAttribute("idDonateur", idDonateur);
		sessionDonateur.setAttribute("idDon", idDon);
		sessionDonateur.setAttribute("quantite", quantite);
		sessionDonateur.setAttribute("time", time);
		sessionDonateur.setAttribute("depots", depots);
		sessionDonateur.setAttribute("period", period);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("Selection-Depot.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
	private void SelectionDepot(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateur = request.getSession(true);
		DonateurDBA DDBA = new DonateurDBA();
		
		int idDepot = Integer.parseInt(request.getParameter("depot"));
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int period = Integer.parseInt(request.getParameter("period"));
		
		String time = request.getParameter("time");
		
		List<Chambre> ChambresDispo = DDBA.EmplacementsValable(idDepot,idDon,quantite);
		
		if(ChambresDispo.isEmpty() == true) {
			String message ="quantite n'est pas disponible dans le Depot choisis!";
			sessionDonateur.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			DDBA.InsertIntoDonation(idDonateur,idDepot,idDon,quantite,time,period);
			
			Chambre x = ChambresDispo.get(0);
			
			DDBA.ReserverEmplacements(x.getIdChambre(),idDepot,idDon,idDonateur,quantite);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	/* pour ajouter un autre donation*/
	private void ajouter_Autre_Donation(HttpServletRequest request, HttpServletResponse response) {
		DonateurDBA DDBA = new DonateurDBA();
		HttpSession sessionDonateur = request.getSession(true);
		
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		String type = request.getParameter("type");
		String genre = request.getParameter("gendre");
		String time = request.getParameter("time");
		int period = Integer.parseInt(request.getParameter("period"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		int idDon = DDBA.getIdType(type,genre);
		
		List<Depot> depots = DDBA.getListDepot();
		sessionDonateur.setAttribute("idDonateur", idDonateur);
		sessionDonateur.setAttribute("idDon", idDon);
		sessionDonateur.setAttribute("quantite", quantite);
		sessionDonateur.setAttribute("time", time);
		sessionDonateur.setAttribute("period", period);
		sessionDonateur.setAttribute("depots", depots);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("Selection-Depot2.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}

		
		
	}
	
	private void Selection_Autre_Depot(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateur = request.getSession(true);
		
		DonateurDBA DDBA = new DonateurDBA();
		int idDepot = Integer.parseInt(request.getParameter("depot"));
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		String time = request.getParameter("time");
		int period = Integer.parseInt(request.getParameter("period"));
		
		List<Chambre> ChambresDispo = DDBA.EmplacementsValable(idDepot,idDon,quantite);
		
		if(ChambresDispo.isEmpty() == true) {
			String message ="quantite n'est pas disponible dans le Depot choisis!";
			sessionDonateur.setAttribute("message", message);
			sessionDonateur.setAttribute("idDonateur", idDonateur);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ajouteDonation.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			DDBA.InsertIntoDonation(idDonateur, idDepot, idDon, quantite, time, period);
			Chambre x = ChambresDispo.get(0);
			DDBA.ReserverEmplacements(x.getIdChambre(),idDepot,idDon,idDonateur,quantite);
			
			Profil_Donateur(response,request);
		}
	}
	
	
	private void Profil_Donateur(HttpServletResponse response, HttpServletRequest request) {
		DonateurDBA DDBA = new DonateurDBA();
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		System.out.println("id donateur login : "+idDonateur);
		List<Donation> donationList = DDBA.getDonateur_Donations(idDonateur);
		
		HttpSession sessionDonateur = request.getSession(true);
		
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
