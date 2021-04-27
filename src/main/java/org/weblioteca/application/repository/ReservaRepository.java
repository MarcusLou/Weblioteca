package org.weblioteca.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	
	@Query("SELECT r FROM Reserva r, Cliente c, Livro l WHERE r.ativo = :ativo AND r.clienteId = c.clienteId AND r.livroId = l.livroId AND CONCAT(r.dataReserva, c.nome, l.tituloLivro) LIKE %:pesquisa%")
	List<Reserva> pesquisar(@Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa);

	@Query("SELECT r FROM Reserva r WHERE r.ativo = :ativo")
	Page<Reserva> findAllAtivos(@Param("ativo") Integer ativo, Pageable pageable);

	
	
}
