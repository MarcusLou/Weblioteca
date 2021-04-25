package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByTituloLivroContainingIgnoreCase(String tituloLivro);
	
	@Query("SELECT a FROM Livro a WHERE a.ativo = :ativo AND a.tituloLivro LIKE %:pesquisa%")
	List<Livro> pesquisar(@Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa);

	@Query("SELECT a FROM Livro a WHERE a.ativo = :ativo")
	Page<Livro> findAllAtivos(@Param("ativo") Integer ativo, Pageable pageable);
}
