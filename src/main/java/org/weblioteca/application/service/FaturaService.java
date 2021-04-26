package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.model.Livro;

@Service
public interface FaturaService {
	List<Fatura> getAllFaturas();
	
	List<Fatura> pesquisar(String pesquisa);

	void salvarFatura(Fatura fatura);

	Fatura getFaturaById(Long id);

	void deletarFaturaById(Long id);

	Page<Fatura> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
