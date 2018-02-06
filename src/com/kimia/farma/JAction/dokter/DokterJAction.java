package com.kimia.farma.JAction.dokter;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.DokterInterface;
import com.kimia.farma.Interface.UserInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class DokterJAction extends CoreAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dokter dokter;
	private String err="",pass;
	private ArrayList<Dokter> listDokter= new ArrayList<>();
	private String page;
	private User user;
	private UserInterface userMapper = (UserInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("userMapper");
	private DokterInterface dokterMapper= (DokterInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("dokterMapper");
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass() {
		return pass;
	}
	public User getSession(User u) {

		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}
	public String input() {
		setPage("inputDokterfrm.action");
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
		setUser(getSession(new User()));
		dokter = new Dokter();
		setPass(User.decrypt(user.getPassword()));
		dokter.setKd_dokter(getUser().getKd_user());
		dokter.setNama(getUser().getNama());
		dokter=dokterMapper.getDokterWhere(dokter);
		getListDokter().add(getDokter());
		return SUCCESS;

	}

	public String getDokterWhere() {
		dokter = dokterMapper.getDokterWhere(dokter);
		getListDokter().add(getDokter());
		return SUCCESS;
	}

	public String initUpdate() {
		setPage("initUpdateFrm?dokter.kd_dokter=" +dokter.getKd_dokter());
		return SUCCESS;
	}

	public String update() {
		
		if (dokter!= null && dokter.getNama().length()>5 && dokter.getTgl_lahir()!=null) {
			try {
				dokterMapper.Update(dokter);
				if(getPass().length()>1){
					user.setPassword(getPass());
					user.setNama(dokter.getNama());
					user.setKelamin(dokter.getKelamin());
					user.setNomer_tlp(dokter.getNomer_tlp());
					user.setTgl_lahir(dokter.getTgl_lahir());
					user.setKd_user(dokter.getKd_dokter());
					userMapper.UpdateUser(user);	
				}
				setDokter(null);
			} catch (Exception e) {
				e.printStackTrace();
				setPage("inputDokterfrm.action?err=gagal1");
				return ERROR;
			}
			setPage("inputDokterfrm.action?err=suc1");
			return SUCCESS;
		}
		setPage("inputDokterfrm.action?err=input");
		return INPUT;
	}

	
	public Dokter getDokter() {
		return dokter;
	}
	public void setDokter(Dokter dokter) {
		this.dokter = dokter;
	}
	public ArrayList<Dokter> getListDokter() {
		return listDokter;
	}
	public void setListDokter(ArrayList<Dokter> listDokter) {
		this.listDokter = listDokter;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public DokterInterface getDokterMapper() {
		return dokterMapper;
	}
	public void setDokterMapper(DokterInterface dokterMapper) {
		this.dokterMapper = dokterMapper;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getErr() {
		return err;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUserMapper(UserInterface userMapper) {
		this.userMapper = userMapper;
	}
	public UserInterface getUserMapper() {
		return userMapper;
	}
}
