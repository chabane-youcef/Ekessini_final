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
import model.DonateurAnonymeDBA;

/**
 * Servlet implementation class NecessiteuxAnonymeServlet
 */
@WebServlet("/DonateurAnonymeServlet")
public class DonateurAnonymeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonateurAnonymeServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TheCommand = request.getParameter("command");
		
		switch(TheCommand) {
		case "donationAnonyme":
			Donation_Anonyme_Values(request,response);
			break;
		case "selectionDepot":
			Enregistrer_Donation_Anonyme(request,response);
			break;
		default:
			break;
		}
	}


	private void Enregistrer_Donation_Anonyme(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateurAnonyme = request.getSession(true);
		DonateurAnonymeDBA DADBA = new DonateurAnonymeDBA();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String birthday = request.getParameter("birthday");
		String genre = request.getParameter("genre");
		String email = request.getParameter("email");
		String numTel = request.getParameter("numTel");
		String type = request.getParameter("type");
		String genreType = request.getParameter("genreType");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		String date = request.getParameter("date");
		int idDon = Integer.parseInt(request.getParameter("idDon"));
		int idDepot = Integer.parseInt(request.getParameter("depot"));
		
		List<Chambre> ChambresDispo = DADBA.EmplacementValable(idDepot,idDon,quantite);
		
		if(ChambresDispo.isEmpty() == true) {
			String message = "Espace n'est pas disponible dans ce Dipot";
			sessionDonateurAnonyme.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("donation-anonyme.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			DADBA.EnregistrerPersonne(nom,prenom,birthday,genre,email,numTel);
			int idPersonne = DADBA.GetIdPersonne(email);
			DADBA.EnregistrerDonationAnonyme(idPersonne,idDepot,idDon,quantite,date);
			
			Chambre x = ChambresDispo.get(0);
			
			DADBA.ReserverEmplacement(x.getIdChambre(),idDepot,idDon,idPersonne,quantite);
			
			sessionDonateurAnonyme.invalidate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		
	}


	private void Donation_Anonyme_Values(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionDonateurAnonyme = request.getSession(true);
		
		DonateurAnonymeDBA DADBA = new DonateurAnonymeDBA();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String birthday = request.getParameter("birthday");
		String genre = request.getParameter("genre");
		String email = request.getParameter("email");
		String numTel = request.getParameter("numTel");
		numTel=numTel.substring(1);
		
		String type = request.getParameter("type");
		String genreType = request.getParameter("genreType");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		String date = request.getParameter("date");
		
		int idDon = DADBA.GetIdDon(type,genreType);
		if(DADBA.CheckEmail(email) == false) {
			if(DADBA.CheckChambres(type) == true) {
					
					List<Depot> Depots = DADBA.DepotsList();
					
					sessionDonateurAnonyme.setAttribute("nom", nom);
					sessionDonateurAnonyme.setAttribute("prenom", prenom);
					sessionDonateurAnonyme.setAttribute("birthday", birthday);
					sessionDonateurAnonyme.setAttribute("genre", genre);
					sessionDonateurAnonyme.setAttribute("email", email);
					sessionDonateurAnonyme.setAttribute("numTel", numTel);
					sessionDonateurAnonyme.setAttribute("type", type);
					sessionDonateurAnonyme.setAttribute("genreType", genreType);
					sessionDonateurAnonyme.setAttribute("quantite", quantite);
					sessionDonateurAnonyme.setAttribute("date", date);
					sessionDonateurAnonyme.setAttribute("idDon", idDon);
					sessionDonateurAnonyme.setAttribute("Depots", Depots);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("Donation-Anonyme-Depot.jsp");
					try {
						dispatcher.forward(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("unloged-sorry-espacet.jsp");
				try {
					dispatcher.forward(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}else {
			String message ="email deja exist!";
			sessionDonateurAnonyme.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("donation-anonyme.jsp");
			try {
				dispatcher.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

}
