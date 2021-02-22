package org.weblioteca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.weblioteca.application.model.Cliente;

@Repository                              //CrudRepository<Cliente, Long>
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
