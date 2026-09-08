package br.com.boaspraticas.modelo;

import java.util.List;

/**
 * Representa um aluno e as notas obtidas por ele no período avaliado.
 */
public class Aluno {

    private final String nome;
    private final List<Double> notas;

    public Aluno(String nome, List<Double> notas) {
        this.nome = nome;
        this.notas = List.copyOf(notas);
    }

    public String getNome() {
        return nome;
    }

    public List<Double> getNotas() {
        return notas;
    }
}
