package services;

import model.bean.UtenteBean;

import java.sql.SQLException;
import java.util.Optional;

public interface UserEmailFinder {
    Optional<UtenteBean> findByEmail(String email) throws SQLException;
}
