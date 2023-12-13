package utils.converters;

import model.bean.UtenteBean;
import model.dto.UtenteDto;

public class UtenteConverter {

	public static UtenteDto toDto(UtenteBean utenteBean) {
		UtenteDto utenteDto = new UtenteDto();
		utenteDto.setUserId(utenteBean.getUtenteId());
		utenteDto.setUserEmail(utenteBean.getEmail());
		utenteDto.setFirstName(utenteBean.getNome());
		utenteDto.setLastName(utenteBean.getCognome());
		utenteDto.setUserEvaluatorId(utenteBean.getValutatoreId());

		return utenteDto;
	}

	public static UtenteBean toBean(UtenteDto utenteDto) {
		UtenteBean utenteBean = new UtenteBean();
		utenteBean.setUtenteId(utenteDto.getUserId());
		utenteBean.setEmail(utenteDto.getUserEmail());
		utenteBean.setNome(utenteDto.getFirstName());
		utenteBean.setCognome(utenteDto.getLastName());
		utenteBean.setValutatoreId(utenteDto.getUserEvaluatorId());

		return utenteBean;
	}
}