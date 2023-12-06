package utils.converters;

import model.Bean.UtenteBean;
import model.Dto.CountDto;
import model.Dto.UtenteDto;

public class CountConverter {

 
    public static CountDto toDto(UtenteBean utenteBean) {

        CountDto countDto = new CountDto();
        countDto.setValutatoreId(utenteBean.getValutatoreId());
        countDto.setUserEmail(utenteBean.getEmail());
        countDto.setRoleId(utenteBean.getRuoloId());
        countDto.setNome(utenteBean.getNome());
        countDto.setCognome(utenteBean.getCognome());
        
        return CountDto;
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