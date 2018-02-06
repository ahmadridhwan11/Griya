package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.Passien;

public interface PassienInterface {
	void Insert(Passien passien);
	void Update(Passien passien);
	ArrayList<Passien> getPassien(Passien kd_pas);
	void Delete(String kd_pas);
	Passien getPasWhere(Passien kd_pas);
	int getCount();
}
