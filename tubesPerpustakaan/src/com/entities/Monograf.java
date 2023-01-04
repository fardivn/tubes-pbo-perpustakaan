package com.entities;

public class Monograf extends Koleksi{
    private String isbn;
    private int jumlah;

    public Monograf(String isbn, int jumlah, int id, String judul, String kreator, int tahun) {
        super(id, judul, kreator, tahun);
        this.isbn = isbn;
        this.jumlah = jumlah;
    }

    public boolean isAvailable() {
        boolean isAvailable = true;
        if(jumlah==0){
            return !isAvailable;
        } else{
            return isAvailable;
        }
    }

    public boolean diPinjam(){
        if(isAvailable()){
            jumlah -= 1;
            return true;
        }
        return false;
    }

    public void kembali(){
        jumlah += 1;
    }
    
    
    
    // SETTER GETTER
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

}
