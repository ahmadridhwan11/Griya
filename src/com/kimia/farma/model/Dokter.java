package com.kimia.farma.model;

public class Dokter extends Identitas {

	private String kd_dokter;
	private String spesialis;

	public Dokter() {
		// TODO Auto-generated constructor stub
		this.kd_dokter = "";
		this.spesialis = "";

	}

	public Dokter(String kd_dokter, String spesialis) {
		this.kd_dokter = kd_dokter;
		this.spesialis = spesialis;
	}

	public String getKd_dokter() {
		return kd_dokter;
	}

	public void setKd_dokter(String kd_dokter) {
		this.kd_dokter = kd_dokter;
	}

	public String getSpesialis() {
		return spesialis;
	}

	public void setSpesialis(String spesialis) {
		this.spesialis = spesialis;
	}

}
