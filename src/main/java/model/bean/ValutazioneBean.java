package model.bean;

import java.util.Date;

public class ValutazioneBean {
    int valut_id;
    int valoreVal;
    String descrVal;
    int utenteId;
    Date dataUltMod;
    Date dataCreaz;
    boolean flgDel;

    public ValutazioneBean() {
    };

    public int getValut_id() {
        return valut_id;
    }

    public void setValut_id(int valut_id) {
        this.valut_id = valut_id;
    }

    public int getValoreVal() {
        return valoreVal;
    }

    public void setValoreVal(int valoreVal) {
        this.valoreVal = valoreVal;
    }

    public String getDescrVal() {
        return descrVal;
    }

    public void setDescrVal(String descrVal) {
        this.descrVal = descrVal;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public Date getDataUltMod() {
        return dataUltMod;
    }

    public void setDataUltMod(Date dataUltMod) {
        this.dataUltMod = dataUltMod;
    }

    public Date getDataCreaz() {
        return dataCreaz;
    }

    public void setDataCreaz(Date dataCreaz) {
        this.dataCreaz = dataCreaz;
    }

    public boolean isFlgDel() {
        return flgDel;
    }

    public void setFlgDel(boolean flgDel) {
        this.flgDel = flgDel;
    }

    @Override
    public String toString() {
        return "ValutazioneBean [valut_id=" + valut_id + ", valoreVal=" + valoreVal + ", descrVal=" + descrVal
                + ", utenteId=" + utenteId + ", dataUltMod=" + dataUltMod + ", dataCreaz=" + dataCreaz + ", flgDel="
                + flgDel + "]";
    }

}
