package org.weblioteca.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.repository.EditoraRepository;

@Service
public class EditoraServiceImpl implements EditoraService {
	@Autowired
	private EditoraRepository editoraRepository;

	@Override
	public List<Editora> getAllEditoras() {
		return editoraRepository.findAll();
	}

	@Override
	public List<Editora> pesquisar(Integer ativo, String pesquisa){
		return editoraRepository.pesquisar(ativo, pesquisa);	
	}
	
	@Override
	public List<Editora> findByNomeContainingIgnoreCase(String nome) {
		List<Editora> editora = editoraRepository.findByNomeContainingIgnoreCase(nome);
		return editora;
	}
	
	@Override
	public void salvarEditora(Editora editora) {
		editoraRepository.save(editora);
	}

	@Override
	public Editora getEditoraById(Long id) {
		Optional<Editora> optional = editoraRepository.findById(id);
		Editora editora = null;
		if (optional.isPresent()) {
			editora = optional.get();
		} else {
			throw new RuntimeException("Editora nao encontrado com id = " + id);
		}
		return editora;
	}

	@Override
	public void deletarEditoraById(Long id) {
		editoraRepository.deleteById(id);
	}

	@Override
	public Page<Editora> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer ativo) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.editoraRepository.findAllAtivos(ativo, pageable);
	}
}
