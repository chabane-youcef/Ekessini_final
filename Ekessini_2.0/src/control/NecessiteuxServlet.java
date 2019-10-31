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

import model.Chambre;
import model.Demande;
import model.Depot;
import model.DonateurDBA;
import model.Donation;
import model.Necessiteux;
import model.NecessiteuxDBA;

/**
 * Servlet implementation class NecessiteuxServlet
 */
@WebServlet("/NecessiteuxServlet")
public class NecessiteuxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NecessiteuxServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		System.out.println("command : "+TheCommand);
		
		switch(TheCommand) {
		case "AjouteDemande":
			Ajouter_Demande(request,response);
			break;
		case "oldDemandess":
			old_Demandes_List(request,response);
			break;
		case "DeleteNecessiteuxAccount":
			Delete_Necessiteux(request,response);
			break;
		case "deleteDemande":
			delete_Demande(request,response);
			break;
		case "NewDemande":
			Ajouter_Demande(request,response);
			break;
		case "updateDemande":
			Modifier_Demande(request,response);
			break;
		case "DemandeAttente":
			Demande_attente(request,response);
			break;
		case "home":
			Profil_Necessiteux(request,response);
			break;
		case "logout":
			Logout_Necessiteux(request,response);
			break;
		default:
			break;
		}
		
	}

	
	private void Modifier_Demande(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		int idDemande = Integer.parseInt(request.getParameter("idDemande"));
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteuxAdmis"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		
		NecessiteuxSession.setAttribute("idDemande",idDemande );
		NecessiteuxSession.setAttribute("idNecessiteux",idNecessiteux );
		NecessiteuxSession.setAttribute("idDon",idDon );
		NecessiteuxSession.setAttribute("idDepot",idDepot );
		NecessiteuxSession.setAttribute("quantite",quantite );
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-demande.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	private void Demande_attente(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		List<Depot> depots = NDBA.ListDepots();
		
		NecessiteuxSession.setAttribute("idNecessiteux",idNecessiteux );
		NecessiteuxSession.setAttribute("idDon", idDon);
		NecessiteuxSession.setAttribute("quantite", quantite);
		NecessiteuxSession.setAttribute("depots",depots );
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("demande-attente-depot.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void delete_Demande(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		
		int idDemande = Integer.parseInt(request.getParameter("idDemande"));
		int IdNecessiteux = Integer.parseInt(request.getParameter("idNecessiteuxAdmis"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		NDBA.DeleteDemande(idDemande);
		
		NDBA.UpdateEmplacement(idDon,idDepot,quantite);
		
		
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


	private void Delete_Necessiteux(HttpServletRequest request, HttpServletResponse response) {
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		List<Demande> listDemandes = NDBA.GetDemandeNonValider(idNecessiteux);
		if(listDemandes.isEmpty() == false) {
			for(Demande x : listDemandes) {
				NDBA.UpdateEmplacement(x.getIdDon(), x.getIdDepot(), x.getQuantite());
			}
		}	
		if(NDBA.BlockNecessiteux(idNecessiteux) == true) {
			NDBA.DeleteNonValideDemandes(idNecessiteux);
			String email = NDBA.getEmailNecessiteux(idNecessiteux);
			NDBA.NotifyBlockedNecessiteuxEmail(email);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	private void old_Demandes_List(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		List<Demande> oldDemandes = NDBA.GetOldDemandes(idNecessiteux);
		
		NecessiteuxSession.setAttribute("oldDemandes", oldDemandes);
		NecessiteuxSession.setAttribute("idNecessiteux", idNecessiteux);
		RequestDispatcher dispatcher = request.getRequestDispatcher("necessiteux-old-demandes.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Logout_Necessiteux(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		
		NecessiteuxSession.invalidate();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void Ajouter_Demande(HttpServletRequest request, HttpServletResponse response) {
		String idNecessiteux = request.getParameter("idNecessiteux");
		
		
		HttpSession NecessiteuxSession = request.getSession(true);
		NecessiteuxSession.setAttribute("idNecessiteux", idNecessiteux);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("demander-donation.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
			case "InscriptionNecessiteux":
				Inscription_Necessiteux(request,response);
				break;
			case "DemandeInformation":
				Demander_Donation_Verification(request,response);
				break;
			case "selectionDepotDemande":
				Enregistrer_Demande(request,response);
				break;
			case "EnregistrerDemandeAttente":
				Enregistrer_Demande_Attente(request,response);
				break;
			case "EnregistrerModification":
				Enregistrer_Modification(request,response);
				break;
			default:
				break;
		}
	}


	private void Enregistrer_Modification(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		int idDemande = Integer.parseInt(request.getParameter("idDemande"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		int quantiteNew = Integer.parseInt(request.getParameter("quantiteNew"));
		String date = request.getParameter("date");
		
		if(quantite < quantiteNew) {
			int count =  quantiteNew - quantite;
			if(NDBA.EmplacementNonReserver(idDepot,idDon,count) == true) {
				NDBA.AddEmplacement(idDepot,idDon,count);
				NDBA.UpdateDemande(idDemande,quantiteNew,date);
			}else {
				NecessiteuxSession.setAttribute("idDemande",idDemande );
				NecessiteuxSession.setAttribute("quantite",quantite );
				NecessiteuxSession.setAttribute("idDon", idDon);
				NecessiteuxSession.setAttribute("idDepot",idDepot );
				NecessiteuxSession.setAttribute("message", "il n y a pas autre emplacements pour reserves");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("update-demande.jsp");
				try {
					dispatcher.forward(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}else if(quantite == quantiteNew){
			NDBA.UpdateDemande(idDemande,quantiteNew,date);
			Profil_Necessiteux(request,response);
		}else {
			int count = quantiteNew - quantite;
			NDBA.LibrerEmplacements(idDepot,idDon,count);
			NDBA.UpdateDemande(idDemande,quantiteNew,date);
			Profil_Necessiteux(request,response);
		}
		
		
		
	}


	private void Enregistrer_Demande_Attente(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		int idDepot = Integer.parseInt(request.getParameter("idDepot"));
		
		System.out.println("idNecessiteux: "+idNecessiteux+", idDon: "+idDon+", quantite: "+quantite+", idDepot: "+idDepot);
		if(NDBA.EnregistrerDemandeAttente(idNecessiteux,idDepot,idDon,quantite) == true) {
			Profil_Necessiteux(request,response);
		}else {
			String message="erreur dans l'insertion de demande en attente! svp faire autre demande";
			NecessiteuxSession.setAttribute("message", message);
			NecessiteuxSession.setAttribute("idNecessiteux", idNecessiteux);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("demander-donation.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}


	private void Enregistrer_Demande(HttpServletRequest request, HttpServletResponse response) {
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		String time = request.getParameter("time");
		int idDepot = Integer.parseInt(request.getParameter("depot"));
		
		if(NDBA.ReserveEmplacements(idDepot,idDon,quantite) == true) {
			NDBA.RegisterDemande(idDon,idNecessiteux,idDepot,quantite,time);
		}
	
		Profil_Necessiteux(request,response);
	}


	private void Profil_Necessiteux(HttpServletRequest request, HttpServletResponse response) {
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		int IdNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		 Necessiteux necessiteux = NDBA.GetNecessiteuxInformations(IdNecessiteux);
		 List<Demande> demandes = NDBA.getListDemandes(IdNecessiteux);
		 
		 HttpSession NecessiteuxSession = request.getSession(true);
		 NecessiteuxSession.setAttribute("necessiteux", necessiteux);
		 NecessiteuxSession.setAttribute("demandesList", demandes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Necessiteux-main.jsp");
		try {
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void Demander_Donation_Verification(HttpServletRequest request, HttpServletResponse response) {
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		HttpSession NecessiteuxSession = request.getSession(true);
		
		int idNecessiteux = Integer.parseInt(request.getParameter("idNecessiteux"));
		String type = request.getParameter("type");
		String gendre = request.getParameter("gendre");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		
		int idDon = NDBA.GetIdDon(type,gendre);
		
		List<Chambre> ChambresDispo = NDBA.ListChambresDispo(idDon,quantite);
			for(Chambre x : ChambresDispo) {
				System.out.println("chambre Depot: "+x.getIdDepot());
				System.out.println("chambre id :"+x.getIdChambre() );
				System.out.println("chambre type: "+x.getType());
			}
		if(ChambresDispo.isEmpty() == true) {
			
			NecessiteuxSession.setAttribute("idNecessiteux", idNecessiteux);
			NecessiteuxSession.setAttribute("idDon", idDon);
			NecessiteuxSession.setAttribute("quantite", quantite);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("sorry-emplacement.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else {
			
			
			List<Depot> Depots = new ArrayList<Depot>();
			
			for(Chambre c: ChambresDispo) {
				Depot x = NDBA.DepotsDispo(c.getIdDepot());
				Depots.add(x);
			}
			
			NecessiteuxSession.setAttribute("idNecessiteux", idNecessiteux);
			NecessiteuxSession.setAttribute("idDon", idDon);
			NecessiteuxSession.setAttribute("quantite", quantite);
			NecessiteuxSession.setAttribute("Depots", Depots);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Selection-Depot-Demande.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
			
			
		
			
	}


	private void Inscription_Necessiteux(HttpServletRequest request, HttpServletResponse response) {
		HttpSession NecessiteuxSession = request.getSession(true);
		Necessiteux necessiteuxInfoPerso,necessiteuxInfoSecondaire;
		NecessiteuxDBA NDBA = new NecessiteuxDBA();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String birthday = request.getParameter("birthday");
		String sexe = request.getParameter("gendre");
		String numTel = request.getParameter("numero");
		
		numTel=numTel.substring(1);
		
		String email = request.getParameter("email");
		String situationFamiliale = request.getParameter("situationFamiliale");
		String NbrEnfants = (request.getParameter("NbrEnfants"));
		int paixMoix = Integer.parseInt(request.getParameter("paixMoix"));
		
		System.out.println("kids: "+NbrEnfants);
		
		if(NDBA.CheckEmail(email) == false) {
			if(NbrEnfants == "") {
				int nbrEnfants=0;
				necessiteuxInfoPerso = new Necessiteux(nom,prenom,sexe,numTel,birthday,email);
				NDBA.InscriptionNecessiteux(necessiteuxInfoPerso);
				
				int IdNecessiteux = NDBA.getNecessiteuxId(email);
				necessiteuxInfoSecondaire = new Necessiteux(IdNecessiteux,situationFamiliale,paixMoix,nbrEnfants);
				NDBA.InsertSituationNecessiteux(necessiteuxInfoSecondaire);
				NDBA.NotifyEmailNecessiteux(necessiteuxInfoPerso);
				
				NDBA.NotifySMSNecessiteux(necessiteuxInfoPerso);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				try {
					dispatcher.forward(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}else {
				int nbrEnfants = Integer.parseInt(NbrEnfants);
				necessiteuxInfoPerso = new Necessiteux(nom,prenom,sexe,numTel,birthday,email);
				NDBA.InscriptionNecessiteux(necessiteuxInfoPerso);
				
				int IdNecessiteux = NDBA.getNecessiteuxId(email);
				System.out.println("id Necessiteux: "+IdNecessiteux);
				
				necessiteuxInfoSecondaire = new Necessiteux(IdNecessiteux,situationFamiliale,paixMoix,nbrEnfants);
				NDBA.InsertSituationNecessiteux(necessiteuxInfoSecondaire);
				NDBA.NotifyEmailNecessiteux(necessiteuxInfoPerso);
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				try {
					dispatcher.forward(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		}else {
			String message = "Email deja existe";
			
			NecessiteuxSession.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("inscription-necessiteux.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}

}
