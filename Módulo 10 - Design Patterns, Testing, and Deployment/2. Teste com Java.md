
# JUnit 
## O que é?

**JUnit** é o framework mais usado em Java para **testes unitários**.

Serve para testar automaticamente partes pequenas do sistema (métodos/classes).

Objetivo:

- encontrar erros cedo
- evitar bugs após mudanças
- dar segurança para refatorar
- melhorar qualidade do código

---

# O que você realmente precisa saber

## `@Test`

Marca um método como teste.

```
@Testvoid deveSomarCorretamente() {}
```

Sem essa anotação, o JUnit não executa o teste.

---

## Assertions

Usadas para verificar resultado esperado.

Exemplo:

```
assertEquals(150, conta.getSaldo());
```

Mais comuns:

- `assertEquals()`
- `assertTrue()`
- `assertFalse()`
- `assertNotNull()`

---

## `@BeforeEach`

Executa antes de cada teste.

Muito usado para preparar objetos.

```
@BeforeEachvoid setup() {    conta = new Conta(100);}
```

Cada teste começa limpo.

---

# Estrutura ideal de teste (AAA)

## Arrange

Preparar dados

## Act

Executar ação

## Assert

Validar resultado

Exemplo:

```
Conta conta = new Conta(100);   // Arrangeconta.depositar(50);            // ActassertEquals(150, conta.getSaldo()); // Assert
```

---

# Como nomear testes

Use nomes claros.

Exemplo:

```
deveAtualizarSaldoAoDepositar()deveRecusarSaqueSemSaldo()
```

Isso é valorizado.

---

# Casos que você deve testar

## Cenário normal

Funciona corretamente.

## Cenário inválido

Erro esperado.

## Limites

- zero
- vazio
- null
- valor máximo
- valor negativo
  
---

# AssertJ 

## O que é?

**AssertJ** é uma biblioteca Java usada para escrever **assertivas de teste** de forma mais clara e legível.

Ela melhora os testes tradicionais do JUnit com uma sintaxe fluida.

---

# Principal método

## `assertThat()`

Todo teste começa com:

```
assertThat(valor)
```

Depois você encadeia a validação esperada.

Exemplo:

```
assertThat(nota).isEqualTo(85);
```

Lê como:

> Verifique que nota é igual a 85.

---

# Assertions mais usadas

## `isEqualTo()`

Compara igualdade exata.

```
assertThat(nome).isEqualTo("Pedro");
```

Usado para:

- Strings
- Inteiros
- Valores exatos

---

## `isCloseTo()`

Usado para números decimais próximos.

```
assertThat(valor).isCloseTo(10.5, within(0.1));
```

Bom para cálculos com casas decimais.

---

## `isTrue()`

Valida booleano verdadeiro.

```
assertThat(resultado).isTrue();
```

---

## `isFalse()`

Valida booleano falso.

```
assertThat(resultado).isFalse();
```

---

# Vantagem do AssertJ

Comparado ao JUnit tradicional:

```
assertEquals(expected, actual)
```

Pode gerar dúvida sobre ordem.

Com AssertJ:

```
assertThat(actual).isEqualTo(expected);
```

Mais legível e natural.

---

# Maven (Dependência)

Adicionar no `pom.xml`:

```
<dependency>    
<groupId>org.assertj</groupId>
<artifactId>assertj-core</artifactId>
<version>3.24.2</version>    
<scope>test</scope>
</dependency>
```

## `scope test`

Significa que a biblioteca só será usada nos testes.

Não vai para produção.

---

# Resumo Final

AssertJ serve para criar testes mais claros e fáceis de ler.

## Estrutura padrão:

```
assertThat(valor).isEqualTo(resultadoEsperado);
```

## Benefícios:

- Melhor legibilidade
- Menos confusão
- Sintaxe fluida
- Integra bem com JUnit 5

---

## O que é TDD?

**TDD (Test-Driven Development)** é uma abordagem onde você **escreve os testes antes do código**.

Primeiro define o comportamento esperado, depois implementa.

---

# Ciclo principal do TDD

## 🔴 Red

Criar um teste que falha.

Mostra o que ainda precisa ser desenvolvido.

---

## 🟢 Green

Escrever o mínimo de código necessário para o teste passar.

Sem exagero.

---

## 🔵 Refactor

Melhorar e organizar o código sem quebrar os testes.

---

# Objetivo do TDD

- garantir comportamento correto
- detectar erros cedo
- melhorar design do código
- facilitar manutenção
- desenvolver em passos pequenos

---

# Exemplo simples

Quer criar depósito bancário.

## Red:

Teste dizendo:

```
depositar(100) => saldo = 100
```

Ainda falha.

## Green:

Criar método `depositar()` mínimo para passar.

## Refactor:

Melhorar estrutura interna depois.

---

# Ferramentas Java usadas com TDD

## JUnit

Executa os testes.

Usa:

```
@Test
```

---

## AssertJ

Deixa validações mais legíveis.

```
assertThat(saldo).isEqualTo(100);
```

---

## Parameterized Tests

Executa o mesmo teste com vários valores.

Exemplo:

- depósito 10
- depósito 50
- depósito 100

Sem repetir código.

---

# Benefícios práticos

## Código melhor estruturado

Você pensa no comportamento antes.

## Menos bugs

Problemas aparecem cedo.

## Refatoração segura

Os testes validam se tudo continua funcionando.

