package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DonateurAnonymeDBA {
	//Databass acces informations
		String url = "jdbc:mysql://localhost:3306/Ekessini";    
		String DBuser = "root";    
		String DBpass = "youcef110863";

	public int GetIdDon(String type, String genreType) {
		int idDon=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Don WHERE type='"+type+"' AND genre='"+genreType+"';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				idDon=res.getInt("idDon");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		//closing database connexion
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return idDon;
	}

	

	public boolean CheckChambres(String type) {
		boolean check = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from chambre WHERE type='t-shirt';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				check= true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		//closing database connexion
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return check;
	}

	

	public List<Depot> DepotsList() {
		List<Depot> depots = new ArrayList<Depot>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Depot;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				depots.add(new Depot(res.getInt("idDepot"),res.getString("localisation")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		//closing database connexion
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return depots;
	}
	
	public void EnregistrerPersonne(String nom, String prenom, String birthday, String genre, String email,String numTel) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql ="insert into personne(nom,prenom,sexe,numTel,dateNaiss,email) VALUE('"+nom+"','"+prenom+"','"+genre+"','"+numTel+"','"+birthday+"','"+email+"');";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("insert into personne is good");
			}else {
				System.out.println("insert into personne failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
	}

	public int GetIdPersonne(String email) {
		int idPersonne = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Personne WHERE email='"+email+"';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				idPersonne = res.getInt("idPersonne");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		//closing database connexion
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return idPersonne;
	}

	public void EnregistrerDonationAnonyme(int idPersonne, int idDepot, int idDon, int quantite, String date) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="INSERT INTO DonationNonP(idPersonne,idDepot,idDon,quantite,date,etat) VALUES("+idPersonne+","+idDepot+","+idDon+","+quantite+",'"+date+"','non-valider');";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("insert into donationNonP is good");
			}else {
				System.out.println("insert into donationNonP failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
	}

	

	

	public boolean CheckEmail(String email) {
		boolean verify = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Personne WHERE email='"+email+"';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				verify = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		//closing database connexion
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return verify;
	}

	public void ValiderDonationNonP(int idDonationNonP) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "UPDATE DonationNonP SET etat='valider' WHERE idDonationNonP="+idDonationNonP+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("update DonationNonP is good");
			}else {
				System.out.println("update DonationNonP failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
	}

	public boolean UpdateEmplacement(int idDonateurNonP) {
		boolean verify=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "UPDATE Emplacement SET etat='occupe' WHERE idDonateur="+idDonateurNonP+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("update Empalacement is good");
				verify= true;
			}else {
				System.out.println("update Empalacement failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return verify;
	}

	

	

	public void DeleteDonateurNonP(int idDonateurNonP) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "DELETE FROM Personne WHERE idPersonne="+idDonateurNonP+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("DELETE FROM Empalacement is good");
			}else {
				System.out.println("DELETE FROM Empalacement failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
	}



	public List<Chambre> EmplacementValable(int idDepot, int idDon, int quantite) {
		List<Chambre> ChambresDispo = new ArrayList<Chambre>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select *,COUNT(*) as max from emplacement WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND typeA='non-permanent' AND etat='libre' group by idChambre;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				if(res.getInt("max") >= quantite) {
					ChambresDispo.add(new Chambre(res.getInt("idChambre"),res.getInt("max")));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		//closing database connexion
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return ChambresDispo;
	}



	



	public void ReserverEmplacement(int idChambre, int idDepot, int idDon, int idPersonne, int quantite) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "UPDATE Emplacement SET etat='reserve' ,idDonateur="+idPersonne+" WHERE idChambre="+idChambre+" AND idDepot="+idDepot+" AND idDon="+idDon+" AND typeA='non-permanent' AND etat='libre' AND status=0 LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("emplacement reserve  is good");
			}else {
				System.out.println("emplacement reserve failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
	}



	public boolean ResetEmplacement(int idDonateurNonP, int idDepot, int idDon, int quantite) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET idDonateur=0, etat='libre' WHERE idDonateur="+idDonateurNonP+"  AND idDepot="+idDepot+" AND idDon="+idDon+" LIMIT "+quantite+"; ";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("emplacement reset  is good");
				verify=true;
			}else {
				System.out.println("emplacement reset failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
			 	if(res != null) { res.close(); }
			 	if(stat != null) { stat.close(); }
			 	if(conn != null) { conn.close(); }
			 }catch(SQLException e) {
			 		e.printStackTrace();
			 }
		}
		
		return verify;
	}

}
