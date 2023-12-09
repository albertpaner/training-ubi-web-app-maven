package model.bean;

import java.sql.Date;

public class ParagrafoBean {

	int paragrafoId;
	int sezioneId;
	String titoloPar;
	String descrPar;
	Date dataUltMod;
	Date dataCreaz;
	boolean flgDel;

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

	public boolean isFlgDel() {
		return flgDel;
	}

	public void setFlgDel(boolean flgDel) {
		this.flgDel = flgDel;
	}

	@Override
	public String toString() {
		return "ParagrafoBean [paragrafoId=" + paragrafoId + ", sezioneId=" + sezioneId + ", titoloPar=" + titoloPar
				+ ", descrPar=" + descrPar + ", dataUltMod=" + dataUltMod + ", dataCreaz=" + dataCreaz + ", flgDel="
				+ flgDel + "]";
	}

}
