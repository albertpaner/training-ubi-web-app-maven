package model.dto;

import java.sql.Date;

public class ParametriDto {
    private String descrizioneParametro;
    private Date dataUltimaModifica;
    private Date dataDiCreazione;
    public String getDescrizioneParametro() {
        return descrizioneParametro;
    }
    public void setDescrizioneParametro(String descrizioneParametro) {
        this.descrizioneParametro = descrizioneParametro;
    }
    public Date getDataUltimaModifica() {
        return dataUltimaModifica;
    }
    public void setDataUltimaModifica(Date dataUltimaModifica) {
        this.dataUltimaModifica = dataUltimaModifica;
    }
    public Date getDataDiCreazione() {
        return dataDiCreazione;
    }
    public void setDataDiCreazione(Date dataDiCreazione) {
        this.dataDiCreazione = dataDiCreazione;
    }




}