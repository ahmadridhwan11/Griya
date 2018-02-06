package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.Pengeluaran;

public interface PengeluaranInterface {

	void DeletePengeluaran(String kd_pengeluaran);

	ArrayList<Pengeluaran> getAll(String object);

	Pengeluaran getOne(String kd_pengeluaran);

	void update(Pengeluaran pengeluaran);

	int getCount();

	void insert(Pengeluaran pengeluaran);

}
