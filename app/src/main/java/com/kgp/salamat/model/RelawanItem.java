package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

public class RelawanItem{
	public void setNik(String nik) {
		this.nik = nik;
	}

	public void setIdRelawan(String idRelawan) {
		this.idRelawan = idRelawan;
	}

	public void setCdd(String cdd) {
		this.cdd = cdd;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public void setTps(String tps) {
		this.tps = tps;
	}

	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUrlProfile(String urlProfile) {
		this.urlProfile = urlProfile;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	@SerializedName("nik")
	private String nik;

	@SerializedName("id_relawan")
	private String idRelawan;

	@SerializedName("cdd")
	private String cdd;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("tps")
	private String tps;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("email")
	private String email;

	@SerializedName("url_profile")
	private String urlProfile;

	@SerializedName("hash")
	private String hash;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("url_image")
	private String urlImage;

	public String getNik(){
		return nik;
	}

	public String getIdRelawan(){
		return idRelawan;
	}

	public String getCdd(){
		return cdd;
	}

	public String getNoHp(){
		return noHp;
	}

	public String getTps(){
		return tps;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public String getEmail(){
		return email;
	}

	public String getUrlProfile(){
		return urlProfile;
	}

	public String getHash(){
		return hash;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getUrlImage(){
		return urlImage;
	}
}