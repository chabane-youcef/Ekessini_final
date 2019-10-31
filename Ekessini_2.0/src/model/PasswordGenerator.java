package model;

public class PasswordGenerator {
	
	public static String MakePassword() {
		String password="";
		int length = 10;
		
		for(int i=0; i<length - 2; i++) {
			password = password + randomCharecter("abcdefghijklmnopqrstuvwyz");
			
		}
		
		String randomDigite = randomCharecter("0123456789");
		password = insertAtRandom(password,randomDigite);
		return password;
	}

	

	private static String randomCharecter(String Charecters) {
		int n = Charecters.length();
		int r = (int) (n * Math.random());
		return Charecters.substring(r , r+1);
	}
	
	private static String insertAtRandom(String str, String toInsert) {
		int n = str.length();
		int r = (int) ((n+1) * Math.random());
		return str.substring(0, r) + toInsert + str.substring(r);
	}
}
