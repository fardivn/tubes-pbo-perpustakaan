package com.entities;

public class Member implements IVerifikasi{
    private String nama;
    private int noMember;
    private boolean isAktif;

    public Member(int noMember, boolean isAktif, String nama) {
        this.noMember = noMember;
        this.isAktif = isAktif;
        this.nama = nama;
    }
    
    public boolean isValid() {
        return isAktif;
    }



    //SETTER GETTER
    public int getNoMember() {
        return noMember;
    }
    public boolean IsAktif() {
        return isAktif;
    }
    public void setIsAktif(boolean isAktif) {
        this.isAktif = isAktif;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

}
