
# JLink em Java — Resumo

## O que é?

**JLink** é uma ferramenta do Java que cria um **runtime personalizado** para sua aplicação.

Ele empacota:

- sua aplicação
- - apenas os módulos do Java necessários

Resultado: app **pronto para rodar**, sem precisar instalar Java.

---

# Problema que resolve

Sem JLink:

- usuário precisa instalar Java
- conflitos de versão
- suporte técnico aumenta

Com JLink:

- app roda direto
- sem dependência externa

---

# Vantagens

- tamanho menor (≈ 30–80 MB)
- não precisa instalar Java
- inicialização mais rápida
- mais seguro (menos módulos = menos risco)
- distribuição mais profissional

---

# Processo básico

## 1. Compilar aplicação

```
javac
```

---

## 2. Identificar módulos

```
jdeps
```

Descobre quais partes do Java seu app usa.

---

## 3. Criar runtime

```
jlink
```

Inclui apenas os módulos necessários.

---

## 4. Adicionar aplicação

Copiar classes para o runtime.

---

## 5. Criar launcher

Script para rodar o app facilmente.

---

# Alternativas

## GraalVM Native Image

- menor tamanho
- mais rápido
- build mais complexo

---

## JAR tradicional

- simples
- precisa de Java instalado

---

## Docker

- ambiente isolado
- mais pesado
  
---

### Maven – Estratégias Essenciais

**O que é:**

- Ferramenta de build e gerenciamento de projetos Java
- Ajuda a automatizar **build, dependências e deploy**

---

### Multi-module (Arquitetura modular)

- Divide o projeto em **módulos independentes**
- Vantagens:
    - Build mais rápido (só o necessário)
    - Menos conflitos entre equipes
    - Deploy independente

---

### Configuração por ambiente (Profiles)

- Permite usar configs diferentes para:
    - **dev, test, prod**
- Evita erros (ex: usar banco de produção sem querer)

---

### Fat JAR (deploy simples)

- JAR com **tudo dentro** (código + dependências)
- Vantagens:
    - Fácil de rodar (`java -jar`)
    - Evita erro de dependência
    - Portável

---

### Versionamento automático

- Atualiza versões automaticamente
- Importante para:
    - CI/CD
    - controle de releases
    - rollback

---

### Container (Docker)

- Maven pode integrar com Docker
- Gera aplicações prontas para rodar em containers

---

### Ideia geral

- Maven resolve problemas reais de:
    - organização
    - automação
    - deploy
    - escalabilidade
    


### Maven não é só “rodar build”

- Ele resolve problemas reais de projeto:
    - organização
    - automação
    - padronização

---

### Multi-module (muito importante entender a ideia)

- Um projeto grande → dividido em partes menores
- Cada parte (módulo) é independente

Isso resolve:

- builds lentos
- conflitos de equipe
- deploy arriscado

---

### Profiles (configuração por ambiente)

- Mesmo código → configurações diferentes (dev, test, prod)

Evita erros graves tipo:

- usar banco de produção em teste

---

### Fat JAR

- Um único arquivo com tudo dentro

Resolve:

- erro de dependência
- “funciona na minha máquina”

---

### Versionamento automático

- Cada build tem uma versão única

Importante para:

- CI/CD
- rollback
- rastrear deploy

---

### Container (Docker + Maven)

- Maven pode preparar app para rodar em container

 Importante hoje porque:

- backend moderno usa Docker/Kubernetes

---

### Segurança em Java (Deploy + Dependências)

Esse tema se divide em **2 pilares principais**:

---

## 1. Segurança no Deploy (produção)

Refere-se a **como sua aplicação roda no mundo real**.

### HTTPS (obrigatório)

- Sempre usar HTTPS em produção
- Protege dados contra:
    - interceptação
    - alteração (ataques man-in-the-middle)

---

### Security Headers

- Instruções para o navegador lidar com segurança
- Protegem contra:
    - **XSS (Cross-Site Scripting)**
    - **Clickjacking**
    - MIME sniffing

---

### Configuração por ambiente

- Dev → mais flexível
- Prod → mais restrito

Regra importante:

- ❌ NÃO mostrar erros detalhados em produção
- Isso evita exposição de informações internas

---

### Gerenciamento de segredos

- ❌ Nunca colocar:
    - senhas
    - tokens
    - chaves API no código
- ✔ Usar:
    - variáveis de ambiente
    - secret managers

---

## 2. Segurança de Dependências

Refere-se às **bibliotecas externas do projeto**

Problema:

- Cada dependência pode trazer vulnerabilidades
- Inclusive dependências indiretas (transitivas)

---

### Vulnerability Scanning

- Ferramentas analisam libs e detectam falhas conhecidas
- Exemplo: OWASP Dependency Check

Boa prática:

- Bloquear build se vulnerabilidade for crítica

---

### Atualização de dependências

- Bibliotecas antigas = risco alto
- Atualizações corrigem falhas de segurança

Estratégia:

- Atualizar frequentemente (não deixar acumular)

---

### Exclusão de dependências vulneráveis

- Às vezes uma lib traz outra insegura

