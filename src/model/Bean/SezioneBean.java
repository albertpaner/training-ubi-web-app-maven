package model.Bean;

import java.sql.Date;

public class SezioneBean {

    int sezioneId;
    String titoloSez;
    String descrSez;
    Date dataUltAcc;
    Date dataUltMod;
    Boolean flgDel;

    public SezioneBean() {
    }

    public int getSezioneId() {
        return sezioneId;
    }

    public void setSezioneId(int sezioneId) {
        this.sezioneId = sezioneId;
    }

    public String getTitoloSez() {
        return titoloSez;
    }

    public void setTitoloSez(String titoloSez) {
        this.titoloSez = titoloSez;
    }

    public String getDescrSez() {
        return descrSez;
    }

    public void setDescrSez(String descrSez) {
        this.descrSez = descrSez;
    }

    public Date getDataUltAcc() {
        return dataUltAcc;
    }

    public void setDataUltAcc(Date dataUltAcc) {
        this.dataUltAcc = dataUltAcc;
    }

    public Date getDataUltMod() {
        return dataUltMod;
    }

    public void setDataUltMod(Date dataUltMod) {
        this.dataUltMod = dataUltMod;
    }

    public Boolean getFlgDel() {
        return flgDel;
    }

    public void setFlgDel(Boolean flgDel) {
        this.flgDel = flgDel;
    }

    @Override
    public String toString() {
        return "SezioneBean [sezioneId=" + sezioneId + ", titoloSez=" + titoloSez + ", descrSez=" + descrSez
                + ", dataUltAcc=" + dataUltAcc + ", dataUltMod=" + dataUltMod + ", flgDel=" + flgDel + "]";
    }

}
