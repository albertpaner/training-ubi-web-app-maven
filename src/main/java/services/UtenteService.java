package services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import model.Dto.CountDto;
import model.Dto.UtenteDto;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import model.Dto.UtenteDto;
import utils.EncryptJwt;
import utils.Hasher;
import utils.converters.CountConverter;

public class UtenteService {

	// static Logger logUser = LogManager.getLogger("user");
	private UtenteDao utenteDao;

	public UtenteService() {
	}

	public UtenteService(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}

	public int registrazioneUtente(String email, String password, int ruoloId, String nome, String cognome,
			int responsabileId, String societaOp, String mansione, String ambito, String jobFam, String subFam,
			String stdJob, String jobLevel) throws SQLException {

		if (!utenteDao.findByEmail(email).isUserBeanEmpty()) {
			// logUser.error("User already exists: " + email);
			return -1;
		}

		String hashedPassword = Hasher.hashPassword(password);
		// logUser.debug("Hashed password: " + hashedPassword + " for user: " + email);

		int createdUser = utenteDao.create(email, hashedPassword, ruoloId, nome, cognome, responsabileId, societaOp,
				mansione, ambito, jobFam, subFam, stdJob, jobLevel);

		// logUser.debug("Created user: " + nome + " " + cognome + " with email: " +
		// email);
		return createdUser;
	}

	public String loginUtente(String email, String password) throws ClassNotFoundException, SQLException {

		// UtenteDao utenteDao = new UtenteDao();

		if (utenteDao.findByEmail(email).isUserBeanEmpty()) {
			// logUser.error("User not found: " + email);
			return "try again";
		}

		UtenteBean utenteFound = utenteDao.findByEmail(email);

		String hashedPassword = Hasher.hashPassword(password);

		if (utenteFound.getPassword().equals(hashedPassword)) {
			String jwt = EncryptJwt.issueToken(utenteFound.getUtenteId());
			// logUser.debug("Issued token: " + jwt + " for user: " + email);
			return jwt;
		} else {
			// logUser.error("Wrong password for user: " + email);
			return "try again";
		}

	}

	public HashMap<String, List<CountDto>> getEvaluators() throws SQLException, ClassNotFoundException {
		// UtenteDao utenteDao = new UtenteDao();

		HashMap<String, List<UtenteBean>> usersFetched = utenteDao.splitEvaluators(5);
		

		List<UtenteBean> valutatoriOccupati = usersFetched.get("valutatori_occupati");
		List<UtenteBean> valutatoriDisponibili = usersFetched.get("valutatori_disponibili");

		List<CountDto> valutatoriOccupatiDto = new ArrayList<>();
		for (UtenteBean utente : valutatoriOccupati) {
			CountDto countDto = CountConverter.toDto(utente);
			countDto.setCount(utenteDao.countValuedByEvaluator(utente.getValutatoreId()));
			valutatoriOccupatiDto.add(countDto);
		}

		List<CountDto> valutatoriDisponibiliDto = new ArrayList<>();
		for (UtenteBean utente : valutatoriDisponibili) {
			CountDto countDto = CountConverter.toDto(utente);
			countDto.setCount(utenteDao.countValuedByEvaluator(utente.getValutatoreId()));
			valutatoriDisponibiliDto.add(countDto);
		}

		HashMap<String, List<CountDto>> usersToShow = new HashMap<>();
		usersToShow.put("valutatori_occupati", valutatoriOccupatiDto);
		usersToShow.put("valutatori_disponibili", valutatoriDisponibiliDto);

		return usersToShow;
	}

	/*
	 * public void rearrengeValutatori() {
	 * UtenteDao utenteDao = new UtenteDao();
	 * // List<UtenteBean> utentiPiuValutati = utenteDao.findAllByRole(1);
	 * // List<UtenteBean> utentiMenoValutati = utenteDao.findAllByRole(2);
	 * 
	 * Collections.sort(utentiPiuValutati, new Comparator<UtenteBean>() {
	 * 
	 * @Override
	 * public int compare(UtenteBean u1, UtenteBean u2) {
	 * return Integer.compare(u1.getAge(), u2.getAge());
	 * }
	 * });
	 * 
	 * if (utentiPiuValutati.size() > 5) {
	 * for (int i = utentiPiuValutati.size() - 1; i >= 5; i--) {
	 * UtenteBean utente = utentiPiuValutati.remove(i);
	 * utentiMenoValutati.add(0, utente);
	 * }
	 * }
	 */

}
