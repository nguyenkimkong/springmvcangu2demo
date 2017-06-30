package com.example.domain;

import com.google.gson.annotations.SerializedName;

public class CollegeFootballSchedule {
	//@SerializedName("year")
	private String yr; // yr
	//@SerializedName("date")
	private String dte;// dte
	//@SerializedName("visitingTeam")
	private String vis; // vis
	//@SerializedName("homeTeam")
	private String hom; // hom
	//@SerializedName("location")
	private String loc; // loc
	//@SerializedName("time")
	private String tm; // tm

	public String getYr() {
		return yr;
	}

	public void setYr(String yr) {
		this.yr = yr;
	}

	public String getDte() {
		return dte;
	}

	public void setDte(String dte) {
		this.dte = dte;
	}

	public String getVis() {
		return vis;
	}

	public void setVis(String vis) {
		this.vis = vis;
	}

	public String getHom() {
		return hom;
	}

	public void setHom(String hom) {
		this.hom = hom;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

}
