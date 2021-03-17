package org.weblioteca.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.repository.FaturaRepository;

@Service
public class FaturaServiceImpl implements FaturaService {
	@Autowired
	private FaturaRepository faturaRepository;
	private EmprestimoServiceImpl emprestimoService;

	@Override
	public List<Fatura> getAllFaturas() {
		return faturaRepository.findAll();
	}

	@Override
	public void salvarFatura(Fatura fatura) {
		faturaRepository.save(fatura);
	}

	@Override
	public Fatura getFaturaById(Long id) {
		Optional<Fatura> optional = faturaRepository.findById(id);
		Fatura fatura = null;
		if (optional.isPresent()) {
			fatura = optional.get();
		} else {
			throw new RuntimeException("Fatura nao encontrado com id = " + id);
		}
		return fatura;
	}

	@Override
	public void deletarFaturaById(Long id) {
		faturaRepository.deleteById(id);

	}

	@Override
	public Page<Fatura> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.faturaRepository.findAll(pageable);
	}

	@Override
	public Fatura gerarFatura(Long emprestimoId) {
	//	Fatura fatura = new Fatura();
	//	Emprestimo emprestimo;
	//	emprestimo = 
		return null;
	}

}
