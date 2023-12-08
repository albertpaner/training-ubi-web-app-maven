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
   /* private String societaOp;
    private String mansione;
    private String ambito;
    private String jobFam;
    private String subFam;
    private String stdJob;
    private String jobLevel; */
	private Date dataNascita; 
    private Date dataUltAcc;
    private Date dataUltMod;
    private Date dataCreaz;
    private boolean flgDel;


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

	public boolean isFlgDel() {
		return this.flgDel;
	}

	public boolean getFlgDel() {
		return this.flgDel;
	}

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
		return "{" +
			" utente_id='" + getUtenteId() + "'" +
			", email='" + getEmail() + "'" +
			", password='" + getPassword() + "'" +
			", ruolo_id='" + getRuoloId() + "'" +
			", nome='" + getNome() + "'" +
			", cognome='" + getCognome() + "'" +
			", valutatore_id='" + getValutatoreId() + "'" +
			", data_nascita='" + getDataNascita() + "'" +
			", data_ultimo_accesso='" + getDataUltAcc() + "'" +
			", data_ultima_modifica='" + getDataUltMod() + "'" +
			", data_creazione='" + getDataCreaz() + "'" +
			", flg_delete='" + isFlgDel() + "'" +
			"}";
	}


}
