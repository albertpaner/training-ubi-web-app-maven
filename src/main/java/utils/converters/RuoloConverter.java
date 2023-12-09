package utils.converters;

import model.bean.RuoloBean;
import model.dto.RuoloDto;

public class RuoloConverter {

    public static RuoloDto toDto(RuoloBean ruoloBean){

        RuoloDto ruoloDto = new RuoloDto();
        ruoloDto.setRuoloId(ruoloBean.getRuoloId());
        ruoloDto.setDescrRl(ruoloBean.getDescrRl());

        return ruoloDto;
    }

    public static RuoloBean toBean(RuoloDto ruoloDto){

        RuoloBean ruoloBean = new RuoloBean();
        ruoloBean.setRuoloId(ruoloDto.getRuoloId());
        ruoloBean.setDescrRl(ruoloDto.getDescrRl());

        return ruoloBean;

    }
    
}
