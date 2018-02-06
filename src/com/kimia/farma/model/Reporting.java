package com.kimia.farma.model;

import java.util.Date;

public class Reporting {
private Date tgl_awal;
private Date tgl_akhir;
private String table;
public void setTable(String table) {
	this.table = table;
}
public String getTable() {
	return table;
}
public void setTgl_akhir(Date tgl_akhir) {
	this.tgl_akhir = tgl_akhir;
}
public void setTgl_awal(Date tgl_awal) {
	this.tgl_awal = tgl_awal;
}
public Date getTgl_akhir() {
	return tgl_akhir;
}
public Date getTgl_awal() {
	return tgl_awal;
}
public Reporting() {
	// TODO Auto-generated constructor stub
	tgl_akhir=null;
	tgl_awal=null;
	table=null;
}
public Reporting(Date tgl_awal, Date tgl_akhir,String table) {
	super();
	this.tgl_awal = tgl_awal;
	this.tgl_akhir = tgl_akhir;
	this.table=table;
}

}
