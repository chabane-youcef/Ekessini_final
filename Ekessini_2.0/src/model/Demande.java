package model;

public class Demande {
	private int idDemande;
	private int idNecessiteux;
	private int idDon;
	private int idDepot;
	private int quantite;
	private String date;
	private String etat;
	
	private String nom;
	private String prenom;
	private String dateNais;
	private String sexe;
	
	private String email;
	private String numTel;
	
	private String sitFam;
	private int nbrEnfants;
	private int salaire;
	private String priorite;
	
	private String localisation;
	private String type;
	private String genre;
	private int nbrAvertissement;
	
	
	
	public Demande(int idDemande, int idNecessiteux, int idDepot, int idDon, int quantite) {
		this.idDemande = idDemande;
		this.idNecessiteux = idNecessiteux;
		this.idDepot = idDepot;
		this.idDon = idDon;
		this.quantite = quantite;
	}
	
	public Demande(int idDepot, int idDon, int quantite) {
		this.idDepot = idDepot;
		this.idDon = idDon;
		this.quantite = quantite;
	}

	

	public Demande(int idDemande,int quantite,String date,String type,String genre,String localisation) {
		this.idDemande = idDemande;
		this.quantite = quantite;
		this.date = date;
		this.type = type;
		this.genre = genre;
		this.localisation = localisation;
	}
	
	public Demande(int idDemande, int idNecessiteux, int idDon, int idDepot, int quantite, String date, String etat) {
		
		this.idDemande = idDemande;
		this.idNecessiteux = idNecessiteux;
		this.idDon = idDon;
		this.idDepot = idDepot;
		this.quantite = quantite;
		this.date = date;
		this.etat = etat;
	}
	public Demande(int idNecessiteux, int idDon, int idDepot, int quantite, String date) {

		this.idNecessiteux = idNecessiteux;
		this.idDon = idDon;
		this.idDepot = idDepot;
		this.quantite = quantite;
		this.date = date;
	}
	
	
	
	public Demande(int idDemande, int idNecessiteux, int idDon, int idDepot, int quantite, String date, String etat,String localisation, String type, String genre) {
		
		this.idDemande = idDemande;
		this.idNecessiteux = idNecessiteux;
		this.idDon = idDon;
		this.idDepot = idDepot;
		this.quantite = quantite;
		this.date = date;
		this.etat = etat;
		this.localisation = localisation;
		this.type = type;
		this.genre = genre;
	}
	
	

	public Demande(int idDemande, int idNecessiteux, int idDon, int idDepot, int quantite, String date, String nom,
			String prenom, String dateNais, String sexe, String email, String numTel, String sitFam, int nbrEnfants,
			int salaire,String priorite, String localisation, String type, String genre,int nbrAvertissement) {
		
		this.idDemande = idDemande;
		this.idNecessiteux = idNecessiteux;
		this.idDon = idDon;
		this.idDepot = idDepot;
		this.quantite = quantite;
		this.date = date;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNais = dateNais;
		this.sexe = sexe;
		this.email = email;
		this.numTel = numTel;
		this.sitFam = sitFam;
		this.nbrEnfants = nbrEnfants;
		this.salaire = salaire;
		this.priorite = priorite;
		this.localisation = localisation;
		this.type = type;
		this.genre = genre;
		this.nbrAvertissement=nbrAvertissement;
	}

	public int getIdDemande() {
		return idDemande;
	}
	public void setIdDemande(int idDemande) {
		this.idDemande = idDemande;
	}
	public int getIdNecessiteux() {
		return idNecessiteux;
	}
	public void setIdNecessiteux(int idNecessiteux) {
		this.idNecessiteux = idNecessiteux;
	}
	public int getIdDon() {
		return idDon;
	}
	public void setIdDon(int idDon) {
		this.idDon = idDon;
	}
	public int getIdDepot() {
		return idDepot;
	}
	public void setIdDepot(int idDepot) {
		this.idDepot = idDepot;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNais() {
		return dateNais;
	}

	public void setDateNais(String dateNais) {
		this.dateNais = dateNais;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getSitFam() {
		return sitFam;
	}

	public void setSitFam(String sitFam) {
		this.sitFam = sitFam;
	}

	public int getNbrEnfants() {
		return nbrEnfants;
	}

	public void setNbrEnfants(int nbrEnfants) {
		this.nbrEnfants = nbrEnfants;
	}

	public int getSalaire() {
		return salaire;
	}

	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	
	public String getPriorite() {
		return priorite;
	}

	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}
	
	public int getNbrAvertissement() {
		return nbrAvertissement;
	}

	public void setNbrAvertissement(int nbrAvertissement) {
		this.nbrAvertissement = nbrAvertissement;
	}

	public Demande() {
		
	}
	
	
	
	
}
