package com.kimia.farma.JAction.pasien;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.kimia.farma.Interface.PassienInterface;
import com.kimia.farma.Interface.UserInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Passien;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;
public class PassienJAction extends CoreAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Passien passien;
	private String err="",password;
	private ArrayList<Passien> listPas= new ArrayList<>();
	private String page;
	private User user;
	private PassienInterface pasMapper= (PassienInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("pasMapper");
	private UserInterface userMapper = (UserInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("userMapper");

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String input() {
		setPage("inputPasienfrmp.action");
		//setListSup(supMapper.getSupplier(null));
		return SUCCESS;
	}
	
	public User getSession(User u) {

		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
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
		setUser(getSession(new User()));
		passien = new Passien();
		setPassword(User.decrypt(user.getPassword()));
		passien.setKd_passien(user.getKd_user());
		passien.setNama(user.getNama());
		passien = pasMapper.getPasWhere(passien);
		getListPas().add(passien);
		return SUCCESS;

	}
	
	public String getPasienWhere() {
		passien = pasMapper.getPasWhere(passien);
		getListPas().add(passien);
		return SUCCESS;
	}

	public String initUpdate() {
		setPage("initUpdateFrmp?passien.kd_passien=" +passien.getKd_passien());
		return SUCCESS;
	}

	public String update() {
		if (passien!= null &&passien.getKd_passien().length()>5 && passien.getNama().length()>4) {
			try {
				pasMapper.Update(passien);
				if(getPassword().length()>1){
					user.setPassword(getPassword());
					user.setNama(passien.getNama());
					user.setKelamin(passien.getKelamin());
					user.setNomer_tlp(passien.getNomer_tlp());
					user.setTgl_lahir(passien.getTgl_lahir());
					user.setKd_user(passien.getKd_passien());
					userMapper.UpdateUser(user);	
				}
				setPassien(null);
			} catch (Exception e) {
				e.printStackTrace();
				setPage("inputPasienfrmp.action?err=gagal");
				return ERROR;
			}
			setPage("inputPasienfrmp.action?err=suc1");
			return SUCCESS;
		}
		setPage("inputPasienfrmp.action?err=input");
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
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getErr() {
		return err;
	}
}
