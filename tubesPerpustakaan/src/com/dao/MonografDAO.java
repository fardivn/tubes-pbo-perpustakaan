package com.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.entities.Monograf;
import com.config.*;

public class MonografDAO {
    Connection con;
    Monograf m;
    
    //read all
    public List<Monograf> getAll() throws SQLException{
        con = Config.getConnection();
        List<Monograf> listMonograf = new ArrayList<>();
        if(con!=null){
            String sql = "SELECT k.*, m.isbn, m.jumlah FROM koleksi AS k INNER JOIN monograf AS m ON k.id = m.id_koleksi ORDER BY k.judul ASC;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String judul = rs.getString("judul");
                String kreator = rs.getString("kreator");
                int tahun = rs.getInt("tahun");
                String isbn = rs.getString("isbn");
                int jumlah = rs.getInt("jumlah");
                Monograf m = new Monograf(isbn, jumlah, id, judul, kreator, tahun);
                listMonograf.add(m);
            }
        }
        return listMonograf;
    }

    //insert
    public int insert(Monograf m) throws SQLException{
        con = Config.getConnection();
        int id = 0;
        if(con!=null){
            String sql_1 = "INSERT INTO koleksi (judul, kreator, tahun) VALUES ('"+m.getJudul()+"', '"+m.getKreator()+"', '"+m.getTahun()+"')";
            String[] returnedID = {"id"};
            PreparedStatement ps_1 = con.prepareStatement(sql_1, returnedID);
            ps_1.executeUpdate();
            try(ResultSet rs = ps_1.getGeneratedKeys()){
                if(rs.next()){
                    id = rs.getInt(1);
                    String sql_2 = "INSERT INTO monograf (id_koleksi, isbn, jumlah) VALUES ("+id+", '"+m.getIsbn()+"', "+m.getJumlah()+")";
                    PreparedStatement ps_2 = con.prepareStatement(sql_2);
                    ps_2.executeUpdate();
                }
            }
        }
        return id;
    }

    //update
    public int update(Monograf m) throws SQLException{
        con = Config.getConnection();
        int id = 0;
        if(m.getId()==0){
            return insert(m);
        }
        if(con!=null){
            String sql_1 = "UPDATE monograf SET isbn = '"+m.getIsbn()+"', jumlah = "+m.getJumlah()+" WHERE id_koleksi = "+m.getId();
            Statement st = con.createStatement();
            id = st.executeUpdate(sql_1);
            String sql_2 = "UPDATE koleksi SET judul = '"+m.getJudul()+"', kreator = '"+m.getKreator()+"', tahun = "+m.getTahun()+" WHERE id = "+m.getId();
            id = st.executeUpdate(sql_2);
        }
        return id;
    }

    //find
    public boolean isAda(int id) throws SQLException{
        con = Config.getConnection();
        if(con!=null){
            String query = "SELECT * FROM monograf WHERE id_koleksi = "+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                m = new Monograf(rs.getString("isbn"), rs.getInt("jumlah"), rs.getInt("id_koleksi"), null, null, 0);
                return true;
            }
        }
        return false;
    }

    //isValid
    public boolean isValid(){
        return m.isAvailable();
    }


}
