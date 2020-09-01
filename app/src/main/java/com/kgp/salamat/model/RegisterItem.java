package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

public class RegisterItem {
    @SerializedName("nik")
    public String nik;
    @SerializedName("nama_lengkap")
    public String nama_lengkap;
    @SerializedName("alamat")
    public String alamat;
    @SerializedName("no_hp")
    public String no_hp;
    @SerializedName("email")
    public String email;
    @SerializedName("pass")
    public String pass;
    @SerializedName("url_image")
    public String url_image;
    @SerializedName("tps")
    public String tps;

    public RegisterItem(String nik, String nama_lengkap, String alamat, String no_hp, String email, String pass, String url_image, String tps) {
        this.nik = nik;
        this.nama_lengkap = nama_lengkap;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.email = email;
        this.pass = pass;
        this.url_image = url_image;
        this.tps = tps;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }
}
