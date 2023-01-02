package com.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Peminjaman {
    private int no;
    private Monograf monograf;
    private Member member;
    private String tglPinjam;
    private String tglTenggat;
    private String status;
    
    public Peminjaman(int no, Monograf monograf, Member member, String tglPinjam, String tglTenggat, String status) {
        this.no = no;
        this.monograf = monograf;
        this.member = member;
        this.tglPinjam = tglPinjam;
        this.tglTenggat = tglTenggat;
        this.status = status;
    }

    public String perpanjang() throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date_tenggat = df.parse(tglTenggat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date_tenggat);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        return df.format(cal.getTime());
    }

    
    //SETTER GETTER
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Monograf getMonograf() {
        return monograf;
    }
    public void setMonograf(Monograf monograf) {
        this.monograf = monograf;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public String getTglPinjam() {
        return tglPinjam;
    }
    public void setTglPinjam(String tglPinjam) {
        this.tglPinjam = tglPinjam;
    }
    public String getTglTenggat() {
        return tglTenggat;
    }
    public void setTglTenggat(String tglTenggat) {
        this.tglTenggat = tglTenggat;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
