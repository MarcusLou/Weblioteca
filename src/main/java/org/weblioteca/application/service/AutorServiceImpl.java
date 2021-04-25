package org.weblioteca.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.repository.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public List<Autor> pesquisar(Integer ativo, String pesquisa){
		return autorRepository.pesquisar(ativo, pesquisa);	
	}
	
	@Override
	public List<Autor> findByNomeContainingIgnoreCase(String nome) {		
		List<Autor> autor = autorRepository.findByNomeContainingIgnoreCase(nome);
		return autor;
	}
		
	@Override
	public void salvarAutor(Autor autor) {
		autorRepository.save(autor);
	}

	@Override
	public Autor getAutorById(Long id) {
		Optional<Autor> optional = autorRepository.findById(id);
		Autor autor = null;
		if (optional.isPresent()) {
			autor = optional.get();
		} else {
			throw new RuntimeException("Autor nao encontrado com id = " + id);
		}
		return autor;
	}

	@Override
	public void deletarAutorById(Long id) {
		autorRepository.deleteById(id);
	}

	@Override
	public Page<Autor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, Integer ativo) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.autorRepository.findAllAtivos(ativo, pageable);
	}
}
