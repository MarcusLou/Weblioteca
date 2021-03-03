package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Funcionario;

@Service
public interface FuncionarioService {
	List<Funcionario> getAllFuncionarios();

	void salvarFuncionario(Funcionario funcionario);

	Funcionario getFuncionarioById(Long id);

	void deletarFuncionarioById(Long id);

	Page<Funcionario> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
