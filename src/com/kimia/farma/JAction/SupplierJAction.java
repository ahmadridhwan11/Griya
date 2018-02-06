package com.kimia.farma.JAction;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.kimia.farma.Interface.SupplierInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Supplier;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class SupplierJAction extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Supplier supplier;
	private String err = "";
	private ArrayList<Supplier> listSup = new ArrayList<>();
	private String page;
	private User u;
	private SupplierInterface supMapper = (SupplierInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("supMapper");

	public User getSession(User u) {
		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputSupplierfrm.action");
			// setListSup(supMapper.getSupplier(null));
		}
		return SUCCESS;
	}

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		System.out.println("=================================\n\n" + data.length);
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				setListSup(supMapper.getSupplier(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			setListSup(supMapper.getSupplier(null));
			return ERROR;
		}
		setPage("inputSupplierfrm.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public String delete() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			try {
				supMapper.Delete(supplier.getKd_supplier());
				setPage("inputSupplierfrm.action?err=sc2");
			} catch (Exception e) {
				setPage("inputSupplierfrm.action?err=err1");
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
			setListSup(supMapper.getSupplier(null));
			supplier = new Supplier();
			supplier.setKd_supplier(buatRumusKode());
		}
		return SUCCESS;

	}

	public String getSupplierWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			supplier = supMapper.getSupWhere(supplier.getKd_supplier());
			setListSup(supMapper.getSupplier(null));
			System.out.println("======================== " + supplier.getNama());
		}
		return SUCCESS;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?supplier.kd_supplier=" + supplier.getKd_supplier());
		}
		return SUCCESS;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (supplier != null && supplier.getNama().length() > 5) {
				System.out.println("========================================\n " + supplier.getNama());
				try {
					supMapper.Update(supplier);
					setSupplier(null);
				} catch (Exception e) {
					e.printStackTrace();
					setPage("inputSupplierfrm.action?err=gagal1");
					return ERROR;
				}
				setPage("inputSupplierfrm.action?err=suc1");
				return SUCCESS;
			}
			setPage("inputSupplierfrm.action?err=input");
		}
		return INPUT;
	}

	public String buatRumusKode() {
		String id = "";
		int x = supMapper.getCount();
		System.out.println("==========================  ; " + x);
		x++;
		if (x < 10) {
			id = "0000";
		} else if (x >= 10 && x < 99) {
			id = "000";
		} else if (x >= 100 && x < 999) {
			id = "00";
		} else if (x >= 1000 && x < 9999) {
			id = "0";
		}
		Supplier s = getSupMapper().getSupWhere("SUP" + id + x);
		if (s != null && s.getNama().length() > 0) {
			x++;
		}
		id += x;
		return "SUP" + id;
	}

	public String saveSupplier() throws ParseException {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			supplier.setKd_supplier(buatRumusKode());
			if (supplier != null && supplier.getNama().length() > 5 && supplier.getKd_supplier().length() > 4) {
				try {

					supMapper.Insert(supplier);
					setSupplier(null);
				} catch (Exception e) {
					e.printStackTrace();
					setPage("inputSupplierfrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputSupplierfrm.action?err=succes");
				return SUCCESS;
			}
			setPage("inputSupplierfrm.action?err=input");
		}
		return INPUT;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ArrayList<Supplier> getListSup() {
		return listSup;
	}

	public void setListSup(ArrayList<Supplier> listSup) {
		this.listSup = listSup;
	}

	public SupplierInterface getSupMapper() {
		return supMapper;
	}

	public void setSupMapper(SupplierInterface supMapper) {
		this.supMapper = supMapper;
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
