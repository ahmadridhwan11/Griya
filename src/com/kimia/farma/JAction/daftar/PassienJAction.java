package com.kimia.farma.JAction.daftar;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.kimia.farma.Interface.PassienInterface;
import com.kimia.farma.Interface.UserInterface;
import com.kimia.farma.model.Passien;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;
public class PassienJAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Passien passien;
	private String err="",password;
	private ArrayList<Passien> listPas= new ArrayList<>();
	private String page;
	private PassienInterface pasMapper= (PassienInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("pasMapper");
	private UserInterface userMapper = (UserInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("userMapper");

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
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
		passien = new Passien();
		passien.setKd_passien(buatRumusKode());
		return SUCCESS;

	}
	
	
	public String buatRumusKode(){
		String id = "";
		int x = pasMapper.getCount();
		System.out.println("==========================  ; "+x);
		x++;
		if (x < 10) {
			id += "00000000";
		} else if (x >=10 && x <100) {
			id += "0000000";
		} else if (x>=100 && x <1000) {
			id +="000000";
		} else if(x>=1000 && x<10000){
			id +="00000";
		}else if( x>=10000&& x<100000){
			id +="0000";
		}else if( x>=100000&& x<1000000){
			id +="000";
		}else if( x>=1000000&& x<10000000){
			id +="00";
		}else if( x>=10000000&& x<100000000){
			id +="0";
		}
		passien.setKd_passien("PAS"+id+x);
		Passien p = pasMapper.getPasWhere(passien);
		if(p!=null && p.getNama().length()>0){
			x++;
		}
		return "PAS"+id+x;
	}
	public String savePasien() throws ParseException {
		if (passien != null && passien.getNama().length()>5 && passien.getKd_passien().length()>5&&password.length()>2) {
			try {
				System.out.println("masuk fungs222i===============");
				passien.setKd_passien(buatRumusKode());
				pasMapper.Insert(passien);
				User u = new User();
				u.setKd_user(passien.getKd_passien());
				u.setNama(passien.getNama());
				u.setKelamin(passien.getKelamin());
				u.setTgl_lahir(passien.getTgl_lahir());
				u.setNomer_tlp(passien.getNomer_tlp());
				u.setAlamat(passien.getAlamat());
				u.setPassword(getPassword());
				u.setLevel(1);
				userMapper.insertUser(u);

			} catch (Exception e) {
				e.printStackTrace();
				addActionMessage("PERIKSA KEMBALI FIELD YANG KOSONG");
				return ERROR;
			}
			
			addActionMessage("SELAMAT ANDA BERHASIL MENDAFTAR,SILAH KAN GUNAKAN ID DAN PASSWORD UNTUK LOGIN");
			return SUCCESS;
		}
		addActionMessage("PERIKSA KEMBALI FIELD YANG KOSONG");
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
