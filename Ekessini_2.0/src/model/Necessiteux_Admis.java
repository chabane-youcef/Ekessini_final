package model;

import java.sql.Date;

public class Necessiteux_Admis {
	private int IdNecessiteux;
	private String nom;
	private String prenom;
	private String Sexe;
	private String NumTel;
	private String DateNais;
	private String Email;
	private String SituationFam;
	private int PaixMois;
	private String Priorite;
	private String MotDePass;
	private int NbrEnfants;
	private int nbrDemande;
	
	
	
	
	public Necessiteux_Admis(int idNecessiteux, String nom, String prenom, String sexe, String numTel, String dateNais,
			String email, String situationFam, int paixMois, String priorite, int nbrEnfants, int nbrDemande) {
		
		IdNecessiteux = idNecessiteux;
		this.nom = nom;
		this.prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		SituationFam = situationFam;
		PaixMois = paixMois;
		Priorite = priorite;
		NbrEnfants = nbrEnfants;
		this.nbrDemande = nbrDemande;
	}


	public Necessiteux_Admis(int idNecessiteux, String nom, String prenom, String sexe, String numTel, String dateNais,
			String email, String situationFam, int paixMois, String priorite, int nbrEnfants) {
		
		IdNecessiteux = idNecessiteux;
		this.nom = nom;
		this.prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		SituationFam = situationFam;
		PaixMois = paixMois;
		Priorite = priorite;
		NbrEnfants = nbrEnfants;
	}


	public Necessiteux_Admis(int idNecessiteux, String nom, String prenom, String sexe, String numTel, String dateNais, String email, String situationFam, int paixMois, String priorite, String motDePass) {

		IdNecessiteux = idNecessiteux;
		this.nom = nom;
		this.prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		SituationFam = situationFam;
		PaixMois = paixMois;
		Priorite = priorite;
		MotDePass = motDePass;
	}
	

	public Necessiteux_Admis(String nom,String prenom,String Sexe,String NumTel,String DateNais,String Email,String SituationFam,int PaixMois) {
		this.nom = nom;
		this.prenom = prenom;
		this.Sexe = Sexe;
		this.NumTel = NumTel;
		this.DateNais = DateNais;
		this.Email = Email;
		this.SituationFam = SituationFam;
		this.PaixMois = PaixMois;
		
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


	public int getPaixMois() {
		return PaixMois;
	}


	public void setPaixMois(int paixMois) {
		PaixMois = paixMois;
	}


	public String getPriorite() {
		return Priorite;
	}


	public void setPriorite(String priorite) {
		Priorite = priorite;
	}


	public String getMotDePass() {
		return MotDePass;
	}


	public void setMotDePass(String motDePass) {
		MotDePass = motDePass;
	}


	public int getNbrEnfants() {
		return NbrEnfants;
	}


	public void setNbrEnfants(int nbrEnfants) {
		NbrEnfants = nbrEnfants;
	}


	public int getNbrDemande() {
		return nbrDemande;
	}


	public void setNbrDemande(int nbrDemande) {
		this.nbrDemande = nbrDemande;
	}
	
	
	
}
