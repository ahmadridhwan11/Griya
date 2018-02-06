package com.kimia.farma.model;

import java.util.Date;

public class LogData {

	private String kd_log;
	private String type;
	private Date tgl_log;
	private String deskripsi;
	private String data;

	public LogData() {
		this.kd_log = "";
		this.type = "";
		this.tgl_log = null;
		this.deskripsi = "";
		this.data = "";

	}

	public LogData(String kd_log, String type, Date tgl_log, String deskripsi, String data) {
		this.kd_log = kd_log;
		this.type = type;
		this.tgl_log = tgl_log;
		this.deskripsi = deskripsi;
		this.data = data;
	}

	public String getKd_log() {
		return kd_log;
	}

	public void setKd_log(String kd_log) {
		this.kd_log = kd_log;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTgl_log() {
		return tgl_log;
	}

	public void setTgl_log(Date tgl_log) {
		this.tgl_log = tgl_log;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
