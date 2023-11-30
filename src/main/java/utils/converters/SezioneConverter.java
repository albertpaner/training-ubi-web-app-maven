package utils.converters;

import model.Bean.SezioneBean;
import model.Dto.SezioneDto;

public class SezioneConverter{

    public static SezioneDto toDto(SezioneBean sezioneBean){
        SezioneDto sezioneDto = new SezioneDto();
        sezioneDto.setSezioneId(sezioneBean.getSezioneId());
        sezioneDto.setTitoloSez(sezioneBean.getTitoloSez());
        sezioneDto.setDescrSez(sezioneBean.getDescrSez());

        return sezioneDto;

    }

    public static SezioneBean toBean(SezioneDto sezioneDto){

        SezioneBean sezioneBean = new SezioneBean();
        sezioneBean.setSezioneId(sezioneDto.getSezioneId());
        sezioneBean.setTitoloSez(sezioneDto.getTitoloSez());
        sezioneBean.setDescrSez(sezioneDto.getDescrSez());

        return sezioneBean;
    }

}
