package com.kgp.salamat.admin.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProvinsi{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<Provinsi> data;

	@SerializedName("success")
	private boolean success;

	public int getCode(){
		return code;
	}

	public List<Provinsi> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}
}