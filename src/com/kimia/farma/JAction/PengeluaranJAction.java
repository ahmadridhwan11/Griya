package com.kimia.farma.JAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.ObatInterface;
import com.kimia.farma.Interface.PengeluaranInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Obat;
import com.kimia.farma.model.Pengeluaran;
import com.kimia.farma.model.User;
import com.kimia.farma.util.MyUtil;

public class PengeluaranJAction extends CoreAction{
	private static final long serialVersionUID = 1L;
	private Pengeluaran pengeluaran;
	private boolean safe = false;
	private String page = "", err = "";
	private User u;
	private ArrayList<Pengeluaran> listPengeluaran = new ArrayList<>();
	private MyUtil myUtil = new MyUtil();
	private PengeluaranInterface pengeluaranMapper = (PengeluaranInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("pengeluaranMapper");

	

	public User getSession(User u) {
		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String initSearch() {
		setPage("searchObat.action?err=" + getErr());
		return SUCCESS;
	}

	

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputPengeluaranFrm.action");
		}
		return SUCCESS;
	}

	public String delete() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			try {
				pengeluaranMapper.DeletePengeluaran(pengeluaran.getKd_pengeluaran());
				setPage("inputPengeluaranFrm.action?err=sc2");
			} catch (Exception e) {
				setPage("inputPengeluaranFrm.action?err=err1");
			}
		}
		return SUCCESS;
	}

	public boolean validasi(Obat o) {
		if (myUtil.ValidasiString(o.getKd_obat(), "obat.kd_obat", 8,
				"Tolong isikan sesuai dengan format yg di tentukan.") == 0) {
			if (myUtil.ValidasiString(o.getNama_obat(), "obat.nama_obat", 9, "Masukan nama obat Dengan benar") == 0) {
				if (myUtil.ValidasiString(o.getMerk(), "obat.merk", 9, "Masukan merk dengan benar") == 0) {
					if (myUtil.ValidasiString(o.getType(), "obat.type", 3,
							"Isikan dengan benar type Obat(N/A - GENERIC- etc)") == 0) {
						if (myUtil.ValidasiString(o.getJenis(), "obat.jenis", 4,
								"Isikan Dengan Benas Jenis Obat (KAPLET- DUST-KAPSUL-etc)") == 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
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
			setListPengeluaran(pengeluaranMapper.getAll(null));
			pengeluaran= new Pengeluaran();
			pengeluaran.setDetail("");
			pengeluaran.setTotal(0d);
			pengeluaran.setTgl(new Date());
			pengeluaran.setKd_pengeluaran(buatRumusKode());
		}
		return SUCCESS;

	}

	public String getPengeluaranWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			pengeluaran = pengeluaranMapper.getOne(pengeluaran.getKd_pengeluaran());
			setListPengeluaran(pengeluaranMapper.getAll(null));
		}
		return SUCCESS;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?pengeluaran.kd_pengeluaran=" + pengeluaran.getKd_pengeluaran());
		}
		return SUCCESS;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (pengeluaran!= null && pengeluaran.getDetail().length() > 4) {
				try {
					pengeluaranMapper.update(pengeluaran);
					setPengeluaran(null);
				} catch (Exception e) {
					setPage("inputPengeluaranFrm.action?err=gagal1");
					return ERROR;
				}
				setPage("inputPengeluaranFrm.action?err=suc1");
				return SUCCESS;
			}
			setPage("inputPengeluaranFrm.action?err=input");
		}
		return INPUT;
	}

	public String buatRumusKode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

		String id = "";
		int y = 0;
		int x = pengeluaranMapper.getCount();
		System.out.println("==========================  ; " + x);
		x++;
		if (x >= 1000 ) {
			id +=x;
		} else if (x <9) {
			id += "000" + x;
		} else if (x >= 10 && x < 99) {
			id += "00" + x;
		} else {
			id += "0"+x;
		}
		String d[] = sdf.format(new Date()).split("/"), date = "";
		date += d[0] + d[1] + d[2];
		return "PN" + date + id;
	}

	public String savePengeluaran() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (pengeluaran != null && pengeluaran.getKd_pengeluaran()!= "" && pengeluaran.getDetail().length() > 4
					&& pengeluaran.getTotal() >= 100) {

				try {
					// if (validasi(obat)) {
					pengeluaranMapper.insert(pengeluaran);
					// }else{
					// return INPUT;
					// }
				} catch (Exception e) {
					//setPage("inputPengeluaranFrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputPengeluaranFrm.action?err=succes");
				return SUCCESS;
			}
			setPage("inputPengeluaranFrm.action?err=input");
		}
		return INPUT;
	}

	public void setPengeluaran(Pengeluaran pengeluaran) {
		this.pengeluaran = pengeluaran;
	}
	public void setListPengeluaran(ArrayList<Pengeluaran> listPengeluaran) {
		this.listPengeluaran = listPengeluaran;
	}
	public Pengeluaran getPengeluaran() {
		return pengeluaran;
	}
	public ArrayList<Pengeluaran> getListPengeluaran() {
		return listPengeluaran;
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
