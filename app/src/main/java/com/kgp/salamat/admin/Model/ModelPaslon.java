package com.kgp.salamat.admin.Model;

import org.parceler.Parcel;

@Parcel
public class ModelPaslon {
    String id_paslon,nama_paslon,no_paslon,url_image,cdd,udd,hash;

    public String getId_paslon() {
        return id_paslon;
    }

    public void setId_paslon(String id_paslon) {
        this.id_paslon = id_paslon;
    }

    public String getNama_paslon() {
        return nama_paslon;
    }

    public void setNama_paslon(String nama_paslon) {
        this.nama_paslon = nama_paslon;
    }

    public String getNo_paslon() {
        return no_paslon;
    }

    public void setNo_paslon(String no_paslon) {
        this.no_paslon = no_paslon;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getCdd() {
        return cdd;
    }

    public void setCdd(String cdd) {
        this.cdd = cdd;
    }

    public String getUdd() {
        return udd;
    }

    public void setUdd(String udd) {
        this.udd = udd;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
