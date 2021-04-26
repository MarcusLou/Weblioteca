package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.model.Livro;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
	@Query("SELECT a FROM Fatura a INNER JOIN Cliente c ON a.clienteId = c.clienteId WHERE c.nome LIKE %:pesquisa%")
	List<Fatura> pesquisar(@Param("pesquisa") String pesquisa);
}
