package com.anuncios.db;

public class Constants {
    protected static final String DATABASE = "capg_anuncio";
    protected static final String HOST = "jdbc:mysql://localhost/";
    protected static final String USER = "root";
    protected static final String PASSWORD = "";
    protected static final String URL = HOST + DATABASE + "?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC&useSSL=false";
}
