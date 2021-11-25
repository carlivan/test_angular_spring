package com.carlivan.bookstore.resoures;

import com.carlivan.bookstore.domain.Livro;
import com.carlivan.bookstore.dtos.LivroDto;
import com.carlivan.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroResource {
    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id){
        Livro livro = service.findById(id);
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> findAll(@RequestParam(value = "categoria",defaultValue = "0")Long id_cat){
        List<Livro> list = service.findAll(id_cat);
        List<LivroDto> listDto = list.stream().map(LivroDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id,@Valid @RequestBody Livro obj){
        Livro livro = service.update(id, obj);
        return ResponseEntity.ok().body(livro);
    }
    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0")Long id_cat,
                                        @Valid @RequestBody Livro obj){
        Livro livro = service.create(id_cat, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}")
                .buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
