package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.Jasa;
import com.kimia.farma.model.Obat;

public interface JasaInterface {
	void InsertJasa(Jasa j);
	void UpdateJasa(Jasa j);
	void DeleteJasa(String kd_jasa);
	void DeleteJasaByDokter(String kd_dokter);
	ArrayList<Jasa> getAllJasa(String kd_jasa);
	ArrayList<Jasa> getAllmyJasa(String kd_jasa);
	Jasa getOneJasa(Jasa kd_jasa);
	Jasa getOnemyJasa(Jasa kd_jasa);
	int getCount();
}
