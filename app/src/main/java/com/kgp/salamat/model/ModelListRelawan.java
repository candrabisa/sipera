package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

public class ModelListRelawan {
    @SerializedName("id_relawan")
    private int id_relawan;
    @SerializedName("nik")
    private String nik;
    @SerializedName("nama_lengkap")
    private String nama_lengkap;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("email")
    private String email;
    @SerializedName("tps")
    private String tps;
    @SerializedName("url_images")
    private String url_images;
    @SerializedName("url_profile")
    private String url_profile;
    @SerializedName("cdd")
    private String cdd;
    @SerializedName("hash")
    private String hash;

    public ModelListRelawan(int id_relawan, String nik, String nama_lengkap, String alamat, String no_hp, String email, String tps, String url_images, String url_profile, String cdd, String hash) {
        this.id_relawan = id_relawan;
        this.nik = nik;
        this.nama_lengkap = nama_lengkap;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.email = email;
        this.tps = tps;
        this.url_images = url_images;
        this.url_profile = url_profile;
        this.cdd = cdd;
        this.hash = hash;
    }

    public int getId_relawan() {
        return id_relawan;
    }

    public void setId_relawan(int id_relawan) {
        this.id_relawan = id_relawan;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }

    public String getUrl_images() {
        return url_images;
    }

    public void setUrl_images(String url_images) {
        this.url_images = url_images;
    }

    public String getUrl_profile() {
        return url_profile;
    }

    public void setUrl_profile(String url_profile) {
        this.url_profile = url_profile;
    }

    public String getCdd() {
        return cdd;
    }

    public void setCdd(String cdd) {
        this.cdd = cdd;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
