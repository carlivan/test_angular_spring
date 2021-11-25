package com.carlivan.bookstore.service;

import com.carlivan.bookstore.domain.Categoria;
import com.carlivan.bookstore.dtos.CategoriaDto;
import com.carlivan.bookstore.repositories.CategoriaRepository;
import com.carlivan.bookstore.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Long id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){
        return repository.findAll();
    }

    public Categoria create(Categoria categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Categoria update(Long id, CategoriaDto categoriaDto) {
        Categoria categoria = findById(id);
        categoria.setNome(categoriaDto.getNome());
        categoria.setDescricao(categoriaDto.getDescricao());
        return repository.save(categoria);
    }

    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new com.carlivan.bookstore.service.exception
                    .DataIntegrityViolationException("Categoria não pode ser deletata! Existe livros associados");
        }
    }
}
