package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

import org.apache.catalina.mbeans.ClassNameMBean;

public class NecessiteuxDBA {
	
	//Databass acces informations
		String url = "jdbc:mysql://localhost:3306/Ekessini";    
		String DBuser = "root";    
		String DBpass = "youcef110863";
		
		
	public int getNecCounter() {
		int count = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from personne Where idPersonne in (select idNecessiteux from Necessiteux where idNecessiteux not in (select idNecessiteuxAdmis from necessiteux_admis));";
		
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


	public List<Necessiteux> getNecessiteuxList() {
		List<Necessiteux> NecNonP = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="select *from personne as p inner join Necessiteux as n on p.idPersonne = n.idNecessiteux WHERE n.idNecessiteux not in(select idNecessiteuxAdmis from necessiteux_Admis);";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				NecNonP.add(new Necessiteux(res.getInt("idPersonne"),res.getString("nom"),res.getString("prenom"),res.getString("sexe"),res.getString("sitFamiliale"),res.getString("dateNaiss")));
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
		
		return NecNonP;
	}


	public Necessiteux getNecessiteuxInfo(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select *from personne inner join Necessiteux on Personne.idPersonne = Necessiteux.idNecessiteux WHERE Necessiteux.idNecessiteux not in(select idNecessiteuxAdmis from necessiteux_Admis) AND Necessiteux.IdNecessiteux = "+id+";";
		Necessiteux NecNonP = new Necessiteux();
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				 NecNonP = new Necessiteux(Integer.parseInt(id),res.getString("nom"),res.getString("prenom"),res.getString("sexe"),res.getString("numTel"),res.getString("dateNaiss"),res.getString("email"),res.getString("sitFamiliale"),res.getInt("NbrEnfants"),res.getInt("paixMois"));
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
		
		return NecNonP;
	}


	public String getEmailNecessiteux(int id) {
		
		String email="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select email from Personne	INNER JOIN Necessiteux on Personne.idPersonne = Necessiteux.idNecessiteux WHERE   Necessiteux.idNecessiteux NOT IN (SELECT idNecessiteuxAdmis FROM Necessiteux_Admis ) AND Necessiteux.idNecessiteux = "+id+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				email = res.getString("email");
				System.out.println(email);
				
				
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
		
		return email;
	}


	public void sendRefusedEmail(String email) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<b>vous avez été refusé comme un necessiteux dans l'association Ekessini!</b><br>";
        message += "<font color=red>Admin Chabane Youcef, Ekessini</font>";
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


	public void sendValiderEmail(String email,String priorite,String Npassword) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
		message += "<b>vous avez été accepté comme un necessiteux dans l'association Ekessini!</b><br>";
		message += "<b>Votre compte est : </b><br>";
		message +="<b>Email : "+email+"</b>";
		message +="<b>Mot de Passe : "+Npassword+"</b>";	
        message += "<font color=red>Admin Chabane Youcef, Ekessini</font>";
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


	public void InsertIntoNecessiteuxAdmis(int id, String priorite, String password) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql = "insert into Necessiteux_Admis(idNecessiteuxAdmis, priorite, motdePasse,nbrAvertissement) VALUES ("+id+",'"+priorite+"','"+password+"',0);";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert into Necessiteux_Admis is done");
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
		
		String sql ="select * from Personne WHERE email='"+email+"'";
		
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


	public void InscriptionNecessiteux(Necessiteux necessiteux) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql = "insert into personne(nom,prenom,sexe,numTel,dateNaiss,email) VALUES ('"+necessiteux.getNom()+"','"+necessiteux.getPrenom()+"','"+necessiteux.getSexe()+"','"+necessiteux.getNumTel()+"','"+necessiteux.getDateNais()+"','"+necessiteux.getEmail()+"');";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert into Necessiteuxis done");
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


	public int getNecessiteuxId(String email) {
		int id= 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Personne WHERE email = '"+email+"'";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				id = res.getInt("idPersonne");
				System.out.println(id);
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


	public void InsertSituationNecessiteux(Necessiteux necessiteuxInfoSecondaire) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql = "insert into Necessiteux(idNecessiteux,sitFamiliale,paixMois,NbrEnfants) VALUES ("+necessiteuxInfoSecondaire.getIdNecessiteux()+",'"+necessiteuxInfoSecondaire.getSituationFam()+"',"+necessiteuxInfoSecondaire.getNbrEnfants()+","+necessiteuxInfoSecondaire.getPaixMois()+");";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert into Necessiteuxis done");
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


	public void NotifyEmailNecessiteux(Necessiteux necessiteuxInfoPerso) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = necessiteuxInfoPerso.getEmail();
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
		message += "<b>vous avez inscrier comme un necessiteux dans l'association Ekessini!</b><br>";
		message += "<b>attendre validation de votre compte via l'admin </b><br>";
		
        message += "<font color=red>Admin Chabane Youcef, Ekessini</font>";
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


	public boolean LoginNecessiteux(String email, String pswrd) {
		boolean verify = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Personne	INNER JOIN Necessiteux on Personne.idPersonne = Necessiteux.idNecessiteux INNER JOIN Necessiteux_Admis on Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis WHERE Personne.idPersonne NOT IN (SELECT idDonateur FROM Donateur) AND Personne.email ='"+email+"' AND Necessiteux_Admis.motdePasse='"+pswrd+"' AND Necessiteux_Admis.nbrAvertissement < 3;";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				verify = false;
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


	public int GetIdNecessiteux(String email) {
		int id =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Personne	INNER JOIN Necessiteux on Personne.idPersonne = Necessiteux.idNecessiteux INNER JOIN Necessiteux_Admis on Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis WHERE Personne.idPersonne NOT IN (SELECT idDonateur FROM Donateur) AND Personne.email ='"+email+"';";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				id = res.getInt("idPersonne");
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
		
		return id;
	}


	public Necessiteux GetNecessiteuxInformations(int idNecessiteux) {
		Necessiteux necessiteux = new Necessiteux();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Personne	INNER JOIN Necessiteux on Personne.idPersonne = Necessiteux.idNecessiteux INNER JOIN Necessiteux_Admis on Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis WHERE Personne.idPersonne NOT IN (SELECT idDonateur FROM Donateur) AND Personne.idPersonne ='"+idNecessiteux+"';";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				necessiteux = new Necessiteux(res.getInt("idPersonne"),res.getString("nom"),res.getString("prenom"));
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
		
		return necessiteux;
	}


	public int GetIdDon(String type, String gendre) {
		int idDon=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Don WHERE type='"+type+"' and genre='"+gendre+"';";
		
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



	


	public void RegisterDemande(int idDon, int idNecessiteux, int idDepot, int quantite, String time) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="INSERT INTO  demande(idDon,idNecessiteuxAdmis,idDepot,quantite,date,etat) VALUES("+idDon+","+idNecessiteux+","+idDepot+","+quantite+",'"+time+"','non-valider');";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert into demande done");
			}else {
				System.out.println("insert into demande failed");
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


	


	public List<Demande> getListDemandes(int idNecessiteux) {
		List<Demande> demandesList = new ArrayList<Demande>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from Demande	INNER JOIN Personne ON Demande.IdNecessiteuxAdmis = Personne.idPersonne INNER JOIN Don ON Demande.idDon = Don.idDon INNER JOIN Depot ON Demande.idDepot = Depot.idDepot WHERE idNecessiteuxAdmis = "+idNecessiteux+" AND Demande.etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				demandesList.add(new Demande(res.getInt("idDemande"),res.getInt("idNecessiteuxAdmis"),res.getInt("idDon"),res.getInt("idDepot"),res.getInt("quantite"),res.getString("date"),res.getString("etat"),res.getString("localisation"),res.getString("type"),res.getString("genre")));
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
		return demandesList;
	}


	public void NotifySMSNecessiteux(Necessiteux necessiteux) {
		try {
			// Construct data
			
			String apiKey = "apikey=" + "2zLNAKjhDag-L87vq7yTZtjM6GsLiv9S06QbjCRJBP";
			String message = "&message=" + "Welcome to Ekessini /n vous avez inscrier dans Ekessini comme Necessiteux ,/n attendre validation de votre compte via l'admin";
			String sender = "&sender=" + "Ekessini Admin";
			String numbers = "&numbers=" + "213"+necessiteux.getNumTel()+"";
			
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			e.printStackTrace();
			
		}
		
	}


	public void sendValiderSMS(String numTel, String email, String password) {
		try {
			// Construct data
			
			String apiKey = "apikey=" + "2zLNAKjhDag-L87vq7yTZtjM6GsLiv9S06QbjCRJBP";
			String message = "&message=" + "Votre compte a ete valider par Admin. \n les informations de votre compte : \n email: "+email+" , mot de passe: "+password+"";
			String sender = "&sender=" + "Ekessini's Admin";
			String numbers = "&numbers=" + "213"+numTel+"";
			
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			
		}
		
	}


	


	public List<Demande> GetOldDemandes(int idNecessiteux) {
		List<Demande> demandeList = new ArrayList<Demande>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="select * from demande INNER JOIN Don ON demande.idDon = Don.idDon INNER JOIN Depot ON Demande.idDepot = Depot.idDepot WHERE Demande.idNecessiteuxAdmis="+idNecessiteux+" AND etat='valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				demandeList.add(new Demande(res.getInt("idDemande"),res.getInt("quantite"),res.getString("date"),res.getString("type"),res.getString("genre"),res.getString("localisation")));
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
		
		return demandeList;
	}


	


	public void DeleteDemande(int idDemande) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql = "DELETE FROM Demande WHERE idDemande="+idDemande+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("Delete necessiteux est reussie");
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


	public void UpdateEmplacement(int idDon, int idDepot, int quantite) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql = "UPDATE Emplacement SET status=0 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=1 LIMIT "+quantite+"; ";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("Delete necessiteux est reussie");
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



	public List<Demande> GetDemandeNonValider(int idNecessiteux) {
		List<Demande> demands = new ArrayList<Demande>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="select * from demande WHERE idNecessiteuxAdmis="+idNecessiteux+" AND etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				demands.add(new Demande(res.getInt("idDepot"),res.getInt("idDon"),res.getInt("quantite")));
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
		
		return demands;
	}


	public List<Chambre> ListChambresDispo(int idDon, int quantite) {
		List<Chambre> chambres = new ArrayList<Chambre>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="select * ,COUNT(*) AS emplacementOccupe from Chambre INNER JOIN Emplacement ON Chambre.idChambre = Emplacement.idChambre WHERE Emplacement.idDon="+idDon+" AND Emplacement.etat='occupe' AND Emplacement.status=0 GROUP BY Chambre.idDepot;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				if(res.getInt("emplacementOccupe") >= quantite) {
					chambres.add(new Chambre(res.getInt("idDepot"),res.getInt("idChambre"),res.getString("type")));
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
		
		return chambres;
	}


	public Depot DepotsDispo(int i) {
		Depot x = new Depot();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Depot WHERE idDepot="+i+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				x = new Depot(res.getInt("idDepot"),res.getString("localisation"));
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
		
		return x;
	}


	public boolean ReserveEmplacements(int idDepot, int idDon, int quantite) {
		boolean verify = false;
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET status=1 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=0 LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("emplacement reserved for necessiteux");
				verify = true;
			}else {
				System.out.println("emplacement reserved failed for necessiteux");
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


	public boolean BlockNecessiteux(int idNecessiteux) {
		boolean verify = false;
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="UPDATE Necessiteux_Admis SET nbrAvertissement=3 WHERE idNecessiteuxAdmis="+idNecessiteux+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("emplacement reserved for necessiteux");
				verify = true;
			}else {
				System.out.println("emplacement reserved failed for necessiteux");
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


	public void NotifyBlockedNecessiteuxEmail(String email) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
		message += "<b>votre compte a ete blocke l'association Ekessini!</b><br>";
		message += "<b>pour le deblocker , svp contacter notre assossiation: ekessini2019@gmail.com </b><br>";
		
        message += "<font color=red>Admin Chabane Youcef, Ekessini</font>";
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


	public void DeleteNonValideDemandes(int idNecessiteux) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="DELETE FROM Demande WHERE idNecessiteuxAdmis="+idNecessiteux+" AND etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("delete non-valider demandes succed");
				
			}else {
				System.out.println("delete non-valider demandes failed");
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


	public List<Necessiteux_Admis> NecessiteuxBlocked() {
		List<Necessiteux_Admis> necessiteux = new ArrayList<Necessiteux_Admis>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Personne INNER JOIN Necessiteux ON Personne.idpersonne = Necessiteux.idNecessiteux INNER JOIN Necessiteux_Admis ON Personne.idPersonne = Necessiteux_Admis.idNecessiteuxAdmis WHERE Necessiteux_Admis.nbrAvertissement =3;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				necessiteux.add(new Necessiteux_Admis(res.getInt("idNecessiteux"),res.getString("nom"),res.getString("prenom"),res.getString("sexe"),res.getString("numTel"),res.getString("dateNaiss"),res.getString("email"),res.getString("sitFamiliale"),res.getInt("paixMois"),res.getString("priorite"),res.getInt("nbrAvertissement")));
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
		
		return necessiteux;
	}


	public int CountDemandeNecessiteuxBloque(int idNecessiteux) {
		int count =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql= "SELECT *,COUNT(*) AS nbrDemande FROM Demande WHERE idNecessiteuxAdmis="+idNecessiteux+" AND etat='valider' GROUP BY idNecessiteuxAdmis;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				count = res.getInt("nbrDemande");
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


	public String EmailNecessiteuxBloque(int idNecessiteux) {
		String email="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Personne WHERE idPersonne="+idNecessiteux+";";
		
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


	public boolean DebloquerNecessiteuxCompte(int idNecessiteux) {
		boolean verify = false;
		
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="UPDATE Necessiteux_Admis SET nbrAvertissement=0 WHERE idNecessiteuxAdmis="+idNecessiteux+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("unblock necessiteux succed");
				verify = true;
			}else {
				System.out.println("unblock necessiteux failed");
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


	public void NotifyNecessiteuxBlocked(String email) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b> votre compte a ete debloquer dans l'association Ekessini!</b><br>";
        message +="<br><b>maintenent tu peux faire autre demandes!</b><br>";
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


	public List<Depot> ListDepots() {
		List<Depot> depots = new ArrayList<Depot>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Depot;";
		
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


	public boolean EnregistrerDemandeAttente(int idNecessiteux, int idDepot, int idDon, int quantite) {
		boolean verify = false;
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql= "INSERT INTO Demande(idDon,idNecessiteuxAdmis,idDepot,quantite,etat) VALUES("+idDon+","+idNecessiteux+","+idDepot+","+quantite+",'attente');";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("insert demande attente success");
				verify = true;
			}else {
				System.out.println("insert demande attente failed");
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


	public boolean EmplacementNonReserver(int idDepot, int idDon, int count) {
		boolean verify = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT *,COUNT(*) AS count FROM Emplacement WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=0 GROUP BY idDon;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next()) {
				if(res.getInt("count") >= count) {
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


	public void AddEmplacement(int idDepot, int idDon, int count) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET status=1 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=0 LIMIT "+count+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("add Emplacement to demande success");
			}else {
				System.out.println("add Emplacement to demande failed");
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


	public void UpdateDemande(int idDemande, int quantite, String date) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="UPDATE Demande SET quantite="+quantite+", date='"+date+"' WHERE idDemande="+idDemande+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update demande success");
			}else {
				System.out.println("update demande failed");
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


	public void LibrerEmplacements(int idDepot, int idDon, int count) {
		try {     	
			Class.forName( "com.mysql.jdbc.Driver" );        
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		Connection conn = null;    
		PreparedStatement stat = null;    
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET status=0 WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND etat='occupe' AND status=1 LIMIT "+count+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("update demande success");
			}else {
				System.out.println("update demande failed");
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
	
}
