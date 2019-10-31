package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Necessiteux_AdmisDBA {
	
	//Databass acces informations
			String url = "jdbc:mysql://localhost:3306/Ekessini";    
			String DBuser = "root";    
			String DBpass = "youcef110863";
	
	
	public int getNAdmCounter() {
		int count = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from personne INNER JOIN Necessiteux INNER JOIN Necessiteux_Admis WHERE personne.idPersonne = Necessiteux.idNecessiteux AND Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count + 1;
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
		
		
		return count;
	}


	public List<Necessiteux_Admis> getNecessiteuxAdmisList() {
		List<Necessiteux_Admis> necessiteuxAdmisList = new ArrayList<Necessiteux_Admis>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="select * from Personne inner join Necessiteux on Personne.idPersonne = Necessiteux.idNecessiteux INNER JOIN Necessiteux_Admis on Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				necessiteuxAdmisList.add(new Necessiteux_Admis(res.getString("nom"),res.getString("prenom"),res.getString("sexe"),res.getString("numTel"),res.getString("dateNaiss"),res.getString("email"),res.getString("sitFamiliale"),res.getInt("paixMois")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return necessiteuxAdmisList;
	}

}
