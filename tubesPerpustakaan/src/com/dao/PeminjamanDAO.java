package com.dao;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.entities.*;
import com.config.*;

public class PeminjamanDAO{
    private Connection con;

    //read all
    public List<Peminjaman> getAll() throws SQLException{
        con = Config.getConnection();
        List<Peminjaman> listPinjam = new ArrayList<>();
        if(con!=null){
            String sql = "SELECT * FROM peminjaman ORDER BY no ASC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int no = rs.getInt("no");
                int id_koleksi = rs.getInt("id_koleksi");
                int no_member = rs.getInt("no_member");
                String tgl_pinjam = rs.getString("tgl_pinjam");
                String tenggat = rs.getString("tgl_tenggat");
                String status = rs.getString("status");
                Peminjaman pinjam = new Peminjaman(no, (new Monograf(null, 0, id_koleksi, null, null, 0)), (new Member(no_member, true, null)), tgl_pinjam, tenggat, status);
                listPinjam.add(pinjam);
            }
        }
        return listPinjam;
    }

    //insert
    public int insert(Peminjaman p) throws SQLException{
        con = Config.getConnection();
        int no = 0;
        if(con!=null){
            String query = "SELECT jumlah FROM monograf WHERE id_koleksi = "+p.getMonograf().getId();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean mg_isVerified = false;
            while(rs.next()){
                p.getMonograf().setJumlah(rs.getInt("jumlah"));
                mg_isVerified = p.getMonograf().diPinjam();
                break;
            }
            query = "SELECT isAktif FROM member WHERE no_member = "+p.getMember().getNoMember();
            rs = st.executeQuery(query);
            boolean mb_isVerified = false;
            while(rs.next()){
                p.getMember().setIsAktif(rs.getBoolean("isAktif"));
                mb_isVerified = p.getMember().isValid();
                break;
            }
            if(mg_isVerified && mb_isVerified){
                String sql = "INSERT INTO peminjaman (id_koleksi, no_member, tgl_pinjam, tgl_tenggat, status) VALUES ("+p.getMonograf().getId()+", "+p.getMember().getNoMember()+", DATE_FORMAT('"+p.getTglPinjam()+"', '%Y-%m-%d'), DATE_ADD(DATE_FORMAT('"+p.getTglPinjam()+"', '%Y-%m-%d'), INTERVAL 1 WEEK), 'Dipinjam')";
                no = st.executeUpdate(sql);
                String update = "UPDATE monograf SET jumlah = jumlah - 1 WHERE id_koleksi = "+p.getMonograf().getId();
                st.executeUpdate(update);
            }
        }
        return no;
    }

    //update - perpanjang
    public int updatePerpanjang(Peminjaman p) throws SQLException, ParseException{
        con = Config.getConnection();
        int updatedRow = 0;
        if(con!=null){
            String query = "SELECT tgl_tenggat FROM peminjaman WHERE no = "+p.getNo();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            String tgl_tenggat = null;
            if(rs.next()){
                tgl_tenggat = rs.getString("tgl_tenggat");
                tgl_tenggat = p.perpanjang();
                p.setTglTenggat(tgl_tenggat);
            }
            if(tgl_tenggat!=null){
                String sql = "UPDATE peminjaman SET tgl_tenggat = '"+tgl_tenggat+"' WHERE no = "+p.getNo();
                updatedRow = st.executeUpdate(sql);
            }
        }
        return updatedRow;
    }

    //update - kembali
    public int updateStatus(Peminjaman p) throws SQLException{
        con = Config.getConnection();
        int updatedRow = 0;
        if(con!=null){
            String sql = "UPDATE peminjaman SET status = 'Kembali' WHERE no = "+p.getNo();
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            String query = "SELECT jumlah FROM monograf WHERE id_koleksi = "+p.getMonograf().getId();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                p.getMonograf().setJumlah(rs.getInt("jumlah"));
                p.getMonograf().kembali();
                sql = "UPDATE monograf SET jumlah = "+p.getMonograf().getJumlah()+ " WHERE id_koleksi = "+p.getMonograf().getId();
                updatedRow = st.executeUpdate(sql);
                break;
            }
        }
        return updatedRow;
    }

    //delete
    public int delete(int no) throws SQLException{
        con = Config.getConnection();
        int affectedRow = 0;
        if(con!=null){
            String sql = "DELETE FROM peminjaman WHERE no = "+no;
            Statement st = con.createStatement();
            affectedRow = st.executeUpdate(sql);
        }
        return affectedRow;
    }
}