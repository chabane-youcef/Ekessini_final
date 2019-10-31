package model;

public class Chambre {
	private int idDepot;
	private int idChambre;
	private String type;
	private int max;
	
	private String localisation;
	private String action;
	
	private int capacitePermanant;
	private int capaciteNonPermanant;
	
	private int pm;
	private int pf;
	private int pmoc;
	private int pfoc;
	private int npm;
	private int npf;
	private int npmoc;
	private int npfoc;
	
	
	
	

	public Chambre(int idDepot,int idChambre,String type, int max,int pmoc, int pm, int pfoc, int pf, int npmoc, int npm, int npfoc, int npf) {
		this.idDepot = idDepot;
		this.idChambre = idChambre;
		this.type = type;
		this.max = max;
		this.pmoc = pmoc;
		this.pm = pm;
		this.pfoc = pfoc;
		this.pf = pf;
		this.npmoc = npmoc;
		this.npm = npm;
		this.npfoc= npfoc;
		this.npf = npf;

	}
	
	public Chambre(int ididChambre, int max) {
		this.idChambre = ididChambre;
		this.max = max;
	}
	
	public Chambre(int idDepot, int idChambre,String type) {
		this.idDepot = idDepot;
		this.idChambre = idChambre;
		this.type = type;
	}
	
	public Chambre(int idDepot,String type, int max,int capacitePermanant, int capaciteNonPermanant) {
		this.idDepot = idDepot;
		this.type = type;
		this.max = max;
		this.capacitePermanant = capacitePermanant;
		this.capaciteNonPermanant= capaciteNonPermanant;
	}
	
	public Chambre(String type, int max,int capacitePermanant, int capaciteNonPermanant) {
		this.type = type;
		this.max = max;
		this.capacitePermanant = capacitePermanant;
		this.capaciteNonPermanant= capaciteNonPermanant;
	}
	
	public Chambre() {
		
	}
	
	public Chambre(String action,int idDepot, int idChambre, String type, int max) {
		this.idDepot = idDepot;
		this.idChambre = idChambre;
		this.type = type;
		this.max = max;
		this.action =action;
	}
	
	public Chambre(int idDepot, int idChambre, String type, int max,String localisation) {
		this.idDepot = idDepot;
		this.idChambre = idChambre;
		this.type = type;
		this.max = max;
		this.localisation =localisation;
	}
	
	public Chambre(int idDepot, int idChambre, String type, int max) {
		this.idDepot = idDepot;
		this.idChambre = idChambre;
		this.type = type;
		this.max = max;
	}
	
	

	public Chambre(int idDepot, String type, int max) {
		this.idDepot = idDepot;
		this.type = type;
		this.max = max;
	}
	
	public Chambre( String type, int max) {
		this.type = type;
		this.max = max;
	}

	public int getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(int idDepot) {
		this.idDepot = idDepot;
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getCapaciteNonPermanant() {
		return capaciteNonPermanant;
	}

	public void setCapaciteNonPermanant(int capaciteNonPermanant) {
		this.capaciteNonPermanant = capaciteNonPermanant;
	}

	public int getCapacitePermanant() {
		return capacitePermanant;
	}

	public void setCapacitePermanant(int capacitePermanant) {
		this.capacitePermanant = capacitePermanant;
	}

	public int getNpfoc() {
		return npfoc;
	}

	public void setNpfoc(int npfoc) {
		this.npfoc = npfoc;
	}
	
	public int getPm() {
		return pm;
	}

	public void setPm(int pm) {
		this.pm = pm;
	}

	public int getPf() {
		return pf;
	}

	public void setPf(int pf) {
		this.pf = pf;
	}

	public int getPmoc() {
		return pmoc;
	}

	public void setPmoc(int pmoc) {
		this.pmoc = pmoc;
	}

	public int getPfoc() {
		return pfoc;
	}

	public void setPfoc(int pfoc) {
		this.pfoc = pfoc;
	}

	public int getNpm() {
		return npm;
	}

	public void setNpm(int npm) {
		this.npm = npm;
	}

	public int getNpf() {
		return npf;
	}

	public void setNpf(int npf) {
		this.npf = npf;
	}

	public int getNpmoc() {
		return npmoc;
	}

	public void setNpmoc(int npmoc) {
		this.npmoc = npmoc;
	}
	
	
}
