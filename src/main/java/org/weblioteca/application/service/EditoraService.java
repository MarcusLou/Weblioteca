package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Editora;

@Service
public interface EditoraService {
	List<Editora> getAllEditoras();

	List<Editora> findByNomeContainingIgnoreCase(String nome);
	
	List<Editora> pesquisar(Integer ativo, String pesquisa);

	void salvarEditora(Editora editora);

	Editora getEditoraById(Long id);

	void deletarEditoraById(Long id);

	Page<Editora> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer ativo);
}
