package br.com.bookstore.dao;

import br.com.bookstore.model.Livro;

import java.util.List;
import java.util.Optional;

// contrato de persistência desacoplado: a camada de negócio não conhece JDBC | RA: 25033061-2 
public interface LivroDAO {
    void salvar(Livro livro);
    Optional<Livro> buscarPorId(Long id);
    List<Livro> listarTodos();
}
