package model.bean;

import java.sql.Date;

public class UtenteBean extends Bean {
    private int utenteId;
    private String email;
    private String password;
    private int ruoloId;
    private String nome;
    private String cognome;
    private int valutatoreId;
    private Date dataNascita;
    private Date dataUltAcc;
    private Date dataUltMod;
    private Date dataCreaz;

    public String getSocietàOperativa() {
        return societàOperativa;
    }

    public void setSocietàOperativa(String societàOperativa) {
        this.societàOperativa = societàOperativa;
    }

    private boolean inSospeso;
    private String societàOperativa;

    public UtenteBean() {
        super();
    }

    public boolean getInSospeso() {
        return inSospeso;
    }

    public void setInSospeso(boolean inSospeso) {
        this.inSospeso = inSospeso;
    }

    public int getUtenteId() {
        return this.utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRuoloId() {
        return this.ruoloId;
    }

    public void setRuoloId(int ruoloId) {
        this.ruoloId = ruoloId;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getValutatoreId() {
        return this.valutatoreId;
    }

    public void setValutatoreId(int valutatoreId) {
        this.valutatoreId = valutatoreId;
    }

    public Date getDataNascita() {
        return this.dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Date getDataUltAcc() {
        return this.dataUltAcc;
    }

    public void setDataUltAcc(Date dataUltAcc) {
        this.dataUltAcc = dataUltAcc;
    }

    public Date getDataUltMod() {
        return this.dataUltMod;
    }

    public void setDataUltMod(Date dataUltMod) {
        this.dataUltMod = dataUltMod;
    }

    public Date getDataCreaz() {
        return this.dataCreaz;
    }

    public void setDataCreaz(Date dataCreaz) {
        this.dataCreaz = dataCreaz;
    }


    /**
     * This method is used to print the utente BEAN in a readable way
     *
     * @return the string representation of the utente BEAN
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UtenteBean{" +
                "utenteId=" + utenteId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruoloId=" + ruoloId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", valutatoreId=" + valutatoreId +
                ", dataNascita=" + dataNascita +
                ", dataUltAcc=" + dataUltAcc +
                ", dataUltMod=" + dataUltMod +
                ", dataCreaz=" + dataCreaz +
                ", inSospeso=" + inSospeso +
                ", societàOperativa='" + societàOperativa + '\'' +
                '}';
    }
}
