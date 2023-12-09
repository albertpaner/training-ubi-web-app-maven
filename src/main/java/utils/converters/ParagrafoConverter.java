package utils.converters;

import model.bean.ParagrafoBean;
import model.dto.ParagrafoDto;

public class ParagrafoConverter {
	
	public ParagrafoDto toDto(ParagrafoBean paragrafoBean) {
	
		ParagrafoDto paragrafoDto = new ParagrafoDto();
	
		paragrafoDto.setIdSezione(paragrafoBean.getSezioneId());
		paragrafoDto.setParDescr(paragrafoBean.getDescrPar());
		paragrafoDto.setParTitolo(paragrafoBean.getTitoloPar());
	
		return paragrafoDto; 
	}

	public ParagrafoBean toBean(ParagrafoDto paragrafoDto) {
	
		ParagrafoBean paragrafoBean = new ParagrafoBean();
	
		paragrafoBean.setDescrPar(paragrafoDto.getParDescr());
		paragrafoBean.setTitoloPar(paragrafoDto.getParTitolo());
		paragrafoBean.setSezioneId(paragrafoDto.getIdSezione());
	
		return paragrafoBean; 
	}
}
