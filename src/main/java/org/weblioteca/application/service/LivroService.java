package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Livro;


@Service
public interface LivroService {
	List<Livro> getAllLivros();
	
	List<Livro> findByTituloLivroContainingIgnoreCase(String tituloLivro);

	void salvarLivro(Livro livro);

	Livro getLivroById(Long id);

	void deletarLivroById(Long id);

	Page<Livro> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
