package model;

import java.sql.Date;

public class Necessiteux {
	private int IdNecessiteux;
	private String nom;
	private String prenom;
	private String Sexe;
	private String NumTel;
	private String DateNais;
	private String Email;
	private String SituationFam;
	private int NbrEnfants;
	private int PaixMois;
	
	
	public Necessiteux() {
		
	}
	
	
	
	public Necessiteux(int idNecessiteux, String nom, String prenom) {

		IdNecessiteux = idNecessiteux;
		this.nom = nom;
		this.prenom = prenom;
	}



	public Necessiteux(int idNecessiteux, String nom, String prenom, String sexe, String numTel, String dateNais, String email, String situationFam,int NbrEnfants, int paixMois) {
		
		this.IdNecessiteux = idNecessiteux;
		this.nom = nom;
		this.prenom = prenom;
		this.Sexe = sexe;
		this.NumTel = numTel;
		this.DateNais = dateNais;
		this.Email = email;
		this.SituationFam = situationFam;
		this.NbrEnfants = NbrEnfants;
		this.PaixMois = paixMois;
	}
	
	
	public Necessiteux(String nom, String prenom, String sexe, String numTel, String dateNais, String email,String situationFam, int paixMois) {
		
		this.nom = nom;
		this.prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		SituationFam = situationFam;
		PaixMois = paixMois;
	}

	public Necessiteux(String nom, String prenom, String sexe, String numTel, String dateNais, String email) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.Sexe = sexe;
		this.NumTel = numTel;
		this.DateNais = dateNais;
		this.Email = email;
		
	}


	public Necessiteux(int IdNecessiteux,String Nom, String Prenom, String Sexe, String SituationFam, String DateNais) {
		this.IdNecessiteux = IdNecessiteux;
		this.nom = Nom;
		this.prenom = Prenom;
		this.Sexe = Sexe;
		this.SituationFam = SituationFam;
		this.DateNais = DateNais;
	}
	
	


	public Necessiteux(int idNecessiteux, String situationFam, int nbrEnfants, int paixMois) {
		
		IdNecessiteux = idNecessiteux;
		SituationFam = situationFam;
		NbrEnfants = nbrEnfants;
		PaixMois = paixMois;
	}
	
	public Necessiteux(int idNecessiteux, String situationFam, int paixMois) {
		
		IdNecessiteux = idNecessiteux;
		SituationFam = situationFam;
		PaixMois = paixMois;
	}

	public int getIdNecessiteux() {
		return IdNecessiteux;
	}


	public void setIdNecessiteux(int idNecessiteux) {
		IdNecessiteux = idNecessiteux;
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


	public String getSexe() {
		return Sexe;
	}


	public void setSexe(String sexe) {
		Sexe = sexe;
	}


	public String getNumTel() {
		return NumTel;
	}


	public void setNumTel(String numTel) {
		NumTel = numTel;
	}


	public String getDateNais() {
		return DateNais;
	}


	public void setDateNais(String dateNais) {
		DateNais = dateNais;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getSituationFam() {
		return SituationFam;
	}


	public void setSituationFam(String situationFam) {
		SituationFam = situationFam;
	}

	public int getNbrEnfants() {
		return NbrEnfants;
	}
	
	public void setNbrEnfants(int nbrEnfants) {
		NbrEnfants = nbrEnfants;
	}
	
	public int getPaixMois() {
		return PaixMois;
	}


	public void setPaixMois(int paixMois) {
		PaixMois = paixMois;
	}
	
	
	
	
}
