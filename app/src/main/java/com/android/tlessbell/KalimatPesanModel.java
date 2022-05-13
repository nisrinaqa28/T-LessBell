package com.android.tlessbell;

public class KalimatPesanModel{

    private String idKalimat, pesan;

    public KalimatPesanModel() {
    }

    public KalimatPesanModel(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getIdKalimat() {
        return idKalimat;
    }

    public void setIdKalimat(String idKalimat) {
        this.idKalimat = idKalimat;
    }
}
