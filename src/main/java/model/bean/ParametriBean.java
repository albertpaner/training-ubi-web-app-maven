package model.bean;

import java.sql.Date;

public class ParametriBean extends Bean{
	int paramId;
	int paragrafoId;
	String descrParam;
	Date dataUltMod;
	Date dataCreazione;

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


	public int getValutId() {
		return valutId;
	}

	public void setValutId(int valutId) {
		this.valutId = valutId;
	}


}
