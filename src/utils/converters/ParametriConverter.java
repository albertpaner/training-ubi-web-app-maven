package utils.converters;

import model.Bean.ParametriBean;
import model.Dto.ParametriDto;

public class ParametriConverter {
    public ParametriDto toDto(ParametriBean parametriBean) {

    ParametriDto parametriDto = new ParametriDto();

    parametriDto.setDescrizioneParametro(parametriBean.getDescrParam());
    parametriDto.setDataUltimaModifica(parametriBean.getDataUltMod());
    parametriDto.setDataDiCreazione(parametriBean.getDataCreazione());

    return parametriDto;

    }
    public ParametriBean toBean(ParametriDto parametriDto) {
        ParametriBean parametriBean = new ParametriBean();

        parametriBean.setDescrParam(parametriDto.getDescrizioneParametro());
        parametriBean.setDataUltMod(parametriDto.getDataUltimaModifica());
        parametriBean.setDataCreazione(parametriDto.getDataDiCreazione());

        return parametriBean;
    }
}