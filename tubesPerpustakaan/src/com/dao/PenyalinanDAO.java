package com.dao;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;

import com.entities.Member;
import com.entities.Penyalinan;
import com.entities.Video;
import com.config.*;

public class PenyalinanDAO {
    Connection con;

    //read all
    public List<Penyalinan> getAll() throws SQLException{
        con = Config.getConnection();
        List<Penyalinan> list = new ArrayList<>();
        if(con!=null){
            String sql = "SELECT * FROM penyalinan ORDER BY no ASC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int no = rs.getInt("no");
                int id_koleksi = rs.getInt("id_koleksi");
                int no_member = rs.getInt("no_member");
                String tgl_penyalinan = rs.getString("tgl_penyalinan");
                Penyalinan p = new Penyalinan(no, (new Video(0, false, id_koleksi, null, null, 0)), (new Member(no_member, false, null)), tgl_penyalinan);
                list.add(p);
            }
        }
        return list;
    }

    //insert
    public int insert(Penyalinan p) throws SQLException{
        con = Config.getConnection();
        int no = 0;
        if(con!=null){
            String query = "SELECT isPublik FROM video WHERE id_koleksi = "+p.getVideo().getId();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean v_isVerified = false;
            while(rs.next()){
                p.getVideo().setIsPublik(rs.getBoolean("isPublik"));
                v_isVerified = p.getVideo().isAvailable();
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
            if(v_isVerified && mb_isVerified){
                String sql = "INSERT INTO penyalinan (id_koleksi, no_member, tgl_penyalinan) VALUES ("+p.getVideo().getId()+", "+p.getMember().getNoMember()+", DATE_FORMAT('"+p.getTglPenyalinan()+"', '%Y-%m-%d'))";
                no = st.executeUpdate(sql);
            }
        }
        return no;
    }

    //delete
    public int delete(int no) throws SQLException{
        con = Config.getConnection();
        int affectedRow = 0;
        if(con!=null){
            String sql = "DELETE FROM penyalinan WHERE no = "+no;
            Statement st = con.createStatement();
            affectedRow = st.executeUpdate(sql);
        }
        return affectedRow;
    }
}
