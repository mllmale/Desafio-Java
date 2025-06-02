package com.Desafio_Java.biblioteca.model;

public abstract class Usuario {
    protected Long id;
    protected String nome;

    public Usuario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }

    public abstract boolean podeEmprestar();

    @Override
    public String toString() {
        return String.format("%s{id=%d, nome='%s'}",
                this.getClass().getSimpleName(), id, nome);
    }
}