package br.com.boaspraticas.servico;

import br.com.boaspraticas.modelo.Aluno;
import br.com.boaspraticas.modelo.SituacaoAcademica;

import java.util.List;

/**
 * Concentra as regras de avaliação acadêmica: cálculo da média e
 * definição da situação final do aluno.
 */
public class AvaliadorDeAluno {

    private static final double MEDIA_MINIMA_APROVACAO = 6.0;

    public double calcularMedia(Aluno aluno) {
        List<Double> notas = aluno.getNotas();

        if (notas.isEmpty()) {
            throw new IllegalArgumentException(
                    "O aluno precisa ter ao menos uma nota para o cálculo da média.");
        }

        double somaDasNotas = 0.0;
        for (double nota : notas) {
            somaDasNotas += nota;
        }

        return somaDasNotas / notas.size();
    }

    public SituacaoAcademica verificarSituacao(double media) {
        return media >= MEDIA_MINIMA_APROVACAO
                ? SituacaoAcademica.APROVADO
                : SituacaoAcademica.REPROVADO;
    }
}
