package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import model.Chambre;
import model.Demande;
import model.Depot;
import model.DonateurAnonymeDBA;
import model.Donation;
import model.Emplacement;
import model.GestionnaireDBA;


@WebServlet("/GestionnaireServlet")
public class GestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GestionnaireServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
		case "AjouterDepot":
			Ajouter_Depot_Demande(request,response);
			break;
		case "EnregistrerDepot":
			Enregistrer_Depot(request,response);
			break;
		case "GererDepots":
			Afficher_Depots(request,response);
			break;
		case "ProfilDepot":
			Profil_Depot(request,response);
			break;
		case "ModifierDepot":
			Modifier_Depot(request,response);
			break;
		case "DeleteDepot":
			Delete_Depot(request,response);
			break;
		case "GererDonations":
			Gerer_Donations(request,response);
			break;
		case "GererDonationNonPermanant":
			Afficher_Donations_NonPermanant(request,response);
			break;
		case "validerDonationNonP":
			Valider_Donation_NonP(request,response);
			break;
		case "RefuserDonationNonP":
			Refuser_Donation_NonP(request,response);
			break;
		case "GererDonationPermanant":
			Afficher_Donations_permanat(request,response);
			break;
		case "GererDemandes":
			Afficher_Demandes(request,response);
			break;
		case "validerDonation":
			Valider_Donation(request,response);
			break;
		case "RefuserDonation":
			Refuser_Donation(request,response);
			break;
		case "RefuserDemande":
			Refuser_Demande(request,response);
			break;
		case "ValiderDemande":
			Valider_Demande(request,response);
			break;
		case "GererTransferts":
			Gerer_Transfert(request,response);
			break;
		case "EffectuerTransfert":
			Effectuer_Transfert(request,response);
			break;
		case "home":
			profil_Gestionnaire(request,response);
			break;
		case "logout":
			Logout_Gestionnaire(request,response);
			break;
		default:
			break;
		}
	}

	
	


	private void Effectuer_Transfert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession();
		GestionnaireDBA GDBA = new GestionnaireDBA();
		List<Depot> depots = GDBA.listDepot();
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		GestionnaireSession.setAttribute("listDepots", depots);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("effectuer-transfert.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Gerer_Transfert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true); 
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("transfert-page.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Modifier_Depot(HttpServletRequest request, HttpServletResponse response) {
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		String localisation = GDBA.DepotLocalisation(idDepot);
		
		HttpSession GestionnaireSession = request.getSession(true);
		
		GestionnaireSession.setAttribute("idDepot", idDepot);
		GestionnaireSession.setAttribute("localisation", localisation);
		GestionnaireSession.setAttribute("role", "gestionnaire");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-depot.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Profil_Depot(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		String localisation = GDBA.DepotLocalisation(idDepot);
		
		List<Chambre> ListChambres = GDBA.GetChambresList(idDepot);
		List<Chambre> ChambresDetails = new ArrayList<Chambre>();
		
		for(Chambre x : ListChambres) {
			int idDonM=0;
			int idDonF=0;
			
			if(x.getType().equals("T-shirt")) {
				idDonM=1;
				idDonF=2;
			}else if(x.getType().equals("pantlon")) {
				idDonM=3;
				idDonF=4;
			}else if(x.getType().equals("chaussure")) {
				idDonM=5;
				idDonF=6;
			}
			
			int PMOC = GDBA.CountPMOC(x.getIdChambre(),idDonM);
			int PM = GDBA.CountPM(x.getIdChambre(), idDonM);
			
			int PFOC =GDBA.CountPFOC(x.getIdChambre(),idDonF);
			int PF = GDBA.CountPF(x.getIdChambre(),idDonF);
			
			int NPMOC = GDBA.CountNPMOC(x.getIdChambre(),idDonM);
			int NPM = GDBA.CountNPM(x.getIdChambre(), idDonM);
			
			int NPFOC = GDBA.CountNPFOC(x.getIdChambre(),idDonF);
			int NPF = GDBA.CountNPF(x.getIdChambre(),idDonF);
			
			ChambresDetails.add(new Chambre(idDepot,x.getIdChambre(),x.getType(),x.getMax(),PMOC,PM,PFOC,PF,NPMOC,NPM,NPFOC,NPF));
		}
		
		GestionnaireSession.setAttribute("chambres", ChambresDetails);
		GestionnaireSession.setAttribute("idDepot", idDepot);
		GestionnaireSession.setAttribute("localisation", localisation);
		GestionnaireSession.setAttribute("role", "gestionnaire");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("depot-page.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Refuser_Donation_NonP(HttpServletRequest request, HttpServletResponse response) {
		int idDonationNonP = Integer.parseInt(request.getParameter("idDonation"));
		int idDonateurNonP = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		DonateurAnonymeDBA DADBA = new DonateurAnonymeDBA();
		if(DADBA.ResetEmplacement(idDonateurNonP,idDepot,idDon,quantite)) {
			DADBA.DeleteDonateurNonP(idDonateurNonP);
		}
		
		
		
		Afficher_Donations_NonPermanant(request,response);
	}


	private void Valider_Donation_NonP(HttpServletRequest request, HttpServletResponse response) {
		int idDonationNonP = Integer.parseInt(request.getParameter("idDonation"));
		int idDonateurNonP = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantitie = Integer.parseInt(request.getParameter("quantite"));
		
		DonateurAnonymeDBA DADBA = new DonateurAnonymeDBA();
		
		if(DADBA.UpdateEmplacement(idDonateurNonP) == true) {
			DADBA.ValiderDonationNonP(idDonationNonP);
		}
		
		Afficher_Donations_NonPermanant(request,response);
	}


	private void Afficher_Donations_NonPermanant(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		List<Donation> donationsNonP = GDBA.GetDonationsNonPList();
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		GestionnaireSession.setAttribute("donationsNonP", donationsNonP);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("List-Donations-NonP.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Gerer_Donations(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		GestionnaireSession.setAttribute("role", "Gestionnaire");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("donations-page.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	private void Logout_Gestionnaire(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		
		GestionnaireSession.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Valider_Demande(HttpServletRequest request, HttpServletResponse response) {
		int idDemande = Integer.parseInt(request.getParameter("idDemande"));
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		if(GDBA.ValiderDemande(idDemande) == true) {
			GDBA.ResetNecessiteuxEmplacements(idDepot,idDon,quantite);
		}
		
		
		Afficher_Demandes(request,response);
	}


	private void Refuser_Demande(HttpServletRequest request, HttpServletResponse response) {
		int idDemande = Integer.parseInt(request.getParameter("idDemande"));
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int nbrAvertissement = Integer.parseInt(request.getParameter("nbrAvertissement"));
		String email = request.getParameter("email");
		String numTel = request.getParameter("numTel");
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		nbrAvertissement++;
		if(nbrAvertissement < 3) {
			if(GDBA.UpdateNbrAvertissement(idNecessiteux,nbrAvertissement) == true){
				if(GDBA.ViderEmplacement(idDon,idDepot,quantite) == true) {
					GDBA.RefuserDemande(idDemande);
					GDBA.AlertNecessiteux(email,nbrAvertissement);
				}
			}
			
		}else if(nbrAvertissement >= 3) {
			if(GDBA.UpdateNbrAvertissement(idNecessiteux,nbrAvertissement) == true) {
				List<Demande> demandes = GDBA.BlockedNecessiteuxDemandes(idNecessiteux);
				
				for(Demande x : demandes) {
					GDBA.ViderEmplacement(x.getIdDon(),x.getIdDepot(),x.getQuantite());
				}
				GDBA.BlockNecessiteux(idNecessiteux,nbrAvertissement);
				GDBA.NotifyNecessiteux(email,nbrAvertissement);
			}
		}
		
		Afficher_Demandes(request,response);
		
	}


	private void Afficher_Demandes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		List<Demande> demandes = GDBA.GetDemandesList();
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		GestionnaireSession.setAttribute("demandes", demandes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("List-Demandes.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Refuser_Donation(HttpServletRequest request, HttpServletResponse response) {
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		int idDonation = Integer.parseInt(request.getParameter("idDonation"));
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int nbrAvertissement = Integer.parseInt(request.getParameter("nbrAvertissement"));
		String email = request.getParameter("email");
		String numTel = request.getParameter("numTel");
		String type = request.getParameter("type");
		String genre = request.getParameter("genre");
		
		int idChambre = GDBA.GetIdChambre(idDepot,type);
		
		
		nbrAvertissement++;
		if(nbrAvertissement <3) {
			if(GDBA.UpdateNbrAvertissementDonateur(idDonateur,nbrAvertissement)==true) {
				if(GDBA.ResetEmplacement(idDonateur,idDon,idDepot,idChambre,quantite) == true) {
					GDBA.RefuserDonation(idDonation);
					GDBA.AlertDonateur(email,nbrAvertissement); 
					Afficher_Donations_permanat(request,response);
				}
			}else {
				Afficher_Donations_permanat(request,response);
			}
		} else if(nbrAvertissement >= 3) {
			if(GDBA.UpdateNbrAvertissementDonateur(idDonateur,nbrAvertissement)==true) {;
				if(GDBA.ResetAllEmplacements(idDonateur) == true) {
					GDBA.BlockALLDonation(idDonateur);
					GDBA.NotifyEmailDonateur(email,nbrAvertissement);
				}else {
				Afficher_Donations_permanat(request,response);
				}
			}else {
			Afficher_Donations_permanat(request,response);
			}
			Afficher_Donations_permanat(request,response);
		}
		
		
	}


	private void Valider_Donation(HttpServletRequest request, HttpServletResponse response) {
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		int idDonation = Integer.parseInt(request.getParameter("idDonation"));
		int idDonateur = Integer.parseInt(request.getParameter("idDonateur"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int period = Integer.parseInt(request.getParameter("period"));
		String type = request.getParameter("type");
		String genre = request.getParameter("genre");
		
		
		 int idChambre = GDBA.GetIdChambre(idDepot,type);
		 int EmplacementValable = GDBA.CountEmplacementsValable(idDon,idDepot,idChambre);
		 
		 
		 if(period == 0) {
				GDBA.ValiderDonation(idDonation);
				
				GDBA.UpdateRecievedEmplacements(idDonateur,idDepot,idChambre,idDon,quantite);
				 Afficher_Donations_permanat(request,response);
			 	
		 }else if (EmplacementValable >= quantite) {
		 	if(period == 1) {
				GDBA.ValiderDonation(idDonation);
				
				LocalDate futureDate = LocalDate.now().plusWeeks(period);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
				String date = futureDate.format(formatter);
				
				GDBA.InsertUpdatedDonation(idDonateur,idDon,idDepot,quantite,date,period);
				
				GDBA.ReserveNewEmplacements(idDonateur,idDon,idDepot,idChambre,quantite);
				GDBA.UpdateRecievedEmplacements(idDonateur,idDepot,idChambre,idDon,quantite);
				 Afficher_Donations_permanat(request,response);
			}else if(period == 2) {
				GDBA.ValiderDonation(idDonation);
				
				LocalDate futureDate = LocalDate.now().plusMonths(period);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
				String date = futureDate.format(formatter);
				
				GDBA.InsertUpdatedDonation(idDonateur,idDon,idDepot,quantite,date,period);
				
				GDBA.ReserveNewEmplacements(idDonateur,idDon,idDepot,idChambre,quantite);
				GDBA.UpdateRecievedEmplacements(idDonateur,idDepot,idChambre,idDon,quantite);
				 Afficher_Donations_permanat(request,response);
			}else if(period == 3) {
				GDBA.ValiderDonation(idDonation);
				
				LocalDate futureDate = LocalDate.now().plusMonths(period);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
				String date = futureDate.format(formatter);
				
				GDBA.InsertUpdatedDonation(idDonateur,idDon,idDepot,quantite,date,period);
				
				GDBA.ReserveNewEmplacements(idDonateur,idDon,idDepot,idChambre,quantite);
				GDBA.UpdateRecievedEmplacements(idDonateur,idDepot,idChambre,idDon,quantite);
				 Afficher_Donations_permanat(request,response);
			}
		 	
		 }else {
			 int count=quantite - EmplacementValable;
			 
			 HttpSession GestionnaireSession = request.getSession(true);
			 String message="spv faire liberer "+count+" d'emplacement de type: "+type+", genre: "+genre+"";
			
			 List<Donation> donations = GDBA.GetDonationsList();
			 
			 GestionnaireSession.setAttribute("role", "gestionnaire");
			 GestionnaireSession.setAttribute("message", message);
			 GestionnaireSession.setAttribute("donations", donations);
			 
			 
			 RequestDispatcher dispatcher = request.getRequestDispatcher("List-Donations.jsp");
			 try {
				 dispatcher.forward(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			}
		 }
		 
	}


	private void Afficher_Donations_permanat(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		List<Donation> donations = GDBA.GetDonationsList();
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		GestionnaireSession.setAttribute("donations", donations);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("List-Donations.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Delete_Depot(HttpServletRequest request, HttpServletResponse response) {
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		
		
		GDBA.DeleteDepot(idDepot);
		Afficher_Depots(request,response);
	
		
		
	}


	private void Afficher_Depots(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		List<Depot> countChambres = GDBA.GetChambresCount();
		List<String> DepotsLocalisations = GDBA.GetIdDeposLocalisation();
		
		List<Depot> Depots = new ArrayList<Depot>();
		
		for(int i=0; i<DepotsLocalisations.size();i++) {
			Depot x = countChambres.get(i);
			Depots.add(new Depot(x.getIdDepot(),DepotsLocalisations.get(i),x.getNbrChambres()));
		}
		
		GestionnaireSession.setAttribute("role", "gestionnaire");
		GestionnaireSession.setAttribute("depots", Depots);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Gerer-Depots.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}


	private void Enregistrer_Depot(HttpServletRequest request, HttpServletResponse response) {
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		String role = request.getParameter("role");
		String nomDepot = request.getParameter("depot");
		
		GDBA.CreatDepot(nomDepot);
		int idDepot = GDBA.GetIdDepot(nomDepot);
		
		Enumeration inputs = request.getParameterNames();
		List<String> typeChambres = new ArrayList<String>();
		List<Integer> capacitePermanant = new ArrayList<Integer>();
		List<Integer> capaciteNonPermanant = new ArrayList<Integer>();
		
		
		while(inputs.hasMoreElements()) {
			String nomInput =(String) inputs.nextElement();
			
			
			String[] inputsValues = request.getParameterValues(nomInput);
			for(int i=0;i<inputsValues.length;i++) {
				String inputValue = inputsValues[i];
				
				if(nomInput.equals("chambre")) {
					typeChambres.add(inputValue);
				}else if(nomInput.equals("capacite-permanant")){
					capacitePermanant.add(Integer.parseInt(inputValue));
				}else if(nomInput.equals("capacite-non-permanant")) {
					capaciteNonPermanant.add(Integer.parseInt(inputValue));
				}
			}
		}
		
		int size = capacitePermanant.size();
		
		List<Chambre> chambres = new ArrayList<Chambre>();
		for(int i=0; i<size;i++) {
			int max = capacitePermanant.get(i) + capaciteNonPermanant.get(i);
			chambres.add(new Chambre(typeChambres.get(i),max));
		}
		
		for(Chambre x : chambres) {
			GDBA.CreateChambre(idDepot,x);
		}
		
		List<Chambre> EmplacementsChambre = new ArrayList<Chambre>();
		
		for(int i=0; i<size; i++) {
			int max = capacitePermanant.get(i) + capaciteNonPermanant.get(i);
			EmplacementsChambre.add(new Chambre(typeChambres.get(i),max,capacitePermanant.get(i),capaciteNonPermanant.get(i)));
		}
		
		int idDonM=0;
		int idDonF=0;
		
		for(Chambre x : EmplacementsChambre) {
			int idChambre = GDBA.GetIdNewChambre(idDepot,x);
			
			if(x.getType().equals("T-shirt")) {
				idDonM=1;
				idDonF=2;
			}else if(x.getType().equals("pantlon")) {
				idDonM=3;
				idDonF=4;
			}else if(x.getType().equals("chaussure")) {
				idDonM=5;
				idDonF=6;
			}
			
			int halfPermanant = x.getCapacitePermanant()/2;
			int halfNonPermanant = x.getCapaciteNonPermanant()/2;
			
			for(int i=0;i<halfPermanant;i++) {
				GDBA.InsertEmplacementHomme(idDepot,idChambre,idDonM);
			}
			
			for(int i=0;i<halfPermanant;i++) {
				GDBA.InsertEmplacementFemme(idDepot,idChambre,idDonF);
			}
			
			
			for(int i=0; i<halfNonPermanant;i++) {
				GDBA.InsertEmpalcementNonPHomme(idDepot,idChambre,idDonM);
			}
			
			for(int i=0; i<halfNonPermanant;i++) {
				GDBA.InsertEmpalcementNonPFemme(idDepot,idChambre,idDonF);
			}
		}
		
		
		Afficher_Depots(request,response);
		
	}


	private void profil_Gestionnaire(HttpServletRequest request, HttpServletResponse response) {
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		List<Emplacement> stocks = GDBA.ListEmplacementValider();
		
		HttpSession sessionGestionnaire = request.getSession(true);
		sessionGestionnaire.setAttribute("nom", "Chabane");
		sessionGestionnaire.setAttribute("prenom", "Youcef");
		
		sessionGestionnaire.setAttribute("role", "gestionnaire");
		sessionGestionnaire.setAttribute("stocks", stocks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Gestionnaire-main.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Ajouter_Depot_Demande(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionGestionnaire = request.getSession(true);
		
		sessionGestionnaire.setAttribute("role", "gestionnaire");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Ajouter-Depot.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
		case "EnregistrerDepot":
			Enregistrer_Depot(request,response);
			break;
		case "saveUpdateDepot":
			Save_Update_Depot(request,response);
			break;
		case "EnregistrerTransfert":
			Enregistrer_Transfert(request,response);
			break;
		default:
			break;
		}
	}
	
	private void Enregistrer_Transfert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession GestionnaireSession = request.getSession(true);
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		int idDepot1 = Integer.parseInt(request.getParameter("depot1"));
		System.out.println("id Depot1: "+idDepot1);
		int idDepot2 = Integer.parseInt(request.getParameter("depot2"));
		System.out.println("id Depot1: "+idDepot2);
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		System.out.println("quantite Depot1: "+quantite);
		String type = request.getParameter("type");
		String genre = request.getParameter("genre");
		System.out.println("type :"+type);
		System.out.println("genre: "+genre);
		int idDon = GDBA.GetIdDon(type,genre);
		System.out.println("type Depot1: "+idDon);
		
		if(idDepot1 == idDepot2) {
			String message = "pour effectuer un transfert i fault choisir différents depots";
			GestionnaireSession.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("effectuer-transfert.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			System.out.println("here we go");
			if(GDBA.CheckTransfert(idDepot1,idDon,quantite) == true) {
				List<Emplacement> listTransferts = GDBA.TransferedEmplacements(idDepot1,idDon,quantite);
				int count=0;
				
				for(Emplacement x : listTransferts ) {
					if(GDBA.CheckForwardedDepot(idDepot2,idDon,x.getTypeA()) == true) {
						count++;
					}
				}
				
				if(count == quantite) {
					System.out.println("nop2");
					for(Emplacement x : listTransferts) {
						GDBA.TransfertEmpalcement(x.getIdEmplacement());
					}
					
					for(Emplacement x : listTransferts) {
						GDBA.ForwardDepotTransfer(idDepot2,idDon,x.getIdDonateur(),x.getTypeA());
					}
					
					profil_Gestionnaire(request,response);
					
				}else {
					String message = "quantite n'est pas disponible dans l'autre Depot";
					GestionnaireSession.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("effectuer-transfert.jsp");
					try {
						dispatcher.forward(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else {
				System.out.println("nop1");
			}
		}
		
	}


	private void Save_Update_Depot(HttpServletRequest request, HttpServletResponse response) {
		GestionnaireDBA GDBA = new GestionnaireDBA();
		
		String role = request.getParameter("role");
		
		
		
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		
		Enumeration inputs = request.getParameterNames();


		
		List<String> typeNewChambres = new ArrayList<String>();
		List<Integer> capacite_permanant = new ArrayList<Integer>();
		List<Integer> capacite_non_permanant = new ArrayList<Integer>();
		
		while(inputs.hasMoreElements()) {
			String nomInput =(String) inputs.nextElement();
			System.out.println("input name: "+nomInput);
			
			String[] inputsValues = request.getParameterValues(nomInput);
			for(int i=0;i<inputsValues.length;i++) {
				String inputsValue = inputsValues[i];
				System.out.println("input values: "+inputsValue);
				
				if(nomInput.equals("chambre")) {
					typeNewChambres.add(inputsValue);
					
				}else if(nomInput.equals("capacite-permanant")){
					capacite_permanant.add(Integer.parseInt(inputsValue));
				}else  if(nomInput.equals("capacite-non-permanant")) {
					capacite_non_permanant.add(Integer.parseInt(inputsValue));
					
				}
			}
		}
		

		List<Chambre> newChambres = new ArrayList<Chambre>();
		
		
		for(int i=0; i<typeNewChambres.size();i++) {
			int max = capacite_permanant.get(i) + capacite_non_permanant.get(i);
			newChambres.add(new Chambre(idDepot,typeNewChambres.get(i),max,capacite_permanant.get(i),capacite_non_permanant.get(i)));
		}
		
		
		for(Chambre x : newChambres) {
			GDBA.AddChambre(x);
			 
			int idChambre = GDBA.GetIdNewChambre(idDepot, x);
			
			int idDonM=0;
			int idDonF=0;
			
			if(x.getType().equals("T-shirt")) {
				idDonM=1;
				idDonF=2;
			}else if(x.getType().equals("pantlon")) {
				idDonM=3;
				idDonF=4;
			}else if(x.getType().equals("chaussure")) {
				idDonM=5;
				idDonF=6;
			}
			
			int halfPermanant = x.getCapacitePermanant()/2;
			int halfNonPermanant = x.getCapaciteNonPermanant()/2;
			
			for(int i=0;i<halfPermanant;i++) {
				GDBA.InsertEmplacementHomme(idDepot,idChambre,idDonM);
			}
			
			for(int i=0;i<halfPermanant;i++) {
				GDBA.InsertEmplacementFemme(idDepot,idChambre,idDonF);
			}
			
			
			for(int i=0; i<halfNonPermanant;i++) {
				GDBA.InsertEmpalcementNonPHomme(idDepot,idChambre,idDonM);
			}
			
			for(int i=0; i<halfNonPermanant;i++) {
				GDBA.InsertEmpalcementNonPFemme(idDepot,idChambre,idDonF);
			}
		}
		
		Afficher_Depots(request,response);
		
		
		
	}

}
