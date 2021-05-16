package com.anuncios.models.dao.impl;

import com.anuncios.db.DB;
import com.anuncios.db.DbException;
import com.anuncios.models.Anuncio;
import com.anuncios.models.dao.AnuncioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnuncioDAOJDBC implements AnuncioDAO {

    private Connection conn;

    public AnuncioDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(Anuncio anuncio) {
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO anuncio (nome, cliente, data_inicio, data_termino, investimento_dia) VALUES (?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);

            st.setString(1, anuncio.getNome());
            st.setString(2, anuncio.getCliente());
            st.setObject(3, anuncio.getDataInicio());
            st.setObject(4, anuncio.getDataTermino());
            st.setDouble(5, anuncio.getInvestimentoPorDia());

            int result = st.executeUpdate();
            return result;

        } catch (SQLException e) {
            DB.closeStatment(st);
        }
        return 0;
    }

    @Override
    public List<Anuncio> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM anuncio ORDER BY id";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Anuncio> list = new ArrayList<>();

            while (rs.next()) {
                Anuncio anuncio = instanciarAnuncio(rs);
                list.add(anuncio);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResult(rs);
        }
    }

    private Anuncio instanciarAnuncio(ResultSet rs) throws SQLException {
        Anuncio anuncio = new Anuncio();
        anuncio.setId(rs.getInt("id"));
        anuncio.setNome(rs.getString("nome"));
        anuncio.setCliente(rs.getString("cliente"));
        anuncio.setDataInicio(LocalDate.parse(rs.getString("data_inicio")));
        anuncio.setDataTermino(LocalDate.parse(rs.getString("data_termino")));
        anuncio.setInvestimentoPorDia(rs.getDouble("investimento_dia"));
        return anuncio;
    }

    @Override
    public List<Anuncio> findByCliente(String cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM anuncio WHERE cliente = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, cliente);
            rs = st.executeQuery();

            List<Anuncio> list = new ArrayList<>();

            while (rs.next()) {
                Anuncio anuncio = instanciarAnuncio(rs);
                list.add(anuncio);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResult(rs);
        }
    }

    @Override
    public List<Anuncio> findByDatas(LocalDate dataInicio, LocalDate dataFinal) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM anuncio WHERE data_inicio >= ? AND data_inicio <= ?";
            st = conn.prepareStatement(sql);
            st.setObject(1, dataInicio);
            st.setObject(2, dataFinal);

            rs = st.executeQuery();

            List<Anuncio> list = new ArrayList<>();

            while (rs.next()) {
                Anuncio anuncio = instanciarAnuncio(rs);
                list.add(anuncio);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatment(st);
            DB.closeResult(rs);
        }
    }

}
