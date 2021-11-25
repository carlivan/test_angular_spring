package com.carlivan.bookstore.service;

import com.carlivan.bookstore.domain.Categoria;
import com.carlivan.bookstore.domain.Livro;
import com.carlivan.bookstore.repositories.CategoriaRepository;
import com.carlivan.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private LivroRepository livroRepository;

    public void instanciaBaseDeDados() {
        Categoria cat1 = new Categoria("Informática", "Livro de TI");
        Categoria cat2 = new Categoria("Ficção", "Ficção Científica");
        Categoria cat3 = new Categoria("Biografias", "Livros de Biografias");

        Livro liv1 = new Livro("Clean Code", "Robert Martin", "Lorem ipsum", cat1);
        Livro liv2 = new Livro("Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", cat1);
        Livro liv3 = new Livro("The Time Machine", "H.G. Wells", "Lorem ipsum", cat2);
        Livro liv4 = new Livro("The War of the Worlds", "H.G. Wells", "Lorem ipsum", cat2);
        Livro liv5 = new Livro("I, Robot", "Isaac Asimov", "Lorem ipsum", cat2);

        cat1.getLivros().addAll(Arrays.asList(liv1, liv2));
        cat1.getLivros().addAll(Arrays.asList(liv3, liv4, liv5));

        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        this.livroRepository.saveAll(Arrays.asList(liv1, liv2, liv3, liv4, liv5));
    }
}
