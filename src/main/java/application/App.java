package application;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.DBConnection;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

	System.out.println("Welcome to the new Maven Project!");
	System.setProperty("log4j.configurationFile", "src/main/resources/log4j.xml");
	Logger logMain = LogManager.getLogger("main");
	/*
	 * UtenteService bobService = new UtenteService(); String bobPswd =
	 * "Hey!9Bob99"; int bobServiceResult =
	 * bobService.registrazioneUtente("Bob@submarine.com", bobPswd, 2, "Bob",
	 * "Narvhal", 9, null, null, null, null, null, null, null);
	 */
	/*
	 * String bobPswd = "password"; String hashedPswd =
	 * Hasher.hashSaltPassword(bobPswd); logMain.info("Hashed password: " +
	 * hashedPswd + " for user: Bob");
	 */

	Connection conn = DBConnection.createConnection();
	logMain.info("coonection stabilita" + conn);
	conn.close();
    }

}