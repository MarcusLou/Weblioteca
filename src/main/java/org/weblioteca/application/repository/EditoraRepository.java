package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Editora;

@Repository                              
public interface EditoraRepository extends JpaRepository<Editora, Long> {
	List<Editora> findByNomeContainingIgnoreCase(String nome);
}
