package com.kgp.salamat.admin.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class RelawanItem{

	@SerializedName("nik")
	public String nik;

	@SerializedName("id_relawan")
	public String idRelawan;

	@SerializedName("cdd")
	public String cdd;

	@SerializedName("no_hp")
	public String noHp;

	@SerializedName("tps")
	public String tps;

	@SerializedName("nama_lengkap")
	public String namaLengkap;

	@SerializedName("email")
	public String email;

	@SerializedName("url_profile")
	public String urlProfile;

	@SerializedName("hash")
	public String hash;

	@SerializedName("alamat")
	public String alamat;

	@SerializedName("url_image")
	public String urlImage;

	public void setNik(String nik){
		this.nik = nik;
	}

	public String getNik(){
		return nik;
	}

	public void setIdRelawan(String idRelawan){
		this.idRelawan = idRelawan;
	}

	public String getIdRelawan(){
		return idRelawan;
	}

	public void setCdd(String cdd){
		this.cdd = cdd;
	}

	public String getCdd(){
		return cdd;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setTps(String tps){
		this.tps = tps;
	}

	public String getTps(){
		return tps;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUrlProfile(String urlProfile){
		this.urlProfile = urlProfile;
	}

	public Object getUrlProfile(){
		return urlProfile;
	}

	public void setHash(String hash){
		this.hash = hash;
	}

	public String getHash(){
		return hash;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setUrlImage(String urlImage){
		this.urlImage = urlImage;
	}

	public String getUrlImage(){
		return urlImage;
	}
}