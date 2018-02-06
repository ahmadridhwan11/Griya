package com.kimia.farma.model;

public class Supplier extends Identitas {
	private String kd_supplier;

	public void setKd_supplier(String kd_supplier) {
		this.kd_supplier = kd_supplier;
	}

	public String getKd_supplier() {
		return kd_supplier;
	}

	public Supplier() {
		// TODO Auto-generated constructor stub
		kd_supplier = "";
	}

	public Supplier(String kd_supplier) {
		this.kd_supplier = kd_supplier;
	}

}
