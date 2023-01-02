package com.entities;

public class Video extends Koleksi {
    private int durasi;
    private boolean isPublik;

    public Video(int durasi, boolean isPublik, int id, String judul, String kreator, int tahun) {
        super(id, judul, kreator, tahun);
        this.durasi = durasi;
        this.isPublik = isPublik;
    }

    public boolean isAvailable() {
        return isPublik;
    }

    
    //SETTER GETTER
    public int getDurasi() {
        return durasi;
    }
    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }
    public boolean isPublik() {
        return isPublik;
    }
    public void setIsPublik(boolean isPublik) {
        this.isPublik = isPublik;
    }

}
