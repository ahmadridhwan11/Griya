package com.kimia.farma.Interface;

import java.util.ArrayList;
import com.kimia.farma.model.Obat;

public interface ObatInterface {
	void InsertObat(Obat o);
	void UpdateObat(Obat o);
	void DeleteObat(String kd_obat);
	ArrayList<Obat> getAllObat(String kd_obat);
	ArrayList<Obat> getObat(String nama);
	Obat getOneObat(String kd_obat);
	int getCount();
	void UpdateStock(Obat o);
}
