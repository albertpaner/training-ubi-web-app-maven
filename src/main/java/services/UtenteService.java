package services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import utils.EncryptJwt;
import utils.Hasher;

public class UtenteService {

    static Logger logUser = LogManager.getLogger("user");

    public int registrazioneUtente(String email, String password, int ruoloId, String nome, String cognome,
	    int responsabileId, String societaOp, String mansione, String ambito, String jobFam, String subFam,
	    String stdJob, String jobLevel) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

	UtenteDao utenteDao = new UtenteDao();

	if (!utenteDao.findByEmail(email).isUserBeanEmpty()) {
	    logUser.error("User already exists: " + email);
	    return -1;
	}

	String hashedPassword = Hasher.hashPassword(password);
	// logUser.debug("Hashed password: " + hashedPassword + " for user: " + email);

	int createdUser = utenteDao.create(email, hashedPassword, ruoloId, nome, cognome, responsabileId, societaOp,
		mansione, ambito, jobFam, subFam, stdJob, jobLevel);

	logUser.debug("Created user: " + nome + " " + cognome + " with email: " + email);
	return createdUser;
    }

    public String loginUtente(String email, String password) throws ClassNotFoundException, SQLException {

	UtenteDao utenteDao = new UtenteDao();

	if (utenteDao.findByEmail(email).isUserBeanEmpty()) {
	    logUser.error("User not found: " + email);
	    return "try again";
	}

	UtenteBean utenteFound = utenteDao.findByEmail(email);

	String hashedPassword = Hasher.hashPassword(password);

	if (utenteFound.getPassword().equals(hashedPassword)) {
	    String jwt = EncryptJwt.issueToken(utenteFound.getUtenteId());
	    logUser.debug("Issued token: " + jwt + " for user: " + email);
	    return jwt;
	} else {
	    logUser.error("Wrong password for user: " + email);
	    return "try again";
	}

    }





}