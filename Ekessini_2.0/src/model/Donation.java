package model;

import java.sql.Date;

public class Donation {
	private int IdDonation;
	private int IdDonateur;
	private int IdDon;
	private int IdDepot;
	private int Quantite;
	private String date;
	private String Etat;
	
	private String Type;
	private String Genre;
	private String Localisation;
	
	private String nom;
	private String prenom;
	private String email;
	private String numTel;
	
	private int nbrAvertissement;
	private int period;
	
	
	
	
	public Donation(int idDonation, int idDonateur, int idDon, int idDepot, int quantite, String date, String type,
			String genre, String localisation, String nom, String prenom, String email, String numTel) {
		IdDonation = idDonation;
		IdDonateur = idDonateur;
		IdDon = idDon;
		IdDepot = idDepot;
		Quantite = quantite;
		this.date = date;
		Type = type;
		Genre = genre;
		Localisation = localisation;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numTel = numTel;
	}


	public Donation (int IdDonation,int Quantite, String date, String Type, String Genre, String Localisation) {
		this.IdDonation = IdDonation;
		this.Quantite = Quantite;
		this.date = date;
		this.Type = Type;
		this.Genre = Genre;
		this.Localisation = Localisation;
	}
	

	public Donation(int idDonation, int idDonateur, int idDon, int idDepot, int quantite, String date, String etat,String type, String genre, String localisation) {
		
		IdDonation = idDonation;
		IdDonateur = idDonateur;
		IdDon = idDon;
		IdDepot = idDepot;
		Quantite = quantite;
		this.date = date;
		Etat = etat;
		Type = type;
		Genre = genre;
		Localisation = localisation;
	}
	
	public Donation(int idDonation,int IdDon,int IdDepot, int idDonateur, int quantite, String date, String type, String genre,String localisation) {
		
		IdDonation = idDonation;
		this.IdDon = IdDon;
		this.IdDepot = IdDepot;
		IdDonateur = idDonateur;
		Quantite = quantite;
		this.date = date;
		Type = type;
		Genre = genre;
		Localisation = localisation;
	}
	
	
	
	
	
	public Donation(int idDonation, int idDonateur, int idDon, int idDepot, int quantite, String date,int period,
			String type, String genre, String localisation, String nom, String prenom, String email, String numTel,int nbrAvertissement) {
		
		IdDonation = idDonation;
		IdDonateur = idDonateur;
		IdDon = idDon;
		IdDepot = idDepot;
		Quantite = quantite;
		this.date = date;
		this.period = period;
		Type = type;
		Genre = genre;
		Localisation = localisation;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numTel = numTel;
		this.nbrAvertissement = nbrAvertissement;
	}

	public int getIdDonation() {
		return IdDonation;
	}
	public void setIdDonation(int idDonation) {
		IdDonation = idDonation;
	}
	public int getIdDonateur() {
		return IdDonateur;
	}
	public void setIdDonateur(int idDonateur) {
		IdDonateur = idDonateur;
	}
	public int getIdDon() {
		return IdDon;
	}
	public void setIdDon(int idDon) {
		IdDon = idDon;
	}
	public int getIdDepot() {
		return IdDepot;
	}
	public void setIdDepot(int idDepot) {
		IdDepot = idDepot;
	}
	public int getQuantite() {
		return Quantite;
	}
	public void setQuantite(int quantite) {
		Quantite = quantite;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEtat() {
		return Etat;
	}
	public void setEtat(String etat) {
		Etat = etat;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getLocalisation() {
		return Localisation;
	}
	public void setLocalisation(String localisation) {
		Localisation = localisation;
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
	
	public int getNbrAvertissement() {
		return nbrAvertissement;
	}

	public void setNbrAvertissement(int nbrAvertissement) {
		this.nbrAvertissement = nbrAvertissement;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}	
}
