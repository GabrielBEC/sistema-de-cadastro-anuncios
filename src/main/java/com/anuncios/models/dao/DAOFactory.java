package com.anuncios.models.dao;

import com.anuncios.db.DB;
import com.anuncios.models.dao.impl.AnuncioDAOJDBC;

public class DAOFactory {

    public static AnuncioDAO createAnuncioDAO(){
        return new AnuncioDAOJDBC(DB.getConnection());
    }


}
