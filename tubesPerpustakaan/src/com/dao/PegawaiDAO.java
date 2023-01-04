package com.dao;

import java.sql.*;
import com.entities.*;
import com.config.*;

public class PegawaiDAO {
    Connection con;

    //login
    public boolean login(int idInput, String passInput) throws SQLException{
        Pegawai p = new Pegawai(idInput, passInput, null);
        con = Config.getConnection();
        if(con!=null){
            String sql = "SELECT id_pegawai, password FROM pegawai WHERE id_pegawai = "+idInput+";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int idDB = 0;
            String passDB = null;
            if(rs.next()){
                idDB = rs.getInt("id_pegawai");
                passDB = rs.getString("password");
                if(p.isValid(idDB, passDB)){
                    return true;
                } else{
                    return false;
                }
            } else{
                return false;
            }
        } 
        return false;
    }

    //find
    public boolean isAda(int id) throws SQLException{
        con = Config.getConnection();
        if(con!=null){
            String query = "SELECT * FROM pegawai WHERE id_pegawai = "+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }
        return false;
    }
}
