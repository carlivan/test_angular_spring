package com.carlivan.bookstore.service;

import com.carlivan.bookstore.domain.Categoria;
import com.carlivan.bookstore.domain.Livro;
import com.carlivan.bookstore.repositories.LivroRepository;
import com.carlivan.bookstore.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Long id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+
                id+", Tipo: "+Livro.class.getName()));
    }

    public List<Livro> findAll(Long id_cat) {
        categoriaService.findById(id_cat);
        return repository.findAllByCategoria(id_cat);
    }

    public Livro update(Long id, Livro obj) {
        Livro livro = findById(id);
        updateData(livro, obj);
        return repository.save(livro);
    }

    private void updateData(Livro livro, Livro obj) {
        livro.setTitulo(obj.getTitulo());
        livro.setNome_autor(obj.getNome_autor());
        livro.setTexto(obj.getTexto());
    }

    public Livro create(Long id_cat, Livro obj) {
        obj.setId(null);
        Categoria categoria = categoriaService.findById(id_cat);
        obj.setCategoria(categoria);
        return repository.save(obj);
    }

    public void delete(Long id) {
        Livro livro = findById(id);
        repository.delete(livro);
    }
}
