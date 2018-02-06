package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.BarangMasuk;
import com.kimia.farma.model.Reporting;
import com.kimia.farma.model.Supplier;

public interface BarangMasukInterface {
	void simpanBarangMasuk(BarangMasuk bm);
	void updateBarangMasuk(BarangMasuk bm);
	void deleteBranagMasuk(String kd_masuk);
	BarangMasuk getBarangMasuk(BarangMasuk barangMasuk);
	ArrayList<BarangMasuk> getAllBarangMasuk(String kd_masuk);
	ArrayList<BarangMasuk> reportBarangMasuk(Reporting r);
	ArrayList<Supplier> getSup();
	int getCountBM();
}
