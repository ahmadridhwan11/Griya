package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.Dokter;

public interface DokterInterface {
	void Insert(Dokter dokter);
	void Update(Dokter dokter);
	ArrayList<Dokter> getDokter(String kd_dokter);
	void Delete(String kd_dokter);
	Dokter getDokterWhere(Dokter dokter);
	int getCount();
}
