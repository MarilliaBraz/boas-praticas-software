package br.com.boaspraticas.apresentacao;

import br.com.boaspraticas.modelo.Aluno;
import br.com.boaspraticas.modelo.SituacaoAcademica;

/**
 * Responsável apenas por apresentar o resultado da avaliação ao usuário.
 * Isolar a exibição permite trocar a saída (console, arquivo, API) sem
 * alterar as regras de negócio.
 */
public class RelatorioDeAvaliacao {

    public void exibir(Aluno aluno, double media, SituacaoAcademica situacao) {
        System.out.printf("Aluno: %s%n", aluno.getNome());
        System.out.printf("Média: %.2f%n", media);
        System.out.printf("Situação: %s%n", situacao.getDescricao());
    }
}
