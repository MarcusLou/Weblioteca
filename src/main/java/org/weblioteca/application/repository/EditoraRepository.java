package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Editora;

@Repository                              
public interface EditoraRepository extends JpaRepository<Editora, Long> {
	List<Editora> findByNomeContainingIgnoreCase(String nome);

	@Query("SELECT e FROM Editora e WHERE e.ativo = :ativo AND CONCAT(e.nome, e.cnpj) LIKE %:pesquisa%")
	List<Editora> pesquisar(@Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa);

	@Query("SELECT e FROM Editora e WHERE e.ativo = :ativo")
	Page<Editora> findAllAtivos(@Param("ativo") Integer ativo, Pageable pageable);
}
