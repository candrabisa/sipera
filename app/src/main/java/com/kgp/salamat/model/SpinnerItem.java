package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

public class SpinnerItem{

	@SerializedName("alamat_tps")
	private String alamatTps;

	@SerializedName("id_tps")
	private String idTps;

	public String getAlamatTps(){
		return alamatTps;
	}

	public String getIdTps(){
		return idTps;
	}
}