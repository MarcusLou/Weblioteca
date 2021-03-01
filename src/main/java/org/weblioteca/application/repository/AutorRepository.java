package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Autor;

@Repository                              
public interface AutorRepository extends JpaRepository<Autor, Long> {
	List<Autor> findByNomeContainingIgnoreCase(String nome);
}
