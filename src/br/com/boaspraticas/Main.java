package br.com.boaspraticas;

import br.com.boaspraticas.apresentacao.RelatorioDeAvaliacao;
import br.com.boaspraticas.modelo.Aluno;
import br.com.boaspraticas.modelo.SituacaoAcademica;
import br.com.boaspraticas.servico.AvaliadorDeAluno;

import java.util.List;

/**
 * Ponto de entrada da aplicação. Apenas orquestra a chamada dos módulos,
 * sem conter regra de negócio nem lógica de exibição.
 */
public class Main {

    public static void main(String[] args) {
        Aluno aluno = new Aluno("Carlos", List.of(8.0, 7.0));

        AvaliadorDeAluno avaliador = new AvaliadorDeAluno();
        RelatorioDeAvaliacao relatorio = new RelatorioDeAvaliacao();

        double mediaFinal = avaliador.calcularMedia(aluno);
        SituacaoAcademica situacao = avaliador.verificarSituacao(mediaFinal);

        relatorio.exibir(aluno, mediaFinal, situacao);
    }
}
