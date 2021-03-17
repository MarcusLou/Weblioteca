package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Fatura;

@Service
public interface FaturaService {
	List<Fatura> getAllFaturas();

	void salvarFatura(Fatura fatura);
	
	Fatura gerarFatura(Long emprestimoId);

	Fatura getFaturaById(Long id);

	void deletarFaturaById(Long id);

	Page<Fatura> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
