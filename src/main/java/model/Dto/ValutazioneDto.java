package model.Dto;

import model.Bean.ValutazioneBean;

public class ValutazioneDto {
    private int valutazioneId;
    private int valoreValutazione;
    private String descrizioneValutazione;
    private int utenteId;

    public ValutazioneDto() {
        
    }


    public int getValutazioneId() {
        return valutazioneId;
    }

    public void setValutazioneId(int valutazioneId) {
        this.valutazioneId = valutazioneId;
    }

    public int getValoreValutazione() {
        return valoreValutazione;
    }

    public void setValoreValutazione(int valoreValutazione) {
        this.valoreValutazione = valoreValutazione;
    }

    public String getDescrizioneValutazione() {
        return descrizioneValutazione;
    }

    public void setDescrizioneValutazione(String descrizioneValutazione) {
        this.descrizioneValutazione = descrizioneValutazione;
    }

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }


    @Override
    public String toString() {
        return "ValutazioneDto [valutazioneId=" + valutazioneId + ", valoreValutazione=" + valoreValutazione
                + ", descrizioneValutazione=" + descrizioneValutazione + ", utenteId=" + utenteId + "]";
    }

}

