package com.kimia.farma.model;

public class Obat {

	private String kd_obat;
	private String nama_obat;
	private Integer stok;
	private String merk;
	private String jenis;
	private String type;
	private Double harga;

	public String getKd_obat() {
		return kd_obat;
	}

	public void setKd_obat(String kd_obat) {
		this.kd_obat = kd_obat;
	}

	public String getNama_obat() {
		return nama_obat;
	}

	public void setNama_obat(String nama_obat) {
		this.nama_obat = nama_obat;
	}

	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getHarga() {
		return harga;
	}

	public void setHarga(Double harga) {
		this.harga = harga;
	}

	public Obat() {
		this.kd_obat = "";
		this.nama_obat = "";
		this.stok = 0;
		this.merk = "";
		this.jenis = "";
		this.type = "";
		this.harga = 0d;
	}

	public Obat(String kd_obat, String nama_obat, Integer stok, String merk, String jenis, String type, Double harga) {
		this.kd_obat = kd_obat;
		this.nama_obat = nama_obat;
		this.stok = stok;
		this.merk = merk;
		this.jenis = jenis;
		this.type = type;
		this.harga = harga;
	}

}
