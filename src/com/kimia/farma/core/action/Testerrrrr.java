package com.kimia.farma.core.action;

import com.kimia.farma.model.Dokter;
import com.kimia.farma.model.Identitas;
import com.kimia.farma.model.Passien;
import com.kimia.farma.model.User;

public class Testerrrrr {
	public static void main(String[] args) {
		Object d = new Identitas();
		if (d instanceof Dokter) {
			Dokter g= (Dokter) d;
			System.out.println("dokter");
		}else if(d instanceof User){
			System.out.println("user");
		}else if(d instanceof Passien){
			System.out.println("pasien");
		}else{
			System.out.println("gadaa");
		}
	}
}