Solução:

- Excluir manualmente a dependência problemática
- Adicionar versão segura

---

## 3. Boas práticas de código seguro

### Validação de entrada

- Nunca confiar em dados do usuário
- Usar validação (ex: `@Valid` no Spring)

---

### Logging de segurança

- Registrar eventos importantes:
    - falhas de login
    - comportamentos suspeitos

MAS:

- ❌ nunca logar dados sensíveis
  
---  
## Java em Containers – Boas Práticas

O foco do vídeo é: **como rodar aplicações Java corretamente dentro de containers (Docker)** sem problemas de memória, performance ou segurança.

---

## 1. Problema principal: memória no container

* Containers têm **limite de memória rígido**  
* JVM (Java) nem sempre respeita isso automaticamente

### Problema:

- JVM acha que tem muita memória (ex: 32GB da máquina)
- Container tem pouco (ex: 512MB)
- Resultado:  
	aplicação crasha imediatamente

---

### Solução:

- Configurar JVM para entender o container

Principais configs:

- `UseContainerSupport` → JVM usa limite do container
- `MaxRAMPercentage` → define quanto da memória será usada (ex: 75%)

Importante:

- Nem toda memória é heap (tem stack, threads, etc.)

---

## Escolha da base image (muito importante)

A imagem base é o “sistema operacional” do container

### Imagens grandes (ex: Ubuntu)

- Muito pesadas (~400MB+)
- Mais vulnerabilidades
- Mais lentas

### ✔ Imagens leves (ex: Alpine + OpenJDK)

- Menores (~150MB)
- Mais seguras
- Melhor performance

Atenção:

- Alpine usa **musl**, não **glibc**
- Algumas libs Java podem quebrar → precisa testar

---

## Docker Layers (otimização de build)

Docker usa **cache por camadas**

### Estratégia correta:

1. Base (OS + Java) → raramente muda
2. Dependências (Maven) → muda às vezes
3. Código → muda sempre

Resultado:

- Build mais rápido
- Evita baixar dependências toda hora

---

## 4. Multi-stage build (ESSENCIAL)

Problema:

- Build tools (Maven/Gradle) deixam a imagem gigante

### Sem multi-stage:

- ~350MB+
- Contém:
    - código fonte
    - Maven
    - cache

### Com multi-stage:

- ~150MB
- Contém:
    - apenas runtime + JAR

Como funciona:

- Stage 1 → build (com Maven)
- Stage 2 → produção (só o necessário)

---

## 5. Boas práticas adicionais

### ✔ Rodar com usuário não-root

- Mais segurança

---

### ✔ Health checks

- Verificar se a aplicação está funcionando

---

### ✔ Variáveis de ambiente

- Permite mudar config sem rebuild

---

## CI/CD para Java – Estratégias

### O que é CI/CD

- **CI (Continuous Integration)** → integrar código frequentemente + testar automaticamente
- **CD (Continuous Delivery/Deployment)** → entregar/deploy automático

Objetivo:

- código mais confiável
- menos bugs em produção
- releases mais rápidos

---

## Ideia central

Não existe uma única estratégia ideal  
depende de:

- tamanho da equipe
- frequência de deploy
- risco do sistema
- complexidade

---

## Branch-based pipeline

### Como funciona

- Cada dev trabalha em uma **feature branch**
- Cada branch roda pipeline:
    - build
    - testes
    - análise de código
- Só a **main branch** faz deploy

---

### Tipos de branches

- **Feature branch** → desenvolvimento + testes iniciais
- **Main branch** → integração + deploy
- **Release branch** → testes finais (ex: performance)

---

### ✔ Vantagens

- Mais seguro
- desenvolvimento em paralelo
- evita bugs em produção

---

### ❌ Desvantagens

- feedback mais lento
- conflitos de merge
- integração tardia

---

## Trunk-based development

### Como funciona

- Todos fazem commit direto na **main**
- várias vezes ao dia
- pipeline roda constantemente

Features incompletas ficam escondidas com **feature flags**

---

### Vantagens

- deploy rápido
- feedback rápido
- integração contínua

---

### Desvantagens

- mais complexo
- exige disciplina
- risco maior se mal feito

---

## 3. Pipeline para Microservices

### ✔ Ideia principal

- Cada microserviço tem seu **próprio pipeline**

---

### Etapas do pipeline

1. **Build** → compilar + dependências
2. **Test** → testes + análise
3. **Package** → gerar artefato (ex: Docker)
4. **Deploy** → staging ou produção

---

### Vantagens

- deploy independente
- escalabilidade
- isolamento de falhas

---

### Desvantagens

- maior complexidade
- mais infraestrutura
- difícil monitorar tudo

---

## Conceitos importantes

### ✔ Feature Flags

- esconder funcionalidades incompletas
- permite deploy sem impactar usuários

---

### ✔ Contract Testing

- garante que serviços se comuniquem corretamente

---

### ✔ API Versioning

- evita quebrar integração entre serviços

---

## Ideia geral do pipeline

Um pipeline geralmente faz:

- build
- testes
- validação
- deploy automático
  
---
