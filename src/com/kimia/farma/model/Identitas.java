package com.kimia.farma.model;

import java.util.Date;

public class Identitas {
	private String nama;
	private String kelamin;
	private String alamat;
	private String nomer_tlp;
	private Date tgl_lahir;
	private String status_perkawinan;
	
	public Identitas() {
			this.nama = "";
			this.kelamin = "";
			this.alamat = "";
			this.nomer_tlp = "";
			this.tgl_lahir = new Date();
			this.status_perkawinan = "";
	}
	
	public Identitas(String nama, String kelamin, String alamat, String nomer_tlp, Date tgl_lahir,
			String status_perkawinan) {
		this.nama = nama;
		this.kelamin = kelamin;
		this.alamat = alamat;
		this.nomer_tlp = nomer_tlp;
		this.tgl_lahir = tgl_lahir;
		this.status_perkawinan = status_perkawinan;
	}

	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getKelamin() {
		return kelamin;
	}
	public void setKelamin(String kelamin) {
		this.kelamin = kelamin;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getNomer_tlp() {
		return nomer_tlp;
	}
	public void setNomer_tlp(String nomer_tlp) {
		this.nomer_tlp = nomer_tlp;
	}
	public Date getTgl_lahir() {
		return tgl_lahir;
	}
	public void setTgl_lahir(Date tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
	public String getStatus_perkawinan() {
		return status_perkawinan;
	}
	public void setStatus_perkawinan(String status_perkawinan) {
		this.status_perkawinan = status_perkawinan;
	}
}
