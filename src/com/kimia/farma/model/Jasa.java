package com.kimia.farma.model;

public class Jasa {
	
	private String kd_jasa;
	private Double harga;
	private String nama;
	private Integer persen;
	private Double fee;
	private Dokter dokter;
	 
	

	public Jasa(String kd_jasa, Double harga, String nama, Integer persen, Double fee, Dokter dokter) {
		super();
		this.kd_jasa = kd_jasa;
		this.harga = harga;
		this.nama = nama;
		this.persen = persen;
		this.fee = fee;
		this.dokter = dokter;
	}
	public Jasa() {
		// TODO Auto-generated constructor stub
		kd_jasa="";
		 harga=0d;
		nama="";
		dokter=null;
		this.persen = 0;
		this.fee = 0d;
	}
	
	public void setPersen(Integer persen) {
		this.persen = persen;
	}
	public Integer getPersen() {
		return persen;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getFee() {
		return fee;
	}
	public void setDokter(Dokter dokter) {
		this.dokter = dokter;
	}
	public Dokter getDokter() {
		return dokter;
	}

	public String getKd_jasa() {
		return kd_jasa;
	}

	public void setKd_jasa(String kd_jawa) {
		this.kd_jasa = kd_jawa;
	}

	public Double getHarga() {
		return harga;
	}

	public void setHarga(Double harga) {
		this.harga = harga;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
}
