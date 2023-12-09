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
import java.util.*;

public class UtenteService {

    // static Logger logUser = LogManager.getLogger("user");
    private UtenteDao utenteDao;

    public UtenteService() {
    }

    public UtenteService(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    /**
     * This method registers a new user in the database.
     * It checks if the user already exists in the database.
     * If the user does not exist, it creates a new user in the database.
     *
     * @param email        The email of the user.
     * @param password     The password of the user.
     * @param ruoloId      The ID of the user's role.
     * @param nome         The name of the user.
     * @param cognome      The surname of the user.
     * @param valutatoreId The ID of the user's evaluator.
     * @param dataNascita  The date of birth of the user.
     * @return The ID of the created user.
     * @throws SQLException                If a database access error occurs.
     * @throws RegistrationFailedException If the user already exists in the database.
     */
    public int registrazioneUtente(String email, String password, int ruoloId, String nome, String cognome,
                                   int valutatoreId, Date dataNascita) throws SQLException, RegistrationFailedException {

        if (findByEmail(email).isPresent()) {
            throw new RegistrationFailedException("User already exists with the email: " + email);
        }

        String hashedPassword = Hasher.hashPassword(password);
        // logUser.debug("Hashed password: " + hashedPassword + " for user: " + email);

        int createdUser = utenteDao.create(email, hashedPassword, ruoloId, nome, cognome, valutatoreId, dataNascita);

        // logUser.debug("Created user: " + nome + " " + cognome + " with email: " +
        // email);

        return createdUser;
    }

    /**
     * This method returns an optional user from the database based on the user's email.
     * If the user exists, the method returns an Optional object containing the user.
     * If the user does not exist, the method returns an empty Optional object.
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public Optional<UtenteBean> findByEmail(String email) throws SQLException {
        List<UtenteBean> allUsers = utenteDao.findAll();
        return allUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> !user.isFlgDel())
                .findFirst();
    }

    /**
     * This is a method for logging in a user.
     * It checks if the user exists in the database and if the password is correct.
     * If the user exists and the password is correct, it issues a JWT token for the user.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return A JWT token for the user.
     */
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

    /**
     * This method returns a list of users who are assigned to a given evaluator.
     *
     * @param evatorId The ID of the evaluator.
     * @return A list of users who are assigned to the given evaluator.
     * @throws SQLException If a database access error occurs.
     */
    private List<UtenteBean> findValuedByEvaluator(int evatorId) throws SQLException {
        List<UtenteBean> allUsers = utenteDao.findAll();
        return allUsers.stream()
                .filter(user -> user.getValutatoreId() == evatorId)
                .filter(user -> !user.isFlgDel())
                .toList();
    }

    /**
     * This method fetches all evaluators and their corresponding users from the database.
     * It returns a HashMap where the keys are evaluators and the values are lists of users valued by the evaluator.
     *
     * @return A HashMap where the keys are evaluators and the values are lists of users.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC Driver class is not found.
     */
    private HashMap<UtenteBean, List<UtenteBean>> fetchEvaluatorsAndValued() throws SQLException, ClassNotFoundException {
        // UtenteDao utenteDao = new UtenteDao();
        HashMap<UtenteBean, List<UtenteBean>> lordsAndPeasants = new HashMap<>();

        List<UtenteBean> allUsers = utenteDao.findAll();

        List<UtenteBean> allEvaluators = allUsers.stream()
                .filter(user -> user.getRuoloId() == 1)
                .filter(user -> !user.isFlgDel())
                .toList();

        for (UtenteBean lord : allEvaluators) {
            List<UtenteBean> peasantsUnderLord = findValuedByEvaluator(lord.getUtenteId());
            lordsAndPeasants.put(lord, peasantsUnderLord);
        }

        return lordsAndPeasants;
    }

    /**
     * This method categorizes evaluators into two groups: those who have more users than a given threshold (occupied) and those who have less (available).
     * It returns a HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * Each EvalCountDto object represents an evaluator and contains the evaluator's ID, name, surname, and the count of users assigned to them.
     *
     * @param soglia The threshold value for the number of users.
     * @return A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC Driver class is not found.
     */
    public HashMap<String, List<EvalCountDto>> getEvaluatorsOccupiedFree(int soglia)
            throws SQLException, ClassNotFoundException {

        HashMap<UtenteBean, List<UtenteBean>> lordsAndPeasants = fetchEvaluatorsAndValued();

        List<EvalCountDto> valutatoriOccupatiDto = new ArrayList<>();
        List<EvalCountDto> valutatoriDisponibiliDto = new ArrayList<>();

        for (UtenteBean lord : lordsAndPeasants.keySet()) {
            EvalCountDto evaluatorDto = CountConverter.beanToDto(lord);
            int howManyPeasants = lordsAndPeasants.get(lord).size();
            evaluatorDto.setCount(howManyPeasants);

            if (howManyPeasants > soglia) {
                valutatoriOccupatiDto.add(evaluatorDto);
            } else {
                valutatoriDisponibiliDto.add(evaluatorDto);
            }
        }

        HashMap<String, List<EvalCountDto>> usersToShow = new HashMap<>();
        usersToShow.put("valutatori_occupati", valutatoriOccupatiDto);
        usersToShow.put("valutatori_disponibili", valutatoriDisponibiliDto);

        return usersToShow;
    }

    /**
     * This method reassigns users to evaluators based on a threshold value.
     * If an evaluator has more users than the threshold, the method reassigns the excess users to evaluators who have less than the threshold number of users.
     * The reassignment is done based on the user's date of birth, with the youngest users being reassigned first.
     *
     * @param usersToShow A HashMap of evaluators and their corresponding users.
     * @param soglia      The threshold value for the number of users an evaluator can have.
     * @throws SQLException If a database access error occurs.
     */
    public void rearrengeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException {

        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("valutatori_occupati");
        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("valutatori_disponibili");
        int evatorId = 0;

        for (EvalCountDto valutatore : valutatoriOccupatiDto) {
            evatorId = valutatore.getValutatoreId();
            List<UtenteBean> utentiValutatiDa = findValuedByEvaluator(evatorId);
            sortingUtentiEta(utentiValutatiDa);
            if (utentiValutatiDa.size() > soglia) {
                for (int i = utentiValutatiDa.size() - 1; i >= soglia; i--) {
                    UtenteBean utenteSwap = utentiValutatiDa.remove(i);
                    utenteDao.updateValutatoreId(utenteSwap.getUtenteId(), valutatoriDisponibiliDto.get(0).getValutatoreId());
                }
            }
        }

    }

    /**
     * This method sorts a list of UtenteBean objects based on their dataNascita (date of birth) attribute.
     * The sorting is done in ascending order, meaning the oldest user will be first.
     *
     * @param utentiToSort The list of UtenteBean objects to be sorted.
     */
    public void sortingUtentiEta(List<UtenteBean> utentiToSort) {
        //utentiToSort.sort((u1, u2) -> u1.getDataNascita().compareTo(u2.getDataNascita()));
        utentiToSort.sort(Comparator.comparing(UtenteBean::getDataNascita));
    }

}
