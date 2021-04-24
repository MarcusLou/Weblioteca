package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Autor;

@Repository                              
public interface AutorRepository extends JpaRepository<Autor, Long> {
	List<Autor> findByNomeContainingIgnoreCase(String nome);

	
	@Query("SELECT a FROM Autor a WHERE a.ativo = :ativo AND CONCAT(a.nome, a.origem, a.nascimento) LIKE %:pesquisa%")
	List<Autor> pesquisar(@Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa);

	@Query("SELECT a FROM Autor a WHERE a.ativo = :ativo")
	Page<Autor> findAllAtivos(@Param("ativo") Integer ativo, Pageable pageable);
	
	
}
