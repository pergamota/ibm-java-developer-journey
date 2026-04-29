
# O que são padrões de design:

Padrões de design são soluções comprovadas para problemas comuns que os desenvolvedores enfrentam repetidamente. Use padrões de design para implementar e reutilizar modelos, não é algo que você apenas copia e cola, mas vai te ajudar a te guiar para estruturar seu código.

# Padrões Criacionais
  
**Padrões de projeto criacionais** são estratégias de design para criar objetos em seu código que permitem que você reutilize e altere o código facilmente mais tarde. Em vez de escrever um novo código toda vez que você precisa de um objeto, esses padrões oferecem maneiras inteligentes de construir objetos. Esses padrões de design ocultam os passos usados para criar um objeto, de modo que seu programa não dependa de um método fixo para construir o objeto, tornando seu código mais flexível e mais fácil de atualizar ou expandir.

# Padrões Estruturais

**Padrões de projeto estruturais** trabalham com composição de objetos e relacionamentos entre objetos. Você pode usar padrões estruturais para combinar classes e objetos para construir sistemas maiores e mais flexíveis. Use padrões de design estruturais para tornar seu código mais fácil de ler, reutilizar e gerenciar. 

# Padrões Comportamentais

**Padrões de projeto comportamentais** focam nas comunicações entre objetos e na atribuição de responsabilidades, gerenciando algoritmos, responsabilidades e colaborações dentro de um sistema de software. Padrões de design comportamentais ajudam a simplificar a comunicação complexa e o gerenciamento de algoritmos em aplicações Java, tornando seu código mais modular, reutilizável e mais fácil de manter.

# Interfaces prontas do Java

# Consumer<T>

Recebe valor e não retorna nada.

name -> System.out.println(name)

## Function<T,R>

Recebe valor e retorna outro.

text -> text.length()

## Predicate<T>

Retorna boolean.

n -> n % 2 == 0

## Supplier<T>

Não recebe nada e retorna valor.

() -> "Olá"

---

# Lambda Expression

Forma curta de implementar interfaces funcionais.

Antes:

class Printer implements Consumer<String> { ... }

Agora:

name -> System.out.println(name)

---

# Uso em Design Patterns

Facilitam patterns como **Strategy**, pois permitem passar comportamento direto.

Antes:

- Uma classe para cada pagamento

Agora:

valor -> pagarComPix(valor)

---

# Vantagens

- Menos código
- Menos classes
- Mais flexibilidade
- Fácil manutenção
- Reutilização de lógica
  
  
  # 📌 O que são Design Patterns?  
  
São **soluções prontas e reutilizáveis** para problemas comuns no desenvolvimento de software.  
  
Servem como **modelos de estrutura**, ajudando a criar código:  
  
- limpo  
- organizado  
- reutilizável  
- fácil de manter  
---

# 1. Singleton  
  
Garante que exista **apenas 1 instância** de uma classe.  
  
## Usado para:  
  
- conexão com banco de dados  
- configurações globais  
- cache  
- logs
---

# 2. Factory

Cria objetos dinamicamente sem expor a lógica de criação.

## Usado para:

- tipos de pagamento
- notificações (Email, SMS, Push)
- formatos de arquivo
- testes (mock / real)

## Vantagem:

Em vez de usar vários `new`, uma fábrica decide qual objeto criar.
---

# ✅ 3. Observer

Quando um objeto muda, vários outros são avisados automaticamente.

## Usado para:

- notificações
- atualizações de interface
- eventos
- sistemas em tempo real

## Exemplo:

Preço de ação muda → investidores recebem alerta.
---

# ✅ 4. Builder

Cria objetos complexos passo a passo.

## Usado para:

- objetos com muitos parâmetros opcionais
- configurações
- APIs
- montagem de objetos grandes

## Exemplo:

Computer.builder()  
   .cpu("i7")  
   .ram(16)  
   .wifi(true)  
   .build();
---

# 🎯 Resumo Final

