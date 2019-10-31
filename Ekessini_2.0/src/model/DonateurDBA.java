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

public class DonateurDBA {
	
	//Databass acces informations
	String url = "jdbc:mysql://localhost:3306/Ekessini";    
	String DBuser = "root";    
	String DBpass = "youcef110863";
	
	

	public boolean InsertMainInfos(Donateur donateur) {
		boolean verify = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql = "insert into personne(nom,prenom,sexe,numTel,dateNaiss,email) VALUES ('"+donateur.getNom()+"','"+donateur.getPrenom()+"','"+donateur.getSexe()+"','"+donateur.getNumTel()+"','"+donateur.getDateNais()+"','"+donateur.getEmail()+"');";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				return verify;
			}else {
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

	public int getID(String email) {
		int id= 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from personne WHERE Personne.idPersonne NOT IN(select idNecessiteux from Necessiteux inner join Necessiteux_Admis on Necessiteux.idNecessiteux = Necessiteux_Admis.idNecessiteuxAdmis) AND email='"+email+"';";
		
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

	public boolean registerPassword(int id, String pswrd) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql = "insert into Donateur(idDonateur,motdePass,nbrAvertissement) VALUES("+id+",'"+pswrd+"',0);";
		boolean verify = false;
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status == 0) {
				System.out.println("insert into donateur has failed");
				return verify;
			}else {
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

	

	public int getIdType(String type,String genre) {
		int idDon=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Don WHERE type='"+type+"' and genre='"+genre+"';";
		
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

	

	public List<Depot> getListDepot() {
		List<Depot> depots = new ArrayList<Depot>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="SELECT * FROM Depot";
		
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

	

	

	

	public void InsertIntoDonation(int idDonateur, int idDepot, int idDon, int quantite, String time,int period) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql ="INSERT INTO Donation(idDonateur,idDon,idDepot,quantite,date,period,etat) VALUES ("+idDonateur+","+idDon+","+idDepot+","+quantite+",'"+time+"',"+period+",'non-valider');"; 
		
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

	public boolean LoginDonateur(String email,String pswrd) {
		boolean verify = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql ="select * from Personne INNER JOIN Donateur on Personne.idPersonne = Donateur.idDonateur WHERE Personne.email not in (SELECT	email FROM Personne INNER JOIN Necessiteux on Personne.idPersonne = Necessiteux.idNEcessiteux) AND Personne.email='"+email+"' AND Donateur.motdePass='"+pswrd+"' AND Donateur.nbrAvertissement <> 3;";
		
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

	public void sendCompteInformationsViaEmail(Donateur donateur,String pswrd) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = donateur.getEmail();
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b> "+donateur.getNom()+" , votre données sont engregistrer dans l'association Ekessini!</b><br>";
        message +="<br><b> Votre mot de passe: "+pswrd+"</b><br>";
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

	public List<Donation> getDonateur_Donations(int idDonateur) {
		List<Donation> donationList = new ArrayList<Donation>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "select * from donation	inner join personne on donation.idDonateur = personne.idPersonne inner join Don on donation.idDon = Don.idDon inner join Depot on donation.idDepot = Depot.idDepot where idDonateur="+idDonateur+" AND etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				donationList.add(new Donation(res.getInt("idDonation"),res.getInt("idDon"),res.getInt("idDepot"),res.getInt("idDonateur"),res.getInt("quantite"),res.getString("date"),res.getString("type"),res.getString("genre"),res.getString("localisation")));
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
		return donationList;
	}

	public boolean deleteDoantion(int idDonation) {
		boolean deleted = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql ="DELETE FROM Donation Where idDonation= ?;";
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			stat.setInt(1, idDonation);
			int status = stat.executeUpdate();
			if(status != 0) {
				System.out.println("delete from donation is succecefull ");
				deleted = true;
			}else {
				System.out.println("delete from donation has failed");
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
		
		return deleted;
	}


	public void sendCompteInformationsViaSMS(String numTel,Donateur donateur) {
		try {
			// Construct data
			
			String apiKey = "apikey=" + "2zLNAKjhDag-L87vq7yTZtjM6GsLiv9S06QbjCRJBP";
			String message = "&message=" + "Bienvenez dans notre Association Ekessini,\n mr."+donateur.getNom()+" les informations de votre compte: \n email: "+donateur.getEmail()+" , \n mot de passe: "+donateur.getMotDePass()+"";
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
			e.printStackTrace();
		}
		
	}

	public List<Donation> GetOldDonations(int idDonateur) {
		List<Donation> donationList = new ArrayList<Donation>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql = "SELECT * FROM donation INNER JOIN Don ON Donation.idDon = Don.idDon INNER JOIN Depot ON Donation.idDepot = Depot.idDepot WHERE Donation.idDonateur ="+idDonateur+" AND Donation.etat='valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				donationList.add(new Donation(res.getInt("idDonation"),res.getInt("quantite"),res.getString("date"),res.getString("type"),res.getString("genre"),res.getString("localisation")));
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
		
		return donationList;
	}

	

	

	

//	

	public List<Chambre> EmplacementsValable(int idDepot, int idDon, int quantite) {
		List<Chambre> ChambresDispo = new ArrayList<Chambre>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT *,COUNT(*) AS max FROM emplacement WHERE idDepot="+idDepot+" AND idDon="+idDon+" AND typeA='permanent' AND etat='libre' GROUP BY idChambre;";
		
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

	public void ReserverEmplacements(int idChambre, int idDepot, int idDon, int idDonateur, int quantite) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "UPDATE Emplacement SET etat='reserve' ,idDonateur="+idDonateur+" WHERE idChambre="+idChambre+" AND idDepot="+idDepot+" AND idDon="+idDon+" AND typeA='permanent' AND etat='libre' AND status=0 LIMIT "+quantite+";";
		
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

	public boolean ResetEmplacement(int idDonateur, int idDon, int idDepot, int quantite) {
		boolean verify= false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Emplacement SET etat='libre' ,idDonateur=0  WHERE idDonateur="+idDonateur+" AND idDepot="+idDepot+" AND idDon="+idDon+" AND etat='reserve' AND typeA='permanent' AND status=0 LIMIT "+quantite+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("emplacement reset  is good");
				verify= true;
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

	public List<Donateur> DonateursBlocked() {
		List<Donateur> donateurs = new ArrayList<Donateur>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Personne INNER JOIN Donateur ON Personne.idPersonne = Donateur.idDonateur WHERE Donateur.nbrAvertissement=3;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				donateurs.add(new Donateur(res.getInt("idDonateur"),res.getString("nom"),res.getString("prenom"),res.getString("sexe"),res.getString("numTel"),res.getString("dateNaiss"),res.getString("email")));
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
		
		return donateurs;
	}

	public int CountDonationsDonateurBloque(int idDonateur) {
		int count=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * , COUNT(*) AS nbrDonations FROM Donation WHERE idDonateur="+idDonateur+" AND etat='valider' GROUP BY idDonateur;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if(res.next()) {
				count = res.getInt("nbrDonations");
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

	public boolean DebloquerDonateur(int idDonateur) {
		boolean verify = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Donateur SET nbrAvertissement=0 WHERE idDonateur="+idDonateur+"; ";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("debloquer donateur is good");
				verify= true;
			}else {
				System.out.println("debloquer donateur failed");
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

	public String EmailDonateur(int idDonateur) {
		String email="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT * FROM Personne WHERE idPersonne="+idDonateur+"";
		
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

	public void NotifyBlockedDonateur(String email) {
		String host = "smtp.gmail.com";
		String port = "587";
		String userName = "chabaneyoucef63@gmail.com";
		String password = "110863on";
		String toAddress = email;
		String subject = "Ekessini";
		String message = "<i>Salut!</i><br>";
        message += "<br><b> votre compte a ete debloquer dans l'association Ekessini!</b><br>";
        message +="<br><b>maintenent tu peux faire autre donations!</b><br>";
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

	public List<Donateur> TopDonateurs() {
		List<Donateur> topDonateur = new ArrayList<Donateur>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		
		String sql="SELECT *,COUNT(*) AS donations FROM Personne INNER JOIN Donateur ON Personne.idPersonne = Donateur.idDonateur INNER JOIN Donation ON Personne.idPersonne = Donation.idDonateur WHERE Donation.etat='valider' GROUP BY Donation.idDonation ORDER BY donations;";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()) {
				topDonateur.add(new Donateur(res.getInt("idDonateur"),res.getString("nom"),res.getString("prenom"),res.getString("sexe"),res.getString("numTel"),res.getString("email"),res.getInt("donations")));
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
		
		return topDonateur;
	}

	public boolean ReleaseEmplacements(int idDonateur) {
		boolean verify = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "UPDATE Emplacement SET idDonateur=0 , etat='libre' WHERE idDonateur="+idDonateur+";";
				
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("emplacements donateur is good");
				verify= true;
			}else {
				System.out.println("emplacements donateur failed");
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

	public void DeleteDonationNonValider(int idDonateur) {


		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql= "DELETE from Donation WHERE idDonateur="+idDonateur+" AND etat='non-valider';";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("donation non-valider deleted is good");
			}else {
				System.out.println("donation non-valider delete failed");
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

	public void BloquerDonateur(int idDonateur) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		
		String sql="UPDATE Donateur SET nbrAvertissement=3 WHERE idDonateur="+idDonateur+";";
		
		try {
			conn = DriverManager.getConnection(url, DBuser, DBpass);
			stat = conn.prepareStatement(sql);
			int status = stat.executeUpdate();
			if(status !=0) {
				System.out.println("donateur blocked is good");
			}else {
				System.out.println("donateur blocked failed");
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
