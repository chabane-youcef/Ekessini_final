package model;

import java.sql.Date;

public class Donateur {
	private int IdDonateur;
	private String Nom;
	private String Prenom;
	private String Sexe;
	private String NumTel;
	private String DateNais;
	private String Email;
	private String MotDePass;
	private int nbrDonations;
	
	
	
	
	public Donateur(int idDonateur, String nom, String prenom, String sexe, String numTel, String email,
			int nbrDonations) {
		IdDonateur = idDonateur;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		Email = email;
		this.nbrDonations = nbrDonations;
	}

	public Donateur(int idDonateur, String nom, String prenom, String sexe, String numTel, String dateNais, String email, String motDePass) {
		IdDonateur = idDonateur;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		MotDePass = motDePass;
	}
	
	public Donateur(int idDonateur, String nom, String prenom, String sexe, String numTel, String dateNais, String email,int nbrDonations) {
		IdDonateur = idDonateur;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		this.nbrDonations = nbrDonations;
		
	}
	
	public Donateur(int idDonateur, String nom, String prenom, String sexe, String numTel, String dateNais, String email) {
		IdDonateur = idDonateur;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
		
	}
	
	
	public Donateur(String nom, String prenom, String sexe, String numTel, String dateNais, String email) {
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		NumTel = numTel;
		DateNais = dateNais;
		Email = email;
	}
	
	
	public Donateur(int idDonateur, String motDePass) {
		super();
		IdDonateur = idDonateur;
		MotDePass = motDePass;
	}


	public int getIdDonateur() {
		return IdDonateur;
	}
	public void setIdDonateur(int idDonateur) {
		IdDonateur = idDonateur;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
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
	public String getMotDePass() {
		return MotDePass;
	}
	public void setMotDePass(String motDePass) {
		MotDePass = motDePass;
	}

	public int getNbrDonations() {
		return nbrDonations;
	}

	public void setNbrDonations(int nbrDonations) {
		this.nbrDonations = nbrDonations;
	}
	
	
	
	
}
