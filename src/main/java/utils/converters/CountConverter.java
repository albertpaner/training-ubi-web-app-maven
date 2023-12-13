package utils.converters;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import utils.DBConnection;

import java.sql.SQLException;

public class CountConverter {

    /**
     * This method converts a UtenteBean object into a CountDto object.
     *
     * @param utenteBean the UtenteBean object to be converted
     *                   (valutatoreId, nome, cognome, email)
     *
     * @return the EvalCountDto object
     * */
    public static EvalCountDto beanToDto(UtenteBean utenteBean) {

        EvalCountDto countDto = new EvalCountDto();
        
        countDto.setUtenteId(utenteBean.getUtenteId());
        countDto.setEmail(utenteBean.getEmail());
        countDto.setNome(utenteBean.getNome());
        countDto.setCognome(utenteBean.getCognome());
        
        return countDto;
        }

    public static UtenteBean dtoToBean(EvalCountDto countDto) throws SQLException, ClassNotFoundException {

        UtenteDao utenteDao = new UtenteDao(DBConnection.createConnection());
        UtenteBean utenteBean = utenteDao.findById(countDto.getUtenteId());

        return utenteBean;
    }

}