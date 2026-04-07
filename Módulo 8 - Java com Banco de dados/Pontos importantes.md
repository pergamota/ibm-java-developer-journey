
# 1. **Repositórios**

* É onde o código fica pronto para conversar com o banco de dados: salvar, buscar um id específico...
  
# 2. **Serviços**

* Aplica regras para os repositórios como: confirmar se uma conta deve ser excluída após o usuário clicar em DELETE.
  
# 3. **Controles**

* É a parte que lida com as requisições HTTP, ou seja, quando o usuário clica em algum botão, os controles são responsáveis pela interação.
  
  
# Anotações de forma simplificada:


# @**Repository**

* Marca classes que cuidam de acesso a dados. 
* Onde normalmente você escreve consultas para o banco de dados. 

# @**Service**

* Marca classes que contêm **regras de negócio**.
  
# @**Autowired**

* Normalmente fica em service, ou seja, é uma integração de repository e service, logo, quando você criar, por exemplo: buscarId(), o service precisa injetar essa dependência, então você coloca @Autowired antes de buscar uma conexão com alguma classe do repositório e automaticamente ele injeta isso.
  
# @**Transactional**

* Diz que um método ou classe deve rodar dentro de uma **transação**.Use quando um serviço precisa seguir várias etapas juntas, por exemplo, um pagamento que precisa verificar o valor na conta, se foi debitado e destino final, se alguma falhar, tudo falha.
  
# @**RestControllerAdvice**

* Trata exceções de forma global nos controllers.


# @**ExceptionHandler**

* Marca um método que lida com uma exceção específica.
  

# @**ResponseStatus**

* Marca um método para definir qual HTTP deve ser devolvido. Por exemplo, que quando algo não é encontrado, retorne 404.
  
  
# @**RequestMapping**

* Define mapeamento de requisições a um controller ou método.
  
# @**GetMapping**

* Atalho para `@RequestMapping` de método GET, usado para **ler** dados.

# @**PostMapping** 

* Criar dados.
  
# @**PutMapping**

* Atualizar dados
  
# @**DeleteMapping**

* Apagar dados.
  

# @**Entity**

* Marca uma classe como entidade.
  

# @**Id**

* Marca a chave primária.
   
# @**Table**

* Define detalhes do nome da tabela.
  
# @**JoinColumn**

* É tipo uma chave estrangeira.
  
  
# @**Query**

* Permite escrever uma consulta personalizada diretamente no repositório.
  
  
# **@Modifying**: 

* Usado junto com `@Query` quando a consulta **altera dados** — update, delete, insert
  
  
# **O que é JPA**

* Ele é um tradutor, ele pega o código Java e transforma em tabelas para o banco de dados entender.
   
# **O que é JSON**

* Ele pega um código java e converte em texto porque assim o back-end e o front-end podem conversar.
  

# **O que são endpoints:**

* É um URL + uma requisição, por exemplo: GET/produtos.
  
  
# **O que são beans:**

* É um objeto em java que o spring cuida pra você, por exemplo, quando você aplica uma anotação em uma classe, você deixa claro para o spring que aquele objeto é importante, então você não precisa criar toda vez um objeto, ele é criado automaticamente.