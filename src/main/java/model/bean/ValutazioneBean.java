package model.bean;

import java.util.Date;

public class ValutazioneBean extends Bean{
    int valutId;
    int valoreVal;
    String descrVal;
    int utenteId;
    Date dataUltMod;
    Date dataCreaz;


    public ValutazioneBean() {
    };

    public int getValutId() {
        return valutId;
    }

    public void setValutId(int valutId) {
        this.valutId = valutId;
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


}
