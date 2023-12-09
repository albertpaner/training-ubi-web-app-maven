package utils.converters;

import model.bean.UtenteBean;
import model.dto.UtenteDto;

public class UtenteConverter {

	public static UtenteDto toDto(UtenteBean utenteBean) {
		UtenteDto utenteDto = new UtenteDto();
		utenteDto.setUserId(utenteBean.getUtenteId());
		utenteDto.setUserEmail(utenteBean.getEmail());
		utenteDto.setUserPassword(utenteBean.getPassword());
		utenteDto.setRoleId(utenteBean.getRuoloId());
		utenteDto.setFirstName(utenteBean.getNome());
		utenteDto.setLastName(utenteBean.getCognome());
		utenteDto.setUserManagerId(utenteBean.getValutatoreId());
		utenteDto.setCompanyOp(utenteBean.getSocietaOp());
		utenteDto.setUserJob(utenteBean.getMansione());
		utenteDto.setUserScope(utenteBean.getAmbito());
		utenteDto.setUserJobFamily(utenteBean.getJobFam());
		utenteDto.setUserSubFamily(utenteBean.getSubFam());
		utenteDto.setUserStandardJob(utenteBean.getStdJob());
		utenteDto.setUserJobLevel(utenteBean.getJobLevel());

		return utenteDto;
	}

	public static UtenteBean toBean(UtenteDto utenteDto) {
		UtenteBean utenteBean = new UtenteBean();
		utenteBean.setUtenteId(utenteDto.getUserId());
		utenteBean.setEmail(utenteDto.getUserEmail());
		utenteBean.setPassword(utenteDto.getUserPassword());
		utenteBean.setRuoloId(utenteDto.getRoleId());
		utenteBean.setNome(utenteDto.getFirstName());
		utenteBean.setCognome(utenteDto.getLastName());
		utenteBean.setValutatoreId(utenteDto.getUserManagerId());
		utenteBean.setSocietaOp(utenteDto.getCompanyOp());
		utenteBean.setMansione(utenteDto.getUserJob());
		utenteBean.setAmbito(utenteDto.getUserScope());
		utenteBean.setJobFam(utenteDto.getUserJobFamily());
		utenteBean.setSubFam(utenteDto.getUserSubFamily());
		utenteBean.setStdJob(utenteDto.getUserStandardJob());
		utenteBean.setJobLevel(utenteDto.getUserJobLevel());
		return utenteBean;
	}
}