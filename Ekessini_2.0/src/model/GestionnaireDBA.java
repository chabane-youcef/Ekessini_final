package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestionnaireDBA {
	
	//Databass acces informations
		String url = "jdbc:mysql://localhost:3306/Ekessini";    
		String DBuser = "root";    
		String DBpass = "youcef110863";
	
	public List<Emplacement> ListEmplacementValider() {
		List<Emplacement> DonationValider = new ArrayList<Emplacement>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select *,count(*) as count from Emplacement INNER JOIN Don ON Emplacement.idDon = Don.idDon WHERE Emplacement.etat='occupe' group by Emplacement.idDon; ";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				DonationValider.add(new Emplacement(res.getInt("idDon"),res.getString("type"),res.getString("genre"),res.getInt("count")));
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
		
		return DonationValider;
	}

	public void CreatDepot(String nomDepot) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="insert into Depot(localisation) values ('"+nomDepot+"');";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert depot is succesfull");
			}else {
				System.out.println("failed to insert into Depot");
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

	public int GetIdDepot(String nomDepot) {
		int id=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Depot WHERE localisation='"+nomDepot+"';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				id= res.getInt("idDepot");
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
		
		return id;
	}

	public void CreateChambre(int idDepot, Chambre x) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="insert into Chambre(idDepot,type,max) VALUES ("+idDepot+",'"+x.getType()+"',"+x.getMax()+");";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert Chambre is succesfull");
			}else {
				System.out.println("failed to insert into Chambre");
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

	public List<Depot> GetChambresCount() {
		List<Depot> chambresCount = new ArrayList<Depot>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select *,COUNT(idChambre) as nbrChambres from Chambre GROUP BY idDepot;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				chambresCount.add(new Depot(res.getInt("idDepot"),res.getInt("nbrChambres")));
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
		
		return chambresCount;
	}

	public List<String> GetIdDeposLocalisation() {
		List<String> DepotsLocalisations = new ArrayList<String>();
		
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
				DepotsLocalisations.add(res.getString("localisation"));
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
		
		return DepotsLocalisations;
	}

	public void DeleteDepot(int idDepot) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="DELETE FROM Depot WHERE idDepot="+idDepot+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			
			
			
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

	public List<Donation> GetDonationsList() {
		List<Donation> donations = new ArrayList<Donation>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Donation	INNER JOIN Don ON Donation.idDon = Don.idDon INNER JOIN Depot ON Donation.idDepot = Depot.idDepot INNER JOIN Personne ON Personne.idPersonne = Donation.idDonateur INNER JOIN Donateur ON Personne.idPersonne = Donateur.idDonateur WHERE Donation.etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				donations.add(new Donation(res.getInt("idDonation"),res.getInt("idDonateur"),res.getInt("idDon"),res.getInt("idDepot"),res.getInt("quantite"),res.getString("date"),res.getInt("period"),res.getString("type"),res.getString("genre"),res.getString("localisation"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("numTel"),res.getInt("nbrAvertissement")));
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
		
		return donations;
	}

	public void ValiderDonation(int idDonation) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Donation SET etat='valider' WHERE idDonation="+idDonation+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update donation is succesfull");
			}else {
				System.out.println("failed to update donation");
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

	

	public void RefuserDonation(int idDonation) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql ="DELETE FROM Donation WHERE idDonation="+idDonation+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update donation is succesfull");
			}else {
				System.out.println("failed to update donation");
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

	

	public List<Demande> GetDemandesList() {
		List<Demande> demandes = new ArrayList<Demande>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Demande	INNER JOIN Personne ON Demande.idNecessiteuxAdmis = Personne.idPersonne INNER JOIN Necessiteux ON Demande.idNecessiteuxAdmis = Necessiteux.idNecessiteux INNER JOIN Necessiteux_Admis ON Demande.idNecessiteuxAdmis = Necessiteux_Admis.idNecessiteuxAdmis INNER JOIN Don ON Demande.idDon = Don.idDon INNER JOIN Depot ON Demande.idDepot = Depot.idDepot WHERE etat='non-valider' ORDER BY priorite;";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				demandes.add(new Demande(res.getInt("idDemande"),res.getInt("idNecessiteuxAdmis"),res.getInt("idDon"),res.getInt("idDepot"),res.getInt("quantite"),res.getString("date"),res.getString("nom"),res.getString("prenom"),res.getString("dateNaiss"),res.getString("sexe"),res.getString("email"),res.getString("numTel"),res.getString("sitFamiliale"),res.getInt("NbrEnfants"),res.getInt("paixMois"),res.getString("priorite"),res.getString("localisation"),res.getString("type"),res.getString("genre"),res.getInt("nbrAvertissement")));
				/*you are here*/
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
		
		return demandes;
	}

	public void RefuserDemande(int idDemande) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql ="Delete From Demande WHERE idDemande = "+idDemande+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update Emplacement is good");
			}else {
				System.out.println("failed to update Emplacement");
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

	public boolean ViderEmplacement(int idDon, int idDepot, int quantite) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET status=0 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=1 LIMIt "+quantite+"";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update Emplacement is good");
				verify = true;
			}else {
				System.out.println("failed to update Emplacement");
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

	public boolean ValiderDemande(int idDemande) {
		boolean verify = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Demande SET etat='valider' WHERE idDemande ="+idDemande+" ;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update demande is good");
				verify = true;
			}else {
				System.out.println("failed to update demande");
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


	public boolean UpdateNbrAvertissement(int idNecessiteux, int nbrAvertissement) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Necessiteux_Admis Set nbrAvertissement ="+nbrAvertissement+"  WHERE idNecessiteuxAdmis ="+idNecessiteux+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update nbrAvertissement is good");
				verify = true;
			}else {
				System.out.println("failed to update nbrAvertissement in  Necessiteux_Admis");
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


	public void AlertNecessiteux(String email,int nbrAvertissement) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress =	email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b>votre demande a été refusée.</b><br>";
        message +="<br><b>Vous avez "+nbrAvertissement+"/3 avertissements</b><br>";
        message +="<br><b>si tu veux , tu peux faire un autre demande</b><br>";
        message += "<br><font color=red>Admin Chabane Youcef, Ekessini</font>";
        // sets SMTP server properties
		Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
       
        Message msg = new MimeMessage(session);
        try {
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

	public void NotifyNecessiteux(String email, int nbrAvertissement) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress =	email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b>votre compte a ete effacer.</b><br>";
        message +="<br><b>a cause de "+nbrAvertissement+"/3 avertissements</b><br>";
        message +="<br><b>si tu veux , tu peux faire u autre compte pour demande autre fois</b><br>";
        message += "<br><font color=red>Admin Chabane Youcef, Ekessini</font>";
        // sets SMTP server properties
		Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
       
        Message msg = new MimeMessage(session);
        try {
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
        }catch(Exception e) {
        	e.printStackTrace();
        }
	}

	public boolean UpdateNbrAvertissementDonateur(int idDonateur, int nbrAvertissement) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Donateur Set nbrAvertissement ="+nbrAvertissement+"  WHERE idDonateur ="+idDonateur+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update nbrAvertissement is good");
				verify = true;
			}else {
				System.out.println("failed to update nbrAvertissement in  Necessiteux_Admis");
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

	public void AlertDonateur(String email ,int nbrAvertissement ) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress =	email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b>votre Demande a ete effacer.</b><br>";
        message +="<br><b>a cause de votre rotard , maintenent vous avez "+nbrAvertissement+"/3 avertissements</b><br>";
        message +="<br><b>si tu veux , tu peux faire un autre  Donation</b><br>";
        message += "<br><font color=red>Admin Chabane Youcef, Ekessini</font>";
        // sets SMTP server properties
		Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
       
        Message msg = new MimeMessage(session);
        try {
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

	

	public void NotifyEmailDonateur(String email, int nbrAvertissement) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress =	email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b>votre compte a ete blocker.</b><br>";
        message +="<br><b>a cause de "+nbrAvertissement+"/3 avertissements</b><br>";
        message +="<br><b>si tu veux re-activer le compte, svp contacter l'association via email: ekessini2019@gmail.com</b><br>";
        message += "<br><font color=red>Admin Chabane Youcef, Ekessini</font>";
        // sets SMTP server properties
		Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
       
        Message msg = new MimeMessage(session);
        try {
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

	public void InsertUpdatedDonation(int idDonateur, int idDon, int idDepot, int quantite, String date, int period) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql ="INSERT INTO Donation(idDonateur,idDon,idDepot,quantite,date,period,etat) VALUES ("+idDonateur+","+idDon+","+idDepot+","+quantite+",'"+date+"',"+period+",'non-valider');"; 
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert into Donation has failed");
				
			}else {
				System.out.println("insert into Donation is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public int GetIdChambre(int idDepot, String type) {
		int idChambre = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM  Chambre WHERE idDepot="+idDepot+" AND type='"+type+"';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				idChambre = res.getInt("idChambre");
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
		
		return idChambre;
	}

	

	public List<Donation> GetDonationsNonPList() {
		
		List<Donation> donationNonP = new ArrayList<Donation>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM DonationNonP	INNER JOIN Personne ON DonationNonP.idPersonne = Personne.idPersonne INNER JOIN Depot ON DonationNonP.idDepot = Depot.idDepot INNER JOIN Don ON DonationNonP.idDon = Don.idDon WHERE etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				donationNonP.add(new Donation(res.getInt("idDonationNonP"),res.getInt("idPersonne"),res.getInt("idDon"),res.getInt("idDepot"),res.getInt("quantite"),res.getString("date"),res.getString("type"),res.getString("genre"),res.getString("localisation"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("numTel")));
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
		
		return donationNonP;
	}

	

	

	public List<Chambre> GetChambresList(int idDepot) {
		List<Chambre> chambres = new ArrayList<Chambre>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT Chambre.idDepot,idChambre,type,max,localisation FROM Chambre INNER JOIN Depot ON Chambre.idDepot = Depot.idDepot WHERE Chambre.idDepot="+idDepot+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				chambres.add(new Chambre(res.getInt("idDepot"),res.getInt("idChambre"),res.getString("type"),res.getInt("max"),res.getString("localisation")));
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
		
		return chambres;
	}

	public String DepotLocalisation(int idDepot) {
		String localisation="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Depot WHERE idDepot="+idDepot+"";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				localisation = res.getString("localisation");
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
		
		return localisation;
	}

	public void UpdateChambre(Chambre x) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Chambre SET max="+x.getMax()+" WHERE idChambre="+x.getIdChambre()+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("update Chambre has failed");
				
			}else {
				System.out.println("update Chambre is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void DeleteChambre(Chambre x) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="DELETE FROM Chambre WHERE idChambre="+x.getIdChambre()+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("update Chambre has failed");
				
			}else {
				System.out.println("update Chambre is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}

	public void AddChambre(Chambre x) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="INSERT INTO Chambre(idDepot,type,max) VALUES("+x.getIdDepot()+",'"+x.getType()+"',"+x.getMax()+");";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Chambre has failed");
				
			}else {
				System.out.println("insert Chambre is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public int GetIdNewChambre(int idDepot, Chambre x) {
		int idChambre=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Chambre WHERE idDepot="+idDepot+" AND type='"+x.getType()+"' AND max="+x.getMax()+"";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				idChambre = res.getInt("idChambre");
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
		
		return idChambre;
	}

	public void InsertEmplacementHomme(int idDepot, int idChambre, int idDonM) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="INSERT INTO Emplacement	(idChambre,idDepot,idDon,idDonateur,typeA,etat,status) VALUES("+idChambre+","+idDepot+","+idDonM+",0,'permanent','libre',0);";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void InsertEmplacementFemme(int idDepot, int idChambre, int idDonF) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="INSERT INTO Emplacement	(idChambre,idDepot,idDon,idDonateur,typeA,etat,status) VALUES("+idChambre+","+idDepot+","+idDonF+",0,'permanent','libre',0);";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void InsertEmpalcementNonPHomme(int idDepot, int idChambre, int idDonM) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="INSERT INTO Emplacement	(idChambre,idDepot,idDon,idDonateur,typeA,etat,status) VALUES("+idChambre+","+idDepot+","+idDonM+",0,'non-permanent','libre',0);";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void InsertEmpalcementNonPFemme(int idDepot, int idChambre, int idDonF) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="INSERT INTO Emplacement	(idChambre,idDepot,idDon,idDonateur,typeA,etat,status) VALUES("+idChambre+","+idDepot+","+idDonF+",0,'non-permanent','libre',0);";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public int CountEmplacementsValable(int idDon, int idDepot, int idChambre) {
		int count=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Emplacement WHERE idDonateur=0 AND idDepot="+idDepot+" AND idChambre="+idChambre+" AND idDon="+idDon+" AND typeA='permanent' AND etat='libre';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count+1;
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

	public void UpdateRecievedEmplacements(int idDonateur, int idDepot, int idChambre, int idDon, int quantite) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement Set etat='occupe' WHERE idDepot="+idDepot+" AND idChambre="+idChambre+" AND idDon="+idDon+" AND idDonateur="+idDonateur+" AND typeA='permanent' AND etat='reserve' LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void ReserveNewEmplacements(int idDonateur, int idDon, int idDepot, int idChambre, int quantite) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET idDonateur="+idDonateur+" , etat='reserve' WHERE idDepot="+idDepot+" AND idChambre="+idChambre+" AND idDon="+idDon+" AND typeA='permanent' AND etat='libre' LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public boolean ResetEmplacement(int idDonateur, int idDon, int idDepot, int idChambre, int quantite) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET idDonateur=0 , etat='libre' WHERE idDepot="+idDepot+" AND idChambre="+idChambre+" AND idDon="+idDon+" AND idDonateur="+idDonateur+" AND typeA='permanent' AND etat='reserve' LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
				verify = true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return verify;
	}

	public void BlockALLDonation(int idDonateur) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="DELETE FROM Donation WHERE idDonateur="+idDonateur+" AND etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("delete non-valide donations failed");
				
			}else {
				System.out.println("delete non-valide donations succed");
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public boolean ResetAllEmplacements(int idDonateur) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET idDonateur=0 , etat='libre' WHERE idDonateur="+idDonateur+" AND etat='reserve';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
				verify = true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return verify;
	}

	public void ResetNecessiteuxEmplacements(int idDepot, int idDon, int quantite) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement  SET idDonateur=0 , etat='libre', status=0 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=1 LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert Empalcement has failed");
				
			}else {
				System.out.println("insert Empalcement is succeseful");
				verify = true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public List<Demande> BlockedNecessiteuxDemandes(int idNecessiteux) {
		List<Demande> demandes = new ArrayList<Demande>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="select * from Demande WHERE idNecessiteuxAdmis="+idNecessiteux+" AND etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				demandes.add(new Demande(res.getInt("idDepot"),res.getInt("idDon"),res.getInt("quantite")));
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
		
		return demandes;
	}

	public void BlockNecessiteux(int idNecessiteux, int nbrAvertissement) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Necessiteux_Admis SET nbrAvertissement="+nbrAvertissement+" WHERE idNecessiteuxAdmis="+idNecessiteux+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("blocking necessiteux succed");
			}else {
				System.out.println("blocking necessiteux failed");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public List<Depot> listDepot() {
		List<Depot> depots = new ArrayList<Depot>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql= "SELECT * FROM Depot;";
		
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

	public int GetIdDon(String type, String genre) {
		int idDon=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql= "SELECT * FROM Don WHERE type='"+type+"' AND genre='"+genre+"';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				idDon = res.getInt("idDon");
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

	public boolean CheckTransfert(int idDepot, int idDon, int quantite) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql= "select *,count(*) AS valable from Emplacement WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=0 GROUP BY idDon;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				if(res.getInt("valable") >= quantite) {
					verify = true;
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
		
		return verify;
	}

	public List<Emplacement> TransferedEmplacements(int idDepot1, int idDon, int quantite) {
		List<Emplacement> emplacements = new ArrayList<Emplacement>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Emplacement WHERE idDepot="+idDepot1+" AND idDon="+idDon+" AND etat='occupe' AND status=0 LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				emplacements.add(new Emplacement(res.getInt("idEmplacement"),res.getInt("idDepot"),res.getInt("idDon"),res.getInt("idDonateur"),res.getString("typeA")));
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
		
		return emplacements;
	}

	public boolean CheckForwardedDepot(int idDepot2, int idDon, String typeA) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Emplacement WHERE idDepot="+idDepot2+" AND idDon="+idDon+" AND typeA='"+typeA+"' AND etat='libre' AND status=0;";
		
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

	public void TransfertEmpalcement(int idEmplacement) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET etat='libre',idDonateur=0 WHERE idEmplacement="+idEmplacement+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("tranfert emplacement depot1 failed");
			}else {
				System.out.println("tranfert emplacement depot1 good");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public void ForwardDepotTransfer(int idDepot2, int idDon, int idDonateur, String typeA) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET etat='occupe', idDonateur="+idDonateur+" WHERE idDepot="+idDepot2+" AND idDon="+idDon+" AND typeA='"+typeA+"' AND etat='libre' AND status=0 LIMIT 1;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("tranfert emplacement depot2 failed");
			}else {
				System.out.println("tranfert emplacement depot2 good");	
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public List<Demande> ListDemandesWaiting() {
		List<Demande> demandes = new ArrayList<Demande>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Demande INNER JOIN Necessiteux_Admis ON Demande.idNecessiteuxAdmis = Necessiteux_Admis.idNecessiteuxAdmis WHERE etat='attente' ORDER BY Necessiteux_Admis.priorite;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				demandes.add(new Demande(res.getInt("idDemande"),res.getInt("idNecessiteuxAdmis"),res.getInt("idDepot"),res.getInt("idDon"),res.getInt("quantite")));
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
		
		return demandes;
	}

	public boolean EmplacementDisponible(int idDepot, int idDon, int quantite) {
		boolean verify = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT *,Count(*) AS valable FROM Emplacement WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=0 GROUP BY idDon;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				if(res.getInt("valable") >= quantite) {
					System.out.println("4 pantalon dispo");
					verify = true;
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
		
		return verify;
	}

	public boolean  ReserveEmplacement(int idDepot, int idDon, int quantite) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement Set status=1 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=0 LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("demande en attent's emplacements hasn't been reserved");
			}else {
				System.out.println("demande en attent's emplacements has been reserved");
				
				verify=true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return verify;
	}

	public void UpdateDemande(int idDemande, String date) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql = "UPDATE Demande SET date='"+date+"' , etat='non-valider' WHERE idDemande="+idDemande+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("demande hasn't been reserved");	
			}else {
				System.out.println("demande has been updated");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public String GetEmail(int idNecessiteux) {
		String email="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Personne WHERE idPersonne="+idNecessiteux+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				email = res.getString("email");
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
		
		return email;
	}

	public void NotifyDemande_Attent_eNecessiteux(String email,String date) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress =	email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b>votre Demande en attente est mise a jours.</b><br>";
        message +="<br><b>tu peux prendre votre demande le : "+date+"</b><br>";
        message += "<br><font color=red>Admin Chabane Youcef, Ekessini</font>";
        // sets SMTP server properties
		Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        Message msg = new MimeMessage(session);
        try {
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

	public int CountPMOC(int idChambre, int idDonM) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM Emplacement WHERE idChambre="+idChambre+" AND typeA='permanent' AND idDon="+idDonM+" AND etat='occupe';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountPM(int idChambre, int idDonM) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonM+" AND typeA='permanent';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountPFOC(int idChambre, int idDonF) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonF+" AND typeA='permanent' AND etat='occupe';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountPF(int idChambre, int idDonF) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonF+" AND typeA='permanent';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountNPMOC(int idChambre, int idDonM) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonM+" AND etat='occupe' AND typeA='non-permanent';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountNPM(int idChambre, int idDonM) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonM+" AND typeA='non-permanent';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountNPFOC(int idChambre, int idDonF) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonF+" AND typeA='non-permanent' AND etat='occupe';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	public int CountNPF(int idChambre, int idDonF) {
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Emplacement WHERE idChambre="+idChambre+" AND idDon="+idDonF+" AND typeA='non-permanent';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				count = count +1;
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
		
		return count;
	}

	
}
