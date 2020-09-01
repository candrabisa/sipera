package com.kgp.salamat.admin.Model;

import com.google.gson.annotations.SerializedName;

public class Provinsi {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}