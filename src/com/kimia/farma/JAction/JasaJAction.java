package com.kimia.farma.JAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.DokterInterface;
import com.kimia.farma.Interface.JasaInterface;
import com.kimia.farma.Interface.ObatInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.Jasa;
import com.kimia.farma.model.Obat;
import com.kimia.farma.model.User;
import com.kimia.farma.util.MyUtil;
import com.opensymphony.xwork2.ActionSupport;

public class JasaJAction extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jasa jasa;
	private boolean safe = false;
	private String page = "", err = "";
	private User u;
	private ArrayList<Jasa> listJasa = new ArrayList<>();
	private ArrayList<Dokter> listDokter = new ArrayList<>();
	private MyUtil myUtil = new MyUtil();
	private JasaInterface jasaInterface = (JasaInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("jasaMapper");
	private DokterInterface dokterInterface = (DokterInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("dokterMapper");

	public User getSession(User u) {
		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				System.out.println("============================================ okeeeee");
				setListJasa(jasaInterface.getAllJasa(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			System.out.println("===============================================   oke 4");
			setListJasa(jasaInterface.getAllJasa(null));
			return ERROR;
		}
		setPage("inputJasafrm.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputJasafrm.action");
		}
		return SUCCESS;
	}

	public String delete() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			try {
				jasaInterface.DeleteJasa(jasa.getKd_jasa());
				setPage("inputJasafrm.action?err=sc2");
			} catch (Exception e) {
				setPage("inputJasafrm.action?err=err1");
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
			jasa = new Jasa();
			jasa.setKd_jasa(buatRumusKode());
			setListJasa(jasaInterface.getAllJasa(null));
			setListDokter(dokterInterface.getDokter(null));
		}
		return SUCCESS;

	}

	public String getJasaWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			jasa = jasaInterface.getOneJasa(jasa);
			setListJasa(jasaInterface.getAllJasa(null));
			setListDokter(dokterInterface.getDokter(null));
		}
		return SUCCESS;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?jasa.kd_jasa=" + jasa.getKd_jasa());
		}
		return SUCCESS;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (jasa != null && jasa.getNama().length() > 4 && jasa.getDokter().getNama().length() > 1) {
				try {
					jasaInterface.UpdateJasa(jasa);
					setJasa(null);
				} catch (Exception e) {

					setPage("inputJasafrm.action?err=gagal1");
					return ERROR;
				}
				setPage("inputJasafrm.action?err=suc1");
				return SUCCESS;
			}
		}
		setPage("inputJasafrm.action?err=input");
		return INPUT;
	}

	public String buatRumusKode() {
		String id = "";
		int y = 0;
		int x = jasaInterface.getCount();
		++x;
		if (x > 0 && x < 10) {
			id = "0000";
		} else if (x >= 10 && x < 100) {
			id = "000";
		} else if (x >= 100 && x < 1000) {
			id = "00";
		} else if (x >= 1000 && x < 10000) {
			id = "0";
		}
		jasa.setKd_jasa("JSA" + id + x);
		Jasa jj = jasaInterface.getOneJasa(jasa);
		String h = id;
		if (jj != null && jj.getNama().length() > 0) {
			x++;
			h = "";
			for (int i = 0; i < id.length() - 1; i++) {
				h += "0";
			}
		}
		return "JSA" + h + x;
	}

	public String saveJasa() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (jasa != null && jasa.getKd_jasa() != "" && jasa.getNama().length() > 4
					&& jasa.getDokter().getNama().length() > 1) {
				try {
					System.out.println("================================\n" + jasa.getDokter().getNama());
					// if (validasi(obat)) {
					jasaInterface.InsertJasa(jasa);
					// }else{
					// return INPUT;
					// }
				} catch (Exception e) {
					setPage("inputJasafrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputJasafrm.action?err=succes");
				return SUCCESS;
			}
			setPage("inputJasafrm.action?err=input");
		}
		return INPUT;
	}

	public Jasa getJasa() {
		return jasa;
	}

	public void setJasa(Jasa jasa) {
		this.jasa = jasa;
	}

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public ArrayList<Jasa> getListJasa() {
		return listJasa;
	}

	public void setListJasa(ArrayList<Jasa> listJasa) {
		this.listJasa = listJasa;
	}

	public ArrayList<Dokter> getListDokter() {
		return listDokter;
	}

	public void setListDokter(ArrayList<Dokter> listDokter) {
		this.listDokter = listDokter;
	}

	public MyUtil getMyUtil() {
		return myUtil;
	}

	public void setMyUtil(MyUtil myUtil) {
		this.myUtil = myUtil;
	}

	public JasaInterface getJasaInterface() {
		return jasaInterface;
	}

	public void setJasaInterface(JasaInterface jasaInterface) {
		this.jasaInterface = jasaInterface;
	}

	public DokterInterface getDokterInterface() {
		return dokterInterface;
	}

	public void setDokterInterface(DokterInterface dokterInterface) {
		this.dokterInterface = dokterInterface;
	}

}
