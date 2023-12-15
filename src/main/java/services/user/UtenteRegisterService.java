package services.user;

import exceptions.RegistrationFailedException;
import model.dao.UtenteDao;
import services.UtenteService;
import utils.Hasher;

import java.sql.Date;
import java.sql.SQLException;

public class UtenteRegisterService extends UtenteService {

    public UtenteRegisterService(UtenteDao utenteDao) throws SQLException, ClassNotFoundException {
        super(utenteDao);
    }


    public int registrazioneUtente(String email, String password, int ruoloId, String nome, String cognome, int valutatoreId, String mansione, String jobLevel, String societàOperativa, Date dataNascita) throws SQLException, RegistrationFailedException, ClassNotFoundException {

        if (findByEmail(email).isPresent()) {
            throw new RegistrationFailedException("User already exists with the email: " + email);
        }



        String hashedPassword = Hasher.hashPassword(password);

        int createdUser = utenteDao.create(email, hashedPassword, ruoloId, nome, cognome, valutatoreId, mansione, jobLevel, societàOperativa, dataNascita);

        return createdUser;
    }

}
