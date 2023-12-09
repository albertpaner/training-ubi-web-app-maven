package model.bean;

public class RuoloBean {

    int ruoloId;
    String descrRl;
    Boolean flgDel;

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

    public Boolean getFlgDel() {
        return flgDel;
    }

    public void setFlgDel(Boolean flgDel) {
        this.flgDel = flgDel;
    }

    @Override
    public String toString() {
        return "RuoloBean [ruoloId=" + ruoloId + ", descrRl=" + descrRl + ", flgDel=" + flgDel + "]";
    }

    
}
