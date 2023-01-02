package com.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.config.*;
import com.entities.Member;

public class MemberDAO {
    Connection con;
    Member m;

    //find
    public boolean isAda(int id) throws SQLException{
        con = Config.getConnection();
        if(con!=null){
            String query = "SELECT * FROM member WHERE no_member = "+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                m = new Member(rs.getInt("no_member"), rs.getBoolean("isAktif"), rs.getString("nama"));
                return true;
            }
        }
        return false;
    }

    //isvalid
    public boolean isValid(){
        return m.isValid();
    }
}
