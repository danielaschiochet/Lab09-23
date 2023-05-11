package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	
	private String stateAbb;
	private int cCode;
	private String stateNme;
	public Country(String stateAbb, int cCode, String stateNme) {
		super();
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateNme = stateNme;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public int getcCode() {
		return cCode;
	}
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}
	public String getStateNme() {
		return stateNme;
	}
	public void setStateNme(String stateNme) {
		this.stateNme = stateNme;
	}
	@Override
	public String toString() {
		return "Country [stateAbb=" + stateAbb + ", cCode=" + cCode + ", stateNme=" + stateNme + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return cCode == other.cCode;
	}
	
	

}
