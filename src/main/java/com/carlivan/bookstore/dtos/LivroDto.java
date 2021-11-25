package com.carlivan.bookstore.dtos;

import com.carlivan.bookstore.domain.Livro;

public class LivroDto {
    private Long id;
    private String titulo;

    public LivroDto() {
    }

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
