package com.anuncios.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculo {

    public static int visualizacaoPorValorInvestido(double valorInvestido) {
        return (int) Math.round(valorInvestido * 30);
    }

    public static int cliquesPorVisualizacao(int visualizacoesPorInvestimento) {
        return Math.round((visualizacoesPorInvestimento / 100) * 12);
    }

    public static int compartilhamentosPorCliques(int pessoasQueClicaram) {
        int result = ((pessoasQueClicaram / 20) * 3) >= (4 * 3) ? (4 * 3) : (pessoasQueClicaram / 20) * 3;
        return result;
    }

    public static int visualizacaoPorCompartilhamento(int pessoasQueCompartilharam) {
        return pessoasQueCompartilharam * 40;
    }

    public static int totalDeVisualizacao(int visualizacoesPorInvestimento, int visualizacaoPorCompartilhar){
        return visualizacoesPorInvestimento + visualizacaoPorCompartilhar;
    }

    public static double valorTotalInvestido(double valorInicial, LocalDate dataInicial, LocalDate dataFinal){
        int quantidadeDeDias = (int) ChronoUnit.DAYS.between(dataInicial, dataFinal);
        return valorInicial * quantidadeDeDias;
    }

}
