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
import java.util.Optional;

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

        if (findByEmail(email).isPresent()) {
            throw new RegistrationFailedException("User already exists with the email: " + email);
        }

        String hashedPassword = Hasher.hashPassword(password);
        // logUser.debug("Hashed password: " + hashedPassword + " for user: " + email);

        int createdUser = utenteDao.create(email, hashedPassword, ruoloId, nome, cognome, valutatoreId, dataNascita);
        registrationSuccess = createdUser > 0;
        // logUser.debug("Created user: " + nome + " " + cognome + " with email: " +
        // email);

        return registrationSuccess;
    }

    public Optional<UtenteBean> findByEmail(String email) throws SQLException {
        List<UtenteBean> allUsers = utenteDao.findAll();
        return allUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public String loginUtente(String email, String password)
            throws ClassNotFoundException, SQLException, LoginUserNotFoundException, LoginPasswordFailedException {

        // UtenteDao utenteDao = new UtenteDao();
        Optional<UtenteBean> maybeUtente = findByEmail(email);

        if (!maybeUtente.isPresent()) {
            throw new LoginUserNotFoundException("User not found with email: " + email);
        }

        UtenteBean utenteFound = maybeUtente.get();

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
        HashMap<UtenteBean, List<UtenteBean>> lordsAndPeasants = new HashMap<>();

        List<UtenteBean> allUsers = utenteDao.findAll();

        List<UtenteBean> allEvaluators = allUsers.stream()
                .filter(user -> user.getRuoloId() == 1)
                .filter(user -> !user.isFlgDel())
                .toList();

        for (UtenteBean evaluator : allEvaluators) {
            List<UtenteBean> allValuedUsers = utenteDao.findValuedByEvaluator(evaluator.getUtenteId());
            lordsAndPeasants.put(evaluator, allValuedUsers);
        }




        HashMap<String, List<EvalCountDto>> usersToShow = new HashMap<>();
        usersToShow.put("valutatori_occupati", valutatoriOccupatiDto);
        usersToShow.put("valutatori_disponibili", valutatoriDisponibiliDto);

        return usersToShow;
    }

    public void rearrengeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException {

        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("valutatori_occupati");
        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("valutatori_disponibili");
        int evatorId = 0;

        for (EvalCountDto valutatore : valutatoriOccupatiDto) {
            evatorId = valutatore.getValutatoreId();
            List<UtenteBean> utentiValutatiDa = utenteDao.findValuedByEvaluator(evatorId);
            sortingUtenti(utentiValutatiDa);
            if (utentiValutatiDa.size() > soglia) {
                for (int i = utentiValutatiDa.size() - 1; i >= soglia; i--) {
                    UtenteBean utenteSwap = utentiValutatiDa.remove(i);
                    utenteDao.updateValutatoreId(utenteSwap.getUtenteId(), valutatoriDisponibiliDto.get(0).getValutatoreId());
                }
            }
        }

    }

    public void sortingUtenti(List<UtenteBean> utentiToSort) {
        Collections.sort(utentiToSort,
                new Comparator<UtenteBean>() {
                    @Override
                    public int compare(UtenteBean u1, UtenteBean u2) {
                        return u1.getDataNascita().compareTo(u2.getDataNascita());
                    }
                }
        );
    }

}
