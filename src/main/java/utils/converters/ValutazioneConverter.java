package utils.converters;

import model.dto.ValutazioneDto;
import model.bean.ValutazioneBean;

public class ValutazioneConverter {

    public static ValutazioneDto toDto(ValutazioneBean valutazioneBean) {
        ValutazioneDto valutazioneDto = new ValutazioneDto();
        valutazioneDto.setUtenteId(valutazioneBean.getUtenteId());
        valutazioneDto.setValutazioneId(valutazioneBean.getValut_id());
        valutazioneDto.setDescrizioneValutazione(valutazioneBean.getDescrVal());
        valutazioneDto.setValoreValutazione(valutazioneBean.getValoreVal());
        return valutazioneDto;
    }

    public static ValutazioneBean toBean(ValutazioneDto valutazioneDto) {
        ValutazioneBean valutazioneBean = new ValutazioneBean();
        valutazioneBean.setUtenteId(valutazioneDto.getUtenteId());
        valutazioneBean.setValut_id(valutazioneDto.getValutazioneId());
        valutazioneBean.setDescrVal(valutazioneDto.getDescrizioneValutazione());
        valutazioneBean.setValoreVal(valutazioneDto.getValoreValutazione());
        return valutazioneBean;
    }
}
