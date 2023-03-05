# Ada's Feed :rocket:
Bem-vindo ao projeto <b>Ada's Feed!</b>

A ideia desse repositório é dividirmos as aulas em branches para termos uma proximidade maior do que acontece no dia a dia do programador Android :robot:. Para isso, ao longo desse módulo vamos desenvolver o seguinte projeto:
- [Figma do projeto](https://www.figma.com/file/JhP1GD7IkFsVryztoW3q09/Untitled?node-id=0%3A1&t=VM9Dn01siLwbqNsa-1)

| Tela do feed | Tela de perfil |
|---|---|
| ![Design](https://user-images.githubusercontent.com/62938087/219476521-5d83e62e-b95c-4216-971a-8586f7a8ec75.png) | ![Design profile](https://user-images.githubusercontent.com/62938087/219476543-5b0f0867-e728-49bd-9530-5fc33243474c.png) |

### Setup do projeto
- Faça o clone do projeto: `git clone https://github.com/VictorMagosso/AdasFeed.git`
- Em `File > Settings (se for macOS,  Android Studio > Preferences) > Build, Execution, Deployment > Build Tools > Gradle` e na versão da JDK selecione `Use Embedded JDK`)
- Sincronize as dependências clicando no ícone do gradle na parte superiro à direita (elefantinho com seta azul)

### Divisão das branches
O projeto é <b>NOSSO</b>, então fique à vontade para subir alterações. Basta criar uma branch no padrão de gitflow (`feature`, `bugfix`, `refactor`). As branches com nome reservado `feature/aula-<numero_da_aula>` terá todos os avanços do nosso projeto. Por exemplo: Na branch `feature/aula-2` terá tudo o que tem na branch `feature/aula-1` mais o que criaremos na aula 2. Na `feature/aula-3` terá tudo o que tem na `1` e `2` e assim por diante.

# Exercício final
Como colocado no class e informado no discord, iremos para a última atividade do módulo:

Lembre-se de seguir os padrões antes de começar o desenvolvimento: gitflow, com a branch nomeada da seguinte forma: `nomedoaluno/feature/oque-vou-implementar` (exemplo: `git checkout -b victormagosso/feature/add-bundles-and-intents`). Ao finalizar, abram um Pull request para a branch `main` e selecionem colegas para revisar o código de vocês. Iremos corrigir e visualizar os PRs na sexta-feira (10, última aula nossa).

Abaixo estão as atividades (estarão no class e no Discord também!!!), e cada feature tem um desafio acompanhado:

### Feature 1: 
- Em ProfileActivity, adicionar evento para mudar o nome e o nickname quando forem preenchidos
e clicado no botão de salvar (FloatingActionButton abaixo)
**DESAFIO: mudar o nome conforme fomos preenchendo o campo EditText

- Implementar botão “Voltar para o feed” para retornar para a primeira tela
COM AS INFORMAÇÕES ATUALIZADAS<br>

<img src="https://user-images.githubusercontent.com/62938087/222975383-0f27da75-32ba-40c3-b152-5bed19d8f398.png" 
width="415"/><br>
### Feature 2: 
- Em FeedActivity, remover um post da lista ao manter o item pressionado. Ao deletar, o usuário deverá
receber um feedback através de uma Snackbar
**DESAFIO: Implementando coroutines, dê ao usuário a chance de desfazer a ação de remover
um item. Ele terá 5 segundos para toamr essa decisão. Caso contrário, siga com a remoção

- Adicionar o estado completo de “Like” e adicionar em uma lista (List<LikedPost>). Apenas para check,
deverá ser exibido um Toast com os itens que estão nessa lista sempre que o usuário clicar
no ícone de coração
<img src="https://user-images.githubusercontent.com/62938087/222975406-9fa486d7-79bb-4d18-b533-32ab09dffd16.png" width="415"/><br>
### Contribuidores

 - @francineeliza
 - @paulohssouza
 - @Araujo-Raiara
 - @rafael-rosa1
 - @udimile
 - @GabrielTamura97
 - @laura-lannab
 - @on-ferreira
 - @eduardo-assimo
 - @juliamarqss
 - @jcmalmeida
