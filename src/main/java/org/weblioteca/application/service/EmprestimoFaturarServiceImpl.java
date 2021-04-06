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
import org.weblioteca.application.repository.EmprestimoRepository;

@Service
public class EmprestimoFaturarServiceImpl implements EmprestimoFaturarService {
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Override
	public List<Emprestimo> getAllLivros() {
		return emprestimoRepository.findAll();
	}

	@Override
	public Emprestimo getEmprestimoById(Long id) {
		Optional<Emprestimo> optional = emprestimoRepository.findById(id);
		Emprestimo emprestimo = null;
		if (optional.isPresent()) {
			emprestimo = optional.get();
		} else {
			throw new RuntimeException("Emprestimo nao encontrado com id = " + id);
		}
		return emprestimo;
	}

	@Override
	public Page<Emprestimo> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.emprestimoRepository.findAll(pageable);
	}

}