|Pattern|Função|
|---|---|
|Singleton|Uma única instância|
|Factory|Criar objetos dinamicamente|
|Observer|Notificar mudanças|
|Builder|Montar objetos complexos|


# Factory Pattern em Java
## 📌 O que é?  
  
O **Factory Pattern** é um padrão de criação que permite criar objetos **sem expor diretamente a classe usada**.

Você faz:

```
VehicleFactory.createVehicle("car")
```

A fábrica decide qual objeto retornar.

# Objetivo Principal

Centralizar a criação de objetos e evitar código cheio de if e else.

# Exemplo

Interface comum:

```
Vehicle
```

Classes concretas:

- Car
- Motorcycle
- Truck

Fábrica:

```
VehicleFactory.createVehicle("truck")
```

# ✅ Vantagens

## 1. Código limpo

Cliente usa objeto sem saber como foi criado.

## 2. Baixo acoplamento

Código principal não depende de classes concretas.

## 3. Fácil expansão

Novo tipo?

Só criar classe nova e atualizar a fábrica.

---

# Onde usar

- Tipos de pagamento
- Relatórios diferentes
- Banco de dados por configuração
- JSON / XML parser
- Notificações (Email, SMS, Push)
  
---

# Strategy Pattern  
  
Permite definir **várias formas de executar uma mesma tarefa** e trocar entre elas em tempo de execução.  
  
## Exemplo:  
  
Sistema de pagamento:  
  
- Cartão  
- PayPal  
- Transferência  
  
Em vez de vários `if/else`, cada forma vira uma estratégia.

## Quando usar:

- Métodos de pagamento
- Ordenação
- Validação
- Autenticação
- Algoritmos diferentes

## Vantagens:

- Código limpo
- Fácil adicionar novas estratégias
- Troca comportamento facilmente

---

# 📌 Builder Pattern

Usado para criar objetos complexos **passo a passo**.

Ideal quando há muitos parâmetros, principalmente opcionais.

## Exemplo:

```
User.builder()   
.name("Pedro")
.email("x@email.com")
.age(20)
.build();
```

Em vez de construtores enormes.

## Quando usar:

- Usuários
- Configurações
- HTTP Requests
- Objetos grandes

## Vantagens:

- Código legível
- Flexível
- Evita vários construtores

---

# Usando os dois juntos

- Builder cria objeto complexo
- Strategy define como ele será usado
  
---

# Observer Pattern em Java
## 📌 O que é?  
  
O **Observer Pattern** é usado quando **um objeto muda** e vários outros precisam ser avisados automaticamente.  
  
Relação **1 para muitos**.  
  
---  
  
# Estrutura  
  
## Subject (Observado)  
  
Objeto principal que sofre mudanças.  
  
## Observers  
  
Objetos interessados nas mudanças.  
  
## Notification  
  
Aviso enviado quando algo muda.  
  
---  
  
# Exemplo  
  
Preço de ação muda:  
  
- App recebe alerta  
- Email recebe aviso  
- Sistema atualiza dashboard  
  
Tudo automaticamente.  
  
---  
  
# Sem Observer  
  
Código fica verificando mudanças em loop.

## Quando o uso de padrões de design é bom

Vamos explorar alguns cenários em que o uso de padrões de design é bom.

**Você está enfrentando um problema familiar:** Os padrões de design se destacam quando você encontra problemas que muitos desenvolvedores já resolveram antes. Se você está construindo um sistema onde os objetos precisam se comunicar sem saber diretamente sobre uns aos outros, o padrão Observer pode ser perfeito. Se você precisa criar objetos complexos com muitos parâmetros opcionais, o padrão Builder pode te salvar do inferno dos construtores. A pergunta chave a se fazer é: “Outros desenvolvedores provavelmente enfrentaram esse exato desafio?” Se a resposta for sim, provavelmente há um padrão para isso.


