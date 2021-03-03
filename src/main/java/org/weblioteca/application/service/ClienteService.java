package org.weblioteca.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.weblioteca.application.model.Cliente;

@Service
public interface ClienteService {
	List<Cliente> getAllClientes();

	List<Cliente> findByNomeContainingIgnoreCase(String nome);

	void salvarCliente(Cliente cliente);

	Cliente getClienteById(Long id);

	void deletarClienteById(Long id);

	Page<Cliente> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
