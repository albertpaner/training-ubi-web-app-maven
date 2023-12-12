package utils.converters;

import model.bean.UtenteBean;
import model.dto.EvalCountDto;

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
    
        /*public static UtenteBean toBean(CountDto CountDto) {
        UtenteBean utenteBean = new UtenteBean();
        utenteBean.setUtenteId(CountDto.getUserId());
        utenteBean.setEmail(CountDto.getUserEmail());
        utenteBean.setPassword(CountDto.getUserPassword());
        utenteBean.setRuoloId(CountDto.getRoleId());
        utenteBean.setNome(CountDto.getFirstName());
        utenteBean.setCognome(CountDto.getLastName());
        utenteBean.setResposabileId(CountDto.getUserManagerId());
        utenteBean.setSocietaOp(CountDto.getCompanyOp());
        utenteBean.setMansione(CountDto.getUserJob());
        utenteBean.setAmbito(CountDto.getUserScope());
        utenteBean.setJobFam(CountDto.getUserJobFamily());
        utenteBean.setSubFam(CountDto.getUserSubFamily());
        utenteBean.setStdJob(CountDto.getUserStandardJob());
        utenteBean.setJobLevel(CountDto.getUserJobLevel());
        return utenteBean;
        }*/


}