## Desenvolvimento incremental

Pequenos passos.

---

# Onde TDD é útil

- regras de negócio
- cálculos
- validações
- sistemas financeiros
- APIs críticas
- qualquer área que exige confiabilidade

---

# Boas práticas

- começar simples
- testar um comportamento por vez
- escrever teste pequeno
- fazer passar
- depois melhorar código

---
# Mockito 

## O que é?

**Mockito** é a biblioteca Java mais usada para criar **mocks** em testes.

Mock = objeto falso que imita uma dependência real.

Usado para testar classes sem depender de:

- banco de dados
- APIs externas
- email real
- gateway de pagamento
- serviços externos

---

# Objetivo principal

Testar uma classe **isoladamente**.

Exemplo:

`OrderService` depende de:

- PaymentGateway
- EmailService

No teste, você substitui essas dependências por mocks.

---

# Criando mocks

Pode usar:

```
mock(Classe.class)
```

ou anotação:

```
@Mock
```

---

# Stubbing (definir comportamento)

Ensinar o mock a responder algo específico.

```
when(payment.process(50.0)).thenReturn(true);
```

Significa:

Quando chamar esse método, retorne `true`.

---

# Injeção de mocks

```
@InjectMocks
```

Insere os mocks automaticamente na classe testada.

---

# Verification

Verifica se um método foi chamado.

```
verify(emailService).sendEmail();
```

Também pode usar:

```
times(1)never()
```

---

# Argument Matchers

Permitem aceitar qualquer valor.

```
anyString()anyDouble()eq("mensagem")
```

Útil quando o valor exato não importa.

---

# Exception Stubbing

Simular erro em dependência.

```
when(payment.process(anyDouble())).thenThrow(new Exception());
```

Serve para testar falhas.

---

# Múltiplos retornos

Simular comportamento mudando a cada chamada.

```
thenReturn(false, true)
```

Primeira chamada falha, segunda funciona.

---

# Benefícios

- testes rápidos
- sem acessar serviços reais
- mais controle nos cenários
- valida tratamento de erro
- isolamento da classe testada

---

# Exemplo real

Testar `OrderService`:

- pagamento aprovado
- email enviado
- retorno de sucesso

Tudo sem cobrar cartão real.

---
# Testcontainers em Java

## O que é?

**Testcontainers** é uma ferramenta que permite rodar **testes com serviços reais** (ex: banco de dados) usando containers.

Exemplos:

- PostgreSQL
- Redis
- RabbitMQ

---

# Problema que resolve

Sem Testcontainers:

- configurar banco manualmente
- conflitos de versão
- “funciona na minha máquina”
- dados antigos quebrando testes
- risco de mexer em produção

---

# Solução

Testcontainers cria **ambientes isolados e temporários** para testes.

- sobe o serviço automaticamente
- ambiente sempre limpo
- apaga tudo ao final

---

# Como funciona

- usa **Docker**
- cria containers (mini ambientes isolados)
- executa testes com serviços reais

---

# Uso básico

## Anotações principais

```
@Testcontainers@Container
```

## Exemplo

- cria um PostgreSQL real
- pega URL com:

```
postgres.getJdbcUrl()
```

- conecta aplicação normalmente

---

# Benefícios

## Testes mais confiáveis

Testa com sistema real, não mock.

## Ambiente limpo sempre

Sem dados antigos interferindo.

## Evita erros de produção

Simula cenário real.

## Fácil para equipe

Novo dev roda tudo sem configurar nada.

---

# Onde usar

- banco de dados (PostgreSQL, MySQL)
- filas (Redis, RabbitMQ)
- testes de integração
- testes de API
- sistemas completos (end-to-end)

---

# Diferença importante

## Mockito (mock)

- simula comportamento
- não usa serviço real

## Testcontainers

- usa serviço real
- testa integração de verdade

---

# Quando usar

Quando quiser garantir que:

- conexão com banco funciona
- queries funcionam
- integração entre sistemas funciona
---

# Integração em Microservices 

## O que é Integration Testing?

Testa se **vários serviços funcionam juntos corretamente**.

Não testa partes isoladas, mas a comunicação entre elas.

---

# Contexto em Microservices

Sistema dividido em vários serviços:

- User Service
- Order Service
- Payment Service
- Inventory Service

Todos precisam se comunicar corretamente.

---

# Problemas que detecta

- formato de dados errado entre serviços
- falha de comunicação
- inconsistência (ex: pagamento falha, mas pedido é confirmado)

---

# Tipos principais de testes

## 1. Contract Testing

Garante que os serviços seguem um **contrato de comunicação**.

- define formato de entrada/saída
- não depende do outro serviço rodando
- detecta mudanças de estrutura cedo

---

## 2. API Testing

Testa os endpoints da API.

Verifica:

- respostas corretas
- tratamento de erros
- comportamento com dados inválidos

Exemplos:

- criar pedido retorna ID
- erro retorna mensagem correta

---

## 3. End-to-End (E2E)

Testa o fluxo completo do sistema.

Exemplo:

1. usuário faz pedido
2. pagamento processado
3. estoque atualizado
4. notificação enviada

Mais completo, porém mais lento e complexo.

---

# Comparação

- **Contract** → rápido, valida formato
- **API** → valida endpoints
- **E2E** → valida fluxo completo

---

# Importância

- garante integração correta entre serviços
- evita erros em produção
- valida cenários reais
- essencial em microservices

---

