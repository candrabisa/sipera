package com.kgp.salamat.admin.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TpsItem{

	@SerializedName("alamat_tps")
	public String alamatTps;

	public void setAlamatTps(String alamatTps) {
		this.alamatTps = alamatTps;
	}

	public void setIdTps(String idTps) {
		this.idTps = idTps;
	}

	public void setNamaTps(String namaTps) {
		this.namaTps = namaTps;
	}

	@SerializedName("id_tps")
	public String idTps;

	@SerializedName("nama_tps")
	public String namaTps;

	public String getAlamatTps(){
		return alamatTps;
	}

	public String getIdTps(){
		return idTps;
	}

	public String getNamaTps(){
		return namaTps;
	}
}