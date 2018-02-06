package com.kimia.farma.model;

import java.util.Date;

public class BarangMasuk {
	private String kd_masuk;
	private int qty;
	private Date tgl_masuk;
	private Date tgl_expired;
	private Supplier supplier;
	private Obat obat;
	private User user;

	public BarangMasuk() {
		this.kd_masuk = "";
		this.qty = 0;
		this.tgl_masuk = null;
		this.tgl_expired = null;
		this.supplier = null;
		this.obat = null;
		this.user = null;
	}

	public BarangMasuk(String kd_masuk, int qty, Date tgl_masuk, Date tgl_expired, Supplier supplier, Obat obat,
			User user) {
		this.kd_masuk = kd_masuk;
		this.qty = qty;
		this.tgl_masuk = tgl_masuk;
		this.tgl_expired = tgl_expired;
		this.supplier = supplier;
		this.obat = obat;
		this.user = user;
	}

	public String getKd_masuk() {
		return kd_masuk;
	}

	public void setKd_masuk(String kd_masuk) {
		this.kd_masuk = kd_masuk;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Date getTgl_masuk() {
		return tgl_masuk;
	}

	public void setTgl_masuk(Date tgl_masuk) {
		this.tgl_masuk = tgl_masuk;
	}

	public Date getTgl_expired() {
		return tgl_expired;
	}

	public void setTgl_expired(Date tgl_expired) {
		this.tgl_expired = tgl_expired;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setObat(Obat obat) {
		this.obat = obat;
	}
	public Obat getObat() {
		return obat;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
