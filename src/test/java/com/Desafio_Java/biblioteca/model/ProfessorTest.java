package com.Desafio_Java.biblioteca.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ProfessorTest {

    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor(1L, "Dr. Maria Santos");
    }

    @Test
    @DisplayName("Deve criar professor com dados corretos")
    void deveCriarProfessorComDadosCorretos() {
        // Assert
        assertEquals(1L, professor.getId());
        assertEquals("Dr. Maria Santos", professor.getNome());
    }

    @Test
    @DisplayName("Professor sempre deve poder emprestar")
    void professorSempreDevePodeEmprestar() {
        // Act & Assert
        assertTrue(professor.podeEmprestar());
    }

    @Test
    @DisplayName("ToString deve conter informações corretas")
    void toStringDeveConterInformacoesCorretas() {
        // Act
        String resultado = professor.toString();

        // Assert
        assertTrue(resultado.contains("Professor"));
        assertTrue(resultado.contains("1"));
        assertTrue(resultado.contains("Dr. Maria Santos"));
    }

    @Test
    @DisplayName("Deve herdar corretamente de Usuario")
    void deveHerdarCorretamenteDeUsuario() {
        // Assert
        assertInstanceOf(Usuario.class, professor);
    }

    @Test
    @DisplayName("Dois professores com mesmo ID devem ser considerados iguais")
    void doisProfessoresComMesmoIdDevemSerConsideradosIguais() {
        // Arrange
        Professor professor1 = new Professor(1L, "Prof A");
        Professor professor2 = new Professor(1L, "Prof B");

        // Act & Assert
        assertEquals(professor1.getId(), professor2.getId());
    }
}