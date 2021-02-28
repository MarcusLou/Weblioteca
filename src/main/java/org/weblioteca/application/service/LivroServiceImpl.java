package org.weblioteca.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.repository.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {
	@Autowired
	private LivroRepository livroRepository;

	@Override
	public List<Livro> getAllLivros() {
		return livroRepository.findAll();
	}
	
	@Override
	public List<Livro> findByTituloLivroContainingIgnoreCase(String tituloLivro) {
		List<Livro> livro = livroRepository.findByTituloLivroContainingIgnoreCase(tituloLivro);
		return livro;
	}

	@Override
	public void salvarLivro(Livro livro) {
		livroRepository.save(livro);

	}

	@Override
	public Livro getLivroById(Long id) {
		Optional<Livro> optional = livroRepository.findById(id);
		Livro livro = null;
		if (optional.isPresent()) {
			livro = optional.get();
		} else {
			throw new RuntimeException("Livro nao encontrado com id = " + id);
		}
		return livro;
	}

	@Override
	public void deletarLivroById(Long id) {
		livroRepository.deleteById(id);

	}

	@Override
	public Page<Livro> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.livroRepository.findAll(pageable);
	}

}
