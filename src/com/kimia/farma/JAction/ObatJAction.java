package com.kimia.farma.JAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.ObatInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.Obat;
import com.kimia.farma.model.User;
import com.kimia.farma.util.MyUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ObatJAction extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Obat obat;
	private boolean safe = false;
	private String page = "", err = "";
	private User u;
	private ArrayList<Obat> listObat = new ArrayList<>();
	private MyUtil myUtil = new MyUtil();
	private ObatInterface obatMapper = (ObatInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("obatMapper");

	public String search() {
		if (getErr().trim().length() > 0) {
			setListObat(obatMapper.getObat(getErr()));
		} else {
			setListObat(obatMapper.getAllObat(null));
		}
		return SUCCESS;
	}

	public User getSession(User u) {
		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String initSearch() {
		setPage("searchObat.action?err=" + getErr());
		return SUCCESS;
	}

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				System.out.println("============================================ okeeeee");
				setListObat(obatMapper.getAllObat(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			System.out.println("===============================================   oke 4");
			setListObat(obatMapper.getAllObat(null));
			return ERROR;
		}
		setPage("inputObatfrm.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputObatfrm.action");
		}
		return SUCCESS;
	}

	public String delete() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			try {
				obatMapper.DeleteObat(obat.getKd_obat());
				setPage("inputObatfrm.action?err=sc2");
			} catch (Exception e) {
				setPage("inputObatfrm.action?err=err1");
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
			setListObat(obatMapper.getAllObat(null));
			obat = new Obat();
			obat.setHarga(null);
			obat.setKd_obat(buatRumusKode());
		}
		return SUCCESS;

	}

	public String getObatWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			obat = obatMapper.getOneObat(obat.getKd_obat());
			System.out.println("======================= \ndata : " + obat.getNama_obat());
			setListObat(obatMapper.getAllObat(null));
		}
		return SUCCESS;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?obat.kd_obat=" + obat.getKd_obat());
		}
		return SUCCESS;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (obat != null && obat.getNama_obat().length() > 4) {
				try {
					obatMapper.UpdateObat(obat);
					setObat(null);
				} catch (Exception e) {
					setPage("inputObatfrm.action?err=gagal1");
					return ERROR;
				}
				setPage("inputObatfrm.action?err=suc1");
				return SUCCESS;
			}
			setPage("inputObatfrm.action?err=input");
		}
		return INPUT;
	}

	public String buatRumusKode() {
		String id = "";
		int x = obatMapper.getCount();
		System.out.println("==========================  ; " + x);
		x++;
		if (x < 10) {
			id += "00000000";
		} else if (x >= 10 && x < 100) {
			id += "0000000";
		} else if (x >= 100 && x < 1000) {
			id += "000000";
		} else if (x >= 1000 && x < 10000) {
			id += "00000";
		} else if (x >= 10000 && x < 100000) {
			id += "0000";
		} else if (x >= 100000 && x < 1000000) {
			id += "000";
		} else if (x >= 1000000 && x < 10000000) {
			id += "00";
		} else if (x >= 10000000 && x < 100000000) {
			id += "0";
		}
		Obat ob = obatMapper.getOneObat("OBT" + id + x);
		if (ob != null && ob.getNama_obat().length() > 0) {
			x++;
		}
		return "OBT" + id + x;
	}

	public String saveObat() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (obat != null && obat.getKd_obat() != "" && obat.getNama_obat().length() > 4
					&& obat.getJenis().length() >= 1) {
				System.out.println("oke========================================");
				System.out.println("dataaaaa ===== " + obat.getKd_obat());
				try {
					// if (validasi(obat)) {
					obatMapper.InsertObat(obat);
					// }else{
					// return INPUT;
					// }
				} catch (Exception e) {
					setPage("inputObatfrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputObatfrm.action?err=succes");
				return SUCCESS;
			}
			setPage("inputObatfrm.action?err=input");
		}
		return INPUT;
	}

	public void setListObat(ArrayList<Obat> listObat) {
		this.listObat = listObat;
	}

	public List<Obat> getListObat() {
		return listObat;
	}

	public void setObat(Obat obat) {
		this.obat = obat;
	}

	public Obat getObat() {
		return obat;
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
