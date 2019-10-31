package model;

public class Depot {
	private int idDepot;
	private String localisation;
	
	private int nbrChambres;
	private int capacityMax;
	
	
	public Depot() {
	
	}
	
	public Depot(int idDepot) {
		this.idDepot = idDepot;
	}
	
	public Depot(int idDepot, String localisation) {
		this.idDepot = idDepot;
		this.localisation = localisation;
	}
	
	

	public Depot(int idDepot, String localisation, int nbrChambres, int capacityMax) {
		this.idDepot = idDepot;
		this.localisation = localisation;
		this.nbrChambres = nbrChambres;
		this.capacityMax = capacityMax;
	}
	
	public Depot(int idDepot, String localisation, int nbrChambres) {
		this.idDepot = idDepot;
		this.localisation = localisation;
		this.nbrChambres = nbrChambres;
	}
	
	public Depot(String localisation) {
		this.localisation = localisation;
	}
	
	public Depot(int idDepot, int nbrChambres) {
		this.idDepot = idDepot;
		
		this.nbrChambres = nbrChambres;
		
	}



	public int getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(int idDepot) {
		this.idDepot = idDepot;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}



	public int getNbrChambres() {
		return nbrChambres;
	}



	public void setNbrChambres(int nbrChambres) {
		this.nbrChambres = nbrChambres;
	}



	public int getCapacityMax() {
		return capacityMax;
	}



	public void setCapacityMax(int capacityMax) {
		this.capacityMax = capacityMax;
	}
	
}
