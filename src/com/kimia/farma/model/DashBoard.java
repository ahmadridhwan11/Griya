package com.kimia.farma.model;

public class DashBoard {

	private Integer transaksi_h;
	private Integer transaksi_b;
	private Integer transaksi_obt_h;
	private Integer transaksi_obt_b;
	private Double pendapatan_h;
	private Double pendapatan_b;

	public DashBoard() {
		// TODO Auto-generated constructor stub
		transaksi_h = 0;
		transaksi_b = 0;
		transaksi_obt_h = 0;
		transaksi_obt_b = 0;
		pendapatan_h = 0d;
		pendapatan_b = 0d;

	}
	

	public DashBoard(Integer transaksi_h, Integer transaksi_b, Integer transaksi_obt_h, Integer transaksi_obt_b,
			Double pendapatan_h, Double pendapatan_b) {
		super();
		this.transaksi_h = transaksi_h;
		this.transaksi_b = transaksi_b;
		this.transaksi_obt_h = transaksi_obt_h;
		this.transaksi_obt_b = transaksi_obt_b;
		this.pendapatan_h = pendapatan_h;
		this.pendapatan_b = pendapatan_b;
	}


	public Integer getTransaksi_h() {
		return transaksi_h;
	}

	public void setTransaksi_h(Integer transaksi_h) {
		this.transaksi_h = transaksi_h;
	}

	public Integer getTransaksi_b() {
		return transaksi_b;
	}

	public void setTransaksi_b(Integer transaksi_b) {
		this.transaksi_b = transaksi_b;
	}

	public Integer getTransaksi_obt_h() {
		return transaksi_obt_h;
	}

	public void setTransaksi_obt_h(Integer transaksi_obt_h) {
		this.transaksi_obt_h = transaksi_obt_h;
	}

	public Integer getTransaksi_obt_b() {
		return transaksi_obt_b;
	}

	public void setTransaksi_obt_b(Integer transaksi_obt_b) {
		this.transaksi_obt_b = transaksi_obt_b;
	}

	public Double getPendapatan_h() {
		return pendapatan_h;
	}

	public void setPendapatan_h(Double pendapatan_h) {
		this.pendapatan_h = pendapatan_h;
	}

	public Double getPendapatan_b() {
		return pendapatan_b;
	}

	public void setPendapatan_b(Double pendapatan_b) {
		this.pendapatan_b = pendapatan_b;
	}
	
}
