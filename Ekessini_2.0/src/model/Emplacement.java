package model;

public class Emplacement {
	private int idEmplacement;
	private int idChambre;
	private int idDepot;
	private int idDonateur;
	private int idDon;
	private String typeA;
	private String etat;
	private int status;
	
	private String type;
	private String genre;
	private int count;
	
	public Emplacement(int idEmplacement,int idDepot,int idDon,int idDonateur, String typeA) {
		this.idEmplacement = idEmplacement;
		this.idDepot = idDepot;
		this.idDon = idDon;
		this.idDonateur = idDonateur;
		this.typeA = typeA;
	}
	
	public Emplacement(int idEmplacement, int idChambre, int idDepot, int idDon, String typeA, String etat, int status,	String type, String genre, int count) {	
		this.idEmplacement = idEmplacement;
		this.idChambre = idChambre;
		this.idDepot = idDepot;
		this.idDon = idDon;
		this.typeA = typeA;
		this.etat = etat;
		this.status = status;
		this.type = type;
		this.genre = genre;
		this.count = count;
	}
	
	

	public Emplacement(int idDon, String type, String genre, int count) {
		
		this.idDon = idDon;
		this.type = type;
		this.genre = genre;
		this.count = count;
	}



	public int getIdEmplacement() {
		return idEmplacement;
	}

	public void setIdEmplacement(int idEmplacement) {
		this.idEmplacement = idEmplacement;
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	public int getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(int idDepot) {
		this.idDepot = idDepot;
	}

	public int getIdDon() {
		return idDon;
	}

	public void setIdDon(int idDon) {
		this.idDon = idDon;
	}

	public String getTypeA() {
		return typeA;
	}

	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIdDonateur() {
		return idDonateur;
	}

	public void setIdDonateur(int idDonateur) {
		this.idDonateur = idDonateur;
	}
}
