package model.Dto;

public class RuoloDto {

    int ruoloId;
    String descrRl;

    public int getRuoloId() {
	return ruoloId;
    }

    public void setRuoloId(int ruoloId) {
	this.ruoloId = ruoloId;
    }

    public String getDescrRl() {
	return descrRl;
    }

    public void setDescrRl(String descrRl) {
	this.descrRl = descrRl;
    }

    @Override
    public String toString() {
        return "RuoloBean [ruoloId=" + ruoloId + ", descrRl=" + descrRl + "]";
    }


}
