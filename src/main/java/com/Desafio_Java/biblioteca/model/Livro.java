package com.Desafio_Java.biblioteca.model;

import lombok.Setter;

public class Livro {
    private Long id;
    private String titulo;
    private int valorCredito;
    @Setter
    private boolean disponivel;
    @Setter
    private Usuario emprestadoPara;

    public Livro(Long id, String titulo, int valorCredito) {
        this.id = id;
        this.titulo = titulo;
        this.valorCredito = valorCredito;
        this.disponivel = true;
        this.emprestadoPara = null;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getValorCredito() { return valorCredito; }
    public boolean isDisponivel() { return disponivel; }
    public Usuario getEmprestadoPara() { return emprestadoPara; }

    @Override
    public String toString() {
        return String.format("Livro{id=%d, titulo='%s', valorCredito=%d, disponivel=%s}",
                id, titulo, valorCredito, disponivel);
    }
}

