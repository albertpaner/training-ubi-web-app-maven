package model.bean;

import java.sql.Date;

public class ParagrafoBean extends Bean{

	int paragrafoId;
	int sezioneId;
	String titoloPar;
	String descrPar;
	Date dataUltMod;
	Date dataCreaz;

	public ParagrafoBean() {
	}

	public int getParagrafoId() {
		return paragrafoId;
	}

	public void setParagrafoId(int paragrafoId) {
		this.paragrafoId = paragrafoId;
	}

	public int getSezioneId() {
		return sezioneId;
	}

	public void setSezioneId(int sezioneId) {
		this.sezioneId = sezioneId;
	}

	public String getTitoloPar() {
		return titoloPar;
	}

	public void setTitoloPar(String titoloPar) {
		this.titoloPar = titoloPar;
	}

	public String getDescrPar() {
		return descrPar;
	}

	public void setDescrPar(String descrPar) {
		this.descrPar = descrPar;
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
