package com.kimia.farma.JAction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcraft.jsch.Session;
import com.kimia.farma.Interface.BarangMasukInterface;
import com.kimia.farma.Interface.DokterInterface;
import com.kimia.farma.Interface.JasaInterface;
import com.kimia.farma.Interface.ObatInterface;
import com.kimia.farma.Interface.PassienInterface;
import com.kimia.farma.Interface.TransaksiInterface;
import com.kimia.farma.core.action.CoreAction;
import com.kimia.farma.model.BarangMasuk;
import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.Jasa;
import com.kimia.farma.model.Obat;
import com.kimia.farma.model.Passien;
import com.kimia.farma.model.ReportDokter;
import com.kimia.farma.model.ReportTM;
import com.kimia.farma.model.Supplier;
import com.kimia.farma.model.Transaksi;
import com.kimia.farma.model.Transaksi_detail;
import com.kimia.farma.model.User;
import com.kimia.farma.model.Reporting;
import com.opensymphony.xwork2.ActionSupport;

public class TransaksiJAction extends CoreAction {

	private static final long serialVersionUID = 1L;

	private Transaksi transaksi, transaksi2;
	private Reporting reporting;
	private ArrayList<Transaksi_detail> transaksi_detail;
	private Obat obat;
	private String err = "",kd_dokter1;
	private InputStream inputStream;
	private String namaFile = "", tgl_awal, tgl_akhir;
	private ArrayList<Obat> listObat = new ArrayList<>();
	private String page, pagging;
	private ArrayList<Jasa> listJ = new ArrayList<>();
	private ArrayList<Transaksi> listTransaksi = new ArrayList<>();
	private ArrayList<Passien> listPassien = new ArrayList<>();
	private ArrayList<BarangMasuk> listBarang = new ArrayList<>();
	private ArrayList<ReportTM> reportTm = new ArrayList<>();
	private ArrayList<ReportDokter> reportDokter = new ArrayList<>();
	private User u;
	private BarangMasukInterface barangMapper = (BarangMasukInterface) new ClassPathXmlApplicationContext(
			"beans-config.xml").getBean("barangMapper");

	private ObatInterface obatMapper = (ObatInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("obatMapper");
	private PassienInterface pasMapper = (PassienInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("pasMapper");

	private TransaksiInterface transaksiMapper = (TransaksiInterface) new ClassPathXmlApplicationContext(
			"beans-config.xml").getBean("trMapper");
	private JasaInterface jasaInterface = (JasaInterface) new ClassPathXmlApplicationContext("beans-config.xml")
			.getBean("jasaMapper");

	public String history() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setListTransaksi(transaksiMapper.getTransaksiHistory(null));
			reporting = new Reporting();
		}
		return SUCCESS;
	}

