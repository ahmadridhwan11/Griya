package com.kimia.farma.model;


public class Transaksi_detail {
	private Obat Obat;
	
	private Jasa jasa;
	private Double sub_total;
	private Integer jml_beli;
	private Integer dsc_obat;
	public Transaksi_detail() {
		this.Obat = null;
		this.jasa = null;
		this.sub_total = 0d;
		this.jml_beli = 0;
		this.dsc_obat=0;
		
	}



	

	public Transaksi_detail(Obat obat, Jasa jasa, Double sub_total,
			Integer jml_beli ,Dokter dokter,Integer dsc_obat) {
		this.Obat = obat;
		this.jasa = jasa;
		this.sub_total = sub_total;
		this.jml_beli = jml_beli;
		this.dsc_obat=dsc_obat;
	}




	public Obat getObat() {
		return Obat;
	}

	public void setObat(Obat obat) {
		this.Obat = obat;
	}

	public Jasa getJasa() {
		return jasa;
	}

	public void setJasa(Jasa jasa) {
		this.jasa = jasa;
	}

	public Double getSub_total() {
		return sub_total;
	}

	public void setSub_total(Double sub_total) {
		this.sub_total = sub_total;
	}

	public Integer getJml_beli() {
		return jml_beli;
	}

	public void setJml_beli(Integer jml_beli) {
		this.jml_beli = jml_beli;
	}
	public void setDsc_obat(Integer dsc_obat) {
		this.dsc_obat = dsc_obat;
	}
	public Integer getDsc_obat() {
		return dsc_obat;
	}
	
}
