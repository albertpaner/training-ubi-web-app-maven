package services;

import exceptions.LoginPasswordFailedException;
import exceptions.LoginUserNotFoundException;
import exceptions.RegistrationFailedException;
import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import model.Dto.EvalCountDto;
import utils.EncryptJwt;
import utils.Hasher;
import utils.converters.CountConverter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

public class UtenteService {

    // static Logger logUser = LogManager.getLogger("user");
    private UtenteDao utenteDao;

    public UtenteService() {
    }

    public UtenteService(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    public boolean registrazioneUtente(String email, String password, int ruoloId, String nome, String cognome,
                                       int valutatoreId, Date dataNascita) throws SQLException, RegistrationFailedException {
        boolean registrationSuccess = false;

        if (!utenteDao.findByEmail(email).isUserBeanEmpty()) {
            throw new RegistrationFailedException("User already exists: " + email);
        }

        String hashedPassword = Hasher.hashPassword(password);
        // logUser.debug("Hashed password: " + hashedPassword + " for user: " + email);

        int createdUser = utenteDao.create(email, hashedPassword, ruoloId, nome, cognome, valutatoreId, dataNascita);
        registrationSuccess = createdUser > 0;
        // logUser.debug("Created user: " + nome + " " + cognome + " with email: " +
        // email);

        return registrationSuccess;
    }

    public String loginUtente(String email, String password)
            throws ClassNotFoundException, SQLException, LoginUserNotFoundException, LoginPasswordFailedException {

        // UtenteDao utenteDao = new UtenteDao();

        if (utenteDao.findByEmail(email).isUserBeanEmpty()) {
            throw new LoginUserNotFoundException("User not found: " + email);
        }

        UtenteBean utenteFound = utenteDao.findByEmail(email);

        String hashedPassword = Hasher.hashPassword(password);

        if (utenteFound.getPassword().equals(hashedPassword)) {
            String jwt = EncryptJwt.issueToken(utenteFound.getUtenteId());
            // logUser.debug("Issued token: " + jwt + " for user: " + email);
            int lastAccessDate = utenteDao.updateLastAccess(utenteFound.getUtenteId());
            // logUser.debug("Logged in user: " + nome + " " + cognome + " with email: " )
            return jwt;
        } else {
            throw new LoginPasswordFailedException("Wrong password for user: " + email);
        }

    }

    public HashMap<String, List<EvalCountDto>> getEvaluatorsOccupiedFree(int soglia)
            throws SQLException, ClassNotFoundException {
        // UtenteDao utenteDao = new UtenteDao();

        HashMap<String, List<UtenteBean>> usersFetched = utenteDao.splitEvaluators(soglia);

        List<UtenteBean> valutatoriOccupati = usersFetched.get("valutatori_occupati");
        List<UtenteBean> valutatoriDisponibili = usersFetched.get("valutatori_disponibili");

        List<EvalCountDto> valutatoriOccupatiDto = new ArrayList<>();
        for (UtenteBean utente : valutatoriOccupati) {
            EvalCountDto countDto = CountConverter.toDto(utente);
            countDto.setCount(utenteDao.countValuedByEvaluator(utente.getValutatoreId()));
            valutatoriOccupatiDto.add(countDto);
        }

        List<EvalCountDto> valutatoriDisponibiliDto = new ArrayList<>();
        for (UtenteBean utente : valutatoriDisponibili) {
            EvalCountDto countDto = CountConverter.toDto(utente);
            countDto.setCount(utenteDao.countValuedByEvaluator(utente.getValutatoreId()));
            valutatoriDisponibiliDto.add(countDto);
        }

        HashMap<String, List<EvalCountDto>> usersToShow = new HashMap<>();
        usersToShow.put("valutatori_occupati", valutatoriOccupatiDto);
        usersToShow.put("valutatori_disponibili", valutatoriDisponibiliDto);

        return usersToShow;
    }

    public void rearrengeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) {

        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("valutatori_occupati");
        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("valutatori_disponibili");

        Collections.sort(utentiPiuValutati,
                new Comparator<UtenteBean>() {
                    @Override
                    public int compare(UtenteBean u1, UtenteBean u2) {
                        return u1.getDataNascita().compareTo(u2.getDataNascita());
                    }
                }
    /*
        if (utentiPiuValutati.size() > soglia) {
            for (int i = utentiPiuValutati.size() - 1; i >= soglia; i--) {
                UtenteBean utente = utentiPiuValutati.remove(i);
                utentiMenoValutati.add(0, utente);
            }
        }
    */

    }

}
