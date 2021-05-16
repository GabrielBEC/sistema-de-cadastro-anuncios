package com.anuncios.db;

import java.sql.*;

import static com.anuncios.db.Constants.*;

public class DB {

    private static Connection conn;

    public static Connection getConnection(){
        if(conn == null){
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatment(Statement st){
        if(st != null){
            try {
                st.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResult(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
