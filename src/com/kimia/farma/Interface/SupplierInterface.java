package com.kimia.farma.Interface;

import java.util.ArrayList;

import com.kimia.farma.model.Supplier;;

public interface SupplierInterface {
	void Insert(Supplier supplier);
	void Update(Supplier supplier);
	ArrayList<Supplier> getSupplier(Supplier kd_sup);
	void Delete(String kd_sup);
	Supplier getSupWhere(String kd_supplier);
	int getCount();
}
