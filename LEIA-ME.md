# Revisão aplicada ao TrabalhoLoja

As correções foram aplicadas em C:\Users\pedro\Documents\Codex\TrabalhoLoja.
O backup anterior à aplicação está em TrabalhoLoja-antes-da-aplicacao-20261015-220022.zip, na pasta outputs desta conversa.

## Alterações
- ProdutoDao: substituído o trecho incompleto por uma implementação de cadastro.
- Produto: criada uma classe mínima com código, nome e preço em BigDecimal.
- ClienteDao: implementado cadastro usando CPF como chave.
- Crud<T>: recebe os dados das operações e devolve List, Optional e resultados booleanos.
- DaoEmMemoria<T>: concentra a implementação compartilhada, rejeita duplicatas e protege a estrutura da lista retornada.
- Base e Endereco: adicionados acessores; Base inicializa a data no construtor vazio.
- Main: demonstra o cadastro e a listagem de um produto.
- VerificacaoCrud: verificação executável de produtos e clientes, sem bibliotecas externas.
- Os pacotes existentes e o código de Cliente foram preservados.
- O pom.xml e o SDK do IntelliJ continuam em Java 26, que foi localizado no computador.

## Validação
Todos os fontes foram compilados com o JDK 26 instalado e -Xlint:all, sem erros ou avisos.
VerificacaoCrud executou com sucesso. Main imprimiu:
P001 - Caderno - R$ 15.90

A execução completa do Maven ficou bloqueada: faltam dependências dos plugins e a sessão não conseguiu resolver repo.maven.apache.org. Isso não é erro de compilação dos fontes. Ao abrir o projeto no IntelliJ com acesso à internet, recarregue o Maven para baixar essas dependências. A verificação VerificacaoCrud é um programa Java e deve ser executada diretamente; não é executada automaticamente por Maven test.

## Como continuar no IntelliJ
Abra a pasta C:\Users\pedro\Documents\Codex\TrabalhoLoja pelo menu File > Open.
Use o SDK 26 já configurado. Execute org.aula.Main.
Para conferir as operações, execute org.aula.VerificacaoCrud, dentro de src/test/java.

## Limites da proposta
Os dados ficam em memória e desaparecem ao encerrar a execução.
Não foi fornecido o enunciado: código, nome e preço são os campos mínimos propostos para Produto. Não foram adicionados banco, telas ou regras completas de validação de CPF.
Não altere o CPF de um Cliente depois de cadastrá-lo, pois ele identifica o registro; para trocar, exclua pela chave antiga e cadastre novamente.
A lista devolvida não pode sofrer inclusão/remoção, mas seus objetos continuam mutáveis.


## Execucao local sem Maven
O arquivo Executar.ps1 compila todos os fontes com Java 26, executa as verificacoes e inicia Main, sem downloads. Execute-o pelo PowerShell na pasta do projeto com: .\Executar.ps1
Foram adicionadas configuracoes Main e VerificacaoCrud na pasta .run para uso no IntelliJ.
A execucao de Executar.ps1 foi validada com sucesso.
A abertura automatica no IntelliJ nao foi concluida: o dialogo de selecao nao respondeu ao controle da interface. Abra a pasta manualmente por File > Open.
