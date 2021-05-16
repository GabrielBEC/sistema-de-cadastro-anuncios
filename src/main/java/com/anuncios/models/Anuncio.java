package com.anuncios.models;

import java.time.LocalDate;

public class Anuncio {

    private Integer id;
    private String nome;
    private String cliente;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private Double investimentoPorDia;

    public Anuncio() {
    }

    public Anuncio(Integer id, String nome, String cliente, LocalDate dataInicio, LocalDate dataTermino, Double investimentoPorDia) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.investimentoPorDia = investimentoPorDia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Double getInvestimentoPorDia() {
        return investimentoPorDia;
    }

    public void setInvestimentoPorDia(Double investimentoPorDia) {
        this.investimentoPorDia = investimentoPorDia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio)) return false;

        Anuncio anuncio = (Anuncio) o;

        return id != null ? id.equals(anuncio.id) : anuncio.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cliente='" + cliente + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTermino=" + dataTermino +
                ", investimentoPorDia=" + investimentoPorDia +
                '}';
    }

}
