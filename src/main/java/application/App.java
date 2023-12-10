package application;

import model.dao.UtenteDao;
import services.user.UtenteRegister;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

		UtenteRegister utenteRegister = new UtenteRegister(new UtenteDao());
		System.out.println("Opened a Register Service and this is the database connected: " + utenteRegister.getUtenteDao().getConn());

		System.out.println("These are all the users in the database: " + utenteRegister.getUtenteDao().findAll());
    }

}