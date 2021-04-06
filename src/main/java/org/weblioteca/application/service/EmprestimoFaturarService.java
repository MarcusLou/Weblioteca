package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Emprestimo;

@Service
public interface EmprestimoFaturarService {
	List<Emprestimo> getAllLivros();

	Emprestimo getEmprestimoById(Long id);

	Page<Emprestimo> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
