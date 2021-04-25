package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Reserva;

@Service
public interface ReservaService {
	
	List<Reserva> getAllReservas();

	List<Reserva> pesquisar(Integer ativo, String pesquisa);
	
	void salvarReserva(Reserva reserva);

	Reserva getReservaById(Long id);

	void cancelarReservaById(Long id);
	
	void deletarReservaById(Long id);

	Page<Reserva> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer ativo);

	Page<Reserva> findPaginatedC(int pageNo, int pageSize, String sortField, String sortDirection);
}
