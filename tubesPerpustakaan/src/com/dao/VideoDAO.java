package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.config.*;
import com.entities.Video;

public class VideoDAO {

    Connection con;
    Video v;
    
    //read all
    public List<Video> getAll() throws SQLException{
        con = Config.getConnection();
        List<Video> listVideo = new ArrayList<>();
        if(con!=null){
            String sql = "SELECT k.*, v.durasiMenit, v.isPublik FROM koleksi AS k INNER JOIN video AS v ON k.id = v.id_koleksi ORDER BY k.judul ASC;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String judul = rs.getString("judul");
                String kreator = rs.getString("kreator");
                int tahun = rs.getInt("tahun");
                int durasiMenit = rs.getInt("durasiMenit");
                boolean isPublik = rs.getBoolean("isPublik");
                Video v = new Video(durasiMenit, isPublik, id, judul, kreator, tahun);
                listVideo.add(v);
            }
        }
        return listVideo;
    }

    //insert
    public int insert(Video v) throws SQLException{
        con = Config.getConnection();
        int id = 0;
        if(con!=null){
            String sql_1 = "INSERT INTO koleksi (judul, kreator, tahun) VALUES ('"+v.getJudul()+"', '"+v.getKreator()+"', '"+v.getTahun()+"')";
            String[] returnedID = {"id"};
            PreparedStatement ps_1 = con.prepareStatement(sql_1, returnedID);
            ps_1.executeUpdate();
            try(ResultSet rs = ps_1.getGeneratedKeys()){
                if(rs.next()){
                    id = rs.getInt(1);
                    String sql_2 = "INSERT INTO video (id_koleksi, durasiMenit, isPublik) VALUES ("+id+", "+v.getDurasi()+", "+v.isPublik()+")";
                    PreparedStatement ps_2 = con.prepareStatement(sql_2);
                    ps_2.executeUpdate();
                }
            }
        }
        return id;
    }

    //update
    public int update(Video v) throws SQLException{
        con = Config.getConnection();
        int id = 0;
        if(v.getId()==0){
            return insert(v);
        }
        if(con!=null){
            String sql_1 = "UPDATE video SET durasiMenit = "+v.getDurasi()+", isPublik = "+v.isPublik()+ " WHERE id_koleksi = "+v.getId();
            Statement st = con.createStatement();
            id = st.executeUpdate(sql_1);
            String sql_2 = "UPDATE koleksi SET judul = '"+v.getJudul()+"', kreator = '"+v.getKreator()+"', tahun = "+v.getTahun()+" WHERE id = "+v.getId();
            id = st.executeUpdate(sql_2);
        }
        return id;
    }

    //find
    public boolean isAda(int id) throws SQLException{
        con = Config.getConnection();
        if(con!=null){
            String query = "SELECT * FROM video WHERE id_koleksi = "+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                v = new Video(rs.getInt("durasiMenit"), rs.getBoolean("isPublik"), rs.getInt("id_koleksi"), null, null, 0);
                return true;
            }
        }
        return false;
    }
    
    //isvalid
    public boolean isValid(){
        return v.isAvailable();
    }
}
