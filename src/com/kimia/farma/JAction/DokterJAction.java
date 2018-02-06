package com.kimia.farma.JAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.DokterInterface;
import com.kimia.farma.Interface.UserInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class DokterJAction extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dokter dokter;
	private String err = "";
	private ArrayList<Dokter> listDokter = new ArrayList<>();
	private String page;
	private User u;
	private UserInterface userMapper = (UserInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("userMapper");
	private DokterInterface dokterMapper = (DokterInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("dokterMapper");

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		System.out.println("=================================\n\n" + data.length);
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				System.out.println("============================================ okeeeee");
				setListDokter(dokterMapper.getDokter(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			System.out.println("===============================================   oke 4");
			setListDokter(dokterMapper.getDokter(null));
			return ERROR;
		}
		setPage("inputDokterfrm.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public User getSession(User u) {

		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("inputDokterfrm.action");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			dokterMapper.Delete(dokter.getKd_dokter());
			setPage("inputDokterfrm.action?err=sc2");
		} catch (Exception e) {
			setPage("inputDokterfrm.action?err=err1");
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
			setListDokter(dokterMapper.getDokter(null));
			setDokter(null);
		}
		return SUCCESS;

	}

	public String getDokterWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			dokter = dokterMapper.getDokterWhere(dokter);
			setListDokter(dokterMapper.getDokter(null));
		}
		return SUCCESS;
	}

	public String initUpdate() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setPage("initUpdateFrm?dokter.kd_dokter=" + dokter.getKd_dokter());
		}
		return SUCCESS;
	}

	public String update() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (dokter != null && dokter.getNama().length() > 5 && dokter.getTgl_lahir() != null) {
				System.out.println(
						"========================================\n " + dokter.getNama() + " " + dokter.getKd_dokter());
				try {
					dokterMapper.Update(dokter);
					User u = new User();
					u.setKd_user(dokter.getKd_dokter());
					SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
					String pas[] = sdf.format(dokter.getTgl_lahir()).split("/");
					String pas2 = pas[1] + pas[2] + pas[0];
					u.setPassword(pas2);
					u.setNama(dokter.getNama());
					u.setNomer_tlp(dokter.getNomer_tlp());
					u.setAlamat(dokter.getAlamat());
					u.setTgl_lahir(dokter.getTgl_lahir());
					u.setKelamin(dokter.getKelamin());
					userMapper.UpdateUser(u);
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
		}
		return INPUT;
	}

	public String buatRumusKode(String data, String jenis) {
		System.out.println("================\n Sebelum Split");
		String[] temp = data.split("/");
		System.out.println("================\n sesudah Sebelum Split");

		temp[0] += "/" + jenis + "/" + temp[2];
		return temp[0];
	}

	public String saveDokter() throws ParseException {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (dokter != null && dokter.getNama().length() > 5 && dokter.getTgl_lahir() != null) {
				try {
					System.out.println("masuk fungsi===============\n\n\n" + dokter.getKelamin() + " "
							+ dokter.getSpesialis() + "  ini " + dokter.getTgl_lahir());

					String id = "";
					int x = dokterMapper.getCount();
					System.out.println("==========================  ; " + x);
					x++;
					if (x < 10) {
						id += "00";
					} else if (x >= 10 && x < 99) {
						id += "0";
					}
					String kd = "DOK/XX/" + id + x;
					dokter.setKd_dokter(buatRumusKode(kd, dokter.getSpesialis()));
					Dokter d = dokterMapper.getDokterWhere(dokter);
					if (d != null && d.getNama().length() > 0) {
						x++;
					}
					kd = buatRumusKode("DOK/XX/" + id + x, dokter.getSpesialis());
					dokter.setKd_dokter(kd);
					System.out.println("masuk fungs222i===============");
					System.out.println("====" + dokter.getSpesialis() + "   " + dokter.getKd_dokter());
					// if (validasi(obat)) {
					dokterMapper.Insert(dokter);
					User u = new User();
					u.setKd_user(dokter.getKd_dokter());
					SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
					String pas[] = sdf.format(dokter.getTgl_lahir()).split("/");
					String pas2 = pas[1] + pas[2] + pas[0];
					u.setPassword(pas2);
					u.setNama(dokter.getNama());
					u.setNomer_tlp(dokter.getNomer_tlp());
					u.setLevel(3);
					u.setAlamat(dokter.getAlamat());
					u.setTgl_lahir(dokter.getTgl_lahir());
					u.setKelamin(dokter.getKelamin());
					userMapper.insertUser(u);
					// }else{
					// return INPUT;
					// }
				} catch (Exception e) {
					e.printStackTrace();
					setPage("inputDokterfrm.action?err=gagal");
					return ERROR;
				}
				setPage("inputDokterfrm.action?err=succes");
				return SUCCESS;
			}
			setPage("inputDokterfrm.action?err=input");
		}
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

	public void setUserMapper(UserInterface userMapper) {
		this.userMapper = userMapper;
	}

	public UserInterface getUserMapper() {
		return userMapper;
	}
}
