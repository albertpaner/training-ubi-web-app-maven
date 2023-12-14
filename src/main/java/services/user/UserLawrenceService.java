package services.user;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import utils.converters.CountConverter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserLawrenceService {


    public UserLawrenceService() throws SQLException, ClassNotFoundException {
    }

    private UtenteDao utenteDao = new UtenteDao();
    private CountConverter countConverter = new CountConverter();

    public List<EvalCountDto> showUserBySO(String societàOperativa) throws SQLException, ClassNotFoundException {

        List<UtenteBean> userList = utenteDao.findAll();
        List<EvalCountDto> userDtoList = new ArrayList<>();


        //System.out.println("before: " + userList.size());
        for (int i = userList.size() - 1; i >= 0; i--) {
            // se è nulla o divesa dalla società operativa rimuovi escludi
            if ((userList.get(i).getSocietàOperativa() == null) || (!userList.get(i).getSocietàOperativa().equals(societàOperativa))) {

                if (userList.get(i).getRuoloId() == 1 && true) { //! aggiungere mansione, mancano i medoti
                    //userList.remove(i);
                    utenteDao.updateInSopseso(true, userList.get(i).getRuoloId());

                }
                //userList.remove(i);
            } else {
                userDtoList.add(CountConverter.beanToDto(userList.get(i)));
            }

        }

        //System.out.println("after: " + userList.size());
        return userDtoList;
    }

}
