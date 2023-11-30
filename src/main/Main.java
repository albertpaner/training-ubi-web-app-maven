package application;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import services.UtenteService;
import utils.Hasher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

	System.setProperty("log4j.configurationFile", "src/properties/log4j.xml");
	Logger logMain = LogManager.getLogger("main");
/* 
	UtenteService bobService = new UtenteService();
	String bobPswd = "Hey!9Bob99";
	int bobServiceResult = bobService.registrazioneUtente("Bob@submarine.com", bobPswd, 2, "Bob", "Narvhal", 9,
		null, null, null, null, null, null, null);
*/

	String alicePswd = "ciao";
	String hashedPswd = Hasher.hashSaltPassword(alicePswd);
	logMain.info("Hashed password: " + hashedPswd + " for user: Alice");

    }

}