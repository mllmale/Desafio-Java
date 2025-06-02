package com.Desafio_Java.biblioteca.service;

import com.Desafio_Java.biblioteca.model.Livro;
import com.Desafio_Java.biblioteca.model.Professor;
import com.Desafio_Java.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

public class BibliotecaServiceTest {

    private BibliotecaService repository;
    private Livro livro1;
    private Livro livro2;
    private Livro livro3;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        repository = new BibliotecaService();
        livro1 = new Livro(1L, "Java: Como Programar", 1);
        livro2 = new Livro(2L, "Clean Code", 2);
        livro3 = new Livro(3L, "Design Patterns", 3);
        usuario = new Professor(1L, "Dr. Test");

        // Adicionar livros ao repositório
        repository.salvar(livro1);
        repository.salvar(livro2);
        repository.salvar(livro3);
    }

    @Test
    @DisplayName("Deve salvar livro corretamente")
    void deveSalvarLivroCorretamente() {
        // Arrange
        Livro novoLivro = new Livro(4L, "Spring Boot", 2);

        // Act
        repository.salvar(novoLivro);

        // Assert
        Optional<Livro> livroSalvo = repository.buscarPorId(4L);
        assertTrue(livroSalvo.isPresent());
        assertEquals(novoLivro, livroSalvo.get());
    }

    @Test
    @DisplayName("Deve atualizar livro existente")
    void deveAtualizarLivroExistente() {
        // Arrange
        livro1.setDisponivel(false);
        livro1.setEmprestadoPara(usuario);

        // Act
        repository.salvar(livro1);

        // Assert
        Optional<Livro> livroAtualizado = repository.buscarPorId(1L);
        assertTrue(livroAtualizado.isPresent());
        assertFalse(livroAtualizado.get().isDisponivel());
        assertEquals(usuario, livroAtualizado.get().getEmprestadoPara());
    }

    @Test
    @DisplayName("Deve buscar livro por ID existente")
    void deveBuscarLivroPorIdExistente() {
        // Act
        Optional<Livro> resultado = repository.buscarPorId(1L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(livro1, resultado.get());
    }

    @Test
    @DisplayName("Deve retornar vazio para ID inexistente")
    void deveRetornarVazioParaIdInexistente() {
        // Act
        Optional<Livro> resultado = repository.buscarPorId(999L);

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve listar todos os livros")
    void deveListarTodosOsLivros() {
        // Act
        List<Livro> todos = repository.listarTodos();

        // Assert
        assertEquals(3, todos.size());
        assertTrue(todos.contains(livro1));
        assertTrue(todos.contains(livro2));
        assertTrue(todos.contains(livro3));
    }

    @Test
    @DisplayName("Deve listar apenas livros disponíveis")
    void deveListarApenasLivrosDisponiveis() {
        // Arrange - Emprestar um livro
        livro2.setDisponivel(false);
        livro2.setEmprestadoPara(usuario);
        repository.salvar(livro2);

        // Act
        List<Livro> disponiveis = repository.listarDisponiveis();

        // Assert
        assertEquals(2, disponiveis.size());
        assertTrue(disponiveis.contains(livro1));
        assertFalse(disponiveis.contains(livro2));
        assertTrue(disponiveis.contains(livro3));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há livros disponíveis")
    void deveRetornarListaVaziaQuandoNaoHaLivrosDisponiveis() {
        // Arrange - Emprestar todos os livros
        livro1.setDisponivel(false);
        livro2.setDisponivel(false);
        livro3.setDisponivel(false);
        repository.salvar(livro1);
        repository.salvar(livro2);
        repository.salvar(livro3);

        // Act
        List<Livro> disponiveis = repository.listarDisponiveis();

        // Assert
        assertTrue(disponiveis.isEmpty());
    }

    @Test
    @DisplayName("Deve funcionar com repositório vazio")
    void deveFuncionarComRepositorioVazio() {
        // Arrange
        BibliotecaService repositorioVazio = new BibliotecaService();

        // Act & Assert
        assertTrue(repositorioVazio.listarTodos().isEmpty());
        assertTrue(repositorioVazio.listarDisponiveis().isEmpty());
        assertTrue(repositorioVazio.buscarPorId(1L).isEmpty());
    }

    @Test
    @DisplayName("Deve adicionar livro através do método adicionarLivro")
    void deveAdicionarLivroAtravesDoMetodoAdicionarLivro() {
        // Arrange
        Livro novoLivro = new Livro(5L, "Effective Java", 3);

        // Act
        repository.adicionarLivro(novoLivro);

        // Assert
        Optional<Livro> livroAdicionado = repository.buscarPorId(5L);
        assertTrue(livroAdicionado.isPresent());
        assertEquals(novoLivro, livroAdicionado.get());
    }

    @Test
    @DisplayName("Deve manter integridade dos dados após múltiplas operações")
    void deveManterIntegridadeDosDadosAposMultiplasOperacoes() {
        // Arrange & Act
        livro1.setDisponivel(false);
        repository.salvar(livro1);

        Livro novoLivro = new Livro(6L, "Novo Livro", 1);
        repository.adicionarLivro(novoLivro);

        // Assert
        assertEquals(4, repository.listarTodos().size());
        assertEquals(3, repository.listarDisponiveis().size());
        assertFalse(repository.buscarPorId(1L).get().isDisponivel());
        assertTrue(repository.buscarPorId(6L).get().isDisponivel());
    }
}
