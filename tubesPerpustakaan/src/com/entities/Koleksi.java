package com.entities;

public abstract class Koleksi {
    int id;
    String judul;
    String kreator;
    int tahun;

    public Koleksi(int id, String judul, String kreator, int tahun) {
        this.id = id;
        this.judul = judul;
        this.kreator = kreator;
        this.tahun = tahun;
    }
    
    public abstract boolean isAvailable();


    
    //SETTER GETTER
    public int getId() {
        return id;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getKreator() {
        return kreator;
    }
    public void setKreator(String kreator) {
        this.kreator = kreator;
    }
    public int getTahun() {
        return tahun;
    }
    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
}
