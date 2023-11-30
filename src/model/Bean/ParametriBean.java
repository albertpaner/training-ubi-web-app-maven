package model.Bean;

import java.sql.Date;

public class ParametriBean {
	int paramId;
	int paragrafoId;
	String descrParam;
	Date dataUltMod;
	Date dataCreazione;
	Boolean flgDel;
	int valutId;

	public ParametriBean() {
	}

	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public int getParagrafoId() {
		return paragrafoId;
	}

	public void setParagrafoId(int paragrafoId) {
		this.paragrafoId = paragrafoId;
	}

	public String getDescrParam() {
		return descrParam;
	}

	public void setDescrParam(String descrParam) {
		this.descrParam = descrParam;
	}

	public Date getDataUltMod() {
		return dataUltMod;
	}

	public void setDataUltMod(Date dataUltMod) {
		this.dataUltMod = dataUltMod;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Boolean getFlgDel() {
		return flgDel;
	}

	public void setFlgDel(Boolean flgDel) {
		this.flgDel = flgDel;
	}

	public int getValutId() {
		return valutId;
	}

	public void setValutId(int valutId) {
		this.valutId = valutId;
	}

	@Override
	public String toString() {
		return "ParametriBean [paramId=" + paramId + ", paragrafoId=" + paragrafoId + ", descrParam=" + descrParam
				+ ", dataUltMod=" + dataUltMod + ", dataCreazione=" + dataCreazione + ", flgDel=" + flgDel
				+ ", valutId=" + valutId + "]";
	}

}