**Seu código está ficando bagunçado:** Quando você se vê escrevendo longas cadeias de instruções if-else para lidar com comportamentos diferentes, o padrão Strategy pode ajudar. Se você está copiando e colando código semelhante em várias classes, o padrão Template Method pode eliminar essa duplicação. Padrões frequentemente surgem naturalmente quando você está refatorando código bagunçado.


**Você precisa de flexibilidade para mudanças futuras:** Padrões se destacam em tornar o código adaptável. Se você sabe que sua aplicação precisará suportar vários tipos de banco de dados no futuro, implementar o padrão Abstract Factory desde o início pode economizar horas incontáveis mais tarde. Quando os requisitos provavelmente evoluirão, os padrões fornecem uma estrutura que acomoda mudanças de forma elegante.


**Você está trabalhando com uma equipe:** Padrões criam um vocabulário compartilhado. Quando você diz a um colega de equipe, “Eu usei o padrão Singleton aqui,” ele entende imediatamente a decisão de design sem precisar se aprofundar nos detalhes da implementação. Esse benefício de comunicação se torna inestimável em projetos maiores, onde vários desenvolvedores precisam entender e manter o mesmo código.


## Quando evitar padrões de design

Agora, vamos explorar cenários em que você deve evitar padrões de design.

**Seu problema é simples:** Não use um martelo para quebrar uma noz. Se você precisa formatar uma string de data, não precisa do padrão Strategy—um simples método utilitário serve. Programadores iniciantes muitas vezes superdimensionam soluções aplicando padrões a problemas que não os justificam. Comece simples e adicione complexidade apenas quando necessário.


**Você está aprendendo o básico:** Se você ainda está tendo dificuldades com conceitos fundamentais como loops, arrays ou programação orientada a objetos básica, concentre-se em dominar esses primeiros. Padrões são ferramentas avançadas que se baseiam em um conhecimento sólido. Tentar usar padrões antes de entender o básico é como tentar realizar uma cirurgia antes de aprender anatomia.


**Você não entende o padrão:** Nunca implemente um padrão apenas porque soa impressionante ou porque você leu sobre ele online. Cada padrão resolve problemas específicos e introduz certos trade-offs. O padrão Singleton, por exemplo, pode dificultar os testes e criar dependências ocultas. Use apenas padrões que você realmente entende, incluindo suas desvantagens.


**Sua aplicação é pequena:** Para pequenos scripts, trabalhos escolares ou projetos de prova de conceito, padrões muitas vezes adicionam complexidade desnecessária. Um programa de 50 linhas raramente se beneficia de padrões arquitetônicos elaborados. O custo de implementar padrões deve ser justificado pelos benefícios que eles proporcionam.


## As regras de ouro

**Comece sem padrões:** Inicie cada projeto com a solução mais simples que funcione. Os padrões devem surgir de necessidades reais, e não serem impostos desde o início. Essa abordagem ajuda você a reconhecer quando os padrões seriam realmente benéficos em vez de serem apenas exercícios acadêmicos.


**Aprenda um padrão de cada vez:** Não tente dominar todos os 23 padrões da Gang of Four simultaneamente. Escolha um que aborde um problema que você está enfrentando atualmente, implemente-o em um pequeno projeto e compreenda-o completamente antes de passar para o próximo.


**Concentre-se no problema, não no padrão:** Pergunte a si mesmo: “Qual problema estou tentando resolver?” em vez de “Qual padrão devo usar?” O padrão deve ser uma solução natural para o seu problema, e não o ponto de partida do seu design.


**Leia o código de outras pessoas:** Estude projetos de código aberto para ver como desenvolvedores experientes aplicam padrões em cenários do mundo real. Você aprenderá não apenas como implementar padrões, mas quando eles escolheram usá-los e, o mais importante, quando não o fizeram.


# 🔹 Exemplo Real Completo

## Sistema E-commerce

### Factory

Cria pagamento correto.

### Strategy

Escolhe forma de pagamento.

### Builder

Monta pedido complexo.

### Observer

Envia notificação após compra.

### Singleton

Classe de configuração global.

  

