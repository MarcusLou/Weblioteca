package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByTituloLivroContainingIgnoreCase(String tituloLivro);
}
