package com.kimia.farma.model;

import java.util.ArrayList;
import java.util.Date;

public class Transaksi {

	private String kd_transaksi;
	private User user;
	private Double total;
	private Passien passien;
	private ArrayList<Transaksi_detail> transaksi_detail;
	private Date tgl_transaksi;
	private Double uang;
	private int dsc;
	
	public Transaksi() {
		this.kd_transaksi = "";
		this.user = null;
		this.total = 0d;
		this.passien = null;
		this.tgl_transaksi = null;
		this.uang = 0d;
		this.transaksi_detail=new ArrayList<>();
		dsc=0;

	}

	

	public Transaksi(String kd_transaksi, Double total, Passien passien,
			ArrayList<Transaksi_detail> transaksi_detail, Date tgl_transaksi,  Double uang,int dsc) {
		this.kd_transaksi = kd_transaksi;
		this.total = total;
		this.passien = passien;
		this.transaksi_detail = transaksi_detail;
		this.tgl_transaksi = tgl_transaksi;
		this.uang = uang;
		this.dsc=dsc;
	}
	public void setDsc(int dsc) {
		this.dsc = dsc;
	}
	public int getDsc() {
		return dsc;
	}



	public String getKd_transaksi() {
		return kd_transaksi;
	}

	public void setKd_transaksi(String kd_transaksi) {
		this.kd_transaksi = kd_transaksi;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Passien getPassien() {
		return passien;
	}

	public void setPassien(Passien passien) {
		this.passien = passien;
	}

	public ArrayList<Transaksi_detail> getTransaksi_detail() {
		return transaksi_detail;
	}

	public void setTransaksi_detail(ArrayList<Transaksi_detail> transaksi_detail) {
		this.transaksi_detail = transaksi_detail;
	}

	public Date getTgl_transaksi() {
		return tgl_transaksi;
	}

	public void setTgl_transaksi(Date tgl_transaksi) {
		this.tgl_transaksi = tgl_transaksi;
	}

	public void setUang(Double uang) {
		this.uang = uang;
	}
	public Double getUang() {
		return uang;
	}

	
}
