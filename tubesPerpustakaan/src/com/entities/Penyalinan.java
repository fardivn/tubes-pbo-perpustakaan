package com.entities;

public class Penyalinan {
    private int no;
    private Video video;
    private Member member;
    private String tglPenyalinan;
    
    public Penyalinan(int no, Video video, Member member, String tglPenyalinan) {
        this.no = no;
        this.video = video;
        this.member = member;
        this.tglPenyalinan = tglPenyalinan;
    }


    //SETTER GETTER
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Video getVideo() {
        return video;
    }
    public void setVideo(Video video) {
        this.video = video;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public String getTglPenyalinan() {
        return tglPenyalinan;
    }
    public void setTglPenyalinan(String tglPenyalinan) {
        this.tglPenyalinan = tglPenyalinan;
    }

}
