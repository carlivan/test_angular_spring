package com.carlivan.bookstore.dtos;

import com.carlivan.bookstore.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {
    private Long id;
    @NotBlank(message = "Campo obrigatório!!!")
    @Length(min = 3, max = 100)
    private String nome;
    @NotBlank(message = "Campo obrigatório!!!")
    @Length(min = 3, max = 200,message = "Descrição deve ter entre 3 e 200 caracteres!!")
    private String descricao;

    public CategoriaDto() {
    }
    public CategoriaDto(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
