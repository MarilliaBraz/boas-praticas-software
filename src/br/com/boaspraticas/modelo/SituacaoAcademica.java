package br.com.boaspraticas.modelo;

/**
 * Situações possíveis para o aluno após o cálculo da média final.
 */
public enum SituacaoAcademica {

    APROVADO("Aprovado"),
    REPROVADO("Reprovado");

    private final String descricao;

    SituacaoAcademica(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
