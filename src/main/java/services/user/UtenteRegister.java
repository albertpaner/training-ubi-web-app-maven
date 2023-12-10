package services.user;

import exceptions.RegistrationFailedException;
import model.dao.UtenteDao;
import services.UtenteService;
import utils.Hasher;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UtenteRegister extends UtenteService {

    public UtenteRegister(UtenteDao utenteDao) throws SQLException, ClassNotFoundException {
        super();
    }

    /**
     * This method registers a new user in the database.
     * It checks if the user already exists in the database.
     * If the user does not exist, it creates a new user in the database.
     *
     * @param paramsUser A list of parameters of the user to be created.
     * @return The ID of the created user.
     * @throws SQLException                If a database access error occurs.
     * @throws RegistrationFailedException If the user already exists in the database.
     */
    public int registrazioneUtente(List<Object> paramsUser) throws SQLException, RegistrationFailedException, ClassNotFoundException {
        String email = (String) paramsUser.get(0);
        String password = (String) paramsUser.get(1);
        int ruoloId = (Integer) paramsUser.get(2);
        String nome = (String) paramsUser.get(3);
        String cognome = (String) paramsUser.get(4);
        int valutatoreId = (Integer) paramsUser.get(5);
        Date dataNascita = (Date) paramsUser.get(6);

        if (findByEmail(email).isPresent()) {
            throw new RegistrationFailedException("User already exists with the email: " + email);
        }

        String hashedPassword = Hasher.hashPassword(password);
        List<Object> paramsUserToCreate = (List<Object>) paramsUser.set(1, hashedPassword);
        int createdUser = utenteDao.create(paramsUserToCreate);

        return createdUser;
    }

}
