package com.Desafio_Java.biblioteca;

import com.Desafio_Java.biblioteca.model.Aluno;
import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.model.Professor;
import com.Desafio_Java.biblioteca.repository.BibliotecaRepository;
import com.Desafio_Java.biblioteca.service.BibliotecaService;
import com.Desafio_Java.biblioteca.userCase.DevolverLivroUseCase;
import com.Desafio_Java.biblioteca.userCase.EmprestarLivroUseCase;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class BibliotecaApplication {
	private BibliotecaRepository bibliotecaRepository;
	private EmprestarLivroUseCase emprestarLivroUseCase;
	private DevolverLivroUseCase devolverLivroUseCase;

	public BibliotecaApplication() {
		this.bibliotecaRepository = new BibliotecaService();
		this.emprestarLivroUseCase = new EmprestarLivroUseCase(bibliotecaRepository);
		this.devolverLivroUseCase = new DevolverLivroUseCase(bibliotecaRepository);

		inicializarBiblioteca();
	}

	private void inicializarBiblioteca() {
		// Adicionar livros à biblioteca
		((BibliotecaService) bibliotecaRepository).adicionarLivro(
				new Livro(1L, "Java: Como Programar", 1));
		((BibliotecaService) bibliotecaRepository).adicionarLivro(
				new Livro(2L, "Clean Code", 2));
		((BibliotecaService) bibliotecaRepository).adicionarLivro(
				new Livro(3L, "Design Patterns", 3));
		((BibliotecaService) bibliotecaRepository).adicionarLivro(
				new Livro(4L, "Spring Boot in Action", 2));
	}

	public void demonstrarFuncionamento() {
		System.out.println("=== SISTEMA DE BIBLIOTECA ===\n");

		// Criar usuários
		Aluno aluno1 = new Aluno(1L, "João Silva", 2);
		Professor professor1 = new Professor(2L, "Dr. Maria Santos");

		System.out.println("Usuários criados:");
		System.out.println(aluno1);
		System.out.println(professor1);
		System.out.println();

		// Listar livros disponíveis
		System.out.println("Livros disponíveis inicialmente:");
		listarLivrosDisponiveis();

		try {
			// Aluno empresta um livro
			System.out.println("\n--- Aluno João empresta 'Java: Como Programar' ---");
			emprestarLivroUseCase.executar(1L, aluno1);
			System.out.println("Empréstimo realizado com sucesso!");
			System.out.println("Créditos do aluno após empréstimo: " + aluno1.getCreditos());

			// Professor empresta um livro
			System.out.println("\n--- Professor Maria empresta 'Clean Code' ---");
			emprestarLivroUseCase.executar(2L, professor1);
			System.out.println("Empréstimo realizado com sucesso!");

			// Listar livros disponíveis após empréstimos
			System.out.println("\nLivros disponíveis após empréstimos:");
			listarLivrosDisponiveis();

			// Aluno tenta emprestar outro livro
			System.out.println("\n--- Aluno João tenta emprestar 'Design Patterns' ---");
			emprestarLivroUseCase.executar(3L, aluno1);
			System.out.println("Empréstimo realizado com sucesso!");
			System.out.println("Créditos do aluno após segundo empréstimo: " + aluno1.getCreditos());

			// Aluno tenta emprestar mais um livro (sem créditos)
			System.out.println("\n--- Aluno João tenta emprestar 'Spring Boot in Action' (sem créditos) ---");
			try {
				emprestarLivroUseCase.executar(4L, aluno1);
			} catch (IllegalStateException e) {
				System.out.println("Erro: " + e.getMessage());
			}

			// Devolver um livro
			System.out.println("\n--- Aluno João devolve 'Java: Como Programar' ---");
			devolverLivroUseCase.executar(1L, aluno1);
			System.out.println("Devolução realizada com sucesso!");
			System.out.println("Créditos do aluno após devolução: " + aluno1.getCreditos());

			// Listar livros disponíveis após devolução
			System.out.println("\nLivros disponíveis após devolução:");
			listarLivrosDisponiveis();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private void listarLivrosDisponiveis() {
		List<Livro> livrosDisponiveis = bibliotecaRepository.listarDisponiveis();
		if (livrosDisponiveis.isEmpty()) {
			System.out.println("Nenhum livro disponível");
		} else {
			livrosDisponiveis.forEach(System.out::println);
		}
	}

	public static void main(String[] args) {
		BibliotecaApplication app = new BibliotecaApplication();
		app.demonstrarFuncionamento();
	}
}