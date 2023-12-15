package services.user;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.UtenteDto;
import services.UtenteService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class UtenteSortingService extends UtenteService {

    public UtenteSortingService(UtenteDao utenteDao) {
        super(utenteDao);
    }

    public List<UtenteDto> sortByNameLexicographically() throws SQLException, ClassNotFoundException {

        List<UtenteBean> allUsers = utenteDao.findAll();

        /*
        allUsers.sort(UtenteBean::compareByNameThenSurname);

        List<UtenteDto> usersSorted = allUsers.stream()
                .filter(user -> !user.getFlgDel())
                .map(user -> new UtenteDto(user.getUtenteId(), user.getEmail(), user.getRuoloId(), user.getNome(), user.getCognome(), user.getValutatoreId()))
                .toList(); */

        List<UtenteDto> usersSorted = allUsers.stream()
                .filter(user -> !user.getFlgDel())
                .map(user -> new UtenteDto(user.getUtenteId(), user.getEmail(),  user.getNome(), user.getCognome()))
                .sorted(Comparator.comparing(UtenteDto::getFirstName)
                        .thenComparing(UtenteDto::getLastName))
                .toList();

        return usersSorted;
    }

}
