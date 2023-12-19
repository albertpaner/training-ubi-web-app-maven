package services.user;

import exceptions.LoginPasswordFailedException;
import exceptions.LoginUserNotFoundException;
import model.bean.UtenteBean;
import model.dao.UtenteDao;
import services.UtenteService;
import utils.EncryptJwt;
import utils.Hasher;

import java.sql.SQLException;
import java.util.Optional;

public class UtenteLoginService extends UtenteService {

    public UtenteLoginService(UtenteDao utenteDao) throws SQLException, ClassNotFoundException {
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

        Optional<UtenteBean> maybeUtente = findByEmail(email);

        if (!maybeUtente.isPresent()) {
            throw new LoginUserNotFoundException("User not found with email: " + email);
        }

        UtenteBean utenteFound = maybeUtente.get();

        String hashedPassword = Hasher.hashPassword(password);

        if (utenteFound.getPassword().equals(hashedPassword)) {
            String jwt = EncryptJwt.issueToken(utenteFound.getUtenteId());
            // logUser.debug("Issued token: " + jwt + " for user: " + email);
            // logUser.debug("Logged in user: " + nome + " " + cognome + " with email: " )
            return jwt;
        } else {
            throw new LoginPasswordFailedException("Wrong password for user: " + email);
        }

    }
}
