package services;

import model.Bean.UtenteBean;
import model.Dao.UtenteDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public abstract class UtenteService implements UserEmailFinder{

    // static Logger logUser = LogManager.getLogger("user");
    protected UtenteDao utenteDao;

    public UtenteService() {
    }

    public UtenteService(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
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


}
