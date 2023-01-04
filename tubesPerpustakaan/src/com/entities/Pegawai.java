package com.entities;

public class Pegawai implements IVerifikasi{
    private int id;
    private String password;
    private String nama;

    public Pegawai(int id, String password, String nama) {
        this.id = id;
        this.password = password;
        this.nama = nama;
    }

    public boolean isValid() {
        return false;
    }

    //overloading dari overriden method isValid dari interface IVerifikasi
    public boolean isValid(int id, String pass){
        if(this.id==id && password.equals(pass)){
            return true;
        } else{
            return false;
        }
    }



    //SETTER GETTER
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    
    
}
