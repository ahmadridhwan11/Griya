package com.kimia.farma.model;

public class Passien extends Identitas {

	private String kd_passien;

	public Passien() {
		kd_passien = "";
	}

	public Passien(String kd_passien) {
		super();
		this.kd_passien = kd_passien;
	}

	public void setKd_passien(String kd_passien) {
		this.kd_passien = kd_passien;
	}

	public String getKd_passien() {
		return kd_passien;
	}
}
