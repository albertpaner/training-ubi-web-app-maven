package model.bean;

import java.sql.Date;

public class UtenteBean extends Bean{
    private int utenteId;
    private String email;
    private String password;
    private int ruoloId;
    private String nome;
    private String cognome;
    private int valutatoreId;
	private String mansione;
	private String jobLevel;
	private String societàOperativa;
	private Date dataNascita;
	private boolean inSospeso;
    private Date dataUltAcc;
    private Date dataUltMod;
    private Date dataCreaz;
	private boolean flgDel;

	public UtenteBean() {
		super();
	}

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

	public void setValutatoreId(int valutatoreId) {
		this.valutatoreId = valutatoreId;
	}

	public String getMansione() {
		return mansione;
	}

	public void setMansione(String mansione) {
		this.mansione = mansione;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getSocietàOperativa() {
		return societàOperativa;
	}

	public void setSocietàOperativa(String societàOperativa) {
		this.societàOperativa = societàOperativa;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public boolean isInSospeso() {
		return inSospeso;
	}

	public void setInSospeso(boolean inSospeso) {
		this.inSospeso = inSospeso;
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

	@Override
	public void setFlgDel(boolean flgDel) {
		this.flgDel = flgDel;
	}

	/**
	 * This method is used to print the utente BEAN in a readable way
	 *
	 * @return the string representation of the utente BEAN
	 * @see java.lang.Object#toString()
	 * */
	@Override
	public String toString() {
		return "UtenteBean{" +
				"utente Id=" + utenteId +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", ruolo Id=" + ruoloId +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", valutatore Id=" + valutatoreId +
				", mansione='" + mansione + '\'' +
				", job Level='" + jobLevel + '\'' +
				", società Operativa='" + societàOperativa + '\'' +
				", data Nascita=" + dataNascita +
				", in Sospeso=" + inSospeso +
				", data Ult Acc=" + dataUltAcc +
				", data Ult Mod=" + dataUltMod +
				", data Creaz=" + dataCreaz +
				", flg Del=" + flgDel +
				'}';
	}

	/*
	public static int compareByNameThenSurname(UtenteBean lhs, UtenteBean rhs) {
		if (lhs.getNome().equals(rhs.getNome())) {
			return (lhs.getCognome().compareTo(rhs.getCognome()));
		} else {
			return (lhs.getNome().compareTo(rhs.getNome()));
		}
	}*/

}
