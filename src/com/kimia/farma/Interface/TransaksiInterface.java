package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.ReportDokter;
import com.kimia.farma.model.ReportTM;
import com.kimia.farma.model.Reporting;
import com.kimia.farma.model.Transaksi;

public interface TransaksiInterface {
	public ArrayList<Transaksi> getTransaksiAll(Transaksi tr);

	public ArrayList<Transaksi> getTransaksiHistory(Transaksi tr);
	public Transaksi getTransaksi(Transaksi tr);
	public void updateTransaksi(Transaksi tr);
	public void updateDetail(Transaksi tr);
	public int getCount();
	public void saveTransaksi(Transaksi tr);
	public Transaksi getTransaksiDetail(Transaksi tr);
	public void saveTransaksiDetail(Transaksi tr);
	public ArrayList<Transaksi> reporting(Reporting tr);
	public ArrayList<Transaksi> getKikiReport(Reporting tr);
	public ArrayList<ReportTM> laporanMT(Reporting tr);
	public ArrayList<ReportDokter> reportDokter22(Reporting r);
}
