package services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import model.Dto.UtenteDto;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import model.Dto.UtenteDto;
import utils.EncryptJwt;
import utils.Hasher;

public class UtenteService {

    static Logger logUser = LogManager.getLogger("user");
	private UtenteDao utenteDao;

	public UtenteService(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    public int registrazioneUtente(String email, String password, int ruoloId, String nome, String cognome,
	    int responsabileId, String societaOp, String mansione, String ambito, String jobFam, String subFam,
	    String stdJob, String jobLevel) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

	// UtenteDao utenteDao = new UtenteDao();

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

	// UtenteDao utenteDao = new UtenteDao();

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

public HashMap<String, List<CountDto>> findUsersToShow() throws ClassNotFoundException, SQLException {
	// UtenteDao utenteDao = new UtenteDao();
	
	HashMap<String, List<CountDto>> usersToShow = new HashMap<>();
	
	List<UtenteBean> utentiPiuValutati = utenteDao.findVal5();
	List<UtenteBean> utentiMenoValutati = utenteDao.findValDisp();
	String valutatoriOccupati = "valutatori_occupati";
	String valutatoriDisponibili = "valutatori_disponibili";
	
	for(utente : )
	
	usersToShow.put(valutatoriOccupati, null);
	usersToShow.put(valutatoriDisponibili, null);
	

	return usersToShow;
}


	public void rearrengeValutatori() {
		UtenteDao utenteDao = new UtenteDao();
		// List<UtenteBean> utentiPiuValutati = utenteDao.findAllByRole(1);
		// List<UtenteBean> utentiMenoValutati = utenteDao.findAllByRole(2);

		Collections.sort(utentiPiuValutati, new Comparator<UtenteBean>() {
			@Override
			public int compare(UtenteBean u1, UtenteBean u2) {
				return Integer.compare(u1.getAge(), u2.getAge());
			}
		});

		if (utentiPiuValutati.size() > 5) {
			for (int i = utentiPiuValutati.size() - 1; i >= 5; i--) {
				UtenteBean utente = utentiPiuValutati.remove(i);
				utentiMenoValutati.add(0, utente);
			}
		}


	}


}