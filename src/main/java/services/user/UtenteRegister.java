package services.user;

import exceptions.RegistrationFailedException;
import model.Dao.UtenteDao;
import services.UtenteService;
import utils.Hasher;

import java.sql.Date;
import java.sql.SQLException;

public class UtenteRegister extends UtenteService {

    public UtenteRegister(UtenteDao utenteDao) {
        super(utenteDao);
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

}
