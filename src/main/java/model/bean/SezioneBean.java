package model.bean;

import java.sql.Date;

public class SezioneBean extends Bean {

    int sezioneId;
    String titoloSez;
    String descrSez;
    Date dataUltAcc;
    Date dataUltMod;

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



}
