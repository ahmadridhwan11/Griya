package com.kimia.farma.JAction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kimia.farma.Interface.DashBoardInterface;
import com.kimia.farma.Interface.UserInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.DashBoard;
import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.Passien;
import com.kimia.farma.model.Reporting;
import com.kimia.farma.model.User;

public class UserJAction extends CoreAction{
	private User user;
	private Dokter dokter;
	private Passien pasien;
	private DashBoard ds;
	private String page;
	private int level;
	private Map<String, Object> session;
	private UserInterface userMapper = (UserInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("userMapper");
	private DashBoardInterface boardInterface = (DashBoardInterface) new ClassPathXmlApplicationContext("beans-config.xml").getBean("dashMapper");

	/**
	 * @return
	 */
	public String Login(){
		try{
			if(user!=null && user.getLevel()==4){
				return "DAFTAR";
			}else if(user!=null && user.getLevel()==2 && user.getNama().length()>3){
				return "ADMIN";
			}else if(user!=null && user.getLevel()==1 && user.getNama().length()>3){
				return"PASIEN";
			}else if(user!=null && user.getLevel()==3 && user.getNama().length()>3){
				return "DOKTER";
			}
			
			if(user!=null && user.getKd_user().length()>4){
				user = userMapper.Login(user);
			if(user.getNama()!=null && user.getNama().length()>4){
				
				session.put("userName",user);
				if(user.getLevel()==2){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					String d[] = sdf.format(new Date()).split("/");
					d[2]="01";
					ds = new DashBoard();
					Reporting r = new Reporting();
					r.setTgl_awal(sdf.parse(d[0]+"/"+d[1]+"/"+d[2]));
					r.setTgl_akhir(new Date());
					ds.setPendapatan_b(boardInterface.getPendapatan(r));
					ds.setTransaksi_b(boardInterface.getjmlTran(r));
					ds.setTransaksi_obt_b(boardInterface.getJmlTranObt(r));
					r.setTgl_awal(new Date());
					ds.setPendapatan_h(boardInterface.getPendapatan(r));
					ds.setTransaksi_h(boardInterface.getjmlTran(r));
					ds.setTransaksi_obt_h(boardInterface.getJmlTranObt(r));
					
					session.put("dashBoard",ds);
					return "ADMIN";
				}else if(user.getLevel()==3){
					return "DOKTER";
				}else if(user.getLevel()==1){
					return "PASIEN";
				}
				//setPage("dashboard.action");
				System.out.println("\n\n\n\n\noke================================================================\n\n\n\n\n\n\n");
				
			}
			addActionMessage("Kd.User atau Password salah ..");
				return ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			
			addActionMessage("SERVER TERPUTUS");
			return ERROR;
		}
			addActionMessage("kd.User dan password wajib di isi .!!");
			return INPUT;
	}
	
	public String inputHome(){
		setPage("dashboard.action");
		return SUCCESS;
	}
	
	public String LogOut(){
		try{
		session.remove("userName");
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public UserInterface getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserInterface userMapper) {
		this.userMapper = userMapper;
	}
	public void setDs(DashBoard ds) {
		this.ds = ds;
	}
	public DashBoard getDs() {
		return ds;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPage() {
		return page;
	}
	
}
