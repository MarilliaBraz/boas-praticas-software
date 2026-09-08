# boas-praticas-software

Atividade prática da disciplina **Manutenção e Configuração de Software** — Normas de Configuração, Boas Práticas e Git.

O projeto parte de um pequeno sistema que calcula a média de um aluno e informa sua situação. O código original funcionava, mas apresentava problemas de organização e legibilidade. A refatoração aplica código auto comentado, modularização e padronização.

## Estrutura do projeto

```
src/
└── br/com/boaspraticas/
    ├── Main.java                          # ponto de entrada, apenas orquestra
    ├── modelo/
    │   ├── Aluno.java                     # dados do aluno
    │   └── SituacaoAcademica.java         # enum: APROVADO / REPROVADO
    ├── servico/
    │   └── AvaliadorDeAluno.java          # regras: média e situação
    └── apresentacao/
        └── RelatorioDeAvaliacao.java      # exibição dos resultados
```

## Como executar

```bash
javac -d out $(find src -name "*.java")
java -cp out br.com.boaspraticas.Main
```

Saída esperada:

```
Aluno: Carlos
Média: 7,50
Situação: Aprovado
```

---

## Questão final

### 1. Qual era o principal problema do código original?

O principal problema era a **falta de legibilidade somada à ausência de separação de responsabilidades**.

Os nomes das variáveis (`n`, `a`, `b`, `c`) não comunicavam nada sobre o que armazenavam, o que obrigava quem lia o código a reconstruir mentalmente a intenção de cada linha. Além disso, todo o programa estava concentrado dentro do método `main`: entrada de dados, cálculo da média, decisão de aprovação e exibição dos resultados ocupavam o mesmo bloco.

Havia ainda um número mágico (`6`) escrito diretamente na condição, sem indicar que representava a média mínima para aprovação. Como visto na aula, código que não segue boas práticas transforma pequenas modificações em tarefas difíceis e arriscadas, e é exatamente nesse ponto que se concentra a maior parte do custo de manutenção de um software.

### 2. Quais melhorias você realizou?

**a) Nomes descritivos (código auto comentado)**

| Original | Refatorado |
|---|---|
| `n` | `nome` (atributo de `Aluno`) |
| `a`, `b` | `notas` (lista de notas do aluno) |
| `c` | `mediaFinal` |
| `Sistema` | `Main`, `Aluno`, `AvaliadorDeAluno`, `RelatorioDeAvaliacao` |

**b) Modularização**

O programa foi dividido em módulos com responsabilidade única:

- `Aluno` — representa os dados;
- `AvaliadorDeAluno` — concentra as regras de negócio (`calcularMedia` e `verificarSituacao`);
- `RelatorioDeAvaliacao` — cuida apenas da exibição;
- `Main` — apenas orquestra a chamada dos módulos.

**c) Código auto comentado**

Os nomes de classes e métodos explicam a intenção sem necessidade de comentário linha a linha. Os comentários que permaneceram são de documentação (Javadoc) e explicam decisões de projeto, e não o óbvio, seguindo o princípio de que comentários devem **complementar** o código, nunca substituir nomes claros.

**d) Padronização**

- classes em `PascalCase`, métodos e variáveis em `camelCase`, constantes em `UPPER_SNAKE_CASE`;
- indentação consistente de 4 espaços;
- organização em pacotes por responsabilidade (`modelo`, `servico`, `apresentacao`);
- um arquivo por classe pública.

**e) Melhorias adicionais**

- O número mágico `6` virou a constante `MEDIA_MINIMA_APROVACAO`, nomeando a regra e centralizando sua alteração em um único ponto.
- As strings `"Aprovado"` / `"Reprovado"` viraram o enum `SituacaoAcademica`, eliminando o risco de erro de digitação e tornando a situação um tipo verificado em tempo de compilação.
- O cálculo passou a operar sobre uma lista de notas, permitindo qualquer quantidade de avaliações em vez de duas fixas.
- Foi adicionada validação para o caso de lista de notas vazia, evitando divisão por zero.

### 3. Como a modularização facilitou a organização do código?

A modularização fez com que cada parte do sistema passasse a ter **uma única razão para mudar**.

Se a regra de aprovação mudar, o ajuste ocorre apenas em `AvaliadorDeAluno`. Se o formato de saída mudar, apenas `RelatorioDeAvaliacao` é alterado. No código original, qualquer uma dessas mudanças exigiria mexer no mesmo método `main`, aumentando o risco de quebrar algo não relacionado.

Os ganhos práticos observados foram os mesmos apontados na aula:

- **entendimento** — o nome do módulo já indica onde procurar cada coisa;
- **reutilização** — `AvaliadorDeAluno` pode ser usado por outro programa, por um relatório em lote ou por um teste automatizado;
- **redução de complexidade** — cada arquivo é curto e trata de um assunto só;
- **manutenção** — mudanças ficam localizadas e o impacto é previsível.

Um efeito colateral importante: o código modularizado se tornou testável. Com a lógica dentro do `main` e a saída acoplada ao `System.out`, não havia como verificar o cálculo da média de forma isolada.

### 4. Como o Git ajudou a controlar as alterações realizadas no sistema?

O Git registrou a **evolução do sistema ao longo do tempo**, e não apenas seu estado final.

- **Histórico de commits** — o código original permanece preservado no primeiro commit. É possível comparar as duas versões e demonstrar exatamente o que foi alterado, além de reverter qualquer mudança caso a refatoração introduzisse um defeito.
- **Branch (`melhoria-boas-praticas`)** — as melhorias foram desenvolvidas isoladamente, sem afetar a `main`. A versão estável permaneceu disponível durante todo o processo de refatoração.
- **Mensagens de commit** — funcionam como documentação do *porquê* de cada mudança, complementando o que o código mostra sobre o *como*.
- **Pull Request** — criou um ponto formal de revisão antes da integração, que é o mecanismo usado em equipes para aplicar as normas de configuração e os padrões internos acordados.
- **Merge** — consolidou as melhorias na `main` mantendo a rastreabilidade de origem de cada alteração.

Na prática, o Git é a ferramenta que torna a gerência de mudanças viável: sem ele, uma refatoração como esta seria uma substituição de arquivos, sem histórico, sem revisão e sem possibilidade de retorno.
