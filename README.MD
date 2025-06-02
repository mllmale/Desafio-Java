# Sistema de Biblioteca - Java

Sistema de biblioteca desenvolvido em **Java** que simula o funcionamento básico de **empréstimo** e **devolução** de livros por usuários (alunos e professores). O projeto implementa conceitos de **orientação a objetos**, **princípios SOLID** e **arquitetura limpa** com divisão clara de responsabilidades.

---

## Arquitetura

O projeto segue os princípios da **Arquitetura Limpa** e **SOLID**, dividido nas seguintes camadas:

### 🔹 Entidades

Representam as regras de negócio centrais:

- `Livro`
- `Usuario` (classe abstrata)
- `Aluno` (subclasse de Usuario)
- `Professor` (subclasse de Usuario)

### 🔹 Casos de Uso

Contêm a lógica da aplicação:

- `EmprestarLivroUseCase`
- `DevolverLivroUseCase`

### 🔹 Interfaces

Definem os contratos e abstrações para os repositórios:

- `BibliotecaRepository`

### 🔹 Implementações

Contêm implementações concretas dos repositórios:

- `BibliotecaRepositoryImpl` (exemplo)

---

## Princípios SOLID Aplicados

- **S - Single Responsibility Principle (SRP)**  
  Cada classe tem uma única responsabilidade.

- **O - Open/Closed Principle (OCP)**  
  As classes são abertas para extensão, mas fechadas para modificação.

- **L - Liskov Substitution Principle (LSP)**  
  Subclasses podem ser utilizadas no lugar das classes base sem quebrar a lógica.

- **I - Interface Segregation Principle (ISP)**  
  Interfaces são específicas e evitam obrigar a implementação de métodos desnecessários.

- **D - Dependency Inversion Principle (DIP)**  
  O sistema depende de abstrações, não de implementações concretas.

---

## Tecnologias Utilizadas

- Java (versão 11 ou superior recomendada)
- Programação Orientada a Objetos (POO)
- Arquitetura Limpa
- Princípios SOLID

---

## Funcionalidades

- Cadastro de livros
- Cadastro de usuários (aluno e professor)
- Empréstimo de livros com regras específicas por tipo de usuário
- Devolução de livros
- Controle de limite de empréstimos por usuário

### Execute a aplicação

Após compilar, execute o programa principal:

```bash
java app.BibliotecaApplication
```

Você verá uma saída parecida com esta:

```
=== SISTEMA DE BIBLIOTECA ===

Usuários criados:
Aluno{id=1, nome='João Silva', creditos=2}
Professor{id=2, nome='Dr. Maria Santos'}

Livros disponíveis inicialmente:
Livro{id=1, titulo='Java: Como Programar', valorCredito=1, disponivel=true}
Livro{id=2, titulo='Clean Code', valorCredito=2, disponivel=true}
Livro{id=3, titulo='Design Patterns', valorCredito=3, disponivel=true}
Livro{id=4, titulo='Spring Boot in Action', valorCredito=2, disponivel=true}

--- Aluno João empresta 'Java: Como Programar' ---
Empréstimo realizado com sucesso!
Créditos do aluno após empréstimo: 1

--- Professor Maria empresta 'Clean Code' ---
Empréstimo realizado com sucesso!

Livros disponíveis após empréstimos:
Livro{id=3, titulo='Design Patterns', valorCredito=3, disponivel=true}
Livro{id=4, titulo='Spring Boot in Action', valorCredito=2, disponivel=true}

--- Aluno João tenta emprestar 'Design Patterns' ---
Empréstimo realizado com sucesso!
Créditos do aluno após segundo empréstimo: 0

--- Aluno João tenta emprestar 'Spring Boot in Action' (sem créditos) ---
Erro: Usuário não pode emprestar livros

--- Aluno João devolve 'Java: Como Programar' ---
Devolução realizada com sucesso!
Créditos do aluno após devolução: 1

Livros disponíveis após devolução:
Livro{id=1, titulo='Java: Como Programar', valorCredito=1, disponivel=true}
Livro{id=4, titulo='Spring Boot in Action', valorCredito=2, disponivel=true}
```

