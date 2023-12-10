package services;

import model.bean.UtenteBean;
import model.dao.UtenteDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public abstract class UtenteService {

    // static Logger logUser = LogManager.getLogger("user");
    protected UtenteDao utenteDao;

    public UtenteService() {
    }

    public UtenteService(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    /**
     * This method searches for a user in the database based on their email.
     * It returns an Optional<UtenteBean> object that contains the user if found, or an empty Optional if the user is not found.
     * The method also filters out users who have been marked for deletion (isFlgDel() returns true).
     *
     * @param email The email of the user to be searched.
     * @return An Optional<UtenteBean> object that contains the user if found, or an empty Optional if the user is not found.
     * @throws SQLException If a database access error occurs.
     */
    public Optional<UtenteBean> findByEmail(String email) throws SQLException {
        List<UtenteBean> allUsers = utenteDao.findAll();
        return allUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> !user.getFlgDel())
                .findFirst();
    }


}
