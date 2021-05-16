package com.anuncios.models.dao;

import com.anuncios.models.Anuncio;

import java.time.LocalDate;
import java.util.List;

public interface AnuncioDAO {

    int insert(Anuncio anuncio);

    List<Anuncio> findAll();

    List<Anuncio> findByCliente(String cliente);

    List<Anuncio> findByDatas(LocalDate data_inicio, LocalDate data_final);

}
