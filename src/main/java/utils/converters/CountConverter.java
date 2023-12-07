package utils.converters;

import model.Bean.UtenteBean;
import model.Dto.EvalCountDto;

public class CountConverter {

 
    public static EvalCountDto toDto(UtenteBean utenteBean) {

        EvalCountDto countDto = new EvalCountDto();
        
        countDto.setValutatoreId(utenteBean.getValutatoreId());
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