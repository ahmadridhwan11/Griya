package com.kimia.farma.util;

import com.opensymphony.xwork2.ActionSupport;

public class MyUtil extends ActionSupport {

	public int ValidasiString(String data,String key,int length,String message){
		if (data !=null && !data.trim().equalsIgnoreCase("") && data.trim().length()>=length) {
			addFieldError(key,message);
				return 1;
		}
		return 0;
	}
}
