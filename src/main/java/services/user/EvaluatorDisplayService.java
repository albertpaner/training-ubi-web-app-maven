package services.user;

import model.dao.UtenteDao;
import model.dto.UtenteDto;
import services.UtenteService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EvaluatorDisplayService extends UtenteService {

    public EvaluatorDisplayService(UtenteDao utenteDao) {
        super(utenteDao);
    }

    public List<UtenteDto> getValutatori() throws SQLException, ClassNotFoundException {
        return utenteDao.findAll().stream()
                .filter(user -> user.getRuoloId() == 1)
                .map(user -> new UtenteDto(user.getUtenteId(), user.getEmail(), user.getNome(), user.getCognome()))
                .collect(Collectors.toList());
    }

    public void removeFromEvaluator(int evaluatorID, String mansione) throws SQLException, ClassNotFoundException {

        utenteDao.findAll().stream()
                .filter(user -> user.getRuoloId() == 2)
                .filter(user -> user.getValutatoreId() == evaluatorID)
                .filter(user -> user.getMansione().equals(mansione))
                .forEach(user -> {
                    try {
                        utenteDao.updateMod(
                                user.getEmail(),
                                user.getPassword(),
                                user.getRuoloId(),
                                user.getNome(),
                                user.getCognome(),
                                999,
                                true,
                                user.getMansione(),
                                user.getUtenteId()
                        );
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
    }

    public List<UtenteDto> getSuspendedUsers() throws SQLException, ClassNotFoundException {
        return utenteDao.findAll().stream()
                .filter(user -> user.getValutatoreId()==999)
                .map(user -> new UtenteDto(user.getUtenteId(), user.getEmail(), user.getNome(), user.getCognome()))
                .collect(Collectors.toList());
    }
}
