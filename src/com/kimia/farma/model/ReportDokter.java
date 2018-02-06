package com.kimia.farma.model;



public class ReportDokter {
	
	private int jml;
	private Double sub_total;
	private Double gaji;
	private Jasa jasa;
	
	
	public ReportDokter(int jml, Double sub_total, Jasa jasa,Double gaji) {
		super();
		this.jml = jml;
		this.sub_total = sub_total;
		this.gaji=gaji;
		this.jasa = jasa;
	}
	
	public Double getGaji() {
		return gaji;
	}
	public void setGaji(Double gaji) {
		this.gaji = gaji;
	}
	
	public Jasa getJasa() {
		return jasa;
	}
	public void setJasa(Jasa jasa) {
		this.jasa = jasa;
	}
	public int getJml() {
		return jml;
	}
	public void setJml(int jml) {
		this.jml = jml;
	}
	public Double getSub_total() {
		return sub_total;
	}
	public void setSub_total(Double sub_total) {
		this.sub_total = sub_total;
	}
	
	
	 public ReportDokter() {
		 this.jasa = new Jasa();
			this.jml = 0;
			this.sub_total = 0d;
			this.gaji=0d;
	}
	
	
}
