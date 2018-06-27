# Tralho integrado - Programação Orientada a Objetos I
```java
String nome_do_projeto = "EasyMarket (Desktop)";
```
### 1. COMPONENTES

```java
String aluno1 = "Antônio Carlos Durães da Silva";
String aluno2 = "Jennifer De Castro Gonçalves";
String aluno3 = "Joel Will Belmiro";
String aluno4 = "Larissa Santos Da Motta";
```

### 2. PROPÓSITO DO SISTEMA
<p align="justify">
O “EasyMarket” é um sistema que traz como principal proposta a informatização parcial ou total de alguns processos dentro de estabelecimentos comerciais, neste caso, supermercados. Os processos alvos de informatização parcial ou total são: Controle de produtos e seus lotes, controle de funcionários, registro de fornecedores e de clientes.<br><br>
A versão móvel do sistema busca atender a demanda de clientes do estabelecimento, de modo a oferecer-lhes comodidade na palma da mão, por meio de recursos como disponibilização das informações (Nome, preço, unidades) de cada produto adicionado à sua compra, contagem e contabilização dos itens sem a necessidade de passá-los por um operador de caixa, controle de meios de pagamentos como cartão de crédito ou débito. Além disso, há disponível um histórico de compras com informações dos produtos adquiridos e estabelecimento, o que possibilita acompanhar quanto e com que itens suas economias estão sendo gastas em cada supermercado.<br><br>
Dessa forma, o estabelecimento desfrutará não só do controle de suas mercadorias mas também de informações de seus colaboradores, clientes e fornecedores, em um único sistema.</p><br>

### 3. MINIMUNDO
<p align="justify">
O estabelecimento opera revendendo produtos adquiridos de um fornecedor ou comercializando itens de fabricação própria para clientes.<br><br>
Todo funcionário do estabelecimento deve ser registrado com informações pessoais como CPF, data de nascimento, nome, sexo, um endereço e, pelo menos, uma forma de contato, além do cargo e setor em que trabalha.<br><br>
Sempre que um produto novo, nunca vendido antes pelo supermercado, for adquirido de algum fornecedor ou confeccionado para venda aos clientes, ele deve ser registrado com sua categoria, código de barras, custo, marca, nome, preço de venda, quantidade na prateleira, quantidade no estoque, além de poder ter uma descrição.<br><br>
Para facilitar a comunicação com seu(s) fornecedor(es), o supermercado necessita do registro do nome, endereço, pelo menos um meio de contato (e-mail, celular, telefone fixo), além de armazenar o CNPJ de quem lhe fornece mercadorias.<br><br>
Quando o supermercado negocia com algum fornecedor, ele costuma adquirir um ou mais lotes de produtos. Informações sobre os lotes dos produtos como a data de fabricação, data de compra, data de validade e seu número de identificação, são bastante relevantes, pois caso algum item venha apresentar problemas de fabricação, por exemplo, é por meio do lote do produto que outras unidades serão identificadas e retiradas de circulação.<br><br>
Durante a compra, o cliente do supermercado deve escolher os produtos desejados e colocá-los no carrinho. Ao final da compra, se todas informações de pagamento do cliente estiverem corretas, então basta que ele escolha um cartão de crédito ou débito e realize o pagamento. Se o pagamento foi feito sem problemas, o cliente receberá uma nota fiscal eletrônica em seu e-mail e poderá consultar qual, quantas unidades e preço de cada produto comprado, além de saber a data, hora e em qual supermercado a compra foi realizada.<br><br>
Para o supermercado conhecer melhor as preferências e a proximidade com seus compradores, espera-se registrar informações pessoais (CPF, data de nascimento, nome, sexo, um endereço) de seus clientes, além de um ou mais meios de contatos e seu(s) cartão(ões) usados para pagar suas compras.
</p><br>

### 4. DIAGRAMA DE CLASSES

- Obs.1: Optou-se por não incluir os métodos das classes no diagrama para melhorar a visibilidade do modelo.
- Obs.2: Optou-se por não incluir ou criar outro diagrama com classes que dizem respeito ao padrão de projeto DAO, Testes e GUI.

<p align="center"><img src="https://github.com/duraes-antonio/rfidmarket_POO1/blob/master/imagens/classes_DIAG.png"></p><br>

### 5. MODELO CONCEITUAL - BANCO DE DADOS I
<p align="center"><img src="https://github.com/duraes-antonio/rfidmarket_POO1/blob/master/imagens/mod_CONCEITUAL.png"></p><br>

### 6. MODELO LÓGICO - BANCO DE DADOS I
<p align="center"><img src="https://github.com/duraes-antonio/rfidmarket_POO1/blob/master/imagens/mod_LOGICO.png"></p>

### 7. DESCRIÇÃO DA TECNOLOGIA UTILIZADA

- API de backend "EasyMarket": Usado para instaciar objetos do modelo, fazer o CRUD e outras operações que precisam de acesso ao banco de dados.
- [ControlsFX](v8.40.14)(https://bitbucket.org/controlsfx/controlsfx/): Complemento para o Core do FX, escolhido por fornece um número expressivo de componentes para serem utilizados em GUI.
- [FontAwesomeFX](v8.9)(https://bitbucket.org/Jerady/fontawesomefx): Ferramenta que fornece centenas de ícones. 
- [Java 8](https://www.java.com/pt_BR/download/): Linguagem fortemente orientada a objetos, usada por permitir maior portabilidade e por estar mais próxima dos integrantes do grupo de acordo com as ferramentas que o grupo achou necessário fazer uso.
- [JavaFX](http://www.oracle.com/technetwork/pt/java/javafx/overview/index.html): Plataforma usada para desenvolvimento da interface gráfica do sistema (GUI) por prover estilos que agradaram mais o grupo que os disponibilizados pelo toolkit Swing.
- [JSON](http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm): JavaScript Object Notation, formato utilizado como dependência de uso do Webservice ViaCEP.
- [Junit 4](https://junit.org/junit4/): Frammework utilizada para testes unitários das classes.
- [PostegreSQL](https://www.postgresql.org/) (v42.2.2): Sistema gerenciador de banco de dados utilizado para concretizar a persistência dos dados. A opção se deu pela experiência do grupo com o sistema e com sua robustez (se comparado com ferramentas como o SQLite).
- [JDBC](https://jdbc.postgresql.org/download.html): API com itens escritos em Java para realizar envio de instruções SQL para qualquer banco de dados relacional. A versão escolhida possui seu driver voltado para PostgreSQL.
- [ViaCEP](https://gitlab.com/parg/ViaCEP): Webservice (modificado pelo grupo) para obter os dados de um endereço a partir de seu CEP.
