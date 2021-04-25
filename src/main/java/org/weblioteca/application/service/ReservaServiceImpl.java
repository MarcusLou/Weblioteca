package org.weblioteca.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Reserva;
import org.weblioteca.application.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public List<Reserva> pesquisar(Integer ativo, String pesquisa){
		return reservaRepository.pesquisar(ativo, pesquisa);	
	}
	
	@Override
	public List<Reserva> getAllReservas() {
		return reservaRepository.findAll();
	}

	@Override
	public void salvarReserva(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	@Override
	public Reserva getReservaById(Long id) {
		Optional<Reserva> optional = reservaRepository.findById(id);
		Reserva reserva = null;
		if (optional.isPresent()) {
			reserva = optional.get();
		} else {
			throw new RuntimeException("Reserva nao encontrado com id = " + id);
		}
		return reserva;
	}

	@Override
	public void deletarReservaById(Long id) {
		reservaRepository.deleteById(id);
	}
	
	@Override
	public void cancelarReservaById(Long id) {
		reservaRepository.deleteById(id);
	}
	
	@Override
	public Page<Reserva> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer ativo) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.reservaRepository.findAllAtivos(ativo, pageable);
	}
	

	@Override
	public Page<Reserva> findPaginatedC(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.reservaRepository.findAll(pageable);
	}
}
