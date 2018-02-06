package com.kimia.farma.JAction;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.kimia.farma.Interface.PassienInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Passien;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class PassienJAction extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Passien passien;
	private String err = "";
	private User u;
	private ArrayList<Passien> listPas = new ArrayList<>();
	private String page;
	private PassienInterface pasMapper = (PassienInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("pasMapper");

	public User getSession(User u) {
		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputPasienfrm.action");
			// setListSup(supMapper.getSupplier(null));
		}
		return SUCCESS;
	}

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				System.out.println("============================================ okeeeee");
				setListPas(pasMapper.getPassien(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			System.out.println("===============================================   oke 4");
			setListPas(pasMapper.getPassien(null));
			return ERROR;
		}
		setPage("inputPasienfrm.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public String delete() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (passien != null && passien.getKd_passien() != null && passien.getNama().length() > 4) {
				try {
					pasMapper.Delete(passien.getKd_passien());
					setPage("inputPasienfrm.action?err=sc2");
				} catch (Exception e) {
					setPage("inputPasienfrm.action?err=err1");
				}
			}
		}
		return SUCCESS;
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
			setListPas(pasMapper.getPassien(null));
			passien = new Passien();
			passien.setKd_passien(buatRumusKode());
		}
		return SUCCESS;

	}

	public String getPasienWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			passien = pasMapper.getPasWhere(passien);
			setListPas(pasMapper.getPassien(null));
		}
		return SUCCESS;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?passien.kd_passien=" + passien.getKd_passien());
		}
		return SUCCESS;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (passien != null && passien.getKd_passien().length() > 5 && passien.getNama().length() > 4) {
				try {
					pasMapper.Update(passien);
					setPassien(null);
				} catch (Exception e) {
					e.printStackTrace();
					setPage("inputPasienfrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputPasienfrm.action?err=suc1");
				return SUCCESS;
			}
			setPage("inputPasienfrm.action?err=input");
		}
		return INPUT;
	}

	public String buatRumusKode() {
		String id = "";
		int y = 0;
		int x = pasMapper.getCount();
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
		passien.setKd_passien("PAS" + id + x);
		Passien p = pasMapper.getPasWhere(passien);
		if (p != null && p.getNama().length() > 0) {
			x++;
		}
		return "PAS" + id + x;
	}

	public String savePasien() throws ParseException {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (passien != null && passien.getNama().length() > 5 && passien.getKd_passien().length() > 5) {
				try {
					System.out.println("masuk fungs222i===============");
					pasMapper.Insert(passien);
					setPassien(null);

				} catch (Exception e) {
					e.printStackTrace();
					setPage("inputPasienfrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputPasienfrm.action?err=succes");
				return SUCCESS;
			}
			setPage("inputPasienfrm.action?err=input");
		}
		return INPUT;
	}

	public Passien getPassien() {
		return passien;
	}

	public void setPassien(Passien passien) {
		this.passien = passien;
	}

	public ArrayList<Passien> getListPas() {
		return listPas;
	}

	public void setListPas(ArrayList<Passien> listPas) {
		this.listPas = listPas;
	}

	public PassienInterface getPasMapper() {
		return pasMapper;
	}

	public void setPasMapper(PassienInterface pasMapper) {
		this.pasMapper = pasMapper;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getErr() {
		return err;
	}
}