	public String initLaporan() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			
			reporting = new Reporting();
		}
		setListJ(jasaInterface.getAllJasa(null));
		return SUCCESS;
	}

	public String detailHistory() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setTransaksi(transaksiMapper.getTransaksiDetail(transaksi));
		}
		return SUCCESS;
	}

	public String input() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (getPage() != null && getPage().equalsIgnoreCase("his")) {
				setPage("history.action");
			} else if (getPage() != null && getPage().equalsIgnoreCase("hisDetail")) {
				setPage("hisDetail.action?transaksi.kd_transaksi=" + transaksi.getKd_transaksi());
			} else if (getPage() != null && getPage().equalsIgnoreCase("lap")) {
				setPage("laporan.action");
			} else {
				setPage("inputTRfrm.action");
			}
		}
		return SUCCESS;
	}

	public User getSession(User u) {

		u = (User) ServletActionContext.getRequest().getSession().getAttribute("userName");
		return u;
	}

	public String pagging() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] data = ((HttpServletRequest) request).getQueryString().split("-");
		System.out.println("=================================\n\n" + data.length);
		if (data.length == 3) {
			if (data[2].startsWith("e")) {
				System.out.println("============================================ okeeeee");
				setListTransaksi(transaksiMapper.getTransaksiAll(null));
				return ERROR;
			}
		} else if (data.length == 5) {
			System.out.println("===============================================   oke 4");
			setListTransaksi(transaksiMapper.getTransaksiAll(null));
			return ERROR;
		}
		setPage("history.action?" + ((HttpServletRequest) request).getQueryString());
		return SUCCESS;
	}

	public String pagging2() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			setTransaksi(transaksiMapper.getTransaksiDetail(transaksi));
		}
		return ERROR;
	}

	public String ajaxObat() {
		System.out.println("okeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		obat = obatMapper.getOneObat(obat.getKd_obat());
		System.out.println("ajaxxx/n======================/n " + obat.getKd_obat() + " " + obat.getNama_obat() + "   "
				+ obat.getMerk());
		try {
			Transaksi_detail td = new Transaksi_detail();
			td.setObat(obat);
			td.setJml_beli(9);
			td.setSub_total(9000d);
			td.setJasa(new Jasa());
			transaksi = new Transaksi();
			transaksi.getTransaksi_detail().add(td);
			System.out.println("============================\n" + transaksi.getTransaksi_detail().size());
		} catch (Exception e) {
			e.printStackTrace();
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
				addActionMessage("Transaksi Berhasil .!");
				break;
			case "suc1":
				addActionMessage("Berhasil mengubah data");
				break;
			case "":
				addActionMessage("");
				break;
			default:
				addActionMessage("Terjadi keselahan");
				break;
			}
			setListObat(obatMapper.getAllObat(null));
			setListPassien(pasMapper.getPassien(null));
			setListJ(jasaInterface.getAllJasa(null));

			transaksi = new Transaksi();
			transaksi_detail = new ArrayList<>();
			transaksi.setTgl_transaksi(new Date());
			transaksi.setKd_transaksi(buatRumusKode());
		}
		return SUCCESS;
	}

	public String getTrWhere() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			transaksi = transaksiMapper.getTransaksi(transaksi);
			setListObat(obatMapper.getAllObat(null));
			setListPassien(pasMapper.getPassien(null));
		}
		return SUCCESS;
	}

	public String initUpdate() {
		setPage("initUpdateFrm?transaksi.kd_transaksi=" + transaksi.getKd_transaksi());
		return SUCCESS;
	}

	public String update() {
		if (transaksi != null && transaksi.getTransaksi_detail() != null && transaksi.getPassien() != null) {
			try {
				int q = 0, i = 0;
				if (transaksi.getTransaksi_detail().size() > 0) {
					for (Transaksi_detail td : transaksi.getTransaksi_detail()) {
						Transaksi_detail td2 = transaksi2.getTransaksi_detail().get(i);
						if (!td.getObat().getKd_obat().equalsIgnoreCase(td2.getObat().getKd_obat())) {
							q = td.getObat().getStok() - td.getJml_beli();
							td.getObat().setStok(q);
							obatMapper.UpdateStock(td.getObat());

							q = td2.getJml_beli();
							q += td2.getObat().getStok();
							td2.getObat().setStok(q);
							obatMapper.UpdateStock(td2.getObat());
							transaksiMapper.updateTransaksi(transaksi2);
							transaksiMapper.updateDetail(transaksi2);
						} else {
							q = td.getObat().getStok() - td.getJml_beli();
							q += td2.getJml_beli();
							td2.getObat().setStok(q);
							obatMapper.UpdateObat(td2.getObat());
							transaksiMapper.updateTransaksi(transaksi2);
							transaksiMapper.updateDetail(transaksi2);
						}

					}
					setTransaksi(null);
					setTransaksi2(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("===========================\n\n\n masuk err");

				setPage("inputTRfrm.action?err=gagal1");
				return ERROR;
			}
			System.out.println("===========================\n\n\n masuk succ");
			setPage("inputTRfrm.action?err=suc1");
			return SUCCESS;
		}
		System.out.println("===========================\n\n\n masuk input");
		setPage("inputTRfrm.action?err=input");
		return INPUT;
	}

	public String buatRumusKode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

		String id = "";
		int y = 0;
		int x = transaksiMapper.getCount();
		System.out.println("==========================  ; " + x);
		x++;
		if (x < 9) {
			id += "000000" + x;
		} else if (x >= 10 && x < 99) {
			id += "00000" + x;
		} else if (x >= 100 && x < 999) {
			id += "0000" + x;
		} else if (x >= 1000 && x < 9999) {
			id += "000" + x;
		} else if (x >= 10000) {
			id += "00" + x;
		} else if (x >= 100000 && x < 99999) {
			id += "0" + x;
		} else {
			id += x;
		}
		String d[] = sdf.format(new Date()).split("/"), date = "";
		date += d[0] + d[1] + d[2];
		return "TR" + date + id;
	}

	public String saveTR() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			if (transaksi != null && transaksi_detail != null && transaksi.getTransaksi_detail() != null
					&& getTransaksi_detail().size() > 0 && transaksi.getPassien() != null) {
				try {

					Double total = 0d;

					for (Transaksi_detail td : getTransaksi_detail()) {
						if (td.getObat() != null && td.getObat().getKd_obat().length() >= 5) {
							int stock = td.getObat().getStok() - td.getJml_beli();
							td.getObat().setStok(stock);
							System.out.println(
									"data dalam for \n============================\n" + td.getObat().getKd_obat() + " "
											+ td.getObat().getStok() + "  " + td.getJml_beli());
							obatMapper.UpdateStock(td.getObat());
						}
						// transaksiMapper.saveTransaksiDetail(transaksi);
					}
					transaksi.setUser(getSession(new User()));
					System.out.println("\n===================================\n\n\n=============\n"
							+ transaksi_detail.get(0).getSub_total());
					transaksi.setTransaksi_detail(getTransaksi_detail());
					transaksiMapper.saveTransaksi(transaksi);
					transaksiMapper.saveTransaksiDetail(transaksi);
					// insert batch heree
					// generate pdf
					setNamaFile(transaksi.getKd_transaksi() + ".pdf");
					print();
					setTransaksi(null);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("===========================\n\n\n masuk err");
					setPage("inputTRfrm.action?err=gagal");
					return ERROR;
				}
				System.out.println("===========================\n\n\n masuk succc");
				/* setPage("inputTRfrm.action?err=succes"); */
				return SUCCESS;
			}
			System.out.println("===========================\n\n\n masuk input");
			setPage("inputTRfrm.action?err=input");
		}
		return INPUT;
	}

	public String print() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			try {
				if (getNamaFile().length() <= 1) {
					setTransaksi(transaksiMapper.getTransaksiDetail(transaksi));
					setNamaFile(transaksi.getKd_transaksi() + ".pdf");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				Document document = new Document();
				PdfWriter.getInstance(document, buffer);
				document.open();
				Paragraph p = new Paragraph();
				ServletContext cx = ServletActionContext.getServletContext();
				String abc = cx.getRealPath("/content/BG/logo2.png");
				Image img = Image.getInstance(abc);
				document.add(img);
				p.add("\n kode-transaksi : " + transaksi.getKd_transaksi());
				p.add("\n tgl-transaksi  : " + sdf.format(transaksi.getTgl_transaksi()));
				if (transaksi.getPassien() != null && transaksi.getPassien().getNama().length() >= 3) {
					p.add("\n passien        : " + transaksi.getPassien().getNama());
				} else {
					p.add("\n passien        : -");
				}
				for (Transaksi_detail td : transaksi.getTransaksi_detail()) {
					System.out.println("masuk report \n\n");
					p.add("\n============================");
					if (td.getObat() != null && td.getObat().getKd_obat().length() >= 5) {

						p.add("\n #nama-obat: " + td.getObat().getNama_obat());
						p.add("\n #jenis    : " + td.getObat().getJenis());
						p.add("\n #harga    : " + td.getObat().getHarga());
						p.add("\n #jml-beli : " + td.getJml_beli());
						p.add("\n #discount : " + td.getDsc_obat() + "%");
					}
					if (td.getJasa() != null && td.getJasa().getKd_jasa().length() >= 4) {
						p.add("\n #nama-jasa: " + td.getJasa().getNama());
						p.add("\n #harga    : " + td.getJasa().getHarga());

						if (td.getJasa() != null && td.getJasa().getDokter() != null
								&& td.getJasa().getDokter().getNama().length() >= 3) {
							p.add("\n #dokter   : " + td.getJasa().getDokter().getNama());
						} else {
							p.add("\n #dokter   : -");
						}
					}
					p.add("\n sub-total : Rp." + td.getSub_total());

				}

				p.add("\n================================\n total : " + transaksi.getTotal());
				if (transaksi.getDsc() > 0) {
					Double nm = transaksi.getTotal() / 100;
					nm *= transaksi.getDsc();

					transaksi.setTotal(transaksi.getTotal() - nm);
				}
				p.add("\n disc : " + transaksi.getDsc() + "%  -> Rp." + transaksi.getTotal());

				p.add("\n Uang : Rp." + transaksi.getUang());
				p.add("\n kembali : Rp." + (transaksi.getUang() - transaksi.getTotal()));

				p.add("\n petugas : " + getSession(transaksi.getUser()).getNama());
				document.add(p);
				document.close();
			 inputStream = new ByteArrayInputStream(buffer.toByteArray());
			 return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String reporting1() {
		u = getSession(new User());
		if (u.getLevel() == 2) {
			String r = ERROR;
			if (getTgl_akhir() != null && getTgl_akhir().length() >= 4 && getTgl_awal() != null
					&& getTgl_awal().length() >= 4) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				try {
					reporting.setTgl_awal(sdf.parse(getTgl_awal()));
					reporting.setTgl_akhir(sdf.parse(getTgl_akhir()));
					System.out.println("====================\n\n======================\n\nmasuuuutaiiiiuuuk "
							+ reporting.getTgl_awal() + " " + reporting.getTable());
					if (reporting.getTable().equalsIgnoreCase("MT2")) {
						setListTransaksi(transaksiMapper.getKikiReport(reporting));
						if (getListTransaksi().size() <= 0) {
							setPage("laporan.action");
							return ERROR;
						}

						r = reportKiki(sdf);
					} else if (reporting.getTable().equalsIgnoreCase("MT")) {
						setReportTm(transaksiMapper.laporanMT(reporting));
						System.out.println("iyaaaaaaaaaaaaaaaaaaaaaaaaaa555555aaaaaa\n\n\n");
						if (getReportTm().size() <= 0) {
							setPage("laporan.action");
							return ERROR;
						}

						r = reportMT(sdf);
					} else if (reporting.getTable().equals("BM")) {
						System.out.println("iyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n\n");
						setListBarang(barangMapper.reportBarangMasuk(reporting));
						if (getListBarang().size() <= 0) {
							setPage("laporan.action");
							return ERROR;
						}

						r = reportBM(sdf);
					} else if (reporting.getTable().equalsIgnoreCase("MD")) {
						System.out.println("iyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n\n"+getKd_dokter1());
						reporting.setTable(getKd_dokter1().trim());
						setReportDokter(transaksiMapper.reportDokter22(reporting));
						System.out.println("==================> "+getReportDokter().size());
						if (getReportDokter()==null) {
							setPage("laporan.action");
							return ERROR;
						}
						r = reportMD(sdf);
					} else {
						setListTransaksi(transaksiMapper.reporting(reporting));
						if (getListTransaksi().size() <= 0) {
							setPage("laporan.action");
							return ERROR;
							
						}
						r = reportTR(sdf);
					}

				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				}
			}

			return r;
		}
		return "";
	}

	public String reportBM(SimpleDateFormat sdf) {
		Double totalInvest = 0d, h = 0d;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			PdfWriter.getInstance(document, buffer);
			document.open();
			int page = 1, number = 1;
			Paragraph p = new Paragraph();

			ServletContext cx = ServletActionContext.getServletContext();
			String abc = cx.getRealPath("/content/BG/logo2.png");
			Image img = Image.getInstance(abc);
			document.add(img);
			p.add(" Laporan Barang Masuk, page : " + number);
			p.add("\n Petugas	   : " + getSession(new User()).getNama() + "....................tgl-periode  : "
					+ getTgl_awal() + " s/d " + getTgl_akhir() + "....................tgl-dibuat   : "
					+ sdf.format((new Date())));
			p.add("\n========================================================================================================================");
			document.add(p);
			PdfPTable table1 = new PdfPTable(8);
			table1.setTotalWidth(new float[] { 100, 200, 60, 32, 70, 85, 85, 200 });
			table1.setLockedWidth(true);

			table1.addCell("KD-Masuk");
			table1.addCell("OBAT");
			table1.addCell("HARGA");
			table1.addCell("QTY");
			table1.addCell("INVEST");
			table1.addCell("TGL-MASUK");
			table1.addCell("TGL-EXPIRED");
			table1.addCell("SUPPLIER");
			for (BarangMasuk bm : getListBarang()) {
				if (page == 25) {
					System.out.println("===========================\n" + bm.getKd_masuk());
					page = 1;
					number++;
					document.add(table1);
					document.newPage();
					table1 = null;
					p.clear();
					p = null;
					table1 = new PdfPTable(8);
					table1.setTotalWidth(new float[] { 100, 200, 60, 32, 70, 85, 85, 200 });
					table1.setLockedWidth(true);
					p = new Paragraph();
					p.add(" Laporan Barang Masuk, page : " + number);
					document.add(img);
					p.add("\n Petugas	   : " + getSession(new User()).getNama()
							+ ".................... tgl-periode  : " + getTgl_awal() + " s/d " + getTgl_akhir()
							+ "....................tgl-dibuat   : " + sdf.format((new Date())));
					p.add("\n========================================================================================================================");
					document.add(p);

					table1.addCell("KD-Masuk");
					table1.addCell("OBAT");
					table1.addCell("HARGA");
					table1.addCell("QTY");
					table1.addCell("INVEST");
					table1.addCell("TGL-MASUK");
					table1.addCell("TGL-EXPIRED");
					table1.addCell("SUPPLIER");
				}
				table1.addCell(bm.getKd_masuk());
				table1.addCell(bm.getObat().getNama_obat());
				table1.addCell(String.valueOf(bm.getObat().getHarga()));
				table1.addCell(String.valueOf(bm.getQty()));
				h = bm.getObat().getHarga() * bm.getQty();
				table1.addCell(String.valueOf(h));
				table1.addCell(sdf.format(bm.getTgl_masuk()));
				if (bm.getTgl_expired() != null) {
					table1.addCell(sdf.format(bm.getTgl_expired()));
				} else {
					table1.addCell("-");
				}
				table1.addCell(bm.getSupplier().getNama());

				totalInvest += h;
				page++;

			}

			p = new Paragraph();
			p.add("Total Investasi : Rp." + String.valueOf(totalInvest));
			document.add(table1);
			document.add(p);
			document.addTitle("LAPORAN BARANG MASUK GRIYA SEHAT NATURA");
			document.close();
			inputStream = new ByteArrayInputStream(buffer.toByteArray());
			setNamaFile(sdf.format(new Date()) + ".pdf");
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			setPage("laporan.action");
			return ERROR;
		}
	}
	
	
	public String reportMD(SimpleDateFormat sdf) {
		Double totalInvest = 0d, h = 0d;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			PdfWriter.getInstance(document, buffer);
			document.open();
			Paragraph p = new Paragraph();

			ServletContext cx = ServletActionContext.getServletContext();
			String abc = cx.getRealPath("/content/BG/logo2.png");
			Image img = Image.getInstance(abc);
			document.add(img);
			p.add(" Gaji Dokter");
			p.add("\n Petugas	   : " + getSession(new User()).getNama());
			p.add("\n tgl-periode  : "+ getTgl_awal() + " s/d " + getTgl_akhir());
			p.add("\n tgl-dibuat   : "+ sdf.format((new Date())));
			p.add("\n========================================================================================================================");
			document.add(p);
			PdfPTable table1 = new PdfPTable(9);
			table1.setTotalWidth(new float[] { 80,150, 60, 170 , 100, 50, 100,30,100 });
			table1.setLockedWidth(true);

			table1.addCell("KD-dokter");
			table1.addCell("Dokter");
			table1.addCell("Spesialis");
			table1.addCell("Jasa");
			table1.addCell("Harga");
			table1.addCell("Persen");
			table1.addCell("Fee/Jasa");
			table1.addCell("Jml");
			table1.addCell("Gaji");

			
			for (ReportDokter j : getReportDokter() ){
				table1.addCell(j.getJasa().getDokter().getKd_dokter());
				table1.addCell(j.getJasa().getDokter().getNama());
				table1.addCell(j.getJasa().getDokter().getSpesialis());
				table1.addCell(j.getJasa().getNama());
				table1.addCell(String.valueOf(j.getJasa().getHarga()));
				table1.addCell(String.valueOf(j.getJasa().getPersen()));
				table1.addCell(String.valueOf(j.getJasa().getFee()));
				table1.addCell(String.valueOf(j.getJml()));
				table1.addCell(String.valueOf(j.getSub_total()));
			}
			document.add(table1);
			document.addTitle("LAPORAN Gaji Dokter GRIYA SEHAT NATURA");
			document.close();
			inputStream = new ByteArrayInputStream(buffer.toByteArray());
			setNamaFile(sdf.format(new Date()) + ".pdf");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			setPage("laporan.action");
			return ERROR;
		}
	}
	
	

	public String reportTR(SimpleDateFormat sdf) {
		Double pendapatan = 0d;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			PdfWriter.getInstance(document, buffer);
			document.open();
			int page = 1, number = 1;
			Paragraph p = new Paragraph();

			ServletContext cx = ServletActionContext.getServletContext();
			String abc = cx.getRealPath("/content/BG/logo2.png");
			Image img = Image.getInstance(abc);
			document.add(img);
			p.add(" Laporan Transaksi, page : " + number);
			p.add("\n Petugas	   : " + getSession(new User()).getNama() + "....................tgl-periode  : "
					+ getTgl_awal() + " s/d " + getTgl_akhir() + "....................tgl-dibuat   : "
					+ sdf.format((new Date())));
			p.add("\n========================================================================================================================");
			document.add(p);
			PdfPTable table1 = new PdfPTable(14);
			table1.setTotalWidth(new float[] { 67, 65, 57, 72, 72, 40, 57, 32, 62, 62, 50, 72, 65, 65 });
			table1.setLockedWidth(true);

			table1.addCell("KD-TRANS");
			table1.addCell("TANGGAL");
			table1.addCell("PASSIEN");
			table1.addCell("KD-OBAT");
			table1.addCell("OBAT");
			table1.addCell("JENIS");
			table1.addCell("HARGA");
			table1.addCell("BELI");
			table1.addCell("KD-JASA");
			table1.addCell("JASA");
			table1.addCell("HARGA");
			table1.addCell("DOKTER");
			table1.addCell("SUB");
			table1.addCell("TOTAL");
			for (Transaksi tr : getListTransaksi()) {
				for (Transaksi_detail td : tr.getTransaksi_detail()) {
					if (page == 11) {
						page = 1;
						number++;
						document.add(table1);
						document.newPage();
						table1 = null;
						p.clear();
						p = null;
						table1 = new PdfPTable(14);
						table1.setTotalWidth(new float[] { 67, 65, 57, 72, 72, 40, 57, 32, 62, 62, 50, 72, 65, 65 });
						table1.setLockedWidth(true);
						p = new Paragraph();
						p.add(" Laporan Transaksi, page : " + number);
						document.add(img);
						p.add("\n Petugas	   : " + getSession(new User()).getNama()
								+ ".................... tgl-periode  : " + getTgl_awal() + " s/d " + getTgl_akhir()
								+ "....................tgl-dibuat   : " + sdf.format((new Date())));
						p.add("\n========================================================================================================================");
						document.add(p);

						table1.addCell("KD-TRANS");
						table1.addCell("TANGGAL");
						table1.addCell("PASSIEN");
						table1.addCell("KD-OBAT");
						table1.addCell("OBAT");
						table1.addCell("JENIS");
						table1.addCell("HARGA");
						table1.addCell("BELI");
						table1.addCell("KD-JASA");
						table1.addCell("JASA");
						table1.addCell("HARGA");
						table1.addCell("DOKTER");
						table1.addCell("SUB");
						table1.addCell("TOTAL");
					}
					table1.addCell(tr.getKd_transaksi());
					table1.addCell(sdf.format(tr.getTgl_transaksi()));
					if (tr.getPassien() != null && tr.getPassien().getNama().length() >= 3) {
						table1.addCell(tr.getPassien().getNama());
					} else {
						table1.addCell("-");
					}
					if (td.getObat() != null && td.getObat().getKd_obat().length() >= 5) {
						table1.addCell(td.getObat().getKd_obat());
						table1.addCell(td.getObat().getNama_obat());
						table1.addCell(td.getObat().getJenis());
						table1.addCell(String.valueOf(td.getObat().getHarga()));
						table1.addCell(String.valueOf(td.getJml_beli()));
					} else {
						table1.addCell("-");
						table1.addCell("-");
						table1.addCell("-");
						table1.addCell("-");
						table1.addCell("-");
					}
					if (td.getJasa() != null && td.getJasa().getKd_jasa().length() >= 4) {
						// System.out.print(" "+td.getJasa().getKd_jasa()+"
						// "+td.getJasa().getNama()+"
						// "+td.getJasa().getHarga()+"
						// "+td.getJasa().getDokter().getNama());

						table1.addCell(td.getJasa().getKd_jasa());
						table1.addCell(td.getJasa().getNama());
						table1.addCell(String.valueOf(td.getJasa().getHarga()));

					} else {
						table1.addCell("-");
						table1.addCell("-");
						table1.addCell("-");

					}
					if (td.getJasa() != null && td.getJasa().getDokter() != null
							&& td.getJasa().getDokter().getNama().length() >= 3) {
						table1.addCell(td.getJasa().getDokter().getNama());
					} else {
						table1.addCell("-");
					}

					table1.addCell(String.valueOf(td.getSub_total()));
					table1.addCell(String.valueOf(tr.getTotal()));
					pendapatan += tr.getTotal();
					page++;

				}
			}

			p = new Paragraph();
			p.add("Total Pendapatan : Rp." + String.valueOf(pendapatan));
			document.add(table1);
			document.add(p);
			document.addTitle("LAPORAN TRANSAKSI GRIYA SEHAT NATURA");
			document.close();
			inputStream = new ByteArrayInputStream(buffer.toByteArray());
			setNamaFile(sdf.format(new Date()) + ".pdf");
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			setPage("laporan.action");
			return ERROR;
		}
	}

	public String reportMT(SimpleDateFormat sdf) {
		Double pendapatan = 0d;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			PdfWriter.getInstance(document, buffer);
			document.open();
			int page = 1, number = 1;
			Paragraph p = new Paragraph();

			ServletContext cx = ServletActionContext.getServletContext();
			String abc = cx.getRealPath("/content/BG/logo2.png");
			Image img = Image.getInstance(abc);
			document.add(img);
			p.add(" Laporan Transaksi, page : " + number);
			p.add("\n Petugas	   : " + getSession(new User()).getNama() + "....................tgl-periode  : "
					+ getTgl_awal() + " s/d " + getTgl_akhir() + "....................tgl-dibuat   : "
					+ sdf.format((new Date())));
			p.add("\n========================================================================================================================");
			document.add(p);
			PdfPTable table1 = new PdfPTable(8);
			table1.setTotalWidth(new float[] { 200, 90, 60, 80, 80, 80, 50, 100 });
			table1.setLockedWidth(true);

			table1.addCell("NAMA-OBAT");
			table1.addCell("JENIS");
			table1.addCell("HARGA");
			table1.addCell("S.AWAL");
			table1.addCell("TERJUAL");
			table1.addCell("S.SAATINI");
			table1.addCell("C.DSC");
			table1.addCell("SUB.TOTAL");

			for (ReportTM tr : getReportTm()) {

				if (page == 11) {
					page = 1;
					number++;
					document.add(table1);
					document.newPage();
					table1 = null;
					p.clear();
					p = null;
					table1 = new PdfPTable(8);
					table1.setTotalWidth(new float[] { 200, 90, 60, 80, 80, 80, 50, 100 });
					table1.setLockedWidth(true);
					p = new Paragraph();
					p.add(" Laporan Transaksi, page : " + number);
					document.add(img);
					p.add("\n Petugas	   : " + getSession(new User()).getNama()
							+ ".................... tgl-periode  : " + getTgl_awal() + " s/d " + getTgl_akhir()
							+ "....................tgl-dibuat   : " + sdf.format((new Date())));
					p.add("\n========================================================================================================================");
					document.add(p);

					table1.addCell("NAMA-OBAT");
					table1.addCell("JENIS");
					table1.addCell("HARGA");
					table1.addCell("S.AWAL");
					table1.addCell("TERJUAL");
					table1.addCell("S.SAATINI");
					table1.addCell("C.DSC");
					table1.addCell("SUB.TOTAL");
				}
				table1.addCell(tr.getObat().getNama_obat());
				table1.addCell(tr.getObat().getJenis());
				table1.addCell(String.valueOf(tr.getObat().getHarga()));
				table1.addCell(String.valueOf(tr.getStok_awal()));
				table1.addCell(String.valueOf(tr.getTerjual()));
				table1.addCell(String.valueOf(tr.getObat().getStok()));
				table1.addCell(String.valueOf(tr.getCountDsc()));
				table1.addCell(String.valueOf(tr.getTotal()));
				pendapatan += tr.getTotal();
				page++;
			}

			p = new Paragraph();
			p.add("Total Pendapatan : Rp." + String.valueOf(pendapatan));
			document.add(table1);
			document.add(p);
			document.addTitle("LAPORAN TRANSAKSI GRIYA SEHAT NATURA");
			document.close();
			inputStream = new ByteArrayInputStream(buffer.toByteArray());
			setNamaFile("LAP" + sdf.format(new Date()) + ".pdf");
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			setPage("laporan.action");
			return ERROR;
		}
	}

	public String reportKiki(SimpleDateFormat sdf) {
		Double pendapatan = 0d;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			PdfWriter.getInstance(document, buffer);
			document.open();
			int page = 1, number = 1;
			Paragraph p = new Paragraph();

			ServletContext cx = ServletActionContext.getServletContext();
			String abc = cx.getRealPath("/content/BG/logo2.png");
			Image img = Image.getInstance(abc);
			document.add(img);
			p.add(" Laporan Transaksi, page : " + number);
			p.add("\n Petugas	   : " + getSession(new User()).getNama() + "....................tgl-periode  : "
					+ getTgl_awal() + " s/d " + getTgl_akhir() + "....................tgl-dibuat   : "
					+ sdf.format((new Date())));
			p.add("\n========================================================================================================================");
			document.add(p);
			PdfPTable table1 = new PdfPTable(3);
			table1.setTotalWidth(new float[] { 200, 90, 200 });
			table1.setLockedWidth(true);

			table1.addCell("TGL-TRANSAKSI");
			table1.addCell("TRANSAKSI");
			table1.addCell("PENDAPATAN");

			for (Transaksi tr : getListTransaksi()) {

				if (page == 11) {
					page = 1;
					number++;
					document.add(table1);
					document.newPage();
					table1 = null;
					p.clear();
					p = null;
					table1 = new PdfPTable(2);
					table1.setTotalWidth(new float[] { 200, 90, 200 });
					table1.setLockedWidth(true);
					p = new Paragraph();
					p.add(" Laporan Transaksi, page : " + number);
					document.add(img);
					p.add("\n Petugas	   : " + getSession(new User()).getNama()
							+ ".................... tgl-periode  : " + getTgl_awal() + " s/d " + getTgl_akhir()
							+ "....................tgl-dibuat   : " + sdf.format((new Date())));
					p.add("\n========================================================================================================================");
					document.add(p);

					table1.addCell("TRANSAKSI");
					table1.addCell("JML-TRANSAKSI");
					table1.addCell("PENDAPATAN");
				}
				table1.addCell(sdf.format(tr.getTgl_transaksi()));
				table1.addCell(String.valueOf(tr.getDsc()));
				table1.addCell(String.valueOf(tr.getUang()));

				pendapatan += tr.getUang();
				page++;
			}

			p = new Paragraph();
			p.add("Total Pendapatan : Rp." + String.valueOf(pendapatan));
			document.add(table1);
			document.add(p);
			document.addTitle("LAPORAN TRANSAKSI GRIYA SEHAT NATURA");
			document.close();
			inputStream = new ByteArrayInputStream(buffer.toByteArray());
			setNamaFile("LAP" + sdf.format(new Date()) + ".pdf");
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			setPage("laporan.action");
			return ERROR;
		}
	}

	public void setListObat(ArrayList<Obat> listObat) {
		this.listObat = listObat;
	}

	public ArrayList<Obat> getListObat() {
		return listObat;
	}

	public Transaksi getTransaksi() {
		return transaksi;
	}

	public void setTransaksi(Transaksi transaksi) {
		this.transaksi = transaksi;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public ArrayList<Jasa> getListJ() {
		return listJ;
	}

	public void setListJ(ArrayList<Jasa> listJ) {
		this.listJ = listJ;
	}

	public ArrayList<Transaksi> getListTransaksi() {
		return listTransaksi;
	}

	public void setListPassien(ArrayList<Passien> listPassien) {
		this.listPassien = listPassien;
	}

	public ArrayList<Passien> getListPassien() {
		return listPassien;
	}

	public void setListTransaksi(ArrayList<Transaksi> listTransaksi) {
		this.listTransaksi = listTransaksi;
	}

	public ObatInterface getObatMapper() {
		return obatMapper;
	}

	public void setObatMapper(ObatInterface obatMapper) {
		this.obatMapper = obatMapper;
	}

	public PassienInterface getPasMapper() {
		return pasMapper;
	}

	public void setPasMapper(PassienInterface pasMapper) {
		this.pasMapper = pasMapper;
	}

	public TransaksiInterface getTransaksiMapper() {
		return transaksiMapper;
	}

	public void setTransaksiMapper(TransaksiInterface transaksiMapper) {
		this.transaksiMapper = transaksiMapper;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public void setPagging(String pagging) {
		this.pagging = pagging;
	}

	public String getPagging() {
		return pagging;
	}

	public void setTransaksi2(Transaksi transaksi2) {
		this.transaksi2 = transaksi2;
	}

	public Transaksi getTransaksi2() {
		return transaksi2;
	}

	public void setObat(Obat obat) {
		this.obat = obat;
	}

	public Obat getObat() {
		return obat;
	}

	public void setTransaksi_detail(ArrayList<Transaksi_detail> transaksi_detail) {
		this.transaksi_detail = transaksi_detail;
	}

	public ArrayList<Transaksi_detail> getTransaksi_detail() {
		return transaksi_detail;
	}

	public void setReporting(Reporting reporting) {
		this.reporting = reporting;
	}

	public Reporting getReporting() {
		return reporting;
	}

	public void setJasaInterface(JasaInterface jasaInterface) {
		this.jasaInterface = jasaInterface;
	}

	public JasaInterface getJasaInterface() {
		return jasaInterface;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setNamaFile(String namaFile) {
		this.namaFile = namaFile;
	}

	public String getNamaFile() {
		return namaFile;
	}

	public void setTgl_akhir(String tgl_akhir) {
		this.tgl_akhir = tgl_akhir;
	}

	public void setTgl_awal(String tgl_awal) {
		this.tgl_awal = tgl_awal;
	}

	public String getTgl_akhir() {
		return tgl_akhir;
	}

	public String getTgl_awal() {
		return tgl_awal;
	}

	public ArrayList<BarangMasuk> getListBarang() {
		return listBarang;
	}

	public void setListBarang(ArrayList<BarangMasuk> listBarang) {
		this.listBarang = listBarang;
	}

	public BarangMasukInterface getBarangMapper() {
		return barangMapper;
	}

	public void setReportTm(ArrayList<ReportTM> reportTm) {
		this.reportTm = reportTm;
	}

	public ArrayList<ReportTM> getReportTm() {
		return reportTm;
	}

	public void setBarangMapper(BarangMasukInterface barangMapper) {
		this.barangMapper = barangMapper;
	}
	public ArrayList<ReportDokter> getReportDokter() {
		return reportDokter;
	}
	public void setReportDokter(ArrayList<ReportDokter> reportDokter) {
		this.reportDokter = reportDokter;
	}

	public void setKd_dokter1(String kd_dokter) {
		this.kd_dokter1 = kd_dokter;
	}
	public String getKd_dokter1() {
		return kd_dokter1;
	}
}
