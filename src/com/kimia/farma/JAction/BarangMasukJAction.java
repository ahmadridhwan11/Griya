package com.kimia.farma.JAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.BarangMasukInterface;
import com.kimia.farma.Interface.ObatInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.BarangMasuk;
import com.kimia.farma.model.Obat;
import com.kimia.farma.model.Supplier;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class BarangMasukJAction extends CoreAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarangMasuk barangMasuk, bm;
	private String err = "";
	private User u;
	private ArrayList<BarangMasuk> listBarang = new ArrayList<>();
	private ArrayList<Obat> listObat = new ArrayList<>();
	private String page, pagging;
	private ArrayList<Supplier> listSup = new ArrayList<>();
	private ObatInterface obatMapper = (ObatInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("obatMapper");
	private BarangMasukInterface barangMapper = (BarangMasukInterface) new ClassPathXmlApplicationContext(
			"beans-config.xml").getBean("barangMapper");

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputBMfrm.action");
			// setListBarang(barangMapper.getAllBarangMasuk(null));
		}
		return SUCCESS;
	}

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		System.out.println("=================================\n\n" + data.length);
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				System.out.println("============================================ okeeeee");
				setListBarang(barangMapper.getAllBarangMasuk(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			System.out.println("===============================================   oke 4");
			setListBarang(barangMapper.getAllBarangMasuk(null));
			return ERROR;
		}
		setPage("inputBMfrm.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public String ajax() {
		setListSup(barangMapper.getSup());
		return SUCCESS;
	}

	public String delete() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			try {
				barangMapper.deleteBranagMasuk(barangMasuk.getKd_masuk());
				setPage("inputDokterfrm.action?err=sc2");
			} catch (Exception e) {
				setPage("inputDokterfrm.action?err=err1");
			}
		}
		return SUCCESS;
	}

	public User getSession(User u) {

		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String initFrm() {

		u = getSession(new User());
		if (u.getLevel() == 2) {
			switch (getErr()) {
			case "input":
				addActionMessage("Data harus di isidengan benar");
				break;
			case "gagal":
				addActionMessage("Gagal Menyimpan data");
				break;
			case "gagal1":
				addActionMessage("Gagal Mengubah data");
				break;
			case "succes":
				addActionMessage("Berhasil menyimpan data");
				break;
			case "suc1":
				addActionMessage("Berhasil mengubah data");
				break;
			case "err1":
				addActionMessage("gagal menghapus data");
				break;
			case "sc2":
				addActionMessage("berhasilmenghapus data");
				break;
			case "":
				addActionMessage("");
				break;
			default:
				addActionMessage("Terjadi keselahan");
				break;
			}
			setListObat(obatMapper.getAllObat(null));
			setListBarang(barangMapper.getAllBarangMasuk(null));
			setListSup(barangMapper.getSup());
			barangMasuk = new BarangMasuk();
			barangMasuk.setTgl_masuk(new Date());
			barangMasuk.setKd_masuk(buatRumusKode());
			return SUCCESS;
		}
		return ERROR;

	}

	public String getBmWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			System.out.println("sebelum ============\n " + barangMasuk.getKd_masuk() + "\n==============");
			barangMasuk = barangMapper.getBarangMasuk(barangMasuk);
			System.out.println("sesudah ============\n " + barangMasuk.getObat().getStok() + "\n==============\n"
					+ barangMasuk.getQty());
			setListObat(obatMapper.getAllObat(null));
			setListBarang(barangMapper.getAllBarangMasuk(null));
			setListSup(barangMapper.getSup());
			return SUCCESS;
		}
		return ERROR;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?barangMasuk.kd_masuk=" + barangMasuk.getKd_masuk());
			return SUCCESS;
		}
		return ERROR;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (barangMasuk != null && barangMasuk.getObat() != null && barangMasuk.getSupplier() != null) {
				try {
					System.out.println("========================================\n " + barangMasuk.getObat().getStok()
							+ " " + barangMasuk.getQty() + "=====================\n================"
							+ bm.getObat().getKd_obat() + "  " + bm.getObat().getStok());

					int q = 0;
					if (!bm.getObat().getKd_obat().equalsIgnoreCase(barangMasuk.getObat().getKd_obat())) {
						q = barangMasuk.getObat().getStok() - barangMasuk.getQty();
						barangMasuk.getObat().setStok(q);
						obatMapper.UpdateStock(barangMasuk.getObat());

						q = bm.getQty() + bm.getObat().getStok();
						bm.getObat().setStok(q);
						obatMapper.UpdateStock(bm.getObat());
						bm.setTgl_expired(barangMasuk.getTgl_expired());
						bm.setTgl_masuk(barangMasuk.getTgl_masuk());
						bm.setKd_masuk(barangMasuk.getKd_masuk());
						bm.setSupplier(barangMasuk.getSupplier());
						barangMapper.updateBarangMasuk(bm);
					} else {
						q = barangMasuk.getObat().getStok() - barangMasuk.getQty();
						q += bm.getQty();
						barangMasuk.getObat().setStok(q);
						obatMapper.UpdateStock(barangMasuk.getObat());
						barangMasuk.setQty(bm.getQty());
						barangMapper.updateBarangMasuk(barangMasuk);
					}
					setBarangMasuk(null);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("===========================\n\n\n masuk err");

					setPage("inputBMfrm.action?err=gagal1");
					return ERROR;
				}
				System.out.println("===========================\n\n\n masuk succ");
				setPage("inputBMfrm.action?err=suc1");
				return SUCCESS;
			}
			System.out.println("===========================\n\n\n masuk input");
			setPage("inputBMfrm.action?err=input");
			return INPUT;
		}
		return ERROR;
	}

	public String buatRumusKode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		String id = "";
		int y = 0;
		int x = barangMapper.getCountBM();
		System.out.println("==========================  ; " + x);
		x++;
		if (x < 9) {
			id += "000" + x;
		} else if (x > 9 && x < 99) {
			id += "00" + x;
		} else if (x >= 100) {
			id += "0" + x;
		} else {
			id += x;
		}
		String d[] = sdf.format(new Date()).split("/"), date = "";
		date += d[0] + d[1] + d[2];
		return "BM" + date + id;
	}

	public String saveBM() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (barangMasuk != null && barangMasuk.getObat().getKd_obat().length() > 5
					&& barangMasuk.getSupplier().getKd_supplier().length() > 4) {
				try {
					System.out.println("==========================\n obat : " + barangMasuk.getObat().getStok()
							+ "\n==============" + barangMasuk.getQty());
					barangMapper.simpanBarangMasuk(barangMasuk);
					int stock = barangMasuk.getObat().getStok() + barangMasuk.getQty();
					barangMasuk.getObat().setStok(stock);
					obatMapper.UpdateStock(barangMasuk.getObat());
					setBarangMasuk(null);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("===========================\n\n\n masuk err");
					setPage("inputBMfrm.action?err=gagal");
					return ERROR;
				}
				System.out.println("===========================\n\n\n masuk succc");

				setPage("inputBMfrm.action?err=succes");
				return SUCCESS;
			}
			System.out.println("===========================\n\n\n masuk input");

			setPage("inputBMfrm.action?err=input");
			return INPUT;
		}
		return ERROR;
	}

	public void setListObat(ArrayList<Obat> listObat) {
		this.listObat = listObat;
	}

	public ArrayList<Obat> getListObat() {
		return listObat;
	}

	public BarangMasuk getBarangMasuk() {
		return barangMasuk;
	}

	public void setBarangMasuk(BarangMasuk barangMasuk) {
		this.barangMasuk = barangMasuk;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public ArrayList<BarangMasuk> getListBarang() {
		return listBarang;
	}

	public void setListBarang(ArrayList<BarangMasuk> listBarang) {
		this.listBarang = listBarang;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public BarangMasukInterface getBarangMapper() {
		return barangMapper;
	}

	public void setBarangMapper(BarangMasukInterface barangMapper) {
		this.barangMapper = barangMapper;
	}

	public void setListSup(ArrayList<Supplier> listSup) {
		this.listSup = listSup;
	}

	public ArrayList<Supplier> getListSup() {
		return listSup;
	}

	public void setPagging(String pagging) {
		this.pagging = pagging;
	}

	public String getPagging() {
		return pagging;
	}

	public void setBm(BarangMasuk bm) {
		this.bm = bm;
	}

	public BarangMasuk getBm() {
		return bm;
	}
}
