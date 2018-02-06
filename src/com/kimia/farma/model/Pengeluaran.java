package com.kimia.farma.model;

import java.util.Date;

public class Pengeluaran {
	private String kd_pengeluaran;
	private String detail;
	private Double total;
	private Date tgl;
	public String getKd_pengeluaran() {
		return kd_pengeluaran;
	}
	public void setKd_pengeluaran(String kd_pengeluaran) {
		this.kd_pengeluaran = kd_pengeluaran;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getTgl() {
		return tgl;
	}
	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}
	
	public Pengeluaran() {
		// TODO Auto-generated constructor stub
	}
	public Pengeluaran(String kd_pengeluaran, String detail, Double total, Date tgl) {
		super();
		this.kd_pengeluaran = kd_pengeluaran;
		this.detail = detail;
		this.total = total;
		this.tgl = tgl;
	}
	
	

}
