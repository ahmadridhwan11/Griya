package com.kimia.farma.model;

public class ReportTM {
	private Obat obat;
	private int stok_awal;
	private int terjual;
	private int countDsc;
	private Double total;
	
	
	public ReportTM() {
		this.obat = null;
		this.stok_awal = 0;
		this.terjual = 0;
		this.countDsc = 0;
		this.total = 0d;
	}
	public ReportTM(Obat obat, int stok_awal, int terjual, int countDsc, Double total) {
		super();
		this.obat = obat;
		this.stok_awal = stok_awal;
		this.terjual = terjual;
		this.countDsc = countDsc;
		this.total = total;
	}
	public Obat getObat() {
		return obat;
	}
	public void setObat(Obat obat) {
		this.obat = obat;
	}
	public int getStok_awal() {
		return stok_awal;
	}
	public void setStok_awal(int stok_awal) {
		this.stok_awal = stok_awal;
	}
	public int getTerjual() {
		return terjual;
	}
	public void setTerjual(int terjual) {
		this.terjual = terjual;
	}
	public int getCountDsc() {
		return countDsc;
	}
	public void setCountDsc(int countDsc) {
		this.countDsc = countDsc;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	

}
