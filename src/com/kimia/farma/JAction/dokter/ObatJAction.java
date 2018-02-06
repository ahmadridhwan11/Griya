package com.kimia.farma.JAction.dokter;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.ObatInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Obat;
import com.kimia.farma.util.MyUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ObatJAction extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean safe = false;
	private String page = "",err="";
	private ArrayList<Obat> listObat = new ArrayList<>();
	private MyUtil myUtil = new MyUtil();
	private ObatInterface obatMapper = (ObatInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("obatMapper");
	
	public String input() {
		setPage("inputObatfrm.action");
		return SUCCESS;
	}
	

	public String initFrm() {
		switch (getErr()) {
			case "input": 	addActionMessage("Data harus di isidengan benar");	break;
			case "gagal":addActionMessage("Gagal Menyimpan data"); break;
			case "gagal1":addActionMessage("Gagal Mengubah data"); break;
			case "succes" :addActionMessage("Berhasil menyimpan data"); break;
			case "suc1" :addActionMessage("Berhasil mengubah data"); break;
			case "err1" :addActionMessage("gagal menghapus data"); break;
			case "sc2" :addActionMessage("berhasilmenghapus data"); break;
			case "" :addActionMessage(""); break;
			default: addActionMessage("Terjadi keselahan");break;
		}
		setListObat(obatMapper.getAllObat(null));
		return SUCCESS;

	}



	public void setListObat(ArrayList<Obat> listObat) {
		this.listObat = listObat;
	}

	public List<Obat> getListObat() {
		return listObat;
	}

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
}
