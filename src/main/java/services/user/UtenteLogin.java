package services.user;

import exceptions.LoginPasswordFailedException;
import exceptions.LoginUserNotFoundException;
import model.Bean.UtenteBean;
import model.Dao.UtenteDao;
import services.UtenteService;
import utils.EncryptJwt;
import utils.Hasher;

import java.sql.SQLException;
import java.util.Optional;

public class UtenteLogin extends UtenteService {

    public UtenteLogin(UtenteDao utenteDao) {
        super(utenteDao);
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
}
