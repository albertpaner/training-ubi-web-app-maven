package model.Bean;

import java.sql.Date;

public class UtenteBean {
    private int utenteId;
    private String email;
    private String password;
    private int ruoloId;
    private String nome;
    private String cognome;
    private int valutatoreId;
    private String societaOp;
    private String mansione;
    private String ambito;
    private String jobFam;
    private String subFam;
    private String stdJob;
    private String jobLevel;
    private Date dataUltAcc;
    private Date dataUltMod;
    private Date dataCreaz;
    private boolean flgDel;

   
    


    public int getUtenteId() {
        return utenteId;
    }





    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }





    public String getEmail() {
        return email;
    }





    public void setEmail(String email) {
        this.email = email;
    }





    public String getPassword() {
        return password;
    }





    public void setPassword(String password) {
        this.password = password;
    }





    public int getRuoloId() {
        return ruoloId;
    }





    public void setRuoloId(int ruoloId) {
        this.ruoloId = ruoloId;
    }





    public String getNome() {
        return nome;
    }





    public void setNome(String nome) {
        this.nome = nome;
    }





    public String getCognome() {
        return cognome;
    }





    public void setCognome(String cognome) {
        this.cognome = cognome;
    }





    public int getValutatoreId() {
        return valutatoreId;
    }





    public void setValutatoreId(int i) {
        this.valutatoreId = i;
    }





    public String getSocietaOp() {
        return societaOp;
    }





    public void setSocietaOp(String societaOp) {
        this.societaOp = societaOp;
    }





    public String getMansione() {
        return mansione;
    }





    public void setMansione(String mansione) {
        this.mansione = mansione;
    }





    public String getAmbito() {
        return ambito;
    }





    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }





    public String getJobFam() {
        return jobFam;
    }





    public void setJobFam(String jobFam) {
        this.jobFam = jobFam;
    }





    public String getSubFam() {
        return subFam;
    }





    public void setSubFam(String subFam) {
        this.subFam = subFam;
    }





    public String getStdJob() {
        return stdJob;
    }





    public void setStdJob(String stdJob) {
        this.stdJob = stdJob;
    }





    public String getJobLevel() {
        return jobLevel;
    }





    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
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





    public boolean isUserBeanEmpty() {
        return this.getEmail().isEmpty();
    }





    @Override
    public String toString() {
        return "UtenteBean [utenteId=" + utenteId + ", email=" + email + ", password=" + password + ", ruoloId="
                + ruoloId + ", nome=" + nome + ", cognome=" + cognome + ", valutatoreId=" + valutatoreId
                + ", societaOp=" + societaOp + ", mansione=" + mansione + ", ambito=" + ambito + ", jobFam=" + jobFam
                + ", subFam=" + subFam + ", stdJob=" + stdJob + ", jobLevel=" + jobLevel + ", dataUltAcc=" + dataUltAcc
                + ", dataUltMod=" + dataUltMod + ", dataCreaz=" + dataCreaz + ", flgDel=" + flgDel + "]";
    }

    

}
